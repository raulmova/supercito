package Adapters;

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


public class RecycleViewCustomAdapterTwo extends RecyclerView.Adapter<RecycleViewCustomAdapterTwo.PerfilViewHolder>{

    private Context mContext;
    private ArrayList<Order> orders;
    private Adapters.RecyclerViewClickListener listener;
    private ProductCRUD crud;

    public RecycleViewCustomAdapterTwo(Context c, ArrayList<Order> orders, Adapters.RecyclerViewClickListener listener){
        mContext = c;
        this.listener = listener;
        this.orders = orders;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new RowViewHolderTwo(v, listener);
    }

    @Override
    public void onBindViewHolder(final PerfilViewHolder holder, final int position) {
        holder.tvId.setText("ID: " +orders.get(position).getId());
        holder.tvDate.setText("Created on: " +orders.get(position).getDate());
    }




    @Override
    public int getItemCount() {
        return orders.size();
    }


    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId;
        private TextView tvDate;

        PerfilViewHolder(View vistaElemento){
            super(vistaElemento);
            tvId = (TextView) vistaElemento.findViewById(R.id.orderID);
            tvDate = (TextView) vistaElemento.findViewById(R.id.orderDate);
        }
    }
}