package project_palace_guide;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class Viewer extends JInternalFrame {
	private JLabel picture;
	private JTextArea content;
	private JLabel lblTitle;
	private JLabel lblWriter;
	private JLabel writer;
	private JLabel title;
	private JButton btnLike;
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnFind;
	private JPanel panel;
	private JTextField getcomment;
	private JLabel lb_title;
	String fileName;
	String id;
	int serial;
	Board bd;
	BrdDao dao;
	BrdVo vo;
	boolean picturechange = false;
	private JLabel lblLike;
	private JLabel lbl_like;
	private JTextField tf_comment;
	private JScrollPane scrollPane;
	private JTextArea comment;
	private JLabel path;

	public Viewer(String id, int serial, Board bd) {
		this();
		this.id = id;
		this.serial = serial;
		this.bd = bd;
		if (dao == null) {
			dao = new BrdDao();
		}
		view_detail();
	}

	public Viewer() {
		super("View", false, true, true, true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				bd.select(bd.getFindstr().getText(), bd.getComboBox().getSelectedItem().toString(), "first");
			}
			
		});
		setBounds(100, 100, 794, 477);
		getContentPane().setLayout(null);
		getContentPane().add(getContent());
		getContentPane().add(getLblTitle());
		getContentPane().add(getLblWriter());
		getContentPane().add(getWriter());
		getContentPane().add(getBtnLike());
		getContentPane().add(getBtnDelete());
		getContentPane().add(getBtnModify());
		getContentPane().add(getBtnFind());
		getContentPane().add(getPicture());
		getContentPane().add(getPanel());
		getContentPane().add(getLb_title());
		getContentPane().add(getLblLike());
		getContentPane().add(getLbl_like());
		getContentPane().add(getPath());
		setVisible(true);
	}

	protected JLabel getPicture() {
		if (picture == null) {
			picture = new JLabel("");
			picture.setBounds(new Rectangle(0, 0, 206, 313));
			picture.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			picture.setBounds(445, 91, 206, 174);
		}
		return picture;
	}

	public void view_detail() {
		this.vo = dao.view(serial);

		lb_title.setText(vo.getTitle());
		writer.setText(vo.getId());
		content.setText(vo.getContent());
		lbl_like.setText(vo.getLike() + "");
		if (id.equals(vo.getId())) {
			Viewer.this.setTitle("Modify");
		}
		if (vo.getId().equals(id)) {
			btnDelete.setEnabled(true);
			btnModify.setEnabled(true);
			btnFind.setEnabled(true);
		}
		if (vo.getPicture() != null) {
			ImageIcon icon = new ImageIcon("C:\\workspaceN\\SemiProject\\server_icon\\" + vo.getPicture().toString());
			Image temp1 = icon.getImage();
			Image temp2 = temp1.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp2);
			picture.setIcon(icon);
		}
		if (vo.getPath() != null) {
			ImageIcon icon = new ImageIcon(vo.getPath());
			Image temp1 = icon.getImage();
			Image temp2 = temp1.getScaledInstance(path.getWidth(), path.getHeight(), Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp2);
			path.setIcon(icon);
		}
		comment.setText("");
		for (String con : vo.getComment()) {
			comment.append(con + "\n");
		}

	}

	protected JTextArea getContent() {
		if (content == null) {
			content = new JTextArea();
			content.setToolTipText("수정하려면 비밀번호를 입력하세요!");
			content.setEnabled(false);
			content.setEditable(false);
			content.setBounds(134, 91, 299, 331);
		}
		return content;
	}

	protected JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Title");
			lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitle.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblTitle.setBounds(134, 57, 84, 24);
		}
		return lblTitle;
	}

	protected JLabel getLblWriter() {
		if (lblWriter == null) {
			lblWriter = new JLabel("Writer");
			lblWriter.setHorizontalAlignment(SwingConstants.LEFT);
			lblWriter.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblWriter.setBounds(134, 23, 84, 24);
		}
		return lblWriter;
	}

	protected JLabel getWriter() {
		if (writer == null) {
			writer = new JLabel("");
			writer.setBounds(194, 28, 145, 15);
		}
		return writer;
	}

	protected JButton getBtnLike() {
		if (btnLike == null) {
			btnLike = new JButton("Like");
			btnLike.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean b = dao.favorite(serial);
					if (b) {
						JOptionPane.showMessageDialog(Viewer.this, "Thumbs up");
						view_detail();
						btnLike.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(Viewer.this, "error");
					}
				}
			});
			btnLike.setBounds(452, 24, 93, 23);
		}
		return btnLike;
	}

	protected JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean b = dao.delete(serial);
					if (b) {
						JOptionPane.showMessageDialog(Viewer.this, "delete completely");
					} else {
						JOptionPane.showMessageDialog(Viewer.this, "error");
					}

				}
			});
			btnDelete.setEnabled(false);
			btnDelete.setBounds(557, 24, 94, 23);
		}
		return btnDelete;
	}

	protected JButton getBtnModify() {
		if (btnModify == null) {
			btnModify = new JButton("Modify");
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BrdVo vo = new BrdVo();
					vo.setContent(content.getText());
					vo.setId(id);
					vo.setTitle(title.getText());
					vo.setSerial(serial);
					boolean pic = false;
					if (picturechange) {
						vo.setPicture(fileName);
						pic = true;
					}

					boolean b = dao.modify(vo, pic);
					if (b) {
						JOptionPane.showMessageDialog(Viewer.this, "Modify complete");
					} else {
						JOptionPane.showMessageDialog(Viewer.this, "error");
					}
				}
			});
			btnModify.setEnabled(false);
			btnModify.setBounds(557, 54, 94, 23);
		}
		return btnModify;
	}

	protected JButton getBtnFind() {
		if (btnFind == null) {
			btnFind = new JButton("Find");
			btnFind.addActionListener(new ActionListener() {
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
						fileName = temp.substring(pos + 1);
						picturechange = true;
					}
				}
			});
			btnFind.setEnabled(false);
			btnFind.setBounds(452, 54, 93, 23);
		}
		return btnFind;
	}

	protected JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(445, 275, 105, 146);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getGetcomment(), BorderLayout.SOUTH);
			panel.add(getTf_comment(), BorderLayout.SOUTH);
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	protected JTextField getGetcomment() {
		if (getcomment == null) {
			getcomment = new JTextField();
			getcomment.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						boolean b = dao.comment(getcomment.getText(), serial, id);
						if (b) {
							view_detail();
						}
					}
				}
			});
			getcomment.setToolTipText("please press enter button if you want to comment!");
			getcomment.setColumns(10);
		}
		return getcomment;
	}

	protected JLabel getLb_title() {
		if (lb_title == null) {
			lb_title = new JLabel("");
			lb_title.setBounds(194, 62, 145, 15);
		}
		return lb_title;
	}

	protected JLabel getLblLike() {
		if (lblLike == null) {
			lblLike = new JLabel("Like");
			lblLike.setHorizontalAlignment(SwingConstants.LEFT);
			lblLike.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblLike.setBounds(357, 23, 47, 24);
		}
		return lblLike;
	}

	protected JLabel getLbl_like() {
		if (lbl_like == null) {
			lbl_like = new JLabel("");
			lbl_like.setBounds(389, 28, 47, 15);
		}
		return lbl_like;
	}

	protected JTextField getTf_comment() {
		if (tf_comment == null) {
			tf_comment = new JTextField();
			tf_comment.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						boolean b = dao.comment(tf_comment.getText(), serial, id);
						if (b) {
							view_detail();
							tf_comment.setText("");
						} else {
							JOptionPane.showMessageDialog(Viewer.this, "error");
						}
					}
				}
			});
			tf_comment.setColumns(10);
		}
		return tf_comment;
	}

	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getComment());
		}
		return scrollPane;
	}

	protected JTextArea getComment() {
		if (comment == null) {
			comment = new JTextArea();
		}
		return comment;
	}

	protected JLabel getPath() {
		if (path == null) {
			path = new JLabel("");
			path.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			path.setBounds(557, 275, 93, 147);
		}
		return path;
	}
}
