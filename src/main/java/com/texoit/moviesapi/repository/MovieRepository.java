package com.texoit.moviesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.texoit.moviesapi.domain.Movie;



public interface MovieRepository extends JpaRepository<Movie, Long> { 
  List<Movie> findByProducersAndWinnerTrue(String producer);
  @Query("SELECT DISTINCT m.producers FROM Movie m WHERE m.winner=1")  
  List<String> distinctProducers();    
}
