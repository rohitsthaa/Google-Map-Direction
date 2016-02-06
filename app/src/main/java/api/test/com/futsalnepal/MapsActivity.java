package api.test.com.futsalnepal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, RoutingListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks  {
    // Add a marker in Sydney and move the camera
    LatLng KU = new LatLng(27.618134, 85.537391);
    LatLng buspark = new LatLng(27.704181, 85.317305);
    LatLng buspark1 = new LatLng(27.704181, 75.317305);
    private ArrayList<Polyline> polylines;
    private GoogleMap mMap;
    JSONArray json = new JSONArray();
    String latitude,longitude;
    LatLng end;


    private static final LatLngBounds BOUNDS_JAMAICA= new LatLngBounds(new LatLng(23.704181, 83.317305),
            new LatLng(72.77492067739843, -9.998857788741589));
    private int[] colors = new int[]{R.color.primary_dark,R.color.primary,R.color.primary_light,R.color.accent,R.color.primary_dark_material_light};
    String jsondata= "[{\"Futsal\": {\"Name\": \"Pepsicola Futsal\", \"Price/h\":\"1200\", \"Location\":\"Lalitpur,Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [25.92,7.22] }}, { \"Futsal\": {\"Name\": \"Duku Futsal Hub\", \"Price/h\":\"1200\", \"Location\":\"Battisputali, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [54.7,6.09] }}, { \"Futsal\": {\"Name\": \"X - Cel Recreation Centre\", \"Price-ph\":\"1200\", \"Location\":\"Baluwatar,Kathamandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [24.77,56.67] }}, { \"Futsal\": {\"Name\": \"Futsal Arena \", \"Price/h\":\"1200\", \"Location\":\"GAA Hall, Thamel, Kathmandu, Nepal\", \"Phone\":\"984756475645\", \"coordinates\": [1.33,47.06]}}, { \"Futsal\": {\"Name\": \"Royal Futsal\", \"Price/h\":\"1200\", \"Location\":\"Thapagaun, Anamnagar, Kathmandu, Nepal\" ,\"Phone\":\"984756475645\", \"coordinates\": [27.41,85.19]}} ]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent().getStringExtra("EXTRA_SESSION_ID")!=null && !getIntent().getStringExtra("EXTRA_SESSION_ID").isEmpty()) {

            try {
                json = new JSONArray(jsondata);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.w("intent", getIntent().getStringExtra("EXTRA_SESSION_ID"));
            Integer i = Integer.valueOf(getIntent().getStringExtra("EXTRA_SESSION_ID"));
            try {
                latitude=json.getJSONObject(i).getJSONObject("Futsal").getJSONArray("coordinates").get(0).toString();
                longitude=json.getJSONObject(i).getJSONObject("Futsal").getJSONArray("coordinates").get(1).toString();
                Log.w("lat",String.valueOf(Double.valueOf(latitude)));
                Log.w("long",String.valueOf(Double.valueOf(longitude
                )));
            }catch (JSONException e){
                e.printStackTrace();
            }
                    LatLng start = new LatLng(27.704181, 85.317305);
             end = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
            LatLng end1 =new LatLng(27.704181, 85.317305);




            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(KU, end)
                    .build();
            routing.execute();



        }
            polylines = new ArrayList<>();

    }
    public void onClickBtn(View v)
    {

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);





    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add     markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (getIntent().getStringExtra("EXTRA_SESSION_ID")!=null && !getIntent().getStringExtra("EXTRA_SESSION_ID").isEmpty()) {

            mMap.addMarker(new MarkerOptions().position(end).title("Marker in endpoint"));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(KU, 20);
            mMap.animateCamera(cameraUpdate);
        }

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(KU).title("Marker in KU"));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(KU, 10);
        mMap.animateCamera(cameraUpdate);

        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(27.618383, 85.537435))
                .add(new LatLng(27.637247, 85.484083))  // North of the previous point, but at the same longitude
                .add(new LatLng(27.666157, 85.424789))  // Same latitude, and 30km to the west
                .add(new LatLng(27.704181, 85.317305));  // Same longitude, and 16km to the south


// Set the rectangle's color to red
        rectOptions.color(Color.RED);

// Get back the mutable Polyline
        /*Polyline polyline = mMap.addPolyline(rectOptions);*/
/*
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(true)
                .waypoints(KU, buspark)
                .build();
        routing.execute();*/


    }

    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onRoutingStart() {
        // The Routing Request starts
    }
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex)
    {
        CameraUpdate center = CameraUpdateFactory.newLatLng(KU);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        mMap.moveCamera(center);


        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % colors.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(colors[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }

        // Start` marker
        MarkerOptions options = new MarkerOptions();
        options.position(KU);

        mMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(buspark);

        mMap.addMarker(options);


    }
    @Override
    public void onRoutingCancelled() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {


    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
