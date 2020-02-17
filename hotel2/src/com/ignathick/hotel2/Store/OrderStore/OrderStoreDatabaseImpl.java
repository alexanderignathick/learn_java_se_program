package com.ignathick.hotel2.Store.OrderStore;

import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Order.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStoreDatabaseImpl implements IOrderStore{
    private List<Order> orders = new ArrayList<>();
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private Connection connection = database.getConnection();
    private static Logger logger = Logger.getLogger(OrderStoreDatabaseImpl.class);
    public final String SELECT_ALL_ORDERS = "SELECT * FROM order";
    public final String SELECT_ORDER_BY_ID = "SELECT * FROM order WHERE guest_id = ?";
    public final String INSERT_NEW_ORDER = "INSERT INTO order (guest_id, room_id, datefrom, dateto, orderprice) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_ORDER = "UPDATE order SET guest_id = ?, room_id = ?, datefrom = ?, dateto = ?, orderprice = ? WHERE order_id = ?";
    public static final String DELETE_ORDER = "DELETE FROM order WHERE (order_id = ?)";

    @Override
    public Order get(Long id) {
        Order order = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long orderID = resultSet.getLong("order_id");
                Long guestID = resultSet.getLong("guest_id");
                Long roomID = resultSet.getLong("room_id");
                java.sql.Date sqlBusyFrom = resultSet.getDate("busyfrom");
                java.util.Date busyfrom = null;
                if(sqlBusyFrom != null) {
                    busyfrom = new Date(sqlBusyFrom.getTime());
                }
                java.util.Date busyto = null;
                java.sql.Date sqlBusyTo = resultSet.getDate("busyTo");
                if(sqlBusyTo != null) {
                    busyto = new Date(sqlBusyTo.getTime());
                }
                Double orderprice = (Double)resultSet.getBigDecimal("orderprice").doubleValue();
                //order = new Order(orderID, guestID, roomID,  busyfrom, busyto, orderprice);
            }

            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException ex) {
            logger.error("SQL error. Database " + database.getDBNAME() + " error:" + ex);
        } catch (Exception ex) {
            logger.error("Connection error: " + ex);
        }

        return order;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public void save(Order order) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getGuest().getId());
            preparedStatement.setLong(2, order.getRoom().getId());
            if (order.getDateFrom() != null) {
                preparedStatement.setDate(3, new java.sql.Date(order.getDateFrom().getTime()));
            } else {
                preparedStatement.setDate(3, null);
            }
            if (order.getDateTo() != null) {
                preparedStatement.setDate(4, new java.sql.Date(order.getDateTo().getTime()));
            } else {
                preparedStatement.setDate(4,null);
            }
            preparedStatement.setDouble(5, order.getOrderPrice());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            order.setId((long)generatedKey);

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException ex) {
            logger.error("SQL error. Database " + database.getDBNAME() + " error:" + ex);
        } catch (Exception ex) {
            logger.error("Connection error: " + ex);
        }
    }

    @Override
    public void update(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setLong(1, order.getGuest().getId());
            preparedStatement.setLong(2, order.getRoom().getId());
            if (order.getDateFrom() != null) {
                preparedStatement.setDate(3, new java.sql.Date(order.getDateFrom().getTime()));
            } else {
                preparedStatement.setDate(3,null);
            }
            if (order.getDateTo() != null) {
                preparedStatement.setDate(4, new java.sql.Date(order.getDateTo().getTime()));
            } else {
                preparedStatement.setDate(4, null);
            }
            preparedStatement.setDouble(5, order.getOrderPrice());
            preparedStatement.setLong(6, order.getId());
            int updateCount = preparedStatement.executeUpdate();
            logger.info("update " + updateCount + " row(s) in transaction guest update");

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (Exception ex) {
            logger.error("Error in SQL guest update transaction: " + ex);
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                logger.error("Error in rolback guest update transaction:" + sqlEx);
            }
        } finally {

            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.error("Error in trurn on autoCommit option in guest update:" + ex);
            }
        }
    }

    @Override
    public void delete(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.execute();

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException ex) {
            logger.error("SQL error. Database " + database.getDBNAME() + "delete error:" + ex);
        } catch (Exception ex) {
            logger.error("Connection error: " + ex);
        }
    }

    @Override
    public void deleteAll() {
        this.orders.clear();
    }
}
