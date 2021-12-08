package com.alkemychallenge.alkemynazarenolaraia.repository;


import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> , JpaSpecificationExecutor<Character> {

    List<Character> findAll(Specification<Character> spec);
}
