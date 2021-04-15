package com.example.brgye_vaccination;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Final extends AppCompatActivity {
    String url = "https://recursive-menu.000webhostapp.com/registrer.php";
    // One Button
    Button BSelectImage,up;
    String sDate,stime ;
EditText n_uname,n_upass,weight;
    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
weight = findViewById(R.id.mass);
        n_uname= (EditText)findViewById(R.id.editTextTextPersonName) ;
        n_upass = (EditText)findViewById(R.id.editTextTextPersonName2) ;
up = findViewById(R.id.upload );


        Calendar c = Calendar.getInstance();

        sDate = c.get(Calendar.YEAR) + "-"
                + String.valueOf(c.get(Calendar.MONTH)+1)
                + "-" + c.get(Calendar.DAY_OF_MONTH);


                 stime=c.get(Calendar.HOUR_OF_DAY)
                + ":" + c.get(Calendar.MINUTE);



    }



    public void tapos(View view) {




        String b1 = (getIntent().getStringExtra("z1")).trim();
        String b2 = (getIntent().getStringExtra("z2")).trim();
      //  String b3 = (getIntent().getStringExtra("z3")).trim();
        String b4 = (getIntent().getStringExtra("z4")).trim();
        String b5 = (getIntent().getStringExtra("z5")).trim();
        String b6= (getIntent().getStringExtra("z6")).trim();
        String b7 = (getIntent().getStringExtra("z7")).trim();//father



        String a8 = (getIntent().getStringExtra("n1")).trim();

        String a10= (getIntent().getStringExtra("n3")).trim();
        String a11= (getIntent().getStringExtra("n4")).trim();
        String a12= (getIntent().getStringExtra("n5")).trim();
String gender = (getIntent().getStringExtra("sex")).trim();

        String new_uname = n_uname.getText().toString().trim();
        String new_upass = n_upass.getText().toString().trim();
String etMass = weight.getText().toString().trim();


        if(b1.isEmpty() || b2.isEmpty() ||b4.isEmpty()||b5.isEmpty()
                ||b6.isEmpty()||b7.isEmpty()||a8.isEmpty()||a10.isEmpty()||
                a11.isEmpty()||a12.isEmpty()|| new_uname.isEmpty()||new_upass.isEmpty() ||gender .isEmpty()||etMass.isEmpty() ){

           Toast .makeText(Final.this,"Complete all Fields",Toast.LENGTH_SHORT ).show() ;

        }

        else{
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject =new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if(success.equals("1")){

                            Toast.makeText(Final.this,"Register succesfully !",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(Final.this,"Error!"+e.toString(),Toast.LENGTH_LONG).show();

                    }
            }
            }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Final.this,"ERROR"+error.toString(),Toast.LENGTH_LONG).show();
                }
            }

            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String,String> params = new HashMap<String,String>();
                   params.put("parent_name",b1);
                   params.put("address",b2);

                    params.put("cp_no",b4);
                    params.put("email_add",b5);
                    params.put("birthday",b6);
                    params.put("Father",b7);//father

                    params.put("name_child",a8);

                    params.put("child_bday",a10);
                    params.put("birth_place",a11);
                    params.put("Barangay",a12);


                    params.put("user_name",new_uname);
                    params.put("user_pass",new_upass);
                    params.put("Sex",gender);
                    params.put("date",sDate);
                    params.put("time",stime);
                    params.put("birth_weight(kg)",etMass);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Final.this);
            requestQueue.add(request);
        }



    }

//end of class
}