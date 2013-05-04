/**
 * 
 */
package org.easymetrics.easymetrics.cglib;

import org.easymetrics.easymetrics.model.annotation.ProxyMetrics;

/**
 * @author Administrator
 * 
 */
public class MetricsHelloWorld {

	@ProxyMetrics
	public Object testHold(String arg1) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return arg1;
	}

	@ProxyMetrics
	Object testHold1(String arg1) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return arg1;
	}
	
	Object testHold2(String arg1) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return arg1;
	}
}
