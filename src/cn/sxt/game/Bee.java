package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Bee {
   
	int x;
	int y;
	int width;
	int height;
	GameStart gs;
	boolean isLive;
	
	
	
	
	public Bee(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}

	public void drawBee(Graphics g) {

		if(!this.isLive) {
			return;
		}
		g.drawImage(gs.Bee, x, y, width,height,gs);
		y += 6;
		
	}
	
	
	
	public Rectangle getRec() {
		return new Rectangle(x, y,width,height);
	}
	
}
