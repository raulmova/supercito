package Adapters;

/**
 * Created by Raul on 15/10/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raul.shopkart.R;

import java.util.ArrayList;

import Data.Order;
import Data.OrderDetail;
import Data.ProductCRUD;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raul.shopkart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Data.Order;
import Data.Product;
import Data.ProductCRUD;
import Views.EditForm;

/**
 * Created by Raul on 14/10/2017.
 */


public class RecycleViewCustomAdapterThree extends RecyclerView.Adapter<RecycleViewCustomAdapterThree.PerfilViewHolder>{

    private Context mContext;
    private ArrayList<OrderDetail> ordersDetail;
    private Adapters.RecyclerViewClickListener listener;
    private ProductCRUD crud;

    public RecycleViewCustomAdapterThree(Context c, ArrayList<OrderDetail> ordersDetail, Adapters.RecyclerViewClickListener listener){
        mContext = c;
        this.listener = listener;
        this.ordersDetail = ordersDetail;
        crud = new ProductCRUD(c);
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardthree, viewGroup, false);
        return new RowViewHolderThree(v, listener);
    }

    @Override
    public void onBindViewHolder(final PerfilViewHolder holder, final int position) {
        holder.orderDetailID.setText("Product: " +crud.getProduct(ordersDetail.get(position).getId_product()).getProductName());
        holder.orderDetailsth.setText("Pieces: " +ordersDetail.get(position).getQuantity()  +". Total: " +ordersDetail.get(position).getPrice()+"$");
    }




    @Override
    public int getItemCount() {
        return ordersDetail.size();
    }


    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private TextView orderDetailID;
        private TextView orderDetailsth;

        PerfilViewHolder(View vistaElemento){
            super(vistaElemento);
            orderDetailID = (TextView) vistaElemento.findViewById(R.id.orderDetailID);
            orderDetailsth = (TextView) vistaElemento.findViewById(R.id.orderDetailsth);
        }
    }
}