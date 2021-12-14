package com.rschlichta.MovieFlix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rschlichta.MovieFlix.entities.User;
import com.rschlichta.MovieFlix.repositories.UserRepository;
import com.rschlichta.MovieFlix.services.exceptions.ForbiddenException;
import com.rschlichta.MovieFlix.services.exceptions.UnAuthorizedException;

@Service
public class AuthService {

  @Autowired
  private UserRepository repository;

  public User authenticated() {
    try {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      return repository.findByEmail(username);
    } catch (Exception e) {
      throw new UnAuthorizedException("Invalid User");
    }
  }

  public void validateSelfOrAdminAndMember(Long userId) {
    User user = authenticated();
    if(!user.getId().equals(userId) && (!user.hasHole("ROLE_ADMIN") || !user.hasHole("ROLE_MEMBER"))){
      throw new ForbiddenException("Access denied");
    }
  }
}
