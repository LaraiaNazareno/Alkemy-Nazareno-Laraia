package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "genre")
@Getter
@Setter

public class genreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;






}
