package training.task.dao.impl;

import org.apache.log4j.Logger;
import training.task.bean.Lot;
import training.task.dao.api.LotDAO;
import training.task.dao.api.exception.ConnectionPoolException;
import training.task.dao.api.exception.DAOException;
import training.task.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotDAOImpl implements LotDAO {
    private static Logger logger = Logger.getLogger(LotDAOImpl.class);

    private static final String GET_LOTS_BIDDER_BARGAINING = "select lot.id_lot, lot.name, lot.type, lot.author, lot.year, " +
            "lot.description, lot.start_price, lot.photos, lot.owner, lot.seller, lot.created_at, lot.current_price, lot.last_bidder, " +
            "lot.close_at from model_for_auction.lot inner join model_for_auction.bid on lot.id_lot=bid.id_lot " +
            "where lot.owner=0 and not lot.seller=0 and bid.id_bidder=?";
    private static final String GET_LOTS_BIDDER_SUGGESTED_SELLING_OWN = "select * from model_for_auction.lot where lot.owner=? or lot.seller=?";
    private static final String GET_LOTS_PAGE_BY_CATEGORY = "select * from model_for_auction.lot where lot.owner=0 and not lot.seller=0 and lot.type=? LIMIT ?,?";
    private static final String GET_ALL_SELLING_LOTS_PAGE = "select * from model_for_auction.lot where lot.owner=0 and not lot.seller=0 LIMIT ?,?";
    private static final String GET_ALL_LOTS_COUNT = "select count(*) from model_for_auction.lot WHERE lot.owner=0 and not lot.seller=0";
    private static final String GET_LOTS_COUNT_BY_CATEGORY = "select count(*) from model_for_auction.lot where lot.owner=0 and not lot.seller=0 and lot.type=?";
    private static final String INSERT_LOT = "INSERT INTO model_for_auction.lot (name, type, author, year, description, start_price, " +
            "photos, owner, seller, close_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LOT_ID = "SELECT lot.id_lot FROM model_for_auction.lot ORDER BY lot.id_lot DESC LIMIT 1";
    private static final String GET_LOT_BY_ID = "SELECT * FROM model_for_auction.lot WHERE lot.id_lot=?";
    private static final String UPDATE_LOT_STATE = "UPDATE model_for_auction.lot SET lot.owner=?, lot.seller=? WHERE lot.id_lot=?";
    private static final String GET_ALL_SUGGESTIONS_COUNT = "SELECT COUNT(*) from model_for_auction.lot WHERE lot.seller=0 and not lot.owner=0";
    private static final String GET_ALL_SUGGESTED_LOTS_PAGE = "SELECT * FROM model_for_auction.lot WHERE lot.seller=0 and not lot.owner=0 LIMIT ?,?";
    private static final String UPDATE_LOT = "UPDATE model_for_auction.lot SET lot.owner=?, lot.seller=?, lot.current_price=?, " +
            "lot.created_at=?, lot.close_at=?, lot.last_bidder=? WHERE lot.id_lot=?";

    @Override
    public List<Lot> getLotsPageByCategory(String category, int start, int quantity) throws DAOException {
        logger.info("Trying to get page of lots by category from db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Lot> lots = new ArrayList<>();

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_LOTS_PAGE_BY_CATEGORY)) {
                prep.setString(1, category);
                prep.setInt(2, start);
                prep.setInt(3, quantity);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Lot lot = new Lot();
                        setLotParameters(lot, rs);
                        lots.add(lot);
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
        logger.info("Successfully got page of lots by category.");
        return lots;
    }

    @Override
    public List<Lot> getAllLotsPage(int start, int quantity) throws DAOException {
        logger.info("Trying to get page of all lots from db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Lot> lots = new ArrayList<>();

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_SELLING_LOTS_PAGE)) {
                prep.setInt(1, start);
                prep.setInt(2, quantity);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Lot lot = new Lot();
                        setLotParameters(lot, rs);
                        lots.add(lot);
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
        logger.info("Successfully got page of all lots.");
        return lots;
    }

    @Override
    public Lot getLotById(int id) throws DAOException {
        logger.info("Trying to get lot from db by id.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        Lot lot = new Lot();
        lot.setId(id);
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_LOT_BY_ID)) {
                prep.setInt(1, lot.getId());
                try (ResultSet rs = prep.executeQuery()) {
                    rs.next();
                    setLotParameters(lot, rs);

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
        logger.info("Successfully got lot from db by its id.");

        return lot;
    }

    @Override
    public List<Lot> getLotsBidderSuggestedSellingOwnBargaining(int idBidder) throws DAOException {
        logger.info("Trying to get list of lots that bidder suggested, is selling or own.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Lot> lots = new ArrayList<>();

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_LOTS_BIDDER_SUGGESTED_SELLING_OWN)) {
                prep.setInt(1, idBidder);
                prep.setInt(2, idBidder);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Lot lot = new Lot();
                        setLotParameters(lot, rs);
                        lots.add(lot);
                    }
                }
            }
            try (PreparedStatement prep = connection.prepareStatement(GET_LOTS_BIDDER_BARGAINING)) {
                prep.setInt(1, idBidder);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Lot lot = new Lot();
                        setLotParameters(lot, rs);
                        lots.add(lot);
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
        logger.info("Successfully got List<Lot> for cart page.");
        return lots;
    }

    @Override
    public int getAllLotsCount() throws DAOException {
        logger.info("Trying to get quantity of all active lots.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int quantity;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_LOTS_COUNT)) {
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
        logger.info("Successfully got all active lots count.");
        return quantity;
    }

    @Override
    public int getLotsCountByCategory(String category) throws DAOException {
        logger.info("Trying to get quantity of lots by category.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int quantity;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_LOTS_COUNT_BY_CATEGORY)) {
                prep.setString(1, category);
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
        logger.info("Successfully got lots count by category.");
        return quantity;
    }

    @Override
    public void updateLotOwnerSeller(int idLot, int owner, int seller) throws DAOException {
        logger.info("Trying to update lot state.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(UPDATE_LOT_STATE)) {
                prep.setInt(1, owner);
                prep.setInt(2, seller);
                prep.setInt(3, idLot);
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
        logger.info("Successfully updated lot state.");
    }

    private static void setLotParameters(Lot lot, ResultSet rs) throws SQLException {
        logger.info("LotDAOImpl.setLotParameters().");
        lot.setId(rs.getInt(1));
        lot.setName(rs.getString(2));
        lot.setCategory(rs.getString(3));
        lot.setAuthor(rs.getString(4));
        lot.setYear(rs.getInt(5));
        lot.setDescription(rs.getString(6));
        lot.setStartPrice(rs.getLong(7));
        lot.setImage(rs.getString(8));
        lot.setOwnerBidder(rs.getInt(9));
        lot.setSellerBidder(rs.getInt(10));
        lot.setCreatedAt(rs.getLong(11));
        lot.setCurrentPrice(rs.getLong(12));
        lot.setLastBidder(rs.getInt(13));
        lot.setCloseAt(rs.getLong(14));
        logger.info("LotDAOImpl.setLotParameters() completed.");
    }

    @Override
    public synchronized void insertLot(Lot lot) throws DAOException {
        logger.info("Trying to insert lot in db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(INSERT_LOT)) {
                prep.setString(1, lot.getName());
                prep.setString(2, lot.getCategory());
                prep.setString(3, lot.getAuthor());
                prep.setInt(4, lot.getYear());
                prep.setString(5, lot.getDescription());
                prep.setDouble(6, lot.getStartPrice());
                prep.setString(7, lot.getImage());
                prep.setInt(8, lot.getOwnerBidder());
                prep.setInt(9, lot.getSellerBidder());
                prep.setLong(10, lot.getCloseAt());
                prep.executeUpdate();
            }
            try (PreparedStatement prep = connection.prepareStatement(GET_LOT_ID)) {
                try (ResultSet rs = prep.executeQuery()) {
                    rs.next();
                    lot.setId(rs.getInt(1));
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
        logger.info("Successfully inserted lot to the suggest state.");
    }

    @Override
    public void updateLot(Lot lot) throws DAOException {
        logger.info("Trying to update lot in db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(UPDATE_LOT)) {
                prep.setInt(1, lot.getOwnerBidder());
                prep.setInt(2, lot.getSellerBidder());
                prep.setDouble(3, lot.getCurrentPrice());
                prep.setLong(4, lot.getCreatedAt());
                prep.setLong(5, lot.getCloseAt());
                prep.setInt(6, lot.getLastBidder());
                prep.setInt(7, lot.getId());
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
        logger.info("Successfully updated lot to the sale state.");
    }

    @Override
    public List<Lot> getSuggestedLotsPage(int start, int quantity) throws DAOException {
        logger.info("Trying to get page of suggested lots from db.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Lot> suggestions = new ArrayList<>();

        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_SUGGESTED_LOTS_PAGE)) {
                prep.setInt(1, start);
                prep.setInt(2, quantity);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        Lot lot = new Lot();
                        setLotParameters(lot, rs);
                        suggestions.add(lot);
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
        logger.info("Successfully got page of suggested lots.");
        return suggestions;
    }

    @Override
    public int getAllSuggestionsCount() throws DAOException {
        logger.info("Trying to get quantity of all suggested lots.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int quantity;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_SUGGESTIONS_COUNT)) {
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
        logger.info("Successfully got all suggested lots count.");
        return quantity;
    }
}
