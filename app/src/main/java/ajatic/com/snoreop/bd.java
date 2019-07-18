package ajatic.com.snoreop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bd {
    //tablas
    //sensibilidad
    private static final String idSensibilidad = "_id";
    private static final String porcentaje = "porcentaje";
    //tiempo inicial
    private static final String idTiempoInicial = "_id";
    private static final String tiempo = "tiempo";
    //configuracion
    private static final String idConfiguracion = "_id";
    private static final String sensibilidadId = "sensibilidadId";
    private static final String tiempoInicialId = "tiempoInicialId";
    //promedio
    private static final String idPromedio = "_id";
    private static final String dia = "dia";
    private static final String promedio = "promedio";
    //monitoreo
    private static final String idmonitoreo = "_id";
    private static final String promedioId = "promedioId";
    private static final String medicion = "medicion";
    //alerta
    private static final String idAlerta = "_id";
    private static final String monitoreoId = "monitoreo";
    // BASE DE DATOS TABLAS
    private static final String BD = "BDRonquidos";
    private static final String sensibilidad = "sensibilidad";
    private static final String tiempoInicial = "tiempoInicial";
    private static final String configuracion = "configuracion";
    private static final String promedioDia = "promedioDia";
    private static final String monitoreo = "monitoreo";
    private static final String alerta = "alerta";
    private static final int VERSION_BD = 1;

    private BDHelper nHelper;
    private final Context nContexto;
    private SQLiteDatabase nBD;

    private static class BDHelper extends SQLiteOpenHelper {

        public BDHelper(Context context) {
            super(context, BD, null, VERSION_BD);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + sensibilidad + "(" + idSensibilidad
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + porcentaje
                    + " TEXT NOT NULL);");

            db.execSQL("CREATE TABLE " + tiempoInicial + "(" + idTiempoInicial
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + tiempo
                    + " TEXT NOT NULL);");

            db.execSQL("CREATE TABLE " + configuracion + "(" + idConfiguracion
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + sensibilidadId
                    + " INTEGER NOT NULL, " + tiempoInicialId + " INTEGER NOT NULL);");

            db.execSQL("CREATE TABLE " + promedioDia + "(" + idPromedio
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + dia
                    + " TEXT NOT NULL, " + promedio + " TEXT NOT NULL);");

            db.execSQL("CREATE TABLE " + monitoreo + "(" + idmonitoreo
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + promedioId
                    + " INTEGER NOT NULL, " + medicion + " TEXT NOT NULL);");

            db.execSQL("CREATE TABLE " + alerta + "(" + idAlerta
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + monitoreoId
                    + " INTEGER NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + sensibilidad);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + tiempoInicial);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + configuracion);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + promedioDia);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + monitoreo);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + alerta);
            onCreate(db);

        }
    }

    public bd(Context c) {

        nContexto = c;

    }

    public bd abrir() throws Exception {

        nHelper = new BDHelper(nContexto);
        nBD = nHelper.getWritableDatabase();
        return this;

    }

    public void cerrar() {
        // TODO Auto-generated method stub

        nHelper.close();

    }

}
