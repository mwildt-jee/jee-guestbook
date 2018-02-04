package de.hsw.jee.guestbook.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.hsw.jee.guestbook.model.Benutzer;
import de.hsw.jee.guestbook.model.Eintrag;

@Singleton
@Local(GuestbookService.class)
public class GuestbookServiceImpl implements GuestbookService{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Eintrag neuerEintrag(Benutzer benutzer, String nachricht) {
		Eintrag eintrag = new Eintrag();
		eintrag.setBenutzer(benutzer);
		eintrag.setDate(new Date());
		eintrag.setNachricht(nachricht);
		em.persist(eintrag);
		return eintrag;
	}

	@Override
	public List<Eintrag> alleEintraege() {
		TypedQuery<Eintrag> query = em.createQuery("Select e From Eintrag e", Eintrag.class);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
