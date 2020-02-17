package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class Boss{

	int x;
	int y;
	int width;
	int height;
	GameStart gs;
	boolean isLive;
	int enemyBulletTime = 0;
	
	public Boss(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	public void drawBoos(Graphics g) {
		g.drawImage(gs.Boss, x, y, width,height,gs);
		
		if(!this.isLive) {
			return;
		}
		
        enemyBulletTime++;
		
		if(enemyBulletTime % 80 == 0) {
		EnemyBullet bullet = new EnemyBullet(x+15, y, 40, 40, gs, true);
		gs.enemyBulletsList.add(bullet);
		enemyBulletTime = 0;
		}
	}
	
	
	public Rectangle getRec() {
 		return new Rectangle(x, y,width,height);
 	}
	
	 public void  isIntersectBoss(ArrayList<MyBullet>  myBulletList) {
	   		
	   		for(int i = 0 ; i < myBulletList.size(); i++) {
	   			
	   			MyBullet  myBullet = myBulletList.get(i);
	   			if(this.isLive && myBullet.isLive && this.getRec().intersects(myBullet.getRec())) {
	   				gs.booldBoss -= 10;
	   				if(gs.booldBoss <= 0) {
	   			
	   				this.isLive = false;
	   				gs.bomb = new Bomb(x, y, 50, 50, gs);
	   				gs.score += 100;
	   				
	   				}
	   				myBullet.isLive = false;
	   			}
	   		}
	   	}
	
}
