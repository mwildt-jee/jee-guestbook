package de.hsw.jee.guestbook.view;

import java.io.IOException;
import java.util.Optional;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hsw.jee.guestbook.model.Benutzer;
import de.hsw.jee.guestbook.service.BenutzerService;
import de.hsw.jee.guestbook.service.BenutzerServiceImpl;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = -104799241428708957L;
	
	@EJB
	private BenutzerService service;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Benutzer benutzer = new Benutzer();
		benutzer.setName(req.getParameter("benutzerName"));
		benutzer.setPassword(req.getParameter("password"));
		getService().addBenutzer(benutzer);
		
		resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
	}

	public BenutzerService getService() {
		return service;
	}

	public void setService(BenutzerService service) {
		this.service = service;
	}

}
