package cl.safe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.safe.entity.ExamenEntity;

@Repository
public interface ExamenRepository extends CrudRepository<ExamenEntity, Long> {

}
