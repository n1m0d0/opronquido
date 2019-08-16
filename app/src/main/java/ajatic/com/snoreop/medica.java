package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class medica extends AppCompatActivity {

    EditText etAlergias, etAfecciones;
    Spinner spSangre;
    String blood [] = {"--Opciones--","O negativo","O positivo","A negativo","A positivo","B negativo","B positivo","AB negativo","AB positivo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medica);

        etAlergias = findViewById(R.id.etAlergias);
        etAfecciones = findViewById(R.id.etAfecciones);
        spSangre = findViewById(R.id.spSangre);


        ArrayAdapter adapterBlood = new ArrayAdapter(this,android.R.layout.simple_spinner_item,blood);
        spSangre.setAdapter(adapterBlood);



    }
}
