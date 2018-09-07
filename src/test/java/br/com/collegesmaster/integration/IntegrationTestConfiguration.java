package br.com.collegesmaster.integration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import br.com.collegesmaster.config.ApplicationBoot;

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
	
    @Before
    public void beforeTest() throws Exception {
    	this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
    	          .addFilter(springSecurityFilterChain).build();
    	this.oauthAccessToken = this.obtainAccessToken("test", "secret");
    }
    
    protected String obtainAccessToken(String username, String password) throws Exception {
    	  
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "angular-client");
        params.add("username", username);
        params.add("password", password);
       
        ResultActions result 
          = mvc.perform(post("/oauth/token")
            .params(params)
            .with(httpBasic("angular-client","secret"))
            .accept("application/json;charset=UTF-8"))
          	.andExpect(status().isOk())
          	.andExpect(content().contentType("application/json;charset=UTF-8"));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
        	
    }
}
