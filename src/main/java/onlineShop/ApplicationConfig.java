package onlineShop;

import javax.sql.DataSource;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


// Using Java based configuration allows you
// to write your Spring configuration without using XML.
// Java based configuration uses the @Configuration-annotated classes
// and @Bean-annotated methods.
// Using @Configuration annotation indicates that
// Spring IoC container can use it as a source of Beans definitions.
// Using the @Bean tells Spring that the method will return an object
// which should be registered as a bean in Spring container.


@Configuration
@EnableWebMvc
public class ApplicationConfig {

    @Bean(name = "sessionFactory") // by Spring
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean(); // 连接需要操作的db
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("onlineShop.model"); // 定义entity的位置/路径
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://laiproject-instance.c47ffvp2ygxw.us-east-2.rds.amazonaws.com:3306/ecommerce?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername("admin");
        dataSource.setPassword("493458130jzx");
        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        // 自动创建table
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // update，断开服务器后不清除数据库，create-drop 断开服务器后清除清除
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return hibernateProperties;
    }
}
