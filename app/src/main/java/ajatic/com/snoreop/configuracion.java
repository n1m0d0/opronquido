package ajatic.com.snoreop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class configuracion extends AppCompatActivity {
    Spinner spTiempoInicio;
    EditText etTiempoInicio;
    Spinner spSensibilidad;
    Button btnSiguiente;
    Button btnRegistrate;
    ArrayList<String> tiempos;
    ArrayList<String> sensibilidad;
    String idSensibilidad;
    String idTiempoInicial;
    Cursor cursorConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        spTiempoInicio = findViewById(R.id.spTiempoInicio);
        etTiempoInicio = findViewById(R.id.etTiempoInicio);
        spSensibilidad = findViewById(R.id.spSensibilidad);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnRegistrate = findViewById(R.id.btnRegistrate);
        bd conexion = new bd(configuracion.this);
        try {
            conexion.abrir();
            cursorConfiguracion = conexion.configuracion();
            cursorConfiguracion.moveToFirst();
            tiempos = conexion.tiempoInicial();
            sensibilidad = conexion.sensibilidad();
            ArrayAdapter<String> adaptadorTiempos = new ArrayAdapter<String>(configuracion.this, android.R.layout.simple_spinner_item, tiempos);
            spTiempoInicio.setAdapter(adaptadorTiempos);
            ArrayAdapter<String> adatadorSensibilidad = new ArrayAdapter<String>(configuracion.this, android.R.layout.simple_spinner_item, sensibilidad);
            spSensibilidad.setAdapter(adatadorSensibilidad);
            int posicionTiempoInicial = Integer.parseInt(cursorConfiguracion.getString(2)) - 1;
            spTiempoInicio.setSelection(posicionTiempoInicial);
            int posicionSensibilidad = Integer.parseInt(cursorConfiguracion.getString(1)) - 1;
            spSensibilidad.setSelection(posicionSensibilidad);
            conexion.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        spTiempoInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bd conexion = new bd(configuracion.this);
                try {
                    conexion.abrir();
                    idTiempoInicial = conexion.buscar_tiempoInicial(tiempos.get(position));
                    conexion.cerrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Log.w("idTiempoInicial", idTiempoInicial);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSensibilidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bd conexion = new bd(configuracion.this);
                try {
                    conexion.abrir();
                    idSensibilidad = conexion.buscar_sensibilidad(sensibilidad.get(position));
                    conexion.cerrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.w("idSensibilidad", idSensibilidad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd conexion = new bd(configuracion.this);
                try {
                    conexion.abrir();
                    if (etTiempoInicio.getText().toString().trim().equals("")) {
                        conexion.modificar_configuracion(idSensibilidad, idTiempoInicial);
                    } else {

                        idTiempoInicial = "" + conexion.registrar_tiempoInicial(etTiempoInicio.getText().toString().trim());
                        conexion.modificar_configuracion(idSensibilidad, idTiempoInicial);
                        Log.w("idTiempoInicialButton", idTiempoInicial);
                    }

                    conexion.cerrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent irMedicion = new Intent(configuracion.this, tiempoespera.class);
                startActivity(irMedicion);
            }
        });
        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(configuracion.this, formularioUsuario.class);
                startActivity(ir);
            }
        });
    }
}






