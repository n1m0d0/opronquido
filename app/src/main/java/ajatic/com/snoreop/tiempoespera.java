package ajatic.com.snoreop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class tiempoespera extends AppCompatActivity {

    int tiempoEspera = 0;
    Cursor cursorConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempoespera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*bd conexion = new bd(tiempoespera.this);
        try {
            conexion.abrir();
            cursorConfiguracion = conexion.configuracion();
            cursorConfiguracion.moveToFirst();
            Log.w("idTiempo", cursorConfiguracion.getString(2));
            String tiempo = conexion.buscar_TiempoInicialId(cursorConfiguracion.getString(2));
            tiempoEspera = (Integer.parseInt(tiempo) * 60) * 1000;
            Log.w("tiempo", tiempo + " = "+ tiempoEspera);
            conexion.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        tiempoEspera = 10000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent irMedicion = new Intent(tiempoespera.this, Medicion.class);
                startActivity(irMedicion);
                finish();

            }
        }, tiempoEspera);

    }

}
