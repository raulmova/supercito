package Data;

import android.provider.BaseColumns;

/**
 * Created by Raul on 13/10/2017.
 */

public class OrderContract {
    private OrderContract(){}

    public static class Entrada implements BaseColumns {
        public static final String NOMBRE_TABLA = "orders";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_DATE = "date";
    }
}
