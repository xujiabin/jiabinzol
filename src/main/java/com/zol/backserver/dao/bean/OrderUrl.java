package com.zol.backserver.dao.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="orderurl")
public class OrderUrl implements Serializable{
	
	
	
	/**
	 * 属性说明
	 */
	private static final long serialVersionUID = 1L;

	public OrderUrl(){
		
	}
	
	
	public OrderUrl(Userinfo userinfo,String urlid){
		this.userinfo = userinfo;
		this.urlid = urlid;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="userid")
	private Userinfo userinfo;
	
	private String urlid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 
	public Userinfo getUserinfo() {
		return userinfo;
	}


	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}


	public String getUrlid() {
		return urlid;
	}

	public void setUrlid(String urlid) {
		this.urlid = urlid;
	}
	
	
	
	

}
