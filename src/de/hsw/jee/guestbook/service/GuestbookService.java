package de.hsw.jee.guestbook.service;

import java.util.List;
import de.hsw.jee.guestbook.model.Benutzer;
import de.hsw.jee.guestbook.model.Eintrag;

public interface GuestbookService {

	/**
	 * Erzeuge einen neuen Eintrag
	 * @param benutzer
	 * @param nachricht
	 * @return
	 */
	Eintrag neuerEintrag(
			Benutzer benutzer, 
			String nachricht);
	
	/**
	 * Lies alle Eintraege
	 * @return
	 */
	List<Eintrag> alleEintraege();
	
}
