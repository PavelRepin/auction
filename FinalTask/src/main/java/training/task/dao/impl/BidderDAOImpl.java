package training.task.dao.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.dao.api.BidderDAO;
import training.task.dao.api.exception.ConnectionPoolException;
import training.task.dao.api.exception.DAOException;
import training.task.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidderDAOImpl implements BidderDAO {
    private static Logger logger = Logger.getLogger(BidderDAOImpl.class);

    private static final String INSERT_BIDDER = "INSERT INTO `model_for_auction`.`bidder` (`login`, `password`, `email`, `age`, `local`) VALUES (?, ?, ?, ?, ?);";
    private static final String GET_BIDDER_BY_LOGIN = "SELECT * FROM model_for_auction.bidder WHERE `login`=?";
    private static final String UPDATE_LOCAL = "UPDATE `model_for_auction`.`bidder` SET `local`=? WHERE `login`=?";
    private static final String EDIT_USER = "UPDATE `model_for_auction`.`bidder` SET `name`=?,`last_name`=?,`money_usd`=?,`age`=?,`document_number`=?," +
            "`country`=?,`full_address`=?,`login`=?,`password`=?,`email`=?,`local`=? WHERE `id_bidder`=?";
    private static final String GET_BIDDER_ID = "SELECT `id_bidder` FROM model_for_auction.bidder WHERE `login`=?";
    private static final String GET_BIDDER_PASSWORD = "SELECT `password` FROM model_for_auction.bidder WHERE `id_bidder`=?";
    private static final String GET_ALL_BIDDERS_COUNT = "SELECT COUNT(*) FROM model_for_auction.bidder";
    private static final String GET_ALL_BIDDERS_PAGE = "SELECT * FROM model_for_auction.bidder LIMIT ?,?";
    private static final String UPDATE_BLOCK_STATE = "UPDATE model_for_auction.bidder SET bidder.is_blocked=? WHERE bidder.id_bidder=?";
    private static final String UPDATE_ADMIN_STATE = "UPDATE model_for_auction.bidder SET bidder.is_admin=? WHERE bidder.id_bidder=?";
    private static final String GET_LOGIN_COUNT = "SELECT COUNT(*) FROM model_for_auction.bidder WHERE bidder.login=?";
    private static final String GET_BIDDER_BY_ID = "SELECT * FROM model_for_auction.bidder WHERE bidder.id_bidder=?";

    @Override
    public void insertBidder(Bidder bidder) throws DAOException {
        logger.info("Trying to set signed up attributes into the db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(INSERT_BIDDER)) {

                prep.setString(1, bidder.getLogin());
                prep.setString(2, bidder.getPassword());
                prep.setString(3, bidder.getEmail());
                prep.setInt(4, bidder.getAge());
                prep.setString(5, bidder.getLocal());
                prep.executeUpdate();
            }
            try (PreparedStatement prep = connection.prepareStatement(GET_BIDDER_ID)) {
                prep.setString(1, bidder.getLogin());
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        bidder.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of signing up.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully added signed up info in db.");
    }

    @Override
    public void updateBidder(Bidder bidder) throws DAOException {
        logger.info("Trying to edit db info in a bidder table.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(EDIT_USER)) {
                prep.setString(1, bidder.getName());
                prep.setString(2, bidder.getLastName());
                prep.setDouble(3, bidder.getMoney());
                prep.setInt(4, bidder.getAge());
                prep.setString(5, bidder.getDocumentNumber());
                prep.setString(6, bidder.getCountry());
                prep.setString(7, bidder.getFullAddress());
                prep.setString(8, bidder.getLogin());
                prep.setString(9, bidder.getPassword());
                prep.setString(10, bidder.getEmail());
                prep.setString(11, bidder.getLocal());
                prep.setInt(12, bidder.getId());
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of editing bidder.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully edited bidder's info in db.");
    }

    @Override
    public Bidder getBidderByLogin(String login) throws DAOException {
        logger.info("Trying to get bidder from database by signing in.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Bidder bidder = new Bidder();
        bidder.setLogin(login);

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_BIDDER_BY_LOGIN)) {
                prep.setString(1, bidder.getLogin());

                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        setBidderParameters(bidder, rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of signing in.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully added parameters from db in a bidder object.");

        return bidder;
    }

    @Override
    public String getBidderPasswordById(int id) throws DAOException {
        logger.info("Trying to get bidder's password from database with bidder's id.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        String password = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_BIDDER_PASSWORD)) {
                prep.setInt(1, id);

                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        password = rs.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of getting password.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully added parameters from db in a bidder object.");

        return password;
    }

    @Override
    public void updateIsAdmin(int idBidder, boolean isAdmin) throws DAOException {
        logger.info("Trying to update admin state for bidder in db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(UPDATE_ADMIN_STATE)) {
                prep.setBoolean(1, isAdmin);
                prep.setInt(2, idBidder);
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully updated admin state in db.");
    }

    @Override
    public void updateIsBlocked(int idBidder, boolean isBlocked) throws DAOException {
        logger.info("Trying to update block state for bidder in db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(UPDATE_BLOCK_STATE)) {
                prep.setBoolean(1, isBlocked);
                prep.setInt(2, idBidder);
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully updated block state in db.");
    }

    @Override
    public List<Bidder> getAllBiddersPage(int start, int quantity) throws DAOException {
        logger.info("Trying to get page of all bidders from db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Bidder> bidders = new ArrayList<>();

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_BIDDERS_PAGE)) {
                prep.setInt(1, start);
                prep.setInt(2, quantity);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Bidder bidder = new Bidder();
                        setBidderParameters(bidder, rs);
                        bidders.add(bidder);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully got page of all bidders from db.");
        return bidders;
    }

    @Override
    public Bidder getBidderById(int id) throws DAOException {
        logger.info("Trying to get bidder from database by id.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Bidder bidder = new Bidder();
        bidder.setId(id);

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_BIDDER_BY_ID)) {
                prep.setInt(1, bidder.getId());

                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        setBidderParameters(bidder, rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of signing in.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully got bidder by id.");

        return bidder;
    }

    private void setBidderParameters(Bidder bidder, ResultSet rs) throws SQLException {
        bidder.setId(rs.getInt(1));
        bidder.setName(rs.getString(2));
        bidder.setLastName(rs.getString(3));
        bidder.setMoney(rs.getLong(4));
        bidder.setAge(rs.getInt(5));
        bidder.setDocumentNumber(rs.getString(6));
        bidder.setCountry(rs.getString(7));
        bidder.setFullAddress(rs.getString(8));
        bidder.setLogin(rs.getString(9));
        bidder.setPassword(rs.getString(10));
        bidder.setEmail(rs.getString(11));
        bidder.setLocal(rs.getString(12));
        bidder.setAdmin(rs.getBoolean(13));
        bidder.setBlocked(rs.getBoolean(14));
    }

    @Override
    public int getAllBiddersCount() throws DAOException {
        logger.info("Trying to get quantity of all bidders.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int quantity;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_BIDDERS_COUNT)) {
                try (ResultSet rs = prep.executeQuery()) {
                    rs.next();
                    quantity = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully got all bidders count.");
        return quantity;
    }

    @Override
    public int checkLogin(String login) throws DAOException {
        logger.info("Checking if there is such a login.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int isValid;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_LOGIN_COUNT)) {
                prep.setString(1, login);
                try (ResultSet rs = prep.executeQuery()) {
                    rs.next();
                    isValid = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Returning isValid and check in service.");
        return isValid;
    }


    @Override
    public void updateLocal(String login, String local) throws DAOException {
        logger.info("DaoImpl updating local.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(UPDATE_LOCAL)) {
                prep.setString(1, local);
                prep.setString(2, login);
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of switching local.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Succeed to switch local in db.");
    }


}