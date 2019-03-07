package com.backend.tasks;

import com.backend.tasks.dto.OrganizationDto;
import com.backend.tasks.dto.UserDto;
import com.backend.tasks.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Dilsh0d Tadjiev on 07.03.2019 14:59.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    OrganizationService organizationService;

    @Test
    public void testACreateUser() throws Exception {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("Coca Cola");
        organizationService.save(organizationDto);

        UserDto userDto = new UserDto();
        userDto.setFullname("Jack Douglas");

        mvc.perform(post("/orgs/1/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(userDto))
        ).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value("Jack Douglas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orgId").value(1L));
    }

    @Test
    public void testBUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFullname("Jack Donovan");

        mvc.perform(put("/orgs/1/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(userDto))
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value("Jack Donovan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orgId").value(1L));

    }

    @Test
    public void testCGetUser() throws Exception {
        mvc.perform(get("/orgs/1/users/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value("Jack Donovan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orgId").value(1L));

    }

    @Test
    public void testDGetAllOrgUser() throws Exception {
        mvc.perform(get("/orgs/1/users")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void testEDeleteUser() throws Exception {
        mvc.perform(delete("/orgs/1/users/1")
        ).andExpect(status().isNoContent());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
