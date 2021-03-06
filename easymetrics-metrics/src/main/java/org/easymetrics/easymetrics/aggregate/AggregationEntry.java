/**
 * 
 */
package org.easymetrics.easymetrics.aggregate;

import java.util.Iterator;
import java.util.List;

import org.easymetrics.easymetrics.model.Aggregation;
import org.easymetrics.easymetrics.model.Bucket;
import org.easymetrics.easymetrics.util.MetricsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * metrics aggregation per call.
 * 
 * @author Administrator
 * 
 */
public class AggregationEntry extends Aggregation {

	private static final long		serialVersionUID	= 1L;

	private static final Logger		LOGGER				= LoggerFactory.getLogger(AggregationEntry.class);

	private double					total;

	private double					unitTotal;

	private final TopDurationArray	topTotalDurations;

	private final TopDurationArray	topUnitDurations;

	public AggregationEntry(final String componentName, final String functionName, final Long[] ranges, final int topCount) {
		this.setComponentName(componentName);
		this.setFunctionName(functionName);

		List<Bucket> bucketList = this.getBucketList();
		for (int index = ranges.length - 1; index >= 0; index--) {
			Bucket bucket = new Bucket();
			bucket.setStartRange(ranges[index]);
			bucketList.add(bucket);
		}
		topTotalDurations = new TopDurationArray(topCount);
		topUnitDurations = new TopDurationArray(topCount);
	}

	public void prepare() {
		this.setId(MetricsUtil.createGuid());
		if (this.getCount() > 0) {
			this.setAverage(total / this.getCount());
			this.setUnitAverage(unitTotal / this.getCount());
		}
	}

	/**
	 * 
	 * @param duration
	 * @param workUnits
	 * @return
	 */
	final boolean addMeasurement(final long duration, final int workUnits) {
		boolean valuable = false;
		long durationPerUnit = duration / workUnits;
		if (this.getCount() == 0) {
			// initialize the aggregation
			this.setMaximum(duration);
			this.setMinimum(duration);
			this.setUnitMaximum(durationPerUnit);
			this.setUnitMinimum(durationPerUnit);
			topTotalDurations.check(duration);
			topUnitDurations.check(durationPerUnit);
			valuable = true;
		} else {
			if (topTotalDurations.check(duration)) {
				valuable = true;
				if (duration > this.getMaximum()) {
					this.setMaximum(duration);
				}
				if (duration < this.getMinimum()) {
					this.setMinimum(duration);
				}
			}
			if (topUnitDurations.check(durationPerUnit)) {
				valuable = true;
				if (durationPerUnit > this.getUnitMaximum()) {
					this.setUnitMaximum(durationPerUnit);
				}
			}
			if (durationPerUnit < this.getUnitMinimum()) {
				this.setUnitMinimum(durationPerUnit);
			}
		}

		// add to total duration for calculating average later
		total += duration;
		unitTotal += durationPerUnit;
		this.setCount(this.getCount() + 1);

		// add to distribution buckets with total duration count
		for (Bucket bucket : this.getBucketList()) {
			// bucket are in descending order
			if (duration >= bucket.getStartRange()) {
				bucket.setCount(bucket.getCount() + 1);
				break;
			}
		}

		// add to distribution buckets with unit duration count
		for (Bucket bucket : this.getBucketList()) {
			// bucket are in descending order
			if (durationPerUnit >= bucket.getStartRange()) {
				bucket.setUnitCount(bucket.getUnitCount() + 1);
				break;
			}
		}

		return valuable;
	}

	/**
	 * Add an aggregation to this aggregation.
	 * <p>
	 * It is caller's responsibility to handle concurrency.
	 * 
	 * @param aggregation
	 */
	public final void addAggregation(AggregationEntry aggregation) {

		// update boundary
		if (aggregation.getMaximum() > this.getMaximum()) {
			this.setMaximum(aggregation.getMaximum());
		}
		if (aggregation.getMinimum() > this.getMinimum()) {
			this.setMinimum(aggregation.getMinimum());
		}
		if (aggregation.getUnitMaximum() > this.getUnitMaximum()) {
			this.setUnitMaximum(aggregation.getUnitMaximum());
		}
		if (aggregation.getUnitMinimum() > this.getUnitMinimum()) {
			this.setUnitMinimum(aggregation.getUnitMinimum());
		}

		// update average and count
		long allCount = aggregation.getCount() + this.getCount();
		if (allCount > 0) {
			this.setAverage(aggregation.getAverage() * (aggregation.getCount() / (double) allCount) + this.getAverage() * (this.getCount() / (double) allCount));
		}
		this.setCount(this.getCount() + aggregation.getCount());
		this.total += aggregation.total;
		if (allCount > 0) {
			this.setUnitAverage(aggregation.getUnitAverage() * (aggregation.getCount() / (double) allCount) + this.getUnitAverage()
					* (this.getCount() / (double) allCount));
		}
		this.unitTotal += aggregation.unitTotal;

		// update buckets
		if (this.getBucketList().size() != aggregation.getBucketList().size()) {
			LOGGER.warn("Aggregation ranges changed from " + this.getBucketList().size() + " to " + aggregation.getBucketList().size());
		} else {
			Iterator<Bucket> iterator = aggregation.getBucketList().iterator();
			for (Bucket bucket : this.getBucketList()) {
				Bucket sourceBucket = iterator.next();
				bucket.setCount(bucket.getCount() + sourceBucket.getCount());
				bucket.setUnitCount(bucket.getUnitCount() + sourceBucket.getUnitCount());
			}
		}

		// update time
		long duration;
		if (aggregation.getStartTime().before(this.getStartTime())) {
			duration = this.getDuration() + MetricsUtil.millisToSeconds(this.getStartTime().getTime() - aggregation.getStartTime().getTime());
			this.setStartTime(aggregation.getStartTime());
			if (duration > aggregation.getDuration()) {
				this.setDuration(duration);
			} else {
				this.setDuration(aggregation.getDuration());
			}

		} else {
			duration = aggregation.getDuration() + MetricsUtil.millisToSeconds(aggregation.getStartTime().getTime() - this.getStartTime().getTime());
			if (duration > this.getDuration()) {
				this.setDuration(duration);
			}
		}

	}

	/**
	 * For holding the top list of durations in the aggregation.
	 * 
	 * @author Administrator
	 * 
	 */
	class TopDurationArray {
		private int				count	= 0;
		private final long[]	dataArray;

		public TopDurationArray(int size) {
			dataArray = new long[size];
		}

		public boolean check(long duration) {
			// check if the first one
			if (count == 0) {
				dataArray[0] = duration;
				count++;
				return true;
			} else {
				// check duration in array
				for (int i = 0; i < count; i++) {
					if (duration > dataArray[i]) {
						// insert duration into the array
						int start = (count == dataArray.length) ? dataArray.length - 1 : count;
						for (int j = start; j > i; j--) {
							dataArray[j] = dataArray[j - 1];
						}
						dataArray[i] = duration;
						if (count < dataArray.length) {
							count++;
						}
						return true;
					}
				}
			}
			return false;
		}
	}

}
