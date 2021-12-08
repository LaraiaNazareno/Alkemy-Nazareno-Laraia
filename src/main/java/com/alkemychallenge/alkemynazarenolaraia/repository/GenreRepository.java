package com.alkemychallenge.alkemynazarenolaraia.repository;


import com.alkemychallenge.alkemynazarenolaraia.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {



}
