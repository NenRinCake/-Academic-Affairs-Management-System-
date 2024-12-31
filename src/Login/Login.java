package Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import StudentOperate.*;
import TeacherOperate.*;
import AdministratorOperate.*;

@SuppressWarnings("unused")
public class Login {
	
	JFrame loginWindow;
	JLabel loginLabel;
	JTextField name;
	JLabel nameLabel;
	JPasswordField password;
	JLabel passwordLabel;
	JButton yes;
	JButton no;
	JComboBox<String> identity;
	JLabel identityLabel;
	String nameString;
	String passwordString;
	String identityString;
	JLabel error;
	StudentWindow sw;
	TeacherWindow tw;
	AdministratorWindow aw;
	Connection con;
	Statement stmt;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Login() throws Exception{
		
		loginWindow = new JFrame("登录");
		loginWindow.setSize(550,350);		
		loginWindow.setResizable(false);
		loginWindow.setUndecorated(true);
		loginWindow.setLayout(null);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        loginWindow.setIconImage(image);
        ImageIcon background = new ImageIcon("src/Picture/picture2.GIF");
		JLabel back = new JLabel(background);
		back.setBounds(0,0,loginWindow.getSize().width,loginWindow.getSize().height);
		JPanel imagePanel = (JPanel) loginWindow.getContentPane();
		imagePanel.setOpaque(false);
		loginWindow.getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));
        Dimension dim = kit.getScreenSize();
      	int x = (dim.width-loginWindow.getSize().width)/2;
      	int y = (dim.height-loginWindow.getSize().height)/2;
      	loginWindow.setLocation(x,y);
      	
      	String label[]= {"用户名","密   码","登 录","身   份","用户名或密码错误"};
      	nameLabel = new JLabel(label[0]);
		name = new JTextField(20);
		passwordLabel = new JLabel(label[1]);
		password = new JPasswordField(20);
		yes = new JButton("确定");
		no = new JButton("取消");
		loginLabel = new JLabel(label[2]);
		identityLabel = new JLabel(label[3]);
		identity = new JComboBox();
		identity.addItem("学       生");
		identity.addItem("教       师");
		identity.addItem("管  理  员");
		identity.setSelectedIndex(0);
		error = new JLabel(label[4]);	
		
		//改变字体和大小
		loginLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		nameLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		passwordLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		password.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		identityLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		identity.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		error.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		loginWindow.add(nameLabel);
		loginWindow.add(name);
		loginWindow.add(passwordLabel);
		loginWindow.add(password);
		loginWindow.add(yes);
		loginWindow.add(no);
		loginWindow.add(loginLabel);
		loginWindow.add(identityLabel);
		loginWindow.add(identity);
		loginWindow.add(error);
		
		loginLabel.setBounds(20, 10, 50, 30);
		nameLabel.setBounds(50, 110, 50, 30);
		name.setBounds(100, 110, 150, 30);
		passwordLabel.setBounds(50, 150, 50, 30);
		password.setBounds(100, 150, 150 , 30);
		identityLabel.setBounds(50, 190, 50, 30);
		identity.setBounds(100, 190, 150, 30);
		yes.setBounds(70, 230, 70, 40);
		no.setBounds(150, 230, 70, 40);
		error.setBounds(87, 270, 200, 30);
		
		MyListener listen = new MyListener();
		name.addActionListener(listen);
		password.addActionListener(listen);
		yes.addActionListener(listen);
		no.addActionListener(listen);
		
		//改变字体颜色
		nameLabel.setForeground(Color.WHITE); 	
		passwordLabel.setForeground(Color.WHITE);	
		yes.setForeground(Color.WHITE);
		no.setForeground(Color.WHITE);
		loginLabel.setForeground(Color.WHITE);	
		identityLabel.setForeground(Color.WHITE);	
		identity.setForeground(Color.WHITE);
		error.setForeground(Color.WHITE);
		
		//改变背景颜色
		name.setBackground(Color.GRAY);
		password.setBackground(Color.GRAY);
		identity.setBackground(Color.GRAY);
		
		//设置按钮透明
		yes.setContentAreaFilled(false);
		no.setContentAreaFilled(false);
		
		//去除按钮边框
		yes.setBorderPainted(false);
		no.setBorderPainted(false);
		
		//去除按钮内边框
		yes.setFocusPainted(false);
		no.setFocusPainted(false);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
      	
		error.setVisible(false);
		loginWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      	loginWindow.validate();
		loginWindow.setVisible(true);
      	
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == yes) {
				error.setVisible(false);
				nameString = name.getText();
				passwordString = String.valueOf(password.getPassword());
				identityString = identity.getSelectedItem().toString();
				String str = "SELECT name,password FROM PasswordList WHERE name = '"
						+ nameString + "' AND password = '" + passwordString + "' AND identity = '" +
						identityString + "' ;";
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(str);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(rs.next() == true) {
						if(identityString.compareTo("学       生") == 0) {
							stmt.close();
							con.close();
							loginWindow.setVisible(false);;
							try {
								sw = new StudentWindow(nameString);
							} catch (Exception e1) {
								e1.printStackTrace();
							}						
						}
						else if(identityString.compareTo("教       师") == 0){
							stmt.close();
							con.close();
							loginWindow.setVisible(false);;
							try {
								tw = new TeacherWindow(nameString);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else if(identityString.compareTo("管  理  员") == 0){
							stmt.close();
							con.close();
							loginWindow.setVisible(false);;
							try {
								aw = new AdministratorWindow();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
					else {
						error.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == no) {
				System.exit(0);
			}
			
		}
		
	}
	
}
