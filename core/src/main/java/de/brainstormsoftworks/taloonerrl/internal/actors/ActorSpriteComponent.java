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
package de.brainstormsoftworks.taloonerrl.internal.actors;

import de.brainstormsoftworks.taloonerrl.actors.IActorSprite;
import de.brainstormsoftworks.taloonerrl.dungeon.ESprite;

public class ActorSpriteComponent implements IActorSprite, IComponent {

	private ESprite sprite = ESprite.NOTHING;

	@Override
	public ESprite getSprite() {
		return sprite;
	}

	public void setSprite(final ESprite newSprite) {
		sprite = newSprite;
	}

	@Override
	public void recieveMessage(final int msg) {
		// TODO Auto-generated method stub

	}
}
