package calendar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calendar_ex extends JFrame {

	private JDesktopPane contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField tf_month;
	private JTextField tf_year;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar_ex frame = new Calendar_ex();
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
	public void show_claendar() {
		test3 t3 = new test3();
		CaledarVo vo = t3.testt(Integer.parseInt(tf_year.getText()), Integer.parseInt(tf_month.getText()));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		List<Integer> f_1 = vo.getWeek_1();
		List<Integer> f_2 = vo.getWeek_2();
		List<Integer> f_3 = vo.getWeek_3();
		List<Integer> f_4 = vo.getWeek_4();
		List<Integer> f_5 = vo.getWeek_5();
		
		Vector<Object> temp_1 = new Vector<Object>();
		for(int i =0 ; i<f_1.size() ; i++) {
		temp_1.add(f_1.get(i));}
		model.addRow(temp_1);
		
		Vector<Object> temp_2 = new Vector<Object>();
		for(int i =0 ; i<f_2.size() ; i++) {
		temp_2.add(f_2.get(i));}
		model.addRow(temp_2);
		
		Vector<Object> temp_3 = new Vector<Object>();
		for(int i =0 ; i<f_3.size() ; i++) {
		temp_3.add(f_3.get(i));}
		model.addRow(temp_3);
		
		Vector<Object> temp_4 = new Vector<Object>();
		for(int i =0 ; i<f_4.size() ; i++) {
		temp_4.add(f_4.get(i));}
		model.addRow(temp_4);
		
		Vector<Object> temp_5 = new Vector<Object>();
		for(int i =0 ; i<f_5.size() ; i++) {
		temp_5.add(f_5.get(i));}
		model.addRow(temp_5);
		table.setRowHeight(50);
		
		
		Vector<CaledarVo> v = new Vector<CaledarVo>();
	}
	
	public Calendar_ex() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 648);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTable());
		contentPane.add(getLblNewLabel());
		contentPane.add(getTf_month());
		contentPane.add(getTf_year());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getBtnNewButton());
	}
	protected JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBounds(12, 91, 680, 519);
			String[] header = { "일", "월", "화", "수", "목", "금", "토" };
			DefaultTableModel model = new DefaultTableModel(header, 0);
			table.setModel(model);
		}
		return table;
	}
	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("월");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(316, 10, 111, 40);
		}
		return lblNewLabel;
	}
	
	public void create_month() {
		
	}
	protected JTextField getTf_month() {
		if (tf_month == null) {
			tf_month = new JTextField();
			tf_month.setBounds(244, 20, 116, 21);
			tf_month.setColumns(10);
		}
		return tf_month;
	}
	protected JTextField getTf_year() {
		if (tf_year == null) {
			tf_year = new JTextField();
			tf_year.setBounds(71, 20, 116, 21);
			tf_year.setColumns(10);
		}
		return tf_year;
	}
	protected JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setBounds(186, 23, 57, 15);
		}
		return lblNewLabel_1;
	}
	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					show_claendar();
				}
			});
			btnNewButton.setBounds(386, 19, 97, 23);
		}
		return btnNewButton;
	}
}
