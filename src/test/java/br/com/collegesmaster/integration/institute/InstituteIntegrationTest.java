package br.com.collegesmaster.integration.institute;

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

import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;
import br.com.collegesmaster.integration.IntegrationTestConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstituteIntegrationTest extends IntegrationTestConfiguration {
	
	@Test
	public void test_001_givenToken_whenGetInstitutes_thenStatus200()
	  throws Exception {
		
	    mvc.perform(get("/institutes").header("Authorization", "Bearer " + super.oauthAccessToken)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.length()", is(0)));
	}
	
	@Test
	public void test_002_givenInstituteAndToken_whenCreateInstitute_thenReturnInstitute()
	  throws Exception {
		
		final InstituteImpl institute = new InstituteImpl();
		institute.setCity("RECIFE");
		institute.setCountry("BRASIL");
		institute.setName("IFPE");
		institute.setState("PE");
		
		final ObjectMapper mapper = new ObjectMapper();
		final String instituteToJson = mapper.writeValueAsString(institute);
		
		
	    mvc.perform(post("/institutes/create").header("Authorization", "Bearer " + super.oauthAccessToken)
	      .contentType(MediaType.APPLICATION_JSON).content(instituteToJson))
	      .andExpect(status().isOk())
	      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.name", is("IFPE")));
	}
}

