package data;

/**
 * Created by Briam on 31/10/2014.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWrite {
    public void writeNumbers (int pTamano) {

        JSONObject obj = new JSONObject();


        JSONArray list = new JSONArray();
        for(int i = 0 ; i < pTamano; i++){
            int ram = (int) (Math.random() * 100000);
            list.add(ram + "");
        }
//		list.add("tag 1");
//		list.add("tag 2");
//		list.add("tag 3");
//
        obj.put("Tags", list);

//		JSONObject innerObj = new JSONObject();
//		innerObj.put("PostX","Escribir un JSON");
//		innerObj.put("PostY", "Leer un JSON");
//		innerObj.put("PostZ", "lalala");
//
//		obj.put("Posts",innerObj);

        try {
            System.out.println("Guardando");
            FileWriter file = new FileWriter("src/Numeros.json");
            System.out.println("Guardado");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            //manejar error
        }

        //System.out.print(obj);

    }


    public void writeString (int pTamano) {

        JSONObject obj = new JSONObject();


        JSONArray list = new JSONArray();
        //LeerTexto leer = new LeerTexto();
//
        //String [] vieja = leer.leerArreglo("CROSSWD.TXT");
        String [] nueva = new String [pTamano] ;
        //Lista <String> nuevo = new Lista<>();
        for (int i = 0; i < pTamano; i++ ){
            int random = (int) (Math.random() * 100000);
            //nueva [i] = vieja[random];
            //nuevo.insertarFinal(vieja[random]);
            //list.add(vieja[random]);
        }
//		list.add("tag 1");
//		list.add("tag 2");
//		list.add("tag 3");
//
        obj.put("Tags", list);

//		JSONObject innerObj = new JSONObject();
//		innerObj.put("PostX","Escribir un JSON");
//		innerObj.put("PostY", "Leer un JSON");
//		innerObj.put("PostZ", "lalala");
//
//		obj.put("Posts",innerObj);

        try {
            System.out.println("Guardando");
            FileWriter file = new FileWriter("src/String.json");
            System.out.println("Guardado");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            //manejar error
        }

        //System.out.print(obj);

    }

}