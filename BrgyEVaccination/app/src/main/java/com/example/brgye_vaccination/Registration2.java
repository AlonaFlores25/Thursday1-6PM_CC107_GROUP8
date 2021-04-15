package com.example.brgye_vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText c1,c2,c3,c4,c5,c6;
RadioButton rad1,rad2;
    Spinner spinner;
    String itemtxt;

    RadioButton radm1,radf2;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
c1 = (EditText)findViewById(R.id.nameofchild);
c2= (EditText)findViewById(R.id.mm1);
        c3= (EditText)findViewById(R.id.mm1);
        c4= (EditText)findViewById(R.id.bplace);
        c5= (EditText)findViewById(R.id.dd1);
        c6= (EditText)findViewById(R.id.yyyy1);
rad1 = (RadioButton)findViewById(R.id.radioButton) ;
rad2 = (RadioButton)findViewById(R.id.radioButton2) ;
        spinner = findViewById(R.id.spinner2);
btn = findViewById(R.id .next_btn2 );

radm1 = findViewById(R.id.radMale);
radf2 = findViewById(R.id.radfemale);




    }

    String sex = "";

    public void regnow(View view) {
if(radm1 .isChecked() ){
    sex = "Male";
}else if(radf2 .isChecked()){
    sex = "Female";
}


        Intent intent = new Intent(getApplicationContext(), Final.class);
        String r8 = c1.getText().toString();
                String r9 =c2.getText().toString();
                String r10 =c3.getText().toString()+"/"+c5.getText().toString()+"/"+c6.getText().toString();
            String r11=c4.getText().toString();
        String spin1 = spinner.getSelectedItem().toString();
        String r1 = (getIntent().getStringExtra("NAME"));
        String r2 =(getIntent().getStringExtra("Address"));

        String r4 =(getIntent().getStringExtra("CP"));
        String r5 = (getIntent().getStringExtra("email"));
        String r6 =(getIntent().getStringExtra("BDAY"));
        String r7 = (getIntent().getStringExtra("father"));


    intent.putExtra("z1", r1);
    intent.putExtra("z2", r2);

    intent.putExtra("z4", r4);
    intent.putExtra("z5", r5);
    intent.putExtra("z6", r6);
    intent.putExtra("z7", r7);//father
    intent.putExtra("n1", r8);
    intent.putExtra("n2", r9);
    intent.putExtra("n3", r10);
    intent.putExtra("n4", r11);
    intent.putExtra("n5", spin1);
 intent.putExtra("sex", sex);
    startActivity(intent);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void r1click(View view) {
      try{  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.District1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);}
      catch (Exception error){
          Toast .makeText(Registration2 .this,"Select District !",Toast.LENGTH_SHORT).show() ;
      }
    }

    public void r2click(View view) {
     try {
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.District2, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
         spinner.setOnItemSelectedListener(this);
     }
         catch (Exception error){
            Toast .makeText(Registration2 .this,"Select District !",Toast.LENGTH_SHORT).show() ;
        }
    }
}