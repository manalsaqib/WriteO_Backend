package org.writeo.service.impl;

import org.springframework.web.multipart.MultipartFile;
import org.writeo.dao.mapper.NovelsMapper;
import org.writeo.dao.model.Novels;
import org.writeo.dao.repository.NovelsRepository;
import org.writeo.dao.dto.NovelsDTO;
import org.writeo.service.NovelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.writeo.utils.exceps.NovelAlreadyExistsException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class NovelsServiceImpl implements NovelsService {

    @Autowired
    private NovelsRepository novelsRepository; //interact with data base

    @Autowired
    private NovelsMapper novelsMapper; //converts novel entities to novel dto

    @Override
    public NovelsDTO getNovelById(Long id) {
        Novels novels = novelsRepository.findById(id).orElse(null);
        return novelsMapper.entityToDto(novels);//converts novel entity to dto and returning fetched novel
    }

    @Override
    public List<NovelsDTO> getAllNovels() {
        return novelsRepository.findAll()  //retrieve all data from database
                .stream()
                .map(novelsMapper::entityToDto)//convert novels into dto novel
                .collect(Collectors.toList()); //in the form of dtos return
    }


    //    @Override
//    public byte[] processFileUpload(MultipartFile file) throws IOException {
//        return file.getBytes();
//    }
//    public NovelsDTO createNovel(NovelsDTO novelsDTO) {
//        // Check if novel ID already exists
//        // Check if novel name already exists
//        if (novelsRepository.existsByName(novelsDTO.getName())) {
//            throw new NovelAlreadyExistsException("Novel with name " + novelsDTO.getName() + " already exists.");
//        }
//
//        // Convert DTO to entity and save //when we need to save data we convert dto to entity so
//        //        //user inputs as dto convert it into entity to save in data base
//        //        //retrieving data from database uses entity takes data from data base and
//        //        //make it less complex.
//
//        Novels novel = novelsMapper.dtoToEntity(novelsDTO);
//        novel = novelsRepository.save(novel);
//        return novelsMapper.entityToDto(novel); // only relevant data is shown to user
//    }
    @Override
    public NovelsDTO createNovel(NovelsDTO novelsDTO, MultipartFile titlePic) throws IOException {
        // Check if novel name already exists
        if (novelsRepository.existsByName(novelsDTO.getName())) {
            throw new NovelAlreadyExistsException("Novel with name " + novelsDTO.getName() + " already exists.");
        }

        Novels novel = novelsMapper.dtoToEntity(novelsDTO);

        // Handle titlePic if provided
        if (titlePic != null && !titlePic.isEmpty()) {
            byte[] picBytes = titlePic.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(picBytes);
            novel.setTitlePic(base64Encoded);
        }

        novel = novelsRepository.save(novel);
        return novelsMapper.entityToDto(novel);
    }


    @Override
    public List<NovelsDTO> findNovelsByName(String name) {
        List<Novels> novels = novelsRepository.findByName(name);
        return novels.stream()
                .map(novelsMapper::entityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<NovelsDTO> findAllByGenre(String genre) {
        List<Novels> novels = novelsRepository.findAllByGenre(genre);
        return novels.stream()
                .map(novelsMapper::entityToDto)
                .collect(Collectors.toList());
    }
//    @Override
//    public NovelsDTO createNovel(NovelsDTO novelsDTO) {
//        //when we need to save data we convert dto to entity so
//        //user inputs as dto convert it into entity to save in data base
//        //retrieving data from database uses entity takes data from data base and
//        //make it less complex.
//        Novels novels = novelsMapper.dtoToEntity(novelsDTO);
//        novels = novelsRepository.save(novels);
//        return novelsMapper.entityToDto(novels);//only relevant data is shown to user
//    }
//@Override
//public NovelsDTO createNovel(NovelsDTO novelsDTO) {
//    if (novelsRepository.existsByName(novelsDTO.getName())) {
//        throw new NovelAlreadyExistsException("Novel with name " + novelsDTO.getName() + " already exists.");
//    }
//
//    Novels novel = novelsMapper.dtoToEntity(novelsDTO);
//    if (titlePic != null && !titlePic.isEmpty()) {
//        try {
//            novel.setTitlePic(titlePic.getBytes()); // Convert MultipartFile to byte array
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to read titlePic file", e);
//        }
//    }
//
//    novel = novelsRepository.save(novel);
//    return novelsMapper.entityToDto(novel);
//}

//    @Override
//    public NovelsDTO createNovel(NovelsDTO novelsDTO) {
//        if (novelsRepository.existsByName(novelsDTO.getName())) {
//            throw new NovelAlreadyExistsException("Novel with name " + novelsDTO.getName() + " already exists.");
//        }
//
//        Novels novel = novelsMapper.dtoToEntity(novelsDTO);
//
//        // Handle titlePic if provided
//        if (titlePic != null && !titlePic.isEmpty()) {
//            try {
//                novel.setTitlePic(titlePic.getBytes()); // Store file content as byte array
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to read titlePic file", e);
//            }
//        }
//
//        novel = novelsRepository.save(novel);
//        return novelsMapper.entityToDto(novel);
//    }

    @Override
    public NovelsDTO updateNovel(Long id, NovelsDTO novelsDTO) {
        Novels existingNovels = novelsRepository.findById(id).orElse(null); //retrieve from data base existing
        if (existingNovels != null) {
            novelsMapper.existingEntityToNewEntity(novelsDTO, existingNovels); //maps from novels to exisyingdto
            existingNovels = novelsRepository.save(existingNovels); //save updated novel to data base
            return novelsMapper.entityToDto(existingNovels); //return updated novel
        }
        return null;
    }

    @Override
    public void deleteNovel(Long id) {
        novelsRepository.deleteById(id);
    }

    public Novels saveNovel(Novels novel) {
        return novelsRepository.save(novel);
    }

//    public Novels markNovelAsCompleted(Long novelId) {
//        Optional<Novels> optionalNovel = novelsRepository.findById(novelId);
//        if (optionalNovel.isPresent()) {
//            Novels novel = optionalNovel.get();
//            novel.setCompletionStatus("Completed");
//            novel.setEndDate(LocalDate.now());
//            return novelsRepository.save(novel);
//        } else {
//            throw new RuntimeException("Novel not found with id: " + novelId);
//        }
//
//    }
public boolean markNovelAsCompleted(Long novelId) {
    Optional<Novels> optionalNovel = novelsRepository.findById(novelId);
    if (optionalNovel.isPresent()) {
        Novels novel = optionalNovel.get();
        novel.setCompletionStatus("Completed");
        novel.setEndDate(LocalDate.now());
        novelsRepository.save(novel);
        return true;
    } else {
        return false;
    }
}
    @Override
    public List<NovelsDTO> getAllNovelsSortedByIdAsc() {
        List<Novels> novels = novelsRepository.findAllNovelsOrderByIdAsc();
        return novels.stream()
                .map(novelsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NovelsDTO> getAllNovelsSortedByIdDesc() {
        List<Novels> novels = novelsRepository.findAllNovelsOrderByIdDesc();
        return novels.stream()
                .map(novelsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalVolumesByNovelId(Long novelId) {
        return novelsRepository.countTotalVolumesByNovelId(novelId);
    }
}




