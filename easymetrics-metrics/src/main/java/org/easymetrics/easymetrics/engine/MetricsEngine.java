/**
 * 
 */
package org.easymetrics.easymetrics.engine;


/**
 * @author Administrator
 * 
 */
public interface MetricsEngine {

	/**
	 * Check if collect metrics
	 * 
	 * @return
	 */
	boolean isCollectMetrics();

	/**
	 * Is any exception thrown.
	 * 
	 * @return
	 */
	boolean isThrowException();

	/**
	 * 
	 * @param componentName
	 * @param functionName
	 * @return
	 */
	boolean isFilter(String componentName, String functionName);

	/**
	 * Update metrics filter
	 * 
	 * @param componentName
	 * @param functionName
	 * @param isFilter
	 * @return old value
	 */
	boolean updateFilter(String componentName, String functionName, boolean isFilter);

	/**
	 * Create metrics collector with given component name.
	 * 
	 * @param componentName
	 * @return
	 */
	MetricsCollector createMetricsCollector(String componentName);

}
