package org.writeo.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.writeo.dao.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {

    @Query("SELECT c FROM Characters c WHERE REPLACE(LOWER(c.name), ' ', '') LIKE LOWER(CONCAT('%', REPLACE(:name, ' ', ''), '%'))")
    List<Characters> findByNameContainingIgnoreSpaces(String name);

    @Query("SELECT c FROM Characters c WHERE REPLACE(LOWER(c.description), ' ', '') LIKE LOWER(CONCAT('%', REPLACE(:description, ' ', ''), '%'))")
    List<Characters> findByDescriptionContainingIgnoreSpaces(String description);
}
