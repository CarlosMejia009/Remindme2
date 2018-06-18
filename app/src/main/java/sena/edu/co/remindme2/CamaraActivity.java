package sena.edu.co.remindme2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class CamaraActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab_plus;
    FloatingActionButton fab_galery;
    FloatingActionButton fab_share;

    Animation anim_open;
    Animation anim_close;
    Animation anim_forward;
    Animation anim_backward;

    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_galery = (FloatingActionButton) findViewById(R.id.fab_galery);
        fab_share = (FloatingActionButton) findViewById(R.id.fab_share);

        anim_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        anim_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        anim_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_forward);
        anim_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_backward);

        fab_plus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_plus:

                if(isOpen){
                    fab_plus.startAnimation(anim_backward);
                    fab_galery.startAnimation(anim_close);
                    fab_share.startAnimation(anim_close);
                    fab_galery.setClickable(false);
                    fab_galery.setClickable(false);
                    isOpen = false;
                } else {
                    fab_plus.startAnimation(anim_forward);
                    fab_galery.startAnimation(anim_open);
                    fab_share.startAnimation(anim_open);
                    fab_galery.setClickable(true);
                    fab_galery.setClickable(true);
                    isOpen = true;
                }

                break;
        }
    }
}

































