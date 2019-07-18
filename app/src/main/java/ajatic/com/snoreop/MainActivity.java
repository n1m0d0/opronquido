package ajatic.com.snoreop;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private final int duration = 5000;
    Intent ir;
    Cursor cur;
    Toast msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validarPermisos();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                bd conexion = new bd(MainActivity.this);
                try {
                    conexion.abrir();
                    cur = conexion.sensibilidad();
                    if (cur.moveToFirst() == false) {

                        conexion.llenar_sensibilidad();
                        conexion.llenar_tiempoInicial();
                        conexion.llenar_configuracion();
                        Log.w("hola", "hoola");
                    }
                    conexion.cerrar();
                } catch (Exception e) {
                    e.printStackTrace();
                    conexion.cerrar();
                }

                ir = new Intent(MainActivity.this, configuracion.class);
                startActivity(ir);
                finish();
            }
        }, duration);

    }

    /******************/
    private boolean validarPermisos(){

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;

        }
        if((checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)){

            return true;

        }

        if ((shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) || (shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)) || (shouldShowRequestPermissionRationale(CAMERA)) || (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))  || (shouldShowRequestPermissionRationale(RECORD_AUDIO))) {

            cargardialogo();

        }
        else {

            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, WRITE_EXTERNAL_STORAGE, CAMERA, RECORD_AUDIO}, 100);

        }

        return false;

    }

    private void cargardialogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Permisos Desactivados");
        builder.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, WRITE_EXTERNAL_STORAGE, CAMERA, RECORD_AUDIO}, 100);
                }

            }
        });
        builder.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100) {

            if(grantResults.length == 5 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED  && grantResults[4] == PackageManager.PERMISSION_GRANTED) {



            } else {

                cargardialogo2();

            }

        }

    }

    private void cargardialogo2(){

        final CharSequence[] op = {"si", "no"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Desea configurar los permisos manualmente?");
        builder.setItems(op, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(op[which].equals("si")){

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent .setData(uri);
                    startActivity(intent);

                }
                else {

                    msj = Toast.makeText(MainActivity.this, "los permisos no fueron aceptados", Toast.LENGTH_LONG);
                    msj.show();
                    dialog.dismiss();

                }

            }
        });
        builder.show();

    }
}
