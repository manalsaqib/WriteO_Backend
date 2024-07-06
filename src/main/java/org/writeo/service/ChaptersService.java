package org.writeo.service;

import org.writeo.dao.dto.ChaptersDTO;

import java.util.List;

public interface ChaptersService {

    List<ChaptersDTO> getAllChapters();

    ChaptersDTO getChapterById(Long id);

    ChaptersDTO createChapter(ChaptersDTO chapterDTO);
    List<ChaptersDTO> findChaptersByNovelId(Long novelId);
    ChaptersDTO updateChapter( ChaptersDTO updatedChapterDTO);

    boolean deleteChapter(Long id);
    void updateWordCount(ChaptersDTO chapterDTO);
    List<ChaptersDTO> findChaptersByName(String name);
    List<ChaptersDTO> getAllChaptersSortedByNameAsc();
    List<ChaptersDTO> getAllChaptersSortedByNameDesc();
}
