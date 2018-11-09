package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.entity.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Profile> getProfileByUserId(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("profile_by_user_id");
		query.setParameter("P_ID", id);
        return query.getResultList();
	}
	
	@Override
	public List<Profile> getProfileUserByUserId(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("profile_user_by_user_id");
		query.setParameter("P_ID", id);
        return query.getResultList();
	}
	
	@Override
	public List<Profile> getAllProfiles() {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("profiles_get_all");
        return query.getResultList();
    }

	@Override
	public Long asignarPerfilesConUsuarioId(List<Long> perfiles, Long usuarioId) {
		System.out.println("----- ELIMINAR TODOS LOS PERFILES DE USUARIO --------");
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("us_prof_delete_all_by_us");

		System.out.println(perfiles);
		query.setParameter("p_id", usuarioId);
		query.execute();
		
		for (Long perfil : perfiles) {
			System.out.println("----- AGREGANDO PERFIL A USUARIO --------");
			StoredProcedureQuery queryPerfiles = em.createNamedStoredProcedureQuery("USER_PROFILES_INSERT");
			queryPerfiles.setParameter("p_id", usuarioId);
			queryPerfiles.setParameter("p_profile", perfil);
			queryPerfiles.execute();
		}

		return (Long) query.getOutputParameterValue("O_ID");
	}

}
