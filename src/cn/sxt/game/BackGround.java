package cn.sxt.game;

import java.awt.Graphics;

public class BackGround {

	int x;
	int y;
	int y1 = -700;
	int width;
	int height;
	GameStart gs = null;
	
	
	public BackGround(int x, int y, int width, int height, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}



	public void drawBG(Graphics g) {
		
		g.drawImage(gs.bgIMG, x, y, width,height, gs);
		g.drawImage(gs.bgIMG, x, y1, width,height, gs);
		
		y += 2;
		y1 += 2;
		
		if(y1 >= 0) {
			y = 0 ;
			y1 = -700;
		}
		
	}
	
}
