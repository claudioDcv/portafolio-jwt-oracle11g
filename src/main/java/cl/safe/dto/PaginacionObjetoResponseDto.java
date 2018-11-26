package cl.safe.dto;

import java.util.List;

import javax.persistence.Entity;
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
public class PaginacionObjetoResponseDto<T> {
	
	Long count;
	List<T> list;
	Long perPage;
	Long totalPage;
	Long currentPage;
	
	public void initialized(Long count, Long pageSize, Long pageNumber) {
		
		Float paginasInexacto = (float) count / pageSize;
		Boolean hasExactPage = paginasInexacto % 1 == 0; 
		this.setTotalPage((long) (hasExactPage ? paginasInexacto : paginasInexacto + 1));
		this.setCount(count);
		this.setCurrentPage(pageNumber);
	}
}
