package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "genre")
@Getter
@Setter

public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;



}
