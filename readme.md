# ZK MVVM CRUD 8
ZK Framework version 8 MVVM Pattern. CRUD Transaction Proof of Concept Sample Project.

This is the improved version with alternative pattern from previously [ZK MVVM CRUD](https://github.com/mkdika/zkmvvmcrud) sample project.

## Technologies
* __Java Platform:__
	* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html): JDK 8u121

* __Framework Stack:__
	* [Spring Framework](https://spring.io/): 4.3.9.RELEASE
	* [Hibernate Framework](http://hibernate.org/): 4.3.11.Final
	* [Hibernate Validator](http://hibernate.org/validator/): 5.4.1.Final
	* [ZK Framework](https://www.zkoss.org/): 8.0.2.2CE
	* _For detail information about library & its version view `pom.xml` file._

* __Dependency Management & Build System:__
	* [Maven](https://maven.apache.org/): 3.x

* __Database:__
	* [SQLite3](https://www.sqlite.org/)

* __Project Setup & IDE:__
	* [NetBeans IDE 8.2](https://netbeans.org/)


## Features
* Used [Spring Java Configuration](https://dzone.com/articles/spring-java-config-101-0) (alternative to Spring XML Config)
* Used only (pure) ZK MVVM Style on CRUD transaction.
* Used Hibernate Validator Framework as the implementation of Java Bean Validation [JSR-303](http://beanvalidation.org/1.0/spec/).
* Used Hibernate [bidirectional association One-To-Many](https://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch07.html) for Model.
* Browse list data have search feature that used `onChanging` or `onKeyPress` like event.
* Delete data have its popup confirmation.
* Applied [Template Pattern](https://www.tutorialspoint.com/design_pattern/template_pattern.htm) into several generic class, in order to have more clean code & scalability application.


## Install & Run the Application
* Install [NetBeans IDE version 8.2](https://netbeans.org/downloads/), please ensure you also have installed its bundle __Apache Tomcat__ and activate __Maven__ feature. If you're advanced user, you're welcome to use any other IDE (_Eclipse, IntelliJ IDEA, etc_).
* Off course, you need to clone this project into your local storage.
* Unless, you have been working with `maven` and have all the libraries that state above in your local cache. You need a __really proper internet connection__ on first attemp, in order to download all the dependencies. You can run `Build with Dependencies` on NetBeans.
* You dont need to install a database engine prior run the application. I used only a portable SQLite3 file, find it at this location: `/src/main/webapp/WEB-INF/classes/zkmvvmcrud8.db`. You can use any compatible Database Editor to directly connect to that file. And feel free to explor.
* Usually, it has came with sample data. But If you would like, I also prepared the populate script for init data. Please find out at this classpath: `com.mkdika.zkmvvmcrud8.cli.PopulateInitData.class`. Just run this `driver class`.
* Finally, you can simply run this sample application with NetBeans `run` options.


## Screenshots
_comming soon_


## Troubleshoot
* Try to search with google and [stackOverFlow](https://stackoverflow.com/) first, most of you question supposed to be ask by others. 
* And You are also feel free to [contact me](http://blog.mkdika.com/about/). I will feedback whenever I have time.

