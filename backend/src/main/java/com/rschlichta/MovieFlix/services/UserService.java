package com.rschlichta.MovieFlix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rschlichta.MovieFlix.dto.RoleDTO;
import com.rschlichta.MovieFlix.dto.UserDTO;
import com.rschlichta.MovieFlix.dto.UserInsertDTO;
import com.rschlichta.MovieFlix.dto.UserUpdateDTO;
import com.rschlichta.MovieFlix.entities.Role;
import com.rschlichta.MovieFlix.entities.User;
import com.rschlichta.MovieFlix.repositories.RoleRepository;
import com.rschlichta.MovieFlix.repositories.UserRepository;
import com.rschlichta.MovieFlix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(UserUpdateDTO dto, Long id) {
		try {			
			User user = repository.getOne(id);
			copyDtoToEntity(dto, user);
			user = repository.save(user);
			return new UserDTO(user);
		}
		catch (EntityNotFoundException e){
			throw new ResourceNotFoundException("Id n??o Encontrado");
	}
}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException("Id n??o Encontrado" + id);
		}
		
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {

		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());

		entity.getRoles().clear();
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("Usu??rio n??o Encontrado: " + username);
			throw new UsernameNotFoundException("E-mail n??o Encontrado");
		}
		
		logger.info("Usu??rio Encontrado: " + username);
		
		return user;
	}
	
}
