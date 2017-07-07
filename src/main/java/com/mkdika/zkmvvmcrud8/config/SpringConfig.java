package com.mkdika.zkmvvmcrud8.config;

import com.mkdika.zkmvvmcrud8.helper.AppUtil;
import java.io.File;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 *
 * Alternative Configuration for common Spring XML Config file. Used Java Based
 * (Programmatically Configuration).
 */
@Configuration
@ComponentScan(basePackages = "com.mkdika.zkmvvmcrud8")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig {

    public static boolean WEB_MODE = true;

    private static final String HIBERNATE_PACKAGES_TO_SCAN = "com.mkdika.zkmvvmcrud8.model";

    @Resource
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(getDbUrl());
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
        sessionBuilder.scanPackages(HIBERNATE_PACKAGES_TO_SCAN);
        sessionBuilder.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        sessionBuilder.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        sessionBuilder.setProperty("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        sessionBuilder.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

        sessionBuilder.setProperty("hibernate.use_identifer_rollback", env.getRequiredProperty("hibernate.use_identifer_rollback"));
        sessionBuilder.setProperty("hibernate.enable_lazy_load_no_trans", env.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        sessionBuilder.setProperty("hibernate.order_inserts", env.getRequiredProperty("hibernate.order_inserts"));
        sessionBuilder.setProperty("hibernate.order_updates", env.getRequiredProperty("hibernate.order_updates"));
        sessionBuilder.setProperty("hibernate.jdbc.batch_size", env.getRequiredProperty("hibernate.jdbc.batch_size"));
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager htm = new HibernateTransactionManager(getSessionFactory());
        return htm;
    }

    private String getDbUrl() {
        if (WEB_MODE) {
            return "jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "zkmvvmcrud8.db";
        } else {
            String sl = AppUtil.getOsPathSlashChar();
            StringBuilder str = new StringBuilder();
            str.append("jdbc:sqlite:");
            str.append(new File("").getAbsolutePath());
            str.append(sl);
            str.append("src");
            str.append(sl);
            str.append("main");
            str.append(sl);
            str.append("webapp");
            str.append(sl);
            str.append("WEB-INF");
            str.append(sl);
            str.append("classes");
            str.append(sl);
            str.append("zkmvvmcrud8.db");
            return str.toString();
        }
    }
}
