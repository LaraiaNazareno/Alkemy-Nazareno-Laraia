package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter

@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String image;

    private Long age;

    private Long weight;

    private String history;

    @ManyToMany(mappedBy = "characters",cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

}
