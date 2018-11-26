package cl.safe.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginacionRequestDto {
	@NotEmpty Long pageNumber;
	@NotEmpty Long pageSize;
	@NotEmpty Date fromDate;
	@NotEmpty Date toDate;
}
