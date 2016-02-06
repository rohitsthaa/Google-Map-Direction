package api.test.com.futsalnepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohit on 2/3/16.
 */
public class ListActivity extends AppCompatActivity {
    JSONArray json = new JSONArray();
    String jsondata= "[{\"Futsal\": {\"Name\": \"Pepsicola Futsal\", \"Price/h\":\"1200\", \"Location\":\"Lalitpur,Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [25.92,7.22] }}, { \"Futsal\": {\"Name\": \"Duku Futsal Hub\", \"Price/h\":\"1200\", \"Location\":\"Battisputali, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [54.7,6.09] }}, { \"Futsal\": {\"Name\": \"X - Cel Recreation Centre\", \"Price-ph\":\"1200\", \"Location\":\"Baluwatar,Kathamandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [24.77,56.67] }}, { \"Futsal\": {\"Name\": \"Futsal Arena \", \"Price/h\":\"1200\", \"Location\":\"GAA Hall, Thamel, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [1.33,47.06]}}, { \"Futsal\": {\"Name\": \"Royal Futsal\", \"Price/h\":\"1200\", \"Location\":\"Thapagaun, Anamnagar, Kathmandu, Nepal\" ,\"Phone\":\"984756475645\", \"coordinates\": [34.8,49.02]}} ]";

    private List<ContactInfo> createList(int size) {
        try{
        json = new JSONArray(jsondata);}catch (JSONException e){e.printStackTrace();}

        List<ContactInfo> result = new ArrayList<ContactInfo>();
        for (int i=0; i <= 4; i++) {
            ContactInfo ci = new ContactInfo();
            try {
                ci.name = json.getJSONObject(i).getJSONObject("Futsal").getString("Name");
                Log.w("hello",json.getJSONObject(i).getJSONObject("Futsal").getString("Name"));
                ci.surname = json.getJSONObject(i).getJSONObject("Futsal").getString("Location");
                ci.email = json.getJSONObject(i).getJSONObject("Futsal").getString("Phone");
            }catch (JSONException e){e.printStackTrace();}



            result.add(ci);

        }
        return  result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
      setContentView(R.layout.listview_futsal);
      RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
      recList.setHasFixedSize(true);
      LinearLayoutManager llm = new LinearLayoutManager(this);
      llm.setOrientation(LinearLayoutManager.VERTICAL);
      recList.setLayoutManager(llm);


        ContactAdapter ca = new ContactAdapter(createList(30),getApplicationContext());
        recList.setAdapter(ca);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }




}