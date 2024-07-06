package org.writeo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.writeo.dao.mapper.VolumesMapper;
import org.writeo.dao.model.Chapters;
import org.writeo.dao.model.Novels;
import org.writeo.dao.model.Volumes;
import org.writeo.dao.dto.VolumesDTO;
import org.writeo.dao.repository.NovelsRepository;
import org.writeo.dao.repository.VolumesRepository;
import org.writeo.service.VolumesService;
import org.writeo.service.VolumesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolumesServiceImpl implements VolumesService {

@Autowired
    private VolumesRepository volumesRepository;
@Autowired
    private  VolumesMapper volumesMapper;
    @Autowired
    private NovelsRepository novelsRepository;

//    @Override
//    @Transactional
//    public VolumesDTO createVolume(VolumesDTO volumeDTO) {
//        Volumes volume = volumesMapper.dtoToEntity(volumeDTO);
//        Volumes savedVolume = volumesRepository.save(volume);
//        return volumesMapper.entityToDto(savedVolume);
//    }


//@Override
//@Transactional
//public VolumesDTO createVolumeForNovel(Long novelId, VolumesDTO volumeDTO) {
//    Novels novel = novelsRepository.findById(novelId)
//            .orElseThrow(() -> new IllegalArgumentException("Novel not found with id: " + novelId));
//
//    Volumes volume = volumesMapper.dtoToEntity(volumeDTO);
//    volume.setNovel(novel); // Associate volume with the fetched novel
//    Volumes savedVolume = volumesRepository.save(volume);
//    return volumesMapper.entityToDto(savedVolume);
//}
@Override
@Transactional
public VolumesDTO createVolumeForNovel(Long novelId, VolumesDTO volumeDTO) {
    Novels novel = novelsRepository.findById(novelId)
            .orElseThrow(() -> new IllegalArgumentException("Novel not found with id: " + novelId));

    Volumes volume = volumesMapper.dtoToEntity(volumeDTO);
    volume.setNovel(novel);
    Volumes savedVolume = volumesRepository.save(volume);

    // Update totalVolumes in Novels entity
    novel.incrementTotalVolumes();
    novelsRepository.save(novel);

    return volumesMapper.entityToDto(savedVolume);
}


    @Override
    @Transactional
    public VolumesDTO updateVolume(VolumesDTO volumeDTO) {
        Volumes volume = volumesMapper.dtoToEntity(volumeDTO);
        Volumes updatedVolume = volumesRepository.save(volume);
        return volumesMapper.entityToDto(updatedVolume);
    }

    @Override
    @Transactional
    public void deleteVolume(Long volumeId) {
        volumesRepository.deleteById(volumeId);
    }

    @Override
    @Transactional(readOnly = true)
    public VolumesDTO getVolumeById(Long volumeId) {
        Volumes volume = volumesRepository.findById(volumeId)
                .orElseThrow(() -> new RuntimeException("Volume not found with id: " + volumeId));
        return volumesMapper.entityToDto(volume);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VolumesDTO> getAllVolumes() {
        List<Volumes> volumes = volumesRepository.findAll();
        return volumes.stream()
                .map(volumesMapper::entityToDto)
                .collect(Collectors.toList());
    }

    // You can implement addChaptersToVolume method here as previously discussed
//    @Override
//    @Transactional
//    public void addChaptersToVolume(Long volumeId, List<Long> chapterIds) {
//        Volumes volume = volumesRepository.findById(volumeId)
//                .orElseThrow(() -> new RuntimeException("Volume not found with id: " + volumeId));
//
//        List<Chapters> chaptersToAdd = chapterIds.stream()
//                .map(chapterId -> chaptersRepository.findById(chapterId)
//                        .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId)))
//                .collect(Collectors.toList());
//
//        volume.getChapters().addAll(chaptersToAdd);
//        volumesRepository.save(volume);
//    }
}
