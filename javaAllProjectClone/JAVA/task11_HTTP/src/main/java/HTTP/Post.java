package HTTP;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Post {
    public void getPost(String link) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost request = new HttpPost(link);
        request.setEntity(new StringEntity("[1]", ContentType.APPLICATION_JSON));
        HttpResponse response = httpclient.execute(request);
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("Successfully booked ticket");
        }
    }
}

