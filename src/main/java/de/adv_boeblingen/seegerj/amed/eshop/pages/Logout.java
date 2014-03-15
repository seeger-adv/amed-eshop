package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;

import de.adv_boeblingen.seegerj.amed.eshop.model.Session;

public class Logout {
	@SessionState
	private Session session;

	@OnEvent(EventConstants.ACTIVATE)
	public Object redirectToIndex() {
		session.invalidate();
		return Index.class;
	}
}
