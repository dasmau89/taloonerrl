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
package de.brainstormsoftworks.taloonerrl.internal.dungeon;

import de.brainstormsoftworks.taloonerrl.dungeon.EDungeonSprites;
import de.brainstormsoftworks.taloonerrl.dungeon.IMap;
import de.brainstormsoftworks.taloonerrl.math.ArrayHelper;

public class Map implements IMap {

	private final char[][] map;
	private final double[][] fovResistance;
	private final boolean[][] visited;
	private final int tilesHorizontal;
	private final int tilesVertical;
	private EDungeonSprites[][] dungeonSprites;

	public Map(final int _tilesHorizontal, final int _tilesVertical) {
		tilesHorizontal = _tilesHorizontal;
		tilesVertical = _tilesVertical;
		map = new char[tilesHorizontal][tilesVertical];
		fovResistance = new double[tilesHorizontal][tilesVertical];
		visited = new boolean[tilesHorizontal][tilesVertical];
		dungeonSprites = new EDungeonSprites[tilesHorizontal][tilesVertical];
	}

	@Override
	public boolean isVisible(final int x, final int y) {
		if (!isInMapBounds(x, y)) {
			return false;
		}
		// FIXME
		throw new UnsupportedOperationException("TODO: implement");
	}

	@Override
	public boolean isWalkable(final int x, final int y) {
		return isInMapBounds(x, y) && map[x][y] != '#';
	}

	@Override
	public boolean isInMapBounds(final int x, final int y) {
		return ArrayHelper.isInArrayBounds(map, x, y);
	}

	@Override
	public char[][] getMap() {
		return map;
	}

	@Override
	public final double[][] getFovResistance() {
		return fovResistance;
	}

	@Override
	public int getTilesHorizontal() {
		return tilesHorizontal;
	}

	@Override
	public int getTilesVertical() {
		return tilesVertical;
	}

	/**
	 * getter for {@link #dungeonSprites}
	 *
	 * @return the dungeonSprites
	 */
	@Override
	public final EDungeonSprites[][] getDungeonSprites() {
		return dungeonSprites;
	}

	/**
	 * method to set the dungeon sprites
	 *
	 * @param sprites
	 *            array to set
	 */
	public void setDungeonSprites(final EDungeonSprites[][] sprites) {
		dungeonSprites = sprites;
	}

	@Override
	public final boolean[][] getVisited() {
		return visited;
	}

}
