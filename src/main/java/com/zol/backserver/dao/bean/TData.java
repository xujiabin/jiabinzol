package com.zol.backserver.dao.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tdata")
public class TData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String url;
	
	private String name;
	
	private String updatestr;
	
	private int urlid;
	
	
	private Date updates;
	
	private Date createtime;
	
	
	

	public int getUrlid() {
		return urlid;
	}

	public void setUrlid(int urlid) {
		this.urlid = urlid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatestr() {
		return updatestr;
	}

	public void setUpdatestr(String updatestr) {
		this.updatestr = updatestr;
	}

	 

	public Date getUpdates() {
		return updates;
	}

	public void setUpdates(Date updates) {
		this.updates = updates;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	

}
