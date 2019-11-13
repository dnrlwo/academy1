package project_palace_guide;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Ranking extends JInternalFrame {
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	Connection con;
	Vector<RankVo> v = new Vector<RankVo>();
	private JButton refresh;
	private JLabel lblNewLabel_1;

	public Ranking() {
		super("Rank", false, true, true, true);
		setBounds(100, 100, 723, 480);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getRefresh());
		setVisible(true);
		con = new DBConnector().getCon();
		rank_refresh();
		rank();
	}

	public void rank_refresh() {
		String sql = "update semi_log set gold = 1 where compl >= 11 ";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update semi_log set silver = 1 where 11 > compl and compl >= 9 ";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update semi_log set bronze = 1 where 9 > compl and compl >= 7 ";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			con.commit();			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void rank() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			table.setRowHeight(30);
			String sql = "SELECT nationality, sum(gold) g, sum(silver) s, sum(bronze) b from SEMI_LOG GROUP by NATIONALity order by g desc";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Object> temp = new Vector<Object>();
				temp.add(rs.getString("nationality"));
				temp.add(rs.getInt("g"));
				temp.add(rs.getInt("s"));
				temp.add(rs.getInt("b"));
				temp.add(rs.getInt("g") + rs.getInt("s") + rs.getInt("b"));
				
				model.addRow(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 67, 683, 362);
			scrollPane.setViewportView(getTable());
			
		}
		return scrollPane;
	}

	protected JTable getTable() {
		if (table == null) {
			table = new JTable();
			String[] header = { "Nation", "Gold", "Silver", "Bronze", "Total"};
			DefaultTableModel model = new DefaultTableModel(header, 0);
			table.setModel(model);		
		}
		return table;
	}

	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Rank");
			lblNewLabel.setFont(new Font("a말괄량이", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(120, 10, 467, 47);
		}
		return lblNewLabel;
	}
	protected JButton getRefresh() {
		if (refresh == null) {
			refresh = new JButton("");
			refresh.setOpaque(false);
			refresh.setContentAreaFilled(false);
			refresh.setBorderPainted(false);
			refresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rank_refresh();
					rank();
				}
			});
			refresh.setBounds(621, 10, 74, 47);
		}
		return refresh;
	}
	protected JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setIcon(new ImageIcon("C:\\workspaceN\\SemiProject\\iconfinder_refresh20_216525 (1).png"));
			lblNewLabel_1.setBounds(621, 10, 74, 47);
		}
		return lblNewLabel_1;
	}
}
