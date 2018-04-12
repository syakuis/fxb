package org.fxb.module.data.dao;

import data.domain.ModuleEntity;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.fxb.module.data.JPAConfiguration;
import org.fxb.module.data.domain.JPAModuleEntity;
import org.fxb.module.data.mybatis.dao.DemoEntityDAO;
import org.fxb.module.data.mybatis.domain.DemoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * todo 2개의 entityManagerFactory 만들고 transaction 을 하나로 처리할 수 있게?
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, Setting.class })
@Transactional
public class BasicModuleDAOTest {
  @Autowired
  JPAModuleDAO moduleDAO;

//  @Autowired
//  DemoEntityDAO demoEntityDAO;

//  @Autowired
//  private EntityManager entityManager;
//
//  @Before
//  public void setup() {
//    this.entityManager.getTransaction().begin();
//  }
//
//  @After
//  public void close() {
//    this.entityManager.getTransaction().commit();
//  }

//  @Test
//  public void jpaEntityManager() {
//    EntityManager manager = moduleDAO.getEntityManager();
//    System.out.println(entityManager.getTransaction().isActive());
//    System.out.println(manager.getTransaction().isActive());
//  }
//
//  public void save1() {
//    moduleDAO.save(new JPAModuleEntity("board", "board"));
//  }
//
//  public void save2() {
//    demoEntityDAO.save(new DemoEntity("goo", "2"));
//  }
//
//  @Test
//  public void transaction() {
//    try {
//      save1();
//      save2();
//      System.out.println(moduleDAO.findAll().size());
//      System.out.println(demoEntityDAO.findAll().size());
////      int a = 1 / 0;
//    } catch (Exception e) {
//      throw new RuntimeException();
//    }
//
//    System.out.println(moduleDAO.findAll().size());
//    System.out.println(demoEntityDAO.findAll().size());
//  }

  @Test
  public void crud() {
    List<ModuleEntity> moduleEntities = moduleDAO.findAll();

    moduleEntities.stream().forEach(moduleEntity -> {
      ModuleEntity newModuleEntity = moduleDAO.findOne(moduleEntity.getId());
      Assert.assertEquals(moduleEntity, newModuleEntity);
    });

    moduleDAO.save(new JPAModuleEntity("board", "board"));

    Assert.assertTrue(moduleDAO.findAll().size() > moduleEntities.size());

    moduleDAO.delete("board");

    Assert.assertNull(moduleDAO.findOne("board"));

    Assert.assertTrue(moduleDAO.findAll().size() == moduleEntities.size());
  }
}

@Configuration
@ComponentScan(
    basePackages = "org.fxb.module",
    useDefaultFilters = false,
    includeFilters = @Filter(type = FilterType.ANNOTATION, value = Repository.class)
)
@EnableTransactionManagement
class Setting {
//  @Autowired
//  private DataSource dataSource;
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//    bean.setPersistenceUnitName("fxb");
//    bean.setPackagesToScan("org.fxb.module.data.mybatis.domain");
//    bean.setDataSource(dataSource);
//
//    Properties hibernateProperties = new Properties();
//    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//    hibernateProperties.setProperty("hibernate.show_sql", "true");
//    hibernateProperties.setProperty("hibernate.format_sql", "true");
//    hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
//
//    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    bean.setJpaVendorAdapter(vendorAdapter);
//    bean.setJpaProperties(hibernateProperties);
//    return bean;
//  }
//
//  @Bean
//  public PlatformTransactionManager txManager2() {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
//    return transactionManager;
//  }
}
