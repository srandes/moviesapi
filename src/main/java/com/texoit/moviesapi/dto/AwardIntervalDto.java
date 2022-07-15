package com.texoit.moviesapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AwardIntervalDto {
  private List<ProducerDto> min;
  private List<ProducerDto> max;    
}
