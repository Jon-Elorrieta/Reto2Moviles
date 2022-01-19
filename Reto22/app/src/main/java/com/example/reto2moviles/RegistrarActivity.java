package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {
EditText nombre;
EditText contra;



ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        nombre = findViewById(R.id.editTextTextPersonName2);
        contra= findViewById(R.id.editTextTextPersonName3);
    }
    public void volver(View view){
        onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }

    public void registrar(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Simple Toast", Toast.LENGTH_LONG);

        if (nombre.getText().toString().equals("") || contra.getText().toString().equals("")){
            Toast.makeText(this, R.string.toasterrorvacio, Toast.LENGTH_LONG).show();

        }else{
            ejecutarServicio("http://10.5.13.44/android/InsertarUsuarios.php");


            Handler handle = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(10); // Incremented By Value 2
                }
            };

                progressDialog = new ProgressDialog(RegistrarActivity.this);
                progressDialog.setMax(100); // Progress Dialog Max Value
                progressDialog.setMessage("Cargando..."); // Setting Message
                progressDialog.setTitle("Añadiendo Usuarios"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax()) {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog.getMax()) {
                                    progressDialog.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                nombre.setText("");
                contra.setText("");
                    /* Otro ProgressDialog de carga
                    progressDialog = new ProgressDialog(RegistrarActivity.this);
                    progressDialog.setMessage("Añadiendo Usuarios"); // Setting Message
                    progressDialog.setTitle("Cargando......"); // Setting Title
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                     */
        }
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
              parametros.put("nombre",nombre.getText().toString());
              parametros.put("contra",contra.getText().toString());
              return parametros;
          }
      };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }
}