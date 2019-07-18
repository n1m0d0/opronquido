package ajatic.com.snoreop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int duration = 2000;
    Intent ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ir = new Intent(MainActivity.this, MainActivity.class);
                startActivity(ir);
                finish();
            }
        }, duration);

    }
}
