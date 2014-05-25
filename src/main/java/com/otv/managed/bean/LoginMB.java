package com.otv.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginMB {

	public void login() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/j_spring_security_check");
			dispatcher.forward(request, response);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception ex) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					null, "Falha no login.");
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, fm);
		}
	}
}