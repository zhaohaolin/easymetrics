package org.easymetrics.easymetrics.server.service;

import java.util.Date;
import java.util.List;

import org.easymetrics.easymetrics.model.Aggregation;
import org.easymetrics.easymetrics.model.Bucket;
import org.easymetrics.easymetrics.model.Measurement;
import org.easymetrics.easymetrics.model.NameValue;
import org.easymetrics.easymetrics.model.Record;

public interface MetricsService {

	List<String> getDistinctHostName();

	List<String> getDistinctFunctionNameInMeasurement(String componentName);

	List<String> getDistinctFunctionNameInAggregation(String componentName);

	List<String> getDistinctComponentNameInMeasurement();

	List<String> getDistinctComponentNameInAggregation();

	List<Record> getFilteredRecords(String host, Date startDate, Date endDate);

	List<Record> getLatestRecords(int count);

	List<Aggregation> getFilteredAggregations(String component,
			String function, Date startDate, Date endDate);

	List<Aggregation> getLatestAggregations(int count);

	List<Aggregation> getAggregationsByRecordId(String recordId);

	List<Measurement> getFilteredMeasurements(String component,
			String function, Date startDate, Date endDate);

	List<Measurement> getLatestMeasurements(int count);

	List<Measurement> getMeasurementsByAggregationInfo(String aggregationId);

	List<Measurement> getMeasurementsByHost(Record hostInfo, int count);

	List<Measurement> getMeasurementsByRecordId(String recordId);

	List<Measurement> getGroupMeasurements(String measurementId);

	List<Bucket> getBucketsByAggregationId(String aggregationId);

	List<NameValue> getMeasurementAttributes(String measurementId);

	List<Measurement> getMeasurementById(String measurementId);

	List<Record> getRecordById(String recordId);

	List<Record> getRangesByAggregationId(String aggregationId);

	List<Measurement> getMeasurementsByHost(Record hostInfo, Date startTime,
			Date endTime);

	List<Record> getDistinctHost();

	List<Aggregation> getAggregationsByHost(Record hostInfo, int count);

	List<Aggregation> getAggregationsByHost(Record hostInfo, Date startDate,
			Date endDate);
}
