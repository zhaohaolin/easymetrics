package org.easymetrics.easymetrics.server.service.impl;

import java.util.Date;
import java.util.List;

import org.easymetrics.easymetrics.model.Aggregation;
import org.easymetrics.easymetrics.model.Bucket;
import org.easymetrics.easymetrics.model.Measurement;
import org.easymetrics.easymetrics.model.NameValue;
import org.easymetrics.easymetrics.model.Record;
import org.easymetrics.easymetrics.server.dao.MetricsQueryDao;
import org.easymetrics.easymetrics.server.service.MetricsService;

public class MetricsServiceImpl implements MetricsService {

	private MetricsQueryDao metricsQueryDao;

	@Override
	public List<String> getDistinctHostName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDistinctFunctionNameInMeasurement(
			String componentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDistinctFunctionNameInAggregation(
			String componentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDistinctComponentNameInMeasurement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDistinctComponentNameInAggregation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getFilteredRecords(String host, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getLatestRecords(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aggregation> getFilteredAggregations(String component,
			String function, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aggregation> getLatestAggregations(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aggregation> getAggregationsByRecordId(String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getFilteredMeasurements(String component,
			String function, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getLatestMeasurements(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getMeasurementsByAggregationInfo(
			String aggregationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getMeasurementsByHost(Record hostInfo, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getMeasurementsByRecordId(String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getGroupMeasurements(String measurementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bucket> getBucketsByAggregationId(String aggregationId) {
		return metricsQueryDao.getBucketsByAggregationId(aggregationId);
	}

	@Override
	public List<NameValue> getMeasurementAttributes(String measurementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getMeasurementById(String measurementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getRecordById(String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getRangesByAggregationId(String aggregationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> getMeasurementsByHost(Record hostInfo,
			Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getDistinctHost() {
		return metricsQueryDao.getDistinctHost();
	}

	@Override
	public List<Aggregation> getAggregationsByHost(Record hostInfo, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aggregation> getAggregationsByHost(Record hostInfo,
			Date startDate, Date endDate) {
		return metricsQueryDao.getAggregationsByHost(hostInfo, startDate,
				endDate);
	}

	public void setMetricsQueryDao(MetricsQueryDao metricsQueryDao) {
		this.metricsQueryDao = metricsQueryDao;
	}

}
