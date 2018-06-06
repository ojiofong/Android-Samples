package com.ojiofong.androidsamples.paging.model;

import java.util.List;

public class RepoSearchResponse{
	private int totalCount;
	private boolean incompleteResults;
	private List<RepoItem> items;

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setIncompleteResults(boolean incompleteResults){
		this.incompleteResults = incompleteResults;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public void setItems(List<RepoItem> items){
		this.items = items;
	}

	public List<RepoItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"RepoSearchResponse{" + 
			"total_count = '" + totalCount + '\'' + 
			",incomplete_results = '" + incompleteResults + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}