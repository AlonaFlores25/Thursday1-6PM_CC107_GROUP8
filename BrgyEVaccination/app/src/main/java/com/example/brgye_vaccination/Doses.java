package com.example.brgye_vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Doses extends AppCompatActivity {

  List<String> myVacc ;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doses);


        lv = findViewById(R.id.myDoses );

      myVacc    = new ArrayList<>();

      myVacc .add("(Bcg) 1 dose after birth");
        myVacc .add("(Hepatitis B) 1 dose after birth");
        myVacc .add("(Pentavalent Vacc.) 1 dose for 1 1/2 month|1 dose for 2 1/2 month|1 dose for 3 1/2 month.Total of 3 doses  ");
        myVacc .add("(Oral Polio Vacc.) 1 dose for 1 1/2 month|1 dose for 2 1/2 month|1 dose for 3 1/2 month.Total of 3 doses");
        myVacc .add("(Inactivated Polio Vacc.) 1 dose for 3 1/2 month ");
        myVacc .add("(Pneumococcal Vacc.)1 dose for 1 1/2 month|1 dose for 2 1/2 month|1 dose for 3 1/2 month.Total of 3 doses ");
        myVacc .add("(Measles,Mumps,Rubella ) 1 dose for 9 months and 1 dose for 1 year old");

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,myVacc);
        lv.setAdapter(adapter);
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(Doses.this, myVacc.get(position), Toast.LENGTH_LONG).show();
  }
});
    }
}