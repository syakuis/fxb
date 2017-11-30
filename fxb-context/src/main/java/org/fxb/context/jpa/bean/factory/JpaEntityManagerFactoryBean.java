package org.fxb.context.jpa.bean.factory;

import org.fxb.config.Config;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * todo LocalContainerEntityManagerFactoryBean 상속하여 리팩토리하기
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 22.
 */
public class JpaEntityManagerFactoryBean extends AbstractFactoryBean<LocalContainerEntityManagerFactoryBean> {
  private final DataSource dataSource;
  private Config config;
  private Properties jpaProperties;
  private String[] packagesToScan;
  private String packageToScan;
  private JpaVendorAdapter vendorAdapter;

  public JpaEntityManagerFactoryBean(DataSource dataSource) {
    super();
    this.dataSource = dataSource;
  }

  public void setConfig(Config config) {
    this.config = config;
  }

  public void setJpaProperties(Properties jpaProperties) {
    this.jpaProperties = jpaProperties;
  }

  public void setPackagesToScan(String[] packagesToScan) {
    this.packagesToScan = packagesToScan.clone();
  }

  public void setPackageToScan(String packageToScan) {
    this.packageToScan = packageToScan;
  }

  public void setVendorAdapter(JpaVendorAdapter vendorAdapter) {
    this.vendorAdapter = vendorAdapter;
  }

  @Override
  protected LocalContainerEntityManagerFactoryBean createInstance() {
    LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();

    if (!"".equals(packageToScan)) {
      packagesToScan = StringUtils.tokenizeToStringArray(packageToScan, ",");
    }

    if (jpaProperties == null && config != null) {
      jpaProperties = new Properties();

      for(String name : config.getKeys("jpa.hibernate")) {
        jpaProperties.setProperty(name.replace("jpa.", ""), config.getString(name));
      }
    }


    manager.setDataSource(dataSource);
    manager.setPackagesToScan(packagesToScan);

    if (vendorAdapter == null) {
      vendorAdapter = new HibernateJpaVendorAdapter();
    }
    manager.setJpaVendorAdapter(vendorAdapter);
    manager.setJpaProperties(jpaProperties);

    return manager;
  }

  @Override
  public Class<LocalContainerEntityManagerFactoryBean> getObjectType() {
    return LocalContainerEntityManagerFactoryBean.class;
  }
}