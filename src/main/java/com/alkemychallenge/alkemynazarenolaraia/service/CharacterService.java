package com.alkemychallenge.alkemynazarenolaraia.service;


import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save (CharacterDTO dto);

    List<CharacterBasicDTO> getBasicALL();

    CharacterDTO update (Long id, CharacterDTO characterDTO);

    CharacterDTO getDetailsById(Long id);

    void delete(Long id);

    Character getEntityById(Long id);

    List<CharacterDTO> getByFilters(String name, Long age,Long weight ,Set<Long> movies);
}
