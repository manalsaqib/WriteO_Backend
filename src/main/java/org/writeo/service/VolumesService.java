package org.writeo.service;

import org.writeo.dao.dto.VolumesDTO;

import java.util.List;

public interface VolumesService {
    VolumesDTO createVolumeForNovel( Long novelId,VolumesDTO volumeDTO);
    VolumesDTO updateVolume(VolumesDTO volumeDTO);
    void deleteVolume(Long volumeId);
    VolumesDTO getVolumeById(Long volumeId);
    List<VolumesDTO> getAllVolumes();
   // void addChaptersToVolume(Long volumeId, List<Long> chapterIds);
   //VolumesDTO addChaptersToVolume(Long volumeId, List<Long> chapterIds);
}
