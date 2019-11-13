package project_palace_guide;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel extends JInternalFrame {
	boolean login_check;
	String login_id;
	private JLabel lblNewLabel;
	private JLabel lbl_tourismOrganization;
	private JLabel lblPassword;
	private JTextField id;
	private JPasswordField pwd;
	private JButton btnNewButton;
	private JLabel lbl_sooncheon;
	private JButton btnCreate;
	MainProgram wuw;
	LoginDao dao;
	
	public LoginPanel(MainProgram wuw) {
		this();
		this.wuw = wuw;
		
	}
	public LoginPanel() {
		super("Login", false, true, true, true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(50, 100, 450, 319);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLbl_tourismOrganization());
		getContentPane().add(getLblPassword());
		getContentPane().add(getId());
		getContentPane().add(getPwd());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getBtnCreate());
		getContentPane().add(getLbl_sooncheon());
		setVisible(true);
		if(dao == null) {
			dao = new LoginDao();
		}
		
	}

	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID");
			lblNewLabel.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(34, 100, 89, 36);
		}
		return lblNewLabel;
	}
	protected JLabel getLbl_tourismOrganization() {
		if (lbl_tourismOrganization == null) {
			lbl_tourismOrganization = new JLabel("");
			lbl_tourismOrganization.setBounds(81, 10, 271, 80);
			ImageIcon icon = new ImageIcon("C:\\workspaceN\\SemiProject\\tourismOrganizatoin.png");
			Image temp1 = icon.getImage();
			Image temp2 = temp1.getScaledInstance(lbl_tourismOrganization.getWidth(), lbl_tourismOrganization.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp2);
			lbl_tourismOrganization.setIcon(icon);
		}
		return lbl_tourismOrganization;
	}
	protected JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("PassWord");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblPassword.setBounds(34, 139, 89, 36);
		}
		return lblPassword;
	}
	protected JTextField getId() {
		if (id == null) {
			id = new JTextField();
			id.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						pwd.requestFocus();
					}
				}
			});
			id.setBounds(136, 104, 161, 21);
			id.setColumns(10);
		}
		return id;
	}
	protected JPasswordField getPwd() {
		if (pwd == null) {
			pwd = new JPasswordField();
			pwd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ar) {
					if(ar.getKeyCode() == KeyEvent.VK_ENTER) {
						login();
					}
				}
			});
			pwd.setBounds(136, 143, 161, 21);
		}
		return pwd;
	}
	
	public void login() {
		LoginVo vo = dao.login_check(id.getText(), pwd.getText());
		if(vo.getId() != null) {
			JOptionPane.showMessageDialog(wuw, "Wellcome to Korea!");
			wuw.setId(id.getText());
			wuw.setDao(dao);
			wuw.getBtn_down().setEnabled(true);
			wuw.getBtn_left().setEnabled(true);
			wuw.getBtn_right().setEnabled(true);
			wuw.getBtn_seticon().setEnabled(true);
			wuw.getBtn_up().setEnabled(true);
			wuw.getRadio_geography_map().setEnabled(true);
			wuw.getRadio_satellite_map().setEnabled(true);
			wuw.getPath_record().setEnabled(true);
			wuw.getBtn_stop().setEnabled(true);
			wuw.getBtnNewButton_1().setEnabled(true);
			wuw.cnt = vo.getCompl();
			wuw.setTitle(id.getText()+"`s gyeongbokgung palace tour");
			
			if(vo.getImg_user() != null) {
				ImageIcon icon = new ImageIcon("C:\\workspaceN\\SemiProject\\server_icon\\" + vo.getImg_server().toString());
				
				Image temp1 = icon.getImage();
				Image temp2 = temp1.getScaledInstance(wuw.getLbl_icon().getWidth(), wuw.getLbl_icon().getHeight(), Image.SCALE_SMOOTH);
				
				icon = new ImageIcon(temp2);
				wuw.getLbl_icon().setIcon(icon);
				wuw.getLbl_icon().setEnabled(true);
				wuw.getLbl_icon().setVisible(true);
			}
			
			LoginPanel.this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "Please check your password");
		}
	}
	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Login");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			btnNewButton.setBounds(309, 142, 74, 23);
		}
		return btnNewButton;
	}
	protected JLabel getLbl_sooncheon() {
		if (lbl_sooncheon == null) {
			lbl_sooncheon = new JLabel("");
			lbl_sooncheon.setBorder(null);
			lbl_sooncheon.setBounds(0, 210, 434, 80);
			ImageIcon icon = new ImageIcon("C:\\workspaceN\\SemiProject\\순천방문의 해.png");
			Image temp1 = icon.getImage();
			Image temp2 = temp1.getScaledInstance(lbl_sooncheon.getWidth(), lbl_sooncheon.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp2);
			lbl_sooncheon.setIcon(icon);
		}
		return lbl_sooncheon;
	}
	protected JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IdCreator ci = new IdCreator(dao);
					wuw.getContentPane().add(ci);
					ci.toFront();

//					try {
//						Panel_Login.this.setClosed(true);
//					} catch (PropertyVetoException e1) {
//						e1.printStackTrace();
//					}
				}
			});
			btnCreate.setBounds(166, 174, 102, 23);
		}
		return btnCreate;
	}
}
