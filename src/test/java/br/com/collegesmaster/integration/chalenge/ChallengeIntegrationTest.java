package br.com.collegesmaster.integration.chalenge;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.querydsl.core.BooleanBuilder;

import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QChallengeImpl;
import br.com.collegesmaster.challenge.model.repository.ChallengeRepository;
import br.com.collegesmaster.integration.IntegrationTestConfiguration;
import br.com.collegesmaster.security.model.entity.Professor;
import br.com.collegesmaster.security.model.entity.impl.ProfessorImpl;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChallengeIntegrationTest extends IntegrationTestConfiguration {
	
	@Before
	public void before() throws Exception {
		Set<String> privileges = new HashSet<>();
		privileges.add("READ_CHALLENGE");
		super.oauthAccessToken = super.obtainAccessToken("ADMINISTRATOR", privileges);
	}
	
	@MockBean
	private ChallengeRepository challengeRepository;
	
	@Test
	public void test001_givenChallenges_whenProfessor_thenOk() throws Exception {
		
		Professor challengeOwner = new ProfessorImpl();
		challengeOwner.setId(1);
		
		ChallengeImpl challenge = new ChallengeImpl();
		challenge.setId(1);
		challenge.setOwner(challengeOwner);
		
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder();
		booleanBuilderQuery.and(QChallengeImpl.challengeImpl.owner.username.eq("test"));
		
		Page<ChallengeImpl> challengePage = new PageImpl<ChallengeImpl>(Arrays.asList(challenge));
		Pageable page = PageRequest.of(0, 20);
		
		given(this.challengeRepository.findAll(booleanBuilderQuery.getValue(), page)).willReturn(challengePage);
		
		mvc.perform(get("/challenges")
				.header("Authorization", "Bearer " + super.oauthAccessToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(10)));
	}
}
