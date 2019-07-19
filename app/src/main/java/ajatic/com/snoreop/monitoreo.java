package ajatic.com.snoreop;

import android.content.Intent;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class monitoreo extends AppCompatActivity {

    ListView lvMonitoreo;
    Button btnResultados;
    double promedio = 65;
    ArrayList<String> alertas =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo);

        lvMonitoreo=findViewById(R.id.lvMonitoreo);
        btnResultados=findViewById(R.id.btnResultados);
        promedio = promedio + (promedio * 0.05);
        //Inicializando la grabacion
        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new monitoreo.RecorderTask(recorder), 0, 5000);
        recorder.setOutputFile("/dev/null");
        try {
            recorder.prepare();
            recorder.start();
        } catch(IllegalStateException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    // Esta es la funcion que esta escuchando (grabando)
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

                    double amplitudeDb = 20 * Math.log10((double)Math.abs(amplitude));
                    DecimalFormat df = new DecimalFormat("#.00");
                    //Realizacion de comparacion del valor obtenido en db con el promedio

                    Log.w("promedio","" + promedio);

                    if(promedio <= amplitudeDb){
                        alertas.add(df.format(amplitudeDb));
                        ArrayAdapter <String> adaptadorMonitoreo = new ArrayAdapter<String>(monitoreo.this,android.R.layout.simple_spinner_item,alertas);
                        lvMonitoreo.setAdapter(adaptadorMonitoreo);


                    }



                }
            });
        }
    }

}
