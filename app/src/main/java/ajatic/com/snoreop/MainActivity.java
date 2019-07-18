package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spTiempoInicio;
    EditText etTiempoInicio;
    Spinner spSensibilidad;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTiempoInicio =findViewById(R.id.spTiempoInicio);
        etTiempoInicio =findViewById(R.id.etTiempoInicio);
        spSensibilidad =findViewById(R.id.spSensibilidad);
        btnSiguiente =findViewById(R.id.btnSiguiente);

    }
}
