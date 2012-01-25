package com.tinyrender.rollemup;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class GameObject extends PhysicsObject {
	public enum GameObjectType {
		PLAYER, ROLLABLE, STATIC;
	}
	
	public boolean isRolled;
	
	public int points;
	public int level;
	
	public float rot;
	public Vector2 pos = new Vector2();
	public Vector2 rolledPos = new Vector2();
	
	public GameObjectType gameObjType;
	public GameObjectRepresentation objRep = new GameObjectRepresentation(this);
	
	public GameObject parent;
	public Array<GameObject> children = new Array<GameObject>();
	
	public GameObject(World world) {
		super(world);
		parent = null;
		children.shrink();
	}
	
	public GameObjectType getType() {
		return gameObjType;
	}

	public void update() {
		if (!isRolled || body.isActive()) {
			pos = body.getPosition();
			rot = body.getAngle() * MathUtils.radiansToDegrees;
				
			for (int i = 0; i < children.size; i++) {
				children.get(i).pos = children.get(i).body.getPosition();
				children.get(i).rot = children.get(i).body.getAngle() * MathUtils.radiansToDegrees;
			}
		}
		
	}
	
}
