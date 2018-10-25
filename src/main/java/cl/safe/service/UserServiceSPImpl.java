package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.entity.UserEntity;

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
		query.setParameter("p_DISPLAY_NAME", user.getDisplayName());
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

}
