package HTTP;

import java.io.*;
import java.net.URL;

public class CreateXML {
    BufferedReader in;

    public void readURL(String location) throws IOException {
        URL obj = new URL(location);
        in = new BufferedReader(new InputStreamReader(obj.openStream()));
        writeURL();
        in.close();
    }

    public void writeURL() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("tickets.xml"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.write(inputLine);
        }
        out.close();
    }
}
