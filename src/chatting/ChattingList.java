package chatting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project_palace_guide.Board;
import project_palace_guide.Viewer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChattingList extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingList frame = new ChattingList();
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
	public ChattingList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnNewButton());
	}
	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 72, 772, 476);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	
	protected JTable getTable() {
		if (table == null) {
			table = new JTable() {

				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
			String[] header = {"No", "title", "HostName"};
			DefaultTableModel model = new DefaultTableModel(header, 0);
			table.setModel(model);		
			
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int serial = (int) table.getValueAt(table.getSelectedRow(), 0);
						
					//TODO 클릭시 해당 채팅방 연결
					}
				}
			});
		}
		return table;
	}
	
	
	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ChattingList");
			lblNewLabel.setFont(new Font("a말괄량이", Font.BOLD, 17));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(233, 10, 329, 52);
		}
		return lblNewLabel;
	}
	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New chattroom");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ChattCreator cc = new ChattCreator();
				}
			});
			btnNewButton.setBounds(665, 10, 119, 52);
		}
		return btnNewButton;
	}
}
