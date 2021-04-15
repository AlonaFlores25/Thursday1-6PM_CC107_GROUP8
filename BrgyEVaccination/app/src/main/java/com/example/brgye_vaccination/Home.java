package com.example.brgye_vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
TextView tvName,tviD,weight;

static String myName,myID,myMass;
String myURL ="https://recursive-menu.000webhostapp.com/listView.php";
ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

tvName = findViewById(R.id.tvname) ;
        tviD  = findViewById(R.id.tvId ) ;
        weight  = findViewById(R.id.tvmass ) ;
        myName = (getIntent().getStringExtra("child"));
        myID =  (getIntent().getStringExtra("userid"));
                    tvName .setText("Name :"+myName );
                    tviD .setText("ID no. "+myID );
                    listView = findViewById(R.id.mylist );
myMass =(getIntent().getStringExtra("mass"));
weight.setText("Birth Weight: "+myMass);

Fdata();


    }
 static    String Uid = "";


  void Fdata() {


      Uid = (getIntent().getStringExtra("userid"));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myURL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {

                            showJSON(response);
                            //     mProgressDialog.dismiss();

                        } else {

                            showJSON(response);
                            //   mProgressDialog.dismiss();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home  .this, ""+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id",Uid );

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

String kg = "";
    String date ="";
    String Vaccinetype = "";
    String temperature = "";
static final String myDate = "date";
static final String myStaff = "staff";
static final String myVaccine = "vaccine";
    static final String myCute= "weight(kg)";
    static final String myTemp = "temp";

    String vaccinatedby="";
    private void showJSON(String response) {
        Uid = (getIntent().getStringExtra("userid"));

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                Vaccinetype  = jo.getString(myVaccine  );
                date = jo.getString(myDate);

                vaccinatedby  = jo.getString(myStaff  );
               kg  = jo.getString(myCute  );
                temperature  = jo.getString(myTemp);


                final HashMap<String, String> employees = new HashMap<>();

                employees.put("vaccine","Vaccine : "+ Vaccinetype  );

                employees.put("staff","Vaccinated By. "+vaccinatedby);
                employees.put("date", "Date: " + date );
                employees.put("weight(kg)", "Weight(kg): " + kg );
                employees.put("temp", "Temperature(celcius): " + temperature );




                list.add(employees);



                //process code







            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Home.this, list, R.layout.activity_list,
                new String[]{myVaccine ,myDate ,myStaff,myCute,myTemp },
                new int[]{R.id.tvbata,R.id.tvDate,R.id.tvStaff,R.id.tvMyweight,R.id.tvMyTemp});

        listView .setAdapter(adapter);

    }

    public void doses(View view) {
        Intent intent = new Intent(Home.this,Doses.class);
        startActivity(intent);
    }
}