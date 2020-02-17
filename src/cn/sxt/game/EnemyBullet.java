package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBullet {
   
	int x;
	int y;
	int width;
	int height;
	GameStart gs;
	boolean isLive;
	
	public EnemyBullet(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	public void drawEnemyBullet(Graphics g) {
		
		if(!this.isLive) {
			return;
		}
		
		g.drawImage(gs.EnemyBullet, x+16, y+16, width,height,gs);
		y += 15;
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y,width,height);
	}
}
