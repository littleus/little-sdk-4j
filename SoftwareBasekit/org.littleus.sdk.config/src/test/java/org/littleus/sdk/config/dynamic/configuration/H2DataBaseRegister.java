package org.littleus.sdk.config.dynamic.configuration;

import org.littleus.sdk.config.dynamic.annotation.JdbcConfig;
import org.littleus.sdk.config.dynamic.source.H2DataSourceSupplier;

@JdbcConfig(dataSource = H2DataSourceSupplier.class, querySql = "select distinct paramkey,paramvalue from test.setting_info", keyColName = "paramkey", valueColName = "paramvalue")
public class H2DataBaseRegister {
}
