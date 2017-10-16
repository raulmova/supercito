package Data;

/**
 * Created by Raul on 13/10/2017.
 */

public class OrderDetail {
    private String id;
    private String id_product;
    private int quantity;
    private int price;

    public OrderDetail(String id, String id_product, int quantity, int price) {
        this.id = id;
        this.id_product = id_product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: "+id_product +". \n"+"Quantity: "+quantity +". \n"+"Total:"+price;
    }
}

