package org.littleus.sdk.test.db.h2;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.util.Locale;

/**
 * @author hyzhangj
 */
public class H2DataBase {
    private static final String JDBC_URL = "jdbc:h2:mem:%s;MODE=%s;INIT=runscript from'classpath:%s'";
    private DataSource dataSource;

    public H2DataBase(String initSqlRelativePath) {
        this("test", "mysql", initSqlRelativePath);
    }

    public H2DataBase(String schema, String mode, String initSqlRelativePath) {
        this(schema, mode, initSqlRelativePath, "admin", "admin");
    }

    public H2DataBase(String schema, String mode, String initSqlRelativePath, String user, String pass) {
        String url = String.format(Locale.US, JDBC_URL, schema, mode, initSqlRelativePath);
        dataSource = JdbcConnectionPool.create(url, user, pass);
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
