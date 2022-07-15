package com.texoit.moviesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProducerDto {  
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
