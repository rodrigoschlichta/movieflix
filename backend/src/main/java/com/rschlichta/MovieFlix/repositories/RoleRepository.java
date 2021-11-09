package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rschlichta.MovieFlix.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
