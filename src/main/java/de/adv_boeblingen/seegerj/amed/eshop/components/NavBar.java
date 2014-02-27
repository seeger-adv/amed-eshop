package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class NavBar {
	@Inject
	private ComponentResources resources;

	@Property
	private String pageName;

	public String[] getPageNames() {
		return new String[] { "Index", "Products", "About", "Contact" };
	}

	public String getTabClass() {
		if (this.pageName.equalsIgnoreCase(this.resources.getPageName())) {
			return "current";
		}

		return null;
	}
}
