package org.writeo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.writeo.dao.dto.VolumesDTO;
import org.writeo.service.VolumesService;

import java.util.List;

@RestController
@RequestMapping("/volumes")
public class VolumesController {
    @Autowired
    private  VolumesService volumeService;

    @GetMapping
    public ResponseEntity<List<VolumesDTO>> getAllVolumes() {
        List<VolumesDTO> volumes = volumeService.getAllVolumes();
        return ResponseEntity.ok(volumes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolumesDTO> getVolumeById(@PathVariable("id") Long id) {
        VolumesDTO volume = volumeService.getVolumeById(id);
        return ResponseEntity.ok(volume);
    }
    @PostMapping("/createvolume/{novelId}")
    public ResponseEntity<VolumesDTO> createVolumeForNovel(
            @PathVariable("novelId") Long novelId,
            @RequestBody VolumesDTO volumeDTO) {
        VolumesDTO createdVolume = volumeService.createVolumeForNovel(novelId, volumeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVolume);
    }
//    @PostMapping
//    public ResponseEntity<VolumesDTO> createVolume(@RequestBody VolumesDTO volumeDTO) {
//        VolumesDTO createdVolume = volumeService.createVolume(volumeDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdVolume);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolume(@PathVariable("id") Long id) {
        volumeService.deleteVolume(id);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("/{volumeId}/addChapters")
//    public ResponseEntity<VolumesDTO> addChaptersToVolume(
//            @PathVariable("volumeId") Long volumeId,
//            @RequestBody List<Long> chapterIds) {
//        VolumesDTO updatedVolume = volumeService.addChaptersToVolume(volumeId, chapterIds);
//        return ResponseEntity.ok(updatedVolume);
//    }
}
