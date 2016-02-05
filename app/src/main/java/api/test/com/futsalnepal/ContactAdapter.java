package api.test.com.futsalnepal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

/**
 * Created by rohit on 2/3/16.
 *
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
        public static Context ctx;

    String jsondata= "[{\"Futsal\": {\"Name\": \"Chandeswori Futsal\", \"coordinates\": [27.632121, 85.507912]}}, {\"Futsal\": {\"Name\": \"Badrakali Enterprises\", \"coordinates\": [27.674072, 85.375833]}}]";


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
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);

    }



    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        return position;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_contact, viewGroup, false);


        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        int position;
        String dummy;
        JSONObject geometry;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            String jsondata= "[{\"Futsal\": {\"Name\": \"Chandeswori Futsal\", \"coordinates\": [27.632121, 85.507912]}}, {\"Futsal\": {\"Name\": \"Badrakali Enterprises\", \"coordinates\": [27.674072, 85.375833]}}]";

            JSONArray json = new JSONArray();
            JSONObject jPeak;


            try {

                json = new JSONArray(jsondata);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < json.length(); i++) {
                try {
                     jPeak = json.getJSONObject(i);

                     geometry = jPeak.getJSONObject("Futsal");
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
                    Log.w("parser",String.valueOf(coordinates));
                } catch (JSONException e) {
                    Log.e("as", "unexpected JSON exception", e);
                }
            }

            try {
                 position= getPosition();
                dummy=json.getJSONObject(getPosition()).getJSONObject("Futsal").getString("Name");
                Log.w("click on:", String.valueOf(json.getJSONObject(getPosition()).getJSONObject("Futsal").getJSONArray("coordinates")));
            }catch (JSONException e){
                e.printStackTrace();
            }
            Log.e("error", dummy);
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            intent.putExtra("EXTRA_SESSION_ID", String.valueOf(position));
            ctx.startActivity(intent);



        }

    }



}