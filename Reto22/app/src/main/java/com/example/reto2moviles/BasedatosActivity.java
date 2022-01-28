package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasedatosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerprov, spinnerpueblo, spinnerestaciones,spinnerfecha, spinnerhora;
    ArrayList<String> provList = new ArrayList<>();
    ArrayList<String> puebloList = new ArrayList<>();
    ArrayList<String> estacionesList = new ArrayList<>();
    ArrayList<String> fechaList = new ArrayList<>();
    ArrayList<String> horaList = new ArrayList<>();

    ArrayAdapter<String> provAdapter;
    ArrayAdapter<String> puebloAdapter;
    ArrayAdapter<String> estacionesAdapter;
    ArrayAdapter<String> fechaAdapter;
    ArrayAdapter<String> horaAdapter;

    RequestQueue requestQueue;
    private String URL_pov = "http://10.5.13.44/android/Spinnerprov.php";
    private String URL_pueblos = "http://10.5.13.44/android/Spinnerpueblos.php";

    private String URL_povc = "http://192.168.1.57/android/Spinnerprov.php";
    private String URL_pueblosc = "http://192.168.1.57/android/Spinnerpueblos.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedatos);
        requestQueue = Volley.newRequestQueue(this);
        spinnerprov = findViewById(R.id.spinner);
        spinnerpueblo = findViewById(R.id.spinner3);
        spinnerestaciones = findViewById(R.id.spinner2);
        spinnerfecha = findViewById(R.id.spinner4);
        spinnerhora = findViewById(R.id.spinner5);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URL_pov, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("provincias");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombreprov = jsonObject.optString("nombre");
                        provList.add(nombreprov);
                        provAdapter = new ArrayAdapter<>(BasedatosActivity.this,
                                android.R.layout.simple_spinner_item, provList);
                        provAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerprov.setAdapter(provAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinnerprov.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinner){
            puebloList.clear();
            String selectedprov = adapterView.getSelectedItem().toString();
            String url = "http://10.5.13.44/android/Spinnerpueblos.php?nombre="+selectedprov;
            requestQueue = Volley.newRequestQueue(this);
            Toast.makeText(this, selectedprov, Toast.LENGTH_SHORT).show();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("pueblos");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cityName = jsonObject.optString("Pueblo");
                            puebloList.add(cityName);
                            puebloAdapter = new ArrayAdapter<>(BasedatosActivity.this,
                                    android.R.layout.simple_spinner_item, puebloList);
                            puebloAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerpueblo.setAdapter(puebloAdapter);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
            });
            requestQueue.add(jsonObjectRequest);
            spinnerpueblo.setOnItemSelectedListener(this);
        }else if(adapterView.getId() == R.id.spinner3){
                estacionesList.clear();
                String selectedpueblos = adapterView.getSelectedItem().toString();
            Toast.makeText(this, selectedpueblos, Toast.LENGTH_SHORT).show();
                String url = "http://10.5.13.44/android/Spinnerestaciones.php?Pueblo="+selectedpueblos;
                requestQueue = Volley.newRequestQueue(this);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("estaciones");
                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String estacionName = jsonObject.optString("Nombre");
                                estacionesList.add(estacionName);
                                estacionesAdapter = new ArrayAdapter<>(BasedatosActivity.this,
                                        android.R.layout.simple_spinner_item, estacionesList);
                                estacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerestaciones.setAdapter(estacionesAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                });
                requestQueue.add(jsonObjectRequest);
          spinnerestaciones.setOnItemSelectedListener(this);
            }
        /*

        else if(adapterView.getId() == R.id.spinner2){
            fechaList.clear();
            String selectedestaciones = adapterView.getSelectedItem().toString();
            Toast.makeText(this, selectedestaciones, Toast.LENGTH_SHORT).show();
            String url = "http://10.5.13.44/android/Spinnerfecha.php?Nombre="+selectedestaciones;
            requestQueue = Volley.newRequestQueue(this);




            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("mediciones");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                           Date date ;
                            String date2= jsonObject.optString("Fecha");


                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy dd MM");
                            String formattedDate = sdf.format(date2.split("-"));
                            GregorianCalendar gc = new GregorianCalendar();
                         String[] date3 = date2.split("-");
                            gc.set(Integer.parseInt(date3[0]),Integer.parseInt(date3[1]),Integer.parseInt(date3[2]));
                                date = gc.getTime();



                            fechaList.add(date2);
                            fechaAdapter = new ArrayAdapter<>(BasedatosActivity.this,
                                    android.R.layout.simple_spinner_item, fechaList);
                            fechaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerfecha.setAdapter(fechaAdapter);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
            });
            requestQueue.add(jsonObjectRequest);
            // spinnerfecha.setOnItemSelectedListener(this);
        }

         */

        }






    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public void volver(View view){
        onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
/*

    public void Buscar(View view){

    String seleccion = spinner.getSelectedItem().toString();



        String URL = "http://127.0.0.1/android/Spinnerprovincias.php";
      //  BuscarProd(URL);


    if(seleccion.equals("Espacios Naturales")){




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

    private void mostrarSpinner(){
        try{


        }catch(Exception ex){

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



     */

}
