package api.test.com.futsalnepal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * Created by rohit on 2/4/16.
 */
public class MainActivity extends AppCompatActivity {
    private EditText usernameField,passwordField;
    String futsal;
    private TextView status,role,method;
    String jsondata= "[{\"Futsal\": {\"Name\": \"Pepsicola Futsal\", \"Price/h\":\"1200\", \"Location\":\"Lalitpur,Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [25.92,7.22] }}, { \"Futsal\": {\"Name\": \"Duku Futsal Hub\", \"Price/h\":\"1200\", \"Location\":\"Battisputali, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [54.7,6.09] }}, { \"Futsal\": {\"Name\": \"X - Cel Recreation Centre\", \"Price-ph\":\"1200\", \"Location\":\"Baluwatar,Kathamandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [24.77,56.67] }}, { \"Futsal\": {\"Name\": \"Futsal Arena \", \"Price/h\":\"1200\", \"Location\":\"GAA Hall, Thamel, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [1.33,47.06]}}, { \"Futsal\": {\"Name\": \"Royal Futsal\", \"Price/h\":\"1200\", \"Location\":\"Thapagaun, Anamnagar, Kathmandu, Nepal\" ,\"Phone\":\"984756475645\", \"coordinates\": [34.8,49.02]}} ]";
    JSONArray json = new JSONArray();
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.Book);


        if (getIntent().getStringExtra("EXTRA_SESSION_ID")!=null && !getIntent().getStringExtra("EXTRA_SESSION_ID").isEmpty()) {
            Integer i = Integer.valueOf(getIntent().getStringExtra("EXTRA_SESSION_ID"));

            try{
                json = new JSONArray(jsondata);
                 futsal=json.getJSONObject(i).getJSONObject("Futsal").getString("Name");
                  Log.w("print:",futsal);
            }
        catch (JSONException e){e.printStackTrace();}


        }}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




        public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        /*try {
            String number = new SigninActivity(this, status, role, 0).execute(username, password).get();
            Log.w("number",number);
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
*/


                    try {
                        Log.w("hello", "entered");
                        String number =   new SigninActivity(getApplicationContext(), status, role, 1).execute(username, password, futsal).get();
                        Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("Like to pay with Esewa??");
                        builder.setMessage("We are only online payment gateway in Nepal");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog
                                Log.w("enteres","yes");
                                Toast.makeText(getApplicationContext(),"You are booked now ! :)",Toast.LENGTH_LONG).show();

                            }

                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Pay the cash in the office ! :)",Toast.LENGTH_LONG).show();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        Log.w("hello", number);
                    }catch (InterruptedException|ExecutionException e){e.printStackTrace();}

        }



    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        new SigninActivity(this,status,role,1).execute(username, password);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
