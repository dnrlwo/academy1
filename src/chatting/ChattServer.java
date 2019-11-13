package chatting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChattServer extends JFrame {
	public Vector<ServerThread> users = new Vector<ServerThread>();
	ServerSocket serverSocket;
	AcceptThread acceptThread;
	boolean acceptThreadFlag = true;

	class AcceptThread extends Thread {
		@Override
		public void run() {
			while (acceptThreadFlag) {
				try {
					textArea.append("클라이언트의 접속을 기다리고 있습니다.\n");
					Socket socket = serverSocket.accept();
					InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
					textArea.append(isa.getHostName() + "님이 접속하셨습니다.\n");

					// 클라이언트로부터 메세지를 수신하는 쓰레드 생성
					ServerThread st = new ServerThread(ChattServer.this, socket);
					users.add(st); // 1:n의 통신을 위해

				} catch (Exception ex) {
					textArea.append("클라이언트 접속중 오류가 발생하였습니다.\n");
				}
			}
		}
	}

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField tfMessage;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField tfIp;
	private JTextField tfPort;
	private JTextField tfUserName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;
	private JList list;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JLabel lblNewLabel_4;
	private JButton button;
	Vector<String> userList = new Vector<String>();
	private JButton btnNewButton_2;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattServer frame = new ChattServer();
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
	public ChattServer() {
		setTitle("서버관리용");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getScrollPane_1(), BorderLayout.CENTER);
		try {
			InetAddress local = InetAddress.getLocalHost();
			String where = local.getHostAddress();
			tfIp.setText(where);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void serverStart() {
		try {
			int p = Integer.parseInt(tfPort.getText());
			serverSocket = new ServerSocket(p);
			textArea.append("[서버가 시작되었습니다.]\n");
			btnNewButton_1.setEnabled(false);
			acceptThreadFlag = true;
			acceptThread = new AcceptThread();
			acceptThread.setDaemon(true);
			acceptThread.start();

		} catch (Exception ex) {
			textArea.append("서버 시작중 오류 발생.. \n");
		}

	}

	public void serverStop() {
		try {
			ChattData data = new ChattData();
			data.setCommand(ChattData.SERVER_STOP);
			data.setId("server");
			data.setMessage("서버를 중지합니다.");
			for (ServerThread st : users) {
				st.oos.writeObject(data);
				st.oos.flush();
				st.stop();
			}
			btnNewButton_1.setEnabled(true);
			button.setEnabled(false);
			users.clear();
			userList.clear();
			list.setListData(userList);

			acceptThreadFlag = false;
			acceptThread.stop();
			serverSocket.close();
		} catch (Exception ex) {

		}
	}

	public void viewMessage(String str) {
		textArea.append(str + "\n");
//		textArea의 스크롤바를 하단으로 이동
		int leng = textArea.getText().length();
		textArea.setCaretPosition(leng);
	}

	public void send() {
		ChattData data = new ChattData();
		data.setId("관리자");
		data.setMessage(tfMessage.getText());
		data.setCommand(ChattData.MESSAGE);
		try {
			for (ServerThread st : users) {
				st.oos.writeObject(data);
				st.oos.flush();
			}
		} catch (Exception ex) {
		}

	}
	
	public void getOut() {
		int[] pos = list.getSelectedIndices(); //강퇴유저의 위치값
		List<String> speak = list.getSelectedValuesList();
		ChattData data = new ChattData();
		data.setMessage("강퇴당하셨습니다.");
		data.setId("관리자");
		data.setCommand(ChattData.SERVER_STOP);
		
		for(int i = pos.length-1 ; i>=0 ; i--) {
			int k = pos[i];
			ServerThread st = users.get(k);
			try {
				st.oos.writeObject(data);
				st.oos.flush();
				st.serverThreadFlag = false;
				st.socket.close();
				users.remove(k); //thread 제거
				userList.remove(k);//JList 제거
				list.setListData(userList);
			}catch(Exception ex) {}
		}
		
		for(int i =0; i<speak.size() ; i++) {
			ChattData data2 = new ChattData();
			data2.setCommand(ChattData.MESSAGE);
			data2.setId("관리자");
			data2.setMessage("사용자" + speak.get(i) + "를(을) 강퇴했습니다.");
			for(int k = 0; k<users.size() ; k++) {
				ServerThread st = users.get(k);
				try {
					st.oos.writeObject(data2);
					st.oos.flush();
				}catch(Exception ex) {
					
				}
			}
			
		}
		
		
	}
	public void whisper() {
		ChattData data = new ChattData();
		data.setCommand(ChattData.WHISPER);
		data.setId("관리자");
		data.setMessage(tfMessage.getText());

		int[] k = list.getSelectedIndices();
		for(int i = k.length-1 ; i>=0 ; i--) {
			int j = k[i];
			ServerThread st = users.get(j);
			try {
				st.oos.writeObject(data);
				st.oos.flush();
			}catch(Exception ex) {}
		}

	}
	protected JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 70));
			panel.setLayout(null);
			panel.add(getBtnNewButton_1());
			panel.add(getTfIp());
			panel.add(getTfPort());
			panel.add(getTfUserName());
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getLblNewLabel_2());
			panel.add(getButton());
		}
		return panel;
	}

	protected JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 50));
			panel_1.setLayout(null);
			panel_1.add(getTfMessage());
			panel_1.add(getBtnNewButton());
			panel_1.add(getButton_1());
		}
		return panel_1;
	}

	protected JTextField getTfMessage() {
		if (tfMessage == null) {
			tfMessage = new JTextField();
			tfMessage.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						send();
						tfMessage.setText("");
						tfMessage.requestFocus();
					}
				}
			});
			tfMessage.setBounds(109, 11, 565, 30);
			tfMessage.setColumns(10);
		}
		return tfMessage;
	}

	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("전송");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					send();
					tfMessage.setText("");
					tfMessage.requestFocus();
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			btnNewButton.setBounds(686, 8, 97, 30);
		}
		return btnNewButton;
	}

	protected JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("서버 시작");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					serverStart();
				}
			});
			btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			btnNewButton_1.setBounds(582, 10, 97, 51);
		}
		return btnNewButton_1;
	}

	protected JTextField getTfIp() {
		if (tfIp == null) {

			tfIp = new JTextField();
			tfIp.setBounds(12, 40, 270, 21);
			tfIp.setColumns(10);
		}
		return tfIp;
	}

	protected JTextField getTfPort() {
		if (tfPort == null) {
			tfPort = new JTextField();
			tfPort.setBounds(294, 40, 132, 21);
			tfPort.setColumns(10);
		}
		return tfPort;
	}

	protected JTextField getTfUserName() {
		if (tfUserName == null) {
			tfUserName = new JTextField();
			tfUserName.setBounds(438, 40, 132, 21);
			tfUserName.setColumns(10);
		}
		return tfUserName;
	}

	protected JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("IP");
			lblNewLabel.setBounds(12, 15, 57, 15);
		}
		return lblNewLabel;
	}

	protected JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("PORT");
			lblNewLabel_1.setBounds(294, 15, 57, 15);
		}
		return lblNewLabel_1;
	}

	protected JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("User Name");
			lblNewLabel_2.setBounds(438, 15, 97, 15);
		}
		return lblNewLabel_2;
	}

	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 2));
			scrollPane.setViewportView(getList());
			scrollPane.setColumnHeaderView(getLblNewLabel_3());
			scrollPane.setRowHeaderView(getBtnNewButton_2());
		}
		return scrollPane;
	}

	protected JList getList() {
		if (list == null) {
			list = new JList();
			list.setListData(userList);
//			list.updateUI();
		}
		return list;
	}

	protected JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("접속자 목록");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		}
		return lblNewLabel_3;
	}

	protected JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTextArea());
			scrollPane_1.setColumnHeaderView(getLblNewLabel_4());
		}
		return scrollPane_1;
	}

	protected JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	protected JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("대화 내용");
			lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_4;
	}

	protected JButton getButton() {
		if (button == null) {
			button = new JButton("서버 종료");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					serverStop();
				}
			});
			button.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			button.setBounds(691, 10, 97, 51);
		}
		return button;
	}
	protected JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("강퇴");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getOut();
				}
			});
			btnNewButton_2.setPreferredSize(new Dimension(57, 10));
		}
		return btnNewButton_2;
	}
	protected JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("귓속말");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					whisper();
				}
			});
			button_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			button_1.setBounds(0, 8, 97, 30);
		}
		return button_1;
	}
}
