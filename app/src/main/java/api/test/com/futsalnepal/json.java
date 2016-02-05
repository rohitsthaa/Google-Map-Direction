package api.test.com.futsalnepal;


import android.content.Context;
import android.content.res.AssetManager;

import api.test.com.futsalnepal.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hilson on 10/15/2015.
 */
public class json {
    private static String getJSONString(String jsonFile, Context context) {
        String str = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open(jsonFile);
            InputStreamReader isr = new InputStreamReader(in);
            char [] inputBuffer = new char[100];
            int charRead;
            while((charRead = isr.read(inputBuffer))>0) {
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                str += readString;
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return str;
    }

    public static JSONArray parseJSON(String jsonFile)
    {
        JSONArray json = new JSONArray();


        return json;

        //implement logic with JSON here
    }
}
