package com.example.reto2moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.PrintWriter;
import java.net.Socket;

public class ClienteActivity extends AppCompatActivity {
Socket socket;
/*
final Integer PUERTO = "5000";
    PrintWriter printWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new Socket("10.5.13.44", PUERTO);
                    printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.write("name",nombre);
                }catch (Exception e){
                    e.printStackTrace();
                }
                printWriter.flush();
                printWriter.close();
                socket.close();
            }
        }).start();
    }

*/
}