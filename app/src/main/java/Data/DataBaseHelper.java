package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Raul on 11/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // TODO: 4.- declaramos variables para nombre y versión de DB
    private static final String DB_NOMBRE = "productos.db";
    private static final int DB_VERSION = 8;


    // TODO: 5.- Creación de sentencia SQL para crear tabla
    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE "
            + ProductContract.Entrada.NOMBRE_TABLA + "("
            + ProductContract.Entrada.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ProductContract.Entrada.COLUMNA_NAME + " TEXT,"
            + ProductContract.Entrada.COLUMNA_PRICE + " TEXT,"
            + ProductContract.Entrada.COLUMNA_PHOTO + " TEXT"
            +") ";

    public static final String CREATE_ORDER_TABLE = "CREATE TABLE "
            + OrderContract.Entrada.NOMBRE_TABLA + "("
            + OrderContract.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, "
            + OrderContract.Entrada.COLUMNA_DATE + " TEXT" +") ";

    public static final String CREATE_ORDERDETAIL_TABLE = "CREATE TABLE "
            + OrderDetailContract.Entrada.NOMBRE_TABLA + "("
            + OrderDetailContract.Entrada.COLUMNA_ID + " TEXT, "
            + OrderDetailContract.Entrada.COLUMNA_ID_PRODUCT + " TEXT ,"
            + OrderDetailContract.Entrada.COLUMNA_QUANTITY + " INTEGER, "
            + OrderDetailContract.Entrada.COLUMNA_PRECIO + " INTEGER"
            +") ";



    // TODO: 6.- Creación de sentencia SQL para eliminar tabla
    private static final String SQL_DELETE_ENTRIES_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductContract.Entrada.NOMBRE_TABLA;

    private static final String SQL_DELETE_ENTRIES_ORDER =
            "DROP TABLE IF EXISTS " + OrderContract.Entrada.NOMBRE_TABLA;

    private static final String SQL_DELETE_ENTRIES_ORDERDETAIL =
            "DROP TABLE IF EXISTS " + OrderDetailContract.Entrada.NOMBRE_TABLA;

    // TODO: 7.- Constructor
    public DataBaseHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDERDETAIL_TABLE);
        //sqLiteDatabase.execSQL(CREATE_ORDERDETAIL_TABLE);
    }

    // TODO: 9.- Para actualizar las tablas cuando cambie de versión la DB
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_PRODUCT);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_ORDER);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_ORDERDETAIL);

        onCreate(sqLiteDatabase);
    }
}
