package com.texoit.moviesapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import com.texoit.moviesapi.dto.AwardIntervalDto;
import com.texoit.moviesapi.service.MovieService;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Movies Resource")
public class MovieController {

    private final MovieService movieService;   
    
    @GetMapping("/award-interval")
    public AwardIntervalDto getAwardInterval() {      
      return movieService.getAwardInterval();
    }       
}
