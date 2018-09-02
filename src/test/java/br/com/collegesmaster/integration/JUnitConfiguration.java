package br.com.collegesmaster.integration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import br.com.collegesmaster.config.ApplicationBoot;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = ApplicationBoot.class)
@AutoConfigureMockMvc
@DataJpaTest
public class JUnitConfiguration {
	
	@Autowired
    private WebApplicationContext wac;
 
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
	    
	@Autowired
    protected MockMvc mvc;
	
	@Autowired
	protected TestEntityManager em;
	
    protected final static Logger logger = Logger.getGlobal();
    protected StringBuilder queryBuilder;
    protected static Validator validator;
    
    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.INFO);
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Before
    public void beforeTest() {
    	queryBuilder = new StringBuilder();
    	this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
    	          .addFilter(springSecurityFilterChain).build();
    }

    public <T> void validateConstraints(final T object) {
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate( object );
        constraintViolations.forEach(violation -> logger.log(Level.SEVERE, violation.getMessage()));
    }
    
    protected String obtainAccessToken(String username, String password) throws Exception {
    	  
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "angular-client");
        params.add("username", username);
        params.add("password", password);
     
        ResultActions result 
          = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/collegesmaster/oauth/token")
            .params(params)
            .with(httpBasic("angular-client","$2a$04$CYFi1SAuhrbu23CZbcfoZ.idF4XNOaNOaMusKybIbrPxplDfDiSZ6"))
            .accept("application/json;charset=UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
     
        String resultString = result.andReturn().getResponse().getContentAsString();
     
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
