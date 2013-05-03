/**
 * 
 */
package org.easymetrics.easymetrics.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Administrator
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceUsage", propOrder = { "threadList", "heapList", "collectorList" })
public class ResourceUsage implements Publishable, Serializable {

	private static final long			serialVersionUID	= 1L;

	@XmlTransient
	private String								recordId;

	@XmlAttribute(name = "usageId", required = true)
	private String								usageId;

	/**
	 * cpuCount
	 */
	@XmlAttribute(name = "processors", required = true)
	private int										processorCount;

	@XmlAttribute(name = "threads", required = true)
	private int										threadCount;

	@XmlAttribute(name = "checkTime", required = true)
	private Date									checkTime;

	@XmlAttribute(name = "upTime", required = true)
	private long									upTime;

	@XmlAttribute(name = "cpuTime", required = true)
	private long									cpuTime;

	@XmlAttribute(name = "userTime", required = true)
	private long									userTime;

	@XmlAttribute(name = "heapMax", required = true)
	private long									heapMax;

	@XmlAttribute(name = "heapUsed", required = true)
	private long									heapUsed;

	@XmlAttribute(name = "nonHeapMax", required = true)
	private long									nonHeapMax;

	@XmlAttribute(name = "nonHeapUsed", required = true)
	private long									nonHeapUsed;

	@XmlElement(name = "thread")
	private List<ThreadUsage>			threadList				= new ArrayList<ThreadUsage>();

	@XmlElement(name = "heap")
	private List<HeapUsage>				heapList					= new ArrayList<HeapUsage>();

	@XmlElement(name = "collector")
	private List<CollectorUsage>	collectorList			= new ArrayList<CollectorUsage>();

	@Override
	public String getRecordId() {
		return recordId;
	}

	@Override
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getUsageId() {
		return usageId;
	}

	public void setUsageId(String usageId) {
		this.usageId = usageId;
	}

	public int getProcessorCount() {
		return processorCount;
	}

	public void setProcessorCount(int processorCount) {
		this.processorCount = processorCount;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public long getUpTime() {
		return upTime;
	}

	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}

	public long getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(long cpuTime) {
		this.cpuTime = cpuTime;
	}

	public long getUserTime() {
		return userTime;
	}

	public void setUserTime(long userTime) {
		this.userTime = userTime;
	}

	public long getHeapMax() {
		return heapMax;
	}

	public void setHeapMax(long heapMax) {
		this.heapMax = heapMax;
	}

	public long getHeapUsed() {
		return heapUsed;
	}

	public void setHeapUsed(long heapUsed) {
		this.heapUsed = heapUsed;
	}

	public long getNonHeapMax() {
		return nonHeapMax;
	}

	public void setNonHeapMax(long nonHeapMax) {
		this.nonHeapMax = nonHeapMax;
	}

	public long getNonHeapUsed() {
		return nonHeapUsed;
	}

	public void setNonHeapUsed(long nonHeapUsed) {
		this.nonHeapUsed = nonHeapUsed;
	}

	public List<ThreadUsage> getThreadList() {
		return threadList;
	}

	public void addThreadUsage(ThreadUsage threadUsage) {
		threadUsage.setUsageId(this.usageId);
		threadList.add(threadUsage);
	}

	public List<HeapUsage> getHeapList() {
		return heapList;
	}

	public void addHeapUsage(HeapUsage heapUsage) {
		heapUsage.setUsageId(this.usageId);
		heapList.add(heapUsage);
	}

	public List<CollectorUsage> getCollectorList() {
		return collectorList;
	}

	public void addCollectorUsage(CollectorUsage collectorUsage) {
		collectorUsage.setUsageId(this.usageId);
		collectorList.add(collectorUsage);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
