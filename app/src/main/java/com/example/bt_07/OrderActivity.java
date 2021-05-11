package com.example.bt_07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    Button btUpdate,btDelete;
    EditText edtid,edtName,edtPrice,edtQuantity,edtDate;
    String id,name,price,quantity,date;
    SQLiteHelperOrder sqLiteHelperOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        edtid=findViewById(R.id.edtID);
        edtName=findViewById(R.id.edtName);
        edtPrice=findViewById(R.id.edtPrice);
        edtQuantity=findViewById(R.id.edtQuantity);
        edtDate=findViewById(R.id.edtDate);
        btUpdate=findViewById(R.id.btUpdate);
        btDelete=findViewById(R.id.btDelete);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        price=intent.getStringExtra("price");
        quantity=intent.getStringExtra("quantity");
        date=intent.getStringExtra("date");
        edtid.setText(id);
        edtName.setText(name);
        edtPrice.setText(price);
        edtQuantity.setText(quantity);
        edtDate.setText(date);
        edtid.setEnabled(false);
        sqLiteHelperOrder=new SQLiteHelperOrder(this) ;
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id= Integer.parseInt(edtid.getText().toString());
                    String namechange=edtName.getText().toString();
                    double pricechange= Double.parseDouble(edtPrice.getText().toString());
                    int quantitychange= Integer.parseInt(edtQuantity.getText().toString());
                    String datechange=edtDate.getText().toString();
                    Order order=new Order(id,namechange,pricechange,quantitychange,datechange);
                    sqLiteHelperOrder.update(order);
                    Intent intent=new Intent(OrderActivity.this,MainActivity.class);
                    startActivity(intent);
                }catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= Integer.parseInt(edtid.getText().toString());
                Intent intent=new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);
                sqLiteHelperOrder.delete(id);
            }
        });
    }
}