package com.ignathick.hotel2.Store.GuestStore;

import com.ignathick.hotel2.Store.Dao;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Guest.Guest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestStoreDatabaseImpl implements Dao<Guest>, IGuestStore {

    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private Connection connection = database.getConnection();
    private List<Guest> guests = new ArrayList<Guest>();
    private static Logger logger = Logger.getLogger(GuestStoreDatabaseImpl.class);
    public final String SELECT_ALL_GUESTS = "SELECT * FROM guest";
    public final String SELECT_GUESTS_BY_ID = "SELECT * FROM guest WHERE guest_id = ?";
    public final String INSERT_NEW_GUEST = "INSERT INTO guest (firstname, lastname, dateofbirth, passport) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_GUEST = "UPDATE hoteldb.guest SET firstname = ?, lastname = ?, dateofbirth = ?, passport = ? WHERE guest_id = ?";
    public static final String DELETE_GUEST = "DELETE FROM guest WHERE (guest_id = ?)";

    @Override
    public Guest get(Long id) {
        Guest guest = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GUESTS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long guestID = resultSet.getLong("guest_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                java.util.Date dateOfBirth = new Date(resultSet.getDate("dateofbirth").getTime());
                String passport = resultSet.getString("passport");
                Long roomID = resultSet.getLong("room_id");
                guest = new Guest(guestID, firstName, lastName, dateOfBirth, passport);
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

        return guest;
    }

    @Override
    public List<Guest> getAll() {

        List<Guest> guestList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GUESTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long guestID = resultSet.getLong("guest_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                java.util.Date dateOfBirth = new Date(resultSet.getDate("dateofbirth").getTime());
                String passport = resultSet.getString("passport");
                Long roomID = resultSet.getLong("room_id");
                Guest guest = new Guest(guestID, firstName, lastName, dateOfBirth, passport);
                guestList.add(guest);
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

        return guestList;

    }

    @Override
    public void save(Guest guest) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_GUEST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(guest.getDateOfBirth().getTime()));
            preparedStatement.setString(4, guest.getPassport());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            guest.setId((long)generatedKey);

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
    public void update(Guest guest) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GUEST);
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(guest.getDateOfBirth().getTime()));
            preparedStatement.setString(4, guest.getPassport());
            //preparedStatement.setInt(5, (int) (long) guest.getRoom().getId());
            preparedStatement.setLong(5, guest.getId());
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
    public void delete(Guest guest) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GUEST);
            preparedStatement.setLong(1, guest.getId());
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
        this.guests.clear();
    }
}
