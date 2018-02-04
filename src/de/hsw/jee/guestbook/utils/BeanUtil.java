package de.hsw.jee.guestbook.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanUtil {
	
	public static <R> R lookupLocal(Class<? extends R> type, Class<R> interfaceType) {
		try {
			return (R) new InitialContext().lookup("java:module/" + type.getSimpleName());
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("unable to lookup java:module" + type.getSimpleName(), e);
		}
	}

}
