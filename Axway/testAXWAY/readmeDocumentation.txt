The Maven Project uses dependencies:

Web — web development Tomcat, Spring MVC

DevTools — Spring Boot Development Tools

JPA — Java Persistence API including spring-data-JPA, spring-orm, and Hibernate

MySQL — MySQL JDBC driver

Database is Mysql Server and has 3 tables
BuyerCorporate,BuyerIndividual,Transactions


 Java classes:
superclass repository - buyerRepository
-One-To-Many associations are not possible on a mapped superclass so add them to subclasses

subclasses repository - BuyerIndividualRepository
BuyerCorporateRepository
-are mapped to the 2 tables for individual and corporate buyer

BuyerController - responsible with the CRUD operations, used streams,filters from java8 to facilitate searches

Entities:
CorporateBuyer
IndividualBuyer
Transactions


application.properties
have the database configuration, password and ip:port


