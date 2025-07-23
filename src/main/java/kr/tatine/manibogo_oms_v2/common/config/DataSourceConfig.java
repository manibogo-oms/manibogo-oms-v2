package kr.tatine.manibogo_oms_v2.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Profile("prod")
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public HikariConfig writeHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource writeDataSource(@Qualifier("writeHikariConfig") HikariConfig writeHikariConfig) {
        return new HikariDataSource(writeHikariConfig);
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public HikariConfig readHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource readDataSource(@Qualifier("readHikariConfig") HikariConfig readHikariConfig) {
        return new HikariDataSource(readHikariConfig);
    }

    @Bean
    @Primary
    public DataSource dataSource(
            @Qualifier("writeDataSource") DataSource writeDataSource,
            @Qualifier("readDataSource") DataSource readDataSource) {

        return new LazyConnectionDataSourceProxy(writeDataSource) {{
            setReadOnlyDataSource(readDataSource);
        }};
    }

}
