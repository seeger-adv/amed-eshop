package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "context:css/justified-nav.css")
public class NavBar {
	@Inject
	private Messages messages;

	@Inject
	private ComponentResources resources;

	@Property
	private String pageName;

	public String[] getPageNames() {
		return new String[] { "Index", "Browse", "Account", "About", "Contact" };
	}

	public String getPageLabel() {
		return this.messages.get("PAGELABEL_" + this.pageName);
	}

	public String getTabClass() {
		if (this.pageName.equalsIgnoreCase(this.resources.getPageName())) {
			return "current";
		}

		return null;
	}
}
