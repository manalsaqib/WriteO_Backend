package org.writeo.dao.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.writeo.dao.model.Volumes;
import org.writeo.dao.dto.VolumesDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VolumesMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public VolumesDTO entityToDto(Volumes entity) {
        return modelMapper.map(entity, VolumesDTO.class);
    }

    public Volumes dtoToEntity(VolumesDTO dto) {
        return modelMapper.map(dto, Volumes.class);
    }


}
