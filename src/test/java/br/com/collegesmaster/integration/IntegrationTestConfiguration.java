package br.com.collegesmaster.integration;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import br.com.collegesmaster.config.ApplicationBoot;
import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.PrivilegeImpl;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;
import br.com.collegesmaster.security.model.service.UserService;

@SpringBootTest(classes = ApplicationBoot.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class IntegrationTestConfiguration {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	protected MockMvc mvc;

	protected String oauthAccessToken;

	@MockBean
	private UserService userService;

	@Before
	public void beforeTest() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
	}

	protected String obtainAccessToken(String role, Set<String> privileges) throws Exception {
		
		User loggedUser = this.mockLoggedUser(role, privileges);
		given(userService.loadUserByUsername("test")).willReturn(loggedUser);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "angular-client");
		params.add("username", "test");
		params.add("password", "secret");

		ResultActions result = mvc
				.perform(post("/oauth/token").params(params).with(httpBasic("angular-client", "secret"))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();

	}

	private User mockLoggedUser(String role, Set<String> privileges) {

		Collection<PrivilegeImpl> loggedUserPrivileges = privileges.stream().map(privilegeName -> {

			PrivilegeImpl privilege = new PrivilegeImpl();
			privilege.setName(privilegeName);
			return privilege;
			
		}).collect(Collectors.toList());

		RoleImpl loggedUserRole = new RoleImpl();
		loggedUserRole.setId(1);
		loggedUserRole.setName(role);
		loggedUserRole.setPrivileges(loggedUserPrivileges);

		Collection<RoleImpl> loggedUserRoles = new ArrayList<>();
		loggedUserRoles.add(loggedUserRole);

		User loggedUser = new UserImpl();
		loggedUser.setUsername("test");
		loggedUser.setPassword("$2a$04$CYFi1SAuhrbu23CZbcfoZ.idF4XNOaNOaMusKybIbrPxplDfDiSZ6");
		loggedUser.setRoles(loggedUserRoles);

		return loggedUser;
	}
}
