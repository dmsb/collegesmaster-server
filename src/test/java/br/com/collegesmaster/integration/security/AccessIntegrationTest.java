package br.com.collegesmaster.integration.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.collegesmaster.integration.IntegrationTestConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessIntegrationTest extends IntegrationTestConfiguration {

	@Test
	public void test_001_givenWrongToken_whenGetRoles_thenStatus200() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "angular-client");
		params.add("username", "test");
		params.add("password", "wrong_pasword");

		mvc.perform(post("/oauth/token")
			.params(params)
			.with(httpBasic("angular-client", "secret"))
			.accept("application/json;charset=UTF-8"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
}
