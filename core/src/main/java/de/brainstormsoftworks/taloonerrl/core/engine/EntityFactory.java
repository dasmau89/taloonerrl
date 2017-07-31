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
package de.brainstormsoftworks.taloonerrl.core.engine;

import com.artemis.Entity;
import com.artemis.World;

import de.brainstormsoftworks.taloonerrl.components.AnimationComponent;
import de.brainstormsoftworks.taloonerrl.components.FacingAnimationComponent;
import de.brainstormsoftworks.taloonerrl.components.NameComponent;
import de.brainstormsoftworks.taloonerrl.components.PositionComponent;
import de.brainstormsoftworks.taloonerrl.components.SpriteComponent;

/**
 * factory to create entities in to the game world
 *
 * @author David Becker
 *
 */
public final class EntityFactory {

	private EntityFactory() {
	}

	/**
	 * creates a new entity
	 *
	 * @param type
	 *            of entity
	 * @param world
	 *            world to create the entity in
	 * @param xPosition
	 *            horizontal position (tiles position)
	 * @param yPosition
	 *            vertical position (tiles position)
	 * @return new entity
	 */
	protected static Entity createEntity(final EEntity type, final World world, final int xPosition,
			final int yPosition) {
		switch (type) {
		case PLAYER:
			return createPlayer(world);
		case BLOB:
			return createActor(type, world, xPosition, yPosition);
		case SQUIRREL:
			return createActor(type, world, xPosition, yPosition);
		case BAT:
			return createActor(type, world, xPosition, yPosition);
		case MAGICIAN:
			return createActor(type, world, xPosition, yPosition);
		case SLUG:
			return createActor(type, world, xPosition, yPosition);
		case GHOST:
			return createActor(type, world, xPosition, yPosition);
		case SHADOW:
			return createActor(type, world, xPosition, yPosition);
		case EYEBALL:
			return createActor(type, world, xPosition, yPosition);
		case GOLEM:
			return createActor(type, world, xPosition, yPosition);
		case ARCHER:
			return createActor(type, world, xPosition, yPosition);
		case BOMB:
			return createActor(type, world, xPosition, yPosition);
		case WARPER:
			return createActor(type, world, xPosition, yPosition);
		case STEALER:
			return createActor(type, world, xPosition, yPosition);
		case DRAGON:
			return createActor(type, world, xPosition, yPosition);
		case ACID:
			return createActor(type, world, xPosition, yPosition);
		case MUSHROOM:
			return createActor(type, world, xPosition, yPosition);
		case POTION_A:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_B:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_C:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_D:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_E:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_F:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_G:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_H:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_I:
			return createCollectible(type, world, xPosition, yPosition);
		case POTION_J:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_A:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_B:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_C:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_D:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_E:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_F:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_G:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_H:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_I:
			return createCollectible(type, world, xPosition, yPosition);
		case SCROLL_J:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_A:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_B:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_C:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_D:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_E:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_F:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_G:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_H:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_I:
			return createCollectible(type, world, xPosition, yPosition);
		case WAND_J:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_A:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_B:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_C:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_D:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_E:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_F:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_G:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_H:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_I:
			return createCollectible(type, world, xPosition, yPosition);
		case RING_J:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_A:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_B:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_C:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_D:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_E:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_F:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_G:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_H:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_I:
			return createCollectible(type, world, xPosition, yPosition);
		case WEAPON_J:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_A:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_B:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_C:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_D:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_E:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_F:
			return createCollectible(type, world, xPosition, yPosition);
		case SHIELD_G:
			return createCollectible(type, world, xPosition, yPosition);
		case TORCH:
			return createDecoration(type, world, xPosition, yPosition);
		case CURSOR:
			return world.createEntity(Archetypes.getInstance().cursor);
		default:
			return null;
		}
	}

	private static Entity createPlayer(final World world) {
		final Entity newEntity = world.createEntity(Archetypes.getInstance().player);
		newEntity.getComponent(FacingAnimationComponent.class).mapAnimation(EEntity.PLAYER);
		setName(newEntity, EEntity.PLAYER);
		return newEntity;
	}

	private static Entity createActor(final EEntity type, final World world, final int xPosition,
			final int yPosition) {
		final Entity newEntity = world.createEntity(Archetypes.getInstance().actor);
		newEntity.getComponent(AnimationComponent.class).mapAnimation(type);
		final PositionComponent posComponent = newEntity.getComponent(PositionComponent.class);
		posComponent.setX(xPosition);
		posComponent.setY(yPosition);
		setName(newEntity, type);
		return newEntity;
	}

	private static Entity createCollectible(final EEntity type, final World world, final int xPosition,
			final int yPosition) {
		final Entity newEntity = world.createEntity(Archetypes.getInstance().collectible);
		newEntity.getComponent(SpriteComponent.class).mapSprite(type);
		final PositionComponent posComponent = newEntity.getComponent(PositionComponent.class);
		posComponent.setX(xPosition);
		posComponent.setY(yPosition);
		return newEntity;
	}

	private static Entity createDecoration(final EEntity type, final World world, final int xPosition,
			final int yPosition) {
		final Entity newEntity = world.createEntity(Archetypes.getInstance().decoration);
		newEntity.getComponent(AnimationComponent.class).mapAnimation(type);
		final PositionComponent posComponent = newEntity.getComponent(PositionComponent.class);
		posComponent.setX(xPosition);
		posComponent.setY(yPosition);
		return newEntity;
	}

	private static void setName(final Entity entity, final EEntity type) {
		// TODO refactor
		final NameComponent component = entity.getComponent(NameComponent.class);
		switch (type) {
		case BLOB:
			component.setName("Blob");
			component.setDescription("Your most generic opponent");
			break;
		case PLAYER:
			component.setName("Adventurer");
			component.setDescription("Who might this be?");
			break;
		case SQUIRREL:
			component.setName("Squirrel");
			component.setDescription("A rat-like creature with a tail");
			break;
		case BAT:
			component.setName("Bat");
			component.setDescription("A flapping creature");
			break;
		case SLUG:
			component.setName("Slug");
			break;
		case MAGICIAN:
			component.setName("Magician");
			break;
		case GHOST:
			component.setName("Ghost");
			break;
		case SHADOW:
			component.setName("Shadow");
			break;
		case EYEBALL:
			component.setName("Giant Eyeball");
			break;
		case GOLEM:
			component.setName("Golem");
			break;
		case ARCHER:
			component.setName("Archer");
			break;
		case WARPER:
			component.setName("Warper");
			break;
		case STEALER:
			component.setName("Stealer");
			break;
		case DRAGON:
			component.setName("Dragon");
			break;
		case ACID:
			component.setName("Acid Slime");
			break;
		case MUSHROOM:
			component.setName("Mushroom");
			break;
		default:
			break;
		}

	}
}
