package data;

/**
 * Created by Briam on 31/10/2014.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
    public String[] JSONReadInt( String pNombre, int pCantidad) {

        JSONParser parser = new JSONParser();
        String [] arregloInt = new String [pCantidad];
        int cont = 0;
        try {

            Object obj = parser.parse(new FileReader("src/" + pNombre +".json"));

            JSONObject jsonObject = (JSONObject) obj;
//
            // loop array
            JSONArray tags = (JSONArray) jsonObject.get("Tags");
            Iterator<String> iterator = tags.iterator();
            while (cont < pCantidad && iterator.hasNext()) {
                //System.out.println(""+iterator.next());
                arregloInt[cont] = iterator.next();
                cont++;
            }

        } catch (FileNotFoundException e) {
            //manejo de error
        } catch (IOException e) {
            //manejo de error
        } catch (ParseException e) {
            //manejo de error
        }
        return arregloInt;

    }

}