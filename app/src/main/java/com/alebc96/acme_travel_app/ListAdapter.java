package com.alebc96.acme_travel_app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public ListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){ return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewtype){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    };

    public void setItems(List<ListElement> items) { mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage, imageFavorite;
        TextView fecha_salida, fecha_llegada, precio, city_name;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.image_card_view);
            fecha_salida = itemView.findViewById(R.id.fecha_salida);
            fecha_llegada = itemView.findViewById(R.id.fecha_llegada);
            precio = itemView.findViewById(R.id.precio_text);
            city_name = itemView.findViewById(R.id.city_name_text);
            imageFavorite = itemView.findViewById(R.id.image_favorite);
        }

        void bindData(final ListElement item){
            iconImage.setImageResource(item.getImgResource());
            city_name.setText(item.getCity_name());
            precio.setText("Precio: "+item.getPrecio());
            fecha_salida.setText("Fecha salida: " + item.getFecha_salida());
            fecha_llegada.setText("Fecha llegada: " +item.getFecha_llegada());
            boolean isFavorite = (item.getFavorito() != null) ? item.getFavorito().booleanValue() : false;
            if(isFavorite){
                imageFavorite.setImageResource(R.drawable.baseline_favorite_24_black);
            }else{
                imageFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
