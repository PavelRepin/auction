package training.task.dao.impl.pool;

import org.apache.log4j.Logger;
import training.task.dao.impl.DBProperties;
import training.task.dao.impl.DBPropertiesProvider;
import training.task.dao.api.exception.ConnectionPoolException;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPool implements Closeable {

    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final ConnectionPool instance = new ConnectionPool();

    private final String url;
    private final String user;
    private final String password;

    private final BlockingQueue<Connection> queue;

    private ConnectionPool() throws ConnectionPoolException {
        String url = null;
        String user = null;
        String password = null;
        BlockingQueue<Connection> queue = null;

        try {
            DBPropertiesProvider propertiesProvider = DBPropertiesProvider.getInstance();
            logger.info("System found database property file");

            String driverName = propertiesProvider.getString(DBProperties.DRIVER);
            Class.forName(driverName);

            url = propertiesProvider.getString(DBProperties.URL);
            user = propertiesProvider.getString(DBProperties.USERNAME);
            password = propertiesProvider.getString(DBProperties.PASSWORD);
            int initialPoolSize = propertiesProvider.getInt(DBProperties.INITIAL_POOL_SIZE);

            List<Connection> connections = openConnections(url, user, password, initialPoolSize);
            queue = new LinkedBlockingQueue<>(connections);
        } catch (Throwable e) {
            logger.fatal("Can't load driver due to: " + e, e);
        }

        this.url = url;
        this.user = user;
        this.password = password;
        this.queue = queue;
    }

    public Connection borrow() throws ConnectionPoolException {
        if (queue == null) {
            throw new ConnectionPoolException("Connection pool is not initialized");
        }

        Connection connection = queue.poll();

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }

        return connection;
    }


    public void release(Connection connection) {
        if (connection == null) {
            throw new NullPointerException("connection is null");
        }

        queue.add(connection);
    }

    @Override
    public void close() {
        if (queue != null) {
            closeConnections(queue);
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private static List<Connection> openConnections(String url, String user, String password, int count) throws SQLException {
        List<Connection> connections = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.add(connection);
            } catch (Throwable e) {
                closeConnections(connections);
                throw e;
            }
        }

        return connections;
    }

    private static void closeConnections(Collection<Connection> connections) {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can't close connection due to: " + e);
            }
        }
    }

}
