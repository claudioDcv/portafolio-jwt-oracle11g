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

import cl.safe.dto.TrabajadorRequestDto;
import cl.safe.entity.TrabajadorEntity;
import cl.safe.repository.TrabajadorRepository;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{
	@Autowired
	TrabajadorRepository trabajadorRepository;
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<TrabajadorEntity> getByEmpresaIdSP(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("TRABAJADORES_BY_EMPRESA_FK");
		query.setParameter("P_EMPRESA_FK", id);
		return query.getResultList();
	}

	@Override
	public List<TrabajadorEntity> findAll() {
		return (List<TrabajadorEntity>) trabajadorRepository.findAll();
	}

	@Override
	public TrabajadorEntity findOne(Long id) {
		return trabajadorRepository.findOne(id);
	}

	@Override
	public TrabajadorEntity findByEmail(String email) {
		return trabajadorRepository.findByEmail(email);
	}

	@Override
	public Long crearTrabajadorSP(TrabajadorRequestDto trabajadorRequestDto) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("trabajador_insert");
		query.setParameter("p_apellido_materno", trabajadorRequestDto.getApellidoMaterno());
		query.setParameter("p_apellido_paterno", trabajadorRequestDto.getApellidoPaterno());
		query.setParameter("p_email", trabajadorRequestDto.getEmail());
		query.setParameter("p_empresa_fk", trabajadorRequestDto.getEmpresa());
		query.setParameter("p_nombre", trabajadorRequestDto.getNombre());
		query.setParameter("p_run", trabajadorRequestDto.getRun());
		query.execute();
		return (Long) query.getOutputParameterValue("o_ID");
	}

	@Override
	public Long asignarRiesgosConTrabajadorId(List<Long> riesgosIds, Long trabajadorId) {
		System.out.println("----- ELIMINAR TODOS LOS riesgos DE trabajador --------");
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("trab_riesgo_del_by_us_id");

		System.out.println(riesgosIds);
		query.setParameter("p_id", trabajadorId);
		query.execute();
		
		for (Long riesgoId : riesgosIds) {
			System.out.println("----- AGREGANDO riesgos A trabajador --------");
			StoredProcedureQuery queryRiesgos = em.createNamedStoredProcedureQuery("trabajador_riesgo_insert");
			queryRiesgos.setParameter("p_riesgo_FK", riesgoId);
			queryRiesgos.setParameter("p_trabajador_fk", trabajadorId);
			queryRiesgos.execute();
		}

		return (Long) query.getOutputParameterValue("O_ID");
	}

	@Override
	public List<TrabajadorEntity> findAllTrabajadoresRiesgoByEmpresaId(Long empresaId) {
		/*
		 * @NamedStoredProcedureQuery(name = "trabajadores_riesgo_all",
		    procedureName = "trabajadores_riesgo_all",
			resultClasses = TrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
				}
			)
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("trabajadores_riesgo_all");
		query.setParameter("p_empresa_fk", empresaId);
		return query.getResultList();
	}
}
