package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class formularioinfovida extends AppCompatActivity {

    Spinner spConvivencia,spMascota;
    String conviencia [] = {"---Opciones---","Soltera(o)","Casada(o)","Divorciada(o)","Viuda(o)"};
    String mascota [] = {"---Opciones---","Si", "No"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularioinfovida);

        spConvivencia = findViewById(R.id.spConvivencia);
        spMascota = findViewById(R.id.spMascota);


        ArrayAdapter eCivil = new ArrayAdapter(this,android.R.layout.simple_spinner_item,conviencia);
        spConvivencia.setAdapter(eCivil);

        ArrayAdapter pet = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mascota);
        spMascota.setAdapter(pet);


    }
}
