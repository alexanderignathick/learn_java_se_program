package com.ignathick.hotel2.Service.OrderService;

import com.ignathick.hotel2.model.Order.Order;

import java.util.List;

public interface IOrderService {

    //CRUD
    public void addOrder(Order order);
    public Order getOrder(Long id);
    public void deleteOrder(Order order);
    public void updateOrder(Order order);

    public List<Order> getOrdersList();

    public void saveToFile();
    public void setPath(String path);

}
