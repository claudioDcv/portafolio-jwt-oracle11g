package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
