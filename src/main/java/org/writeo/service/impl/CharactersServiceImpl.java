package org.writeo.service.impl;

import org.writeo.dao.mapper.CharactersMapper;
import org.writeo.dao.model.Characters;
import org.writeo.dao.repository.CharactersRepository;
import org.writeo.dao.dto.CharactersDTO;
import org.writeo.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private CharactersMapper charactersMapper;

    @Override
    public CharactersDTO getCharacterById(Long id) {
        Characters character = charactersRepository.findById(id).orElse(null);
        return charactersMapper.entityToDto(character);
    }

    @Override
    public List<CharactersDTO> getAllCharacters() {
        return charactersRepository.findAll()
                .stream()
                .map(charactersMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharactersDTO createCharacter(CharactersDTO charactersDTO) {
        Characters character = charactersMapper.dtoToEntity(charactersDTO);
        character = charactersRepository.save(character);
        return charactersMapper.entityToDto(character);
    }

    @Override
    public CharactersDTO updateCharacter(Long id, CharactersDTO charactersDTO) {
        Characters existingCharacter = charactersRepository.findById(id).orElse(null);
        if (existingCharacter != null) {
            charactersMapper.existingEntityToNewEntity(charactersDTO, existingCharacter);
            existingCharacter = charactersRepository.save(existingCharacter);
            return charactersMapper.entityToDto(existingCharacter);
        }
        return null;
    }

    @Override
    public void deleteCharacter(Long id) {
        charactersRepository.deleteById(id);
    }
    @Override
    public List<CharactersDTO> findCharactersByName(String name) {
        List<Characters> characters = charactersRepository.findByNameContainingIgnoreSpaces(name);
        return characters.stream()
                .map(charactersMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharactersDTO> findCharactersByDescription(String description) {
        List<Characters> characters = charactersRepository.findByDescriptionContainingIgnoreSpaces(description);
        return characters.stream()
                .map(charactersMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
