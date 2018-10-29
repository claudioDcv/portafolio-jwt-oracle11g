package cl.safe.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.safe.entity.TrabajadorEntity;

public interface TrabajadorRepository  extends CrudRepository<TrabajadorEntity, Long>{
	TrabajadorEntity findByEmail(String email);
	
	@Query("SELECT t FROM TrabajadorEntity t WHERE t.empresaEntity= ?1")
	TrabajadorEntity getByEmpresaEntity(Long id);
}
