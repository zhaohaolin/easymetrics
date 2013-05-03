/**
 * 
 */
package org.easymetrics.easymetrics;

import org.easymetrics.easymetrics.engine.MetricsCollector;
import org.easymetrics.easymetrics.engine.MetricsEngine;
import org.easymetrics.easymetrics.engine.MockMetricsEngine;

/**
 * @author Administrator
 * 
 */
public class MetricsCollectorFactory {

	private static final MetricsCollectorFactory	instance			= new MetricsCollectorFactory();

	private MetricsEngine													metricsEngine	= new MockMetricsEngine();

	public static MetricsCollectorFactory getInstance() {
		return instance;
	}

	public static MetricsCollector getMetricsCollector(final Class<? extends Object> clazz) {
		return getMetricsCollector(clazz.getSimpleName());
	}

	public static MetricsCollector getMetricsCollector(final String componentName) {
		return getInstance().getMetricsEngine().createMetricsCollector(componentName);
	}

	public void setMetricsEngine(MetricsEngine metricsEngine) {
		getInstance().metricsEngine = metricsEngine;
	}

	public MetricsEngine getMetricsEngine() {
		return this.metricsEngine;
	}
}
