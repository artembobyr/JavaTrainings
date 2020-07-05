package com.epam.crm.perfuiframework.navigationtiming;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

@Log4j2
public class PerfUtil {

    private static JSONArray resourcesBlackList = new JSONArray();

    public long getResourceDurationByInitiatorType(JSONObject jsonObjectFiltered, String initiatorType) {
        JSONArray jsonArray = jsonObjectFiltered.getJSONArray("filteredResources");
        ArrayList<Long> resourceRequestStartTimes = new ArrayList<>();
        ArrayList<Long> resourceResponseEndTimes = new ArrayList<>();
        long firstRequestStartTime;
        long lastResponseEndTime;

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String initiatorTypeJson = obj.getString("initiatorType");
                if (initiatorTypeJson.equals(initiatorType)) {
                    long requestStartTime = obj.getLong("startTime");
                    long responseEndTime = obj.getLong("responseEnd");
                    resourceRequestStartTimes.add(requestStartTime);
                    resourceResponseEndTimes.add(responseEndTime);
                }
            }
            firstRequestStartTime = Collections.min(resourceRequestStartTimes);
            lastResponseEndTime = Collections.max(resourceResponseEndTimes);

            log.debug("Resource duration for " + initiatorType + "= " + (lastResponseEndTime - firstRequestStartTime)
                    + " WHERE: First Request Start Time= " + firstRequestStartTime + " ,Last Response End Time= " + lastResponseEndTime);
        } catch (NoSuchElementException ex) {
            log.debug("There are no resources with '" + initiatorType + "' initiator type");
            return 0;
        }

        return lastResponseEndTime - firstRequestStartTime;
    }

    public long getResourceDuration(JSONObject jsonObjectFilteredOld, boolean isStartPoint) {
        JSONObject jsonObjectToFile = new JSONObject();
        JSONObject jsonObjectFiltered;
        JSONArray jsonArray;
        if (isStartPoint) {
            jsonObjectFiltered = jsonObjectFilteredOld;
            jsonArray = jsonObjectFiltered.getJSONArray("filteredResources");
            if (resourcesBlackList.length() > 0) {
                //log.debug("\nLength of BlackList before CleanUP: " + resourcesBlackList.length());
                for (int i = resourcesBlackList.length()-1; i >= 0; i--) {
                    resourcesBlackList.remove(i);
                }
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                resourcesBlackList.put(jsonObject);
            }
           // log.debug("\nLength of BlackList after PUT: " + resourcesBlackList.length());
            return 0;
        } else {
            jsonObjectFiltered = compareLastResourcesAndNewAndRemoveDuplicates(jsonObjectFilteredOld);
        }
        jsonArray = jsonObjectFiltered.getJSONArray("filteredResources");
        ArrayList<Long> resourceRequestStartTimes = new ArrayList<>();
        ArrayList<Long> resourceResponseEndTimes = new ArrayList<>();
        long firstRequestStartTime;
        long lastResponseEndTime;

        resourcesBlackList.put(jsonArray);
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                long requestStartTime = obj.getLong("startTime");
                long responseEndTime = obj.getLong("responseEnd");
                resourceRequestStartTimes.add(requestStartTime);
                resourceResponseEndTimes.add(responseEndTime);
            }
            firstRequestStartTime = Collections.min(resourceRequestStartTimes);
            lastResponseEndTime = Collections.max(resourceResponseEndTimes);

            log.debug("Resource duration= " + (lastResponseEndTime - firstRequestStartTime)
                    + " WHERE: First Request Start Time= " + firstRequestStartTime + " ,Last Response End Time= " + lastResponseEndTime);
        } catch (NoSuchElementException ex) {
            log.debug("There are no resources with Script and AJAX initiator types");
            return 0;
        }
        long totalDuration = lastResponseEndTime - firstRequestStartTime;

        return totalDuration;
    }


    private JSONObject compareLastResourcesAndNewAndRemoveDuplicates(JSONObject newJsonObject) {
        JSONObject uniqueObject = new JSONObject();
        try {
            JSONArray newArray = newJsonObject.getJSONArray("filteredResources");
            JSONArray uniqueArray = new JSONArray();

            for (int i = 0; i < newArray.length(); i++) {
                JSONObject newObj = newArray.getJSONObject(i);
                if (resourcesBlackList.length() == 0) {
                    uniqueArray.put(newObj);
                    resourcesBlackList.put(newObj);
                    continue;
                }
                String resourceNameNew = newObj.getString("name");
                long requestStartTimeNew = newObj.getLong("startTime");
                boolean isExists = false;
                for (int j = 0; j < resourcesBlackList.length(); j++) {
                    JSONObject oldObj = resourcesBlackList.getJSONObject(j);
                    String resourceNameOld = oldObj.getString("name");
                    long responseEndTimeOld = oldObj.getLong("responseEnd");
                    if (resourceNameNew.equalsIgnoreCase(resourceNameOld) | requestStartTimeNew < responseEndTimeOld-3000) {
                        isExists = true;
                        break;
                    }
                }
                if (!isExists) {
                    uniqueArray.put(newObj);
                    resourcesBlackList.put(newObj);
                }
            }
            uniqueObject.put("filteredResources", uniqueArray);

            try (FileWriter file = new FileWriter("resourcesUnique.json");
                 FileWriter file2 = new FileWriter("blacklist.json")) {
                new JSONObject().put("filteredResources", resourcesBlackList).write(file2);
                uniqueObject.write(file);
            }
        } catch (Exception e) {
            return newJsonObject;
        }
        return uniqueObject;
    }

    private JSONObject compareLastResourcesAndNewAndRemoveDuplicatesWithFile(JSONObject newJsonObject) {
        JSONParser parser = new JSONParser();
        JSONObject uniqueObject = new JSONObject();
        Object oldTempObj = null;
        try {
            oldTempObj = parser.parse(new FileReader("resourcesFiltered.json"));
            String readedJSON = oldTempObj.toString();
            JSONObject oldJsonObject = new JSONObject(readedJSON);
            JSONArray oldArray = oldJsonObject.getJSONArray("filteredResources");
            JSONArray newArray = newJsonObject.getJSONArray("filteredResources");
            JSONArray uniqueArray = new JSONArray();

            for (int i = 0; i < newArray.length(); i++) {
                JSONObject newObj = newArray.getJSONObject(i);
                if (i < oldArray.length()) {
                    JSONObject oldObj = oldArray.getJSONObject(i);
                    String resourceNameOld = oldObj.getString("name");
                    String resourceNameNew = newObj.getString("name");
                    if (!resourceNameOld.equals(resourceNameNew))
                        uniqueArray.put(newObj);
                } else {
                    uniqueArray.put(newObj);
                }
                uniqueObject.put("filteredResources", uniqueArray);

                try (FileWriter file = new FileWriter("resourcesUnique.json")) {
                    uniqueObject.write(file);
                }
            }
        } catch (Exception e) {
            return newJsonObject;
        }
        return uniqueObject;
    }


    public static void deleteJsonFiles() {
        File file1 = new File("resources.json");
        File file2 = new File("resourcesFiltered.json");
        File file3 = new File("resourcesUnique.json");

        file1.delete();
        file2.delete();
        file3.delete();

    }

//    public Long getResourceDuration(String resourcesBefore, String resourcesAfter) {
//
//        log.debug("resources before: " + resourcesBefore);
//        log.debug("resources after: " + resourcesAfter);
//        String answer = resourcesAfter.replace(resourcesBefore.replace("}]",""),"");
//
//        log.debug("result: " + answer);
//
//        double result =0;
//        String [] str = new String(answer).split(", \\{");
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < str.length; i++) {
//            String [] bla = str[i].split("=|,");
//            for (int j = 0; j < bla.length; j++) {
//                if (bla[j].contains("duration")) {
//                    list.add(bla[j+1]);
//                }
//            }
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            result += Double.valueOf(list.get(i));
//        }
//        log.debug("Duration: " + result);
//        Long resultLong = (long) result;
//        return resultLong;
//    }
}
