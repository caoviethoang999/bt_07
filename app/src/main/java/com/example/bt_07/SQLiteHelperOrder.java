package com.example.bt_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperOrder extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="OrderDB.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHelperOrder(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE OrderProduct(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price REALE," +
                "quantity INTEGER,"+
                "date DATE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    public void addOrder(Order order){
        String sql="INSERT INTO OrderProduct(name,price,quantity,date) VALUES(?,?,?,?)";
        String[] args={order.getName(),Double.toString(order.getPrice()),Integer.toString(order.getQuantity()),order.getDateOrder()};
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    public List<Order> getAllOrder(){
        List<Order> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor resultSet=statement.query("OrderProduct",null,null,null,null,null,null);
        while((resultSet!=null && resultSet.moveToNext())){
            int id=resultSet.getInt(0);
            String name=resultSet.getString(1);
            double price=resultSet.getDouble(2);
            int quantity=resultSet.getInt(3);
            String date= resultSet.getString(4);
            list.add(new Order(id,name,price,quantity,date));
        }
        return list;
    }
    public int update(Order order){
        ContentValues v=new ContentValues();
        v.put("name",order.getName());
        v.put("price",order.getPrice());
        v.put("quantity",order.getQuantity());
        v.put("date",order.getDateOrder());
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(order.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("OrderProduct",v,whereClause,whereArgs);
    }

    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("OrderProduct",whereClause,whereArgs);
    }
    public List<Order> getOrderbyName(String name) {
        List<Order> list=new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("OrderProduct", null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nameorder = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            int quantity=cursor.getInt(cursor.getColumnIndex("quantity"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            list.add(new Order(id, nameorder, price,quantity,date));
        }
        cursor.close();
        return list;
    }

}
