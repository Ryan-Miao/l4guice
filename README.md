<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [learning for guice](#learning-for-guice)
  - [1. Hello World](#1-hello-world)
  - [2.Bindings](#2bindings)
    - [2.1 Linked Bindings](#21-linked-bindings)
    - [2.2 Annotation Bindings](#22-annotation-bindings)
    - [2.3 Instance Bindings](#23-instance-bindings)
    - [2.4 @Providers Methods](#24-providers-methods)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

learning for guice
===
[![](https://img.shields.io/badge/guice-4.1.0-green.svg?style=flat)](https://github.com/google/guice)
![](https://img.shields.io/badge/java-1.8-orange.svg)
[![Build Status](https://travis-ci.org/Ryan-Miao/l4guice.svg?branch=master)](https://travis-ci.org/Ryan-Miao/l4guice)
[![codecov](https://codecov.io/gh/Ryan-Miao/l4guice/branch/master/graph/badge.svg)](https://codecov.io/gh/Ryan-Miao/l4guice)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/winsnow/l4guice/blob/master/LICENSE)

>The dependency injection pattern leads to code that's modular and
 testable, and Guice makes it easy to write.


## 1. Hello World
Example code@`com.test.guice.hello`   
Main class: `com.test.guice.hello.BillingModule`

Note that it will throw a `com.google.inject.CreationException` 
if bind multiple class with the same key.


## 2.Bindings
>To specify how dependencies are resolved, configure your injector with bindings.

Guice is different from spring since I don't find the scan package. I guess it may scan 
all the classes in the classloader. Thus guice could produce any instance for the class.

### 2.1 Linked Bindings
Example code@ `com.test.guice.bindings.LinkedBindings.BillingModule`   

The code may like this:  
```java
bind(Father.class).to(Child.class);
```
This will inject `Father` with `Child`. When you get `Father`  
from injector, you will get `Child`.

Linked bindings can also be chained:  
```java
public class BillingModule extends AbstractModule {
  @Override 
  protected void configure() {
    bind(TransactionLog.class).to(DatabaseTransactionLog.class);
    bind(DatabaseTransactionLog.class).to(MySqlDatabaseTransactionLog.class);
  }
}
```

### 2.2 Annotation Bindings
Example code@ `com.test.guice.bindings.AnnotationsBindgings.AnnotationBindingModule`   

The father class such as `IPersonSay` has many implementations. 
If we just want to inject to a special one, just like `@Qualifier` 
does in spring, guice bindings support an optional binding 
annotation. The annotation and type together identify a 
binding.

We specified an annotation `@Chinese` to mark that the 
`IPersonSay` should inject with `ShenZhenSay`.
```java
public class AnnotationBindingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IPersonSay.class)
                .annotatedWith(Chinese.class)
                .to(ShenZhenSay.class);
    }
}
```
```java
public class HelloAnnotations {

    private IPersonSay personSay;

    @Inject
    public HelloAnnotations(@Chinese IPersonSay personSay) {
        this.personSay = personSay;
    }

}
```
As a comparison, in spring:
```java
@Autowired
@Qualifier("shenZhenSay")
private IPersonSay person;
```

If we don't want to create an annotation, we could use `@Named` instead.
```java
public class RealBillingService implements BillingService {

  @Inject
  public RealBillingService(@Named("Checkout") CreditCardProcessor processor,
      TransactionLog transactionLog) {
  }
}
```
```java
public class AnnotationBindingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreditCardProcessor.class)
        .annotatedWith(Names.named("Checkout"))
        .to(CheckoutCreditCardProcessor.class);
    }
}
```


### 2.3 Instance Bindings
>You can bind a type to specific instance of that type. This is usually only 
useful only for objects that don't have dependencies of their own, such 
as value objects.

Example code@ `com.test.guice.bindings.InstanceBindings.ConfigBind` 
`com.test.guice.bindings.InstanceBindings.ConfigModule`

**Bind**:
```java
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("jdbc.url"))
                .toInstance("jdbc:mysql://localhost/pizza");
        bind(Integer.class)
                .annotatedWith(Names.named("jdbc.timeout"))
                .toInstance(10);
    }
    
}


```

**Inject**:
```java
@Inject
@Named("jdbc.url")
private String jdbcUrl;

@Inject
@Named("jdbc.timeout")
private Integer timeout;

```
Or:
```java
@Inject
public ConfigBind(@Named("jdbc.url")String jdbcUrl,
    @Named("jdbc.timeout")Integer timeout) {
    this.jdbcUrl = jdbcUrl;
    this.timeout = timeout;
}
```

>Avoid using `.toInstance` with objects that are complicated to create, since it 
can slow down application startup. You can use an `@Provides` method instead.  


### 2.4 @Providers Methods
>When you need code to create an object, use an `@Provides` method. The method must be defined 
within a module, and it must have an `@Provides` annotation. The method's return 
type is the bound type. Whenever the injector needs an instance of that type, it 
will invoke the method.   
If the `@Provides` method has a binding annotation liken `@Chinese` or `@Named("xx")`,
Guice binds the annotated type. Dependencies can be passed in as 
parameters to the method. The injector will exercise the 
bindings for each of these before invoking the method.

Example code@ `com.test.guice.bindings.ProvidesBindings.ObjectInstanceModule`

**Bind by @Providers**:
```java
public class ObjectInstanceModule extends AbstractModule {

    public static final String JDBC_URL = "jdbc:mysql://localhost/pizaa";
    public static final Integer THREAD_POOL_SIZE = 30;

    @Override
    protected void configure() {
        bind(IPersonSay.class)
                .to(NewYorkSay.class);
    }

    @Provides
    TransactionLog provideTransactionLog(){
        return new MySqlDatabaseTrannsactionLog(
                JDBC_URL, THREAD_POOL_SIZE);
    }

    @Provides @Chinese
    IPersonSay providePerson(){
        return new ShenZhenSay();
    }

}
```
**Throwing Exceptions**:  
>`Guice` does not allow exceptions to be thrown from Providers. 
Exceptions thrown by `Provides` methods will be wrapped in 
a `ProvisionException`. It is bad practice to allow any 
kind of exception to be thrown--runtime or checked--from 
an `@Provides` method. If you need to throw an exception for some 
reason, you may want to use the [ThhrowingProviders extension](https://github.com/google/guice/wiki/ThrowingProviders) 
`@CheckedProvides` methods.
