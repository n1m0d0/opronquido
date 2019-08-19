package ajatic.com.snoreop;

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

public class medica extends AppCompatActivity {

    EditText etAlergias, etAfecciones;
    Spinner spSangre;
    Button btnGuardarMedica;
    String blood [] = {"--Opciones--","O negativo","O positivo","A negativo","A positivo","B negativo","B positivo","AB negativo","AB positivo"};
    int positionBlood;
    long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medica);

        etAlergias = findViewById(R.id.etAlergias);
        etAfecciones = findViewById(R.id.etAfecciones);
        spSangre = findViewById(R.id.spSangre);

        btnGuardarMedica =  findViewById(R.id.btnGuardarMedica);


        Bundle data = getIntent().getExtras();
        idUsuario = data.getLong("idUsuario");


        ArrayAdapter adapterBlood = new ArrayAdapter(this,R.layout.spinner_item,blood);
        spSangre.setAdapter(adapterBlood);

        spSangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionBlood = adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGuardarMedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAlergias.getText().toString().trim().equals("") || etAfecciones.getText().toString().trim().equals("") || positionBlood == 0 ) {
                    Toast msj = Toast.makeText(medica.this, "Debe completar todos los datos", Toast.LENGTH_LONG);
                    msj.setGravity(Gravity.CENTER, 0, 0);
                    msj.show();
                } else {
                    bd conexion = new bd(medica.this);
                    try {
                        conexion.abrir();
                        conexion.registrar_informacion(idUsuario, etAlergias.getText().toString(), etAfecciones.getText().toString(), blood[positionBlood]);
                        conexion.cerrar();
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }
}
