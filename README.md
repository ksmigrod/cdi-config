# CDI-Config

This project shows a problem with OpenWebBeans in JavaSE setup.

## Sources and setup

`main` method in `Main` class instantiates CDI Container, retrieves OurBean invokes getTestProperty method on retrieved bean.

```java
package me.noip.ksmigrod.spikes.cdiconfig;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
    public static void main(String[] args) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInitializer.initialize()) {
            OurBean ourBean = container.select(OurBean.class).get();
            System.out.println(ourBean.getTestProperty());
        }
    }
}
```

`OurBean` gets config property from Microprofile Config and returns it with `getTestProperty` method. 

```java
package me.noip.ksmigrod.spikes.cdiconfig;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class OurBean {

    @Inject
    @ConfigProperty(name = "test.property")
    private String testProperty;

    public String getTestProperty() {
        return testProperty;
    }
}
```

There is also empty `beans.xml` file and the following `microprofile-config.properties`:

```properties
test.property=Hello World.
```

## Expected output

The project prints 'Hello World.', if Weld is used.

```
ksm@ksm-7560:~/IdeaProjects/spikes/cdi-config$ mvn -PWeld clean package && java -jar ./target/cdi-config-1.0-SNAPSHOT.jar 
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< me.noip.ksmigord.spikes:cdi-config >-----------------
[INFO] Building cdi-config 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ cdi-config ---
[INFO] Deleting /home/ksm/IdeaProjects/spikes/cdi-config/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ cdi-config ---
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.11.0:compile (default-compile) @ cdi-config ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 2 source files with javac [debug target 21] to target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ cdi-config ---
[INFO] skip non existing resourceDirectory /home/ksm/IdeaProjects/spikes/cdi-config/src/test/resources
[INFO] 
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ cdi-config ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] 
[INFO] --- surefire:3.2.2:test (default-test) @ cdi-config ---
[INFO] 
[INFO] --- jar:3.4.2:jar (default-jar) @ cdi-config ---
[INFO] Building jar: /home/ksm/IdeaProjects/spikes/cdi-config/target/cdi-config-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- dependency:3.6.1:copy-dependencies (default) @ cdi-config ---
[INFO] Copying jakarta.enterprise.cdi-api-4.0.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.enterprise.cdi-api-4.0.1.jar
[INFO] Copying jakarta.inject-api-2.0.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.inject-api-2.0.1.jar
[INFO] Copying jakarta.annotation-api-2.1.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.annotation-api-2.1.1.jar
[INFO] Copying jakarta.interceptor-api-2.1.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.interceptor-api-2.1.0.jar
[INFO] Copying microprofile-config-api-3.0.3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/microprofile-config-api-3.0.3.jar
[INFO] Copying smallrye-config-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar
[INFO] Copying smallrye-config-core-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-core-3.11.1.jar
[INFO] Copying smallrye-common-annotation-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-annotation-2.9.0.jar
[INFO] Copying smallrye-common-expression-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-expression-2.9.0.jar
[INFO] Copying smallrye-common-function-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-function-2.9.0.jar
[INFO] Copying smallrye-common-constraint-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-constraint-2.9.0.jar
[INFO] Copying smallrye-common-classloader-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-classloader-2.9.0.jar
[INFO] Copying asm-9.7.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/asm-9.7.1.jar
[INFO] Copying smallrye-config-common-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-common-3.11.1.jar
[INFO] Copying jboss-logging-3.6.1.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jboss-logging-3.6.1.Final.jar
[INFO] Copying weld-se-core-5.1.5.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-se-core-5.1.5.Final.jar
[INFO] Copying weld-environment-common-5.1.5.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-environment-common-5.1.5.Final.jar
[INFO] Copying weld-core-impl-5.1.5.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-core-impl-5.1.5.Final.jar
[INFO] Copying weld-api-5.0.SP3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-api-5.0.SP3.jar
[INFO] Copying weld-spi-5.0.SP3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-spi-5.0.SP3.jar
[INFO] Copying jakarta.el-api-5.0.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.el-api-5.0.1.jar
[INFO] Copying weld-lite-extension-translator-5.1.5.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/weld-lite-extension-translator-5.1.5.Final.jar
[INFO] Copying jboss-logging-processor-2.2.1.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jboss-logging-processor-2.2.1.Final.jar
[INFO] Copying jboss-logging-annotations-2.2.1.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jboss-logging-annotations-2.2.1.Final.jar
[INFO] Copying jdeparser-2.0.3.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jdeparser-2.0.3.Final.jar
[INFO] Copying jboss-classfilewriter-1.3.0.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jboss-classfilewriter-1.3.0.Final.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.416 s
[INFO] Finished at: 2025-02-03T11:02:14+01:00
[INFO] ------------------------------------------------------------------------
lut 03, 2025 11:02:14 AM org.jboss.weld.bootstrap.WeldStartup <clinit>
INFO: WELD-000900: 5.1.5 (Final)
lut 03, 2025 11:02:14 AM org.jboss.weld.environment.deployment.discovery.ReflectionDiscoveryStrategy processAnnotatedDiscovery
INFO: WELD-ENV-000014: Falling back to Java Reflection for bean-discovery-mode="annotated" discovery. Add org.jboss:jandex to the classpath to speed-up startup.
lut 03, 2025 11:02:14 AM org.jboss.weld.bootstrap.WeldStartup startContainer
INFO: WELD-000101: Transactional services not available. Injection of @Inject UserTransaction not available. Transactional observers will be invoked synchronously.
lut 03, 2025 11:02:15 AM org.jboss.weld.environment.se.WeldContainer fireContainerInitializedEvent
INFO: WELD-ENV-002003: Weld SE container 15a53dbe-1d2b-44b3-8da8-52ce4714bdfd initialized
Hello World.
lut 03, 2025 11:02:15 AM org.jboss.weld.environment.se.WeldContainer shutdown
INFO: WELD-ENV-002001: Weld SE container 15a53dbe-1d2b-44b3-8da8-52ce4714bdfd shut down
```

## Actual output

The project fails if build with OpenWebBeans.

```
ksm@ksm-7560:~/IdeaProjects/spikes/cdi-config$ mvn -POWB clean package && java -jar ./target/cdi-config-1.0-SNAPSHOT.jar 
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< me.noip.ksmigord.spikes:cdi-config >-----------------
[INFO] Building cdi-config 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ cdi-config ---
[INFO] Deleting /home/ksm/IdeaProjects/spikes/cdi-config/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ cdi-config ---
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.11.0:compile (default-compile) @ cdi-config ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 2 source files with javac [debug target 21] to target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ cdi-config ---
[INFO] skip non existing resourceDirectory /home/ksm/IdeaProjects/spikes/cdi-config/src/test/resources
[INFO] 
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ cdi-config ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] 
[INFO] --- surefire:3.2.2:test (default-test) @ cdi-config ---
[INFO] 
[INFO] --- jar:3.4.2:jar (default-jar) @ cdi-config ---
[INFO] Building jar: /home/ksm/IdeaProjects/spikes/cdi-config/target/cdi-config-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- dependency:3.6.1:copy-dependencies (default) @ cdi-config ---
[INFO] Copying jakarta.enterprise.cdi-api-4.0.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.enterprise.cdi-api-4.0.1.jar
[INFO] Copying jakarta.inject-api-2.0.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.inject-api-2.0.1.jar
[INFO] Copying jakarta.annotation-api-2.1.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.annotation-api-2.1.1.jar
[INFO] Copying jakarta.interceptor-api-2.1.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jakarta.interceptor-api-2.1.0.jar
[INFO] Copying microprofile-config-api-3.0.3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/microprofile-config-api-3.0.3.jar
[INFO] Copying smallrye-config-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar
[INFO] Copying smallrye-config-core-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-core-3.11.1.jar
[INFO] Copying smallrye-common-annotation-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-annotation-2.9.0.jar
[INFO] Copying smallrye-common-expression-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-expression-2.9.0.jar
[INFO] Copying smallrye-common-function-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-function-2.9.0.jar
[INFO] Copying smallrye-common-constraint-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-constraint-2.9.0.jar
[INFO] Copying smallrye-common-classloader-2.9.0.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-classloader-2.9.0.jar
[INFO] Copying asm-9.7.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/asm-9.7.1.jar
[INFO] Copying smallrye-config-common-3.11.1.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-common-3.11.1.jar
[INFO] Copying jboss-logging-3.6.1.Final.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/jboss-logging-3.6.1.Final.jar
[INFO] Copying openwebbeans-se-4.0.3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/openwebbeans-se-4.0.3.jar
[INFO] Copying openwebbeans-impl-4.0.3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/openwebbeans-impl-4.0.3.jar
[INFO] Copying openwebbeans-spi-4.0.3.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/openwebbeans-spi-4.0.3.jar
[INFO] Copying xbean-finder-shaded-4.26.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/xbean-finder-shaded-4.26.jar
[INFO] Copying xbean-asm9-shaded-4.26.jar to /home/ksm/IdeaProjects/spikes/cdi-config/target/lib/xbean-asm9-shaded-4.26.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.355 s
[INFO] Finished at: 2025-02-03T10:59:55+01:00
[INFO] ------------------------------------------------------------------------
lut 03, 2025 10:59:55 AM org.apache.webbeans.lifecycle.AbstractLifeCycle bootstrapApplication
INFO: OpenWebBeans Container is starting...
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/cdi-config-1.0-SNAPSHOT.jar!/META-INF/beans.xml
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-constraint-2.9.0.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-annotation-2.9.0.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-function-2.9.0.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-core-3.11.1.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/microprofile-config-api-3.0.3.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-common-3.11.1.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-classloader-2.9.0.jar!/
lut 03, 2025 10:59:55 AM org.apache.webbeans.corespi.scanner.AbstractMetaDataDiscovery addWebBeansXmlLocation
INFO: added beans archive URL: jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-common-expression-2.9.0.jar!/
Exception in thread "main" org.apache.webbeans.exception.WebBeansDeploymentException: jakarta.enterprise.inject.AmbiguousResolutionException: There is more than one Bean with type java.lang.String Qualifiers: [@org.eclipse.microprofile.config.inject.ConfigProperty(name="test.property", defaultValue="org.eclipse.microprofile.config.configproperty.unconfigureddvalue")]
for injection into Field Injection Point, field name :  testProperty, Bean Owner : [OurBean, WebBeansType:MANAGED, Name:null, API Types:[java.lang.Object,me.noip.ksmigrod.spikes.cdiconfig.OurBean], Qualifiers:[jakarta.enterprise.inject.Default,jakarta.enterprise.inject.Any]]
found beans: 
String, WebBeansType:PRODUCERMETHOD, Name:null, API Types:[java.io.Serializable,java.lang.CharSequence,java.lang.constant.ConstantDesc,java.lang.Comparable<String>,java.lang.String,java.lang.Object,java.lang.constant.Constable], Qualifiers:[jakarta.enterprise.inject.Any,org.eclipse.microprofile.config.inject.ConfigProperty], Producer Method: protected java.lang.String io.smallrye.config.inject.ConfigProducer.produceStringConfigProperty(jakarta.enterprise.inject.spi.InjectionPoint) from jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar!/io/smallrye/config/inject/ConfigProducer.class
String, WebBeansType:PRODUCERMETHOD, Name:null, API Types:[java.io.Serializable,java.lang.CharSequence,java.lang.constant.ConstantDesc,java.lang.Comparable<String>,java.lang.String,java.lang.Object,java.lang.constant.Constable], Qualifiers:[jakarta.enterprise.inject.Any,org.eclipse.microprofile.config.inject.ConfigProperty], Producer Method: protected java.lang.String io.smallrye.config.inject.ConfigProducer.produceStringConfigProperty(jakarta.enterprise.inject.spi.InjectionPoint) from jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar!/io/smallrye/config/inject/ConfigProducer.class
        at org.apache.webbeans.config.BeansDeployer.deploy(BeansDeployer.java:399)
        at org.apache.webbeans.lifecycle.AbstractLifeCycle.bootstrapApplication(AbstractLifeCycle.java:137)
        at org.apache.webbeans.lifecycle.AbstractLifeCycle.startApplication(AbstractLifeCycle.java:103)
        at org.apache.openwebbeans.se.OWBInitializer.newContainer(OWBInitializer.java:128)
        at org.apache.openwebbeans.se.OWBInitializer.initialize(OWBInitializer.java:113)
        at org.apache.openwebbeans.se.SeInitializerFacade.initialize(SeInitializerFacade.java:177)
        at me.noip.ksmigrod.spikes.cdiconfig.Main.main(Main.java:9)
Caused by: jakarta.enterprise.inject.AmbiguousResolutionException: There is more than one Bean with type java.lang.String Qualifiers: [@org.eclipse.microprofile.config.inject.ConfigProperty(name="test.property", defaultValue="org.eclipse.microprofile.config.configproperty.unconfigureddvalue")]
for injection into Field Injection Point, field name :  testProperty, Bean Owner : [OurBean, WebBeansType:MANAGED, Name:null, API Types:[java.lang.Object,me.noip.ksmigrod.spikes.cdiconfig.OurBean], Qualifiers:[jakarta.enterprise.inject.Default,jakarta.enterprise.inject.Any]]
found beans: 
String, WebBeansType:PRODUCERMETHOD, Name:null, API Types:[java.io.Serializable,java.lang.CharSequence,java.lang.constant.ConstantDesc,java.lang.Comparable<String>,java.lang.String,java.lang.Object,java.lang.constant.Constable], Qualifiers:[jakarta.enterprise.inject.Any,org.eclipse.microprofile.config.inject.ConfigProperty], Producer Method: protected java.lang.String io.smallrye.config.inject.ConfigProducer.produceStringConfigProperty(jakarta.enterprise.inject.spi.InjectionPoint) from jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar!/io/smallrye/config/inject/ConfigProducer.class
String, WebBeansType:PRODUCERMETHOD, Name:null, API Types:[java.io.Serializable,java.lang.CharSequence,java.lang.constant.ConstantDesc,java.lang.Comparable<String>,java.lang.String,java.lang.Object,java.lang.constant.Constable], Qualifiers:[jakarta.enterprise.inject.Any,org.eclipse.microprofile.config.inject.ConfigProperty], Producer Method: protected java.lang.String io.smallrye.config.inject.ConfigProducer.produceStringConfigProperty(jakarta.enterprise.inject.spi.InjectionPoint) from jar:file:/home/ksm/IdeaProjects/spikes/cdi-config/target/lib/smallrye-config-3.11.1.jar!/io/smallrye/config/inject/ConfigProducer.class
        at org.apache.webbeans.util.InjectionExceptionUtil.throwAmbiguousResolutionExceptionForBeans(InjectionExceptionUtil.java:103)
        at org.apache.webbeans.util.InjectionExceptionUtil.throwAmbiguousResolutionException(InjectionExceptionUtil.java:93)
        at org.apache.webbeans.container.InjectionResolver.resolve(InjectionResolver.java:685)
        at org.apache.webbeans.container.InjectionResolver.checkInjectionPointType(InjectionResolver.java:175)
        at org.apache.webbeans.container.BeanManagerImpl.validate(BeanManagerImpl.java:1223)
        at org.apache.webbeans.util.WebBeansUtil.validate(WebBeansUtil.java:1506)
        at org.apache.webbeans.config.BeansDeployer.validate(BeansDeployer.java:1269)
        at org.apache.webbeans.config.BeansDeployer.validateInjectionPoints(BeansDeployer.java:1180)
        at org.apache.webbeans.config.BeansDeployer.deploy(BeansDeployer.java:324)
        ... 6 more
```
