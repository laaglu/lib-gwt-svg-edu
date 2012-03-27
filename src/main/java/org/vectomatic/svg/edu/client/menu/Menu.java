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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGTSpanElement;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGFace;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGFaceName;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGStyleChange;
import org.vectomatic.dom.svg.ui.SVGImage;
import org.vectomatic.dom.svg.ui.SVGPushButton;
import org.vectomatic.dom.svg.ui.SVGResource;
import org.vectomatic.dom.svg.utils.DOMHelper;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGPrefixResolver;
import org.vectomatic.svg.edu.client.commons.CommonBundle;
import org.vectomatic.svg.edu.client.commons.CommonConstants;
import org.vectomatic.svg.edu.client.commons.ConfirmBox;
import org.vectomatic.svg.edu.client.commons.LicenseBox;
import org.vectomatic.svg.edu.client.commons.Utils;
import org.vectomatic.svg.edu.client.dots.DotsMain;
import org.vectomatic.svg.edu.client.maze.MazeMain;
import org.vectomatic.svg.edu.client.push.PushMain;
import org.vectomatic.svg.edu.client.puzzle.PuzzleMain;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Main game menu
 * @author laaglu
 */
public class Menu implements EntryPoint {
	
	/**
	 * Utility class to make use of code splitting less verbose
	 */
	private static abstract class GameCallback implements RunAsyncCallback {
		 public void onFailure(Throwable reason) {
			if (Utils.isDebug()) {
				Utils.handleFatalError(reason);
			} else {
				Window.alert(CommonConstants.INSTANCE.loadError());
			}
		  }
		  public abstract void onSuccess();
	}
	
	/**
	 * Prefix resolver class. The prefix resolver is used by the XPath
	 * expression which injects the game title in the SVG images
	 */
	private static class GamePrefixResolver extends SVGPrefixResolver {
		public GamePrefixResolver() {
			prefixToUri.put("v", VECTOMATIC_NS_URI);
		}
	}
	private static final String VECTOMATIC_NS_URI = "http://www.vectomatic.org";
	private static GamePrefixResolver resolver = new GamePrefixResolver();
	private static MenuBundle bundle = MenuBundle.INSTANCE;
	private static MenuCss css = bundle.css();
	private static MenuConstants menuConstants = MenuConstants.INSTANCE;
	/**
	 * A dialog box to ask for confirmation before leaving the game
	 */
	private static DialogBox confirmBox;

	private static final int COL_COUNT = 2;
	private static final float WIDTH = 100f;
	private static final float HEIGHT = 100f;

	/**
	 * Enum to represent the available games and their characteristics
	 */
	enum Game {
		DOTS {
			@Override
			protected RunAsyncCallback getCallback() {
				return new GameCallback() {
			        public void onSuccess() {
						RootPanel root = RootPanel.get(CommonConstants.ID_UIROOT);
						root.remove(root.getWidget(0));
						DotsMain main = new DotsMain(Game.createHomeButton());
		    			main.onModuleLoad();
			        }
				};
			}
			@Override
			protected SVGImage getImage() {
				return createImage(
						bundle.connectdots(), 
						menuConstants.connectDotsTitle(), 
						getCallback());
			}
			@Override
			protected Label getRule() {
				return new Label(menuConstants.connectDotsRule());
			}
		},
		MAZE {
			@Override
			protected RunAsyncCallback getCallback() {
				return new GameCallback() {
			        public void onSuccess() {
						RootPanel root = RootPanel.get(CommonConstants.ID_UIROOT);
						root.remove(root.getWidget(0));
			        	MazeMain main = new MazeMain(Game.createHomeButton());
			    		main.onModuleLoad();
			        }
				};
			}
			@Override
			protected SVGImage getImage() {
				return createImage(
						bundle.maze(), 
						menuConstants.mazeTitle(), 
						getCallback());
			}
			@Override
			protected Label getRule() {
				return new Label(menuConstants.mazeRule());
			}
		},
		PUSH {
			@Override
			protected RunAsyncCallback getCallback() {
				return new GameCallback() {
			        public void onSuccess() {
						RootPanel root = RootPanel.get(CommonConstants.ID_UIROOT);
						root.remove(root.getWidget(0));
			        	PushMain main = new PushMain(Game.createHomeButton());
			    		main.onModuleLoad();
			        }
				};
			}
			@Override
			protected SVGImage getImage() {
				return createImage(
						bundle.push(), 
						menuConstants.pushTitle(), 
						getCallback());
			}
			@Override
			protected Label getRule() {
				return new Label(menuConstants.pushRule());
			}
		},
		PUZZLE {
			@Override
			protected RunAsyncCallback getCallback() {
				return new GameCallback() {
			        public void onSuccess() {
						RootPanel root = RootPanel.get(CommonConstants.ID_UIROOT);
						root.remove(root.getWidget(0));
			        	PuzzleMain main = new PuzzleMain(Game.createHomeButton());
			    		main.onModuleLoad();
			        }
				};
			}
			@Override
			protected SVGImage getImage() {
				return createImage(
						bundle.puzzle(), 
						menuConstants.puzzleTitle(), 
						getCallback());
			}
			@Override
			protected Label getRule() {
				return new Label(menuConstants.puzzleRule());
			}
		};
		protected abstract SVGImage getImage();
		protected abstract Label getRule();
		protected abstract RunAsyncCallback getCallback();
		
		public FlowPanel getPanel() {
			FlowPanel panel = new FlowPanel();
			FlowPanel imagePanel = new FlowPanel();
			imagePanel.add(getImage());
			imagePanel.setStyleName(css.gameLogo());
			panel.add(imagePanel);
			Label rule = getRule();
			rule.setStyleName(css.gameRule());
			panel.add(rule);
			return panel;
		}
		
		private static SVGImage createImage(
				SVGResource gameLogo,
				String gameTitle,
				final RunAsyncCallback callback) {
			OMSVGDocument document = OMSVGParser.currentDocument();
			final SVGImage svgImage = new SVGImage(gameLogo);
			svgImage.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
			        GWT.runAsync(callback);
				}
				
			});
			
			List<OMSVGTSpanElement> tspans = new ArrayList<OMSVGTSpanElement>();
			Iterator<OMSVGTSpanElement> iterator = DOMHelper.evaluateXPath(svgImage.getSvgElement(), ".//svg:tspan[@v:title]", resolver);
			while(iterator.hasNext()) {
				// Use a temporary list since the iterator is immutable
				tspans.add(iterator.next());
			}
			for (OMSVGTSpanElement tspan : tspans) {
				tspan.appendChild(document.createTextNode(gameTitle));
			}
			return svgImage;
		}

		private static SVGPushButton createHomeButton() {
			Map<SVGFaceName, SVGFace> faces = new HashMap<SVGFaceName, SVGFace>();
			faces.put(SVGFaceName.UP, new SVGFace(new SVGStyleChange[] { new SVGStyleChange( new String[] { CommonBundle.INSTANCE.css().navigationUp()} )}));
			faces.put(SVGFaceName.UP_HOVERING, new SVGFace(new SVGStyleChange[] { new SVGStyleChange( new String[] { CommonBundle.INSTANCE.css().navigationUpHovering()} )}));
			SVGPushButton homeButton = new SVGPushButton(bundle.home().getSvg(), faces);
			homeButton.setClassNameBaseVal(CommonBundle.INSTANCE.css().navigationPanelMenuButton());
			homeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
			        confirmBox.center();
			        confirmBox.show();
				}
			});
			return homeButton;
		}
	};
	
	@Override
    public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable throwable) {
				Utils.handleFatalError(throwable);
			}
		});                                                              

        // use a deferred command so that the handler catches onModuleLoad2()                                                                             
        // exceptions                                                    
		Scheduler.get().scheduleDeferred(new Command() {
			@Override
			public void execute() {
				onModuleLoad2();
			}
		});                                                              
	}
	
	
	public void onModuleLoad2() {
		MenuBundle.INSTANCE.css().ensureInjected();
		
		FlowPanel gamePanel = new FlowPanel();
		FlowPanel menuPanel = new FlowPanel();
		menuPanel.setStyleName(css.gamePanel());
		Game[] games = Game.values();
		int rowCount = (games.length + 1) / 2;
		float rowHeight = HEIGHT / rowCount;
		float colWidth = WIDTH / COL_COUNT;
		for (int i = 0; i < games.length; i++) {
			int row = i / 2;
			int col = i % 2;
			float left = col * colWidth;
			float top = row * rowHeight;
			FlowPanel panel = games[i].getPanel();
			panel.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
			panel.getElement().getStyle().setLeft(left, Style.Unit.PCT);
			panel.getElement().getStyle().setTop(top, Style.Unit.PCT);
			panel.getElement().getStyle().setWidth(colWidth, Style.Unit.PCT);
			panel.getElement().getStyle().setHeight(rowHeight, Style.Unit.PCT);
			menuPanel.add(panel);
		}
		

		final LicenseBox licenseBox = new LicenseBox();
		Anchor licenseAnchor = new Anchor();
		licenseAnchor.setStyleName(css.anchor());
		licenseAnchor.setText(CommonConstants.INSTANCE.license());
		licenseAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				licenseBox.getBox().center();
				licenseBox.getBox().show();
			}
			
		});
		
		gamePanel.add(menuPanel);
		gamePanel.add(licenseAnchor);

		RootPanel.get(CommonConstants.ID_UIROOT).add(gamePanel);
		confirmBox = ConfirmBox.createConfirmBox(gamePanel);
		
		String gameParam = Window.Location.getParameter("game");
		Game game = null;
		if (gameParam != null) {
			try {
				game = Game.valueOf(gameParam.toUpperCase());
			} catch (IllegalArgumentException e) {
			}
		}
		if (game != null) {
	        GWT.runAsync(game.getCallback());
		}
	}
}
