package sena.edu.co.remindme2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CamaraActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab_plus;
    FloatingActionButton fab_camara;
    FloatingActionButton fab_galery;
    FloatingActionButton fab_share;

    Animation anim_open;
    Animation anim_close;
    Animation anim_forward;
    Animation anim_backward;
    Boolean isOpen = false;

    ImageView imgPrueba;

    private final String CARPETA_RAIZ="GaleryWS/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";
    private final int COD_GALERY = 10;
    private final int COD_IMAGEN = 20;
    String path = "";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_camara = (FloatingActionButton) findViewById(R.id.fab_camara);
        fab_galery = (FloatingActionButton) findViewById(R.id.fab_galery);
        fab_share = (FloatingActionButton) findViewById(R.id.fab_share);
        imgPrueba = (ImageView) findViewById(R.id.imgPrueba);

        anim_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        anim_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        anim_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_forward);
        anim_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_backward);

        fab_camara.setVisibility(View.INVISIBLE);
        fab_galery.setVisibility(View.INVISIBLE);
        fab_share.setVisibility(View.INVISIBLE);

        fab_plus.setOnClickListener(this);
        fab_camara.setOnClickListener(this);
        fab_galery.setOnClickListener(this);
        fab_share.setOnClickListener(this);


    }

    private void tomarFoto() {
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreated = fileImagen.exists();
        String nombreImg = "";

        if (!isCreated){
            isCreated = fileImagen.mkdirs();
        }

        if (isCreated) {
            nombreImg = (System.currentTimeMillis() / 1000) + ".jpg";
        }


        path = Environment.getExternalStorageDirectory() +
                File.separator + RUTA_IMAGEN + File.separator + nombreImg;

        File imagen = new File(path);

        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamara.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        startActivityForResult(intentCamara, COD_IMAGEN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode)
            {
                case COD_GALERY:
                    Uri miPath = data.getData();
                    imgPrueba.setImageURI(miPath);
                    break;

                case COD_IMAGEN:
                    MediaScannerConnection.scanFile(this,new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener(){

                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de la Imagen ","Path: "+path);
                                }
                            });
                    Intent intentPhoto = new Intent(this, PhotoActivity.class); // Nuevo
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    intentPhoto.putExtra("BitmapImage", bitmap);
                    // imgPrueba.setImageBitmap(bitmap);
                    break;
            }


        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab_plus:

                if (isOpen) {
                    fab_plus.startAnimation(anim_backward);
                    fab_camara.startAnimation(anim_close);
                    fab_galery.startAnimation(anim_close);
                    fab_share.startAnimation(anim_close);
                    fab_camara.setClickable(false);
                    fab_galery.setClickable(false);
                    fab_share.setClickable(false);
                    fab_camara.setVisibility(View.INVISIBLE);
                    fab_galery.setVisibility(View.INVISIBLE);
                    fab_share.setVisibility(View.INVISIBLE);
                    isOpen = false;
                } else {
                    fab_plus.startAnimation(anim_forward);
                    fab_camara.startAnimation(anim_open);
                    fab_galery.startAnimation(anim_open);
                    fab_share.startAnimation(anim_open);
                    fab_camara.setClickable(true);
                    fab_galery.setClickable(true);
                    fab_share.setClickable(true);
                    fab_camara.setVisibility(View.VISIBLE);
                    fab_galery.setVisibility(View.VISIBLE);
                    fab_share.setVisibility(View.VISIBLE);
                    isOpen = true;
                }

                break;
            case R.id.fab_camara:
                tomarFoto();
                Toast.makeText(this,"Camara", Toast.LENGTH_SHORT).show();

                break;
            case R.id.fab_galery:
                Intent intentGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intentGalery.setType("image/");
                Toast.makeText(this,"Galery", Toast.LENGTH_SHORT).show();
                startActivityForResult(Intent.createChooser(intentGalery,"Abrir con..."),COD_GALERY);
                break;
            case R.id.fab_share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                String shareBody = "World Skills";
                String shareSub= "Cuerpo";
                intentShare.putExtra(Intent.EXTRA_TEXT,shareBody);
                intentShare.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                startActivity(Intent.createChooser(intentShare,"Compartir por..."));
                Toast.makeText(this,"Share", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}

































