package org.writeo.dao.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.writeo.dao.model.Novels;
import org.writeo.dao.dto.NovelsDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NovelsMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public NovelsDTO entityToDto(Novels entity) {
        return modelMapper.map(entity, NovelsDTO.class);
    }

    public Novels dtoToEntity(NovelsDTO dto) {
        return modelMapper.map(dto, Novels.class);
    }
    public Novels existingEntityToNewEntity(NovelsDTO newDto, Novels existingEntity) {
        if (newDto.getName() != null) {
            existingEntity.setName(newDto.getName());
        }
        if (newDto.getDescription() != null) {
            existingEntity.setDescription(newDto.getDescription());
        }


        return existingEntity;
    }


}
