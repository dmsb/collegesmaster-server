package br.com.collegesmaster.integration.chalenge;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.collegesmaster.integration.IntegrationTestConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChallengeIntegrationTest extends IntegrationTestConfiguration {

	@Test
	public void test001_givenChallenges_whenProfessor_thenOk() throws Exception {
		final String accessToken = obtainAccessToken("test", "secret");

		mvc.perform(get("/challenges")
				.header("Authorization", "Bearer " + accessToken)
				.param("user.id", "1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));
	}
}
