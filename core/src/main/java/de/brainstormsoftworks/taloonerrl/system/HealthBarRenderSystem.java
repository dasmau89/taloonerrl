/*******************************************************************************
 * Copyright (c) 2015, 2017 David Becker.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     David Becker - initial API and implementation
 ******************************************************************************/
package de.brainstormsoftworks.taloonerrl.system;

import com.artemis.Aspect;
import com.artemis.systems.IteratingSystem;

import de.brainstormsoftworks.taloonerrl.components.HealthComponent;
import de.brainstormsoftworks.taloonerrl.components.PositionComponent;
import de.brainstormsoftworks.taloonerrl.core.engine.ComponentMappers;
import de.brainstormsoftworks.taloonerrl.render.FovWrapper;
import de.brainstormsoftworks.taloonerrl.render.PaletteUtil;
import de.brainstormsoftworks.taloonerrl.render.Renderer;

/**
 * system that draws health bars under entities
 *
 * @author David Becker
 *
 */
public class HealthBarRenderSystem extends IteratingSystem {
	private static final int totalSize = Renderer.tileSize - 2;
	private HealthComponent healthComponent;
	private int sizeGreen = 0;
	private int sizeRed = 0;
	private PositionComponent positionComponent;
	private int x = -1;
	private int y = -1;
	private int offsetX = -1;
	private int offsetY = -1;

	public HealthBarRenderSystem() {
		super(Aspect.all(PositionComponent.class, HealthComponent.class));
	}

	@Override
	protected void process(final int _entityId) {
		healthComponent = ComponentMappers.getInstance().health.get(_entityId);
		if (healthComponent.isAlive()) {
			positionComponent = ComponentMappers.getInstance().position.get(_entityId);
			x = positionComponent.getX();
			y = positionComponent.getY();
			offsetX = positionComponent.getOffsetX();
			offsetY = positionComponent.getOffsetY();
			if (FovWrapper.getInstance().isLit(x, y)) {
				sizeGreen = (int) (totalSize * healthComponent.getHealthPercent());
				sizeRed = totalSize - sizeGreen;
				Renderer.getInstance().renderOnTile(PaletteUtil.getInstance().BLACK, x, y, offsetX, offsetY,
						Renderer.tileSize, 3);
				for (int i = 0; i < sizeGreen; i++) {
					Renderer.getInstance().renderOnTile(PaletteUtil.getInstance().LIGHT_GREEN, x, y,
							i + 1 + offsetX, 1 + offsetY);
				}
				for (int i = 0; i < sizeRed; i++) {
					Renderer.getInstance().renderOnTile(PaletteUtil.getInstance().RED, x, y,
							i + 1 + sizeGreen + offsetX, 1 + offsetY);
				}
			}
		}
	}
}
