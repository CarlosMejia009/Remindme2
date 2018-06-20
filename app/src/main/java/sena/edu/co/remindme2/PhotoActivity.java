package sena.edu.co.remindme2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab_plus_photo;
    FloatingActionButton fab_audio_photo;
    FloatingActionButton fab_text_photo;

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


    }

    @Override
    public void onClick(View view) {

    }
}
