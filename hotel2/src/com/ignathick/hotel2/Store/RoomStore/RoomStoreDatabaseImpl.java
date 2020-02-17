package com.ignathick.hotel2.Store.RoomStore;

import com.ignathick.hotel2.Store.Dao;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomStoreDatabaseImpl implements Dao<Room>, IRoomStore {

    private List<Room> rooms = new ArrayList<>();

    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private Connection connection = database.getConnection();

    private static Logger logger = Logger.getLogger(RoomStoreDatabaseImpl.class);
    public final String SELECT_ALL_ROOMS = "SELECT * FROM room";
    public final String SELECT_ROOM_BY_ID = "SELECT * FROM room WHERE room_id = ?";
    public final String INSERT_NEW_ROOM = "INSERT INTO room (number, capasity, price, star, status, busyfrom, busyto) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //INSERT INTO `hoteldb`.`room` (`room_id`, `number`, `capasity`, `price`, `star`, `status`, `busyfrom`, `busyto`) VALUES ('6', '6', '2', '100', 'FOUR', 'free', '2020-02-01', '2020-02-03');
    public static final String UPDATE_ROOM = "UPDATE room SET number = ?, capasity = ?, price = ?, star = ?, status = ?, busyfrom = ?, busyto = ? WHERE room_id = ?";
    //UPDATE `hoteldb`.`room` SET `number` = '7', `capasity` = '3', `price` = '101.00', `star` = 'FIVE', `status` = 'busy', `busyfrom` = '2020-02-16', `busyto` = '2020-02-20' WHERE (`room_id` = '6');
    public static final String DELETE_ROOM = "DELETE FROM room WHERE (room_id = ?)";

    @Override
    public Room get(Long id) {
        Room room = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long room_id = resultSet.getLong("room_id");
                Integer number = resultSet.getInt("number");
                Integer capasity = resultSet.getInt("capasity");
                Double price = (Double)resultSet.getBigDecimal("price").doubleValue();
                String star = resultSet.getString("star");
                String status = resultSet.getString("status");
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
                room = new Room(room_id, number, capasity, price, star, status, busyfrom, busyto);
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

        return room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
         try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long room_id = resultSet.getLong("room_id");
                Integer number = resultSet.getInt("number");
                Integer capasity = resultSet.getInt("capasity");
                Double price = (Double)resultSet.getBigDecimal("price").doubleValue();
                String star = resultSet.getString("star");
                String status = resultSet.getString("status");
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
                Room room = new Room(room_id, number, capasity, price, star, status, busyfrom, busyto);
                roomList.add(room);
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
        return  roomList;
    }

    @Override
    public void save(Room room) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ROOM, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, room.getNumber());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setDouble(3, room.getPrice());
            preparedStatement.setString(4, room.getStar().toString());
            preparedStatement.setString(5, room.getStatus().toString());
            if (room.getBusyFrom() != null) {
                preparedStatement.setDate(6, new java.sql.Date(room.getBusyFrom().getTime()));
            } else {
                preparedStatement.setDate(6, null);
            }
            if (room.getBusyTo() != null) {
                preparedStatement.setDate(7, new java.sql.Date(room.getBusyTo().getTime()));
            } else {
                preparedStatement.setDate(7,null);
            }
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            room.setId((long)generatedKey);

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
    public void update(Room room) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROOM);
            //UPDATE `hoteldb`.`room` SET `number` = '7', `capasity` = '3', `price` = '101.00', `star` = 'FIVE', `status` = 'busy', `busyfrom` = '2020-02-16', `busyto` = '2020-02-20' WHERE (`room_id` = '6');
            preparedStatement.setInt(1, room.getNumber());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setDouble(3, room.getPrice());
            preparedStatement.setString(4, room.getStar().toString());
            preparedStatement.setString(5, room.getStatus().toString());
            if (room.getBusyFrom() != null) {
                preparedStatement.setDate(6, new java.sql.Date(room.getBusyFrom().getTime()));
            } else {
                preparedStatement.setDate(6,null);
            }
            if (room.getBusyTo() != null) {
                preparedStatement.setDate(7, new java.sql.Date(room.getBusyTo().getTime()));
            } else {
                preparedStatement.setDate(7, null);
            }
            preparedStatement.setLong(8, room.getId());
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
    public void delete(Room room) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROOM);
            preparedStatement.setLong(1, room.getId());
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
        this.rooms.clear();
    }

    @Override
    public void updateRoomStatus(Long id, Status status) {

        Room room = get(id);
        room.setStatus(status);

    }
}
