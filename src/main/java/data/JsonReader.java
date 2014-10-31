package data;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pablo on 15/10/14.
 */
public class JsonReader {
    public JsonReader(String fileName) throws MalformedURLException {
        URL url = new URL(fileName);
    }
}
