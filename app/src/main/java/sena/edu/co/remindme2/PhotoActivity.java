package sena.edu.co.remindme2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imagen;

    FloatingActionButton fab_plus_photo;
    FloatingActionButton fab_audio_photo;
    FloatingActionButton fab_text_photo;
    Boolean isOpen = false;

    Animation anim_open;
    Animation anim_close;
    Animation anim_forward;
    Animation anim_backward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imagen = findViewById(R.id.imgPhoto);

        anim_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        anim_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        anim_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_forward);
        anim_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_backward);

        fab_plus_photo.setVisibility(View.INVISIBLE);
        fab_audio_photo.setVisibility(View.INVISIBLE);
        fab_text_photo.setVisibility(View.INVISIBLE);

        fab_plus_photo.setOnClickListener(this);
        fab_audio_photo.setOnClickListener(this);
        fab_text_photo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_plus:

                if (isOpen) {
                    fab_plus_photo.startAnimation(anim_backward);
                    fab_audio_photo.startAnimation(anim_close);
                    fab_text_photo.startAnimation(anim_close);
                    fab_audio_photo.setClickable(false);
                    fab_text_photo.setClickable(false);
                    fab_audio_photo.setVisibility(View.INVISIBLE);
                    fab_text_photo.setVisibility(View.INVISIBLE);
                    isOpen = false;
                } else {
                    fab_plus_photo.startAnimation(anim_forward);
                    fab_audio_photo.startAnimation(anim_open);
                    fab_text_photo.startAnimation(anim_open);
                    fab_audio_photo.setClickable(true);
                    fab_text_photo.setClickable(true);
                    fab_audio_photo.setVisibility(View.VISIBLE);
                    fab_text_photo.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                break;
            case R.id.btnSeleccionar:

                cargarImagen();

                break;

        }
    }

    private void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
}
