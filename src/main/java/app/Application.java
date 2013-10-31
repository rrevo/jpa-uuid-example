package app;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@ComponentScan("app")
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

   public static void main(String[] args) {
      AbstractApplicationContext context = new AnnotationConfigApplicationContext(
            Application.class);

      PersonService service = context.getBean(PersonService.class);
      service.deleteAll();
      List<UUID> ids = service.create();
      System.out.println(String.format("Created %d people", ids.size()));
      for (Person person : service.findAll()) {
         System.out.println(person);
      }
      System.out.println();

      context.close();
   }

   @Bean
   public DataSource dataSource() {
      BoneCPDataSource dataSource = new BoneCPDataSource();
      dataSource.setDriverClass("org.postgresql.Driver");
      dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/dbtest");
      dataSource.setUsername("dbuser");
      dataSource.setPassword("dbpasswd");
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
         JpaVendorAdapter jpaVendorAdapter) {
      LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
      lef.setDataSource(dataSource);
      lef.setJpaVendorAdapter(jpaVendorAdapter);
      lef.setPackagesToScan("app");
      return lef;
   }

   @Bean
   public JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
      hibernateJpaVendorAdapter.setShowSql(false);
      hibernateJpaVendorAdapter.setGenerateDdl(true);
      hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
      return hibernateJpaVendorAdapter;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      return transactionManager;
   }

}
