package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter


public class characterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String image;

    private Long age;

    private Long weight;

  //  @ManyToMany(mappedBy = "characters",cascade = CascadeType.ALL)
 //   private List<movieEntity> movies = new ArrayList<>();

}
