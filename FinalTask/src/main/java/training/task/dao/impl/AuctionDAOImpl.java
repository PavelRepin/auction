package training.task.dao.impl;

import org.apache.log4j.Logger;
import training.task.bean.Sale;
import training.task.dao.api.AuctionDAO;
import training.task.dao.api.exception.ConnectionPoolException;
import training.task.dao.api.exception.DAOException;
import training.task.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAOImpl implements AuctionDAO {
    private static Logger logger = Logger.getLogger(AuctionDAOImpl.class);
    private static final String INSERT_BID = "INSERT INTO model_for_auction.bid (id_bidder, id_lot) VALUES (?, ?);";
    private static final String GET_ALL_LOTS_BIDDER_BID="SELECT bid.id_lot from model_for_auction.bid where id_bidder=?";
    private static final String INSERT_SALE = "INSERT INTO model_for_auction.sale (lot_id, bidder_id, final_price, sold_at) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_LAST_SALE = "DELETE FROM sale WHERE id_sale = (SELECT x.id FROM (SELECT MAX(sale.id_sale) AS id FROM sale) x)";

    @Override
    public void removeLastSale() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(REMOVE_LAST_SALE)) {
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of removing sale.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
    }

    @Override
    public void insertSale(Sale sale) throws DAOException {
        logger.info("Trying to insert data into the sale table.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(INSERT_SALE)) {
                prep.setInt(1, sale.getIdLot());
                prep.setInt(2, sale.getIdBidder());
                prep.setDouble(3, sale.getPrice());
                prep.setLong(4, sale.getSoldAt());
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of inserting sale.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully inserted data about bid.");
    }

    @Override
    public void insertBid(int idBidder, int idLot) throws DAOException {
        logger.info("Trying to insert data into the bid table.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(INSERT_BID)) {
                prep.setInt(1, idBidder);
                prep.setInt(2, idLot);
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of inserting bid.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully inserted data about bid.");
    }

    @Override
    public List<Integer> getAllLotsBidderBid(int idBidder) throws DAOException {
        logger.info("Trying to get all the bids.");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        List<Integer> lotsBidderBid = new ArrayList<>();
        try {
            connection = connectionPool.borrow();
            try (PreparedStatement prep = connection.prepareStatement(GET_ALL_LOTS_BIDDER_BID)) {
                prep.setInt(1, idBidder);
                try (ResultSet rs = prep.executeQuery()) {
                    while (rs.next()) {
                        lotsBidderBid.add(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't execute the query of getting all lots bidder bid.", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Can't borrow the connection.", e);
        } finally {
            if (connection != null) {
                connectionPool.release(connection);
            }
        }
        logger.info("Successfully got all the bids.");
        return lotsBidderBid;
    }
}
