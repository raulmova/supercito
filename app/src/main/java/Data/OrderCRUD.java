package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Raul on 13/10/2017.
 */

public class OrderCRUD {

    private DataBaseHelper helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public OrderCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void newOrder(Order item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(OrderContract.Entrada.COLUMNA_ID,item.getId());
        values.put(OrderContract.Entrada.COLUMNA_DATE,item.getDate());

        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(OrderContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
    }

    public ArrayList<Order> getOrders(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Order> items = new ArrayList<Order>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                OrderContract.Entrada.COLUMNA_ID,
                OrderContract.Entrada.COLUMNA_DATE,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                OrderContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Order(
                    c.getString(c.getColumnIndexOrThrow(OrderContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(OrderContract.Entrada.COLUMNA_DATE))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Order getOrder(String id){
        Order item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                OrderContract.Entrada.COLUMNA_ID,
                OrderContract.Entrada.COLUMNA_DATE,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                OrderContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Order(
                    c.getString(c.getColumnIndexOrThrow(OrderContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(OrderContract.Entrada.COLUMNA_DATE))
            );
        }

        c.close();
        return item;
    }

}
