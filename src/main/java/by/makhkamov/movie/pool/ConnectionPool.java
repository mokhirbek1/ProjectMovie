package by.makhkamov.movie.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 10;
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed in registration Driver"+e);
        }
    }


    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> busyConnections;

    public static ConnectionPool getInstance() {
        return instance = new ConnectionPool();
    }

    private ConnectionPool() {
        Properties properties = new Properties();
        String url = "jdbc:mysql://localhost:3306/movieproject";
        properties.put("user", "root");
        properties.put("password", "1234");
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        busyConnections = new LinkedBlockingDeque<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                proxyConnection = new ProxyConnection(DriverManager.getConnection(url, properties));
            } catch (SQLException sqlException) {
                logger.error("error in loading connection!!!", sqlException);
                throw new ExceptionInInitializerError(sqlException);
            }
            freeConnections.add(proxyConnection);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException interruptedException) {
            logger.error("error in getting connection ", interruptedException);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            if (connection instanceof ProxyConnection /*connection1*/ && (busyConnections.remove(connection))) {
                freeConnections.put((ProxyConnection) connection);
            }
        } catch (InterruptedException interruptedException) {
            logger.error("error in releasing connection ", interruptedException);
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException interruptedException) {
                logger.error("error in destroying pools ", interruptedException);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> deregisterDrivers());
    }

}
