package org.example.w2.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionUtil {

    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://127.0.0.1:13306/webdb");
        config.setUsername("webdbuser");
        config.setPassword("webdbuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(1000*10);
        config.setMaximumPoolSize(100);
        config.setMinimumIdle(1);

        ds = new HikariDataSource(config);
    }

    public HikariDataSource getDs() {
        return ds;
    }

}
