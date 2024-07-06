package org.writeo.web;

import org.writeo.dao.dto.CharactersDTO;
import org.writeo.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDTO> getCharacterById(@PathVariable Long id) {
        CharactersDTO characterDTO = charactersService.getCharacterById(id);
        return ResponseEntity.ok(characterDTO);
    }

    @GetMapping
    public ResponseEntity<List<CharactersDTO>> getAllCharacters() {
        List<CharactersDTO> charactersDTOList = charactersService.getAllCharacters();
        return ResponseEntity.ok(charactersDTOList);
    }

    @PostMapping
    public ResponseEntity<CharactersDTO> createCharacter(@RequestBody CharactersDTO charactersDTO) {
        CharactersDTO createdCharacter = charactersService.createCharacter(charactersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }

    @GetMapping("/search/name")
    public List<CharactersDTO> findCharactersByName(@RequestParam String name) {
        return charactersService.findCharactersByName(name);
    }

    @GetMapping("/search/description")
    public List<CharactersDTO> findCharactersByDescription(@RequestParam String description) {
        return charactersService.findCharactersByDescription(description);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CharactersDTO> updateCharacter(@PathVariable Long id, @RequestBody CharactersDTO charactersDTO) {
        CharactersDTO updatedCharacter = charactersService.updateCharacter(id, charactersDTO);
        if (updatedCharacter != null) {
            return ResponseEntity.ok(updatedCharacter);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        charactersService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}
