package ajatic.com.snoreop;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class tiempoespera extends AppCompatActivity {

    int tiempoEspera=10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempoespera);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent irMedicion =new Intent(tiempoespera.this,Medicion.class);
                startActivity(irMedicion);

            }
        },tiempoEspera);

    }

}
