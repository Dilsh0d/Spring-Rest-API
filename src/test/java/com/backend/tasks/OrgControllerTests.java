package com.backend.tasks;

import com.backend.tasks.dto.OrganizationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrgControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testACreateOrg() throws Exception {
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setName("Coca Cola");

		mvc.perform(post("/orgs")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coca Cola"));
	}

	@Test
	public void testBUpdateOrg() throws Exception {
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setName("Coca Cola UZB");

		mvc.perform(put("/orgs/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coca Cola UZB"));

	}

	@Test
	public void testCGetOrg() throws Exception {
				mvc.perform(get("/orgs/1")
//						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coca Cola UZB"));

	}

	@Test
	public void testDGetAllOrg() throws Exception {

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("Sumsung");

        mvc.perform(post("/orgs")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(organizationDto))
        ).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sumsung"));

	    mvc.perform(get("/orgs")
						.accept(MediaType.APPLICATION_JSON_UTF8)
		).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
	}

    @Test
    public void testEDeleteOrg() throws Exception {
        mvc.perform(delete("/orgs/1")
        ).andExpect(status().isNoContent());

        mvc.perform(delete("/orgs/2")
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
