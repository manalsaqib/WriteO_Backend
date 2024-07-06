package org.writeo.dao.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.writeo.dao.model.Characters;
import org.writeo.dao.dto.CharactersDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CharactersMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public CharactersDTO entityToDto(Characters entity) {
        return modelMapper.map(entity, CharactersDTO.class);
    }

    public Characters dtoToEntity(CharactersDTO dto) {
        return modelMapper.map(dto, Characters.class);
    }
    public void existingEntityToNewEntity(CharactersDTO newDto, Characters existingEntity) {
        if (newDto.getName() != null) {
            existingEntity.setName(newDto.getName());
        }
        if (newDto.getDescription() != null) {
            existingEntity.setDescription(newDto.getDescription());
        }
        // Add more fields to update if needed
    }

}
