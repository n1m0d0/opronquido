package ajatic.com.snoreop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class formularioinfovida extends AppCompatActivity {

    Spinner spConvivencia,spMascota;
    EditText etVivienda, etHorasTrabajo, etIngresoAnual, etHijos;
    Button btnGuardarVida;
    String conviencia [] = {"---Opciones---","Soltera(o)","Casada(o)","Divorciada(o)","Viuda(o)"};
    String mascota [] = {"---Opciones---","Si", "No"};
    int positionConvivencia;
    int positionMascota;
    long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularioinfovida);

        Bundle data = getIntent().getExtras();
        idUsuario = data.getLong("idUsuario");

        spConvivencia = findViewById(R.id.spConvivencia);
        spMascota = findViewById(R.id.spMascota);
        btnGuardarVida = findViewById(R.id.btnGuardarVida);
        etVivienda = findViewById(R.id.etVivienda);
        etHorasTrabajo = findViewById(R.id.etHorasTrabajo);
        etIngresoAnual = findViewById(R.id.etIngresoAnual);
        etHijos = findViewById(R.id.etHijos);

        final ArrayAdapter eCivil = new ArrayAdapter(this,R.layout.spinner_item,conviencia);
        spConvivencia.setAdapter(eCivil);

        ArrayAdapter pet = new ArrayAdapter(this,R.layout.spinner_item,mascota);
        spMascota.setAdapter(pet);

        spConvivencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionConvivencia = adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spMascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionMascota = adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGuardarVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etVivienda.getText().toString().trim().equals("") || etHorasTrabajo.getText().toString().trim().equals("") || etIngresoAnual.getText().toString().trim().equals("") || etHijos.getText().toString().trim().equals("") || positionConvivencia ==0 || positionMascota == 0) {
                    Toast msj = Toast.makeText(formularioinfovida.this, "Debe completar todos los datos", Toast.LENGTH_LONG);
                    msj.setGravity(Gravity.CENTER, 0, 0);
                    msj.show();
                } else {
                    bd conexion = new bd(formularioinfovida.this);
                    try {
                        conexion.abrir();
                        conexion.registrar_vida(idUsuario, etVivienda.getText().toString().trim(), conviencia[positionConvivencia], etHorasTrabajo.getText().toString().trim(), etIngresoAnual.getText().toString().trim(), etHijos.getText().toString().trim(), mascota[positionMascota]);
                        conexion.cerrar();
                        Intent ir = new Intent(formularioinfovida.this, medica.class);
                        ir.putExtra("idUsuario", idUsuario);
                        startActivity(ir);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
