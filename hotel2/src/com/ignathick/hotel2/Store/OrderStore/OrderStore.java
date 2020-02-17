package com.ignathick.hotel2.Store.OrderStore;

import com.ignathick.hotel2.model.Order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStore implements IOrderStore{
    private List<Order> orders = new ArrayList<>();

    @Override
    public Order get(Long id) {
        for (Order order:
             orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public void save(Order order) {
        this.orders.add(order);
    }

    @Override
    public void update(Order order) {
        this.orders.set(this.orders.indexOf(order), order);
    }

    @Override
    public void delete(Order order) {
        this.orders.remove(order);
    }

    @Override
    public void deleteAll() {
        this.orders.clear();
    }
}
