/**
 * 
 */
package org.easymetrics.easymetrics.engine;

/**
 * @author Administrator
 * 
 */
public class DefaultMetricsCollector implements MetricsCollector {

	private final DefaultMetricsEngine	metricsEngine;

	private final String								componentName;

	public DefaultMetricsCollector(DefaultMetricsEngine metricsEngine, String componentName) {
		this.metricsEngine = metricsEngine;
		this.componentName = componentName;
	}

	@Override
	public MetricsTimer startMetricsTimer(String functionName, String correlationId, boolean startNew) {
		MetricsTimer timer = null;
		try {
			if (!getMetricsEngine().isCollectMetrics()) {
				timer = new MockMetricsTimer(componentName, functionName);
			} else if (getMetricsEngine().isFilter(correlationId, functionName)) {
				timer = new MockMetricsTimer(componentName, functionName);
			} else if (!startNew && getMetricsEngine().getTimerStack().isEmpty()) {
				timer = new MockMetricsTimer(componentName, functionName);
			} else {
				// create the measurement timer
				timer = new DefaultMetricsTimer(getMetricsEngine(), componentName, functionName, correlationId);
			}
		} catch (RuntimeException e) {
			if (getMetricsEngine().isThrowException()) {
				throw e;
			}
		}
		return timer;
	}

	@Override
	public MetricsTimer startMetricsTimer(String functionName, String correlationId) {
		return startMetricsTimer(functionName, correlationId, false);
	}

	@Override
	public MetricsTimer startMetricsTimer(String functionName) {
		return startMetricsTimer(functionName, null, false);
	}

	@Override
	public MetricsTimer startInitialTimer(String functionName, String correlationId) {
		return startMetricsTimer(functionName, correlationId, true);
	}

	@Override
	public MetricsTimer startInitialTimer(String functionName) {
		return startMetricsTimer(functionName, null, true);
	}

	public DefaultMetricsEngine getMetricsEngine() {
		return metricsEngine;
	}

}
