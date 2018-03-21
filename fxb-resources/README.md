# Spring Properties and MessageSource Loader

스프링 프로퍼티와 국제화 메세지를 기존 방식에서 개선된 방식을 제공합니다.


```xml
<dependency>
	<groupId>org.fxb</groupId>
	<artifactId>fxb-resources</artifactId>
	<version>1.0.0.BUILD-SNAPSHOT</version>
</dependency>
```

## Properties Loader

- 프로퍼티 경로 속성에 `Ant-Style Pattern` 을 사용할 수 있습니다.
- 여러 프로퍼티 파일을 한번에 불러올 수 있습니다.
- 여러 프로퍼티에 같은 속성은 Override 할 수 있습니다.
- 스프링 서비스 환경에 맞는 프로퍼티 파일을 불러올 수 있습니다.

### Spring Configuration

```java
@Configuration
@ActiveProfiles("test")
public class PropertiesConfiguration {

  // Basic
  @Bean
  public PropertiesFactoryBean propertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(
        "classpath:org/fxb/resources/**/first.properties",
        "classpath:org/fxb/resources/*/second.properties"
    );

    return bean;
  }

  @Bean
  public Properties config() throws IOException {
    return propertiesFactoryBean().getObject();
  }

  // Spring profiles
  @Bean
  public PropertiesFactoryBean profilePropertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(
        "classpath:org/fxb/resources/**/first.properties",
        "classpath:org/fxb/resources/*/second.properties",
        "classpath:org/fxb/resources/properties/first-[profile].properties"
    );

    return bean;
  }

  @Bean
  public Properties profileConfig() throws IOException {
    return profilePropertiesFactoryBean().getObject();
  }
}
```

## Spring DI

```java
public class PropertiesFactoryBeanTest {
  @Autowired
  private Properties config;

  @Autowired
  @Qualifier("profileConfig")
  private Properties profileConfig;
}
```

## MessageSource Loader