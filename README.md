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
