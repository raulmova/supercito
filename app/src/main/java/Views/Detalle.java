package Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.raul.shopkart.R;

import java.util.ArrayList;

import Adapters.RecycleViewCustomAdapterThree;
import Adapters.RecycleViewCustomAdapterTwo;
import Data.Order;
import Data.OrderCRUD;
import Data.OrderDetail;
import Data.OrderDetailCRUD;

public class Detalle extends AppCompatActivity {

    private ArrayAdapter adapter;
    private RecyclerView rvDetalle;
    private OrderDetailCRUD orderDetailCRUD;
    private LinearLayoutManager layoutManager;
    private ArrayList<OrderDetail> ordersDetail;
    private TextView total, productNumber;
    private int tot, productNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        rvDetalle = (RecyclerView) findViewById(R.id.rvDetalle);
        total = (TextView) findViewById(R.id.total);
        productNumber = (TextView) findViewById(R.id.productNumber);
        String id = getIntent().getStringExtra("ID");
        orderDetailCRUD = new OrderDetailCRUD(getApplicationContext());
        ordersDetail = orderDetailCRUD.getOrderDetail(id);

        for(int i = 0; i< ordersDetail.size();i++){
            tot = tot + ordersDetail.get(i).getPrice();
            productNum = productNum + ordersDetail.get(i).getQuantity();
        }
        total.setText("Total: " +tot + " $");
        productNumber.setText("Products: " + productNum);
        layoutManager = new LinearLayoutManager(this);
        rvDetalle.setLayoutManager(layoutManager);
        RecycleViewCustomAdapterThree adapter = new RecycleViewCustomAdapterThree(getApplicationContext(),ordersDetail, new Adapters.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        }
        );

        rvDetalle.setAdapter(adapter);
    }
}
