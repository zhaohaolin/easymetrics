package org.easymetrics.easymetrics.aggregate;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.easymetrics.easymetrics.model.Aggregation;
import org.easymetrics.easymetrics.model.Bucket;


@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JmxAggregationSum", propOrder = { "aggregation" })
public class JmxAggregationSum {

	@XmlElement(name = "distribution")
	private Aggregation	aggregation;

	public JmxAggregationSum() {

	}

	public JmxAggregationSum(String componentName, String functionName, Date startTime) {
		aggregation = new Aggregation(componentName, functionName);
		aggregation.setStartTime(startTime);
	}

	public Aggregation getAggregation() {
		return aggregation;
	}

	public synchronized void addAggregation(AggregationEntry entry) {
		if (aggregation.getMaximum() < entry.getMaximum()) {
			aggregation.setMaximum(entry.getMaximum());
		}
		if (aggregation.getMinimum() < entry.getMinimum()) {
			aggregation.setMinimum(entry.getMinimum());
		}
		if (aggregation.getUnitMaximum() < entry.getUnitMaximum()) {
			aggregation.setUnitMaximum(entry.getUnitMaximum());
		}
		if (aggregation.getUnitMinimum() < entry.getUnitMinimum()) {
			aggregation.setUnitMinimum(entry.getUnitMinimum());
		}

		double total = (aggregation.getAverage() * aggregation.getCount() + entry.getAverage() * entry.getCount());
		double unitTotal = (aggregation.getUnitAverage() * aggregation.getCount()) + entry.getUnitAverage()
				* entry.getCount();
		long count = aggregation.getCount() + entry.getCount();
		if (count > 0) {
			aggregation.setCount(count);
			aggregation.setAverage(total / count);
			aggregation.setUnitAverage(unitTotal / count);
		}

		for (Bucket source : entry.getBucketList()) {
			for (Bucket target : aggregation.getBucketList()) {
				if (source.getStartRange() == target.getStartRange()) {
					target.setCount(target.getCount() + source.getCount());
					target.setUnitCount(target.getUnitCount() + source.getUnitCount());
					break;
				}
			}
		}
	}
}
