package ajatic.com.snoreop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    Button btnRegistro;

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

        btnRegistro = findViewById(R.id.btnRegistrar);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombre.getText().toString().trim().equals("") || etApellido.getText().toString().trim().equals("") || etEdad.getText().toString().trim().equals("") || etOcupacion.getText().toString().trim().equals("")
                        || etRaza.getText().toString().trim().equals("") || etPais.getText().toString().trim().equals("") || etEstatus.getText().toString().trim().equals("") || etEmail.getText().toString().trim().equals("")
                        || etTelefono.getText().toString().trim().equals("") || etPeso.getText().toString().trim().equals("")) {
                    Toast msj = Toast.makeText(formularioUsuario.this, "Debe completar todos los Datos", Toast.LENGTH_LONG);
                    msj.setGravity(Gravity.CENTER, 0, 0);
                    msj.show();
                } else {
                    bd conexion = new bd(formularioUsuario.this);
                    try {
                        conexion.abrir();
                        long idUsusario = conexion.registrar_usuario(etNombre.getText().toString().trim(), etApellido.getText().toString().trim(), etEdad.getText().toString().trim(), etOcupacion.getText().toString().trim(), etRaza.getText().toString().trim(), etPais.getText().toString().trim(), etPeso.getText().toString().trim(), etEstatus.getText().toString().trim());
                        conexion.registrar_contacto(idUsusario, etEmail.getText().toString().trim(), etTelefono.getText().toString().trim());
                        conexion.cerrar();
                        Intent ir = new Intent(formularioUsuario.this, formularioinfovida.class);
                        ir.putExtra("idUsuario", idUsusario);
                        Log.w("idUsuario", "" + idUsusario);
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
