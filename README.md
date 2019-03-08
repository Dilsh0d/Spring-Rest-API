# Spring Rest API endpoint examples
Show examples how can create Rest API endpoints in Spring.In the code shows two examples: Organization and them users entities. 
1. Organizations(create,update,delete,all) 
2. Create organization users(create,update,delete,all)
### Project(Frameworks&Libs)
1. JDK 8
2. SpringBoot
3. H2 database
4. Gradle builder
### Run project
  Start this class file. It will run embedded tomcat with port **8080**:
```java
  com.backend.tasks.Application.java
```
### Rest API Controllers
**OrganizationController**<br/>
1. Create organization with ***post*** method:
```java
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Coca Cola"}' \
  http://localhost:8080/orgs
```
Response with status code **201**: _{"id":1,"name":"Coca Cola"}_
<hr/>

2. Update organization with ***put*** method:
```java
curl --header "Content-Type: application/json" \
  --request PUT \
  --data '{"name":"Coca Cola UZB"}' \
  http://localhost:8080/orgs/1
```
Response with status code **200**: _{"id":1,"name":"Coca Cola UZB"}_
<hr/>
3. Get one organization with ***get*** method:
```java
curl --request GET \
  http://localhost:8080/orgs/1
```
Response with status code **200**: _{"id":1,"name":"Coca Cola UZB"}_
<hr/>
4. Get all organizations list with ***get*** method:
```java
curl --request GET \
  http://localhost:8080/orgs
```
Response with status code **200**: _[{"id":1,"name":"Coca Cola UZB"}]_
<hr/>
5. Delete one organization with ***delete*** method:
```java
curl --request DELETE \
  http://localhost:8080/orgs/1
```
   Response with status code **204**:
