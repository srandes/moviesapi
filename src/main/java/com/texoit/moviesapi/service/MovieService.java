package com.texoit.moviesapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.texoit.moviesapi.domain.Movie;
import com.texoit.moviesapi.dto.AwardIntervalDto;
import com.texoit.moviesapi.dto.ProducerDto;
import com.texoit.moviesapi.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

  private final MovieRepository movieRepository;
  private final ResourceLoader resourceLoader; 
  
  public AwardIntervalDto getAwardInterval() {
    List<ProducerDto> listTwoOrMoreAwards = new ArrayList<>();  
    for (String producer: movieRepository.distinctProducers()) {
      List<Movie> movies = movieRepository.findByProducersAndWinnerTrue(producer);
      if (movies.size() > 1) {
        Movie min = movies.stream().min(Comparator.comparingInt(Movie::getYear)).get();
        Movie max = movies.stream().max(Comparator.comparingInt(Movie::getYear)).get(); 
        ProducerDto p = ProducerDto.builder()
          .producer(producer)
          .interval(max.getYear()-min.getYear())
          .previousWin(min.getYear())
          .followingWin(max.getYear())
          .build();
          listTwoOrMoreAwards.add(p);         
      }            
    };
    if (!listTwoOrMoreAwards.isEmpty()) {
      return AwardIntervalDto.builder()
      .min(Arrays.asList(listTwoOrMoreAwards.stream().min(Comparator.comparingInt(ProducerDto::getInterval)).get()))
      .max(Arrays.asList(listTwoOrMoreAwards.stream().max(Comparator.comparingInt(ProducerDto::getInterval)).get()))      
      .build();         
    } else {
      return AwardIntervalDto.builder()
      .min(new ArrayList<>(1))
      .max(new ArrayList<>(1))
      .build();
    } 
  }

  @PostConstruct
  public void init() {
    log.info("initialize database import");
    List<Movie> movies = new ArrayList<>(); 
    Resource resource = resourceLoader.getResource("classpath:movielist.csv");        
    try {
      BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
      CSVParser csvParser = new CSVParser(
        fileReader,
        CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .withIgnoreHeaderCase()
          .withDelimiter(';')
          .withTrim()          
      );      
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();  
      for (CSVRecord csvRecord : csvRecords) {               
        movies.add(
          Movie.builder()
            .year(Integer.parseInt(csvRecord.get("year")))
            .title(csvRecord.get("title"))
            .studios(csvRecord.get("studios"))
            .producers(csvRecord.get("producers"))
            .winner("yes".equals(csvRecord.get("winner")) ? true : false)
            .build()
        );
      }
      movieRepository.saveAll(movies);
      csvParser.close();
      fileReader.close();
      log.info("database initialized with {} records", movies.size());      
    } catch (IOException e) {      
      log.error("Fail to import file to database", e);
    } 
  }
}
