package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Raul on 13/10/2017.
 */

public class OrderDetailCRUD {

    private DataBaseHelper helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public OrderDetailCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void newOrderDetail(OrderDetail item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(OrderDetailContract.Entrada.COLUMNA_ID, item.getId());
        values.put(OrderDetailContract.Entrada.COLUMNA_ID_PRODUCT, item.getId_product());
        values.put(OrderDetailContract.Entrada.COLUMNA_QUANTITY, item.getQuantity());
        values.put(OrderDetailContract.Entrada.COLUMNA_PRECIO, item.getPrice());

        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(OrderDetailContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
    }

    public ArrayList<OrderDetail> getOrderDetail(String id){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<OrderDetail> items = new ArrayList<OrderDetail>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                OrderDetailContract.Entrada.COLUMNA_ID,
                OrderDetailContract.Entrada.COLUMNA_ID_PRODUCT,
                OrderDetailContract.Entrada.COLUMNA_QUANTITY,
                OrderDetailContract.Entrada.COLUMNA_PRECIO,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                OrderDetailContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new OrderDetail(
                    c.getString(c.getColumnIndexOrThrow(OrderDetailContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(OrderDetailContract.Entrada.COLUMNA_ID_PRODUCT)),
                    c.getInt(c.getColumnIndexOrThrow(OrderDetailContract.Entrada.COLUMNA_QUANTITY)),
                    c.getInt(c.getColumnIndexOrThrow(OrderDetailContract.Entrada.COLUMNA_PRECIO))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }


}
