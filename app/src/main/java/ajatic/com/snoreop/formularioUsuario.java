package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class formularioUsuario extends AppCompatActivity {

    EditText etNombre;
    EditText etApellido;
    EditText etEdad;
    EditText etOcupacion;
    EditText etRaza;
    EditText etPais;
    EditText etEstatus;
    EditText etEmail;
    EditText etTelefono;
    EditText etPeso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etOcupacion = findViewById(R.id.etOcupacion);
        etRaza = findViewById(R.id.etRaza);
        etPais = findViewById(R.id.etPais);
        etEstatus = findViewById(R.id.etEstatus);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etPeso = findViewById(R.id.etPeso);



    }
}
