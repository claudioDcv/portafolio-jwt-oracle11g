package cl.safe.repository;

import org.springframework.stereotype.Repository;

import cl.safe.entity.RiesgoEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface RiesgoRepository extends CrudRepository<RiesgoEntity, Long> {

}
