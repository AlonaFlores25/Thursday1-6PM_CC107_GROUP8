package com.example.vaccinationteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LogForm extends AppCompatActivity {
    EditText n1,n2;
    ProgressBar loading;
    RequestQueue requestQueue ;
    String url = "https://recursive-menu.000webhostapp.com/login_members.php";
    Button login_btn;

    String MY_URL = "https://recursive-menu.000webhostapp.com/get_team.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_form);


        loading = (ProgressBar)findViewById(R.id.loading);
        login_btn = (Button)findViewById(R.id.button );
        n1 =(EditText)findViewById(R.id.uname ) ;
        n2=(EditText)findViewById(R.id.upass ) ;

        requestQueue =Volley .newRequestQueue(this) ;




    }

    public void loginbtn(View view) {
String x1,x2;

x1 = n1.getText() .toString() .trim();
        x2 = n2.getText() .toString() .trim();
        if(x1.equals("")|| x2.equals("")){
            Toast.makeText(LogForm.this,"Check username and password",Toast.LENGTH_SHORT).show() ;

        }
      else{
            login();
        }
    }


    String   t1 = "";
    String  t2 = "";


    public void login(){



        loading .setVisibility(View.VISIBLE);
        login_btn.setVisibility(View.GONE);
         t1 = n1.getText().toString().trim();
     t2 = n2.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//starts here

                if(response.equals("success")){

                   ListData();

                    loading .setVisibility(View.GONE);

                }else if(response.equals("Failue")){
                    Toast.makeText(LogForm .this,"Invalid username/password",Toast.LENGTH_LONG).show();
                    loading .setVisibility(View.GONE);
                    login_btn.setVisibility(View.VISIBLE);

                }

//end here
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading .setVisibility(View.GONE);
                login_btn.setVisibility(View.VISIBLE);

                Toast.makeText(LogForm .this, "  ERROR , CHECK CONNECTION SERVICE . \n"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("username",t1);
                params.put("password",t2);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LogForm.this);
        requestQueue.add(stringRequest);
    }



    //get data from database :

    private    void ListData() {


        String username =n1.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,MY_URL ,
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
                        Toast.makeText(LogForm.this, ""+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username",username); //get user input


                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        String input_username = n1.getText() .toString() .trim() ;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String TeamName = jo.getString("Name");




                final HashMap<String, String> employees = new HashMap<>();
                employees.put("username", input_username);



                list.add(employees);
                Intent intent = new Intent(LogForm .this,IDSearch .class ) ;
                intent .putExtra("TeamName",TeamName ) ;


                startActivity(intent );
                finish() ;



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //message

    }
}