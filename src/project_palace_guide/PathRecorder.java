package project_palace_guide;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

public class PathRecorder extends Thread {

	Vector<Point> v = new Vector<Point>();
	MainProgram wuw;

	public void run() {
		try {
			while (true) {
				v.add(wuw.lbl_icon.getLocation());
//				System.out.println(wuw.lbl_icon.getLocation());
				sleep(100);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setdata(MainProgram wuw) {
		this.wuw = wuw;
	}
	public Vector<Point> getV() {
		return v;
	}
}
