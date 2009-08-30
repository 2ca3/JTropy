package com.appspot.jtropy;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author 2ca3
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class JtropyBean {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	@Persistent
	private String title;

	@Persistent
	private String body;

	@Persistent
	private Date createAt;

	@Persistent
	private Date editAt;

	/**
	 * @param title
	 * @param body
	 * @param createAt
	 * @param editAt
	 */
	public JtropyBean(String title, String body, Date createAt, Date editAt) {
		this.title = title;
		this.body = body;
		this.createAt = createAt;
		this.editAt = editAt;
	}

	
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return createAt
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * @param createAt
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * @return editAt
	 */
	public Date getEditAt() {
		return editAt;
	}

	/**
	 * @param editAt
	 */
	public void setEditAt(Date editAt) {
		this.editAt = editAt;
	}
	
	/**
	 * @return id
	 */
	public Key getId() {
		return id;
	}
}
