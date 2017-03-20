<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [learning for guice](#learning-for-guice)
  - [1. Hello World](#1-hello-world)
  - [2.Bindings](#2bindings)
    - [2.1 Linked Bindings](#21-linked-bindings)
    - [2.2 Annotation Bindings](#22-annotation-bindings)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

learning for guice
===
[![](https://img.shields.io/badge/guice-4.1.0-green.svg?style=flat)](https://github.com/google/guice)
![](https://img.shields.io/badge/java-1.8-orange.svg)
[![Build Status](https://travis-ci.org/winsnow/l4guice.svg?branch=master)](https://travis-ci.org/winsnow/l4guice)
[![codecov](https://codecov.io/gh/winsnow/l4guice/branch/master/graph/badge.svg)](https://codecov.io/gh/winsnow/l4guice)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/winsnow/l4guice/blob/master/LICENSE)

>The dependency injection pattern leads to code that's modular and
 testable, and Guice makes it easy to write.


## 1. Hello World
Example code@`com.test.guice.hello`   
Main class: `com.test.guice.hello.BillingModule`



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