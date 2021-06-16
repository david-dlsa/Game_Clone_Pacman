package com.lkestudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.lkestudios.main.Game;
import com.lkestudios.world.AStar;
import com.lkestudios.world.Camera;
import com.lkestudios.world.Vector2i;
import com.lkestudios.world.World;

public class Enemy extends Entity{

	public boolean ghostMode = false;
	public int ghostFrames = 0;
	public int nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;	;
	
	
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);	
	}

	public void tick() {
		depth = 0;
		
			if(Player.pegouFruta == false) {	
				if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
					Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
					path = AStar.findPath(Game.world, start, end);
				}
	
			if(new Random().nextInt(100) < 70) 
				followPath(path);
		
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 10) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			}
		}
		/* 
		ghostFrames++;
		if(ghostFrames == nextTime) {
			//nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;	
			nextTime = 100;
			ghostFrames = 0;
			if(Player.pegouFruta == false) {
				//System.out.println("Modo fantasma");	
				Player.pegouFruta = true;	
			}
			

			else {
					Player.pegouFruta = false;
			}
		}
		*/		
	}	
		 
	public boolean isColinddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky, mwidth, mheight);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	

	public void render(Graphics g) {
		if(Player.pegouFruta == false) {
			super.render(g);
		}
		else {
			g.drawImage(Entity.ENEMY_GHOST, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
}