package com.example.brgye_vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
public String n1,n2,n3,n4,n5,n6,n7;
   EditText t1,t2,t3,t4,t5,t6,t7,t8,t9;
   Button button_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        t1 = (EditText)findViewById(R.id.guardian);
        t2 = (EditText)findViewById(R.id.add);

       t4=(EditText)findViewById(R.id.cpno);
               t5 =(EditText)findViewById(R.id.email);
               t6=(EditText)findViewById(R.id.dd);
               t8=(EditText)findViewById(R.id.mm);
               t9=(EditText)findViewById(R.id.yyyy);
                       t7 =(EditText)findViewById(R.id.Father_name);
                       button_next = (Button)findViewById(R.id.next_btn);


    }


   public void regs_one(){

       button_next .setEnabled(false) ;
        n1 = t1.getText().toString();
    n2 =t2.getText().toString();


        n4=t4.getText().toString();
       n5=t5.getText().toString();
         n6=t6.getText().toString()+"/"+t8.getText().toString()+"/"+t9.getText().toString();
       n7=t7.getText().toString(); //name of father



           Intent intent = new Intent(getApplicationContext(), Registration2.class);


           intent.putExtra("NAME", n1);
           intent.putExtra("Address", n2);


           intent.putExtra("CP", n4);
           intent.putExtra("email", n5);
           intent.putExtra("BDAY", n6);
           intent.putExtra("father", n7);

           startActivity(intent);


    }

    public void reg2(View view) {
regs_one() ;

    }
}