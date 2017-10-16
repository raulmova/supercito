package Data;

import android.provider.BaseColumns;

/**
 * Created by Raul on 11/10/2017.
 */

public class ProductContract {
    private ProductContract(){}

    public static class Entrada implements BaseColumns {
        public static final String NOMBRE_TABLA = "productos";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NAME = "name";
        public static final String COLUMNA_PRICE = "price";
        public static final String COLUMNA_PHOTO = "photo";
    }
}
