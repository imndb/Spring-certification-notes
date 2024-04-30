
# War packaging: 

@SpringBootApplicatn
public class Application extends SpringBootServletInitializer {}


# MVC:

@AutoConfigureMockMVC
@SpringBootTest(webEnvironement=.Mock)

@Autowired
MockMvc mockMvc;

- spring-boot-starter-web is spring mvc


@WebMvcTest will not create an ApplicationContext that contains web components and all their dependency, but only web components.


Slide testing:

@WebMvcTest only for web layer. In this case use @MockBean to mock service layer. 

@DataJpaTest


- Erros, ServletRequest, ServletResopnse can be used as an argument for a method that is mapped to a request in a Controller class and Spring will auto-fill them with instances. 


# AOP:


@AfterReturning: (returning = "reward") -> parameter name in the method is reward

@AfterThrowingAdvice( throwing = "e") -> exception name as parameter e 

@After either normal return or after exception -> 

@Around -> before and after 

SpEL:

@ for spring beans
- expression

- using the default JDK dynamic proxies in Spring AOP, Only public interface method calls can be intercepted


-  @target limits matching to join points (the execution of methods when using Spring AOP) where the class of the executing object has an annotation of the given type. While @args limits matching to join points (the execution of methods when using Spring AOP)”


Join Point Argument: 

muss be the first parameter of an advice, 

it can be used to retrieve the arguments of the join point(method arguments). 

We can have only one JoinPint argument. 

It is not supported with Around advice. Around supports ProceedingJoinPoint. 



- Join Point is a point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution. 


- PointCut is a predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut. 


CGLIB proxy: cannot intercept private or final methods. Self-invation is not supported. Subclass is generated for each proxied object. 


- Spring AOP proxies are created using JDK Dynamic proxy by default.
- On top of auto configuration classes @Conditional or @Configuration is used.

# Spring Boot:

- For DataSource to be autoconfigured and a bean to be created successfully, database connector and spring-boot-starter-data-jdbc  must included as a dependency.
- Spring Boot will first look for profile specific properties before application specific properties. 

- Logback default logging system used by Spring Boot

# Spring Test:

- spring-boot-starter-test bringes junit 4, junit 5, spring test, assertJ, Mockito, Hamcrest, JSONassert to the classpath.


# JDBC:

- Datasource represents any datasource for SQL database
- The DataSource interface is implemented b a driver vendor.

- If JDBC is used with transaction, then DataSourceUtils which is using TransactionSynchronizationManager will reuse connection between method calls as long as transaction is not commited or rolled back. 

Which of the following properties are required in order to configure an external MySQL Database?

1. spring.datasource.password

2. spring.datasource.username

3. spring.datasource.url

4. spring.datasource.driver-class-name


-> all.

- JdbcTemplate.query() -> throws DataAccessException


- update method in jdbc template for update and insert operations.


# Transactions:


- @Transactional > (rollBackFor=, noRollbackFor=)
- @EnableTransactionManagement


- PlatformTransactionManager is an interface that is used by Spring AOP transactions Management to create, commit and rollback transactions.

- programmatic usage of transactions? TransactionTemplate
- attribute “readOnly” on the @Transactional may optimize query performance
- Phantom reads can occur in all database system isolation levels except for SERIALIZABLE

# Error Handling: 

Spring Boot provide regarding error handling?

1- Global error page
2- JSON error response


# Testing:

- ExtendWith is an annotation from JUnit 5.

@Mock and @MockBean: 

both the @MockBean and @Mock annotation can be used to create Mockito mocks but there are a couple of differences between the two annotations:

@Mock can only be applied to fields and parameters while @MockBean can only be applied to classes and fields.
@Mock can be sed to mock any Java class or interface while @MockBean only allows for mocking of Spring beans or creation of mock Spring beans.
@MockBean can be used to mock existing beans but also to create new beans that will belong to the Spring application context.
To be able to use the @MockBean annotation, the Spring runner (@RunWith(SpringRunner.class) ) has to be used to run the associated test.
@MockBean can be used to create custom annotations for specific, reoccurring, needs

- @BeforeEach junit 5


Four different types of web environments can be specified using the webEnvironment attribute of the @SpringBootTest annotation:

MOCK — Loads a web ApplicationContext and provides a mock web environment. Does not start a web server.
RANDOM_PORT — Loads a WebServerApplicationContext, provides a real web environment and starts an embedded web server listening on a random port. The port allocated can be obtained using the @LocalServerPort annotation or @Value(“”${local.server.port}””). Web server runs in a separate thread and server-side transactions will not be rolled back in transactional tests.
DEFINED_PORT — Loads a WebServerApplicationContext, provides a real web environment and starts an embedded web server listening on the port configured in the application properties, or port 8080 if no such configuration exists. Web server runs in a separate thread and server-side transactions will not be rolled back in transactional tests.
NONE — Loads an ApplicationContext without providing any web environment.


- Included in spring-boot-test starter:

- JUnit — Unit-testing framework.

- Spring Test and Spring Boot Test

- AssertJ — Fluent assertions for Java.

- Hamcrest — Framework for writing matchers that are both powerful and easy to read.

- Mockito — Mocking framework for Java.

- JSONassert — Tools for verifying JSON representation of data.

- JsonPath — A Java DSL for reading JSON documents.


# Spring Data: 

- Spring Data uses JDK Dynamic proxy to intercept all calls to repositories and not CGLIB proxy. 

- correct naming convention for custom find methods in Spring Data Repository Interface: find(First[count]) By[Property Expression] [comparison operator] [ordering operator]

- If not specified @EnableJpaRepositories, will scan the package of the configuration class, but not subclasses. 

- find, read, query, count and get can be at the beginning of the method definition in the repository.

- @EnableJpaRepositories(basePackages="package")


spring.sql.init.schema-locations=...
spring.sql.init.data-locations=...

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

sprng.jpa.hibernate.ddl-auto=none

# Beans:

- Controllers implemented using annotations do not have direct dependencies on Portlet or Servlet APIs.
- if you define both the “id” and “name” attributes in a bean’s XML definition:

1. “id” is used as bean identifier

2. “name” is used for bean aliases


valid ways of adding a Bean definition to the IoC Container:

1. <bean/> in XML

2. Calling DefaultListableBeanFactory.registerBeanDefinition

3. @Bean annotated method in a @Configuration class

4. Java Class annotated with @Component

- In order to define a bean, one can create a class annotated with @Configuration and add a method annotated with @Bean to it.


-spring.main.allow-bean-definition-overriding should be changed to allow bean definition overriding.

- condition annotations used by Spring Boot for auto-configuration:

@ConditionalOnClass — Presence of class on classpath.
@ConditionalOnMissingClass — Absence of class on classpath.
@ConditionalOnBean — Presence of Spring bean or bean type (class).
@ConditionalOnMissingBean — Absence of Spring bean or bean type (class).
@ConditionalOnProperty — Presence of Spring environment property.
@ConditionalOnResource — Presence of resource such as file.
@ConditionalOnWebApplication — If the application is considered to be a web application, that is uses the Spring WebApplicationContext, defines a session scope or has a StandardServletEnvironment.
@ConditionalOnNotWebApplication — If the application is not considered to be a web application.
@ConditionalOnExpression — Bean or configuration active based on the evaluation of a SpEL expression.
@ConditionalOnCloudPlatform — If specified cloud platform, Cloud Foundry, Heroku or SAP, is active.
@ConditionalOnEnabledEndpoint — Specified endpoint is enabled.
@ConditionalOnEnabledHealthIndicator — Named health indicator is enabled.
@ConditionalOnEnabledInfoContributor — Named info contributor is enabled.
@ConditionalOnEnabledResourceChain — Spring resource handling chain is enabled.
@ConditionalOnInitializedRestarter — Spring DevTools RestartInitializer has been applied with non-null URLs.
@ConditionalOnJava — Presence of a JVM of a certain version or within Condition Annotation Condition Factor a version range.
@ConditionalOnJndi — Availability of JNDI InitialContext and specified JNDI locations exist.
@ConditionalOnManagementPort — Spring Boot Actuator management port is either: Different from server port, same as server port or disabled.
@ConditionalOnRepositoryType — Specified type of Spring Data repository has been enabled.
@ConditionalOnSingleCandidate — Spring bean of specified type (class) contained in bean factory and single candidate can be determined.

- Weaving: linking aspects with other application types or objects to create an advised object. This can be done at compile time (using the AspectJ compiler, for example), load time, or at runtime

# Actuator:

@GetMapping("/orders")
@Timed("orders.summary")

- Prometheus and logfile are endpoints available for web applications.
- The statuses provided out of the box are UP, DOWN, UNKNOWN, OUT_OF_SERVICE

public RegardController(MeterRegistry meter) 

DistributionSummary.builder("reward.summary").
baseUnit("dollars").
register(mater);

summary.record(xxx);


public class CustomHealthIndoicator implements HealthIndicatior

implements health() method.


features: 1-Monitoring 2. Metrics 3. Management

- loggers endpoint to list all loggers or change the level of a logger.


# Spring Security:

- enable @Secured annotation: @EnableGlobalMethodSecurity(securedEnabled = true)

- SecurityFilterChain associates a request URL pattern with a list of filters in Spring Security.

-  @PreAuthorize, @PostAuthorize, @PreFilter are valid annotations that can use SpEL.

- Spring Security is implemented on web level
- @Secured can be used on class level
- @EnableSpringSecurity Should be used on a @Configuration class to enable spring security. 

- DelegatingFilterProxy is a class that acts as a proxy for a standard Servlet Filter, delegating to a spring-managed bean that implements the Filter interface
- FilterChainProxy is a class that delegates Filter requests to a list of spring-managed filter beans. 

# Rest:

- @ResponseStatus(HttpStatus.NOT_FOUND)
- @ExceptionHandler({Exception.class, ...}) 

- PUT, GET, DELETE are idempotent. 

- what will be injected in the rest method: Principal, Locale, HtttpSession, HttpServletRequest

@RequestParam("userID")
@PathVariable("AccountId")


- ApplicationContext interface is responsible for Instantiating, Configuring, Assembling and Managing the life-cycle of spring beans.


- Spring has mock objects to use in tests in Environment and JNDI and Servlet.


- 400, Client Side Error

-  If you develop a RESTful API that makes use of hypermedia, Spring Boot provides auto-configuration for Spring HATEOAS that works well with most applications. The auto-configuration replaces the need to use @EnableHypermediaSupport and registers a number of beans to ease building hypermedia-based applications, including a LinkDiscoverers (for client side support) and an ObjectMapper configured to correctly marshal responses into the desired representation.

The ObjectMapper is customized by setting the various spring.jackson.* properties or, if one exists, by a Jackson2ObjectMapperBuilder bean.

You can take control of Spring HATEOAS’ configuration by using @EnableHypermediaSupport. Note that doing so disables the ObjectMapper customization described earlier.

- @RequestMapping without method will accept all the methods.
- We can use @ResponseStatus annotation on top of a class to specify the response code for all the methos in the class.


The following bean scopes are only valid in the context of a web-aware Spring ApplicationContext: session, request, application


# Container:

@Bean
@ConditionalOnBean(DataSource.class)
@ConditionalOnMissingBean
@CondtionalOnClass
@ConditionalOnMissingClass
@ConditionlOnProperty


@SpringBootApplication (scanBasePackage-> @SpringBootConfiguration, @ComponentScan, @EnableAutoConfiguration 

@SpringBootTest(classes=Application.class) searches for SpringBootConfiguration


@AttributeOverride(name="value", column=@Column(name=ALLOCATION_PERCENTAGE))
private Percentage allocationPercentage;


@EnableAutoConfiguration(exclude=xxDataSourceConfiguration.class)


- @EntityScan("package")


- @Bean -> wither @Bean("name") or with name property @Bean(name="name")

- For the @Autowired to work, a component scanning should be enabled. 


- We can turn on compiler for SpEL expressions. 

- Single, Prototype, Application, request, session, websocket valid bean scope

- Use the close() method declared in the interface AbstractApplicationContext to close application context. 

- Methods annotated with @Bean:

 can't be final
 can be static
 cannot be private

- Class with @Configuration: cannot be final

- - A Bean has single scope by default.
- @Scope("prototype") or @scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
- Classes marked with the @Configuration annotation are a bean themselves. 
- Auto Configuration class is a class annotated with @Configuration
- @ContextConfiguration is the annotation to declare application context in an integration test.
- @ComponentScan without arguments tells Spring to scan the current package and all of its sub-packages.
- JDK Dynamic proxy does not support self-invocation
- JDK Dynamic Prox does does work with final classes.
- JDK Dynamic proxy requires the proxied object to implement an interface and only interface methods are proxied. 

- implementations of ApplicationContext Interface: AnnotationConfigAppliationContext, AnnotationConfigWebApplicationContext.


@ExtendsWith(SpringExtension.class)

@ContextConfiguration(classes= {SystemTestConfig.class})


@SpringJUnitConfig composes: @ExtendsWith and @ContextConfiguration


- Method annotated with @PostConstruct is called after dependency injection is done. 



- Spring calls methods annotated with @PreDestroy and PostConstruct only once.

- Methods annotated with @PreDestroy are called before the destroy() method of the interface DisposableBean.  


- If bean scope is prototype, then it is note completely managed by spring container and @PreDestroy and @PostConstruct methods won’t get called. 


- Spring calls methods annotated with @PostConstruct only once, just after initialization of bean properties. Methods annotated with @PostConstruct are called before afterPropertiesSet() method of the interface InitialiyingBean. The method annotated with @PostConstruct can have any access level but it can”t be static. 


