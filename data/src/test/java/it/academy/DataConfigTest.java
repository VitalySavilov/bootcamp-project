package it.academy;

import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataConfigTest {

    private static final Properties properties = new Properties();
    private  Connection connection;

    @BeforeAll
    static void init() throws IOException {
        properties.load(new FileInputStream("src/test/resources/test.db.properties"));
    }

    @Test
    void dataSourceHasConnectionToDataBase() throws SQLException {
        DataConfig dataConfig = new DataConfig();
        DataSource dataSource = dataConfig.dataSource(
                properties.getProperty("url"),
                properties.getProperty("driver"),
                properties.getProperty("db.username"),
                properties.getProperty("password"));
        assertNotNull(dataSource);
        connection = dataSource.getConnection();
        assertNotNull(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }

}