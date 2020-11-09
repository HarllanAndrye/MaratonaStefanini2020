package br.com.stefanini.maratonadev.dto;

import java.util.List;

public class PaginationDto {

	private Pagination pagination;
	
	private List<CarroDto> carros;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Long totalItems, Integer currentPage, Integer itemsPerPage) {
		this.pagination = new Pagination(totalItems, currentPage, itemsPerPage);
	}

	public List<CarroDto> getCarros() {
		return carros;
	}

	public void setCarros(List<CarroDto> carros) {
		this.carros = carros;
	}




	public class Pagination {
		private Long totalItems;
		private Integer currentPage;
		private Integer itemsPerPage;
		
		public Pagination(Long totalItems, Integer currentPage, Integer itemsPerPage) {
			super();
			this.totalItems = totalItems;
			this.currentPage = currentPage;
			this.itemsPerPage = itemsPerPage;
		}
		public Long getTotalItems() {
			return totalItems;
		}
		public void setTotalItems(Long totalItems) {
			this.totalItems = totalItems;
		}
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public Integer getItemsPerPage() {
			return itemsPerPage;
		}
		public void setItemsPerPage(Integer itemsPerPage) {
			this.itemsPerPage = itemsPerPage;
		}
	}
	
}
