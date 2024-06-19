package Hanoi;
 
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
 
 
public class MouseAction implements MouseListener, MouseMotionListener {
	TowerVertex[] pointA, pointB, pointC;
	TowerVertex startPoint = null, endPoint = null;
	int leftX, leftY, x0, y0;
	boolean move = false, countTime = false;
	Container ctn;
 
	MouseAction(Container ctn) {
		this.ctn = ctn;
	}
 
	public void setPointA(TowerVertex[] pointA) {
		this.pointA = pointA;
	}
 
	public void setPointB(TowerVertex[] pointB) {
		this.pointB = pointB;
	}
 
	public void setPointC(TowerVertex[] pointC) {
		this.pointC = pointC;
	}
 
	//鼠标按下
	public void mousePressed(MouseEvent e) {
		move = false;
		Disc disc = null;
		disc = (Disc) e.getSource();
		startPoint = disc.getVertex();
		// 记录按下时的坐标
		x0 = e.getX();
		y0 = e.getY();
		int m = 0;

		//判断按下的圆盘是否可以移动
		for (int i = 0; i < pointA.length; i++) {
			if (pointA[i].equals(startPoint)) {
				m = i;
				if (m > 0 && (pointA[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				} else if (m == 0) {
					move = true;
					break;
				}
			}
		}
 
		for (int i = 0; i < pointB.length; i++) {
			if (pointB[i].equals(startPoint)) {
				m = i;
				if (m > 0 && (pointB[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				} else if (m == 0) {
					move = true;
					break;
				}
			}
		}
		
		for (int i = 0; i < pointC.length; i++) {
			if (pointC[i].equals(startPoint)) {
				m = i;
				if (m > 0 && (pointC[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				} else if (m == 0) {
					move = true;
					break;
				}
			}
		}
	}
 
	//鼠标移动MouseMotionListener
	public void mouseMoved(MouseEvent e) {
	}

	//鼠标拖动MouseMotionListener
	public void mouseDragged(MouseEvent e) {
		Disc disc = null;
		disc = (Disc) e.getSource();
		// 当前盘坐标
		leftX = disc.getBounds().x; 
		leftY = disc.getBounds().y;
		// 鼠标坐标
		int x = e.getX();
		int y = e.getY();
		leftX = leftX + x;
		leftY = leftY + y;
		//允许移动
		if (move == true)
			// 盘原始坐标+鼠标移动的相对坐标
			disc.setLocation(leftX - x0, leftY - y0);
	}
	
	//鼠标释放
	public void mouseReleased(MouseEvent e) {
		Disc disc = null;
		disc = (Disc) e.getSource();
		//矩形类
		Rectangle rect = disc.getBounds();
		boolean location = false;
		int x = -1, y = -1;
		// 判断是否能够放置
		for (int i = 0; i < pointA.length; i++) {
			x = pointA[i].getX();
			y = pointA[i].getY();
			if (rect.contains(x, y)) {
				endPoint = pointA[i];
				if (i == pointA.length - 1 && endPoint.isHaveDisc() == false) {
					location = true;
					break;
				} else if (i < pointA.length - 1 && pointA[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false
						&& pointA[i + 1].getDiscOnVertex().getNumber() > disc.getNumber()) {
					location = true;
					break;
				}
			}
		}
		for (int i = 0; i < pointB.length; i++) {
			x = pointB[i].getX();
			y = pointB[i].getY();
			if (rect.contains(x, y)) {
				endPoint = pointB[i];
				if (i == pointB.length - 1 && endPoint.isHaveDisc() == false) {
					location = true;
					break;
				} else if (i < pointB.length - 1 && pointB[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false
						&& pointB[i + 1].getDiscOnVertex().getNumber() > disc.getNumber()) {
					location = true;
					break;
				}
			}
		}
		for (int i = 0; i < pointC.length; i++) {
			x = pointC[i].getX();
			y = pointC[i].getY();
			if (rect.contains(x, y)) {
				endPoint = pointC[i];
				if (i == pointC.length - 1 && endPoint.isHaveDisc() == false) {
					location = true;
					break;
				} else if (i < pointC.length - 1 && pointC[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false
						&& pointC[i + 1].getDiscOnVertex().getNumber() > disc.getNumber()) {
					location = true;
					break;
				}
			}
		}
		if (endPoint != null && location == true) {
			endPoint.putDisc(disc, ctn);
			startPoint.setHaveDisc(false);
		} else
			startPoint.putDisc(disc, ctn);
	}
 
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
}