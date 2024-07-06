package org.writeo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.writeo.dao.model.Chapters;

import java.util.List;

@Repository
public interface ChaptersRepository extends JpaRepository<Chapters, Long> {
    @Query("SELECT c FROM Chapters c WHERE c.novel.id = :novelId")
    List<Chapters> findByNovelId(Long novelId);

    // Custom query to find chapters by name containing
    @Query("SELECT c FROM Chapters c WHERE LOWER(REPLACE(c.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:name, ' ', ''), '%'))")

    List<Chapters> findByName(String name);
    @Query("SELECT c FROM Chapters c ORDER BY c.name ASC")
    List<Chapters> findAllOrderByNameAsc();

    @Query("SELECT c FROM Chapters c ORDER BY c.name DESC")
    List<Chapters> findAllOrderByNameDesc();
}
