package com.example.reto2moviles;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.content.PackageManagerCompat;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CamaraActivity extends AppCompatActivity {
Button opciones;

ImageView Vistaimg;
String rutaImagen;
    Uri imageUri;


    static final int CAPTURA_IMAGEN= 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        opciones = findViewById(R.id.btn_sacarfoto2);

        Vistaimg= findViewById(R.id.imgView);


    }


    public void opciones(View view){
        mostrarDialogOpciones();
    }





    private void mostrarDialogOpciones() {
        final CharSequence[] opciones={"Elegir de Galeria","Subir Foto","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(CamaraActivity.this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(opciones[i].equals("Subir Foto")) {

                }
                else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        imageChooser();
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    public void Sacarfoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Tenemos camara?
        //if (intent.resolveActivity(getPackageManager()) != null) {
        //startActivityForResult(intent, CAPTURA_IMAGEN);

        File imagenArchivo = null;

        try {
            imagenArchivo = crearImagenn();
            if (imagenArchivo != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.reto2moviles.fileprovider", imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intent,10);
            }
        } catch (IOException ex) {
            Log.e("Error", ex.toString());
        }


    }




    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK) {
            //Bundle extras =data.getExtras();
            //Bitmap imgBitmap = (Bitmap) extras.get("data");
            //Vistaimg.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
            Vistaimg.setImageBitmap(imgBitmap);
        }

        else if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 10) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    Vistaimg.setImageURI(selectedImageUri);
                }
            }


        }


       }

    void imageChooser() {
        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
       // File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Selecciona Imagen"), 10);

    }




    private File crearImagenn() throws IOException{
        String nombreImagen="foto_";
       //File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File directorio3 =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        //String directorio2 = "com.example.reto2moviles.Media";
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio3);
        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }



    public void volver(View view){
       onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);

    }


}