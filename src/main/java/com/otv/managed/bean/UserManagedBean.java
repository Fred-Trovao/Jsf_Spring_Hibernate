package com.otv.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.dao.DataAccessException;

import com.otv.model.User;
import com.otv.user.service.IUserService;

@ManagedBean(name="userMB")
@ViewScoped
public class UserManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -962730706537483101L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{userService}")
	IUserService userService;
	
	List<User> userList;
	
	private User usuario;
	
	public UserManagedBean(){
		this.usuario = new User();
	}
	
	public String addUser() {
		try {
			usuario.addPermissao("ROLE_USUARIO");
			usuario.setAtivo(true);
			getUserService().addUser(usuario);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.usuario = new User();
	}
	
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}