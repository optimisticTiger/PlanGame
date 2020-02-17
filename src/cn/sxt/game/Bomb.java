package cn.sxt.game;

import java.awt.Graphics;

public class Bomb {

	int x;
	int y;
	int width;
	int height;
	GameStart gs;
	public Bomb(int x, int y, int width, int height, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}
	
	
	public void drawBomb(Graphics g) {
		g.drawImage(gs.Bomb, x, y, width,height,gs);
	}
	
	
}
