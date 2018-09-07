package br.com.collegesmaster.security.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;
import br.com.collegesmaster.security.model.repository.UserRepository;
import br.com.collegesmaster.security.model.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
	}
	
	@Transactional
	@Override
	public User create(final User user) {
		return userRepository.save((UserImpl) user);
	}

	@PreAuthorize("hasAuthority('STUDENT', 'PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public User update(final User user) {
		return userRepository.save((UserImpl) user);
	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Boolean deleteById(final Integer id) {
		userRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@PreAuthorize("hasAuthority('STUDENT', 'PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public User findById(final Integer id) {
		return userRepository
				.findById(id)
				.orElse(null);
	}
	
	@Transactional
	@Override
	public Boolean existsByCpf(final String cpf) {
		return userRepository.existsByCpf(cpf);
	}

	@Transactional
	@Override
	public Boolean existsByUsername(final String username) {
		return userRepository.existsByUsername(username);
	}

	@Transactional
	@Override
	public Boolean existsByEmail(final String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Transactional
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
