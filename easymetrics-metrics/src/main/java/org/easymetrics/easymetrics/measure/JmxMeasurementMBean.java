package org.easymetrics.easymetrics.measure;

import java.util.List;

public interface JmxMeasurementMBean {
	/**
	 * @return
	 */
	public List<String> queryMeasurementList();

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
