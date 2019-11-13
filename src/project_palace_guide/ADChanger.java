package project_palace_guide;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ADChanger extends Thread{
	JLabel ad;
	Vector<ImageIcon> image = new Vector<ImageIcon>();
	
	public Vector<ImageIcon> getImage() {
		return image;
	}

	public void setData(JLabel ad) {
		this.ad = ad;
		setsize();
	}
	
	public void setsize() {
		ImageIcon icon = null;
		Image temp1 = null;
		Image temp2 = null;
		
		icon = new ImageIcon("C:\\workspaceN\\SemiProject\\경복궁.png");
		temp1 = icon.getImage();
		temp2 = temp1.getScaledInstance(100, 500,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		this.image.add(icon);
		
		icon = new ImageIcon("C:\\workspaceN\\SemiProject\\경희궁.png");
		temp1 = icon.getImage();
		temp2 = temp1.getScaledInstance(100, 500,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		this.image.add(icon);
		
		icon = new ImageIcon("C:\\workspaceN\\SemiProject\\덕수궁.png");
		temp1 = icon.getImage();
		temp2 = temp1.getScaledInstance(100, 500,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		this.image.add(icon);
		
		icon = new ImageIcon("C:\\workspaceN\\SemiProject\\창경궁.png");
		temp1 = icon.getImage();
		temp2 = temp1.getScaledInstance(100, 500,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		this.image.add(icon);
		
		icon = new ImageIcon("C:\\workspaceN\\SemiProject\\창덕궁.png");
		temp1 = icon.getImage();
		temp2 = temp1.getScaledInstance(100, 500,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		this.image.add(icon);
	}
	
	public void run() {
		int i = 0;
		while(true) {
			if(i==4) {
				i=0;
			}
			try {
			ImageIcon ic = image.get(i);
			ad.setIcon(ic);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
