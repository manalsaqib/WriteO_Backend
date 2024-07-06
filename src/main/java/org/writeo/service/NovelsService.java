package org.writeo.service;

import org.springframework.web.multipart.MultipartFile;
import org.writeo.dao.dto.NovelsDTO;
import org.writeo.dao.model.Novels;

import java.io.IOException;
import java.util.List;

public interface NovelsService {
    List<NovelsDTO> findAllByGenre(String genre);
    NovelsDTO getNovelById(Long id);
    List<NovelsDTO> getAllNovels();
    List<NovelsDTO> findNovelsByName(String name);
    //NovelsDTO createNovel(NovelsDTO novelsDTO);
    //NovelsDTO createNovel(NovelsDTO novelsDTO);
   // List<NovelsDTO> findNovelsByNameContaining(String name);
    NovelsDTO updateNovel(Long id, NovelsDTO novelsDTO);
    void deleteNovel(Long id);
    Novels saveNovel(Novels novel);
    boolean markNovelAsCompleted(Long novelId);
   // byte[] processFileUpload(MultipartFile file) throws IOException;
    NovelsDTO createNovel(NovelsDTO novelsDTO, MultipartFile titlePic) throws IOException;

    List<NovelsDTO> getAllNovelsSortedByIdAsc();
    List<NovelsDTO> getAllNovelsSortedByIdDesc();

    int getTotalVolumesByNovelId(Long novelId);
}
