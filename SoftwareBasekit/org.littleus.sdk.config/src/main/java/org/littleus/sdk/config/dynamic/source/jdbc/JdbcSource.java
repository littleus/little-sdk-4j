package org.littleus.sdk.config.dynamic.source.jdbc;


import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.JDBCConfigurationSource;
import org.littleus.sdk.config.dynamic.util.BeanCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author hyzhangj
 * @since 2022-09-25
 */
public class JdbcSource implements PolledConfigurationSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSource.class);
    private PolledConfigurationSource innerSource;

    private final Class<? extends Supplier<DataSource>> dataSourceSupplierCls;
    private final String querySql;
    private final String keyColName;
    private final String valueColName;

    public JdbcSource(Class<? extends Supplier<DataSource>> dataSourceSupplierCls, String querySql, String keyColName, String valueColName) {
        this.dataSourceSupplierCls = dataSourceSupplierCls;
        this.querySql = querySql;
        this.keyColName = keyColName;
        this.valueColName = valueColName;
    }

    @Override
    public PollResult poll(boolean initial, Object checkPoint) throws Exception {
        if (Objects.nonNull(innerSource)) {
            return innerSource.poll(initial, checkPoint);
        }

        try {
            Supplier<DataSource> dataSourceSupplier = BeanCreator.create(dataSourceSupplierCls);
            DataSource dataSource = dataSourceSupplier.get();
            innerSource = new JDBCConfigurationSource(dataSource, querySql, keyColName, valueColName);
            return innerSource.poll(initial, checkPoint);
        } catch (Exception e) {
            LOGGER.error("poll jdbc config error.", e);
            return PollResult.createFull(Collections.emptyMap());
        }
    }
}
