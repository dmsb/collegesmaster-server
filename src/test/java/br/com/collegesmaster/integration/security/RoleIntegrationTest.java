package br.com.collegesmaster.integration.security;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.collegesmaster.integration.IntegrationTestConfiguration;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleIntegrationTest extends IntegrationTestConfiguration {

	@Test
	public void test_001_givenToken_whenGetRoles_thenStatus200()
	  throws Exception {

	    mvc.perform(get("/roles").header("Authorization", "Bearer " + oauthAccessToken)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void test_002_givenRoleAndToken_whenCreateRole_thenReturnRole()
	  throws Exception {
		
		final RoleImpl role = new RoleImpl();
		role.setName("TEST");
		
		final ObjectMapper mapper = new ObjectMapper();
		final String roleToJson = mapper.writeValueAsString(role);
		
	    mvc.perform(post("/roles/create").header("Authorization", "Bearer " + oauthAccessToken)
	      .contentType(MediaType.APPLICATION_JSON).content(roleToJson))
	      .andExpect(status().isOk())
	      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.name", is("TEST")));
	}
}
