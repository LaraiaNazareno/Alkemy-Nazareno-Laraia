package com.alkemychallenge.alkemynazarenolaraia.controller;

import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;


    @PostMapping
    public ResponseEntity<CharacterDTO> save ( @RequestBody  CharacterDTO dto){

            CharacterDTO characterSaved = this.characterService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);

    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getBasic(){
        List<CharacterBasicDTO> characters = this.characterService.getBasicALL();
        return ResponseEntity.ok().body(characters);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO characterdto) {
        CharacterDTO result = this.characterService.update(id, characterdto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){

        CharacterDTO result =this.characterService.getDetailsById(id);

        return ResponseEntity.ok(result);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        this.characterService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/filter")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(

            @RequestParam (required = false) String name,
            @RequestParam (required = false) Long age,
            @RequestParam (required = false) Long weight,
            @RequestParam(required = false) Set<Long> movies


    ){
        List<CharacterDTO> characters = this.characterService.getByFilters(name,age,weight,movies);
        return ResponseEntity.ok(characters);
    }


}
