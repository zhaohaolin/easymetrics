package org.easymetrics.easymetrics.aggregate;

import java.util.List;

public interface JmxAggregationMBean {
	/**
	 * @return
	 */
	public List<String> queryDistributionList();

	/**
	 * @param key
	 * @return
	 */
	public String queryDistributeDetail(String key);

	/**
	 * 
	 */
	public void resetAllCounts();

	/**
	 * @param key
	 * @return
	 */
	public boolean resetCount(String key);
}
