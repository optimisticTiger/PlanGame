package cn.sxt.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MyBullet {
   
	int x;
	int y;
	int width;
	int height;
	GameStart gs = null;
	boolean isLive;
	
	public MyBullet(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	public void drawMyBullet(Graphics g) {

		if(!this.isLive) {
			return;
		}
		if(gs.bulletType == 0) {
		g.drawImage(gs.myBullet, x, y, width,height,gs);
		}
		if(gs.bulletType == 1) {
		g.drawImage(gs.DzBullet, x-18, y, 50,50,gs);
		for(int j = 0; j < gs.times; j++) {
				gs.times--;
				if(gs.times == 0 ) {
					gs.bulletType = 0;
				}
				}
		}
		y -= 10; 
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y,width,height);
	}
	
	public void  isIntersect(ArrayList<EnemyPlane> enemyPlaneList) {
		
		for(int i = 0 ; i < enemyPlaneList.size(); i++) {
			
			EnemyPlane  enemyPlane = enemyPlaneList.get(i);
			if(this.isLive && enemyPlane.isLive && this.getRec().intersects(enemyPlane.getRec()) && gs.score < 100) {
				
				this.isLive = false;
				enemyPlane.isLive = false;
				
				gs.score += 5;
				
				gs.bomb = new Bomb(x, y, 50, 50, gs);
			}
		}
		
	}
	
//	 public void  isIntersectBoss( ArrayList<Boss> bossList) {
//	   		
//	   		for(int i = 0 ; i < bossList.size(); i++) {
//	   			
//	   			Boss  boss = bossList.get(i);
//	   			if(this.isLive && boss.isLive && this.getRec().intersects(boss.getRec())) {
//	   				gs.booldBoss -= 10;
//	   				this.isLive = false;
//	   				if(gs.booldBoss <= 0) {
//	   				boss.isLive = false;
//	   				gs.bomb = new Bomb(x, y, 50, 50, gs);
//	   				gs.getGraphics().setFont(new Font("华文彩云", Font.BOLD,25 ));
//	   				gs.getGraphics().drawString("恭喜通关", 90, 500);
//	   				//System.out.println("cccccccccc");
//	   				gs.score += 100;
//	   				}
//	   			}
//	   		}
//	   	}
//	
	
}
