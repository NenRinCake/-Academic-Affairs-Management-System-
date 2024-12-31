package AdministratorOperate;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Error.*;

public class PasswordList {
	
	JFrame passwordListWindow;
	JButton replace;
	JButton refresh;
	JTable table;
	DefaultTableModel model;
	DefaultTableCellRenderer center;
	JScrollPane scrollPane;
	ReplaceError re;
	ReplacePassword rp;
	int X;
	int Y;
	Connection con;
	Statement stmt;
	public String passwordInformation[];
	public static boolean PASSWORDLIST = false;
	
	@SuppressWarnings("serial")
	public PasswordList() throws Exception {
		
		re = new ReplaceError();
		
		PASSWORDLIST = true;
		passwordListWindow = new JFrame("用户帐密");
		passwordListWindow.setLayout(null);
		passwordListWindow.setResizable(false);
		passwordListWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        passwordListWindow.setIconImage(image);
		passwordListWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
		
		String str = "SELECT COUNT(*) FROM PasswordList;";
		String str1 = "SELECT * FROM PasswordList ORDER BY identity DESC;";
		
		ResultSet rs = stmt.executeQuery(str);
		int num = 0;  //判断有多少行来创建合适的二维数组
		while(rs.next()) {
			num = rs.getInt(1);
		}		
		String p[][] = new String[num][3];
		rs = stmt.executeQuery(str1);
		int cnt = 0;
		//导入数据
	    while(rs.next()) {
	    	String nameString = rs.getString("name");
	    	String passwordString = rs.getString("password");
	    	String identityString = rs.getString("identity");
	    	p[cnt][0] = nameString;
	    	p[cnt][1] = passwordString;
	    	p[cnt][2] = identityString;
	    	cnt++;
	    }
		
		String label[] = {"修改","刷新"};
		replace = new JButton(label[0]);
		refresh = new JButton(label[1]);
		
		String []columnNames = {"用户名","密码","身份"};
		String[][] tableValues = p;
		model = new DefaultTableModel(tableValues,columnNames);
		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		table = new JTable(model){
			public boolean isCellEditable(int row, int column) { 
				 return false;
			}
	    };
		table.setDefaultRenderer(Object.class, center);
		scrollPane = new JScrollPane(table);
		table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 30));
		table.setRowHeight(30); 
		
		passwordListWindow.add(scrollPane);
		passwordListWindow.add(replace);
		passwordListWindow.add(refresh);
		
		table.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		replace.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		refresh.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		scrollPane.setBounds(0, 0, 850, 480);
		replace.setBounds(370, 480, 70, 40);
		//refresh.setBounds(460, 480, 70, 40);
		
		MyListener listen = new MyListener();
		replace.addActionListener(listen);
		refresh.addActionListener(listen);
		
		replace.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                replace.setBackground(Color.lightGray);
            }
		});
		replace.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                replace.setBackground(null);
            }
		});
		refresh.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                refresh.setBackground(Color.lightGray);
            }
		});
		refresh.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                refresh.setBackground(null);
            }
		});
		
		replace.setContentAreaFilled(false);
		refresh.setContentAreaFilled(false);
		
		replace.setFocusPainted(false);
		refresh.setFocusPainted(false);
		
		passwordListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		passwordListWindow.validate();
		passwordListWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == replace) {
				if(table.getSelectedRow() == -1) {
					if(re.REPLACE == false) {
						re.show();
					}
				}
				else {
					rp = new ReplacePassword();
					rp.show();
				}
		    }
			else if(e.getSource() == refresh) {
				try {
					stmt.close();
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}				
				X = passwordListWindow.getBounds().x;
				Y = passwordListWindow.getBounds().y;
				PasswordList p = null;
				try {
					p = new PasswordList();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				p.passwordListWindow.setLocation(X, Y);
				p.passwordListWindow.setVisible(true);
				passwordListWindow.dispose();
			}
			
		}
		
	}
	
public class ReplacePassword {
		
		JFrame replacePasswordWindow;
		JButton yes;
		JButton no;
		JLabel name;
		JLabel nameString;
		JLabel password;
		JTextField passwordString;
		JLabel identity;
		JLabel identityString;
		ReplaceError re;
		InsertError ie;
		public boolean flag = false;
		
		public ReplacePassword() {
			
			re = new ReplaceError();
			ie = new InsertError();
			
			
			replacePasswordWindow = new JFrame("修改密码");
			replacePasswordWindow.setSize(500,350);
			replacePasswordWindow.setResizable(false);
			replacePasswordWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			replacePasswordWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","用  户  名","密       码","身       份"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			name = new JLabel(label[2]);
			nameString = new JLabel(((String) model.getValueAt(table.getSelectedRow(), 0)));
			password = new JLabel(label[3]);
			passwordString = new JTextField();
			passwordString.setText((String) model.getValueAt(table.getSelectedRow(), 1));
			identity = new JLabel(label[4]);
			identityString = new JLabel(((String) model.getValueAt(table.getSelectedRow(), 2)));
			       
			yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			password.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			passwordString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			identity.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			identityString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			
			replacePasswordWindow.add(name);
			replacePasswordWindow.add(nameString);
			replacePasswordWindow.add(password);
			replacePasswordWindow.add(passwordString);
			replacePasswordWindow.add(identity);
			replacePasswordWindow.add(identityString);
			replacePasswordWindow.add(yes);
			replacePasswordWindow.add(no);
			
			name.setBounds(0, 10, 80, 30);
			nameString.setBounds(100, 10, 150, 30);
			password.setBounds(230, 10, 80, 30);
			passwordString.setBounds(330, 10, 150, 30);
			identity.setBounds(0, 80, 80, 30);
			identityString.setBounds(100, 80, 150, 30);
			yes.setBounds(150, 260, 70, 40);
			no.setBounds(260, 260, 70, 40);
			
			MyListener listen = new MyListener();
			yes.addActionListener(listen);
			no.addActionListener(listen);
			
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
			no.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
	                no.setBackground(Color.lightGray);
	            }
			});
			no.addMouseListener(new MouseAdapter() {
				public void mouseExited(MouseEvent e) {
	                no.setBackground(null);
	            }
			});
			
			yes.setContentAreaFilled(false);
			no.setContentAreaFilled(false);
			
			yes.setFocusPainted(false);
			no.setFocusPainted(false);
			
		}
		
		public void show() {
			
			Toolkit kit1 = Toolkit.getDefaultToolkit();
			Dimension dim1 = kit1.getScreenSize();
			int x1 = (dim1.width-replacePasswordWindow.getSize().width)/2;
			int y1 = (dim1.height-replacePasswordWindow.getSize().height)/2;
			replacePasswordWindow.setLocation(x1,y1);
			
			replacePasswordWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			replacePasswordWindow.validate();
			replacePasswordWindow.setVisible(true);	
			replacePasswordWindow.setAlwaysOnTop(true);
						
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {passwordString.getText()};
					if(s[0].compareTo("") != 0) {
						flag = true;
					}
					else {
						flag = false;
					}
					if(flag == true) {
						passwordInformation = s;
						model.setValueAt(s[0], table.getSelectedRow(), 1);
						String str = "UPDATE PasswordList SET password = '" +
						s[0] + "' WHERE name = '" + model.getValueAt(table.getSelectedRow(), 0)
						+ "';";
						try {
							stmt.executeUpdate(str);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						replacePasswordWindow.dispose();						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					replacePasswordWindow.dispose();
				}
			}
			
		}

	}

}
