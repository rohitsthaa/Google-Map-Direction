package api.test.com.futsalnepal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by rohit on 2/3/16.
 *
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
     public static  int position;
        public static Context ctx;

    String jsondata= "[{\"Futsal\": {\"Name\": \"Pepsicola Futsal\", \"Price/h\":\"1200\", \"Location\":\"Lalitpur,Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [25.92,7.22] }}, { \"Futsal\": {\"Name\": \"Duku Futsal Hub\", \"Price/h\":\"1200\", \"Location\":\"Battisputali, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [54.7,6.09] }}, { \"Futsal\": {\"Name\": \"X - Cel Recreation Centre\", \"Price-ph\":\"1200\", \"Location\":\"Baluwatar,Kathamandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [24.77,56.67] }}, { \"Futsal\": {\"Name\": \"Futsal Arena \", \"Price/h\":\"1200\", \"Location\":\"GAA Hall, Thamel, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [1.33,47.06]}}, { \"Futsal\": {\"Name\": \"Royal Futsal\", \"Price/h\":\"1200\", \"Location\":\"Thapagaun, Anamnagar, Kathmandu, Nepal\" ,\"Phone\":\"984756475645\", \"coordinates\": [34.8,49.02]}} ]";
    Integer Index;

    public List<ContactInfo> contactList;


    public ContactAdapter(List<ContactInfo> contactList,Context ctx) {
        this.contactList = contactList;
        this.ctx=ctx;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);


    }



    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        Index = position;

        return position;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_contact, viewGroup, false);

        final TextView status = (TextView)itemView.findViewById(R.id.textView6);
        final TextView role = (TextView)itemView.findViewById(R.id.textView7);
        final Integer number = i;
        Button btn = (Button) itemView.findViewById(R.id.Book);
        Button map =(Button) itemView.findViewById(R.id.Map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MapsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("EXTRA_SESSION_ID", String.valueOf(number));




                Log.w("position",String.valueOf(number));
                ctx.startActivity(intent);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.w("entered","book button");

                try{
                    String number = new SigninActivity(ctx, status, role, 1).execute("admin", "admin").get();
                    Log.w("print:",number);
            }
            catch(ExecutionException | InterruptedException e)
            {
                e.printStackTrace();;

            }}
        });
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected TextView btnNxt;

        String dummy;
        JSONObject geometry;


        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.Title);
            vSurname = (TextView)  v.findViewById(R.id.txtName);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);

            v.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

            position = getPosition();



        }

    }



}