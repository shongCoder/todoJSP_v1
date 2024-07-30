package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public class TodoTest {

    @Test
    public void listTest() throws Exception {

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

        HikariDataSource ds = new HikariDataSource(config);

        @Cleanup Connection con = ds.getConnection();
        String query ="select * from tbl_todo where tno > 0 order by tno desc limit 0,10";
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        while (rs.next()) {
             log.info(rs.getInt(1)); //tno
             log.info(rs.getString(2)); //title
             log.info(rs.getString(3)); //writer
             log.info(rs.getTimestamp(4));
             log.info(rs.getTimestamp(5));
             log.info(rs.getBoolean(6));
        }
        log.info("----------------------------------------");
    }
}
