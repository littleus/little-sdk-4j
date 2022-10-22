package org.littleus.sdk.test.db.h2;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type H 2 data base test.
 */
public class H2DataBaseTest {

    /**
     * Gets data source.
     */
    @Test
    public void getDataSource() {
        H2DataBase h2DataBase = new H2DataBase("h2sql/init_db.sql");
        DataSource dataSource = h2DataBase.getDataSource();
        assertNotNull(dataSource);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("select * from test.test")) {
            ResultSet queryResult = prepareStatement.executeQuery();
            boolean hasNext = queryResult.next();
            assertTrue(hasNext);

            int id = queryResult.getInt(1);
            assertEquals(1, id);

            String name = queryResult.getString(2);
            assertEquals("apple", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}