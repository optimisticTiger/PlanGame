package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyPlane {
    
	int x;
	int y;
	int width;
	int height;
	GameStart gs ;
	boolean isLive;
	int enemyBulletTime = 0;
	
	
	public EnemyPlane(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	

	public EnemyPlane() {
	}


	

	public void drawEnemyPlane(Graphics g) {
		
		if(!this.isLive) {
			return;
		}
		
		g.drawImage(gs.EnemyPlane, x, y, width, height, gs);
		y += 6;
		
		
		
		enemyBulletTime++;
		
		if(enemyBulletTime % 20 == 0) {
		EnemyBullet bullet = new EnemyBullet(x, y, 10, 10, gs, true);
		gs.enemyBulletsList.add(bullet);
		enemyBulletTime = 0;
		}
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y,width,height);
	}
}
