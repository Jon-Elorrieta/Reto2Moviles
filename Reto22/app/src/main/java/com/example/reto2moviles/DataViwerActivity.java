package com.example.reto2moviles;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataViwerActivity extends AppCompatActivity {

    private TableLayout tablaMediciones;
    private RequestQueue requestQueue;
    private Context contexto;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_table);
        tablaMediciones = findViewById(R.id.tablaMediciones);
        contexto = this;
    }

    private void cargarDatos(){
        //Cogemos los datos dados por la activity anterior
        Bundle datos = getIntent().getExtras();
        String fecha = datos.getString("fecha");
        String estacion = datos.getString("estacion");
        //Ahora llamamos al servidor para que nos devuelva los datos que nos interesan
        String url = "http://10.5.13.44/android/MedicionesEstacion.php?Estacion="+estacion+"&Fecha="+fecha;
        requestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("mediciones");
                    for(int i=0; i<jsonArray.length();i++){
                        TableRow fila = new TableRow(contexto);
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TextView hora = new TextView(contexto);
                        hora.setText(jsonObject.getString("hora"));
                        fila.addView(hora);
                        TextView comgm3 = new TextView(contexto);
                        comgm3.setText(jsonObject.getString("comgm3"));
                        fila.addView(comgm3);
                        TextView nogm3 = new TextView(contexto);
                        nogm3.setText(jsonObject.getString("nogm3"));
                        fila.addView(nogm3);
                        TextView no2 = new TextView(contexto);
                        no2.setText(jsonObject.getString("no2"));
                        fila.addView(no2);
                        TextView icaestacion = new TextView(contexto);
                        icaestacion.setText(jsonObject.getString("icaestacion"));
                        fila.addView(icaestacion);
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
    }








}
