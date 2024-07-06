package org.writeo.service;

import org.writeo.dao.dto.CharactersDTO;

import java.util.List;

public interface CharactersService {
    CharactersDTO getCharacterById(Long id);

    List<CharactersDTO> getAllCharacters();

    List<CharactersDTO> findCharactersByName(String name);

    List<CharactersDTO> findCharactersByDescription(String description);
    CharactersDTO createCharacter(CharactersDTO charactersDTO);

    CharactersDTO updateCharacter(Long id, CharactersDTO charactersDTO);

    void deleteCharacter(Long id);
}
