package com.example.reto2moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.content.PackageManagerCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CamaraActivity extends AppCompatActivity {
Button sacarf;
Button guardarf;
ImageView Vistaimg;
String rutaImagen;
    static final int CAPTURA_IMAGEN= 1;
     static final int CAPTURA_IMAGEN_Tamaño_REAL= 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        sacarf = findViewById(R.id.btn_sacarfoto);
        guardarf = findViewById(R.id.btn_sacarfoto);
        Vistaimg= findViewById(R.id.imgView);
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
                    startActivityForResult(intent,CAPTURA_IMAGEN);
                }
            } catch (IOException ex) {
                Log.e("Error", ex.toString());
            }


       }
    // }
       // onActivityResult(intent,REQUEST_CODE);

    //}

    /*
    private void galleryAddPic(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

     */

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode ==CAPTURA_IMAGEN && resultCode==RESULT_OK){
           //Bundle extras =data.getExtras();
           //Bitmap imgBitmap = (Bitmap) extras.get("data");
           //Vistaimg.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
           Vistaimg.setImageBitmap(imgBitmap);
        }
    }

    private File crearImagenn() throws IOException{
        String nombreImagen="foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio);
        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }


String path;

    private File crearImagen() throws IOException {
        //Nombre de la foto
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName ="JPEG_" + timeStamp + "_";

        //Fotos privadas o publicas
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName ,".jpg",storageDir);

        // Path para ser usado despues en el intent con ACTION_VIEW
        path="file:" + image.getAbsolutePath();
        return image;

    }

    public void volver(View view){
       onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);

    }






}