package com.hy.httppostsend;

import java.io.Serializable;

public class EventRecord implements Serializable {


	private Long eventId;
	
	private String eventName;
	
	private Long srcAppId;
	
	private Long triggerId;

	private Long dstAppId;
	
	private Long actionId;
	
	private String eventContact;
	
	private String eventUrl;
	
	private String eventUrlParam;
	
	private String eventHeaderParam;
	
	private String eventBodyParam;
	
	private Integer eventStatus;
	
	private Integer triggerAuthStatus;
	
	private Integer actionAuthStatus;
	
	private String applyDesc;
	
	private boolean canSkip;
	
	private boolean mustOrderd;
	
	private boolean historyStatus;
	
	private Long applyTime;
	
	private Long authTime;

	private Long createTime;
	
	private Long lastModifyTime;
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Long getSrcAppId() {
		return srcAppId;
	}

	public void setSrcAppId(Long srcAppId) {
		this.srcAppId = srcAppId;
	}

	public Long getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(Long triggerId) {
		this.triggerId = triggerId;
	}

	public Long getDstAppId() {
		return dstAppId;
	}

	public void setDstAppId(Long dstAppId) {
		this.dstAppId = dstAppId;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Integer getTriggerAuthStatus() {
		return triggerAuthStatus;
	}

	public void setTriggerAuthStatus(Integer triggerAuthStatus) {
		this.triggerAuthStatus = triggerAuthStatus;
	}

	public Integer getActionAuthStatus() {
		return actionAuthStatus;
	}

	public void setActionAuthStatus(Integer actionAuthStatus) {
		this.actionAuthStatus = actionAuthStatus;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public Long getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	public Long getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Long authTime) {
		this.authTime = authTime;
	}

	public boolean isCanSkip() {
		return canSkip;
	}

	public void setCanSkip(boolean canSkip) {
		this.canSkip = canSkip;
	}

	public boolean isMustOrderd() {
		return mustOrderd;
	}

	public void setMustOrderd(boolean mustOrderd) {
		this.mustOrderd = mustOrderd;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getEventUrlParam() {
		return eventUrlParam;
	}

	public void setEventUrlParam(String eventUrlParam) {
		this.eventUrlParam = eventUrlParam;
	}

	public String getEventHeaderParam() {
		return eventHeaderParam;
	}

	public void setEventHeaderParam(String eventHeaderParam) {
		this.eventHeaderParam = eventHeaderParam;
	}

	public String getEventBodyParam() {
		return eventBodyParam;
	}

	public void setEventBodyParam(String eventBodyParam) {
		this.eventBodyParam = eventBodyParam;
	}

	public boolean isHistoryStatus() {
		return historyStatus;
	}

	public void setHistoryStatus(boolean historyStatus) {
		this.historyStatus = historyStatus;
	}

	public String getEventContact() {
		return eventContact;
	}

	public void setEventContact(String eventContact) {
		this.eventContact = eventContact;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}
}
