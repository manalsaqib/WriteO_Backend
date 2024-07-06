package org.writeo.web;

import org.springframework.web.multipart.MultipartFile;
import org.writeo.dao.dto.NovelsDTO;
import org.writeo.dao.model.Novels;
import org.writeo.service.NovelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.writeo.utils.exceps.NovelAlreadyExistsException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/novels")
public class NovelController {

    @Autowired
    private NovelsService novelsService;

    @GetMapping("/{id}")
    public ResponseEntity<NovelsDTO> getNovelById(@PathVariable Long id) {
        NovelsDTO novel = novelsService.getNovelById(id);
        if (novel != null) {
            return ResponseEntity.ok(novel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<NovelsDTO>> getAllNovels() {
        List<NovelsDTO> novels = novelsService.getAllNovels();
        return ResponseEntity.ok(novels);
    }

    @GetMapping("/search")
    public ResponseEntity<List<NovelsDTO>> searchNovelsByName(@RequestParam("name") String name) {
        List<NovelsDTO> novels = novelsService.findNovelsByName(name);
        if (!novels.isEmpty()) {
            return ResponseEntity.ok(novels);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<NovelsDTO>> getAllNovelsByGenre(@PathVariable("genre") String genre) {
        List<NovelsDTO> novels = novelsService.findAllByGenre(genre);
        return ResponseEntity.ok().body(novels);
    }
//    public ResponseEntity<?> createNovel(@RequestBody NovelsDTO novelsDTO) {
//        try {
//            NovelsDTO createdNovel = novelsService.createNovel(novelsDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdNovel);
//        } catch (NovelAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }
@PostMapping
public ResponseEntity<NovelsDTO> createNovel(
        @RequestParam("name") String name,
        @RequestParam("description") String description,
      //  @RequestParam("totalVolumes") Integer totalVolumes,
        @RequestParam("titlePic") MultipartFile titlePic,
        @RequestParam("genre") String genre
      //  @RequestParam("startDate") String startDate,
       // @RequestParam("endDate") String endDate,
       // @RequestParam("completionStatus") String completionStatus
        // Add more parameters as needed
) throws IOException {
    NovelsDTO novelsDTO = new NovelsDTO();
    novelsDTO.setName(name);
    novelsDTO.setDescription(description);
   // novelsDTO.setTotalVolumes(totalVolumes);
    novelsDTO.setGenre(genre);
   // novelsDTO.setStartDate(startDate);
    //novelsDTO.setEndDate(endDate);
   // novelsDTO.setCompletionStatus(completionStatus);

    // Create novel with Base64 encoded image
    NovelsDTO createdNovel = novelsService.createNovel(novelsDTO, titlePic);

    return new ResponseEntity<>(createdNovel, HttpStatus.CREATED);
}
//public ResponseEntity<NovelsDTO> createNovel(@RequestParam("titlePic") MultipartFile titlePic,
//                                             @RequestParam("name") String name,
//                                             @RequestParam("description") String description,
//                                             @RequestParam("totalVolumes") int totalVolumes,
//                                             @RequestParam("genre") String genre) {
//    NovelsDTO novelsDTO = new NovelsDTO();
//    novelsDTO.setName(name);
//    novelsDTO.setDescription(description);
//    novelsDTO.setTotalVolumes(totalVolumes);
//    novelsDTO.setGenre(genre);
//
//    try {
//        NovelsDTO createdNovel = novelsService.createNovel(novelsDTO, titlePic);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdNovel);
//    } catch (NovelAlreadyExistsException e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//    }
//}
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            // Process the file
//            String fileName = file.getOriginalFilename();
//            byte[] bytes = file.getBytes();
//            long fileSize = file.getSize(); // Get size in bytes
//
//            // You can save the file, process it, or return details in the response
//            // Example response with file details
//            return ResponseEntity.ok().body("File uploaded successfully. Details: " +
//                    "Name: " + fileName + ", Size: " + fileSize + " bytes");
//        } catch (Exception e) {
//            // Handle exceptions
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to upload file: " + e.getMessage());
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<NovelsDTO> updateNovel(@PathVariable Long id, @RequestBody NovelsDTO novelsDTO) {
        NovelsDTO updatedNovel = novelsService.updateNovel(id, novelsDTO);
        if (updatedNovel != null) {
            return ResponseEntity.ok(updatedNovel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovel(@PathVariable Long id) {
        novelsService.deleteNovel(id);
        return ResponseEntity.noContent().build();
    }



//    @PutMapping("/{id}/complete")
//    public Novels completeNovel(@PathVariable Long id) {
//        return novelsService.markNovelAsCompleted(id);
//    }
@PutMapping("/{id}/complete")
public ResponseEntity<String> completeNovel(@PathVariable Long id) {
    boolean isCompleted = novelsService.markNovelAsCompleted(id);
    if (isCompleted) {
        return ResponseEntity.ok("Novel has been completed.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Novel not found with id: " + id);
    }
}

    @GetMapping("/sorted")
    public List<NovelsDTO> getAllNovelsSorted(@RequestParam String order) {
        if (order.equalsIgnoreCase("asc")) {
            return novelsService.getAllNovelsSortedByIdAsc();
        } else if (order.equalsIgnoreCase("desc")) {
            return novelsService.getAllNovelsSortedByIdDesc();
        } else {
            throw new IllegalArgumentException("Invalid order parameter. Use 'asc' or 'desc'.");
        }
    }
    @GetMapping("/{novelId}/totalVolumes")
    public int getTotalVolumesByNovelId(@PathVariable Long novelId) {
        return novelsService.getTotalVolumesByNovelId(novelId);
    }
}
