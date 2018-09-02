package br.com.collegesmaster.integration;

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

import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitSelects extends JUnitConfiguration {
	
	public void insertInstitute() {
		final InstituteImpl institute = new InstituteImpl();
		institute.setCity("RECIFE");
		institute.setCountry("BRASIL");
		institute.setName("IFPE");
		institute.setState("PE");
		em.persist(institute);
	}
	
	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200()
	  throws Exception {
	 
		insertInstitute();
		String accessToken = obtainAccessToken("test", "secret");
		
	    mvc.perform(get("institutes/list/").header("Authorization", "Bearer " + accessToken)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].name", is("IFPE")));
	}
	
//	@Test
//	public void test01_getInstitutes() {
//		
//		queryBuilder
//				.append("SELECT institute ")
//				.append("FROM   InstituteImpl institute ")
//				.append("ORDER  BY institute.name");
//		
//		final String findAll = queryBuilder.toString();
//		logger.info("Proccessing test 01: " + findAll);
//        
//		final TypedQuery<InstituteImpl> query = em.getEntityManager().createQuery(findAll, InstituteImpl.class);
//
//        final List<InstituteImpl> institutes = query.getResultList();
//
//        assertEquals(2, institutes.size());
//	}
//	
//	@Test
//	public void test02_getDisciplines() {
//		queryBuilder
//				.append("SELECT discipline ")
//				.append("FROM   DisciplineImpl discipline ")
//				.append("ORDER  BY discipline.name");			
//		
//		final String findAll = queryBuilder.toString();
//		logger.info("Proccessing test 02: " + findAll);
//		
//		final TypedQuery<DisciplineImpl> query = em.getEntityManager().createQuery(
//        		findAll,
//                DisciplineImpl.class);
//        
//        final List<DisciplineImpl> disciplines = query.getResultList();
//
//        assertEquals(18, disciplines.size());
//	}
//	
//	@Test
//	public void test04_getTotalOfChallenges() {
//
//		queryBuilder
//				.append("SELECT COUNT(c) ")
//				.append("FROM   ChallengeImpl c");			
//		
//		final String totalOfChallenges = queryBuilder.toString();
//		logger.info("Proccessing test 05: " + totalOfChallenges);
//		
//		final Query query = em.getEntityManager().createQuery(queryBuilder.toString());		       
//        
//        final Long total = (Long)query.getSingleResult();       
//
//        assertEquals(Long.valueOf(6), total);
//      
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void test05_sortUserFirstName() {
//
//		queryBuilder
//				.append("SELECT   user.cpf, ")
//				.append("		  user.firstName ")
//				.append("FROM     UserImpl user ")
//				.append("ORDER BY user.firstName");
//		
//		final String listByName = queryBuilder.toString();
//		logger.info("Proccessing test 05: " + listByName);
//		
//		final Query query = em.getEntityManager().createQuery(queryBuilder.toString());
//		
//		final List<String[]> users = query.getResultList();
//		
//		assertEquals(4, users.size());
//
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void test06_getChallenges() {
//
//		queryBuilder
//				.append("FROM ChallengeImpl c");			
//		
//		final String totalAttachments = queryBuilder.toString();
//		logger.info("Proccessing test 07: " + totalAttachments);
//		
//		final Query query = em.getEntityManager().createQuery(queryBuilder.toString());		       
//        
//		final List<ChallengeImpl> result =  query.getResultList();
//		assertEquals(result.size(), 6);
//	}
//	
//	@Test
//	public void test07_login() {
//
//		final String username = "diogo.brito";
//		final String password = "D10g0!";
//		
//		final String salt = getUserSalt(username);        
//		final User user = buildLogin(username, password, salt);
//		
//		assertEquals("User logged!", username, user.getUsername());		
//	}
//
//	private String getUserSalt(final String username) {
//
//		queryBuilder
//				.append("SELECT user.salt ")
//				.append("FROM UserImpl user where user.username = :username");				
//		
//		final Query query = em.getEntityManager().createQuery(queryBuilder.toString());		
//        query.setParameter("username", username);
//        
//        final String salt = (String) query.getSingleResult();
//		return salt;
//	}
//
//	private User buildLogin(final String username, final String password, final String salt) {
//		final PasswordEncoderWithSalt encoder = new PasswordEncoderWithSalt();
//		final String hashedPassword = encoder.generateHashedPassword(password, salt);        	
//		
//		final CriteriaBuilder builder = em.getEntityManager().getCriteriaBuilder();
//		final CriteriaQuery<UserImpl> criteria = builder.createQuery(UserImpl.class);		
//		final Root<UserImpl> userRoot = criteria.from(UserImpl.class);
//		
//		final Predicate usernamePredicate = builder.equal(userRoot.get("username"), username);
//		final Predicate passwordPredicate = builder.equal(userRoot.get("password"), hashedPassword);
//		criteria.where(usernamePredicate, passwordPredicate);
//		final TypedQuery<UserImpl> query = em.getEntityManager().createQuery(criteria);		
//		
//		final User user = query.getSingleResult();
//                
//        return user;
//	}	
}

