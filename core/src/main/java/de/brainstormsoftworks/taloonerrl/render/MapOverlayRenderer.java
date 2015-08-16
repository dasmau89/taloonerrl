package de.brainstormsoftworks.taloonerrl.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.brainstormsoftworks.taloonerrl.dungeon.IMap;

public final class MapOverlayRenderer {

	private static final MapOverlayRenderer instance = new MapOverlayRenderer();
	private int tilesHorizontal = 0;
	private int tilesVertical = 0;
	// reference to the map
	private IMap map = null;

	private Visible[][] overlay;

	private MapOverlayRenderer() {
	}

	/**
	 * getter for instance
	 *
	 * @return the instance
	 */
	public static MapOverlayRenderer getInstance() {
		return instance;
	}

	/**
	 * initializes this overlay renderer with the map that should be used for
	 * the rendering
	 *
	 * @param _map
	 */
	public void initialize(final IMap _map) {
		map = _map;
		tilesHorizontal = map.getTilesHorizontal();
		tilesVertical = map.getTilesVertical();
		overlay = new Visible[tilesHorizontal][tilesVertical];
		reset();
	}

	/**
	 * resets the overlay information to empty map
	 */
	public void reset() {
		for (int x = 0; x < tilesHorizontal; x++) {
			for (int y = 0; y < tilesVertical; y++) {
				// todo add map.isExplored() or something like that
				if (map.isWalkable(x, y)) {
					overlay[x][y] = Visible.WALKABLE;
				} else {
					overlay[x][y] = Visible.NOTHING;
				}
			}
		}
	}

	/**
	 * renders a map overlay onto the screen
	 *
	 * @param map
	 */
	public void render(final IMap map) {
		// TODO add FOV data as parameter
		// compute the offset to draw the overlay
		final int offsetX = (Gdx.graphics.getWidth() - tilesHorizontal) / Renderer.screenScale / 2;
		final float offsetY = (Gdx.graphics.getHeight() - tilesVertical) / Renderer.screenScale / 1.5f;
		TextureRegion tempOverlay = null;
		for (int x = 0; x < tilesHorizontal; x++) {
			for (int y = 0; y < tilesVertical; y++) {
				tempOverlay = getOverlay(overlay[x][y]);
				if (tempOverlay != null) {
					Renderer.getInstance().renderOnScreen(tempOverlay, x + offsetX, y + offsetY);
				}
			}
		}
	}

	private static TextureRegion getOverlay(final Visible v) {
		switch (v) {
		case WALKABLE:
			return PaletteUtil.getInstance().LIGHT_BLUE;
		case MONSTER:
			return PaletteUtil.getInstance().RED;
		case COLLECTIBLE:
			return PaletteUtil.getInstance().LIGHT_GREEN;
		case PLAYER:
			return PaletteUtil.getInstance().WHITE;
		case NOTHING:
		default:
			return null;
		}
	}

	/**
	 * sets the overlay of the given position
	 *
	 * @param x
	 * @param y
	 * @param v
	 */
	public void setOverlay(final int x, final int y, final Visible v) {
		if (map.isInMapBounds(x, y)) {
			overlay[x][y] = v;
		}
	}

	/**
	 * helper class to distinguish the different things that could be rendered
	 * at a given position
	 *
	 * @author David Becker
	 *
	 */
	public static enum Visible {
		NOTHING, WALKABLE, COLLECTIBLE, MONSTER, PLAYER, STAIRS
	}
}
