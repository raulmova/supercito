package Data;

/**
 * Created by Raul on 13/10/2017.
 */

public class Order {
    private String id;
    private String date;

    public Order(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order: " + id + ". Created on: " + this.date;
    }
}

