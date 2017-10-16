package Views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.raul.shopkart.R;

import java.util.ArrayList;

import Adapters.RecycleViewCustomAdapterThree;
import Data.OrderDetail;
import Data.OrderDetailCRUD;
import Data.Product;
import Data.ProductCRUD;

public class Kart extends AppCompatActivity {

    private Spinner spinner, numberPicker;
    private ProductCRUD crud;
    private ArrayList<Product> productos;
    private ArrayList<String> itemNumber;
    private Button bAdd;
    private OrderDetailCRUD orderDetailCRUD;
    private RecyclerView rvKart;
    private ArrayList<OrderDetail> ordersDetail;
    private ArrayAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String orderID = getIntent().getStringExtra("orderID");
        getSupportActionBar().setTitle("Order: " +orderID);
        Log.d("Order ID:",orderID);
        itemNumber = new ArrayList<>();
        itemNumber.add("1");itemNumber.add("2");itemNumber.add("3");itemNumber.add("4");
        itemNumber.add("5");itemNumber.add("6");itemNumber.add("7");itemNumber.add("8");
        itemNumber.add("9");itemNumber.add("10");
        productos = new ArrayList<>();

        crud = new ProductCRUD(this);

        productos = crud.getProducts();
        numberPicker = (Spinner) findViewById(R.id.numberPicker);
        //lvKart = (ListView) findViewById(R.id.lvKart);
        spinner = (Spinner)this.findViewById(R.id.spinner);
        bAdd = (Button)findViewById(R.id.bAdd);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,productos);

        // Step 3: Tell the spinner about our adapter
        spinner.setAdapter(spinnerArrayAdapter);

        ArrayAdapter numberPickerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,itemNumber);

        // Step 3: Tell the spinner about our adapter
        numberPicker.setAdapter(numberPickerArrayAdapter);

        orderDetailCRUD = new OrderDetailCRUD(this);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product aux;

                aux = (Product)spinner.getSelectedItem();
                String numberItems;
                numberItems = numberPicker.getSelectedItem().toString();
                int total;
                total = Integer.parseInt(numberItems) * Integer.parseInt(aux.getProductPrice());
                orderDetailCRUD.newOrderDetail(new OrderDetail(orderID, aux.getId(),Integer.parseInt(numberItems),total));
                /*
                ArrayList<OrderDetail> alOrderDetail;
                alOrderDetail = orderDetailCRUD.getOrderDetail(orderID);
                Log.d("LISTA PRODUCTO",alOrderDetail.toString());
                adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,alOrderDetail);
                lvKart.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                */
                rvKart = (RecyclerView) findViewById(R.id.rvKart);
                //String id = getIntent().getStringExtra("ID");
                orderDetailCRUD = new OrderDetailCRUD(getApplicationContext());
                ordersDetail = orderDetailCRUD.getOrderDetail(orderID);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                rvKart.setLayoutManager(layoutManager);
                RecycleViewCustomAdapterThree adapter = new RecycleViewCustomAdapterThree(getApplicationContext(),ordersDetail, new Adapters.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }
                }
                );

                rvKart.setAdapter(adapter);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
              //  startActivity(new Intent(Kart.this,List.class));
            }
        });
    }
}
