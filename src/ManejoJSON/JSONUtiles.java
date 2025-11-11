package ManejoJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class JSONUtiles {

    public static void grabar(JSONArray array, String archivo) {
        try {
            FileWriter file = new FileWriter(archivo);
            file.write(array.toString());
            file.flush();
            file.close();
            System.out.println(" Datos guardados en: " + archivo);
        } catch (IOException e) {
            System.out.println(" Error al guardar " + archivo );
            e.printStackTrace();
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            System.out.println(" Archivo no encontrado: " + archivo);
            e.printStackTrace();
        }
        return tokener;
    }
}