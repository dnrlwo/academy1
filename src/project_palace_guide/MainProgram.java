package project_palace_guide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class MainProgram extends JFrame {

	LoginPanel pl;
	String id;
	LoginDao dao;

	public LoginDao getDao() {
		return dao;
	}

	public void setDao(LoginDao dao) {
		this.dao = dao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private JDesktopPane contentPane;
	private JSeparator separator;
	private JButton btn_left;
	private JButton btn_down;
	private JButton btn_right;
	private JButton btn_up;
	private JLabel lbl_map;
	private JRadioButton radio_geography_map;
	private JRadioButton radio_satellite_map;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBox;
	private JButton btn_seticon;
	public JLabel lbl_icon;
	int mouseX;
	int mouseY;
	Vector<Point> v = new Vector<Point>();
	PathRecorder pr;
	Vector<Rudder> vr = new Vector<Rudder>();
	LocationFinder lf;
	int cnt = 0;//how many place you`re going
	
	private JLayeredPane layeredPane;
	private JButton btn_광화문;
	private JButton btn_국립고궁박물관;
	private JButton btn_홍례문;
	private JButton btn_근정문;
	private JButton btn_근정전;
	private JButton btn_경회루;
	private JButton btn_강녕전;
	private JButton btn_교태전;
	private JButton btn_향원정;
	private JButton btn_건청궁;
	private JButton btn_국립민속박물관;
	private JToggleButton path_record;
	private JToggleButton path_stop;
	private JButton btnNewButton;
	private JButton btn_stop;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgram frame = new MainProgram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainProgram() {
		setResizable(false);
		setPreferredSize(new Dimension(450, 1000));
		setBounds(new Rectangle(0, 0, 450, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 561, 1000);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbl_icon());
		contentPane.add(getLayeredPane_1());
		contentPane.add(getSeparator());
		contentPane.add(getBtn_left());
		contentPane.add(getBtn_down());
		contentPane.add(getBtn_right());
		contentPane.add(getBtn_up());
		contentPane.add(getBtn_stop());
		contentPane.add(getRadio_geography_map());
		contentPane.add(getRadio_satellite_map());
		contentPane.add(getComboBox());
		contentPane.add(getBtn_seticon());
		pl = new LoginPanel(MainProgram.this);
		contentPane.add(pl);
		contentPane.add(getLbl_map());
		contentPane.add(getPath_record());
		contentPane.add(getPath_stop());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());

		pl.toFront();
		lf = new LocationFinder();
		lf.setData(MainProgram.this, lbl_icon);
		try {
			lf.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lf.start();
	}

	protected JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(12, 850, 531, 2);
		}
		return separator;
	}

	protected JButton getBtn_left() {
		if (btn_left == null) {
			btn_left = new JButton("←");
			btn_left.setEnabled(false);
			btn_left.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Rudder rudder = new Rudder();
						vr.add(rudder);
						rudder.setData(comboBox.getSelectedItem().toString(), lbl_icon, 4, MainProgram.this);
						rudder.join();
						rudder.start();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btn_left.setBounds(12, 891, 49, 28);
		}
		return btn_left;
	}

	protected JButton getBtn_down() {
		if (btn_down == null) {
			btn_down = new JButton("↓");
			btn_down.setEnabled(false);
			btn_down.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Rudder rudder = new Rudder();
						vr.add(rudder);
						rudder.setData(comboBox.getSelectedItem().toString(), lbl_icon, 2, MainProgram.this);
						rudder.join();
						rudder.start();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
			btn_down.setBounds(62, 923, 49, 28);
		}
		return btn_down;
	}

	protected JButton getBtn_right() {
		if (btn_right == null) {
			btn_right = new JButton("→");
			btn_right.setEnabled(false);
			btn_right.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Rudder rudder = new Rudder();
						vr.add(rudder);
						rudder.setData(comboBox.getSelectedItem().toString(), lbl_icon, 6, MainProgram.this);
						rudder.join();
						rudder.start();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
			btn_right.setBounds(112, 891, 49, 28);
		}
		return btn_right;
	}

	protected JButton getBtn_up() {
		if (btn_up == null) {
			btn_up = new JButton("↑");
			btn_up.setEnabled(false);
			btn_up.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Rudder rudder = new Rudder();
						vr.add(rudder);
						rudder.setData(comboBox.getSelectedItem().toString(), lbl_icon, 8, MainProgram.this);
						rudder.join();
						rudder.start();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
			btn_up.setBounds(62, 859, 49, 28);
		}
		return btn_up;
	}
	protected JButton getBtn_stop() {
		if (btn_stop == null) {
			btn_stop = new JButton("○");
			btn_stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(Rudder r : vr) {
						r.stop();
					}
				}
			});
			btn_stop.setEnabled(false);
			btn_stop.setBounds(62, 891, 49, 28);
		}
		return btn_stop;
	}
	
	protected JLabel getLbl_map() {
		if (lbl_map == null) {
			lbl_map = new JLabel("");
			lbl_map.setToolTipText("Please select what kind of map do you want");
			lbl_map.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			lbl_map.setOpaque(true);
			lbl_map.setBounds(12, 10, 531, 830);
		}
		return lbl_map;
	}

	protected JRadioButton getRadio_geography_map() {
		if (radio_geography_map == null) {
			radio_geography_map = new JRadioButton("Map");
			radio_geography_map.setEnabled(false);
			radio_geography_map.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					what_kind_of_map(0);
				}
			});
			buttonGroup.add(radio_geography_map);
			radio_geography_map.setOpaque(false);
			radio_geography_map.setBounds(439, 858, 89, 23);
		}
		return radio_geography_map;
	}

	protected JRadioButton getRadio_satellite_map() {
		if (radio_satellite_map == null) {
			radio_satellite_map = new JRadioButton("Satellite");
			radio_satellite_map.setEnabled(false);
			radio_satellite_map.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					what_kind_of_map(1);
				}
			});
			buttonGroup.add(radio_satellite_map);
			radio_satellite_map.setOpaque(false);
			radio_satellite_map.setBounds(439, 880, 89, 23);
		}
		return radio_satellite_map;
	}

	public void what_kind_of_map(int map) {
		String mapPath = null;
		if (map == 0) {
			mapPath = "C:\\workspaceN\\SemiProject\\경복궁 일반지도.png";
		} else if (map == 1) {
			mapPath = "C:\\workspaceN\\SemiProject\\경복궁 위성지도.PNG";
		}
		lbl_map.setIcon(new ImageIcon(mapPath));
		btn_광화문.setEnabled(true);
		btn_강녕전.setEnabled(true);
		btn_건청궁.setEnabled(true);
		btn_경회루.setEnabled(true);
		btn_교태전.setEnabled(true);
		btn_국립고궁박물관.setEnabled(true);
		btn_국립민속박물관.setEnabled(true);
		btn_근정문.setEnabled(true);
		btn_근정전.setEnabled(true);
		btn_향원정.setEnabled(true);
		btn_홍례문.setEnabled(true);

		// TODO 현재위치에 대한 jlabel set visible하기

	}

	protected JComboBox getComboBox() {
		String[] distance = { "5m", "10m", "20m", "30m", "50m", "100m" };
		if (comboBox == null) {
			comboBox = new JComboBox(distance);
			comboBox.setBounds(173, 891, 89, 28);
		}
		return comboBox;
	}

	protected JButton getBtn_seticon() {
		if (btn_seticon == null) {
			btn_seticon = new JButton("Set icon");
			btn_seticon.setEnabled(false);
			btn_seticon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser jc = new JFileChooser();
						int yn = jc.showOpenDialog(null);
						if (yn == JFileChooser.APPROVE_OPTION) {
							ImageIcon icon = new ImageIcon(jc.getSelectedFile().toString());
							Image temp1 = icon.getImage();
							Image temp2 = temp1.getScaledInstance(lbl_icon.getWidth(), lbl_icon.getHeight(),
									Image.SCALE_SMOOTH);
							icon = new ImageIcon(temp2);
							lbl_icon.setIcon(icon);
							lbl_icon.setVisible(true);

							JOptionPane jo = new JOptionPane();
							int yn2 = jo.showConfirmDialog(MainProgram.this,
									"Do you want save the icon what you selected?");
							if (yn2 == jo.OK_OPTION) {

								String temp = jc.getSelectedFile().toString();
								FileUploader fu = new FileUploader();
								fu.setFileName(temp);
								fu.start();

								int pos = temp.lastIndexOf("\\");
								String fileName = temp.substring(pos + 1);
								boolean b = dao.img_upload(fileName, id); // only saved photoname and file extention

								if (b) {
									JOptionPane.showMessageDialog(MainProgram.this, "upload complete.");
								} else {
									JOptionPane.showMessageDialog(MainProgram.this, "error");
								}
							}

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Please select image file");
					}
				}
			});
			btn_seticon.setToolTipText("You can change the image what you can see where you are");
			btn_seticon.setBounds(443, 909, 89, 23);
		}
		return btn_seticon;
	}

	public JLabel getLbl_icon() {
		if (lbl_icon == null) {
			lbl_icon = new JLabel("");

			lbl_icon.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
			lbl_icon.setTransferHandler(new TransferHandler(null));
			lbl_icon.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			});

			lbl_icon.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					lbl_icon.setBounds(e.getXOnScreen() - mouseX - lbl_icon.getWidth(),
							e.getYOnScreen() - mouseY - lbl_icon.getHeight(), lbl_icon.getWidth(),
							lbl_icon.getHeight());
					repaint();
				}
			});

			lbl_icon.setVisible(false);
			lbl_icon.setOpaque(true);
			lbl_icon.setBounds(382, 858, 49, 61);
		}
		return lbl_icon;
	}

	protected JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(12, 10, 531, 830);
			layeredPane.add(getBtn_광화문());
			layeredPane.add(getBtn_국립고궁박물관());
			layeredPane.add(getBtn_홍례문());
			layeredPane.add(getBtn_근정문());
			layeredPane.add(getBtn_근정전());
			layeredPane.add(getBtn_경회루());
			layeredPane.add(getBtn_강녕전());
			layeredPane.add(getBtn_교태전());
			layeredPane.add(getBtn_향원정());
			layeredPane.add(getBtn_건청궁());
			layeredPane.add(getBtn_국립민속박물관());
		}
		return layeredPane;
	}

	protected JButton getBtn_광화문() {
		if (btn_광화문 == null) {
			btn_광화문 = new JButton("");
			btn_광화문.setOpaque(false);
			btn_광화문.setEnabled(false);
			btn_광화문.setContentAreaFilled(false);
			btn_광화문.setBorderPainted(false);
			btn_광화문.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_광화문.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("광화문", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
			btn_광화문.setBounds(209, 780, 97, 31);
		}
		return btn_광화문;
	}

	protected JButton getBtn_국립고궁박물관() {
		if (btn_국립고궁박물관 == null) {
			btn_국립고궁박물관 = new JButton("");
			btn_국립고궁박물관.setOpaque(false);
			btn_국립고궁박물관.setEnabled(false);
			btn_국립고궁박물관.setContentAreaFilled(false);
			btn_국립고궁박물관.setBorderPainted(false);
			btn_국립고궁박물관.setBounds(61, 713, 97, 56);
			btn_국립고궁박물관.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_국립고궁박물관.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("국립고궁박물관", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_국립고궁박물관;
	}

	protected JButton getBtn_홍례문() {
		if (btn_홍례문 == null) {
			btn_홍례문 = new JButton("");
			btn_홍례문.setOpaque(false);
			btn_홍례문.setEnabled(false);
			btn_홍례문.setContentAreaFilled(false);
			btn_홍례문.setBorderPainted(false);
			btn_홍례문.setBounds(221, 677, 97, 31);
			btn_홍례문.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_홍례문.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("홍례문", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_홍례문;
	}

	protected JButton getBtn_근정문() {
		if (btn_근정문 == null) {
			btn_근정문 = new JButton("");
			btn_근정문.setOpaque(false);
			btn_근정문.setContentAreaFilled(false);
			btn_근정문.setBorderPainted(false);
			btn_근정문.setEnabled(false);
			btn_근정문.setBounds(221, 593, 97, 31);
			btn_근정문.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_근정문.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("근정문", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_근정문;
	}

	protected JButton getBtn_근정전() {
		if (btn_근정전 == null) {
			btn_근정전 = new JButton("");
			btn_근정전.setOpaque(false);
			btn_근정전.setContentAreaFilled(false);
			btn_근정전.setBorderPainted(false);
			btn_근정전.setEnabled(false);
			btn_근정전.setBounds(232, 495, 97, 43);
			btn_근정전.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_근정전.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("근정전", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_근정전;
	}

	protected JButton getBtn_경회루() {
		if (btn_경회루 == null) {
			btn_경회루 = new JButton("");
			btn_경회루.setOpaque(false);
			btn_경회루.setContentAreaFilled(false);
			btn_경회루.setBorderPainted(false);
			btn_경회루.setEnabled(false);
			btn_경회루.setBounds(97, 355, 119, 91);
			btn_경회루.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_경회루.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("경회루", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_경회루;
	}

	protected JButton getBtn_강녕전() {
		if (btn_강녕전 == null) {
			btn_강녕전 = new JButton("");
			btn_강녕전.setOpaque(false);
			btn_강녕전.setContentAreaFilled(false);
			btn_강녕전.setBorderPainted(false);
			btn_강녕전.setEnabled(false);
			btn_강녕전.setBounds(232, 423, 112, 23);
			btn_강녕전.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_강녕전.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("강녕전", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_강녕전;
	}

	protected JButton getBtn_교태전() {
		if (btn_교태전 == null) {
			btn_교태전 = new JButton("");
			btn_교태전.setOpaque(false);
			btn_교태전.setContentAreaFilled(false);
			btn_교태전.setBorderPainted(false);
			btn_교태전.setEnabled(false);
			btn_교태전.setBounds(247, 372, 119, 31);
			btn_교태전.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_교태전.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("교태전", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_교태전;
	}

	protected JButton getBtn_향원정() {
		if (btn_향원정 == null) {
			btn_향원정 = new JButton("");
			btn_향원정.setOpaque(false);
			btn_향원정.setContentAreaFilled(false);
			btn_향원정.setBorderPainted(false);
			btn_향원정.setEnabled(false);
			btn_향원정.setBounds(247, 105, 97, 56);
			btn_향원정.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_향원정.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("향원정", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_향원정;
	}

	protected JButton getBtn_건청궁() {
		if (btn_건청궁 == null) {
			btn_건청궁 = new JButton("");
			btn_건청궁.setOpaque(false);
			btn_건청궁.setContentAreaFilled(false);
			btn_건청궁.setBorderPainted(false);
			btn_건청궁.setEnabled(false);
			btn_건청궁.setBounds(232, 24, 112, 71);
			btn_건청궁.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_건청궁.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("건청궁", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_건청궁;
	}

	protected JButton getBtn_국립민속박물관() {
		if (btn_국립민속박물관 == null) {
			btn_국립민속박물관 = new JButton("");
			btn_국립민속박물관.setOpaque(false);
			btn_국립민속박물관.setContentAreaFilled(false);
			btn_국립민속박물관.setBorderPainted(false);
			btn_국립민속박물관.setEnabled(false);
			btn_국립민속박물관.setBounds(400, 149, 119, 150);
			btn_국립민속박물관.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.cnt = MainProgram.this.cnt + 1;
					btn_국립민속박물관.setEnabled(false);
					MonumentCommentator me = new MonumentCommentator("국립민속박물관", MainProgram.this);
					contentPane.add(me);
					me.toFront();
				}
			});
		}
		return btn_국립민속박물관;
	}

	protected JToggleButton getPath_record() {
		if (path_record == null) {
			path_record = new JToggleButton("●");
			path_record.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					path_stop.setEnabled(true);
					try {
						pr = new PathRecorder();
						pr.setdata(MainProgram.this);
						pr.join();
						pr.start();
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			buttonGroup.add(path_record);
			path_record.setEnabled(false);
			path_record.setToolTipText("Save a path");
			path_record.setBounds(307, 858, 68, 23);
		}
		return path_record;
	}

	protected JToggleButton getPath_stop() {
		if (path_stop == null) {
			path_stop = new JToggleButton("□");
			path_stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainProgram.this.v = pr.getV();
					pr.stop();
					btnNewButton.setEnabled(true);
				}
			});
			buttonGroup.add(path_stop);
			path_stop.setToolTipText("Save a path");
			path_stop.setEnabled(false);
			path_stop.setBounds(307, 894, 68, 23);
		}
		return path_stop;
	}
	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("view");
			btnNewButton.setToolTipText("view the recorded path");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					paintComponent(getGraphics());
					
					Rectangle rx = new Rectangle(112, 60, (int)MainProgram.this.layeredPane.getWidth(), (int)MainProgram.this.layeredPane.getHeight());
					try {
					Robot robot = new Robot();
					BufferedImage image = robot.createScreenCapture(rx);
					JOptionPane jo = new JOptionPane();
					int yn2 = jo.showConfirmDialog(MainProgram.this, "Do you want to save the path?");
					if (yn2 == jo.OK_OPTION) {
						File file = new File("C:\\workspaceN\\SemiProject\\path\\" + MainProgram.this.id + ".jpg");
						ImageIO.write(image, "jpg", file);
						
						boolean b = dao.path_insert(MainProgram.this.id, "C:\\workspaceN\\SemiProject\\path\\" + MainProgram.this.id + ".jpg");
						if(b) {
							JOptionPane.showMessageDialog(MainProgram.this, "Save complete");
						}else {
							JOptionPane.showMessageDialog(MainProgram.this, "error");
						}
					}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}
			});
			btnNewButton.setEnabled(false);
			btnNewButton.setBounds(307, 926, 68, 23);
		}
		return btnNewButton;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponents(g);
		g.setColor(Color.red);
		for (int i = 1; i < v.size(); i++) {
			if (v.get(i - 1) == null)
				continue;
			else if (v.get(i) == null)
				continue;
			else
				g.drawLine((int) (v.get(i - 1).getX()+25), (int) (v.get(i - 1).getY()+30),
						(int) (v.get(i).getX()+25), (int) (v.get(i).getY()+30));
		}
		
	}
	
	protected JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Board");
			btnNewButton_1.setEnabled(false);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Board bd = new Board(id);
					
				}
			});
			btnNewButton_1.setBounds(443, 942, 89, 23);
		}
		return btnNewButton_1;
	}
}