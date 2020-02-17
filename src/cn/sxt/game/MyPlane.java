package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MyPlane {

	int x;
	int y;
	int width;
	int height;

	
	
	GameStart gs = null;
	boolean isLive ;
	boolean U,D,L,R;
	
	
	public MyPlane(int x, int y, int width, int height, GameStart gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	public void drawPlane(Graphics g) {
		
		if(!this.isLive) {
			return;
		}
		g.drawImage(gs.myPlaneIMG, x, y, width, height,gs);
		
		if(U) {
			y -= 10;
		}
		if(D) {
			y += 10;
		}
		if(L) {
			x -= 10;
		}
		if(R) {
			x += 10;
		}
	}
	
	public void movePlane(KeyEvent k) {
		
		int code = k.getKeyCode();
		if(code == KeyEvent.VK_W) {
			U = true;
		}else if(code == KeyEvent.VK_S) {
			D = true;
		}else if(code == KeyEvent.VK_A) {
			L = true;
		}else if(code == KeyEvent.VK_D) {
			R = true;
		}
		
	}
	
     public void stopPlane(KeyEvent k) {
		
		int code = k.getKeyCode();
		if(code == KeyEvent.VK_W) {
			U = false;
		}else if(code == KeyEvent.VK_S) {
			D = false;
		}else if(code == KeyEvent.VK_A) {
			L = false;
		}else if(code == KeyEvent.VK_D) {
			R = false;
		}else if(code == KeyEvent.VK_SPACE && this.isLive) {
			
			MyBullet myBullet = new MyBullet(x+19, y, 15,15, gs, true);
			
			
			gs.myBulletList.add(myBullet);
			
		}
		
	}
     
     public Rectangle getRec() {
 		return new Rectangle(x, y,width,height);
 	}
	
     public void  isIntersects(ArrayList<EnemyBullet> enemyBulletsList) {
 		
 		for(int i = 0 ; i < enemyBulletsList.size(); i++) {
 			
 			EnemyBullet  enemyBullet = enemyBulletsList.get(i);
 			if(this.isLive && enemyBullet.isLive && this.getRec().intersects(enemyBullet.getRec())) {
 				gs.boold -= 10;
 				if(gs.boold <= 0) {
 				  this.isLive = false;
 				}
 				enemyBullet.isLive = false;
 			}
 		}
 	}
     
     public void  isIntersectes(ArrayList<EnemyPlane> enemyPlaneList) {
  		
  		for(int i = 0 ; i < enemyPlaneList.size(); i++) {
  			
  			EnemyPlane  enemyPlane = enemyPlaneList.get(i);
  			if(this.isLive && enemyPlane.isLive && this.getRec().intersects(enemyPlane.getRec())) {
  				
  				gs.boold -= 10;
  				if(gs.boold <= 0) {
  				  this.isLive = false;
  				}
  				enemyPlane.isLive = false;
  			}
  		}
  	}
     
    
     public void  isIntersect(ArrayList<Bee>  bee ) {
   		
   		for(int i = 0 ; i < bee.size(); i++) {
   			
   			Bee  bees = bee.get(i);
   			if(this.isLive && bees.isLive && this.getRec().intersects(bees.getRec())) {
   				
   				bees.isLive = false;
   				gs.bulletType = 1;
   				
   			}
   		}
		
   	}
}
