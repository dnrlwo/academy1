package project_palace_guide;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inserter extends JInternalFrame {
	String id;
	BrdDao dao;
	String fileName;
	boolean picturechange =false;
	private JLabel label;
	private JLabel label_1;
	private JTextArea content;
	private JLabel picture;
	private JLabel writer;
	private JButton button;
	private JLabel path;
	private JButton btnSave;
	private JTextField textField;
	
	public Inserter(String id) {
		this();
		this.id = id;
		if(dao ==null) {
			dao = new BrdDao();
		}
		writer.setText(id);
		this.setVisible(true);
		
		BrdVo v = dao.pathInsert(id);
		if (v.getPath() != null) {
			ImageIcon icon = new ImageIcon(v.getPath());
			Image temp1 = icon.getImage();
			Image temp2 = temp1.getScaledInstance(path.getWidth(), path.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp2);
			path.setIcon(icon);
		}
	}
	
	public Inserter() {
		super("Insert", false, true, true, true);
		setBounds(100, 100, 794, 477);
		getContentPane().setLayout(null);
		getContentPane().add(getLabel());
		getContentPane().add(getLabel_1());
		getContentPane().add(getContent());
		getContentPane().add(getPicture());
		getContentPane().add(getWriter());
		getContentPane().add(getButton());
		getContentPane().add(getPath());
		getContentPane().add(getBtnSave());
		getContentPane().add(getTextField());
		
	}

	protected JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Writer");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			label.setBounds(122, 10, 84, 24);
		}
		return label;
	}
	protected JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Title");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			label_1.setBounds(122, 44, 84, 24);
		}
		return label_1;
	}
	protected JTextArea getContent() {
		if (content == null) {
			content = new JTextArea();
			content.setBounds(122, 78, 299, 331);
		}
		return content;
	}
	protected JLabel getPicture() {
		if (picture == null) {
			picture = new JLabel("");
			picture.setBounds(new Rectangle(0, 0, 206, 313));
			picture.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			picture.setBounds(433, 78, 206, 162);
		}
		return picture;
	}
	protected JLabel getWriter() {
		if (writer == null) {
			writer = new JLabel("");
			writer.setBounds(184, 15, 103, 15);
		}
		return writer;
	}

	protected JButton getButton() {
		if (button == null) {
			button = new JButton("Find");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser jc = new JFileChooser();
					int yn = jc.showOpenDialog(null);
					if (yn == JFileChooser.APPROVE_OPTION) {
						ImageIcon icon = new ImageIcon(jc.getSelectedFile().toString());
						Image temp1 = icon.getImage();
						Image temp2 = temp1.getScaledInstance(picture.getWidth(), picture.getHeight(),
								Image.SCALE_SMOOTH);
						icon = new ImageIcon(temp2);
						picture.setIcon(icon);
						
						String temp = jc.getSelectedFile().toString();
						FileUploader fu = new FileUploader();
						fu.setFileName(temp);
						fu.start();

						int pos = temp.lastIndexOf("\\");
						fileName = temp;
						picturechange = true;
				}
				}
			});
			button.setBounds(548, 353, 93, 23);
		}
		return button;
	}
	protected JLabel getPath() {
		if (path == null) {
			path = new JLabel("");
			path.setBounds(new Rectangle(0, 0, 206, 313));
			path.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			path.setBounds(433, 247, 103, 162);
		}
		return path;
	}
	protected JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BrdVo vo = new BrdVo();
					vo.setId(id);
					vo.setContent(content.getText());
					vo.setPicture(fileName);
					vo.setTitle(textField.getText());
					boolean b = dao.insert(vo, picturechange);
					if(b) {
						JOptionPane.showMessageDialog(Inserter.this, "Save complete");
						dispose();
					}else {
						JOptionPane.showMessageDialog(Inserter.this, "error");
					}
				}
			});
			btnSave.setBounds(546, 386, 93, 23);
		}
		return btnSave;
	}
	protected JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(172, 44, 249, 21);
			textField.setColumns(10);
		}
		return textField;
	}
}
