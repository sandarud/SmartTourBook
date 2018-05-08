package com.example.sandarudaminda.smarttourbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public Button Btn_curr;

    public void currentclick(View view)
    {
        Btn_curr=(Button)findViewById(R.id.Btn_curr);
        Btn_curr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdr=new Intent(MainActivity.this,currentLocation.class);
                startActivity(sdr);
            }
        });
    }
    public Button Btn_search;
    public void Loc_search(View view)
    {
        Btn_search=(Button)findViewById(R.id.Btn_search);
        Btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sdr1=new Intent(MainActivity.this,SearchLocation.class);
                startActivity(sdr1);


            }
        });

    }


}
