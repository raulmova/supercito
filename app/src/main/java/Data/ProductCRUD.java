package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Raul on 11/10/2017.
 */


public class ProductCRUD {

    private DataBaseHelper helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public ProductCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void newProduct(Product item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        //values.put(ProductContract.Entrada.COLUMNA_ID, item.getId());
        values.put(ProductContract.Entrada.COLUMNA_NAME, item.getProductName());
        values.put(ProductContract.Entrada.COLUMNA_PRICE, item.getProductPrice());
        values.put(ProductContract.Entrada.COLUMNA_PHOTO, item.getPhotoURL());

        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(ProductContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
    }

    public ArrayList<Product> getProducts(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Product> items = new ArrayList<Product>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ProductContract.Entrada.COLUMNA_ID,
                ProductContract.Entrada.COLUMNA_NAME,
                ProductContract.Entrada.COLUMNA_PRICE,
                ProductContract.Entrada.COLUMNA_PHOTO,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                ProductContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Product(
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_NAME)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_PRICE)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_PHOTO))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Product getProduct(String id){
        Product item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ProductContract.Entrada.COLUMNA_ID,
                ProductContract.Entrada.COLUMNA_NAME,
                ProductContract.Entrada.COLUMNA_PRICE,
                ProductContract.Entrada.COLUMNA_PHOTO,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                ProductContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Product(
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_NAME)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_PRICE)),
                    c.getString(c.getColumnIndexOrThrow(ProductContract.Entrada.COLUMNA_PHOTO))
            );
        }

        c.close();
        return item;
    }

    public void updateProduct(Product item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        //values.put(ProductContract.Entrada.COLUMNA_ID, item.getId());
        values.put(ProductContract.Entrada.COLUMNA_NAME, item.getProductName());
        values.put(ProductContract.Entrada.COLUMNA_PRICE, item.getProductPrice());
        values.put(ProductContract.Entrada.COLUMNA_PHOTO, item.getPhotoURL());

        // TODO: 19.- Actualizamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.update(
                ProductContract.Entrada.NOMBRE_TABLA,
                values,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        // cierra conexión
        db.close();
    }

    public void deleteProduct(Product item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 20.- Eliminamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.delete(
                ProductContract.Entrada.NOMBRE_TABLA,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();
    }


}