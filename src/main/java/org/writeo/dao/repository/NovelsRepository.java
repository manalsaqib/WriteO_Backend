package org.writeo.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.writeo.dao.model.Novels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelsRepository extends JpaRepository<Novels, Long> {
    @Query(value = "SELECT * FROM Novels WHERE genre = :genre", nativeQuery = true)
    List<Novels> findAllByGenre(String genre);

    //@Query("SELECT n FROM Novels n WHERE LOWER(n.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    //@Query("SELECT n FROM Novels n WHERE LOWER(REPLACE(n.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:name, ' ', ''), '%'))")
   // @Query("SELECT n FROM Novels n WHERE LOWER(REPLACE(n.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:name, ' ', ''), '%'))")
   // @Query("SELECT n FROM Novels n WHERE LOWER(n.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    @Query("SELECT n FROM Novels n WHERE LOWER(REPLACE(n.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:name, ' ', ''), '%'))")
    List<Novels> findByName(@Param("name") String name);
    @Query("SELECT n FROM Novels n ORDER BY n.name ASC")
    List<Novels> findAllNovelsOrderByIdAsc();

    @Query("SELECT n FROM Novels n ORDER BY n.name DESC")
    List<Novels> findAllNovelsOrderByIdDesc();
    boolean existsByName(String name);
    // Custom query to count total volumes for a specific novel
    @Query("SELECT COUNT(v) FROM Volumes v WHERE v.novel.id = :novelId")
    int countTotalVolumesByNovelId(Long novelId);

}
