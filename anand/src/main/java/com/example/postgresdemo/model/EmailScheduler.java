/**
 * 
 */
package com.example.postgresdemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Cybertech1
 *
 */
@Entity
public class EmailScheduler {

	/**
	 * emailScheduleId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emailScheduleId;
	/**
	 * emailSubject
	 */
	private String emailSubject;
	
	/**
	 * managerUserIds
	 */
	private String managerUserIds;

	/**
	 * fieldUser
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_Id", referencedColumnName = "userId")
	private User fieldUser;
	


	/**
	 * sendStatus
	 */
	private boolean sendStatus;

	/**
	 * @param emailSubject
	 * @param fieldUser
	 * @param sendStatus
	 */
	public EmailScheduler(final String emailSubject, final User fieldUser, final boolean sendStatus) {
		super();
		this.emailSubject = emailSubject;
		this.fieldUser = fieldUser;
		this.sendStatus = sendStatus;
	}

	/**
	 * @return the emailScheduleId
	 */
	public long getEmailScheduleId() {
		return emailScheduleId;
	}

	/**
	 * @param emailScheduleId
	 *            the emailScheduleId to set
	 */
	public void setEmailScheduleId(final long emailScheduleId) {
		this.emailScheduleId = emailScheduleId;
	}

	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param emailSubject
	 *            the emailSubject to set
	 */
	public void setEmailSubject(final String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the fieldUser
	 */
	public User getFieldUser() {
		return fieldUser;
	}

	/**
	 * @param fieldUser
	 *            the fieldUser to set
	 */
	public void setFieldUser(final User fieldUser) {
		this.fieldUser = fieldUser;
	}

	/**
	 * @return the sendStatus
	 */
	public boolean isSendStatus() {
		return sendStatus;
	}

	/**
	 * @param sendStatus
	 *            the sendStatus to set
	 */
	public void setSendStatus(final boolean sendStatus) {
		this.sendStatus = sendStatus;
	}
	

	/**
	 * @return the managerUserIds
	 */
	public String getManagerUserIds() {
		return managerUserIds;
	}

	/**
	 * @param managerUserIds the managerUserIds to set
	 */
	public void setManagerUserIds(String managerUserIds) {
		this.managerUserIds = managerUserIds;
	}

	/**
	 * EmailScheduler
	 */
	public EmailScheduler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailScheduler [emailScheduleId=" + emailScheduleId + ", emailSubject=" + emailSubject
				+ ", managerUserIds=" + managerUserIds + ", fieldUser=" + fieldUser + ", sendStatus=" + sendStatus
				+ "]";
	}
}
