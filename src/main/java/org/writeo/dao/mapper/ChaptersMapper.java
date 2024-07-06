package org.writeo.dao.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.writeo.dao.model.Chapters;
import org.writeo.dao.dto.ChaptersDTO;

@Component
public class ChaptersMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ChaptersMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ChaptersDTO entityToDto(Chapters entity) {
        return modelMapper.map(entity, ChaptersDTO.class);
    }

    public Chapters dtoToEntity(ChaptersDTO dto) {
        return modelMapper.map(dto, Chapters.class);
    }

    public void updateEntityFromDto(ChaptersDTO dto, Chapters entity) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getContent() != null) {
            entity.setContent(dto.getContent());
            entity.updateWordCount(); // Update word count when content changes
        }
        // Add more fields to update as needed
    }
}
