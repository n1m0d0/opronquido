package ajatic.com.snoreop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

    //funciones
    //sensibilidad
    //devuelve todos los registros de la tabla sensibilidad
    public Cursor sensibilidad() throws SQLException {
        String selectQuery = "SELECT * FROM " + sensibilidad;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    //registra la sensibilidad
    public long registrar_sensibilidad(String porcentaje)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.porcentaje, porcentaje);
        return nBD.insert(sensibilidad, null, cv);

    }
    //llenar sensibilidad
    public void llenar_sensibilidad()
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv1 = new ContentValues();
        cv1.put(porcentaje, "5%");
        nBD.insert(sensibilidad, null, cv1);
        ContentValues cv2 = new ContentValues();
        cv2.put(porcentaje, "10%");
        nBD.insert(sensibilidad, null, cv2);
        ContentValues cv3 = new ContentValues();
        cv3.put(porcentaje, "15%");
        nBD.insert(sensibilidad, null, cv3);
        ContentValues cv4 = new ContentValues();
        cv4.put(porcentaje, "20%");
        nBD.insert(sensibilidad, null, cv4);

    }
    //tiempo inicial
    //devuelve todos los registros de la tabla tiempoInicial
    public Cursor tiempoInicial() throws SQLException {
        String selectQuery = "SELECT * FROM " + tiempoInicial;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    //registra tiempoInicial
    public long registrar_tiempoInicial(String tiempo)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.tiempo, tiempo);
        return nBD.insert(sensibilidad, null, cv);

    }
    //llenar tiempoInicial
    public void llenar_tiempoInicial()
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv1 = new ContentValues();
        cv1.put(tiempo, "20");
        nBD.insert(tiempoInicial, null, cv1);
        ContentValues cv2 = new ContentValues();
        cv2.put(tiempo, "25");
        nBD.insert(tiempoInicial, null, cv2);
        ContentValues cv3 = new ContentValues();
        cv3.put(tiempo, "30");
        nBD.insert(tiempoInicial, null, cv3);
        ContentValues cv4 = new ContentValues();
        cv4.put(tiempo, "35");
        nBD.insert(tiempoInicial, null, cv4);
        ContentValues cv5 = new ContentValues();
        cv5.put(tiempo, "40");
        nBD.insert(tiempoInicial, null, cv5);

    }

    //configuracion
    //devuelve todos los registros de la tabla configuracion
    public Cursor configuracion() throws SQLException {
        String selectQuery = "SELECT * FROM " + tiempoInicial;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    //llenar configuracion
    public void llenar_configuracion()
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv1 = new ContentValues();
        cv1.put(sensibilidadId, 1);
        cv1.put(tiempoInicialId, 1);
        nBD.insert(configuracion, null, cv1);

    }
    //actualizar configuracion
    public void modificar_configuracion(String sensibilidadId, String tiempoInicialId) throws SQLException {
        // TODO Auto-generated method stub

        ContentValues cv = new ContentValues();
        cv.put(this.sensibilidadId, sensibilidadId);
        cv.put(this.tiempoInicialId, tiempoInicialId);
        nBD.update(configuracion, cv, idConfiguracion + "='" + 1 + "'", null);

    }
    //promedio
    //devuelve todos los registros de la tabla promedio
    public Cursor promedio() throws SQLException {
        String selectQuery = "SELECT * FROM " + promedio;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    //registra la sensibilidad
    public long registrar_promedio(String dia, String promedio)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.dia, dia);
        cv.put(this.promedio, promedio);
        return nBD.insert(promedioDia, null, cv);

    }
    //monitoreo
    //devuelve todos los registros de la tabla monitoreo
    public Cursor monitoreo() throws SQLException {
        String selectQuery = "SELECT * FROM " + monitoreo;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    //registra la monitoreo
    public long registrar_monitoreo(int promedioId, String medicion)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.promedioId, promedioId);
        cv.put(this.medicion, medicion);
        return nBD.insert(monitoreo, null, cv);

    }

}
