package StudentOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import Error.*;

public class ReplacePassword {
	
	public JFrame replacePasswordWindow;
	JLabel newPassword1;
	JPasswordField newPassword1String;
	JLabel newPassword2;
	JPasswordField newPassword2String;
	JButton yes;
	InsertError ie;
	PasswordError pe;
	Success su;
	Connection con;
	Statement stmt;
	String id;
	public static boolean REPLACEPASSWORD = false;
	
	public ReplacePassword(String id) throws Exception{
		
		this.id = id;
		ie = new InsertError();
		pe = new PasswordError();
		su = new Success();
		
		REPLACEPASSWORD = true;
		replacePasswordWindow = new JFrame("修改密码");
		replacePasswordWindow.setLayout(null);
		replacePasswordWindow.setResizable(false);
		replacePasswordWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
		replacePasswordWindow.setIconImage(image);
		replacePasswordWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();		
		
		String label[] = {"确定","新的密码","再次输入"};
		yes = new JButton(label[0]);
		newPassword1 = new JLabel(label[1]);
		newPassword1String = new JPasswordField(20);
		newPassword2 = new JLabel(label[2]);
		newPassword2String = new JPasswordField(20);
		
		replacePasswordWindow.add(yes);
		replacePasswordWindow.add(newPassword1);
		replacePasswordWindow.add(newPassword1String);
		replacePasswordWindow.add(newPassword2);
		replacePasswordWindow.add(newPassword2String);
		
		newPassword1.setBounds(300, 180, 70, 30);
		newPassword1String.setBounds(370, 180, 150, 30);
		newPassword2.setBounds(300, 280, 70, 30);
		newPassword2String.setBounds(370, 280, 150, 30);
		yes.setBounds(385, 380, 70, 40);
		
		yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		newPassword1.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		newPassword1String.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		newPassword2.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		newPassword2String.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		MyListener listen = new MyListener();
		yes.addActionListener(listen);
		
        yes.setContentAreaFilled(false);
		
		yes.setFocusPainted(false);
		
		replacePasswordWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		replacePasswordWindow.validate();
		replacePasswordWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				String password1 = String.valueOf(newPassword1String.getPassword());
				String password2 = String.valueOf(newPassword2String.getPassword());
				if(password1.compareTo("") == 0 || password2.compareTo("") == 0) {
					if(ie.INSERT == false) {
						ie.show();
					}
				}
				else {
					if(password1.compareTo(password2) != 0) {
						if(pe.PASSWORD == false) {
							pe.show();
						}
					}
					else {
						String str = "UPDATE PasswordList SET password = '" + password1 + "' WHERE name = '" + id + "';";
						try {
							stmt.execute(str);
						} catch (SQLException e1) {
							
						}
						if(su.SUCCESS == false) {
							su.show();
						}
					}
				}				
				
			}
			
		}
		
	}
	
	class Success {
		
		JFrame successWindow;
		JLabel success;
		JButton yes;
		public static boolean SUCCESS = false;
		
		public Success() {
			
			successWindow = new JFrame("修改成功");
			successWindow.setSize(300,200);
			successWindow.setResizable(false);
			successWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			successWindow.setIconImage(image2);
			
			String label[] = {"密码修改成功","确定"};
			success = new JLabel(label[0]);
			yes = new JButton(label[1]);
			
			success.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			
	        successWindow.add(success);
	        successWindow.add(yes);
			
	        success.setBounds(95, 50, 250, 30);
			yes.setBounds(110, 100, 70, 40);
			
			MyListener listen = new MyListener();
			yes.addActionListener(listen);
			
			yes.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
	                yes.setBackground(Color.lightGray);
	            }
			});
			yes.addMouseListener(new MouseAdapter() {
				public void mouseExited(MouseEvent e) {
	                yes.setBackground(null);
	            }
			});
			
			yes.setContentAreaFilled(false);
			
			yes.setFocusPainted(false);
			
		}
		
		public void show() {
			
			SUCCESS = true;
			Toolkit kit1 = Toolkit.getDefaultToolkit();
			Dimension dim1 = kit1.getScreenSize();
			int x1 = (dim1.width-successWindow.getSize().width)/2;
			int y1 = (dim1.height-successWindow.getSize().height)/2;
			successWindow.setLocation(x1,y1);
			
			successWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			successWindow.validate();
			successWindow.setVisible(true);	
			successWindow.setAlwaysOnTop(true);
			
		}
		
		class MyListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					SUCCESS = false;
					successWindow.dispose();
				}			
			}
			
		}

	}
	
}
