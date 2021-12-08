package com.alkemychallenge.alkemynazarenolaraia.dto;


import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {

        private  Long id;
        private  String name;
        private  String image;
        private Long age;
        private Long weight;
        private String history;
        private List<MovieDTO> movies;

}
