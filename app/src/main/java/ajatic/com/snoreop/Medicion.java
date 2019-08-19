package ajatic.com.snoreop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Medicion extends AppCompatActivity {
    ListView lvMediciones;
    TextView tvPromedio;
    int tiempoEspera;
    ArrayList<String> arrMedicion = new ArrayList<String>();
    int contador = 0;
    String idPromedio;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        lvMediciones = findViewById(R.id.lvMediciones);
        tvPromedio = findViewById(R.id.tvPromedio);
        tiempoEspera = 24;


        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new RecorderTask(recorder), 0, 1000);
        recorder.setOutputFile("/dev/null");
        try {
            recorder.prepare();
            recorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private class RecorderTask extends TimerTask {
        private MediaRecorder recorder;

        public RecorderTask(MediaRecorder recorder) {
            this.recorder = recorder;
        }

        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int amplitude = recorder.getMaxAmplitude();

                    String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    double amplitudeDb = 20 * Math.log10((double) Math.abs(amplitude));
                    DecimalFormat df = new DecimalFormat("#.00");
                    //tvPromedio.setText("" + df.format(amplitudeDb));
                    tvPromedio.setText("" + amplitudeDb);

                    if (contador == 24) {

                        //Abrir base de datos
                        bd conexionBD = new bd(Medicion.this);
                        try {
                            recorder.stop();
                            String promedio = sacarPromedio(arrMedicion);
                            Log.w("promedio", promedio);
                            conexionBD.abrir();
                            idPromedio = "" + conexionBD.registrar_promedio(fecha, promedio);
                            conexionBD.cerrar();
                            Intent irMonitorei = new Intent(Medicion.this, monitoreo.class);
                            irMonitorei.putExtra("idPromedio", idPromedio);
                            startActivity(irMonitorei);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    } else {

                        if (contador >= 2) {
                            arrMedicion.add("" + df.format(amplitudeDb));
                            ArrayAdapter adaptadorMedicion = new ArrayAdapter(Medicion.this, android.R.layout.simple_spinner_item, arrMedicion);
                            lvMediciones.setAdapter(adaptadorMedicion);
                        }

                    }

                    contador++;

                }
            });
        }
    }

    public String sacarPromedio(ArrayList<String> medicion) {
        String promedio = null;
        double suma = 0;
        int size = medicion.size();
        for (int i = 0; i < size; i++) {
            String numero = medicion.get(i).replace(',', '.');
            suma = suma + (Double.parseDouble(numero));
        }
        double promediar = suma / size;
        //DecimalFormat df = new DecimalFormat("#.00");
        ///promedio = df.format(promediar);
        promedio = "" + promediar;
        Log.w("sacarpromedio", promedio);
        return promedio;
    }
}
