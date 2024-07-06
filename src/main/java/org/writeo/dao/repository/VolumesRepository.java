package org.writeo.dao.repository;
import org.writeo.dao.model.Volumes;
import org.writeo.dao.model.Volumes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumesRepository extends JpaRepository<Volumes, Long> {

}