package org.writeo.service.impl;

import org.writeo.dao.mapper.ChaptersMapper;
import org.writeo.dao.model.Chapters;
import org.writeo.dao.model.Novels; // Import Novel entity
import org.writeo.dao.repository.ChaptersRepository;
import org.writeo.dao.repository.NovelsRepository; // Import NovelRepository
import org.writeo.dao.dto.ChaptersDTO;
import org.writeo.service.ChaptersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.writeo.utils.exceps.ChapterNotFoundException;
import org.writeo.utils.exceps.InvalidChapterDataException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChaptersServiceImpl implements ChaptersService {

    @Autowired
    private ChaptersRepository chaptersRepository;

    @Autowired
    private ChaptersMapper chaptersMapper;

    @Autowired
    private NovelsRepository novelsRepository; // Inject NovelRepository

    @Override
    public List<ChaptersDTO> getAllChapters() {
        return chaptersRepository.findAll()
                .stream()
                .map(chaptersMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChaptersDTO getChapterById(Long id) {
        Optional<Chapters> optionalChapter = chaptersRepository.findById(id);
        return optionalChapter.map(chaptersMapper::entityToDto).orElse(null);
    }

    @Override
    @Transactional
    public ChaptersDTO createChapter(ChaptersDTO chapterDTO) {
        Long novelId = chapterDTO.getNovelId();
        String content = chapterDTO.getContent();

        if (novelId == null || content == null || content.trim().isEmpty()) {
            throw new InvalidChapterDataException("Novel ID and non-empty content must be provided to create a chapter.");
        }

        Optional<Novels> optionalNovel = novelsRepository.findById(novelId);
        if (optionalNovel.isEmpty()) {
            throw new ChapterNotFoundException("Novel not found with id: " + novelId);
        }
        Novels novel = optionalNovel.get();

        Chapters chapter = chaptersMapper.dtoToEntity(chapterDTO);
        chapter.setNovel(novel);

        updateWordCount(chapterDTO);
        chapter.setWordCount(chapterDTO.getWordCount());

        chapter = chaptersRepository.save(chapter);
        return chaptersMapper.entityToDto(chapter);
    }

    @Override
    @Transactional
    public ChaptersDTO updateChapter(ChaptersDTO updatedChapterDTO) {
        Long chapterId = updatedChapterDTO.getChapterId();
        Optional<Chapters> optionalChapter = chaptersRepository.findById(chapterId);

        if (optionalChapter.isPresent()) {
            Chapters chapterToUpdate = optionalChapter.get();
            chaptersMapper.updateEntityFromDto(updatedChapterDTO, chapterToUpdate);

            // Check if content has changed
            if (updatedChapterDTO.getContent() != null && !updatedChapterDTO.getContent().equals(chapterToUpdate.getContent())) {
                chapterToUpdate.setContent(updatedChapterDTO.getContent());

                // Recalculate word count
                updateWordCount(updatedChapterDTO);
                chapterToUpdate.setWordCount(updatedChapterDTO.getWordCount()); // Update word count in entity
            }

            // Save the updated entity
            Chapters updatedChapter = chaptersRepository.save(chapterToUpdate);
            return chaptersMapper.entityToDto(updatedChapter);
        } else {
            throw new ChapterNotFoundException("Chapter not found with id: " + chapterId);
        }


    }
    @Override
    public boolean deleteChapter(Long id) {
        if (chaptersRepository.existsById(id)) {
            chaptersRepository.deleteById(id);
            return true;
        }
        throw new ChapterNotFoundException("Chapter not found with id: " + id);
    }

    @Override
    public void updateWordCount(ChaptersDTO chapterDTO) {
        if (chapterDTO.getContent() != null && !chapterDTO.getContent().trim().isEmpty()) {
            String[] words = chapterDTO.getContent().trim().split("\\s+");
            chapterDTO.setWordCount(words.length);
        } else {
            chapterDTO.setWordCount(0);
        }
    }
    @Override
    public List<ChaptersDTO> findChaptersByNovelId(Long novelId) {
        return chaptersRepository.findByNovelId(novelId).stream()
                .map(chaptersMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChaptersDTO> findChaptersByName(String name) {
        return chaptersRepository.findByName(name).stream()
                .map(chaptersMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ChaptersDTO> getAllChaptersSortedByNameAsc() {
        List<Chapters> chapters = chaptersRepository.findAllOrderByNameAsc();
        return chapters.stream()
                .map(chaptersMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChaptersDTO> getAllChaptersSortedByNameDesc() {
        List<Chapters> chapters = chaptersRepository.findAllOrderByNameDesc();
        return chapters.stream()
                .map(chaptersMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
