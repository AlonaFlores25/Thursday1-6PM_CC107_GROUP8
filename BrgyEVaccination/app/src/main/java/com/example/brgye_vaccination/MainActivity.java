package com.example.brgye_vaccination;




import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

String url = "https://recursive-menu.000webhostapp.com/login.php";
EditText n1,n2;
ProgressBar loading;
RequestQueue requestQueue ;
Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


loading = (ProgressBar)findViewById(R.id.loading);
login_btn = (Button)findViewById(R.id.btnlogin);
        n1 =(EditText)findViewById(R.id.uname ) ;
n2=(EditText)findViewById(R.id.upass ) ;

requestQueue =Volley .newRequestQueue(this) ;

    }


    public void reg(View view) {
        regs();
    }

    void regs(){
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }

    public void login_btn(View view) {
        String a1,a2;
        a1 = n1.getText().toString();
        a2 = n2.getText().toString();
      if(!a1.equals("") ||!a2.equals("")){
         login();
      }
      else{
          Toast.makeText(this,"Input Username & Password",Toast.LENGTH_LONG).show();

      }

    }



    public void login(){



loading .setVisibility(View.VISIBLE);
login_btn.setVisibility(View.GONE);
        String   t1 = n1.getText().toString().trim();
        String  t2 = n2.getText().toString().trim();
StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
//starts here

   if(response.equals("success")){

      getData();


       loading .setVisibility(View.GONE);

   }else if(response.equals("Failue")){
       Toast.makeText(MainActivity.this,"Invalid username/password",Toast.LENGTH_LONG).show();
       loading .setVisibility(View.VISIBLE);
       login_btn.setVisibility(View.VISIBLE);

   }

//end here
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

        loading .setVisibility(View.VISIBLE);
        login_btn.setVisibility(View.VISIBLE);

        Toast.makeText(MainActivity.this, "  ERROR , CHECK CONNECTION SERVICE . \n"+error.toString(), Toast.LENGTH_SHORT).show();

    }
}
)
{

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String,String> params = new HashMap<String,String>();
        params.put("user_name",t1);
        params.put("user_pass",t2);


        return params;
    }
};
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }




    //*************************
    static final String kg = "birth_weight(kg)";
    String bigat = "";


private    void getData() {


        String id = n1.getText().toString().trim();

    StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DATA_URL ,
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
                    Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_LONG).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("user_name",id);

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
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
             String parentname = jo.getString(Config.KEY_NAME );
                String childname = jo.getString("name_child" );
String userid =  jo.getString("user_id" );
bigat =jo.getString(kg );
             

                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config .KEY_NAME, input_username  );
                employees.put("name_child", input_username  );
                employees.put("user_id", input_username  );
                employees.put("birth_weight(kg)", bigat );


                list.add(employees);
Intent intent = new Intent(MainActivity .this,Home .class ) ;
intent .putExtra("Parentname",parentname ) ;
             intent .putExtra("child",childname  ) ;
              intent .putExtra("userid",userid ) ;
                intent .putExtra("mass",bigat) ;
startActivity(intent );
finish() ;
                Toast.makeText(MainActivity .this,"Welcome " + parentname ,Toast .LENGTH_SHORT ) .show() ;



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //message

    }








}