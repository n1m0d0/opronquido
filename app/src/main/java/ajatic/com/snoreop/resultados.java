package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class resultados extends AppCompatActivity {

    ListView lvResultados;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        lvResultados=findViewById(R.id.lvResultados);
        btnFinalizar=findViewById(R.id.btnFinalizar);



    }
}
