package cl.safe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.safe.entity.VisitaMedicaEntity;

@Repository
public interface VisitaMedicaRepository extends CrudRepository<VisitaMedicaEntity, Long> {

}
