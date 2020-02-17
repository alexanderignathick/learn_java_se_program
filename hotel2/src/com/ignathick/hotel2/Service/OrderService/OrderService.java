package com.ignathick.hotel2.Service.OrderService;
import com.ignathick.hotel2.IFileUtil;
import com.ignathick.hotel2.Store.OrderStore.IOrderStore;
import com.ignathick.hotel2.Store.OrderStore.OrderStore;
import com.ignathick.hotel2.Store.OrderStore.OrderStoreDatabaseImpl;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Order.OrderIFileUtil;
import com.ignathick.hotel2.model.Room.Status;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderStore orderStore;
    private final String ORDER_DATA_FILE = "ignathick_data_order_class.txt";
    private IFileUtil<Order> utilOrder;
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private static Logger logger = Logger.getLogger(OrderService.class);

    public OrderService(){
        init();
    }

    private void init(){
        this.orderStore = new OrderStoreDatabaseImpl();
        this.utilOrder = new OrderIFileUtil(ORDER_DATA_FILE);
    }

    @Override
    public void addOrder(Order order) {
        this.orderStore.save(order);
        order.getGuest().setRoom(order.getRoom());
        order.getRoom().setBusyFrom(order.getDateFrom());
        order.getRoom().setBusyTo(order.getDateTo());
        order.getRoom().setStatus(Status.BUSY);
        order.getRoom().setPrice(order.getOrderPrice());
    }

    @Override
    public Order getOrder(Long id) {
        return this.orderStore.get(id);
    }

    @Override
    public void deleteOrder(Order order) {

        this.orderStore.delete(order);
        this.orderStore.save(order);
        order.getGuest().setRoom(null);
        order.getRoom().setBusyFrom(null);
        order.getRoom().setBusyTo(null);
        order.getRoom().setStatus(Status.FREE);
    }

    @Override
    public void updateOrder(Order order) {

        Connection connection = database.getConnection();
        try {
            connection.setAutoCommit(false);
            this.orderStore.update(order);
        } catch (Exception ex) {
            logger.error("Error in SQL guest update transaction: " + ex);

        }
    }

    @Override
    public List<Order> getOrdersList() {
        return this.orderStore.getAll();
    }

    @Override
    public void saveToFile() {
        utilOrder.writeToFile(this.orderStore.getAll());
    }

    @Override
    public void setPath(String path) {
        this.utilOrder = new OrderIFileUtil(path);
    }
}
