package org.easymetrics.easymetrics.aggregate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.easymetrics.easymetrics.aggregate.DefaultAggregateWorker;
import org.easymetrics.easymetrics.engine.DefaultMetricsEngine;
import org.easymetrics.easymetrics.engine.DefaultMetricsTimer;
import org.easymetrics.easymetrics.engine.MetricsTimer;
import org.easymetrics.easymetrics.model.Bucket;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DefaultAggregateWorkerTest {

	private DefaultMetricsEngine		metricsEngine;

	private DefaultAggregateWorker	aggregateWorker;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		metricsEngine = new DefaultMetricsEngine();
		metricsEngine.setCollectMetrics(true);

		aggregateWorker = new DefaultAggregateWorker();
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
	}

/*	*//**
	 * 
	 *//*
	@Test
	public void testInitialRanges() {
		aggregateWorker.flushAggregationMap();
		Collection<MetricsTimer> timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 14));
		timerList.add(createMetricsTimer(metricsEngine, 15));
		boolean valuable = aggregateWorker.addMeasurement(timerList);
		Assert.assertTrue("Initial timers should be valuable", valuable);
	}

	*//**
	 * 
	 *//*
	@Test
	public void testInsideRanges() {
		aggregateWorker.flushAggregationMap();
		Collection<MetricsTimer> timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 14));
		timerList.add(createMetricsTimer(metricsEngine, 15));
		aggregateWorker.checkMetricsTimers(timerList);
		timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 13));
		boolean valuable = aggregateWorker.addMeasurement(timerList);
		Assert.assertFalse("Inside ramge should be dropped", valuable);
	}

	*//**
	 * 
	 *//*
	@Test
	public void testOutsideRanges() {
		aggregateWorker.flushAggregationMap();
		Collection<MetricsTimer> timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 14));
		timerList.add(createMetricsTimer(metricsEngine, 15));
		aggregateWorker.checkMetricsTimers(timerList);
		timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 16));
		boolean valuable = aggregateWorker.addMeasurement(timerList);
		Assert.assertTrue("Outside ramge should be kept", valuable);
	}

	*//**
	 * 
	 *//*
	@Test
	public void testTopCountRanges() {
		aggregateWorker.flushAggregationMap();
		Collection<MetricsTimer> timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 13));
		timerList.add(createMetricsTimer(metricsEngine, 15));
		aggregateWorker.checkMetricsTimers(timerList);
		timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 14));
		boolean valuable = aggregateWorker.addMeasurement(timerList);
		Assert.assertTrue("Top count ramge should be kept", valuable);
	}

	*//**
	 * 
	 *//*
	@Test
	public void testCheckAggregation() {
		aggregateWorker.flushAggregationMap();
		Collection<MetricsTimer> timerList = new ArrayList<MetricsTimer>();
		timerList.add(createMetricsTimer(metricsEngine, 11));
		timerList.add(createMetricsTimer(metricsEngine, 12));
		timerList.add(createMetricsTimer(metricsEngine, 13));
		timerList.add(createMetricsTimer(metricsEngine, 14));
		timerList.add(createMetricsTimer(metricsEngine, 15));
		aggregateWorker.addMeasurement(timerList);
		Map<String, AggregationEntry> aggregationMap = aggregateWorker.getAggregationMap();
		AggregationEntry entry = aggregationMap.get("component" + DefaultAggregateWorker.DELIMITER_AGGREGATION_KEY
				+ "function");
		Assert.assertEquals("Number of timers should be 5", 5, entry.getCount());

		// 10-20 bucket should be index 11 based on default setting
		Bucket bucket = entry.getBucketList().get(11);
		Assert.assertEquals("Bucket start range should be 10", 10, bucket.getStartRange());
		Assert.assertEquals("Number of timers in bucket should be 5", 5, bucket.getCount());
	}

	private DefaultMetricsTimer createMetricsTimer(DefaultMetricsEngine metricsEngine, long duration) {
		DefaultMetricsTimer metricsTimer = new DefaultMetricsTimer(metricsEngine, "component", "function", null);
		metricsTimer.stop();
		metricsTimer.getMeasurement().setDuration(duration);

		return metricsTimer;
	}*/
}
