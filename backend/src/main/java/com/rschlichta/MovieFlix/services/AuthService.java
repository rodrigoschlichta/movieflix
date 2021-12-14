package com.rschlichta.MovieFlix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rschlichta.MovieFlix.entities.User;
import com.rschlichta.MovieFlix.repositories.UserRepository;
import com.rschlichta.MovieFlix.services.exceptions.ForbiddenException;
import com.rschlichta.MovieFlix.services.exceptions.UnAuthorizedException;

@Service
public class AuthService {
  private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

  @Autowired
  private UserRepository repository;

  public User authenticated() {
    LOG.info("method=authenticated, msg=search user authenticated");
    try {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      return repository.findByEmail(username);
    } catch (Exception e) {
      LOG.error("method=authenticated, msg=user authenticated not found");
      throw new UnAuthorizedException("Invalid User");
    }
  }

  public void validateSelfOrAdminAndMember(Long userId) {
    LOG.info("method=validateSelfOrAdminAndMember, msg=validate user {} for role admin or member", userId);
    User user = authenticated();
    if(!user.getId().equals(userId) && (!user.hasHole("ROLE_ADMIN") || !user.hasHole("ROLE_MEMBER"))){
      LOG.error("method=validateSelfOrAdminAndMember, msg=user {} is not an admin or member", userId);
      throw new ForbiddenException("Access denied");
    }
  }
}
