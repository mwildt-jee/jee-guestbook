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
import de.hsw.jee.guestbook.service.GuestbookService;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {

	private static final String PARAM_ID = "id";
	private static final String BENUTZER = "benutzer";
	
	
	private @EJB GuestbookService guestbook;
	
	/**
	 * Erzeugt einen neuen Eintag im Gästebuch
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {

		final String nachricht = req.getParameter("nachricht");
		final Benutzer benutzer = (Benutzer) req.getSession().getAttribute(BENUTZER);
		
		getGuestbook().neuerEintrag(benutzer, nachricht);
		resp.sendRedirect(req.getContextPath());
	}

	/**
	 * Löscht einen Eintrag des Gästebuches
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final Benutzer benutzer = (Benutzer) req.getSession().getAttribute(BENUTZER);
				
		Optional.ofNullable(req.getParameter(PARAM_ID))
				.map(Integer::parseInt)
				.ifPresent(gb -> guestbook.eintragLoeschen(gb, benutzer));
		
		resp.sendRedirect(req.getContextPath());
	}
	
	public GuestbookService getGuestbook() {
		return guestbook;
	}

	public void setGuestbook(GuestbookService guestbook) {
		this.guestbook = guestbook;
	}
	
}
