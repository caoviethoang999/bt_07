package com.example.bt_07;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    List<Order> orderList;
    public OrderAdapter(){
        orderList=new ArrayList<>();
    }
    public void getAllOrder(List<Order> list){
        this.orderList=list;
    }
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item_card,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order=orderList.get(position);
        holder.txtId.setText(String.valueOf(order.getId()));
        holder.txtName.setText(order.getName());
        holder.txtName.setText(order.getName());
        holder.txtPrice.setText(String.valueOf(order.getPrice()));
        holder.txtQuantity.setText(String.valueOf(order.getQuantity()));
        holder.txtOrderDate.setText(order.getDateOrder());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),OrderActivity.class);
                intent.putExtra("id",String.valueOf(order.getId()));
                intent.putExtra("name",order.getName());
                intent.putExtra("price",String.valueOf(order.getPrice()));
                intent.putExtra("quantity",String.valueOf(order.getQuantity()));
                intent.putExtra("date",order.getDateOrder());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(orderList!=null){
            return orderList.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtId,txtName,txtPrice,txtQuantity,txtOrderDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.txtId);
            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtQuantity=itemView.findViewById(R.id.txtQuantity);
            txtOrderDate=itemView.findViewById(R.id.txtDate);
        }
    }
}
