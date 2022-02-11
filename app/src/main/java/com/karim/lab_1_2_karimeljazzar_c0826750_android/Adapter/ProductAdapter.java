package com.karim.lab_1_2_karimeljazzar_c0826750_android.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.karim.lab_1_2_karimeljazzar_c0826750_android.Models.ProductModel;
import com.karim.lab_1_2_karimeljazzar_c0826750_android.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    ArrayList<ProductModel> data=new ArrayList<>();
    LayoutInflater inflater;//
    Activity activity;

    //constructor
    public ProductAdapter(Context context, ArrayList<ProductModel>data, Activity a)
    {
        this.data=data;
        inflater=LayoutInflater.from(context);
        activity = a;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            view=inflater.inflate(R.layout.product_layout,null);
            holder=new ViewHolder();
            holder.name =view.findViewById(R.id.nameP);
            holder.id =view.findViewById(R.id.idP);
            holder.price = view.findViewById(R.id.priceP);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(i).getName());
        holder.id.setText(String.valueOf(data.get(i).getId()));
        holder.price.setText(String.valueOf(data.get(i).getPrice()));

        return view;
    }
    static class ViewHolder{
        private TextView id;
        private TextView name;
        private TextView price;
    }
}
