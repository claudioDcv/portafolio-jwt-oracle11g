package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.UserJson;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.Profile;
import cl.safe.entity.UserEntity;
import cl.safe.repository.EmpresaRepository;
import cl.safe.security.PasswordUtils;
import cl.safe.user.UserBuilder;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	EmpresaRepository empresaRepository;

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<EmpresaEntity> getAllSP() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("empresas_get_all");
        return query.getResultList();
	}
	
	@Override
	public Long crearEmpresa(EmpresaEntity empresaEntity) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("EMPRESAS_INSERT");
		query.setParameter("p_nombre", empresaEntity.getNombre());
		query.setParameter("p_direccion", empresaEntity.getDireccion());
		query.setParameter("p_telefono", empresaEntity.getTelefono());
		query.setParameter("p_email", empresaEntity.getEmail());
		return (Long) query.getOutputParameterValue("o_ID");
	  }
	
	public EmpresaEntity findOne(Long id) {
		return empresaRepository.findOne(id);
	}
}
