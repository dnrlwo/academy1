package project_palace_guide;

import javax.swing.JLabel;

public class LocationFinder extends Thread {

	JLabel lbl_icon;
	MainProgram wuw;

	public void run() {
//		System.out.println("쓰레드 시작");
		while (true) {
			if ((lbl_icon.getX() <= 300 && lbl_icon.getX() >= 200)
					&& (lbl_icon.getY() >= 750 && lbl_icon.getY() <= 810)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_광화문().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("광화문", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();
			}else if((lbl_icon.getX() <= 160 && lbl_icon.getX() >= 60)
					&& (lbl_icon.getY() >= 710 && lbl_icon.getY() <= 770)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_국립고궁박물관().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("국립고궁박물관", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 320 && lbl_icon.getX() >= 220)
					&& (lbl_icon.getY() >= 670 && lbl_icon.getY() <= 700)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_홍례문().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("홍례문", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 320 && lbl_icon.getX() >= 220)
					&& (lbl_icon.getY() >= 590 && lbl_icon.getY() <= 620)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_근정문().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("근정문", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 320 && lbl_icon.getX() >= 230)
					&& (lbl_icon.getY() >= 490 && lbl_icon.getY() <= 550)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_근정전().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("근정전", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 220 && lbl_icon.getX() >= 97)
					&& (lbl_icon.getY() >= 350 && lbl_icon.getY() <= 450)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_경회루().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("경회루", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 320 && lbl_icon.getX() >= 230)
					&& (lbl_icon.getY() >= 420 && lbl_icon.getY() <= 450)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_강녕전().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("강녕전", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 360 && lbl_icon.getX() >= 240)
					&& (lbl_icon.getY() >= 370 && lbl_icon.getY() <= 400)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_교태전().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("교태전", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 350 && lbl_icon.getX() >= 240)
					&& (lbl_icon.getY() >= 100 && lbl_icon.getY() <= 160)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_향원정().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("향원정", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 340 && lbl_icon.getX() >= 230)
					&& (lbl_icon.getY() >= 110 && lbl_icon.getY() <= 200)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_건청궁().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("건청궁", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}else if((lbl_icon.getX() <= 520 && lbl_icon.getX() >= 400)
					&& (lbl_icon.getY() >= 140 && lbl_icon.getY() <= 300)) {
				wuw.cnt = wuw.cnt + 1;
				wuw.getBtn_국립민속박물관().setEnabled(false);
				MonumentCommentator me = new MonumentCommentator("국립민속박물관", wuw);
				wuw.getContentPane().add(me);
				me.toFront();
				for (Rudder r : wuw.vr) {
					r.stop();
				}	
				wuw.lf.suspend();

			}
			
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setData(MainProgram wuw, JLabel lbl_icon) {
		this.lbl_icon = lbl_icon;
		this.wuw = wuw;
	}
}
