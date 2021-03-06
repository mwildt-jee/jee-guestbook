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

@WebServlet("/auth/login")
public class AuthServlet extends HttpServlet {
	
	@EJB
	private BenutzerService service;
	
	/**
	 * Controller für das Anmelden eines bereits existierenden Benutzers
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String benutzerName = req.getParameter("benutzerName");
		String password = req.getParameter("password");
		
		Optional<Benutzer> benutzer = getService().getBenutzer(benutzerName);
		
		if(benutzer.isPresent() && benutzer.get().checkPassword(password)) {
			req.getSession().setAttribute("benutzer", benutzer.get());
			resp.sendRedirect(req.getContextPath());
		} else {
			resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
		}
	}

	public BenutzerService getService() {
		return service;
	}

	public void setService(BenutzerService service) {
		this.service = service;
	}

}
