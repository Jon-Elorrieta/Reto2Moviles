package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    EditText nombre;
    EditText pass;


    private final String usuario ="admin";
    private final String contra ="64-6702199895-618110150-98-95-19294-53-37-66-17";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nombre=(EditText) findViewById(R.id.editTextTextPersonName);
        pass=(EditText) findViewById(R.id.editTextTextPass);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desear salir de la aplicacion?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent =new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }else if(keyCode== event.KEYCODE_HOME){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desear salir de la aplicacion?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent =new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode,event);
    }



    public void login(View view){
        String pass2= pass.getText().toString();

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");

            byte dataBytes[]=pass2.getBytes();
            md.update(dataBytes);
            byte resumen2[] =md.digest();
            String s = "";
            for(int i = 0; i < resumen2.length; i++ ) {
                s = s + resumen2[i];
            }
            pass2 = s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (nombre.getText().toString().equals("") || pass.getText().toString().equals("")){
            Toast.makeText(this, R.string.toasterrorvacio, Toast.LENGTH_SHORT).show();
        }else{
            if (nombre.getText().toString().equals(usuario) && pass2.equals(contra)){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
               overridePendingTransition(R.anim.translate,R.anim.fade_on);
            }else{
                Toast.makeText(this, R.string.toasterrorsesion, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registrarse(View view){
        startActivity(new Intent(LoginActivity.this,RegistrarActivity.class));
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        }
    }
