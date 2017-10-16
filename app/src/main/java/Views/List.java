package Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.raul.shopkart.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Adapters.RecycleViewCustomAdapter;
import Data.Order;
import Data.OrderCRUD;
import Data.Product;
import Data.ProductCRUD;

public class List extends AppCompatActivity {

    private RecyclerView rvList;
    private ProductCRUD crud;
    private ArrayList<Product> products;
    private LinearLayoutManager layoutManager;
    private OrderCRUD orderCrud;
    private String orderID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //deleteDatabase("productos.db");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List.this,ProductForm.class);
                startActivity(intent);
            }
        });

        products = new ArrayList<Product>();

        crud = new ProductCRUD(this);
        products = crud.getProducts();

        rvList =  (RecyclerView) findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        rvList.setLayoutManager(layoutManager);

        RecycleViewCustomAdapter adapter = new RecycleViewCustomAdapter(getApplicationContext(),products, new Adapters.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });

        rvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_history){
            startActivity(new Intent(List.this,History.class));
        }

        if (id == R.id.action_kart) {
            //////////////////////////////////
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Order ID");

            // Set up the input
                        final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_NUMBER);
                        builder.setView(input);

            // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(input.getText().toString().isEmpty()){
                                    /*
                                    AlertDialog.Builder builderTwo = new AlertDialog.Builder(getApplicationContext());
                                    builderTwo.setMessage("ID can´t be empty")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //do things
                                                }
                                            });
                                    AlertDialog alert = builderTwo.create();
                                    alert.show();
                                    */
                                    showToast();
                                    //Snackbar.make(this., "Some field is empty", Snackbar.LENGTH_SHORT).show();
                                    //Toast.makeText(getApplicationContext(),"ID can´t be empty", Toast.LENGTH_SHORT);
                                }
                                else{
                                    orderID = input.getText().toString();
                                    orderCrud = new OrderCRUD(getApplicationContext());
                                    Date date = new Date();
                                    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    orderCrud.newOrder(new Order(orderID,fecha));
                                    Intent intent = new Intent(List.this,Kart.class);
                                    Log.d("QUERY: ",orderCrud.getOrder(orderID).toString());
                                    Log.d("Order ID",orderCrud.getOrder(orderID).getId());
                                    intent.putExtra("orderID",orderID);
                                    startActivity(intent);
                                }

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

            builder.show();

            //////////////////////////////
            //orderCrud.newOrder(new Order("",date.toString()));
            //intent.putExtra("orderID",);
            //intent.putExtra("orderID",orderCrud.getOrder());
            //startActivity(intent);
            return true;
        }



        if (id == R.id.action_refresh) {
            products = new ArrayList<Product>();

            crud = new ProductCRUD(this);
            products = crud.getProducts();

            rvList =  (RecyclerView) findViewById(R.id.rvList);
            rvList.setHasFixedSize(true);
            layoutManager = new GridLayoutManager(this,2);
            rvList.setLayoutManager(layoutManager);

            RecycleViewCustomAdapter adapter = new RecycleViewCustomAdapter(getApplicationContext(),products, new Adapters.RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position) {

                }
            });

            rvList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast(){
        Toast.makeText(this, "ID can´t be empty", Toast.LENGTH_SHORT).show();
    }
}
