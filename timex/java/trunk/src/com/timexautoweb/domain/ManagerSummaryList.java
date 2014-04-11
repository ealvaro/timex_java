package com.timexautoweb.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alvaro E. Escobar
 */
public class ManagerSummaryList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ManagerSummary> summaries = null;

	public ManagerSummaryList(List<ManagerSummary> summaries) {
		this.summaries = summaries;
	}

	public List<ManagerSummary> getSummaries() {
		return summaries;
	}

	public void setSummaries(List<ManagerSummary> summaries) {
		this.summaries = summaries;
	}

	public int getSize() {
		return summaries.size();
	}

}
