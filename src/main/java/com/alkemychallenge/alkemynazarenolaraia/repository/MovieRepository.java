package com.alkemychallenge.alkemynazarenolaraia.repository;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> , JpaSpecificationExecutor<Movie> {

    List<Movie> findAll(Specification<Movie> spec);
}
