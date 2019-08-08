package ajatic.com.snoreop;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class monitoreo extends AppCompatActivity {

    ListView lvMonitoreo;
    Button btnResultados;
    double promedio;
    ArrayList<String> alertas = new ArrayList<String>();
    Cursor cursorCofiguracion;
    double sensibilidad;
    String idPromedio;
    String medicion;
    int contador = 0;

    /***********************************/
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = null;

    /**********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo);

        lvMonitoreo = findViewById(R.id.lvMonitoreo);
        btnResultados = findViewById(R.id.btnResultados);

        Bundle datos = this.getIntent().getExtras();
        idPromedio = datos.getString("idPromedio");
        Log.w("idPromedio", idPromedio);
        bd conexion = new bd(monitoreo.this);
        try {
            conexion.abrir();
            cursorCofiguracion = conexion.configuracion();
            cursorCofiguracion.moveToFirst();
            sensibilidad = Double.parseDouble(conexion.buscar_sensibilidadId(cursorCofiguracion.getString(1)));
            promedio = Double.parseDouble(conexion.buscar_Promedio(idPromedio));
            Log.w("promedio", "" + promedio);
            Log.w("sensibilidad", "" + sensibilidad);
            conexion.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }


        promedio = promedio + (promedio * sensibilidad);
        Log.w("promediototal", "" + promedio);
        //Inicializando la grabacion
        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new monitoreo.RecorderTask(recorder), 0, 500);
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

        //ES LA 1PARTE QUE SE ENCARGA DE PRESENTAR LOS MENSAJES QUE HAN SIDO INGRESADOS AL DISPOSITIVO
        bluetoothIn = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (btSocket != null) {
                    try {
                        btSocket.close();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();*/
            }
        });

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

                    double amplitudeDb = 20 * Math.log10((double) Math.abs(amplitude));
                    DecimalFormat df = new DecimalFormat("#.00");
                    medicion = df.format(amplitudeDb);

                    //Realizacion de comparacion del valor obtenido en db con el promedio
                    if (promedio <= amplitudeDb) {
                        alertas.add(df.format(amplitudeDb));
                        ArrayAdapter<String> adaptadorMonitoreo = new ArrayAdapter<String>(monitoreo.this, android.R.layout.simple_spinner_item, alertas);
                        lvMonitoreo.setAdapter(adaptadorMonitoreo);
                        bd conexion = new bd(monitoreo.this);
                        try {
                            conexion.abrir();
                            conexion.registrar_monitoreo(idPromedio, medicion);
                            conexion.cerrar();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (contador == 0) {
                            MyConexionBT.write("R2`");
                            contador++;
                        } else {
                            if (contador < 5) {
                                contador++;
                            } else {
                                //cadena de cancelacion
                                //MyConexionBT.write("R2`");
                                contador = 0;
                            }
                        }

                    } else {
                        if (contador > 0 && contador < 5) {
                            contador++;
                        } else {
                            //cadena de cancelacion
                            //MyConexionBT.write("R2`");
                            contador = 0;
                        }
                    }

                }
            });
        }
    }

    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                    Log.w("Escuchando", "" + readMessage);
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    public void onResume() {
        super.onResume();
        address = "98:D3:31:70:8B:84";//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
            }
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        try { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {
        }
    }
}
