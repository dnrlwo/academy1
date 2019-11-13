package project_palace_guide;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class IdCreator extends JInternalFrame {
	private JLabel label;
	private JTextField tf_id;
	private JPasswordField tf_pwd;
	private JLabel label_1;
	private JLabel lblCreateAccount;
	private JLabel lblPasswordCheck;
	private JPasswordField tf_pwd_check;
	private JLabel pwd_check;
	private JLabel Id_check;
	private JLabel lblNationality;
	private JComboBox comboBox_nation;
	private JButton btn_create;
	LoginDao dao;
	
	public IdCreator(LoginDao dao) {
		this();
		this.dao = dao;
	}
	public IdCreator() {
		super("CreateCount", false, true,true,true);
		setBounds(50, 100, 450, 337);
		getContentPane().setLayout(null);
		getContentPane().add(getLabel());
		getContentPane().add(getTf_id());
		getContentPane().add(getTf_pwd());
		getContentPane().add(getLabel_1());
		getContentPane().add(getLblCreateAccount());
		getContentPane().add(getLblPasswordCheck());
		getContentPane().add(getTf_pwd_check());
		getContentPane().add(getPwd_check());
		getContentPane().add(getId_check());
		getContentPane().add(getLblNationality());
		getContentPane().add(getComboBox_nation());
		getContentPane().add(getBtn_create());
		setVisible(true);
	}
	protected JLabel getLabel() {
		if (label == null) {
			label = new JLabel("ID");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			label.setBounds(12, 56, 89, 36);
		}
		return label;
	}
	protected JTextField getTf_id() {
		if (tf_id == null) {
			tf_id = new JTextField();
			tf_id.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					boolean b = dao.id_check(tf_id.getText());
					if(!b) {
						Id_check.setForeground(Color.red);
						Id_check.setText("That username is taken");
					}else{
						Id_check.setText("");
					}
				}
			});
			tf_id.setColumns(10);
			tf_id.setBounds(114, 60, 161, 21);
		}
		return tf_id;
	}
	protected JPasswordField getTf_pwd() {
		if (tf_pwd == null) {
			tf_pwd = new JPasswordField();
			tf_pwd.setBounds(114, 124, 161, 21);
		}
		return tf_pwd;
	}
	protected JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("PassWord");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			label_1.setBounds(12, 120, 89, 36);
		}
		return label_1;
	}
	protected JLabel getLblCreateAccount() {
		if (lblCreateAccount == null) {
			lblCreateAccount = new JLabel("Create Account");
			lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
			lblCreateAccount.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			lblCreateAccount.setBounds(136, 10, 161, 36);
		}
		return lblCreateAccount;
	}
	protected JLabel getLblPasswordCheck() {
		if (lblPasswordCheck == null) {
			lblPasswordCheck = new JLabel("Confirm");
			lblPasswordCheck.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPasswordCheck.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblPasswordCheck.setBounds(0, 179, 101, 36);
		}
		return lblPasswordCheck;
	}
	protected JPasswordField getTf_pwd_check() {
		if (tf_pwd_check == null) {
			tf_pwd_check = new JPasswordField();
			tf_pwd_check.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(tf_pwd.getText().equals(tf_pwd_check.getText())) {
						btn_create.setEnabled(true);
						pwd_check.setText("");
					}else {
						pwd_check.setForeground(Color.red);
						pwd_check.setText("Those passwords didn`t match.");
					}
				}
			});
			tf_pwd_check.addMouseListener(new MouseAdapter() {
			});
			tf_pwd_check.setBounds(114, 187, 161, 21);
		}
		return tf_pwd_check;
	}
	protected JLabel getPwd_check() {
		if (pwd_check == null) {
			pwd_check = new JLabel("");
			pwd_check.setBounds(114, 218, 308, 22);
		}
		return pwd_check;
	}
	protected JLabel getId_check() {
		if (Id_check == null) {
			Id_check = new JLabel("");
			Id_check.setBounds(113, 91, 309, 22);
		}
		return Id_check;
	}
	protected JLabel getLblNationality() {
		if (lblNationality == null) {
			lblNationality = new JLabel("Nationality");
			lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNationality.setFont(new Font("a말괄량이", Font.PLAIN, 15));
			lblNationality.setBounds(12, 255, 89, 36);
		}
		return lblNationality;
	}
	protected JComboBox getComboBox_nation() {
		if (comboBox_nation == null) {
			String[] countries = new String[]{" ","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
			comboBox_nation = new JComboBox(countries);
			comboBox_nation.setBounds(114, 263, 161, 21);
		}
		return comboBox_nation;
	}
	protected JButton getBtn_create() {
		if (btn_create == null) {
			btn_create = new JButton("Create");
			btn_create.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoginVo vo = new LoginVo();
					vo.setId(tf_id.getText());
					vo.setPwd(tf_pwd_check.getText());
					vo.setNationality(comboBox_nation.getSelectedItem().toString());
					
					boolean b = dao.insert(vo);
					if(b) {
						JOptionPane.showMessageDialog(null, "Wellcome to Korea!");
						IdCreator.this.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "error");
					}
				}
			});
			btn_create.setEnabled(false);
			btn_create.setBounds(287, 262, 97, 23);
		}
		return btn_create;
	}
}
