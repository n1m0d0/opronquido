package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Medicion extends AppCompatActivity {
    ListView lvMediciones;
    TextView tvPromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);
        lvMediciones =findViewById(R.id.lvMediciones);
        tvPromedio =findViewById(R.id.tvPromedio);
    }
}
