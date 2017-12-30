package yy1020;

import java.awt.Color;
import java.awt.Graphics;

public class Health {
	public double h;
	public Health() {
		h=100.0;
	}
	public void inc(double x) {
		h+=x;
		return;
	}
	public void de(double x){
		h-=x;
		return ;
	}
	public void paint(Graphics g){
		double x=(h/100.0)*500.0;
		int y=50;
		g.setColor(Color.red);
		g.fill3DRect((int)((double)gameConfig.frameX*0.2),(int)((double)gameConfig.frameY*0.8), (int)x, (int)y, false);
	}
}
