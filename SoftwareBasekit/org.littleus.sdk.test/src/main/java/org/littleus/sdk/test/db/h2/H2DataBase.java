package org.littleus.sdk.test.db.h2;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

/**
 * @author hyzhangj
 */
public class H2DataBase {
    private DataSource dataSource;

    private DataSource h2DataSource;

    public H2DataBase(String initSql) {
        h2DataSource = JdbcConnectionPool.create("jdbc:h2:" + initSql, "test", "test");
    }

    public DataSource getDataSource() {
        return h2DataSource;
    }
}
