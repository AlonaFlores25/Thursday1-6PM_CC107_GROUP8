package com.example.vaccinationteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Vaccination extends AppCompatActivity {
TextView z1,z2,tvWeight,temperature ;
static String bigat="";
CheckBox y1,y2,y3,y4,y5,y6,y7;
ProgressBar getProg;
ListView lv;
Button btn;
String link = "https://recursive-menu.000webhostapp.com/request_sched.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);









        //******************//
lv = findViewById(R.id.myRecords);

         // your listview inside scrollview
        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        z1 = findViewById(R.id.bata);
        z2 = findViewById(R.id.magulang);
btn = findViewById(R.id.submitbtn );
getProg = findViewById(R.id .progbar);
tvWeight = findViewById(R.id.tvMass);
temperature = findViewById(R.id.tvTemp);
inlist();
        z1.setText( (getIntent().getStringExtra("child")).trim());

        z2.setText( (getIntent().getStringExtra("Parentname")).trim());
        y1 = findViewById(R.id.vc1);
                y2= findViewById(R.id.vc2);
                y3= findViewById(R.id.vc3);
                        y4= findViewById(R.id.vc4);
                        y5= findViewById(R.id.vc5);
                                y6= findViewById(R.id.vc6);
                                y7= findViewById(R.id.vc7);


        Calendar c = Calendar.getInstance();

        sDate = c.get(Calendar.YEAR) + "-"
                + String.valueOf(c.get(Calendar.MONTH)+1)
                + "-" + c.get(Calendar.DAY_OF_MONTH)
                + " at " + c.get(Calendar.HOUR_OF_DAY)
                + ":" + c.get(Calendar.MINUTE);



    }
    String sDate;
    public void submit(View view) {
       getProg.setVisibility(View.VISIBLE );
       btn.setVisibility(View.GONE);

        StringRequest request = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject =new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){

                        Toast.makeText(Vaccination.this,"Thank you !! ",Toast.LENGTH_LONG).show();
                       // Intent intent = new Intent(Vaccination.this,IDSearch.class);
                 //       startActivity(intent);
                        finish();
                        getProg.setVisibility(View.GONE );
                        btn.setVisibility(View.VISIBLE);

                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(Vaccination.this,"Error!"+e.toString(),Toast.LENGTH_LONG).show();
                    getProg.setVisibility(View.GONE );
                    btn.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Vaccination.this,"ERROR"+error.toString(),Toast.LENGTH_LONG).show();
                getProg.setVisibility(View.GONE );
                btn.setVisibility(View.VISIBLE);
            }
        }

        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
bigat = tvWeight.getText().toString().trim();
String myTemp = temperature.getText().toString().trim();
                String m1,m2,m3;
                m3 =   (getIntent().getStringExtra("userid")).trim();
                String getName = (getIntent().getStringExtra("sub")).trim();
                m2 = sDate ;
                m1 = z1.getText().toString() .trim() ;
                params.put("date",m2);
                params.put("child_name",m1);
                params.put("user_id",m3);
                params.put("weight(kg)",bigat);
                params.put("temp",myTemp);
                params.put("staff",getName);
if(y1.isChecked()){
    sel += y1.getText().toString().trim() + "/ ";
    params.put("vaccine", sel);


}

                if(y2.isChecked()){
                    sel += y2.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }
                if(y3.isChecked()){
                    sel += y3.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }
                if(y4.isChecked()){
                    sel += y4.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }
                if(y5.isChecked()){
                    sel += y5.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }
                if(y6.isChecked()){
                    sel += y6.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }
                if(y7.isChecked()){
                    sel += y7.getText().toString().trim() + "/ ";
                    params.put("vaccine", sel);

                }




                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Vaccination.this);
        requestQueue.add(request);
    }
String sel = "";

     String Uid = "";

    //select data from vaccinated babies.......
static final String  myURL = "https://recursive-menu.000webhostapp.com/listView.php";

    static final String myDate = "date";
    static final String myStaff = "staff";
    static final String myVaccine = "vaccine";



    void inlist() {


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
                        Toast.makeText(Vaccination .this, ""+error, Toast.LENGTH_LONG).show();
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
String tempCel = "";
    static String myTemp = "temp";
String bigatin  = "";
    String date ="";
    String Vaccinetype = "";
static  String cute = "weight(kg)";
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
                bigatin = jo.getString(cute);
               tempCel = jo.getString(myTemp);

                vaccinatedby  = jo.getString(myStaff  );


                final HashMap<String, String> employees = new HashMap<>();

                employees.put("vaccine","Vaccine : "+ Vaccinetype  );

                employees.put("staff","Vaccinated By. "+vaccinatedby);
                employees.put("date", "Date :" + date );
                employees.put("weight(kg)", "Weight(kg) :" + bigatin );
                employees.put("temp", "Temp(celcius) :" + tempCel );



                list.add(employees);



                //process code







            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Vaccination.this, list, R.layout.activity_my_list,
                new String[]{myVaccine ,myDate ,myStaff,cute,myTemp},
                new int[]{R.id.tvVaccine,R.id.tvDate,R.id.tvStaff,R.id.tvmabigat,R.id.tvMyTemp});

       lv.setAdapter(adapter);

    }



}
