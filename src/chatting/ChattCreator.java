package chatting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class ChattCreator extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea message;
	private JButton btnNewButton;
	private JPanel panel_1;
	private JTextArea send_message;
	private JButton btnNewButton_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JCheckBox enter_send;


	public ChattCreator() {
		setTitle("Talk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		this.setVisible(true);
	}

	protected JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 100));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getBtnNewButton(), BorderLayout.EAST);
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getSend_message(), BorderLayout.CENTER);
		}
		return panel;
	}
	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getMessage());
		}
		return scrollPane;
	}
	protected JTextArea getMessage() {
		if (message == null) {
			message = new JTextArea();
		}
		return message;
	}
	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Send");
			btnNewButton.setPreferredSize(new Dimension(70, 23));
		}
		return btnNewButton;
	}
	protected JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 25));
			panel_1.setLayout(null);
			panel_1.add(getBtnNewButton_1());
			panel_1.add(getButton());
			panel_1.add(getButton_1());
			panel_1.add(getButton_2());
			panel_1.add(getEnter_send());
		}
		return panel_1;
	}
	protected JTextArea getSend_message() {
		if (send_message == null) {
			send_message = new JTextArea();
		}
		return send_message;
	}
	protected JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setBounds(21, 0, 45, 23);
		}
		return btnNewButton_1;
	}
	protected JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBounds(87, 0, 45, 23);
		}
		return button;
	}
	protected JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("");
			button_1.setBounds(153, 0, 45, 23);
		}
		return button_1;
	}
	protected JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("");
			button_2.setBounds(219, 0, 45, 23);
		}
		return button_2;
	}
	protected JCheckBox getEnter_send() {
		if (enter_send == null) {
			enter_send = new JCheckBox("");
			enter_send.setHorizontalAlignment(SwingConstants.CENTER);
			enter_send.setBounds(285, 0, 29, 23);
		}
		return enter_send;
	}
}
