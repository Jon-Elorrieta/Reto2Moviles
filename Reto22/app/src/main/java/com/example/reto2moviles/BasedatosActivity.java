package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BasedatosActivity extends AppCompatActivity{
Spinner spinner;
    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedatos);

      spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TablasBD, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        txt1 = findViewById(R.id.editTextTextPersonName6);
        txt2 = findViewById(R.id.editTextTextPersonName7);
        txt3 = findViewById(R.id.editTextTextPersonName8);
        txt4 = findViewById(R.id.editTextTextPersonName9);



    }


    public void volver(View view){
        onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }


    public void Buscar(View view){

    String seleccion = spinner.getSelectedItem().toString();


    if(seleccion.equals("Espacios Naturales")){

String URL = "http://127.0.0.1/android/Selectespaciosnaturales.php";
        BuscarProd(URL);


    }else if(seleccion.equals("Estaciones")){
        Toast.makeText(this,"Espacios natu2",Toast.LENGTH_SHORT).show();

    }else if(seleccion.equals("Provincias")){
        Toast.makeText(this,"Espacios natu3",Toast.LENGTH_SHORT).show();
    }else if(seleccion.equals("Pueblos")){

    }else if(seleccion.equals("Estaciones")){

    }else if(seleccion.equals("Mediciones")){

    }else if(seleccion.equals("Espacios Favoritos")){

    }else if(seleccion.equals("Pueblos Favoritos")){

    }else if(seleccion.equals("Fotos Espacios")){

    }else if(seleccion.equals("Fotos Pueblo")){

    }



    }





    private void BuscarProd(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject=null;
                        for(int i=0;i<response.length();i++){
                            try {
                                jsonObject = response.getJSONObject(i);
                                txt1.setText(jsonObject.getString("tipo"));
                                txt2.setText(jsonObject.getString("latitud"));
                                txt3.setText(jsonObject.getString("longitud"));

                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        }
        ){

        };
        requestQueue =Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }


    private void ejecutarServicio(String URL){
        StringRequest stringrequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Usuario Añadido Correctamente a la base de datos!", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
               // parametros.put("nombre",nombre.getText().toString());
               // parametros.put("contra",contra.getText().toString());
                return parametros;
            }
        };
      requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }



}