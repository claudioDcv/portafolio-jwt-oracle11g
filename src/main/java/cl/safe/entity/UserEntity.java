package cl.safe.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "users_get_all",
            procedureName = "users_get_all",
			resultClasses = UserEntity.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_get_all_by_profile_id",
			procedureName = "users_get_all_by_profile_id",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_by_id",
			procedureName = "users_by_id",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_by_email",
			procedureName = "users_by_email",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_email", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_insert",
			procedureName = "users_insert",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_EMAIL", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_DISPLAY_NAME", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_PASSWORD", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="o_USER_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_delete",
			procedureName = "users_delete",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_delete_id", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_update",
			procedureName = "users_update",
			resultClasses= { UserEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_EMAIL", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_DISPLAY_NAME", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_USER_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_USER_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	)
})
@Entity
@Table(name="USERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -583948100923349227L;

	@Id
	@Column(name="USER_ID")
	@Getter @Setter Long id;
	
	@Column(name="DISPLAY_NAME")
	@Getter @Setter String name;
	
	@Column(name="LASTNAME")
	@Getter @Setter String surname;

	@Getter @Setter String email;
	
	@Column(name="password")
	@Getter @Setter String hash;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
		name = "users_profiles",
		joinColumns = @JoinColumn(name="users_fk", referencedColumnName="user_id"),
		inverseJoinColumns = @JoinColumn(
			name = "profiles_fk",
			referencedColumnName = "profile_id"
		)
	)
	@Getter @Setter List<Profile> profiles;
}
