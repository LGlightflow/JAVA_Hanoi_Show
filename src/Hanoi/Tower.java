package Hanoi;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;
 
public class Tower extends JPanel {
	int discNum = 3;
	Disc[] disc;
	int maxDiscWidth, minDiscWidth, discHeight;
	char[] towerName = { 'A', 'B', 'C' };
	TowerVertex[] pointA, pointB, pointC;
	MouseAction mouseAction;
	AutoMoveDisc autoMoveDisc;
 
	Tower() {
		mouseAction = new MouseAction(this);
		setLayout(null);
		setBackground(Color.white);
	}
 
	public void setDiscNum(int number) {
		if (number <= 1)
			discNum = 1;
		else
			discNum = number;
	}
 
	public void setMaxDiscWidth(int m) {
		maxDiscWidth = m;
	}
 
	public void setMinDiscWidth(int m) {
		minDiscWidth = m;
	}
 
	public void setDiscHeight(int h) {
		discHeight = h;
	}
 
	public AutoMoveDisc getAutoMoveDisc() {
		return autoMoveDisc;
	}

	public void putDiscOnTower() {
		removeAllDisc();
		int n = (maxDiscWidth - minDiscWidth) / discNum;  //(最大盘的宽度 - 最小盘宽度)/圆盘数 = i和i+1个圆盘的差值
		//按照盘数初始化towervertex
		disc = new Disc[discNum];
		for (int i = 0; i < disc.length; i++) {
			disc[i] = new Disc();
			disc[i].setNumber(i);
			int diskwidth = minDiscWidth + i * n;//最小宽度加相应差值
			disc[i].setSize(diskwidth, discHeight);
			disc[i].addMouseListener(mouseAction);
			disc[i].addMouseMotionListener(mouseAction);
		}
		pointA = new TowerVertex[discNum];
		pointB = new TowerVertex[discNum];
		pointC = new TowerVertex[discNum];
		int vertialDistance = discHeight;
		for (int i = 0; i < pointA.length; i++) {
			pointA[i] = new TowerVertex(maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}
		vertialDistance = discHeight;
		for (int i = 0; i < pointB.length; i++) {
			pointB[i] = new TowerVertex(2 * maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}
		vertialDistance = discHeight;
		for (int i = 0; i < pointC.length; i++) {
			pointC[i] = new TowerVertex(3 * maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}
		for (int i = 0; i < pointA.length; i++) {
			pointA[i].putDisc(disc[i], this);
		}
		mouseAction.setPointA(pointA);
		mouseAction.setPointB(pointB);
		mouseAction.setPointC(pointC);
		autoMoveDisc = new AutoMoveDisc(this);
		autoMoveDisc.setTowerName(towerName);
		autoMoveDisc.setDiscNum(discNum);
		autoMoveDisc.setPointA(pointA);
		autoMoveDisc.setPointB(pointB);
		autoMoveDisc.setPointC(pointC);
		validate();
		repaint();
	}
 
	public void removeAllDisc() {
		if (pointA != null) {
			for (int i = 0; i < pointA.length; i++) {
				pointA[i].removeDisc(pointA[i].getDiscOnVertex(), this);
				pointB[i].removeDisc(pointB[i].getDiscOnVertex(), this);
				pointC[i].removeDisc(pointC[i].getDiscOnVertex(), this);
			}
		}
	}
 
	public void paintComponent(Graphics g) {
        Random rand = new Random();
        Color randColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		super.paintComponent(g);
        //画塔（棍子）
		int x1, y1, x2, y2;
		x1 = pointA[0].getX();
		y1 = pointA[0].getY() - discHeight / 2;
		x2 = pointA[discNum - 1].getX();
		y2 = pointA[discNum - 1].getY() + discHeight / 2;
        g.fillRect(x1-3, y1-10, 6, y2-y1+10);  
		x1 = pointB[0].getX();
		y1 = pointB[0].getY() - discHeight / 2;
		x2 = pointB[discNum - 1].getX();
		y2 = pointB[discNum - 1].getY() + discHeight / 2;
		g.fillRect(x1-3, y1-10, 6, y2-y1+10);
		x1 = pointC[0].getX();
		y1 = pointC[0].getY() - discHeight / 2;
		x2 = pointC[discNum - 1].getX();
		y2 = pointC[discNum - 1].getY() + discHeight / 2;
		g.fillRect(x1-3, y1-10, 6, y2-y1+10);
		g.setColor(randColor);
		x1 = pointA[discNum - 1].getX() - maxDiscWidth / 2;
		y1 = pointA[discNum - 1].getY() + discHeight / 2;
		x2 = pointC[discNum - 1].getX() + maxDiscWidth / 2;
		y2 = pointC[discNum - 1].getY() + discHeight / 2;
		int length = x2 - x1, height = 6;
		g.fillRect(x1, y1, length, height);
	}
}
