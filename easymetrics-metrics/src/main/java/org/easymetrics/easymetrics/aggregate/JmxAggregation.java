/**
 * 
 */
package org.easymetrics.easymetrics.aggregate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.easymetrics.easymetrics.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Administrator
 * 
 */
public class JmxAggregation implements JmxAggregationMBean {

	private static final Logger														logger		= LoggerFactory.getLogger(JmxAggregation.class);

	private static final String														KEY_JOIN	= ":";

	private ConcurrentHashMap<String, JmxAggregationSum>	dataMap		= new ConcurrentHashMap<String, JmxAggregationSum>();

	@Override
	public List<String> queryDistributionList() {
		List<String> nameList = new ArrayList<String>();

		Set<String> entrySet = dataMap.keySet();
		for (String key : entrySet) {
			nameList.add(key);
		}
		return nameList;
	}

	@Override
	public String queryDistributeDetail(String key) {
		String detail = null;

		JmxAggregationSum sumData = dataMap.get(key);
		if (sumData != null) {
			ByteArrayOutputStream outputStream = null;
			try {
				outputStream = new ByteArrayOutputStream();

				JAXBContext context = JAXBContext.newInstance(JmxAggregationSum.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				marshaller.marshal(sumData, outputStream);
				detail = outputStream.toString();
			} catch (JAXBException e) {
				String message = "Failed to get aggregation XML with error " + e.getMessage();
				logger.warn(message, e);
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException ignore) {
					}
				}
			}
		}
		return detail;
	}

	@Override
	public void resetAllCounts() {
		dataMap.clear();
	}

	@Override
	public boolean resetCount(String key) {
		return dataMap.remove(key) == null ? false : true;
	}

	void addAggregation(AggregationEntry aggregation, Long[] ranges) {
		String key = StringUtils.join(new Object[] { aggregation.getComponentName(), aggregation.getFunctionName() },
				KEY_JOIN);
		JmxAggregationSum sumData = dataMap.get(key);

		if (sumData == null) {
			sumData = new JmxAggregationSum(aggregation.getComponentName(), aggregation.getFunctionName(),
					aggregation.getStartTime());
			List<Bucket> bucketList = sumData.getAggregation().getBucketList();
			for (int index = ranges.length - 1; index >= 0; index--) {
				Bucket bucket = new Bucket();
				bucket.setStartRange(ranges[index]);
				bucketList.add(bucket);
			}

			JmxAggregationSum oldData = dataMap.putIfAbsent(key, sumData);
			if (oldData != null) {
				sumData = oldData;
			}
		}

		sumData.addAggregation(aggregation);
	}

}
