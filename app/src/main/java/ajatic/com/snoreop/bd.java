package ajatic.com.snoreop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class bd {
    //tablas
    //sensibilidad
    private static final String idSensibilidad = "_id";
    private static final String porcentaje = "porcentaje";
    private static final String valor = "valor";
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
    private static final String capturaFecha = "capturaFecha";
    private static final String capturaHora = "capturaHora";
    private static final String medicion = "medicion";
    //alerta
    private static final String idAlerta = "_id";
    private static final String monitoreoId = "monitoreo";
    /*************************/
    //usuario
    private static final String idUsuario = "_id";
    private static final String nombres = "nombres";
    private static final String apellidos = "apellidos";
    private static final String edad = "edad";
    private static final String ocupacion = "ocupacion";
    private static final String raza = "raza";
    private static final String pais = "pais";
    private static final String peso = "peso";
    private static final String estado = "estado";
    //contacto
    private static final String idContacto = "_id";
    private static final String usuarioIdContacto = "usuarioId";
    private static final String email = "email";
    private static final String telefono = "telefono";
    //vida
    private static final String idVida = "_id";
    private static final String usuarioIdVida = "usuarioId";
    private static final String vivienda = "vivienda";
    private static final String convivencia = "convivencia";
    private static final String horasTrabajo = "horasTrabajo";
    private static final String ingresoAnual = "ingresoAnual";
    private static final String hijos = "hijos";
    private static final String mascotas = "mascotas";
    //informacion medica
    private static final String idInformacion = "idInformacion";
    private static final String usuarioIdInformacion = "usuarioId";
    private static final String alergias = "alergias";
    private static final String afecciones = "afecciones";
    private static final String tipoSangre = "tipoSangre";
    /*************************/
    // BASE DE DATOS TABLAS
    private static final String BD = "BDRonquidos";
    private static final String sensibilidad = "sensibilidad";
    private static final String tiempoInicial = "tiempoInicial";
    private static final String configuracion = "configuracion";
    private static final String promedioDia = "promedioDia";
    private static final String monitoreo = "monitoreo";
    private static final String alerta = "alerta";
    private static final String usuario = "usuario";
    private static final String contacto = "contacto";
    private static final String vida = "vida";
    private static final String informacion = "informacion";
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
                    + " TEXT NOT NULL, " + valor + " TEXT NOT NULL);");

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
                    + " INTEGER NOT NULL, " + capturaFecha
                    + " TEXT NOT NULL, " + capturaHora
                    + " TEXT NOT NULL, " + medicion + " TEXT NOT NULL);");

            db.execSQL("CREATE TABLE " + alerta + "(" + idAlerta
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + monitoreoId
                    + " INTEGER NOT NULL);");

            db.execSQL("CREATE TABLE " + usuario + "(" + idUsuario
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + nombres
                    + " TEXT NOT NULL, " + apellidos
                    + " TEXT NOT NULL, " + edad
                    + " TEXT NOT NULL, " + ocupacion
                    + " TEXT NOT NULL, " + raza
                    + " TEXT NOT NULL, " + pais
                    + " TEXT NOT NULL, " + peso
                    + " TEXT NOT NULL, " + estado
                    + " INTEGER NOT NULL);");

            db.execSQL("CREATE TABLE " + contacto + "(" + idContacto
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + usuarioIdContacto
                    + " INTEGER NOT NULL, " + email
                    + " TEXT NOT NULL, " + telefono
                    + " INTEGER NOT NULL);");

            db.execSQL("CREATE TABLE " + vida + "(" + idVida
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + usuarioIdVida
                    + " INTEGER NOT NULL, " + vivienda
                    + " TEXT NOT NULL, " + convivencia
                    + " TEXT NOT NULL, " + horasTrabajo
                    + " TEXT NOT NULL, " + ingresoAnual
                    + " TEXT NOT NULL, " + hijos
                    + " TEXT NOT NULL, " + mascotas
                    + " INTEGER NOT NULL);");

            db.execSQL("CREATE TABLE " + informacion + "(" + idInformacion
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + usuarioIdInformacion
                    + " INTEGER NOT NULL, " + alergias
                    + " TEXT NOT NULL, " + afecciones
                    + " TEXT NOT NULL, " + tipoSangre
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
    public ArrayList<String> sensibilidad() throws SQLException {
        String selectQuery = "SELECT * FROM " + sensibilidad;
        Cursor cursor = nBD.rawQuery(selectQuery, null);

        ArrayList<String> resultado = new ArrayList<String>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                .moveToNext()) {

            resultado.add(cursor.getString(1));

        }

        return resultado;

    }

    // busca sencibilidad segun el nombre
    public String buscar_sensibilidad(String porcentaje) throws SQLException {
        String selectQuery = "SELECT * FROM " + sensibilidad + " WHERE "
                + this.porcentaje + "='" + porcentaje + "'";
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        String id = null;

        if (cursor.moveToFirst() == false) {
            id = "1";
        } else {
            id = cursor.getString(0);

        }

        return id;

    }

    // busca sencibilidad segun el id
    public String buscar_sensibilidadId(String id) throws SQLException {
        String selectQuery = "SELECT * FROM " + sensibilidad + " WHERE "
                + this.idSensibilidad + "='" + id + "'";
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        String porcentaje = null;

        if (cursor.moveToFirst() == false) {
            porcentaje = "0.05";
        } else {
            porcentaje = cursor.getString(2);

        }

        return porcentaje;

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
        cv1.put(valor, "0.05");
        nBD.insert(sensibilidad, null, cv1);
        ContentValues cv2 = new ContentValues();
        cv2.put(porcentaje, "10%");
        cv2.put(valor, "0.10");
        nBD.insert(sensibilidad, null, cv2);
        ContentValues cv3 = new ContentValues();
        cv3.put(porcentaje, "15%");
        cv3.put(valor, "0.15");
        nBD.insert(sensibilidad, null, cv3);
        ContentValues cv4 = new ContentValues();
        cv4.put(porcentaje, "20%");
        cv4.put(valor, "0.2");
        nBD.insert(sensibilidad, null, cv4);

    }

    //tiempo inicial
    //devuelve todos los registros de la tabla tiempoInicial
    public ArrayList<String> tiempoInicial() throws SQLException {
        String selectQuery = "SELECT * FROM " + tiempoInicial;
        Cursor cursor = nBD.rawQuery(selectQuery, null);

        ArrayList<String> resultado = new ArrayList<String>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                .moveToNext()) {

            resultado.add(cursor.getString(1));

        }

        return resultado;

    }

    // busca TiempoInicial segun el nombre;
    public String buscar_tiempoInicial(String tiempo) throws SQLException {
        String selectQuery = "SELECT * FROM " + tiempoInicial + " WHERE "
                + this.tiempo + "='" + tiempo + "'";
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        String id = null;

        if (cursor.moveToFirst() == false) {
            id = "1";
        } else {
            id = cursor.getString(0);

        }

        return id;

    }

    // busca TiempoInicial segun el id
    public String buscar_TiempoInicialId(String id) throws SQLException {
        String selectQuery = "SELECT * FROM " + tiempoInicial + " WHERE "
                + this.idTiempoInicial + "='" + id + "'";
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        String tiempo = null;

        if (cursor.moveToFirst() == false) {
            tiempo = "20";
        } else {
            tiempo = cursor.getString(1);

        }

        return tiempo;

    }

    //registra tiempoInicial
    public long registrar_tiempoInicial(String tiempo)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.tiempo, tiempo);
        return nBD.insert(tiempoInicial, null, cv);

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
        String selectQuery = "SELECT * FROM " + configuracion;
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
        String selectQuery = "SELECT * FROM " + promedioDia;
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        return cursor;

    }

    // busca Promedio segun el id
    public String buscar_Promedio(String id) throws SQLException {
        String selectQuery = "SELECT * FROM " + promedioDia + " WHERE "
                + this.idPromedio + "='" + id + "'";
        Cursor cursor = nBD.rawQuery(selectQuery, null);
        String promedio = null;

        if (cursor.moveToFirst() == false) {
            promedio = "60";
        } else {
            promedio = cursor.getString(2);

        }

        return promedio;

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
    public long registrar_monitoreo(String promedioId, String capturaFecha, String capturaHora, String medicion)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.promedioId, promedioId);
        cv.put(this.capturaFecha, capturaFecha);
        cv.put(this.capturaHora, capturaHora);
        cv.put(this.medicion, medicion);
        return nBD.insert(monitoreo, null, cv);

    }

    //usuario
    //registrar usuarios
    public long registrar_usuario(String nombres, String apellidos, String edad, String ocupacion, String raza, String pais, String peso, String estado)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.nombres, nombres);
        cv.put(this.apellidos, apellidos);
        cv.put(this.edad, edad);
        cv.put(this.ocupacion, ocupacion);
        cv.put(this.raza, raza);
        cv.put(this.pais, pais);
        cv.put(this.peso, peso);
        cv.put(this.estado, estado);
        return nBD.insert(usuario, null, cv);
    }

    //cantacto
    //registrar contacto
    public long registrar_contacto(long usuarioIdContacto , String email, String telefono)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.usuarioIdContacto, usuarioIdContacto);
        cv.put(this.email, email);
        cv.put(this.telefono, telefono);
        return nBD.insert(contacto, null, cv);
    }

    //vida
    //registrar vida
    public long registrar_vida(long usuarioIdVida , String vivienda, String convivencia, String horasTrabajo, String ingresoAnual, String hijos, String mascotas)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.usuarioIdVida, usuarioIdVida);
        cv.put(this.vivienda, vivienda);
        cv.put(this.convivencia, convivencia);
        cv.put(this.horasTrabajo, horasTrabajo);
        cv.put(this.ingresoAnual, ingresoAnual);
        cv.put(this.hijos, hijos);
        cv.put(this.mascotas, mascotas);
        return nBD.insert(vida, null, cv);
    }

    //vida
    //registrar vida
    public long registrar_informacion(long usuarioIdInformacion , String alergias, String afecciones, String tipoSangre)
            throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(this.usuarioIdInformacion, usuarioIdInformacion);
        cv.put(this.alergias, alergias);
        cv.put(this.afecciones, afecciones);
        cv.put(this.tipoSangre , tipoSangre );
        return nBD.insert(informacion, null, cv);
    }
}
