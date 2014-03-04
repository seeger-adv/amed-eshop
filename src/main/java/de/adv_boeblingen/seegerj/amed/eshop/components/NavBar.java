package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class NavBar {
	@Inject
	private Messages messages;

	@Inject
	private ComponentResources resources;

	@Property
	private String pageName;

	public String[] getPageNames() {
		return new String[] { "Index", "Account", "About", "Contact" };
	}

	public String getPageLabel() {
		return messages.get("PAGELABEL_" + pageName);
	}

	public String getTabClass() {
		if (this.pageName.equalsIgnoreCase(this.resources.getPageName())) {
			return "current";
		}

		return null;
	}
}
