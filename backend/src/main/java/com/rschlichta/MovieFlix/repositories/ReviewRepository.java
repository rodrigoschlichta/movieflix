package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rschlichta.MovieFlix.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
