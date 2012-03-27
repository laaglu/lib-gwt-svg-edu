/**********************************************
 * Copyright (C) 2010 Lukas Laag
 * This file is part of lib-gwt-svg-edu.
 * 
 * libgwtsvg-edu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * libgwtsvg-edu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with libgwtsvg-edu.  If not, see http://www.gnu.org/licenses/
 **********************************************/
package org.vectomatic.svg.edu.client.menu;

import org.vectomatic.dom.svg.ui.SVGResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Bundle class for resources which belong to the navigation menu
 * @author laaglu
 */
public interface MenuBundle extends ClientBundle {
	public static MenuBundle INSTANCE = GWT.create(MenuBundle.class);
	@Source("home12.svg")
	public SVGResource home();
	@SVGResource.Validated(validated=false)
	@Source("connectdots.svg")
	public SVGResource connectdots();
	@SVGResource.Validated(validated=false)
	@Source("maze.svg")
	public SVGResource maze();
	@Source("push.svg")
	@SVGResource.Validated(validated=false)
	public SVGResource push();
	@Source("puzzle.svg")
	@SVGResource.Validated(validated=false)
	public SVGResource puzzle();
	@Source("Menu.css")
	public MenuCss css();
}
