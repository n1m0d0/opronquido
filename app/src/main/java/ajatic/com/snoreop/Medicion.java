package ajatic.com.snoreop;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    ArrayList<String>arrMedicion=new ArrayList<String>();
    int contador=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);
        lvMediciones =findViewById(R.id.lvMediciones);
        tvPromedio =findViewById(R.id.tvPromedio);
        tiempoEspera=1000;

     /*   Thread ico = new Thread() {

            public void run() {

                try {

                    int tiempo = 0;
                    while (tiempo < tiempoEspera) {

                        sleep(100);
                        tiempo = tiempo + 100;



                    }

                    MediaRecorder recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new RecorderTask(recorder), 0, 5000);
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





                } catch (InterruptedException e) {

                    e.printStackTrace();

                } catch (Exception e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } finally {

                    finish();

                }

            }

        };
        ico.start();*/

        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new RecorderTask(recorder), 0, 5000);
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
                    //Date d = new Date();
                    //CharSequence s = DateFormat.format("MMMM, dddd, yyyy ",d.getTime());
                    //Date currentTime = Calendar.getInstance().getTime();
                    //String fecha=new SimpleDateFormat(currentTime)
                    String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    double amplitudeDb = 20 * Math.log10((double)Math.abs(amplitude));
                    DecimalFormat df = new DecimalFormat("#.00");
                    tvPromedio.setText(""+df.format(amplitudeDb));
                    if(contador >= 2){
                        arrMedicion.add(""+df.format(amplitudeDb));
                        ArrayAdapter adaptadorMedicion=new ArrayAdapter(Medicion.this, android.R.layout.simple_spinner_item,arrMedicion);
                        lvMediciones.setAdapter(adaptadorMedicion);
                    }else{
                        contador++;
                    }

                    /*Abrir base de datos
                    bd conexionBD=new bd(Medicion.this);
                    try {
                        conexionBD.abrir();
                        conexionBD.registrar_promedio(fecha,""+df.format(amplitudeDb));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/


                }
            });
        }
    }





}
