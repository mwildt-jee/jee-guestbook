package de.hsw.jee.guestbook.service;

import java.util.Optional;

import de.hsw.jee.guestbook.model.Benutzer;

public interface BenutzerService {

	void addBenutzer(Benutzer benutzer);

	Optional<Benutzer> getBenutzer(String benutzername);
	
}
