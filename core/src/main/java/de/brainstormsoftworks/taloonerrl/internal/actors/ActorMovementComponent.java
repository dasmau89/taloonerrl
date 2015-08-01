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

import de.brainstormsoftworks.taloonerrl.actors.IActorMovement;
import de.brainstormsoftworks.taloonerrl.core.TaloonerRl;

/**
 *
 *
 * @author david
 *
 */
public class ActorMovementComponent implements IActorMovement, IComponent {

	private int xPosition;
	private int yPosition;

	public ActorMovementComponent() {
		this(0, 0);
	}

	public ActorMovementComponent(final int initialXPosition, final int initialYPosition) {
		xPosition = initialXPosition;
		yPosition = initialYPosition;
	}

	@Override
	public void move(final int dX, final int dY) {
		final int newX = xPosition + dX;
		final int newY = yPosition + dY;
		if (TaloonerRl.map.getMap()[newX][newY].isWalkable()) {
			xPosition += dX;
			yPosition += dY;
		}
	}

	@Override
	public int getXPosition() {
		return xPosition;
	}

	@Override
	public int getYPosition() {
		return yPosition;
	}

	@Override
	public void recieveMessage(final int msg) {
		// TODO Auto-generated method stub

	}
}
