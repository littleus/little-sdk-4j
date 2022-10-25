package org.littleus.sdk.config.dynamic.source;

import org.littleus.sdk.test.db.h2.H2DataBase;

import javax.sql.DataSource;
import java.util.function.Supplier;

/**
 * The type H 2 data source supplier.
 *
 * @since 2022 -10-23
 */
public class H2DataSourceSupplier implements Supplier<DataSource> {
    private static final DataSource DATASOURCE = new H2DataBase("config.sql").getDataSource();

    @Override
    public DataSource get() {
        return DATASOURCE;
    }

    /**
     * Gets data source.
     *
     * @return the data source
     */
    public static DataSource getDataSource() {
        return DATASOURCE;
    }

}
