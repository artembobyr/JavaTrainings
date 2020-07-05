package com.epam.crm.perfuiframework.navigationtiming;

import com.epam.crm.perfuiframework.util.Constants;
import lombok.extern.log4j.Log4j2;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.epam.crm.perfuiframework.util.Constants.*;

@Log4j2
public class PerfNavigationTiming {
    private static List<Map<String, Object>> navigationTimingList = new ArrayList<>();
    private static List<Long> durationList = new ArrayList<>();
    private static List<Long> timerTimingsList = new ArrayList<>();
    private static List<String> pagesList = new ArrayList<>();

    Map<String, Object> timings = null;
    ArrayList resources = null;
    PerfUtil perfUtil = new PerfUtil();
    private final String JavaScriptForPerformance = "var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {};var timings = performance.timing || {};return timings;";
    private final String JavaScriptForResources = "var resources = performance.getEntriesByType('resource') || {};return resources;";
    private final String JavaScriptForCleanResources = "performance.clearResourceTimings();";

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL);

    BatchPoints batchPoints = BatchPoints
            .database(DATABASE_NAME)
            .retentionPolicy("autogen")
            .build();

    public void checkDBConnection() {
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Pong response = this.influxDB.ping();
        if (response.getVersion().equalsIgnoreCase("unknown")) {
            log.debug("Error pinging server.");
        } else log.debug("Connection is perfect");
    }

    public void writeToInflux(WebDriver driver, boolean isFirstTime, String scenarioName, String envName, String pageName) {

        Map<String, Object> navigationTiming = getAllTiming(driver);
        boolean isNotNavigationTiming = false;

        for (Map<String, Object> navTim : navigationTimingList) {
            if (navTim.equals(navigationTiming)) {
                isNotNavigationTiming = true;
                break;
            }
        }

        if (isFirstTime && !navigationTimingList.isEmpty() && isNotNavigationTiming) {
            markStartPoint(driver);
            timerTimingsList.add(System.currentTimeMillis());
            log.debug("\nSuccessfully marked start point");
            return;
        } else if (!isFirstTime && !navigationTimingList.isEmpty() && isNotNavigationTiming) {
            writeActionsToInflux(driver, scenarioName, envName, pageName);
            pagesList.add(pageName);
            log.debug("\nSuccessfully wrote action to influx");
            return;
        }

        navigationTimingList.add(navigationTiming);
        pagesList.add(pageName);
        log.debug("This Is Navigation Timing Case");

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("scenario", scenarioName)
                .tag("env", envName)
                .tag("page", pageName)
                .addField("latency", this.getLatency())
                .addField("backend_response", this.getBackend_response())
                .addField("tti", this.getTimeToInteract())
                .addField("ttl", this.getTimeToLoad())
                .addField("onload", this.getOnload())
                .addField("total_time", this.getTotal_time())
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public void writeToInflux(WebDriver driver, boolean isFirstTime, String scenarioName, String envName, String pageName, long difference) {

        Map<String, Object> navigationTiming = getAllTiming(driver);
        boolean isNotNavigationTiming = false;

        for (Map<String, Object> navTim : navigationTimingList) {
            if (navTim.equals(navigationTiming)) {
                isNotNavigationTiming = true;
                break;
            }
        }

        if (isFirstTime && !navigationTimingList.isEmpty() && isNotNavigationTiming) {
            markStartPoint(driver);
            timerTimingsList.add(System.currentTimeMillis());
            log.debug("\nSuccessfully marked start point");
            return;
        } else if (!isFirstTime && !navigationTimingList.isEmpty() && isNotNavigationTiming) {
            writeActionsToInflux(driver, scenarioName, envName, pageName, difference);
            pagesList.add(pageName);
            log.debug("\nSuccessfully wrote action to influx with difference");
            return;
        }

        navigationTimingList.add(navigationTiming);
        pagesList.add(pageName);
        log.debug("This Is Navigation Timing Case");

        if (this.getTotal_time() < 0) // For issue with Navigation timing data in new window
            return;

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("scenario", scenarioName)
                .tag("env", envName)
                .tag("page", pageName)
                .addField("latency", this.getLatency())
                .addField("backend_response", this.getBackend_response())
                .addField("tti", this.getTimeToInteract())
                .addField("ttl", this.getTimeToLoad())
                .addField("onload", this.getOnload())
                .addField("total_time", this.getTotal_time())
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public void markStartPoint(WebDriver driver) {
        JSONObject filteredResources = getAllResources(driver);
        perfUtil.getResourceDuration(filteredResources, true);
    }

    public void writeActionsToInflux(WebDriver driver, String scenarioName, String envName, String pageName) {
        if (!pagesList.isEmpty() && pagesList.contains(pageName)){
            return;
        }
        JSONObject filteredResources = getAllResources(driver);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        long totalDuration = perfUtil.getResourceDuration(filteredResources, false);

        boolean overrideTimer = true; //Counts action time only with timer

        log.debug("\nDuration LIST: " + durationList);
        //if (durationList.size() > 0 && (overrideTimer | (durationList.contains(totalDuration) | totalDuration == 0))) {
        if (overrideTimer | (durationList.contains(totalDuration) | totalDuration == 0)) {
            long endActionTime = System.currentTimeMillis();
            totalDuration = endActionTime - timerTimingsList.get(timerTimingsList.size()-1);
        }

        durationList.add(totalDuration);

        //long ajaxDuration = perfUtil.getResourceDurationByInitiatorType(filteredResources, INITIATOR_AJAX);
        //long scriptDuration = perfUtil.getResourceDurationByInitiatorType(filteredResources, INITIATOR_SCRIPT);
        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("scenario", scenarioName)
                .tag("env", envName)
                .tag("page", pageName + "Action")
                .addField("duration", totalDuration)
                //.addField("ajaxDuration", ajaxDuration)
                //.addField("scriptDuration", scriptDuration)
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public void writeActionsToInflux(WebDriver driver, String scenarioName, String envName, String pageName, long difference) {
        if (!pagesList.isEmpty() && pagesList.contains(pageName)){
            return;
        }
        JSONObject filteredResources = getAllResources(driver);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        long totalDuration = perfUtil.getResourceDuration(filteredResources, false);

        boolean overrideTimer = true; //Counts action time only with timer

        log.debug("\nDuration LIST: " + durationList);
        if (overrideTimer | (durationList.contains(totalDuration) | totalDuration == 0)) {
            long endActionTime = System.currentTimeMillis();
            totalDuration = endActionTime - timerTimingsList.get(timerTimingsList.size()-1);
        }

        log.debug("Total action's duration without 'Difference': " + totalDuration);
        totalDuration -= difference;
        log.debug("Total action's duration with 'Difference': " + totalDuration);
        durationList.add(totalDuration);

        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("scenario", scenarioName)
                .tag("env", envName)
                .tag("page", pageName + "Action")
                .addField("duration", totalDuration)
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public void writeTimerMeasuredActionsToInflux(long startTime, long endTime, String scenarioName, String envName, String pageName) {
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("clientSide")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("scenario", scenarioName)
                .tag("env", envName)
                .tag("page", pageName + "TimerMeasured")
                .addField("duration", (endTime - startTime))
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    public Map<String, Object> getAllTiming(WebDriver driver) {
        JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
        timings = (Map<String, Object>) jsrunner.executeScript(JavaScriptForPerformance);
        return timings;
    }


    public void cleanAllResources(WebDriver driver) {
        JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
        jsrunner.executeScript(JavaScriptForCleanResources);
    }

    public JSONObject getAllResources(WebDriver driver) {
        JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
        resources = (ArrayList) jsrunner.executeScript(JavaScriptForResources);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectFiltered = new JSONObject();
        JSONArray jsonArray = new JSONArray(resources);
        jsonObject.put("resources", jsonArray);
        JSONArray jsonArrayFiltered = new JSONArray();

        JSONArray jsonArrayIterable = jsonObject.getJSONArray("resources");
        for (int i = 0; i < jsonArrayIterable.length(); i++) {
            JSONObject obj = jsonArrayIterable.getJSONObject(i);
            String initiatorType = obj.getString("initiatorType");
            if (initiatorType.equalsIgnoreCase("script") | initiatorType.equalsIgnoreCase("xmlhttprequest")
                    | initiatorType.equalsIgnoreCase("link")) {
                jsonArrayFiltered.put(obj);
            }
        }
        jsonObjectFiltered.put("filteredResources", jsonArrayFiltered);
        try (FileWriter file = new FileWriter("resources.json");
             FileWriter file2 = new FileWriter("resourcesFiltered.json")) {
            jsonObject.write(file);
            jsonArrayFiltered.write(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObjectFiltered;
    }

    //raw data
    private Long getAnTime(String name) {
        return (Long) timings.get((Object) name);
    }

    public Long getnavigationStart() {
        return getAnTime("navigationStart");
    }

    public Long getunloadEventStart() {
        return getAnTime("unloadEventStart");
    }

    public Long getunloadEventEnd() {
        return getAnTime("unloadEventEnd");
    }

    public Long getredirectStart() {
        return getAnTime("redirectStart");
    }

    public Long getredirectEnd() {
        return getAnTime("redirectEnd");
    }

    public Long getfetchStart() {
        return getAnTime("fetchStart");
    }

    public Long getdomainLookupStart() {
        return getAnTime("domainLookupStart");
    }

    public Long getdomainLookupEnd() {
        return getAnTime("domainLookupEnd");
    }

    public Long getconnectStart() {
        return getAnTime("connectStart");
    }

    public Long getconnectEnd() {
        return getAnTime("connectEnd");
    }

    public Long getsecureConnectionStart() {
        return getAnTime("secureConnectionStart");
    }

    public Long getrequestStart() {
        return getAnTime("requestStart");
    }

    public Long getresponseStart() {
        return getAnTime("responseStart");
    }

    public Long getresponseEnd() {
        return getAnTime("responseEnd");
    }

    public Long getdomLoading() {
        return getAnTime("domLoading");
    }

    public Long getdomInteractive() {
        return getAnTime("domInteractive");
    }

    public Long getdomContentLoadedEventStart() {
        return getAnTime("domContentLoadedEventStart");
    }

    public Long getdomContentLoadedEventEnd() {
        return getAnTime("domContentLoadedEventEnd");
    }

    public Long getdomComplete() {
        return getAnTime("domComplete");
    }

    public Long getloadEventStart() {
        return getAnTime("loadEventStart");
    }

    public Long getloadEventEnd() {
        return getAnTime("loadEventEnd");
    }


    //results
    public long getLatency() {
        return getresponseStart() - getnavigationStart();
    }

    public long getBackend_response() {
        return getresponseEnd() - getresponseStart();
    }

    public long getTimeToInteract() {
        return getdomInteractive() - getdomLoading();
    }

    public long getTimeToLoad() {
        return getdomComplete() - getdomInteractive();
    }

    public long getOnload() {
        return getloadEventEnd() - getloadEventStart();
    }

    public long getTotal_time() {
        return getloadEventEnd() - getnavigationStart();
    }


}
