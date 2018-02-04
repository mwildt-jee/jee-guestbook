package de.hsw.jee.guestbook.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hsw.jee.guestbook.model.Benutzer;
import de.hsw.jee.guestbook.service.GuestbookService;
import de.hsw.jee.guestbook.service.GuestbookServiceImpl;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {

	private static final long serialVersionUID = 4976245259268095413L;
	
	private @EJB GuestbookService guestbook;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {

		String nachricht = req.getParameter("nachricht");
		Benutzer benutzer = (Benutzer) req.getSession().getAttribute("benutzer");
		getGuestbook().neuerEintrag(benutzer, nachricht);
		resp.sendRedirect(req.getContextPath());
	}

	public GuestbookService getGuestbook() {
		return guestbook;
	}

	public void setGuestbook(GuestbookService guestbook) {
		this.guestbook = guestbook;
	}
	
}
