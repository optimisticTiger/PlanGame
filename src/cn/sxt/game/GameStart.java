package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;



/*飞机游戏的主窗口*/

public class GameStart extends Frame{

    
	Toolkit tool  = Toolkit.getDefaultToolkit();
	
	Image bgIMG = tool.getImage(GameStart.class.getResource("/images/background.jpg"));
	Image myPlaneIMG = tool.getImage(GameStart.class.getResource("/images/hero0.png"));
	Image myBullet = tool.getImage(GameStart.class.getResource("/images/bullet.png"));
	Image EnemyPlane = tool.getImage(GameStart.class.getResource("/images/airplane.png"));
	Image EnemyBullet = tool.getImage(GameStart.class.getResource("/images/bossbullet.png"));
	Image GameOver = tool.getImage(GameStart.class.getResource("/images/gameover.jpg"));
	Image DzBullet = tool.getImage(GameStart.class.getResource("/images/dz.png"));
	Image Bee = tool.getImage(GameStart.class.getResource("/images/bee.png"));
	Image Bomb = tool.getImage(GameStart.class.getResource("/images/bomb.png"));
	Image Boss = tool.getImage(GameStart.class.getResource("/images/boss.png"));
	Image End = tool.getImage(GameStart.class.getResource("/images/end.png"));
	
	
	BackGround backOBJ = new BackGround(0, 0, 400, 700, this);
	
	MyPlane planeOBJ = new MyPlane(180, 600, 50, 50, this, true);
	
	Boss bossOBJ = new Boss(150,100 , 100, 100, this, true);
	
	ArrayList<MyBullet>  myBulletList = new ArrayList<MyBullet>();
	ArrayList<EnemyPlane> enemyPlaneList = new ArrayList<EnemyPlane>();
    ArrayList<EnemyBullet> enemyBulletsList  = new ArrayList<EnemyBullet>();	
    ArrayList<Bee>  bee = new ArrayList<Bee>();
    ArrayList<Boss> bossList = new ArrayList<Boss>();
     
	
	int bulletType = 0;//0表示普通子弹，1表示大招
	Bomb bomb = null;
/*初始化窗口*/
    public void launchFrame() {
	this.setTitle("飞机大战");
	this.setVisible(true);//使窗口可见	
	this.setSize(400, 700);
	this.setLocationRelativeTo(null);
	this.addWindowListener(new WindowAdapter() {
		
	public void windowClosing(WindowEvent w) {
		System.exit(0);
		}
		
	});
	
	this.addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyPressed(KeyEvent k) {
			
			planeOBJ.movePlane(k);
		}
		
         @Override
        public void keyReleased(KeyEvent k) {

        	 planeOBJ.stopPlane(k);
         }
	});	
		
		
	
	
	new MyThread().start();
	
    }	
	int time = 0,enemyTime = 0,enemyBulletTime = 0,beeTime = 0,times = 0,bossTime = 0;
	EnemyPlane enemyPlane;
	Random r = new Random();
    
    class MyThread extends Thread{
    	
    	@Override
    	public void run() {
    		
    		while(true) {
    			
    			try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			repaint();
    			
//   			time++;
//    			if(time % 5 == 0 && planeOBJ.isLive ) {
//    				MyBullet myBullet = new MyBullet(planeOBJ.x + 19,planeOBJ.y,15, 15,GameStart.this , true);
//    				myBulletList.add(myBullet);
//    				time = 0;
//    			}
    			
    			enemyTime++;
    			if(enemyTime % 40 == 0) {
    			EnemyPlane enemyPlane = new EnemyPlane(r.nextInt(360), 0, 40, 40, GameStart.this, true);
    			enemyPlaneList.add(enemyPlane);
    			enemyTime = 0;
    			}
    			
    			beeTime++;
  			    if(beeTime % 100 == 0) {
    				Bee bees = new Bee(r.nextInt(360), 0, 40, 40, GameStart.this, true);
    				bee.add(bees);
    			}
  			    
  			    bossTime++;
  			    if(bossTime % 10 == 0) {
  			    	Boss boss = new Boss( 150,100 , 100, 100, GameStart.this, true);
  			    	bossList.add(boss);
  			    	bossTime = 0;
  			    }
    			
    		}
    	}
    }
    
    Image TempIMG = null;
    
    @Override
    public void update(Graphics g) {
    	
    	if(TempIMG == null) {
    		TempIMG = this.createImage(400, 700);
    	}
    	
    	Graphics tempG = TempIMG.getGraphics();
    	
    	g.drawImage(TempIMG, 0, 0, 400,700,this);
    	paint(tempG);
    }
    
    int score = 0;
    int boold = 100;
    int booldBoss = 100;
    int overY = 720; 
//    int endY = 720;
    public void paint (Graphics g) {
    	
    	backOBJ.drawBG(g);
    	planeOBJ.drawPlane(g);
    	planeOBJ.isIntersects(enemyBulletsList);
    	planeOBJ.isIntersectes(enemyPlaneList);
    	planeOBJ.isIntersect(bee);
    	times += 5000;	
    	
    
    	
    	
    	for(int i = 0; i < myBulletList.size(); i++) {
    		
    		MyBullet myBullet = myBulletList.get(i);
    		myBullet.drawMyBullet(g);
    		myBullet.isIntersect(enemyPlaneList);//是否相交
    		//myBullet.isIntersectBoss(bossList);
    		if(myBullet.y < 0) {
    			myBulletList.remove(myBullet);
    		}
    	}
    	
    	for(int i = 0 ; i < enemyPlaneList.size(); i++) {
    		EnemyPlane enemyPlane = enemyPlaneList.get(i);
    		if(score < 100) {
    		enemyPlane.drawEnemyPlane(g);
    		}
    		if(enemyPlane.y > 750) {
    			enemyPlaneList.remove(enemyPlane);
    		
    			
    		}
    	}
    	
    	for(int i = 0 ; i < enemyBulletsList.size(); i++) {
    		EnemyBullet enemyBullet = enemyBulletsList.get(i);
    		enemyBullet.drawEnemyBullet(g);
    		if(enemyBullet.y > 750) {
    			enemyBulletsList.remove(i);
    		}
    	}
    	
    	
    	for(int i = 0; i < bee.size(); i++) {
    		Bee bees = bee.get(i);
    		bees.drawBee(g);
    		if(bees.y > 750) {
    			bee.remove(i);
    		}
    	}
    	if(score == 100 ) {
    	for(int i = 0; i < bossList.size(); i++) {
    		Boss boss = bossList.get(i);
    		
    		boss.drawBoos(g);
    		boss.isIntersectBoss(myBulletList);
    		g.setColor(Color.red);
    		g.drawImage(Boss, 10, 50, 20,20,this);
        	g.drawRect(40, 50, 100, 20);
        	g.fillRect(40, 50, booldBoss, 20);
    	}
    	}
    	
    	g.setColor(Color.white);
    	g.setFont(new Font("宋体", Font.BOLD, 15));
    	g.drawString("游戏得分："+score, 280, 70);
    	
    	g.setColor(Color.red);
    	g.drawImage(myPlaneIMG, 10, 650,20,20,this);
    	g.drawRect(40, 650, 100, 20);
    	g.fillRect(40, 650, boold, 20);
    	
    	
    	
    	if(!planeOBJ.isLive) {
    		if(overY > 400) {
    	    g.drawImage(GameOver, 0, overY, 400,200,this);
    	    overY -= 50;
    		}else
    		{
            g.drawImage(GameOver, 0, overY, 400,200,this);
    		}
    	}
    	
    	if(score >= 200) {
        	    g.drawImage(End, 0, 200, 400,200,this);
    	}
    	
    	  if(bomb != null) {
    	    	bomb.drawBomb(g);
    	    }
    	  bomb = null;
    }
    
  

    public static void main(String[] args) {
    new GameStart().launchFrame();
	
	
    }

    }
	