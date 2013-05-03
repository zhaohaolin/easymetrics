package org.easymetrics.easymetrics.engine;

import org.easymetrics.easymetrics.MetricsCollectorFactory;
import org.easymetrics.easymetrics.engine.MetricsCollector;
import org.easymetrics.easymetrics.engine.MetricsTimer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MetricsCollectorTestCase {

	private MetricsCollector	metricsCollector;

	@Before
	public void setUp() throws Exception {
		metricsCollector = MetricsCollectorFactory.getMetricsCollector(MetricsCollectorTestCase.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartMetricsTimer() {
		String functionName = "function1";
		MetricsTimer metricsTimer = metricsCollector.startMetricsTimer(functionName);

		//Assert.assertEquals(functionName, metricsTimer.get);
	}

}
