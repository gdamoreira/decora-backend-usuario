package br.com.damoreira.util;

import java.io.Serializable;
import java.util.List;

public class SearchResult<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 5645314259555445987L;

	private Integer total;
	private List<T> results;

	public SearchResult(Integer total, List<T> results) {
		this.total = total;
		this.results = results;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		SearchResult<T> other = (SearchResult<T>) obj;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchResult [total=" + total + ", results=" + results + "]";
	}

}
