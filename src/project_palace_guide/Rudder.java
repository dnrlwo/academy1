package project_palace_guide;

import java.awt.Rectangle;

import javax.swing.JLabel;

public class Rudder extends Thread {

	int distance;
	JLabel icon;
	int i;
	MainProgram wuw;
	
	public void run() {
		rudder();
		try {
			wuw.lf.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rudder() {
		try {
			for (int k = 0; k < distance; k++) {
				if (i == 8) {
					icon.setBounds((int)icon.getBounds().getX(), (int)icon.getBounds().getY()-1, icon.getWidth(), icon.getHeight());
				} else if (i == 6) {
					icon.setBounds((int)icon.getBounds().getX()+1, (int)icon.getBounds().getY(), icon.getWidth(), icon.getHeight());
				} else if (i == 4) {
					icon.setBounds((int)icon.getBounds().getX()-1, (int)icon.getBounds().getY(), icon.getWidth(), icon.getHeight());
				} else if (i == 2) {
					icon.setBounds((int)icon.getBounds().getX(), (int)icon.getBounds().getY()+1, icon.getWidth(), icon.getHeight());
				}
				Thread.sleep(100);
			}
			wuw.lf.resume();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setData(String distance, JLabel icon, int i, MainProgram wuw) {
		this.distance = Integer.parseInt(distance.substring(0, distance.length() - 1));
		this.icon = icon;
		this.i = i;
		this.wuw = wuw;
	}
}
