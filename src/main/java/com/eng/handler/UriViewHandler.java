package com.eng.handler;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class UriViewHandler extends ViewHandlerWrapper{

	private ViewHandler handler;

	public UriViewHandler(ViewHandler h) { // thanks to decorator pattern
		handler = h;
	}

	@Override
	public ViewHandler getWrapped() {
		return handler;
	}

	@Override
	public String getActionURL(FacesContext ctx, String viewId) {
		return viewId.startsWith("/user")
			? ((HttpServletRequest)ctx.getExternalContext().getRequest()).getRequestURI()
			: handler.getActionURL(ctx, viewId);
	}

}
