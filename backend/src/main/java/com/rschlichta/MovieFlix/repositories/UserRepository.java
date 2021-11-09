package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rschlichta.MovieFlix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
