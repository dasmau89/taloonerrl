/*******************************************************************************
 * Copyright (c) 2015 David Becker.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     David Becker - initial API and implementation
 ******************************************************************************/
package de.brainstormsoftworks.taloonerrl.core;

import com.artemis.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.brainstormsoftworks.taloonerrl.components.FacingComponent;
import de.brainstormsoftworks.taloonerrl.components.PositionComponent;
import de.brainstormsoftworks.taloonerrl.core.engine.EEntity;
import de.brainstormsoftworks.taloonerrl.core.engine.GameEngine;
import de.brainstormsoftworks.taloonerrl.dungeon.IMap;
import de.brainstormsoftworks.taloonerrl.dungeon.MapFactory;
import de.brainstormsoftworks.taloonerrl.render.DungeonRenderer;
import de.brainstormsoftworks.taloonerrl.render.GuiRenderer;
import de.brainstormsoftworks.taloonerrl.render.MapOverlayRenderer;
import de.brainstormsoftworks.taloonerrl.render.RenderUtil;
import de.brainstormsoftworks.taloonerrl.render.Renderer;

/**
 * main entry point to the game
 *
 * @author David Becker
 *
 */
public class TaloonerRl implements ApplicationListener {

	EDirection walkingDirection = EDirection.RIGHT;

	private float delayToNextTurn = 0f;
	/** minimum delay between player turns */
	private static final float delayBetweenTurns = 0.03f;

	public static IMap map = null;
	private Entity playerEntity;
	private PositionComponent playerPositionComponent;
	private FacingComponent playerFacingComponent;

	@Override
	public void create() {
		map = MapFactory.createMap(Renderer.TILES_HORIZONTAL * 2, Renderer.TILES_VERTICAL * 2);

		// forces the engine to initialize
		final GameEngine gameEngine = GameEngine.getInstance();
		playerEntity = gameEngine.createNewEntity(EEntity.PLAYER);
		playerPositionComponent = playerEntity.getComponent(PositionComponent.class);
		playerFacingComponent = playerEntity.getComponent(FacingComponent.class);
		playerPositionComponent.setX(MapFactory.getPlayerStartX());
		playerPositionComponent.setY(MapFactory.getPlayerStartY());

		DungeonRenderer.initInstance();
		GuiRenderer.initInstance();
		MapOverlayRenderer.getInstance().initialize(map);

		// a couple of entities for debugging purposes...
		// for (int i = 2; i < 10; i++) {
		// gameEngine.createNewEntity(EEntity.ARCHER, i, i);
		// gameEngine.createNewEntity(EEntity.POTION_A, i + 2, i);
		// gameEngine.createNewEntity(EEntity.POTION_A, i + 4, i + 6);
		// }

	}

	@Override
	public void resize(final int width, final int height) {
		Renderer.getInstance().resizeViewPort(width, height);
	}

	@Override
	public void render() {
		// check if the player did something first
		// TODO change to a more general system

		final float deltaTime = Gdx.graphics.getDeltaTime();
		delayToNextTurn -= deltaTime;

		boolean keyPressedLeft = false;
		boolean keyPressedRight = false;
		boolean keyPressedDown = false;
		boolean keyPressedUp = false;
		if (delayToNextTurn <= 0f) {
			// possible for player to make his turn
			keyPressedLeft = Gdx.input.isKeyPressed(Keys.LEFT);
			keyPressedRight = Gdx.input.isKeyPressed(Keys.RIGHT);
			keyPressedDown = Gdx.input.isKeyPressed(Keys.DOWN);
			keyPressedUp = Gdx.input.isKeyPressed(Keys.UP);
			if (keyPressedDown || keyPressedLeft || keyPressedRight || keyPressedUp) {
				// player did a move
				// isPlayerTurn = true;
				// TODO refactor into controller input system
				int dX = 0;
				int dY = 0;
				if (keyPressedLeft) {
					dX = -1;
					walkingDirection = EDirection.LEFT;
				}
				if (keyPressedRight) {
					dX = 1;
					walkingDirection = EDirection.RIGHT;
				}
				if (keyPressedDown) {
					dY = -1;
					walkingDirection = EDirection.DOWN;
				}
				if (keyPressedUp) {
					dY = 1;
					walkingDirection = EDirection.UP;
				}
				// TODO only use one player
				playerPositionComponent.setX(playerPositionComponent.getX() + dX);
				playerPositionComponent.setY(playerPositionComponent.getY() + dY);
				playerFacingComponent.setDirection(walkingDirection);
				delayToNextTurn = delayBetweenTurns;
			}
		}

		Renderer.getInstance().beginWorldRendering();
		DungeonRenderer.getInstance().render(map);

		GameEngine.getInstance().update(deltaTime);
		Renderer.getInstance().endWorldRendering();

		Renderer.getInstance().beginScreenRendering();
		Renderer.getInstance().setScreenBlendingAlpha();
		MapOverlayRenderer.getInstance().render();
		Renderer.getInstance().setScreenBlendingNormal();

		GuiRenderer.getInstance().render(Gdx.graphics.getWidth() / Renderer.screenScale,
				Gdx.graphics.getHeight() / Renderer.screenScale);
		Renderer.getInstance().endScreenRendering();

		MapOverlayRenderer.getInstance().reset();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		RenderUtil.disposeInstances();
	}
}
