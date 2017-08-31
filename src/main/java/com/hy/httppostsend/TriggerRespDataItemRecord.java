package com.hy.httppostsend;

import java.util.Map;


public class TriggerRespDataItemRecord {

	private String respDataItemId;
	
	private String nextRespDataItemId;
	
	private String respDataId;
	
	private int seq;
	
	private Map<String, ?> custData;
	
	private Long createTime;

	public String getRespDataItemId() {
		return respDataItemId;
	}

	public void setRespDataItemId(String respDataItemId) {
		this.respDataItemId = respDataItemId;
	}

	public String getNextRespDataItemId() {
		return nextRespDataItemId;
	}

	public void setNextRespDataItemId(String nextRespDataItemId) {
		this.nextRespDataItemId = nextRespDataItemId;
	}

	public String getRespDataId() {
		return respDataId;
	}

	public void setRespDataId(String respDataId) {
		this.respDataId = respDataId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Map<String, ?> getCustData() {
		return custData;
	}

	public void setCustData(Map<String, ?> custData) {
		this.custData = custData;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}
