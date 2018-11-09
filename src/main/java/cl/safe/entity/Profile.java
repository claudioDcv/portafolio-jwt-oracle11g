package cl.safe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "profiles_get_all",
            procedureName = "profiles_get_all",
			resultClasses = Profile.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "profile_by_user_id",
			procedureName = "profile_by_user_id",
			resultClasses= { Profile.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "profile_user_by_user_id",
			procedureName = "profile_user_by_user_id",
			resultClasses= { Profile.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "us_prof_delete_all_by_us",
			procedureName = "us_prof_delete_all_by_us",
			resultClasses= { Profile.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="O_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "USER_PROFILES_INSERT",
			procedureName = "USER_PROFILES_INSERT",
			resultClasses= { Profile.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_profile", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_id", mode = ParameterMode.OUT, type = Long.class)
			}
	)
})
@Entity
@Table(name="PROFILES")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185950103579114010L;
	
	@Id
	@Column(name="PROFILE_ID")
	@Getter @Setter Long id;
	@Column(name="DISPLAY_NAME")
	@Getter @Setter String displayName;
	@Column(name="NATURAL_KEY")
	@Getter @Setter String naturalKey;
}
