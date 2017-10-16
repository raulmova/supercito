package Adapters;

import android.view.View;

/**
 * Created by Raul on 14/10/2017.
 */


public class RowViewHolderTwo extends RecycleViewCustomAdapterTwo.PerfilViewHolder implements View.OnClickListener {
    private RecyclerViewClickListener listener;

    public RowViewHolderTwo(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition());
    }
}