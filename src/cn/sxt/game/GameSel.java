package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameSel extends JFrame{

    Toolkit tool  = Toolkit.getDefaultToolkit();
	
	Image bgIMG = tool.getImage(GameStart.class.getResource("/images/background1.jpg"));
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(bgIMG, 0, 0, 400,700,this);
		g.setColor(Color.white);
		g.setFont(new Font("���Ĳ���", Font.BOLD,35 ));
		g.drawString("�ɻ���ս", 130, 300);
		g.drawString("W,A,S,D���Ʒ���", 60, 600);
		g.drawString("�ո��ӵ�", 110, 650);
		g.drawString("��Enter����ʼ��Ϸ", 50, 500);
	}
	
	public void launchFrame() {
		this.setTitle("�ɻ���ս");
		this.setVisible(true);//ʹ���ڿɼ�	
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
				
				int code = k.getKeyCode();
				if(code == KeyEvent.VK_ENTER) {
					GameSel.this.setVisible(false);
					new GameStart().launchFrame();
				}
			}
			
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new GameSel().launchFrame();
	}

}
