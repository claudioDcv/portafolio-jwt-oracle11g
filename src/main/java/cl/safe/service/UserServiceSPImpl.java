package cl.safe.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.RegisterRequest;
import cl.safe.entity.UserEntity;
import cl.safe.security.PasswordUtils;

@Service
public class UserServiceSPImpl implements UserServiceSP {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<UserEntity> findAll() {
		return null;
	}

	public UserEntity findById(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_by_id");
		query.setParameter("p_id", id);
		return (UserEntity) query.getSingleResult();
	}
	
	@Override
	public List<UserEntity> getAllUsers() {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_get_all");
        return query.getResultList();
    }

	@Override
	public Long updateSP(UserEntity user) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_update");
		query.setParameter("p_EMAIL", user.getEmail());
		query.setParameter("p_DISPLAY_NAME", user.getName());
		query.setParameter("p_SURNAME", user.getSurname());
		query.setParameter("p_USER_ID", user.getId());
		query.execute();
		return (Long) query.getOutputParameterValue("o_USER_ID");
	}

	@Override
	public Long delete(Long id) {
		Long DEFAULT = (long) 0;
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_delete");
			query.setParameter("p_id", id);
			query.execute();
			return (Long) query.getOutputParameterValue("o_delete_id");
		} catch (Exception e) {
			return DEFAULT;
		}
	}
	
	@Override
	public UserEntity findByEmail(String email) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_by_email");
		query.setParameter("p_email", email);
		return (UserEntity) query.getSingleResult();
	}

	@Override
	public List<UserEntity> usersByProfileId(Long profileId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_get_all_by_profile_id");
		query.setParameter("p_id", profileId);

        ArrayList<UserEntity> usuariosSinContrasena = new ArrayList<>();
        
        for (UserEntity userEntity : (List<UserEntity>)query.getResultList()) {
        	userEntity.setHash("");
        	userEntity.setProfiles(null );
        	usuariosSinContrasena.add(userEntity);
		}
        
        return usuariosSinContrasena;
        
        
        /*
		@StoredProcedureParameter(name="p_EMAIL", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_DISPLAY_NAME", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_PASSWORD", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="o_USER_ID", mode = ParameterMode.OUT, type = Long.class)
        */
	}

	@Override
	public Long saveSP(RegisterRequest user) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_insert");
		query.setParameter("p_EMAIL", user.getEmail());
		query.setParameter("p_DISPLAY_NAME", user.getName());
		query.setParameter("p_LAST_NAME", user.getSurname());
		query.setParameter("p_PASSWORD", PasswordUtils.createHash(user.getPassword()));
		query.execute();
		return (Long) query.getOutputParameterValue("o_USER_ID");
	}

}
