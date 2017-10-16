package Data;

import android.provider.BaseColumns;

/**
 * Created by Raul on 13/10/2017.
 */

public class OrderDetailContract {
    private OrderDetailContract(){}

    public static class Entrada implements BaseColumns {
        public static final String NOMBRE_TABLA = "OrderDetail";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_ID_PRODUCT = "id_product";
        public static final String COLUMNA_QUANTITY = "cantidad";
        public static final String COLUMNA_PRECIO = "precio";

    }
}
