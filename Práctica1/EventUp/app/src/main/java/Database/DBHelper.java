package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventupdiseo.R;

public class DBHelper extends SQLiteOpenHelper {
    // Nombre de la Base de Datos
    public static final String nameDB = "EventUp.db";
    // Versión de la Base de Datos
    private static final int versionDB = 8;

    /////////////////////////////////////////////////////

    // Tabla USUARIO
    private String TABLA_USUARIO;
    private String USUARIO;
    private String CONTRASEÑA;
    private String NOMBRE;
    private String CARRERA;
    private String CURSO;
    private String EDAD;
    private String FOTO;

    /////////////////////////////////////////////////////

    // Tabla EVENTO
    private String TABLA_EVENTO;
    private String NOMBRE_EVENTO;
    private String FECHA;
    private String HORA;
    private String AFORO;
    private String GRADO;
    private String UBICACION;
    private String IDIOMA;

    /////////////////////////////////////////////////////

    // Tabla USUARIO-EVENTO
    private String TABLA_USUARIO_EVENTO;
    private String ID_USUARIO;
    private String ID_EVENTO;

    /////////////////////////////////////////////////////

    // Definimos las sentencias para la creación de las Tablas
    private String CREATE_TABLE_USUARIO;
    private String CREATE_TABLE_EVENTO;
    private String CREATE_TABLE_USUARIO_EVENTO;

    /////////////////////////////////////////////////////

    // Definimos las sentencias para cada INSERT de USUARIO
    private String INSERT_SILVIA_INTO_USUARIO;
    private String INSERT_CARLOTA_INTO_USUARIO;
    private String INSERT_ELENA_INTO_USUARIO;
    private String INSERT_ADRIAN_INTO_USUARIO;
    private String INSERT_JUAN_INTO_USUARIO;

    /////////////////////////////////////////////////////

    // Definimos las sentencias para cada INSERT de EVENTO
    private String INSERT_ANALISISVECTORIAL_INTO_EVENTO;
    private String INSERT_ALGEBRACONMUTATIVA_INTO_EVENTO;
    private String INSERT_ESTADISTICA_INTO_EVENTO;
    private String INSERT_VISIONPORCOMPUTADOR_INTO_EVENTO;
    private String INSERT_PROCESADORESDELENGUAJES_INTO_EVENTO;
    private String INSERT_NUEVOSPARADIGMAS_INTO_EVENTO;
    private String INSERT_PADEL_INTO_EVENTO;
    private String INSERT_CHARLA_TFG_INTO_EVENTO;
    private String INSERT_CENA_DGIIM_INTO_EVENTO;
    private String INSERT_FUTBOL_INTO_EVENTO;
    private String INSERT_CARRERA_SALBERTO_INTO_EVENTO;

    /////////////////////////////////////////////////////

    // Definimos las sentencias para cada INSERT de USUARIO-EVENTO
    private String INSERT_SILVIA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_ESTADISTICA_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO;
    private String INSERT_SILVIA_CARRERA_SALBERTO_INTO_USUARIO_EVENTO;


    private String INSERT_CARLOTA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_ESTADISTICA_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO;
    private String INSERT_CARLOTA_CHARLA_TFG_INTO_USUARIO_EVENTO;


    private String INSERT_ELENA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_ESTADISTICA_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO;
    private String INSERT_ELENA_PADEL_INTO_USUARIO_EVENTO;


    private String INSERT_ADRIAN_ANALISISVECTORIAL_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_ESTADISTICA_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO;
    private String INSERT_ADRIAN_FUTBOL_INTO_USUARIO_EVENTO;


    /////////////////////////////////////////////////////

    // DATOS USUARIO

    // SILVIA
    private String VALUE_USU_SILVIA;
    private String VALUE_CONT_SILVIA;
    private String VALUE_NOM_SILVIA;
    private String VALUE_CAR_SILVIA;
    private String VALUE_CUR_SILVIA;
    private String VALUE_EDA_SILVIA;
    private String VALUE_FOT_SILVIA;

    // CARLOTA
    private String VALUE_USU_CARLOTA;
    private String VALUE_CONT_CARLOTA;
    private String VALUE_NOM_CARLOTA;
    private String VALUE_CAR_CARLOTA;
    private String VALUE_CUR_CARLOTA;
    private String VALUE_EDA_CARLOTA;
    private String VALUE_FOT_CARLOTA;

    // ELENA
    private String VALUE_USU_ELENA;
    private String VALUE_CONT_ELENA;
    private String VALUE_NOM_ELENA;
    private String VALUE_CAR_ELENA;
    private String VALUE_CUR_ELENA;
    private String VALUE_EDA_ELENA;
    private String VALUE_FOT_ELENA;

    // ADRIAN
    private String VALUE_USU_ADRIAN;
    private String VALUE_CONT_ADRIAN;
    private String VALUE_NOM_ADRIAN;
    private String VALUE_CAR_ADRIAN;
    private String VALUE_CUR_ADRIAN;
    private String VALUE_EDA_ADRIAN;
    private String VALUE_FOT_ADRIAN;

    // JUAN
    private String VALUE_USU_JUAN;
    private String VALUE_CONT_JUAN;
    private String VALUE_NOM_JUAN;
    private String VALUE_CAR_JUAN;
    private String VALUE_CUR_JUAN;
    private String VALUE_EDA_JUAN;
    private String VALUE_FOT_JUAN;

    /////////////////////////////////////////////////////

    // DATOS EVENTO

    // ANÁLISIS VECTORIAL
    private String VALUE_NOMBRE_ANALISISVECTORIAL;
    private String VALUE_FECHA_ANALISISVECTORIAL;
    private String VALUE_HORA_ANALISISVECTORIAL;
    private String VALUE_AFORO_ANALISISVECTORIAL;
    private String VALUE_GRADO_ANALISISVECTORIAL;
    private String VALUE_UBICACION_ANALISISVECTORIAL;
    private String VALUE_IDIOMA_ANALISISVECTORIAL;

    // ÁLGEBRA CONMUTATIVA
    private String VALUE_NOMBRE_ALGEBRACONMUTATIVA;
    private String VALUE_FECHA_ALGEBRACONMUTATIVA;
    private String VALUE_HORA_ALGEBRACONMUTATIVA;
    private String VALUE_AFORO_ALGEBRACONMUTATIVA;
    private String VALUE_GRADO_ALGEBRACONMUTATIVA;
    private String VALUE_UBICACION_ALGEBRACONMUTATIVA;
    private String VALUE_IDIOMA_ALGEBRACONMUTATIVA;

    // ESTADISTICA
    private String VALUE_NOMBRE_ESTADISTICA;
    private String VALUE_FECHA_ESTADISTICA;
    private String VALUE_HORA_ESTADISTICA;
    private String VALUE_AFORO_ESTADISTICA;
    private String VALUE_GRADO_ESTADISTICA;
    private String VALUE_UBICACION_ESTADISTICA;
    private String VALUE_IDIOMA_ESTADISTICA;

    // VISION POR COMPUTADOR
    private String VALUE_NOMBRE_VISIONPORCOMPUTADOR;
    private String VALUE_FECHA_VISIONPORCOMPUTADOR;
    private String VALUE_HORA_VISIONPORCOMPUTADOR;
    private String VALUE_AFORO_VISIONPORCOMPUTADOR;
    private String VALUE_GRADO_VISIONPORCOMPUTADOR;
    private String VALUE_UBICACION_VISIONPORCOMPUTADOR;
    private String VALUE_IDIOMA_VISIONPORCOMPUTADOR;

    // PROCESADORES DE LENGUAJES
    private String VALUE_NOMBRE_PROCESADORESDELENGUAJES;
    private String VALUE_FECHA_PROCESADORESDELENGUAJES;
    private String VALUE_HORA_PROCESADORESDELENGUAJES;
    private String VALUE_AFORO_PROCESADORESDELENGUAJES;
    private String VALUE_GRADO_PROCESADORESDELENGUAJES;
    private String VALUE_UBICACION_PROCESADORESDELENGUAJES;
    private String VALUE_IDIOMA_PROCESADORESDELENGUAJES;

    // NUEVOS PARADIGMAS
    private String VALUE_NOMBRE_NUEVOSPARADIGMAS;
    private String VALUE_FECHA_NUEVOSPARADIGMAS;
    private String VALUE_HORA_NUEVOSPARADIGMAS;
    private String VALUE_AFORO_NUEVOSPARADIGMAS;
    private String VALUE_GRADO_NUEVOSPARADIGMAS;
    private String VALUE_UBICACION_NUEVOSPARADIGMAS;
    private String VALUE_IDIOMA_NUEVOSPARADIGMAS;

    // PADEL
    private String VALUE_NOMBRE_PADEL;
    private String VALUE_FECHA_PADEL;
    private String VALUE_HORA_PADEL;
    private String VALUE_AFORO_PADEL;
    private String VALUE_GRADO_PADEL;
    private String VALUE_UBICACION_PADEL;
    private String VALUE_IDIOMA_PADEL;

    // CHARLA TFG
    private String VALUE_NOMBRE_CHARLA_TFG;
    private String VALUE_FECHA_CHARLA_TFG;
    private String VALUE_HORA_CHARLA_TFG;
    private String VALUE_AFORO_CHARLA_TFG;
    private String VALUE_GRADO_CHARLA_TFG;
    private String VALUE_UBICACION_CHARLA_TFG;
    private String VALUE_IDIOMA_CHARLA_TFG;

    // CENA DGIIM
    private String VALUE_NOMBRE_CENA_DGIIM;
    private String VALUE_FECHA_CENA_DGIIM;
    private String VALUE_HORA_CENA_DGIIM;
    private String VALUE_AFORO_CENA_DGIIM;
    private String VALUE_GRADO_CENA_DGIIM;
    private String VALUE_UBICACION_CENA_DGIIM;
    private String VALUE_IDIOMA_CENA_DGIIM;

    // FUTBOL
    private String VALUE_NOMBRE_FUTBOL;
    private String VALUE_FECHA_FUTBOL;
    private String VALUE_HORA_FUTBOL;
    private String VALUE_AFORO_FUTBOL;
    private String VALUE_GRADO_FUTBOL;
    private String VALUE_UBICACION_FUTBOL;
    private String VALUE_IDIOMA_FUTBOL;

    // CARRERA SAN ALBERTO
    private String VALUE_NOMBRE_CARRERA_SALBERTO;
    private String VALUE_FECHA_CARRERA_SALBERTO;
    private String VALUE_HORA_CARRERA_SALBERTO;
    private String VALUE_AFORO_CARRERA_SALBERTO;
    private String VALUE_GRADO_CARRERA_SALBERTO;
    private String VALUE_UBICACION_CARRERA_SALBERTO;
    private String VALUE_IDIOMA_CARRERA_SALBERTO;

    /////////////////////////////////////////////////////

    private static SQLiteDatabase dbEventUp;

    // Cuando creamos la base de datos llamamos a este método, es llamado desde context
    public DBHelper(Context context){
        super(context, nameDB, null, versionDB);
        inicializarValores(context);
    }

    // Inicialización de los valores de las tablas
    private void inicializarValores(Context context){
        // TABLA USUARIO
        TABLA_USUARIO = context.getString(R.string.tabla_usuario);
        USUARIO = context.getString(R.string.usuario_usuario);
        CONTRASEÑA= context.getString(R.string.usuario_contraseña);
        NOMBRE = context.getString(R.string.usuario_nombre);
        CARRERA = context.getString(R.string.usuario_carrera);
        CURSO = context.getString(R.string.usuario_curso);
        EDAD = context.getString(R.string.usuario_edad);
        FOTO = context.getString(R.string.usuario_foto);

        //--------------------------------------------------//

        // SILVIA
        VALUE_USU_SILVIA = context.getString(R.string.usu_silvia);
        VALUE_CONT_SILVIA = context.getString(R.string.cont_silvia);
        VALUE_NOM_SILVIA = context.getString(R.string.nom_silvia);
        VALUE_CAR_SILVIA = context.getString(R.string.car_silvia);
        VALUE_CUR_SILVIA = context.getString(R.string.cur_silvia);
        VALUE_EDA_SILVIA = context.getString(R.string.eda_silvia);
        VALUE_FOT_SILVIA = context.getString(R.string.fot_silvia);

        // CARLOTA
        VALUE_USU_CARLOTA = context.getString(R.string.usu_carlota);
        VALUE_CONT_CARLOTA = context.getString(R.string.cont_carlota);
        VALUE_NOM_CARLOTA = context.getString(R.string.nom_carlota);
        VALUE_CAR_CARLOTA = context.getString(R.string.car_carlota);
        VALUE_CUR_CARLOTA = context.getString(R.string.cur_carlota);
        VALUE_EDA_CARLOTA = context.getString(R.string.eda_carlota);
        VALUE_FOT_CARLOTA = context.getString(R.string.fot_carlota);

        // ELENA
        VALUE_USU_ELENA = context.getString(R.string.usu_elena);
        VALUE_CONT_ELENA = context.getString(R.string.cont_elena);
        VALUE_NOM_ELENA = context.getString(R.string.nom_elena);
        VALUE_CAR_ELENA = context.getString(R.string.car_elena);
        VALUE_CUR_ELENA = context.getString(R.string.cur_elena);
        VALUE_EDA_ELENA = context.getString(R.string.eda_elena);
        VALUE_FOT_ELENA = context.getString(R.string.fot_elena);

        // ADRIAN
        VALUE_USU_ADRIAN = context.getString(R.string.usu_adrian);
        VALUE_CONT_ADRIAN = context.getString(R.string.cont_adrian);
        VALUE_NOM_ADRIAN = context.getString(R.string.nom_adrian);
        VALUE_CAR_ADRIAN = context.getString(R.string.car_adrian);
        VALUE_CUR_ADRIAN = context.getString(R.string.cur_adrian);
        VALUE_EDA_ADRIAN = context.getString(R.string.eda_adrian);
        VALUE_FOT_ADRIAN = context.getString(R.string.fot_adrian);

        // JUAN
        VALUE_USU_JUAN = context.getString(R.string.usu_juan);
        VALUE_CONT_JUAN = context.getString(R.string.cont_juan);
        VALUE_NOM_JUAN = context.getString(R.string.nom_juan);
        VALUE_CAR_JUAN = context.getString(R.string.car_juan);
        VALUE_CUR_JUAN = context.getString(R.string.cur_juan);
        VALUE_EDA_JUAN = context.getString(R.string.eda_juan);
        VALUE_FOT_JUAN = context.getString(R.string.fot_juan);

        /////////////////////////////////////////////////////

        // TABLA EVENTO
        TABLA_EVENTO = context.getString(R.string.tabla_evento);
        NOMBRE_EVENTO = context.getString(R.string.evento_nombre);
        FECHA = context.getString(R.string.evento_fecha);
        HORA = context.getString(R.string.evento_hora);
        AFORO = context.getString(R.string.evento_aforo);
        GRADO = context.getString(R.string.evento_grado);
        UBICACION = context.getString(R.string.evento_ubicacion);
        IDIOMA = context.getString(R.string.evento_idioma);

        //--------------------------------------------------//

        // ANÁLISIS VECTORIAL
        VALUE_NOMBRE_ANALISISVECTORIAL = context.getString(R.string.nombre_analisisvectorial);
        VALUE_FECHA_ANALISISVECTORIAL = context.getString(R.string.fecha_analisisvectorial);
        VALUE_HORA_ANALISISVECTORIAL = context.getString(R.string.hora_analisisvectorial);
        VALUE_AFORO_ANALISISVECTORIAL = context.getString(R.string.aforo_analisisvectorial);
        VALUE_GRADO_ANALISISVECTORIAL = context.getString(R.string.grado_analisisvectorial);
        VALUE_UBICACION_ANALISISVECTORIAL = context.getString(R.string.ubicacion_analisisvectorial);
        VALUE_IDIOMA_ANALISISVECTORIAL = context.getString(R.string.idioma_analisisvectorial);

        // ÁLGEBRA CONMUTATIVA
        VALUE_NOMBRE_ALGEBRACONMUTATIVA = context.getString(R.string.nombre_algebraconmutativa);
        VALUE_FECHA_ALGEBRACONMUTATIVA = context.getString(R.string.fecha_algebraconmutativa);
        VALUE_HORA_ALGEBRACONMUTATIVA = context.getString(R.string.hora_algebraconmutativa);
        VALUE_AFORO_ALGEBRACONMUTATIVA = context.getString(R.string.aforo_algebraconmutativa);
        VALUE_GRADO_ALGEBRACONMUTATIVA = context.getString(R.string.grado_algebraconmutativa);
        VALUE_UBICACION_ALGEBRACONMUTATIVA = context.getString(R.string.ubicacion_algebraconmutativa);
        VALUE_IDIOMA_ALGEBRACONMUTATIVA = context.getString(R.string.idioma_algebraconmutativa);

        // ESTADISTICA
        VALUE_NOMBRE_ESTADISTICA = context.getString(R.string.nombre_estadistica);
        VALUE_FECHA_ESTADISTICA = context.getString(R.string.fecha_estadistica);
        VALUE_HORA_ESTADISTICA = context.getString(R.string.hora_estadistica);
        VALUE_AFORO_ESTADISTICA = context.getString(R.string.aforo_estadistica);
        VALUE_GRADO_ESTADISTICA = context.getString(R.string.grado_estadistica);
        VALUE_UBICACION_ESTADISTICA = context.getString(R.string.ubicacion_estadistica);
        VALUE_IDIOMA_ESTADISTICA = context.getString(R.string.idioma_estadistica);

        // VISION POR COMPUTADOR
        VALUE_NOMBRE_VISIONPORCOMPUTADOR = context.getString(R.string.nombre_visionporcomputador);
        VALUE_FECHA_VISIONPORCOMPUTADOR = context.getString(R.string.fecha_visionporcomputador);
        VALUE_HORA_VISIONPORCOMPUTADOR = context.getString(R.string.hora_visionporcomputador);
        VALUE_AFORO_VISIONPORCOMPUTADOR = context.getString(R.string.aforo_visionporcomputador);
        VALUE_GRADO_VISIONPORCOMPUTADOR = context.getString(R.string.grado_visionporcomputador);
        VALUE_UBICACION_VISIONPORCOMPUTADOR = context.getString(R.string.ubicacion_visionporcomputador);
        VALUE_IDIOMA_VISIONPORCOMPUTADOR = context.getString(R.string.idioma_visionporcomputador);

        // PROCESADORES DE LENGUAJES
        VALUE_NOMBRE_PROCESADORESDELENGUAJES = context.getString(R.string.nombre_procesadoresdelenguajes);
        VALUE_FECHA_PROCESADORESDELENGUAJES = context.getString(R.string.fecha_procesadoresdelenguajes);
        VALUE_HORA_PROCESADORESDELENGUAJES = context.getString(R.string.hora_procesadoresdelenguajes);
        VALUE_AFORO_PROCESADORESDELENGUAJES = context.getString(R.string.aforo_procesadoresdelenguajes);
        VALUE_GRADO_PROCESADORESDELENGUAJES = context.getString(R.string.grado_procesadoresdelenguajes);
        VALUE_UBICACION_PROCESADORESDELENGUAJES = context.getString(R.string.ubicacion_procesadoresdelenguajes);
        VALUE_IDIOMA_PROCESADORESDELENGUAJES = context.getString(R.string.idioma_procesadoresdelenguajes);

        // NUEVOS PARADIGMAS
        VALUE_NOMBRE_NUEVOSPARADIGMAS = context.getString(R.string.nombre_nuevosparadigmas);
        VALUE_FECHA_NUEVOSPARADIGMAS = context.getString(R.string.fecha_nuevosparadigmas);
        VALUE_HORA_NUEVOSPARADIGMAS = context.getString(R.string.hora_nuevosparadigmas);
        VALUE_AFORO_NUEVOSPARADIGMAS = context.getString(R.string.aforo_nuevosparadigmas);
        VALUE_GRADO_NUEVOSPARADIGMAS = context.getString(R.string.grado_nuevosparadigmas);
        VALUE_UBICACION_NUEVOSPARADIGMAS = context.getString(R.string.ubicacion_nuevosparadigmas);
        VALUE_IDIOMA_NUEVOSPARADIGMAS = context.getString(R.string.idioma_nuevosparadigmas);

        // PADEL
        VALUE_NOMBRE_PADEL = context.getString(R.string.nombre_padel);
        VALUE_FECHA_PADEL = context.getString(R.string.fecha_padel);
        VALUE_HORA_PADEL = context.getString(R.string.hora_padel);
        VALUE_AFORO_PADEL = context.getString(R.string.aforo_padel);
        VALUE_GRADO_PADEL = context.getString(R.string.grado_padel);
        VALUE_UBICACION_PADEL = context.getString(R.string.ubicacion_padel);
        VALUE_IDIOMA_PADEL = context.getString(R.string.idioma_padel);

        // CHARLA TFG
        VALUE_NOMBRE_CHARLA_TFG = context.getString(R.string.nombre_charla_tfg);
        VALUE_FECHA_CHARLA_TFG = context.getString(R.string.fecha_charla_tfg);
        VALUE_HORA_CHARLA_TFG = context.getString(R.string.hora_charla_tfg);
        VALUE_AFORO_CHARLA_TFG = context.getString(R.string.aforo_charla_tfg);
        VALUE_GRADO_CHARLA_TFG = context.getString(R.string.grado_charla_tfg);
        VALUE_UBICACION_CHARLA_TFG = context.getString(R.string.ubicacion_charla_tfg);
        VALUE_IDIOMA_CHARLA_TFG = context.getString(R.string.idioma_charla_tfg);

        // CENA DGIIM
        VALUE_NOMBRE_CENA_DGIIM = context.getString(R.string.nombre_cena_dgiim);
        VALUE_FECHA_CENA_DGIIM = context.getString(R.string.fecha_cena_dgiim);
        VALUE_HORA_CENA_DGIIM = context.getString(R.string.hora_cena_dgiim);
        VALUE_AFORO_CENA_DGIIM = context.getString(R.string.aforo_cena_dgiim);
        VALUE_GRADO_CENA_DGIIM = context.getString(R.string.grado_cena_dgiim);
        VALUE_UBICACION_CENA_DGIIM = context.getString(R.string.ubicacion_cena_dgiim);
        VALUE_IDIOMA_CENA_DGIIM = context.getString(R.string.idioma_cena_dgiim);

        // FUTBOL
        VALUE_NOMBRE_FUTBOL = context.getString(R.string.nombre_futbol);
        VALUE_FECHA_FUTBOL = context.getString(R.string.fecha_futbol);
        VALUE_HORA_FUTBOL = context.getString(R.string.hora_futbol);
        VALUE_AFORO_FUTBOL = context.getString(R.string.aforo_futbol);
        VALUE_GRADO_FUTBOL = context.getString(R.string.grado_futbol);
        VALUE_UBICACION_FUTBOL = context.getString(R.string.ubicacion_futbol);
        VALUE_IDIOMA_FUTBOL = context.getString(R.string.idioma_futbol);

        // CARRERA SAN ALBERTO
        VALUE_NOMBRE_CARRERA_SALBERTO = context.getString(R.string.nombre_carrera_salberto);
        VALUE_FECHA_CARRERA_SALBERTO = context.getString(R.string.fecha_carrera_salberto);
        VALUE_HORA_CARRERA_SALBERTO = context.getString(R.string.hora_carrera_salberto);
        VALUE_AFORO_CARRERA_SALBERTO = context.getString(R.string.aforo_carrera_salberto);
        VALUE_GRADO_CARRERA_SALBERTO = context.getString(R.string.grado_carrera_salberto);
        VALUE_UBICACION_CARRERA_SALBERTO = context.getString(R.string.ubicacion_carrera_salberto);
        VALUE_IDIOMA_CARRERA_SALBERTO = context.getString(R.string.idioma_carrera_salberto);

        /////////////////////////////////////////////////////

        // TABLA USUARIO-EVENTO
        TABLA_USUARIO_EVENTO = context.getString(R.string.tabla_usuario_evento);
        ID_USUARIO = context.getString(R.string.ue_id_usuario);
        ID_EVENTO = context.getString(R.string.ue_id_evento);
    }


    // Inicializamos los Strings de las sentencias para la creación de las tablas
    private void crearValores(){
        CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLA_USUARIO + "(" +
                USUARIO + " TEXT primary key, " + CONTRASEÑA + " TEXT NOT NULL, " + NOMBRE +
                " TEXT NOT NULL, " + CARRERA + " TEXT NOT NULL, " + CURSO + " TEXT NOT NULL, " +
                EDAD + " TEXT NOT NULL, " + FOTO + " TEXT NOT NULL);";

        CREATE_TABLE_EVENTO = "CREATE TABLE " + TABLA_EVENTO + "(" +
                NOMBRE_EVENTO + " TEXT primary key, " +
                FECHA + " TEXT NOT NULL, " + HORA + " TEXT NOT NULL, " + AFORO + " integer, " +
                GRADO + " TEXT, " + UBICACION + " TEXT NOT NULL, " + IDIOMA + " TEXT);";

        CREATE_TABLE_USUARIO_EVENTO = "CREATE TABLE " + TABLA_USUARIO_EVENTO + "(" +
                ID_USUARIO + " TEXT, " +
                ID_EVENTO + " TEXT, PRIMARY KEY( " + ID_USUARIO +
                ", " + ID_EVENTO + "));";

    }

    // Inicializamos los Strings de las sentencias de inserción para la creación de las tablas
    private void insertarValores(){
        //INSERT DE LA TABLA USUARIO

        // SILVIA
        INSERT_SILVIA_INTO_USUARIO = "INSERT INTO " + TABLA_USUARIO + " (" + USUARIO + ", " +
                CONTRASEÑA + ", " + NOMBRE + ", " + CARRERA + ", " + CURSO + ", " + EDAD + ", " +
                FOTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_CONT_SILVIA + "', " +
                "'" + VALUE_NOM_SILVIA + "', " +
                "'" + VALUE_CAR_SILVIA + "', " +
                "'" + VALUE_CUR_SILVIA + "', " +
                "'" + VALUE_EDA_SILVIA + "', " +
                "'" + VALUE_FOT_SILVIA +"');";

        // CARLOTA
        INSERT_CARLOTA_INTO_USUARIO = "INSERT INTO " + TABLA_USUARIO + " (" + USUARIO + ", " +
                CONTRASEÑA + ", " + NOMBRE + ", " + CARRERA + ", " + CURSO + ", " + EDAD + ", " +
                FOTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_CONT_CARLOTA + "', " +
                "'" + VALUE_NOM_CARLOTA + "', " +
                "'" + VALUE_CAR_CARLOTA + "', " +
                "'" + VALUE_CUR_CARLOTA + "', " +
                "'" + VALUE_EDA_CARLOTA + "', " +
                "'" + VALUE_FOT_CARLOTA +"');";

        // ELENA
        INSERT_ELENA_INTO_USUARIO = "INSERT INTO " + TABLA_USUARIO + " (" + USUARIO + ", " +
                CONTRASEÑA + ", " + NOMBRE + ", " + CARRERA + ", " + CURSO + ", " + EDAD + ", " +
                FOTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_CONT_ELENA + "', " +
                "'" + VALUE_NOM_ELENA + "', " +
                "'" + VALUE_CAR_ELENA + "', " +
                "'" + VALUE_CUR_ELENA + "', " +
                "'" + VALUE_EDA_ELENA + "', " +
                "'" + VALUE_FOT_ELENA +"');";

        // ADRIAN
        INSERT_ADRIAN_INTO_USUARIO = "INSERT INTO " + TABLA_USUARIO + " (" + USUARIO + ", " +
                CONTRASEÑA + ", " + NOMBRE + ", " + CARRERA + ", " + CURSO + ", " + EDAD + ", " +
                FOTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_CONT_ADRIAN + "', " +
                "'" + VALUE_NOM_ADRIAN + "', " +
                "'" + VALUE_CAR_ADRIAN + "', " +
                "'" + VALUE_CUR_ADRIAN + "', " +
                "'" + VALUE_EDA_ADRIAN + "', " +
                "'" + VALUE_FOT_ADRIAN +"');";

        // JUAN
        INSERT_JUAN_INTO_USUARIO = "INSERT INTO " + TABLA_USUARIO + " (" + USUARIO + ", " +
                CONTRASEÑA + ", " + NOMBRE + ", " + CARRERA + ", " + CURSO + ", " + EDAD + ", " +
                FOTO + ") " +
                "VALUES ('" + VALUE_USU_JUAN + "', " +
                "'" + VALUE_CONT_JUAN + "', " +
                "'" + VALUE_NOM_JUAN + "', " +
                "'" + VALUE_CAR_JUAN + "', " +
                "'" + VALUE_CUR_JUAN + "', " +
                "'" + VALUE_EDA_JUAN + "', " +
                "'" + VALUE_FOT_JUAN +"');";

        /////////////////////////////////////////////////////

        //INSERT DE LA TABLA EVENTO

        // ANÁLISIS VECTORIAL
        INSERT_ANALISISVECTORIAL_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_ANALISISVECTORIAL + "', " +
                "'" + VALUE_FECHA_ANALISISVECTORIAL + "', " +
                "'" + VALUE_HORA_ANALISISVECTORIAL + "', " +
                "'" + VALUE_AFORO_ANALISISVECTORIAL + "', " +
                "'" + VALUE_GRADO_ANALISISVECTORIAL + "', " +
                "'" + VALUE_UBICACION_ANALISISVECTORIAL + "', " +
                "'" + VALUE_IDIOMA_ANALISISVECTORIAL + "');";

        // ÁLGEBRA CONMUTATIVA
        INSERT_ALGEBRACONMUTATIVA_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_ALGEBRACONMUTATIVA + "', " +
                "'" +  VALUE_FECHA_ALGEBRACONMUTATIVA + "', " +
                "'" + VALUE_HORA_ALGEBRACONMUTATIVA + "', " +
                "'" + VALUE_AFORO_ALGEBRACONMUTATIVA + "', " +
                "'" + VALUE_GRADO_ALGEBRACONMUTATIVA + "', " +
                "'" + VALUE_UBICACION_ALGEBRACONMUTATIVA + "', " +
                "'" + VALUE_IDIOMA_ALGEBRACONMUTATIVA + "');";

        // ESTADISTICA
        INSERT_ESTADISTICA_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_ESTADISTICA + "', " +
                "'" +  VALUE_FECHA_ESTADISTICA + "', " +
                "'" + VALUE_HORA_ESTADISTICA + "', " +
                "'" + VALUE_AFORO_ESTADISTICA + "', " +
                "'" + VALUE_GRADO_ESTADISTICA + "', " +
                "'" + VALUE_UBICACION_ESTADISTICA + "', " +
                "'" + VALUE_IDIOMA_ESTADISTICA + "');";

        // VISION POR COMPUTADOR
        INSERT_VISIONPORCOMPUTADOR_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_VISIONPORCOMPUTADOR + "', " +
                "'" +   VALUE_FECHA_VISIONPORCOMPUTADOR + "', " +
                "'" + VALUE_HORA_VISIONPORCOMPUTADOR + "', " +
                "'" + VALUE_AFORO_VISIONPORCOMPUTADOR + "', " +
                "'" + VALUE_GRADO_VISIONPORCOMPUTADOR + "', " +
                "'" + VALUE_UBICACION_VISIONPORCOMPUTADOR + "', " +
                "'" + VALUE_IDIOMA_VISIONPORCOMPUTADOR + "');";

        // IPROCESADORES DE LENGUAJES
        INSERT_PROCESADORESDELENGUAJES_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_PROCESADORESDELENGUAJES + "', " +
                "'" +  VALUE_FECHA_PROCESADORESDELENGUAJES + "', " +
                "'" + VALUE_HORA_PROCESADORESDELENGUAJES + "', " +
                "'" + VALUE_AFORO_PROCESADORESDELENGUAJES + "', " +
                "'" + VALUE_GRADO_PROCESADORESDELENGUAJES + "', " +
                "'" + VALUE_UBICACION_PROCESADORESDELENGUAJES + "', " +
                "'" + VALUE_IDIOMA_PROCESADORESDELENGUAJES + "');";

        // NUEVOS PARADIGMAS
        INSERT_NUEVOSPARADIGMAS_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_FECHA_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_HORA_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_AFORO_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_GRADO_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_UBICACION_NUEVOSPARADIGMAS + "', " +
                "'" + VALUE_IDIOMA_NUEVOSPARADIGMAS + "');";

        // PADEL
        INSERT_PADEL_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_PADEL + "', " +
                "'" +  VALUE_FECHA_PADEL + "', " +
                "'" + VALUE_HORA_PADEL + "', " +
                "'" + VALUE_AFORO_PADEL + "', " +
                "'" + VALUE_GRADO_PADEL + "', " +
                "'" + VALUE_UBICACION_PADEL + "', " +
                "'" + VALUE_IDIOMA_PADEL + "');";

        // CHARLA TFG
        INSERT_CHARLA_TFG_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_CHARLA_TFG + "', " +
                "'" +  VALUE_FECHA_CHARLA_TFG + "', " +
                "'" + VALUE_HORA_CHARLA_TFG + "', " +
                "'" + VALUE_AFORO_CHARLA_TFG + "', " +
                "'" + VALUE_GRADO_CHARLA_TFG + "', " +
                "'" + VALUE_UBICACION_CHARLA_TFG + "', " +
                "'" + VALUE_IDIOMA_CHARLA_TFG + "');";

        // CENA DGIIM
        INSERT_CENA_DGIIM_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_CENA_DGIIM + "', " +
                "'" +  VALUE_FECHA_CENA_DGIIM + "', " +
                "'" + VALUE_HORA_CENA_DGIIM + "', " +
                "'" + VALUE_AFORO_CENA_DGIIM +  "', " +
                "'" + VALUE_GRADO_CENA_DGIIM + "', " +
                "'" + VALUE_UBICACION_CENA_DGIIM + "', " +
                "'" + VALUE_IDIOMA_CENA_DGIIM + "');";

        // FUTBOL
        INSERT_FUTBOL_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_FUTBOL + "', " +
                "'" +  VALUE_FECHA_FUTBOL + "', " +
                "'" + VALUE_HORA_FUTBOL + "', " +
                "'" + VALUE_AFORO_FUTBOL + "', " +
                "'" + VALUE_GRADO_FUTBOL + "', " +
                "'" + VALUE_UBICACION_FUTBOL + "', " +
                "'" + VALUE_IDIOMA_FUTBOL + "');";

        // CARRERA SALBERTO
        INSERT_CARRERA_SALBERTO_INTO_EVENTO = "INSERT INTO " + TABLA_EVENTO + " (" +
                NOMBRE_EVENTO + ", " + FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " +
                UBICACION + ", " + IDIOMA + ") " +
                "VALUES ('" + VALUE_NOMBRE_CARRERA_SALBERTO + "', " +
                "'" +  VALUE_FECHA_CARRERA_SALBERTO + "', " +
                "'" + VALUE_HORA_CARRERA_SALBERTO + "', " +
                "'" + VALUE_AFORO_CARRERA_SALBERTO + "', " +
                "'" + VALUE_GRADO_CARRERA_SALBERTO + "', " +
                "'" + VALUE_UBICACION_CARRERA_SALBERTO + "', " +
                "'" + VALUE_IDIOMA_CARRERA_SALBERTO + "');";

        ///////////////////////////////////////////////////

        //INSERT DE LA TABLA USUARIO-EVENTO

        // EVENTOS DE SILVIA

        // SILVIA - ANÁLISIS VECTORIAL
        INSERT_SILVIA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_ANALISISVECTORIAL +  "');";

        // SILVIA - ÁLGEBRA CONMUTATIVA
        INSERT_SILVIA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_ALGEBRACONMUTATIVA +  "');";

        // SILVIA - ESTADISTICA MULTIVARIANTE
        INSERT_SILVIA_ESTADISTICA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_ESTADISTICA +  "');";

        // SILVIA - VISION POR COMPUTADOR
        INSERT_SILVIA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_VISIONPORCOMPUTADOR +  "');";

        // SILVIA - PROCESADORES DE LENGUAJES
        INSERT_SILVIA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_PROCESADORESDELENGUAJES +  "');";

        // SILVIA - NUEVOS PARADIGMAS
        INSERT_SILVIA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_NUEVOSPARADIGMAS +  "');";

        // SILVIA - CARRERA SAN ALBERTO
        INSERT_SILVIA_CARRERA_SALBERTO_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_SILVIA + "', " +
                "'" + VALUE_NOMBRE_CARRERA_SALBERTO +  "');";



        ////////////////////////////////////////////////

        // EVENTOS DE CARLOTA

        // CARLOTA - ANÁLISIS VECTORIAL
        INSERT_CARLOTA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_ANALISISVECTORIAL +  "');";

        // CARLOTA - ÁLGEBRA CONMUTATIVA
        INSERT_CARLOTA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_ALGEBRACONMUTATIVA +  "');";

        // CARLOTA - ESTADISTICA MULTIVARIANTE
        INSERT_CARLOTA_ESTADISTICA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_ESTADISTICA +  "');";

        // CARLOTA - VISION POR COMPUTADOR
        INSERT_CARLOTA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_VISIONPORCOMPUTADOR +  "');";

        // CARLOTA - PROCESADORES DE LENGUAJES
        INSERT_CARLOTA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_PROCESADORESDELENGUAJES +  "');";

        // CARLOTA - NUEVOS PARADIGMAS
        INSERT_CARLOTA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_NUEVOSPARADIGMAS +  "');";

        // CARLOTA - CHARLA TFG
        INSERT_CARLOTA_CHARLA_TFG_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_CARLOTA + "', " +
                "'" + VALUE_NOMBRE_CHARLA_TFG +  "');";

        ////////////////////////////////////////////////

        // EVENTOS DE ELENA

        // ELENA - ANÁLISIS VECTORIAL
        INSERT_ELENA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_ANALISISVECTORIAL +  "');";

        // ELENA - ÁLGEBRA CONMUTATIVA
        INSERT_ELENA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_ALGEBRACONMUTATIVA +  "');";

        // ELENA - ESTADISTICA MULTIVARIANTE
        INSERT_ELENA_ESTADISTICA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_ESTADISTICA +  "');";

        // ELENA - VISION POR COMPUTADOR
        INSERT_ELENA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_VISIONPORCOMPUTADOR +  "');";

        // ELENA - PROCESADORES DE LENGUAJES
        INSERT_ELENA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_PROCESADORESDELENGUAJES +  "');";

        // ELENA - NUEVOS PARADIGMAS
        INSERT_ELENA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_NUEVOSPARADIGMAS +  "');";

        // ELENA - PADEL
        INSERT_ELENA_PADEL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ELENA + "', " +
                "'" + VALUE_NOMBRE_PADEL +  "');";


        ////////////////////////////////////////////////

        // EVENTOS DE ADRIÁN

        // ADRIAN - ANÁLISIS VECTORIAL
        INSERT_ADRIAN_ANALISISVECTORIAL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_ANALISISVECTORIAL +  "');";

        // ADRIAN - ÁLGEBRA CONMUTATIVA
        INSERT_ADRIAN_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_ALGEBRACONMUTATIVA +  "');";

        // ADRIAN - ESTADISTICA MULTIVARIANTE
        INSERT_ADRIAN_ESTADISTICA_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_ESTADISTICA +  "');";

        // ADRIAN - VISION POR COMPUTADOR
        INSERT_ADRIAN_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_VISIONPORCOMPUTADOR +  "');";

        // ADRIAN - PROCESADORES DE LENGUAJES
        INSERT_ADRIAN_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_PROCESADORESDELENGUAJES +  "');";

        // ADRIAN - NUEVOS PARADIGMAS
        INSERT_ADRIAN_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_NUEVOSPARADIGMAS +  "');";

        // ADRIAN - FUTBOL
        INSERT_ADRIAN_FUTBOL_INTO_USUARIO_EVENTO = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" +
                ID_USUARIO + ", " + ID_EVENTO + ") " +
                "VALUES ('" + VALUE_USU_ADRIAN + "', " +
                "'" + VALUE_NOMBRE_FUTBOL +  "');";

    }

    // Se llama a la creación de la actividad
    // Realizamos las inicializaciones
    // Se llama si la Base de Datos no existe
    @Override
    public void onCreate(SQLiteDatabase db) {
        crearValores();     // Inicializamos las sentencias para la creación de las Tablas
        insertarValores();  // Inicializamos las sentencias para cada INSERT

        // Creamos la tabla de USUARIO y realizamos las inserciones
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(INSERT_SILVIA_INTO_USUARIO);
        db.execSQL(INSERT_CARLOTA_INTO_USUARIO);
        db.execSQL(INSERT_ELENA_INTO_USUARIO);
        db.execSQL(INSERT_ADRIAN_INTO_USUARIO);
        db.execSQL(INSERT_JUAN_INTO_USUARIO);

        // Creamos la tabla de EVENTO y realizamos las inserciones
        db.execSQL(CREATE_TABLE_EVENTO);
        db.execSQL(INSERT_ANALISISVECTORIAL_INTO_EVENTO);
        db.execSQL(INSERT_ALGEBRACONMUTATIVA_INTO_EVENTO);
        db.execSQL(INSERT_ESTADISTICA_INTO_EVENTO);
        db.execSQL(INSERT_VISIONPORCOMPUTADOR_INTO_EVENTO);
        db.execSQL(INSERT_PROCESADORESDELENGUAJES_INTO_EVENTO);
        db.execSQL(INSERT_NUEVOSPARADIGMAS_INTO_EVENTO);
        db.execSQL(INSERT_PADEL_INTO_EVENTO);
        db.execSQL(INSERT_CHARLA_TFG_INTO_EVENTO);
        db.execSQL(INSERT_CENA_DGIIM_INTO_EVENTO);
        db.execSQL(INSERT_FUTBOL_INTO_EVENTO);
        db.execSQL(INSERT_CARRERA_SALBERTO_INTO_EVENTO);

        // Creamos la tabla de USUARIO_EVENTO y realizamos las inserciones
        db.execSQL(CREATE_TABLE_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_ESTADISTICA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_SILVIA_CARRERA_SALBERTO_INTO_USUARIO_EVENTO);

        db.execSQL(INSERT_CARLOTA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_ESTADISTICA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_CARLOTA_CHARLA_TFG_INTO_USUARIO_EVENTO);

        db.execSQL(INSERT_ELENA_ANALISISVECTORIAL_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_ESTADISTICA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ELENA_PADEL_INTO_USUARIO_EVENTO);

        db.execSQL(INSERT_ADRIAN_ANALISISVECTORIAL_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_ALGEBRACONMUTATIVA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_ESTADISTICA_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_VISIONPORCOMPUTADOR_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_PROCESADORESDELENGUAJES_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_NUEVOSPARADIGMAS_INTO_USUARIO_EVENTO);
        db.execSQL(INSERT_ADRIAN_FUTBOL_INTO_USUARIO_EVENTO);
    }

    // Manejo de nuevos cambios
    // Cuando se produce un cambio de versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_EVENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO_EVENTO);
        onCreate(db);
    }


    public void crearEvento(String nombre, String fecha, String hora, Integer aforo, String grado, String ubicacion, String idioma){
        // Abrimos la base de datos en modo escritura
        dbEventUp = getWritableDatabase();

        if (dbEventUp != null){
            String sent = "INSERT INTO " + TABLA_EVENTO + " (" + NOMBRE_EVENTO + ", " +
                    FECHA + ", " + HORA + ", " + AFORO + ", " + GRADO + ", " + UBICACION + ", " +
                    IDIOMA + ") " +
                    "VALUES ('" + nombre + "', " +
                    "'" + fecha + "', " +
                    "'" + hora + "', " +
                    "'" + aforo + "', " +
                    "'" + grado + "', " +
                    "'" + ubicacion + "', " +
                    "'" + idioma + "');";

            // Añadimos la nueva fila a la base de datos
            dbEventUp.execSQL(sent);
        }

        // Cerramos la base de datos
        dbEventUp.close();
    }

    public void eliminarEvento(String nombre){
        // Abrimos la base de datos en modo escritura
        dbEventUp = getWritableDatabase();

        if (dbEventUp != null){
            //Eliminamos la fila
            dbEventUp.delete(TABLA_EVENTO, NOMBRE_EVENTO = nombre, null);
            dbEventUp.delete(TABLA_USUARIO_EVENTO, ID_EVENTO = nombre, null);
        }
        // Cerramos la base de datos
        dbEventUp.close();
    }

    public void modificarEvento(String nombre, String fecha, String hora, Integer aforo, String grado, String ubicacion, String idioma){
        // Abrimos la base de datos en modo escritura
        dbEventUp = getWritableDatabase();

        if (dbEventUp != null){
            ContentValues valores = new ContentValues();

            valores.put(FECHA, fecha);
            valores.put(HORA, hora);
            valores.put(AFORO, aforo);
            valores.put(GRADO, grado);
            valores.put(UBICACION, ubicacion);
            valores.put(IDIOMA, idioma);

            dbEventUp.update(TABLA_EVENTO, valores, NOMBRE_EVENTO+"='"+nombre+"'", null);
        }

        // Cerramos la base de datos
        dbEventUp.close();
    }

    public void asignarEvento(String id_usuario, String id_evento){
        // Abrimos la base de datos en modo escritura
        dbEventUp = getWritableDatabase();

        if (dbEventUp != null){
            String sent = "INSERT INTO " + TABLA_USUARIO_EVENTO + " (" + ID_USUARIO + ", " +
                    ID_EVENTO + ") " +
                    "VALUES ('" + id_usuario + "', " +
                    "'" + id_evento + "');";

            // Añadimos la nueva fila a la base de datos
            dbEventUp.execSQL(sent);
        }

        // Cerramos la base de datos
        dbEventUp.close();
    }

    public void desasignarEvento(String id_usuario, String id_evento){
        // Abrimos la base de datos en modo escritura
        dbEventUp = getWritableDatabase();

        if (dbEventUp != null){
            dbEventUp.delete(TABLA_USUARIO_EVENTO, ID_EVENTO+"='"+id_evento+"' and "+ID_USUARIO+"='"+id_usuario+"'", null);
        }

        // Cerramos la base de datos
        dbEventUp.close();
    }

}