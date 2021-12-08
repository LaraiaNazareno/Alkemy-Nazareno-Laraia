package com.alkemychallenge.alkemynazarenolaraia.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFilterDTO {
        private  String name;
        private Long age;
        private Long weight;
        private Set<Long> movies;


        public CharacterFilterDTO(String name, Long age, Long weight,Set<Long> movies ) {
                this.name = name;
                this.age = age;
                this.movies = movies;
                this.weight = weight;
        }





}
