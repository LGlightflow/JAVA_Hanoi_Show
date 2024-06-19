package Hanoi;
import java.awt.Color;
import javax.swing.JButton;
import java.util.*;

// 每个盘是一个button
public class Disc extends JButton {
	int number;
	TowerVertex Vertex;
 
	Disc() {
        Random rand = new Random();
        Color randColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		setBackground(randColor);
	}
	// 从上到下，在一个塔中的第几个圆盘
	public void setNumber(int n) {
		number = n;
	}
 
	public int getNumber() {
		return number;
	}
 
	public void setVertex(TowerVertex p) {
		Vertex = p;
	}
 
	public TowerVertex getVertex() {
		return Vertex;
	}
}