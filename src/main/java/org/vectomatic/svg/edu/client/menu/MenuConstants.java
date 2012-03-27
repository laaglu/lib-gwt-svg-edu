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

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface MenuConstants extends Constants {
	public static final MenuConstants INSTANCE =  GWT.create(MenuConstants.class);

	public String connectDotsTitle();
	public String connectDotsRule();
	public String mazeTitle();
	public String mazeRule();
	public String pushTitle();
	public String pushRule();
	public String puzzleTitle();
	public String puzzleRule();

}
