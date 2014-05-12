package de.adv_boeblingen.seegerj.amed.eshop;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class JqueryZoom implements JavaScriptStack {
	@Inject
	@Path("context:js/jquery-2.11.min.js")
	private Asset jquery;

	@Inject
	@Path("context:js/jquery.zoom.min.js")
	private Asset jqueryZoom;

	@Inject
	@Path("context:js/jquery-zoom-impl.js")
	private Asset jqueryZoomImpl;

	@Override
	public List<String> getStacks() {
		return new ArrayList<String>();
	}

	@Override
	public List<Asset> getJavaScriptLibraries() {
		ArrayList<Asset> libraries = new ArrayList<Asset>();
		libraries.add(this.jquery);
		libraries.add(this.jqueryZoom);
		libraries.add(this.jqueryZoomImpl);
		return libraries;
	}

	@Override
	public List<StylesheetLink> getStylesheets() {
		return new ArrayList<StylesheetLink>();
	}

	@Override
	public List<String> getModules() {
		return new ArrayList<String>();
	}

	@Override
	public String getInitialization() {
		return null;
	}
}
