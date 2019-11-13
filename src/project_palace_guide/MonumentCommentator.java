package project_palace_guide;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSlider;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MonumentCommentator extends JInternalFrame {
	String place;
	MainProgram wuw;
	MonumentalDao dao;
	MonumentalVo vo;
	File mp3;
	Player player;
	FileInputStream fileInputStream;
	Thread playThread;
	int change = 0;
	int pause;
	int totalLength;
	boolean b = false;
	
	List<String> pictureset;
	
	private JSlider slider;
	private JLabel picture;
	private JButton btn_left;
	private JButton btn_right;
	private JButton btn_pause;
	private JButton btn_stop;
	private JButton btn_start;
	private JLabel monumental;
	
	public MonumentCommentator(String place, MainProgram wuw) {
		this();
		this.place = place;
		this.wuw = wuw;
		if(dao == null) {
			dao = new MonumentalDao();
		}
		select();
		
		dao.insert_cnt(wuw.id, wuw.cnt);
	}
	
	public void select() {
		vo = dao.select(place); 
		mp3 = new File(vo.getExplainmp3()); 
		playThread = new Thread(runnablePlay);
		playThread.start();
		ImageIcon pic1 = show_picture(vo.getPicture1());
		picture.setIcon(pic1);

	}
	
	 Runnable runnablePlay=new Runnable() {
	      @Override
	      public void run() {
	          try {
	              //code for play button
	              fileInputStream=new FileInputStream(mp3);
	              BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
	              player=new Player(bufferedInputStream);
	              totalLength=fileInputStream.available();
	              if(!b) {	            	  
	            	  player.play();//starting music
	              }else {
	            	  fileInputStream.skip(totalLength-pause);
	            	  player.play();
	              }
	             
	          } catch (FileNotFoundException e) {
	              e.printStackTrace();
	          } catch (JavaLayerException e) {
	              e.printStackTrace();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }
	  };
	 private JLabel left;
	 private JLabel right;
	
	public MonumentCommentator() {
		super("Explain",false, true,true,true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				try{
					if(player != null) {
						player.close();						
					}
				}catch(Exception ex) {
					
				}
			}
		});
		setBounds(20, 100, 520, 562);
		getContentPane().setLayout(null);
		getContentPane().add(getSlider());
		getContentPane().add(getPicture());
		getContentPane().add(getBtn_left());
		getContentPane().add(getBtn_right());
		getContentPane().add(getBtn_pause());
		getContentPane().add(getBtn_stop());
		getContentPane().add(getBtn_start());
		getContentPane().add(getPlace());
		getContentPane().add(getLeft());
		getContentPane().add(getRight());
		setVisible(true);
	}
	protected JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setValue(0);
			slider.setBounds(12, 500, 480, 23);
		}//TODO 실력되면 하자
		return slider;
	}
	
	public ImageIcon show_picture(String filename) {
		ImageIcon icon = new ImageIcon(filename);
		Image temp1 = icon.getImage();
		Image temp2 = temp1.getScaledInstance(picture.getWidth(), picture.getHeight(),
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp2);
		return icon;
	}//TODO 버튼이 눌렸다는 불린 값 만들고 다 돌면 경복궁 스티커 발급 그리고 국가별로 카운트 후 경쟁 만들기, lbl_icon이 지나가다 버튼 근처가면 monumentalexplain실행하기
	
	protected JLabel getPicture() {
		if (picture == null) {
			picture = new JLabel("");
			picture.setBounds(12, 10, 480, 355);
		}
		return picture;
	}
	protected JButton getBtn_left() {
		if (btn_left == null) {
			btn_left = new JButton("");
			btn_left.setHorizontalAlignment(SwingConstants.LEFT);
			btn_left.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(change ==0) {
						change = 3;
						picture.setIcon(show_picture(vo.getPicture4()));
					}else if(change == 1) {
						change = 0;
						picture.setIcon(show_picture(vo.getPicture1()));
					}else if(change == 2) {
						change = 1;
						picture.setIcon(show_picture(vo.getPicture2()));
					}else if(change == 3) {
						change = 2;
						picture.setIcon(show_picture(vo.getPicture3()));
					}
					left.repaint();
					right.repaint();
				}
			});
			btn_left.setBounds(0, 10, 254, 355);
			btn_left.setOpaque(false);
			btn_left.setContentAreaFilled(false);
			btn_left.setBorderPainted(false);
		}
		return btn_left;
	}
	protected JButton getBtn_right() {
		if (btn_right == null) {
			btn_right = new JButton("");
			btn_right.setHorizontalAlignment(SwingConstants.RIGHT);
			btn_right.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(change == 3) {
						change = 0;
						picture.setIcon(show_picture(vo.getPicture1()));
					}else if(change == 0) {
						change = 1;
						picture.setIcon(show_picture(vo.getPicture2()));
					}else if(change == 1) {
						change = 2;
						picture.setIcon(show_picture(vo.getPicture3()));
					}else if(change == 2) {
						change = 3;
						picture.setIcon(show_picture(vo.getPicture4()));
					}
					left.repaint();
					right.repaint();
				}
			});
			btn_right.setBounds(250, 10, 254, 355);
			btn_right.setOpaque(false);
			btn_right.setContentAreaFilled(false);
			btn_right.setBorderPainted(false);
		}
		return btn_right;
	}
	protected JButton getBtn_pause() {
		if (btn_pause == null) {
			btn_pause = new JButton("||");
			btn_pause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 if(player!=null){
	                     try {
	                    	 b = true;
	                         pause=fileInputStream.available();
	                         player.close();
	                     } catch (IOException e1) {
	                         e1.printStackTrace();
	                     }
	                 }
				}
			});
			btn_pause.setBounds(203, 467, 97, 23);
		}
		return btn_pause;
	}
	protected JButton getBtn_stop() {
		if (btn_stop == null) {
			btn_stop = new JButton("ㅁ");
			btn_stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					  if(player!=null){
			                player.close();
			                b = false;
			            }
				}
			});
			btn_stop.setBounds(353, 467, 97, 23);
		}
		return btn_stop;
	}
	protected JButton getBtn_start() {
		if (btn_start == null) {
			btn_start = new JButton(">");
			btn_start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playThread.start();
				}
			});
			btn_start.setBounds(53, 467, 97, 23);
		}
		return btn_start;
	}
	protected JLabel getPlace() {
		if (monumental == null) {
			monumental = new JLabel("");
			monumental.setFont(new Font("08서울한강체 L", Font.PLAIN, 18));
			monumental.setBounds(26, 388, 452, 60);
		}
		return monumental;
	}
	protected JLabel getLeft() {
		if (left == null) {
			left = new JLabel("◀");
			left.setEnabled(false);
			left.setHorizontalAlignment(SwingConstants.LEFT);
			left.setBounds(0, 180, 57, 15);
		}
		return left;
	}
	protected JLabel getRight() {
		if (right == null) {
			right = new JLabel("▶");
			right.setEnabled(false);
			right.setHorizontalAlignment(SwingConstants.RIGHT);
			right.setBounds(447, 180, 57, 15);
		}
		return right;
	}
}
