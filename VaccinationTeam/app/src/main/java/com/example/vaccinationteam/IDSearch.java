package com.example.vaccinationteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class IDSearch extends AppCompatActivity {
    ProgressBar p1 ;
    Button click;
EditText ids;
String DATA_URL = "https://recursive-menu.000webhostapp.com/member_getData.php";
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_search);
tv = findViewById(R.id.tvMember) ;
        ids  =findViewById(R.id.tvID) ;
p1 = findViewById(R.id.PB1);
click = findViewById(R.id.findbutton);
        String MemberName = (getIntent().getStringExtra("TeamName")).trim();

        tv.setText(MemberName);


    }






    private    void getData() {
       p1.setVisibility(View.VISIBLE);
        click.setVisibility(View.GONE);

        String id =ids.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,DATA_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {

                            showJSON(response);
                            p1.setVisibility(View.GONE);
                            click.setVisibility(View.VISIBLE);

                        } else {

                            showJSON(response);
                            p1.setVisibility(View.GONE);
                            click.setVisibility(View.VISIBLE);


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(IDSearch.this, ""+error, Toast.LENGTH_LONG).show();

                        p1.setVisibility(View.GONE);
                        click.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id",id);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        String input = ids.getText() .toString() .trim() ;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String parentname = jo.getString("parent_name");
                String childname = jo.getString("name_child" );
                String userid =  jo.getString("user_id" );



                final HashMap<String, String> employees = new HashMap<>();
                employees.put("parent_name", input);
                employees.put("name_child", input);
                employees.put("user_id", input );


                list.add(employees);
                Intent intent = new Intent(IDSearch .this,Vaccination .class ) ;
                intent .putExtra("Parentname",parentname ) ;
                intent .putExtra("child",childname  ) ;
                intent .putExtra("userid",userid ) ;
String passa = tv.getText() .toString() .trim() ;
                intent .putExtra("sub",passa ) ;
                startActivity(intent );
ids.setText("");



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //message

    }

    public void findID(View view) {




        getData();


    }




}