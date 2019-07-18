package ajatic.com.snoreop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class monitoreo extends AppCompatActivity {

    ListView lvMonitoreo;
    Button btnResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo);

        lvMonitoreo=findViewById(R.id.lvMonitoreo);
        btnResultados=findViewById(R.id.btnResultados);




    }
}
