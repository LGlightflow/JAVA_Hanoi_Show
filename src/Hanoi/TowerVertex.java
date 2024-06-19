package Hanoi;
 
import java.awt.Component;
import java.awt.Container;
 
public class TowerVertex {
	private int x, y;
	boolean haveDisc;
	Disc disc = null;
 
	public TowerVertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
 
	public boolean isHaveDisc() {
		return haveDisc;
	}
 
	public void setHaveDisc(boolean bool) {
		haveDisc = bool;
	}
 
	public int getX() {
		return x;
	}
 
	public int getY() {
		return y;
	}
	

	public boolean equals(TowerVertex p) {
		if (p.getX() == this.getX() && p.getY() == this.getY())
			return true;
		else
			return false;
	}
	
	//往相应塔添加盘
	public void putDisc(Component cpn, Container ctn) {
		disc = (Disc) cpn;
		ctn.setLayout(null);
		ctn.add(disc);
		int w = disc.getBounds().width;
		int h = disc.getBounds().height;
		disc.setBounds(x - w / 2, y - h / 2, w, h);
		haveDisc = true;
		disc.setVertex(this);
		ctn.validate();
	}
 
	public Disc getDiscOnVertex() {
		return disc;
	}
 
	// 刷新
	public void removeDisc(Component cpn, Container ctn) {
		if (cpn != null)
			ctn.remove(cpn);
		//进行移除操作后重新布置部件
		ctn.validate();
	}
}