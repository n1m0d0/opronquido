package ajatic.com.snoreop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    ArrayList<String> tiempos;
    ArrayList<String> sensibilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        spTiempoInicio =findViewById(R.id.spTiempoInicio);
        etTiempoInicio =findViewById(R.id.etTiempoInicio);
        spSensibilidad =findViewById(R.id.spSensibilidad);
        btnSiguiente =findViewById(R.id.btnSiguiente);
        bd conexion =new bd(configuracion.this);
        try {
            conexion.abrir();
            tiempos=conexion.tiempoInicial();
            sensibilidad=conexion.sensibilidad();
            ArrayAdapter<String> adaptadorTiempos =new ArrayAdapter<String>(configuracion.this,android.R.layout.simple_spinner_item,tiempos);
            spTiempoInicio.setAdapter(adaptadorTiempos);
            ArrayAdapter<String> adatadorSensibilidad = new ArrayAdapter<String>(configuracion.this,android.R.layout.simple_spinner_item,sensibilidad);
            spSensibilidad.setAdapter(adatadorSensibilidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irMedicion =new Intent(configuracion.this,tiempoespera.class);
                startActivity(irMedicion);
            }
        });
    }
}
