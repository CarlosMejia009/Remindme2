package sena.edu.co.remindme2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView imagenLogo;
    ImageView imagenWsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        imagenLogo = (ImageView) findViewById(R.id.imagenLogo);
        imagenWsi = (ImageView) findViewById(R.id.imagenWsi);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_splash_screen);
        imagenLogo.startAnimation(animation);
        imagenWsi.startAnimation(animation);

        Thread timer = new Thread() {

            Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
            public void run () {
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }


        };
        timer.start();
    }
}
