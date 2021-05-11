package com.example.bt_07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btAdd;
    EditText edtName,edtPrice,edtQuantity,edtDate;
    RecyclerView recyclerView;
    SQLiteHelperOrder sqLiteHelperOrder;
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdd=findViewById(R.id.btAdd);
        edtName=findViewById(R.id.edtName);
        edtPrice=findViewById(R.id.edtPrice);
        edtQuantity=findViewById(R.id.edtQuantity);
        edtDate=findViewById(R.id.edtDate);
        recyclerView=findViewById(R.id.recylerview);
        sqLiteHelperOrder=new SQLiteHelperOrder(this);
        orderAdapter=new OrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        edtDate.setText(date);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = edtName.getText().toString();
                    double price = Double.parseDouble(edtPrice.getText().toString());
                    int quantity = Integer.parseInt(edtQuantity.getText().toString());
                    Order order=new Order(name,price,quantity,date);
                    sqLiteHelperOrder.addOrder(order);
                }catch (NumberFormatException e){
                    System.out.println(e);
                }
            }
        });
    }
    public void loadData(){
        List<Order> list=sqLiteHelperOrder.getAllOrder();
        orderAdapter.getAllOrder(list);
        recyclerView.setAdapter(orderAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.mSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Order> list=sqLiteHelperOrder.getOrderbyName(newText);
                orderAdapter.getAllOrder(list);
                recyclerView.setAdapter(orderAdapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}