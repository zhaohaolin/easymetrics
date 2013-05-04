package org.easymetrics.easymetrics.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymetrics.easymetrics.model.Aggregation;
import org.easymetrics.easymetrics.model.Bucket;
import org.easymetrics.easymetrics.model.Record;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * For saving performance measurements and aggregations into database.
 * 
 */
public class MetricsQueryDao {

	private static final String LOAD_METRICS_BUCKET_BY_AGGRE = "SELECT START_RANGE, COUNT, UNIT_COUNT FROM METRICS_AGGREGATION_BUCKET WHERE AGGREGATION_ID = ? ORDER BY START_RANGE";

	private static final String LOAD_DISTINCT_HOST = "SELECT DISTINCT null,SERVICE_GROUP,DOMAIN,HOST_NAME,SERVICE,APPLICATION_VERSION,HOST_USER,INSTANCE_PID, null FROM METRICS_RECORD";

	private static final String LOAD_AGGREGATIONS_BY_HOST = "SELECT AGG.AGGREGATION_ID, AGG.COMPONENT_NAME, AGG.FUNCTION_NAME, "
			+ "AGG.START_TIME, AGG.DURATION, AGG.MAXIMUM, AGG.MINIMUM, AGG.AVERAGE, "
			+ "AGG.UNIT_MAXIMUM, AGG.UNIT_MINIMUM, AGG.UNIT_AVERAGE, AGG.COUNT, REC.AGGREGATION_RANGES"
			+ "FROM METRICS_AGGREGATION AGG"
			+ "LEFT JOIN METRICS_RECORD REC ON REC.RECORD_ID = AGG.RECORD_ID"
			+ "WHERE REC.SERVICE_GROUP = ?"
			+ "AND REC.DOMAIN = ?"
			+ "AND REC.HOST_NAME = ?"
			+ "AND REC.SERVICE = ?"
			+ "AND REC.APPLICATION_VERSION = ?"
			+ "AND REC.HOST_USER = ?"
			+ "AND REC.INSTANCE_PID = ?"
			+ "AND AGG.START_TIME >= ? AND AGG.START_TIME <= ?";

	private JdbcTemplate jdbcTemplate;

	public List<Bucket> getBucketsByAggregationId(String aggregationId) {
		List<Bucket> result = jdbcTemplate.queryForList(
				LOAD_METRICS_BUCKET_BY_AGGRE, new Object[] { aggregationId },
				Bucket.class);
		return result == null ? new ArrayList<Bucket>() : result;
	}

	public List<Record> getDistinctHost() {
		List<Record> result = jdbcTemplate.queryForList(LOAD_DISTINCT_HOST,
				Record.class);
		return result == null ? new ArrayList<Record>() : result;
	}

	public List<Aggregation> getAggregationsByHost(Record hostInfo,
			Date startDate, Date endDate) {
		List<Aggregation> result = jdbcTemplate.queryForList(
				LOAD_AGGREGATIONS_BY_HOST,
				new Object[] { hostInfo.getServiceGroup(),
						hostInfo.getDomain(), hostInfo.getHost(),
						hostInfo.getService(), hostInfo.getVersion(),
						hostInfo.getUser(), hostInfo.getPid(), startDate,
						endDate }, Aggregation.class);
		return result == null ? new ArrayList<Aggregation>() : result;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
