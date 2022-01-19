package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Googlemaps(View view){
        startActivity(new Intent(MainActivity.this,MapsActivity.class));
        overridePendingTransition(R.anim.hiper_space_jump,R.anim.hiper_space_jump);
    }

    public void camara(View view){
        startActivity(new Intent(MainActivity.this,CamaraActivity.class));
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
    public void Basedatos(View view){

        startActivity(new Intent(MainActivity.this,BasedatosActivity.class));
        overridePendingTransition(R.anim.left_in,R.anim.left_out);

    }
}