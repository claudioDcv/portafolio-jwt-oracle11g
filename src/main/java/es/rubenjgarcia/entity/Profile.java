package es.rubenjgarcia.entity;

import java.io.Serializable;

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
