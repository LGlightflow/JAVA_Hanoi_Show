package Hanoi;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.Timer;
public class AutoMoveDisc {
	int discNum = 3;
	TowerVertex[] pointA, pointB, pointC;
	char[] towerName;
	Container ctn;
	// StringBuffer能被多次修改不产生新的未使用对象
	StringBuffer moveStep;
	JButton bStart, bStop, bContinue, bClose;
	Timer time;
	int i = 0, number = 0;
 
	AutoMoveDisc(Container ctn) {
		this.ctn = ctn;
		moveStep = new StringBuffer();

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
 
	public void setTowerName(char name[]) {
		if (name[0] == name[1] || name[0] == name[2] || name[1] == name[2]) {
			towerName[0] = 'A';
			towerName[1] = 'B';
			towerName[2] = 'C';
		} else
			towerName = name;
	}
 
	public void setDiscNum(int n) {
		discNum = n;
	}
 
	
	//递归：汉诺塔算法 获取圆盘移动序列
	public void setMoveStep(int discNum, char one, char two, char three) {

		if (discNum == 1) {
			moveStep.append(one);
			moveStep.append(three);
		} else {
			setMoveStep(discNum - 1, one, three, two);
			moveStep.append(one);
			moveStep.append(three);
			setMoveStep(discNum - 1, two, one, three);
		}
	}
 
	public void autoMoveDisc(char cStart, char cEnd,int speed) {
		try {
			
			Thread.sleep( 250*(10-speed) );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Disc disc = null;
		if (cStart == towerName[0]) {
			for (int i = 0; i < pointA.length; i++) {
				if (pointA[i].isHaveDisc() == true) {
					disc = pointA[i].getDiscOnVertex();
					pointA[i].setHaveDisc(false);
					break;
				}
			}
		}
		if (cStart == towerName[1]) {
			for (int i = 0; i < pointB.length; i++) {
				if (pointB[i].isHaveDisc() == true) {
					disc = pointB[i].getDiscOnVertex();
					pointB[i].setHaveDisc(false);
					break;
				}
			}
		}
		if (cStart == towerName[2]) {
			for (int i = 0; i < pointC.length; i++) {
				if (pointC[i].isHaveDisc() == true) {
					disc = pointC[i].getDiscOnVertex();
					pointC[i].setHaveDisc(false);
					break;
				}
			}
		}
		TowerVertex endPoint = null;
		int i = 0;
		if (cEnd == towerName[0]) {
			for (i = 0; i < pointA.length; i++) {
				if (pointA[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointA[i - 1];
						break;
					} else if (i == 0)
						break;
				}
			}
			if (i == pointA.length)
				endPoint = pointA[pointA.length - 1];
		}
		if (cEnd == towerName[1]) {
			for (i = 0; i < pointB.length; i++) {
				if (pointB[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointB[i - 1];
						break;
					} else if (i == 0)
						break;
				}
			}
			if (i == pointB.length)
				endPoint = pointB[pointB.length - 1];
		}
		if (cEnd == towerName[2]) {
			for (i = 0; i < pointC.length; i++) {
				if (pointC[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointC[i - 1];
						break;
					} else if (i == 0)
						break;
				}
			}
			if (i == pointC.length)
				endPoint = pointC[pointC.length - 1];
		}
		if (endPoint != null && disc != null) {
			endPoint.putDisc(disc, ctn);
			endPoint.setHaveDisc(true);
		}
	}
}