package com.backend.tasks;

import com.backend.tasks.dto.OrganizationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS,hierarchyMode = DirtiesContext.HierarchyMode.CURRENT_LEVEL)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TasksApplicationControllerTests {

	@Autowired
	private MockMvc mvc;

//	@Autowired
//	private JacksonTester<OrganizationDto> organizationJson;
//
//	@Before
//	public void setup() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		JacksonTester.initFields(this, objectMapper);
//	}

	@Test
	public void createOrg() throws Exception {
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setName("Coca Cola");

		mvc.perform(post("/orgs")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coca Cola"));

		organizationDto.setName("Sumsung");

		mvc.perform(post("/orgs")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sumsung"));
	}

	@Test
	public void updateOrg() throws Exception {
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setName("Coca Cola UZB");

		mvc.perform(put("/orgs/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coca Cola UZB"));

		organizationDto.setName("Sumsung UZB");

		mvc.perform(post("/orgs/2")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(organizationDto))
		).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sumsung UZB"));
	}

	/*
     * converts a Java object into JSON representation
     */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
