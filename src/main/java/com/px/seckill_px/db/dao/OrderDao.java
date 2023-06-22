package com.px.seckill_px.db.dao;


import com.px.seckill_px.db.po.Order;

public interface OrderDao {
    void insertOrder(Order order);
    Order queryOrder(String OrderNo);

    void updateOrder(Order order);

}


