package project_palace_guide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Board extends JFrame {
	
	private JDesktopPane contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	public JLabel Ad_left;

	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btn_search;
	private JPanel panel_3;
	private JButton btn_lastpage;
	private JButton btn_nextpage;
	private JButton btn_previouspage;
	private JButton btn_firstpage;
	private JTextField findstr;

	private JButton btn_create;
	private JScrollPane scrollPane;
	private JTable table_2;
	private JLabel status_page;
	private JComboBox comboBox;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	
	String id;
	BrdDao dao;
	ADChanger adc;
	
	public Board(String id) {
		this();
		this.id = id;
		if(dao == null) {
			dao = new BrdDao();
		}
		this.setVisible(true);
		select("", "Newest", "first");
	}
	public Board() {
		setTitle("Bulletinvboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 587);
		setJMenuBar(getMenuBar_1());
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getAd_left(), BorderLayout.WEST);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		
	}

	protected JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 35));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblNewLabel(), BorderLayout.CENTER);
			panel.add(getBtn_create(), BorderLayout.WEST);
			panel.add(getComboBox(), BorderLayout.EAST);
		}
		return panel;
	}

	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Board");
			lblNewLabel.setFont(new Font("a말괄량이", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	
	public void select(String findstr, String sort, String np) {
		String temp = null;
		if(sort.equals("Newest")) {
			temp = "SERIAL";
		}else if(sort.equals("MostLike")) {
			temp = "FAVORITE";
		}else if(sort.equals("MostComment")) {
			temp = "CNT_COMMENT";
		}else if(sort.equals("MostViews")) {
			temp = "VIEWS";
		}
		
		Vector<BrdVo> v = null;
		
		v = dao.select(findstr, temp, np);
		
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0);
		table_2.setRowHeight(30);
		
		if( v!= null) {
			for(BrdVo bv : v) {
				Vector<Object> tempo = new Vector<Object>();
				tempo.add(bv.getSerial());
				tempo.add(bv.getTitle());
				tempo.add(bv.getId());
				tempo.add(bv.getViews());
				tempo.add(bv.getLike());
				tempo.add(bv.getComment().size()); //코멘트 개수
				
				model.addRow(tempo);
			}
		}else {JOptionPane.showMessageDialog(null, "불러오기 오류");}
		
		status_page.setText(dao.nowpage + " / " + dao.totpage);

	}

	public JLabel getAd_left() {
		if (Ad_left == null) {
			Ad_left = new JLabel("");
			Ad_left.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					String site = "";
					if(Ad_left.getIcon().toString().equals(adc.image.get(0).toString())){
						site = "http://www.royalpalace.go.kr:8080/html/eng_gbg/main/main.jsp";
					}else if(Ad_left.getIcon().toString().equals(adc.image.get(1).toString())) {
						site = "http://www.cgcm.go.kr/GHP_HOME/jsp/MM00/main.jsp";
					}else if(Ad_left.getIcon().toString().equals(adc.image.get(2).toString())) {
						site = "http://www.deoksugung.go.kr:8081/";
					}else if(Ad_left.getIcon().toString().equals(adc.image.get(3).toString())) {
						site = "http://english.cha.go.kr/html/HtmlPage.do?pg=/royal/RoyalPalaces_3.jsp&mn=EN_02_03_03";
					}else if(Ad_left.getIcon().toString().equals(adc.image.get(4).toString())) {
						site = "http://www.cdg.go.kr/eng/";
					}
					
					try {
						Desktop.getDesktop().browse(new URI(site));
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			Ad_left.setBorder(new LineBorder(new Color(0, 0, 0)));
			Ad_left.setHorizontalAlignment(SwingConstants.CENTER);
			Ad_left.setPreferredSize(new Dimension(100, 15));
			Ad_left.setToolTipText("광고판");
			Ad_left.setOpaque(true);
			try {
			adc = new ADChanger();
			adc.setData(Ad_left);
			adc.join();
			adc.start();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return Ad_left;
	}

	protected JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getPanel_2_1(), BorderLayout.SOUTH);
			panel_1.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_1;
	}

	protected JPanel getPanel_2_1() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setPreferredSize(new Dimension(10, 50));
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getBtn_search(), BorderLayout.EAST);
			panel_2.add(getPanel_3(), BorderLayout.SOUTH);
			panel_2.add(getFindstr(), BorderLayout.CENTER);
		}
		return panel_2;
	}

	protected JButton getBtn_search() {
		if (btn_search == null) {
			btn_search = new JButton("Search");
			btn_search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					select(findstr.getText(), comboBox.getSelectedItem().toString(), "first");
				}
			});
		}
		return btn_search;
	}

	protected JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(10, 20));
			panel_3.setLayout(null);
			panel_3.add(getBtn_lastpage());
			panel_3.add(getBtn_nextpage());
			panel_3.add(getBtn_previouspage());
			panel_3.add(getBtn_firstpage());
			panel_3.add(getStatus_page());
		}
		return panel_3;
	}

	protected JButton getBtn_lastpage() {
		if (btn_lastpage == null) {
			btn_lastpage = new JButton(">>");
			btn_lastpage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					select(findstr.getText(), comboBox.getSelectedItem().toString(), "lastpage");
				}
			});
			btn_lastpage.setToolTipText("마지막 페이지로.");
			btn_lastpage.setBounds(603, 0, 97, 20);
		}
		return btn_lastpage;
	}

	protected JButton getBtn_nextpage() {
		if (btn_nextpage == null) {
			btn_nextpage = new JButton(">");
			btn_nextpage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					select(findstr.getText(), comboBox.getSelectedItem().toString(), "nextpage");
				}
			});
			btn_nextpage.setBounds(455, 0, 97, 20);
		}
		return btn_nextpage;
	}

	protected JButton getBtn_previouspage() {
		if (btn_previouspage == null) {
			btn_previouspage = new JButton("<");
			btn_previouspage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					select(findstr.getText(), comboBox.getSelectedItem().toString(), "preview");
				}
			});
			btn_previouspage.setToolTipText("이전 페이지로.");
			btn_previouspage.setBounds(199, 0, 97, 20);
		}
		return btn_previouspage;
	}

	protected JButton getBtn_firstpage() {
		if (btn_firstpage == null) {
			btn_firstpage = new JButton("<<");
			btn_firstpage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					select(findstr.getText(), comboBox.getSelectedItem().toString(), "first");
				}
			});
			btn_firstpage.setToolTipText("첫 페이지로.");
			btn_firstpage.setBounds(51, 0, 97, 20);
		}
		return btn_firstpage;
	}

	protected JTextField getFindstr() {
		if (findstr == null) {
			findstr = new JTextField();
			findstr.setToolTipText("검색어를 입력하세요");
			findstr.setColumns(10);
		}
		return findstr;
	}

	protected JButton getBtn_create() {
		if (btn_create == null) {
			btn_create = new JButton("Create");
			btn_create.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Inserter insert = new Inserter(id);
					contentPane.add(insert);
					insert.toFront();
				}
			});
			btn_create.setPreferredSize(new Dimension(100, 23));
		}
		return btn_create;
	}
	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable_2());
		}
		return scrollPane;
	}
	protected JTable getTable_2() {
		if (table_2 == null) {
		
		table_2 = new JTable() {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		
		String[] header = { "No", "Title", "ID", "Views", "Like", "Comment" };
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table_2.setModel(model);		
		
		
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int serial = (int) table_2.getValueAt(table_2.getSelectedRow(), 0);
					Viewer view = new Viewer(id, serial, Board.this);
					contentPane.add(view);
					view.toFront();
				}
			}
		});
		
	}
	return table_2;
	}
	protected JLabel getStatus_page() {
		if (status_page == null) {
			status_page = new JLabel("");
			status_page.setFont(new Font("a말괄량이", Font.BOLD, 14));
			status_page.setBounds(347, 0, 57, 18);
		}
		return status_page;
	}
	String[] district = { "Newest", "MostLike", "MostComment", "MostViews"};
	protected JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(district);
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					select("", comboBox.getSelectedItem().toString(), "first");
				}
			});
		}
		return comboBox;
	}
	protected JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
		}
		return menuBar;
	}
	protected JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Ranking");
			mnNewMenu.addMenuListener(new MenuListener() {
				public void menuCanceled(MenuEvent arg0) {
				}
				public void menuDeselected(MenuEvent arg0) {
				}
				public void menuSelected(MenuEvent arg0) {
					Ranking ranking = new Ranking();
					contentPane.add(ranking);
					ranking.toFront();
				}
			});
			mnNewMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ranking ranking = new Ranking();
					contentPane.add(ranking);
					ranking.toFront();
				}
			});
		}
		return mnNewMenu;
	}
}
