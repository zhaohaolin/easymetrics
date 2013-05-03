package org.easymetrics.easymetrics.server.repository.impl;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.easymetrics.easymetrics.model.Record;
import org.easymetrics.easymetrics.publish.dao.DefaultMetricsDao;
import org.easymetrics.easymetrics.server.repository.MetricsRepository;

public final class DefaultMetricsRepository implements MetricsRepository {

	private DefaultMetricsDao metricsServerDao;

	private JAXBContext context;

	public DefaultMetricsRepository() {
		try {
			context = JAXBContext.newInstance(Record.class);
		} catch (JAXBException e) {
			String error = "Failed to initialize JAXB Context: "
					+ e.getMessage();
			throw new RuntimeException(error, e);
		}
	}

	@Override
	public int saveRecord(String recordStr) {
		Unmarshaller unmarshaller = getUnmarshaller();

		try {
			Record recordObj = (Record) unmarshaller
					.unmarshal(new StringReader(recordStr));
			return saveRecord(recordObj);
		} catch (JAXBException e) {
			String error = "Failed to unmarshall Metrics Record: "
					+ e.getMessage();
			throw new RuntimeException(error, e);// TODO Exception type
		}
	}

	private int saveRecord(Record record) {
		return metricsServerDao.saveRecord(record);
	}

	private Unmarshaller getUnmarshaller() {
		try {
			return context.createUnmarshaller();
		} catch (JAXBException e) {
			String error = "Failed to initialize JAXB Unmarshaller: "
					+ e.getMessage();
			throw new RuntimeException(error, e);// TODO Exception type
		}
	}

}