package de.hsw.jee.guestbook.service;

import java.util.Optional;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.hsw.jee.guestbook.model.Benutzer;
import de.hsw.jee.guestbook.model.BenutzerRolle;

@Singleton
@Local(BenutzerService.class)
public class BenutzerServiceImpl implements BenutzerService{
	
	@PersistenceContext(unitName="guestbook")
	private EntityManager entityManager;
	
	@Override
	public void addBenutzer(Benutzer benutzer) {
		// er erste Benutzer bekommt die Adminstrator-Rolle
		if((long)entityManager.createQuery("select count(b.id) from Benutzer b").getSingleResult() == 0L) {
			benutzer.setRolle(BenutzerRolle.Administrator);
		}
		entityManager.persist(benutzer);
	}
	
	@Override
	public Optional<Benutzer> getBenutzer(String benutzername) {
		TypedQuery<Benutzer> query = entityManager.createQuery("select b From Benutzer b where b.name = :name", Benutzer.class);
		query.setParameter("name", benutzername);
		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
