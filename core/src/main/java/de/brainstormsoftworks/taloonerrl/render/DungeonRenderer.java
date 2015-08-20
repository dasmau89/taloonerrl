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
package de.brainstormsoftworks.taloonerrl.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.brainstormsoftworks.taloonerrl.dungeon.EDungeonSprites;
import de.brainstormsoftworks.taloonerrl.dungeon.IMap;

public final class DungeonRenderer implements IDisposableInstance {

	private static DungeonRenderer instance = null;

	private final Texture floorTexture;
	private final Texture wallTexture;

	private final TextureRegion sFloorTopLeft;
	private final TextureRegion sFloorTop;
	private final TextureRegion sFloorTopRight;
	private final TextureRegion sFloorLeft;
	private final TextureRegion sFloorCenter;
	private final TextureRegion sFloorRight;
	private final TextureRegion sFloorBottomLeft;
	private final TextureRegion sFloorBottom;
	private final TextureRegion sFloorBottomRight;
	private final TextureRegion sFloorTopLeftOutside;
	private final TextureRegion sFloorTopRightOutside;
	private final TextureRegion sFloorBottomLeftOutside;
	private final TextureRegion sFloorBottomRightOutside;
	private final TextureRegion sWallTopLeft;
	private final TextureRegion sWallHorizontal;
	private final TextureRegion sWallTopRight;
	private final TextureRegion sWallVertical;
	private final TextureRegion sWallBottomLeft;
	private final TextureRegion sWallBottomRight;
	private final TextureRegion sWallPillar;
	private final TextureRegion sWallFull;
	private final TextureRegion sWallCrossSection;
	private final TextureRegion sWallTsectionNorth;
	private final TextureRegion sWallTsectionSouth;
	private final TextureRegion sWallTsectionEast;
	private final TextureRegion sWallTsectionWest;
	private final TextureRegion sWallEndEast;
	private final TextureRegion sWallEndWest;

	private static final String TEXTURE_PATH = "textures/dungeon/";

	private DungeonRenderer() {
		floorTexture = new Texture(Gdx.files.internal(TEXTURE_PATH + "Floor02.png"), false);
		floorTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		floorTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
		wallTexture = new Texture(Gdx.files.internal(TEXTURE_PATH + "Wall.png"), false);
		wallTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		wallTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);

		// 1 + yyy was added since there seems to be an offset in the tileset
		// for the floor
		sFloorTopLeft = new TextureRegion(floorTexture, 0 * Renderer.tileSize, 1 + 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTopLeft = new TextureRegion(wallTexture, 0 * Renderer.tileSize, 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorTop = new TextureRegion(floorTexture, 1 * Renderer.tileSize, 1 + 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallHorizontal = new TextureRegion(wallTexture, 1 * Renderer.tileSize, 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorTopRight = new TextureRegion(floorTexture, 2 * Renderer.tileSize, 1 + 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTopRight = new TextureRegion(wallTexture, 2 * Renderer.tileSize, 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorLeft = new TextureRegion(floorTexture, 0 * Renderer.tileSize, 1 + 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallVertical = new TextureRegion(wallTexture, 0 * Renderer.tileSize, 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorCenter = new TextureRegion(floorTexture, 1 * Renderer.tileSize, 1 + 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorRight = new TextureRegion(floorTexture, 2 * Renderer.tileSize, 1 + 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorBottomLeft = new TextureRegion(floorTexture, 0 * Renderer.tileSize, 1 + 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallBottomLeft = new TextureRegion(wallTexture, 0 * Renderer.tileSize, 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorBottom = new TextureRegion(floorTexture, 1 * Renderer.tileSize, 1 + 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorBottomRight = new TextureRegion(floorTexture, 2 * Renderer.tileSize, 1 + 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sFloorBottomRightOutside = new TextureRegion(floorTexture, 6 * Renderer.tileSize,
				1 + 5 * Renderer.tileSize, Renderer.tileSize, Renderer.tileSize);
		sFloorTopRightOutside = new TextureRegion(floorTexture, 6 * Renderer.tileSize,
				1 + 3 * Renderer.tileSize, Renderer.tileSize, Renderer.tileSize);
		sFloorBottomLeftOutside = new TextureRegion(floorTexture, 4 * Renderer.tileSize,
				1 + 5 * Renderer.tileSize, Renderer.tileSize, Renderer.tileSize);
		sFloorTopLeftOutside = new TextureRegion(floorTexture, 4 * Renderer.tileSize,
				1 + 3 * Renderer.tileSize, Renderer.tileSize, Renderer.tileSize);
		sWallBottomRight = new TextureRegion(wallTexture, 2 * Renderer.tileSize, 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallPillar = new TextureRegion(wallTexture, 1 * Renderer.tileSize, 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallCrossSection = new TextureRegion(wallTexture, 4 * Renderer.tileSize, 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTsectionNorth = new TextureRegion(wallTexture, 4 * Renderer.tileSize, 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTsectionSouth = new TextureRegion(wallTexture, 4 * Renderer.tileSize, 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTsectionEast = new TextureRegion(wallTexture, 5 * Renderer.tileSize, 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallTsectionWest = new TextureRegion(wallTexture, 3 * Renderer.tileSize, 4 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallEndEast = new TextureRegion(wallTexture, 5 * Renderer.tileSize, 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallEndWest = new TextureRegion(wallTexture, 3 * Renderer.tileSize, 5 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		sWallFull = new TextureRegion(wallTexture, 3 * Renderer.tileSize, 3 * Renderer.tileSize,
				Renderer.tileSize, Renderer.tileSize);
		RenderUtil.addToDisposeList(this);
	}

	/**
	 * you <b>must</b> call {@link #disposeInstance} when you are done!
	 */
	public static void initInstance() {
		instance = new DungeonRenderer();
	}

	/**
	 * get an instance of this singleton
	 *
	 * @return {@link #instance}
	 */
	public static DungeonRenderer getInstance() {
		return instance;
	}

	/** {@inheritDoc} */
	@Override
	public void disposeInstance() {
		floorTexture.dispose();
		wallTexture.dispose();
	}

	public void render(final IMap map) {
		final EDungeonSprites[][] spriteArray = map.getDungeonSprites();
		// TODO render map into framebuffer and use buffer if player hasn't
		// moved
		for (int x = 0; x < map.getTilesHorizontal(); x++) {
			for (int y = 0; y < map.getTilesVertical(); y++) {
				final TextureRegion tile = getTile(spriteArray[x][y]);
				// final TextureRegion tile = TextureManager.getInstance()
				// .getTile(tileArray[x][y].getDungeonSprite());
				if (tile != null) {
					Renderer.getInstance().renderOnTile(tile, x, y);
				}
			}
		}
	}

	private TextureRegion getTile(final EDungeonSprites sprite) {
		switch (sprite) {
		case FLOOR_BOTTOM:
			return sFloorBottom;
		case FLOOR_BOTTOMLEFT_CORNER:
			return sFloorBottomLeft;
		case FLOOR_BOTTOMRIGHT_CORNER:
			return sFloorBottomRight;
		case FLOOR_CENTER:
			return sFloorCenter;
		case FLOOR_LEFT:
			return sFloorLeft;
		case FLOOR_RIGHT:
			return sFloorRight;
		case FLOOR_TOP:
			return sFloorTop;
		case FLOOR_TOPLEFT_CORNER:
			return sFloorTopLeft;
		case FLOOR_TOPRIGHT_CORNER:
			return sFloorTopRight;
		case FLOOR_BOTTOMLEFT_CORNER_OUTSIDE:
			return sFloorBottomLeftOutside;
		case FLOOR_BOTTOMRIGHT_CORNER_OUTSIDE:
			return sFloorBottomRightOutside;
		case FLOOR_TOPLEFT_CORNER_OUTSIDE:
			return sFloorTopLeftOutside;
		case FLOOR_TOPRIGHT_CORNER_OUTSIDE:
			return sFloorTopRightOutside;
		case WALL_TOPLEFT_CORNER:
			return sWallTopLeft;
		case WALL_HORIZONTAL:
			return sWallHorizontal;
		case WALL_TOPRIGHT_CORNER:
			return sWallTopRight;
		case WALL_VERTICAL:
			return sWallVertical;
		case WALL_BOTTOMLEFT_CORNER:
			return sWallBottomLeft;
		case WALL_BOTTOMRIGHT_CORNER:
			return sWallBottomRight;
		case WALL_PILLAR:
			return sWallPillar;
		case WALL_CROSSSECTION:
			return sWallCrossSection;
		case WALL_TSECTION_NORTH:
			return sWallTsectionNorth;
		case WALL_TSECTION_SOUTH:
			return sWallTsectionSouth;
		case WALL_TSECTION_EAST:
			return sWallTsectionEast;
		case WALL_TSECTION_WEST:
			return sWallTsectionWest;
		case WALL_END_NORT:
			return sWallVertical;
		case WALL_END_SOUTH:
			return sWallPillar;
		case WALL_END_EAST:
			return sWallEndEast;
		case WALL_END_WEST:
			return sWallEndWest;
		case WALL_FULL:
			return sWallFull;
		case NOTHING:
			// same as default for now
		default:
			// keep tile blank
			return null;
		}
		// return TextureManager.getInstance().getTile(tile.getDungeonSprite());
	}

}
