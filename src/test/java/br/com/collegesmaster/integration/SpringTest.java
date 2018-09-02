package br.com.collegesmaster.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.collegesmaster.config.ApplicationBoot;
import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;
import br.com.collegesmaster.security.model.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
@DataJpaTest
public class SpringTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindByName_thenReturnEmployee() {
	    // given
		User alex = new UserImpl();
		alex.setPassword("#Falou08");
		alex.setFirstName("Alex");
		alex.setCpf("107.194.984.57");
		alex.setLastName("José");
		alex.setUsername("alex.jose");
		//course
		//roles
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    // when
	    User found = userRepository.findByUsername(alex.getUsername());
	 
	    // then
	    Assert.assertTrue(found.getUsername().equals(alex.getUsername()));
	}
}