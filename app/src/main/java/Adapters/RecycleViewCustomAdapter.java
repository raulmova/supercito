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

import Views.EditForm;
import com.example.raul.shopkart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Data.Product;
import Data.ProductCRUD;

/**
 * Created by Raul on 11/10/2017.
 */


public class RecycleViewCustomAdapter extends RecyclerView.Adapter<RecycleViewCustomAdapter.PerfilViewHolder>{

    private Context mContext;
    private ArrayList<Product> products;
    private Adapters.RecyclerViewClickListener listener;
    private ProductCRUD crud;

    public RecycleViewCustomAdapter(Context c, ArrayList<Product> products, Adapters.RecyclerViewClickListener listener){
        mContext = c;
        this.listener = listener;
        this.products = products;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardtwo, viewGroup, false);
        return new RowViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(final PerfilViewHolder holder, final int position) {

        holder.tvName.setText(products.get(position).getId()+". "+products.get(position).getProductName());
        holder.tvPrice.setText(products.get(position).getProductPrice() + " $");
        Picasso.with(mContext).load(products.get(position).getPhotoURL()).into(holder.ivPhoto);
        Log.d("URL:",products.get(position).getPhotoURL());
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), holder.ivMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_product);
                crud = new ProductCRUD(view.getContext());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_edit_product:
                                //handle menu1 click

                                Intent intent = new Intent(view.getContext(), EditForm.class);
                                intent.putExtra("productName",products.get(position).getProductName());
                                intent.putExtra("productPrice",products.get(position).getProductPrice());
                                intent.putExtra("productId",products.get(position).getId());
                                intent.putExtra("productURL",products.get(position).getPhotoURL());
                                view.getContext().startActivity(intent);
                                break;

                            case R.id.action_delete_product:
                                //handle menu2 click
                                crud.deleteProduct(products.get(position));
                                products.remove(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }



    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPhoto, ivMenu;
        private TextView tvName;
        private TextView tvPrice;

        PerfilViewHolder(View vistaElemento){
            super(vistaElemento);
            ivPhoto = (ImageView) vistaElemento.findViewById(R.id.product_photo);
            ivMenu = (ImageView) vistaElemento.findViewById(R.id.overflow);
            tvName = (TextView) vistaElemento.findViewById(R.id.product_name);
            tvPrice = (TextView) vistaElemento.findViewById(R.id.product_price);
        }
    }
}
