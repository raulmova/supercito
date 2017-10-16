package Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.raul.shopkart.R;

import java.util.ArrayList;

import Adapters.RecycleViewCustomAdapterTwo;
import Data.Order;
import Data.OrderCRUD;
import Data.OrderDetail;

import static com.example.raul.shopkart.R.id.rvList;

public class History extends AppCompatActivity {

    private ArrayAdapter adapter;
    private RecyclerView rvHistory;
    private OrderCRUD orderCrud;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rvHistory = (RecyclerView) findViewById(R.id.rvHistory);
        orderCrud = new OrderCRUD(getApplicationContext());

        final ArrayList<Order> orders;
        orders = orderCrud.getOrders();
        layoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layoutManager);
        RecycleViewCustomAdapterTwo adapter = new RecycleViewCustomAdapterTwo(getApplicationContext(),orders, new Adapters.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Order item = orders.get(position);
                Intent intent = new Intent(History.this, Detalle.class);
                intent.putExtra("ID",item.getId());
                startActivity(intent);
            }
        }
        );
        rvHistory.setAdapter(adapter);

        /*
        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,orders);
        rvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order item = (Order)adapter.getItem(i);
                Intent intent = new Intent(History.this, Detalle.class);
                intent.putExtra("ID",item.getId());
                startActivity(intent);
            }
        });
        */
    }
}
