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
1. **OrganizationController**<br/>
    ```java
    @RestController
    @RequestMapping("/orgs")
    public class OrganizationController {

        @Autowired
        private OrganizationService organizationService;

        @PostMapping
        @ResponseStatus(value = HttpStatus.CREATED)
        public @ResponseBody OrganizationDto createOrg(@RequestBody OrganizationDto orgDto) {
            return organizationService.save(orgDto);
        }

        @PutMapping(path = "/{orgId}")
        public @ResponseBody OrganizationDto updateOrg(@PathVariable(name = "orgId") Long orgId,@RequestBody OrganizationDto orgDto) {
            orgDto.setId(orgId);
            return organizationService.update(orgDto);
        }

        @GetMapping(path = "/{orgId}")
        public @ResponseBody OrganizationDto getOrg(@PathVariable(name = "orgId") Long orgId) {
            return organizationService.get(orgId);
        }

        @DeleteMapping(path = "/{orgId}")
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void deleteOrg(@PathVariable(name = "orgId") Long orgId) {
            organizationService.delete(orgId);
        }

        @GetMapping
        public @ResponseBody  List<OrganizationDto> listOrg() {
            return organizationService.list();
        }
    }
    ```
    1. Create organization with ***post*** method:
    ```java
    curl --header "Content-Type: application/json" \
      --request POST \
      --data '{"name":"Coca Cola"}' \
      http://localhost:8080/orgs
    ```
    ##### Response with status code **201**: _{"id":1,"name":"Coca Cola"}_
    <hr/>

    2. Update organization with ***put*** method:
    ```java
    curl --header "Content-Type: application/json" \
      --request PUT \
      --data '{"name":"Coca Cola UZB"}' \
      http://localhost:8080/orgs/1
    ```
    ##### Response with status code **200**: _{"id":1,"name":"Coca Cola UZB"}_
    <hr/>

    3. Get one organization with ***get*** method:
    ```java
    curl --request GET \
      http://localhost:8080/orgs/1
    ```
    ##### Response with status code **200**: _{"id":1,"name":"Coca Cola UZB"}_
    <hr/>

    4. Get all organizations list with ***get*** method:
    ```java
    curl --request GET \
      http://localhost:8080/orgs
    ```
    ##### Response with status code **200**: _[{"id":1,"name":"Coca Cola UZB"}]_
    <hr/>

    5. Delete one organization with ***delete*** method:
    ```java
    curl --request DELETE \
      http://localhost:8080/orgs/1
    ```
    ##### Response with status code **204**:

2. **UserController**<br/>
    _At least Create one organization before run this is http urls:_
    ```java
    @RestController
    @RequestMapping("/orgs/{orgId}/users")
    public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping
        @ResponseStatus(value = HttpStatus.CREATED)
        public @ResponseBody
        UserDto createOrg(@PathVariable(name = "orgId") Long orgId, @RequestBody UserDto userDto) {
            return userService.save(orgId,userDto);
        }

        @PutMapping(path = "/{userId}")
        public @ResponseBody UserDto updateOrg(@PathVariable(name = "orgId") Long orgId,@PathVariable(name = "userId") Long userId,@RequestBody UserDto userDto) {
            userDto.setId(userId);
            return userService.update(orgId,userDto);
        }

        @GetMapping(path = "/{userId}")
        public @ResponseBody  UserDto getOrg(@PathVariable(name = "orgId") Long orgId, @PathVariable(name = "userId") Long userId) {
            return userService.get(orgId,userId);
        }

        @DeleteMapping(path = "/{userId}")
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void deleteOrg(@PathVariable(name = "orgId") Long orgId, @PathVariable(name = "userId") Long userId) {
            userService.delete(orgId,userId);
        }

        @GetMapping
        public @ResponseBody
        List<UserDto> listOrg(@PathVariable(name = "orgId") Long orgId) {
            return userService.list(orgId);
        }
    }
    ```
    1. Create organization user with ***post*** method:
    ```java
    curl --header "Content-Type: application/json" \
      --request POST \
      --data '{"fullname":"Jack Douglas"}' \
      http://localhost:8080/orgs/1/users
    ```
    ##### Response with status code **201**: _{"id":1,"fullname":"Jack Douglas","orgId":1}_
    <hr/>

    2. Update organization user with ***put*** method:
    ```java
    curl --header "Content-Type: application/json" \
      --request PUT \
      --data '{"fullname":"Jack Black"}' \
      http://localhost:8080/orgs/1/users/1
    ```
    ##### Response with status code **200**: _{"id":1,"fullname":"Jack Black","orgId":1}_
    <hr/>

    3. Get one organization user with ***get*** method:
    ```java
    curl --request GET \
      http://localhost:8080/orgs/1/users/1
    ```
    ##### Response with status code **200**: _{"id":1,"fullname":"Jack Black","orgId":1}_
    <hr/>

    4. Get all organization users list with ***get*** method:
    ```java
    curl --request GET \
      http://localhost:8080/orgs/1/users
    ```
    ##### Response with status code **200**: _[{"id":1,"fullname":"Jack Black","orgId":1}]_
    <hr/>

    5. Delete one organization user with ***delete*** method:
    ```java
    curl --request DELETE \
      http://localhost:8080/orgs/1/users/1
    ```
    ##### Response with status code **204**:
### 404 Not found response status
```java
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Object not found")
public class NotFound404Exception extends RuntimeException {
}
```
If not found from database organization or users then give runtime error _NotFound404Exception_ and return to client **404** error
