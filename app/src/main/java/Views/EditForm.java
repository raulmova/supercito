package Views;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raul.shopkart.R;

import Data.Product;
import Data.ProductCRUD;

public class EditForm extends AppCompatActivity {

    private EditText etProduct, etPrice, etID;
    private Button bPhoto, bSave, bSelect;
    private ImageView ivPhoto;
    private TextView tvUrl;
    private ProductCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);

        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        final String id = getIntent().getStringExtra("productId");
        final String url = getIntent().getStringExtra("productURL");

        etProduct = (EditText)findViewById(R.id.etEditProduct);
        etPrice = (EditText)findViewById(R.id.etEditPrice);
        bPhoto = (Button) findViewById(R.id.bEditSave);
        crud = new ProductCRUD(this);

        etProduct.setText(name);
        etPrice.setText(price);

        bPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etProduct.getText().toString().isEmpty() || etPrice.getText().toString().isEmpty()){
                    Snackbar.make(view, "Some field is empty", Snackbar.LENGTH_SHORT)
                            .show();
                }
                else{
                    crud.updateProduct(new Product(id, etProduct.getText().toString(),etPrice.getText().toString(),url));
                    //finish();
                    startActivity(new Intent(EditForm.this, List.class));
                }
            }
        });

    }
}
