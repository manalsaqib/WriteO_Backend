package org.writeo.web;

import org.writeo.dao.dto.ChaptersDTO;
import org.writeo.service.ChaptersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChaptersController {

    @Autowired
    private ChaptersService chaptersService;

    @GetMapping("/")
    public ResponseEntity<List<ChaptersDTO>> getAllChapters() {
        List<ChaptersDTO> chapters = chaptersService.getAllChapters();
        return ResponseEntity.ok(chapters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChaptersDTO> getChapterById(@PathVariable Long id) {
        ChaptersDTO chapter = chaptersService.getChapterById(id);
        if (chapter != null) {
            return ResponseEntity.ok(chapter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/novels/{novelId}")
    public ResponseEntity<ChaptersDTO> createChapter(@PathVariable Long novelId, @RequestBody ChaptersDTO chapterDTO) {
        chapterDTO.setNovelId(novelId); // Set the novelId from URL path into DTO

        ChaptersDTO createdChapter = chaptersService.createChapter(chapterDTO);
        return new ResponseEntity<>(createdChapter, HttpStatus.CREATED);
    }
    @PutMapping("/{novelId}/{chapterId}")
    public ResponseEntity<ChaptersDTO> updateChapter(
            @PathVariable Long novelId,
            @PathVariable Long chapterId,
            @RequestBody ChaptersDTO updatedChapterDTO) {

        // Ensure novelId and chapterId are set in updatedChapterDTO
        updatedChapterDTO.setNovelId(novelId);
        updatedChapterDTO.setChapterId(chapterId);

        // Call service layer to update the chapter
        ChaptersDTO updatedChapter = chaptersService.updateChapter(updatedChapterDTO);
        if (updatedChapter != null) {
            return ResponseEntity.ok(updatedChapter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        boolean deleted = chaptersService.deleteChapter(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/novel/{novelId}")
    public ResponseEntity<List<ChaptersDTO>> getChaptersByNovelId(@PathVariable Long novelId) {
        List<ChaptersDTO> chapters = chaptersService.findChaptersByNovelId(novelId);
        return ResponseEntity.ok(chapters);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ChaptersDTO>> searchChaptersByName(@RequestParam String name) {
        List<ChaptersDTO> chapters = chaptersService.findChaptersByName(name);
        return ResponseEntity.ok(chapters);
    }

    @GetMapping("/sorted")
    public List<ChaptersDTO> getAllChaptersSorted(
            @RequestParam(defaultValue = "asc") String order) {
        if (order.equalsIgnoreCase("desc")) {
            return chaptersService.getAllChaptersSortedByNameDesc();
        }
        return chaptersService.getAllChaptersSortedByNameAsc();
    }
}
