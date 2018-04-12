
### Introduce

모듈간의 상호작용을 위한 데이터를 관리하는 라이브러리입니다.

모듈의 데이터에는 어떤 모듈을 의존하고 있는 지 어떤 상태를 갖고 있는 지 알 수 있습니다.

데이터를 활용하여 모듈들은 유연하게 조립되고 다양하게 확장할 수 있습니다.

### Todo

- [x] 데이터 관리자 : 데이터가 주입, 삭제, 조회가 가능해야 한다.
- [x] 데이터 빈 생성자 : Spring FactoryBean
- [x] 클래스를 이용한 데이터 주입 : Spring Annotation Scan 전략 활용
- [x] 주석 클래스를 이용한 동적 데이터 주입 : AspectJ 활용
- [x] 동적 데이터 즉 데이터베이스의 데이터를 주입하기 위한 서비스
- [ ] ModuleRedefinition value 리터럴 모듈 설정
- [ ] 동시성 데이터 보장?

### Module 초기화

```java
// immutable class
@ModuleDefinition
public class DemoModule extends AbstractModule {
	public ModuleEntity(String moduleName, String moduleId) {
		super("moduleName", "moduleId");
	}
}

// mutable class
@ModuleDefinition
public class DemoModule extends AbstractModule {
	public ModuleEntity(String moduleName, String moduleId) {
		super("moduleName", "moduleId", false);
	}
}
```

### Module 갱신

```java
public class BasicModuleContextService implements ModuleContextService {
  @Override
  public List<Module> getModules() {
    return null;
  }

  @Override
  public Module getModule(String moduleId) {
    return new ModuleDetails("moduleName", "moduleId");
  }
}

// service
public class BoardService {

  @ModuleRedefinition(expression = "args[0].moduleId", scope = Scope.THIS)
  public void insert(Board board) {
  }
}
```

### Module Configuration

```java
  @Bean
  public ModuleContextManagerFactoryBean moduleContextManager() {
    ModuleContextManagerFactoryBean bean = new ModuleContextManagerFactoryBean();
    bean.setBasePackages("org.fxb.module.test");
    return bean;
  }

  @Bean
  public ModuleRedefinitionAspectFactoryBean moduleContextAspect() {
    ModuleRedefinitionAspectFactoryBean bean = new ModuleRedefinitionAspectFactoryBean(
        moduleContextManager().getObject(), moduleContextService()
    );

    return bean;
  }
```