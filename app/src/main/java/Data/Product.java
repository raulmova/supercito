package Data;

/**
 * Created by Raul on 11/10/2017.
 */

public class Product {
    private String productName;
    private String productPrice;
    private String photoURL;
    private String id;

    public Product(String id, String productName, String productPrice, String photoURL) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.photoURL = photoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return this.id+". "+this.productName+". "+this.productPrice+"$";
    }
}
