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

public class TeacherList {
	
	JFrame teacherListWindow;
	JTable table;
	DefaultTableModel model;
	DefaultTableCellRenderer center;
	JButton insert;
	JButton delete;
	JButton replace;
	JButton find;
	JScrollPane scrollPane;
	DeleteError de;
	ReplaceError re;
	IDSameError se;
	InsertTeacher it;
	ReplaceTeacher rt;
	FindTeacher ft;
	int row;
	Connection con;
	Statement stmt;
	public String teacherInformation[];
	public static boolean TEACHERLIST = false;
	
	@SuppressWarnings("serial")
	public TeacherList() throws Exception{
		
		de = new DeleteError();
		re = new ReplaceError();
		se = new IDSameError();
		
		TEACHERLIST = true;
		teacherListWindow = new JFrame("教师名单");
		teacherListWindow.setLayout(null);
		teacherListWindow.setResizable(false);
		teacherListWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        teacherListWindow.setIconImage(image);
		teacherListWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
						
		String str = "SELECT COUNT(*) FROM TeacherList;";
		String str1 = "SELECT * FROM TeacherList;";
						
		ResultSet rs = stmt.executeQuery(str);
		int num = 0;  //判断有多少行来创建合适的二维数组
		while(rs.next()) {
			num = rs.getInt(1);
		}		
		String p[][] = new String[num][6];
		rs = stmt.executeQuery(str1);
		int cnt = 0;
		//导入数据
		while(rs.next()) {
			String IDString = rs.getString("ID");
			String nameString = rs.getString("name");
			String genderString = rs.getString("gender");
			String birthdayString = rs.getString("birthday");
			String instituteString = rs.getString("institute");
			String titleString = rs.getString("title");
			p[cnt][0] = IDString;
			p[cnt][1] = nameString;
			p[cnt][2] = genderString;
			p[cnt][3] = birthdayString;
			p[cnt][4] = instituteString;
			p[cnt][5] = titleString;;
			cnt++;
		}
		
		String label[] = {"插入","删除","修改","查找"};
		insert = new JButton(label[0]);
		delete = new JButton(label[1]);
		replace = new JButton(label[2]);
		find = new JButton(label[3]);
		
		String []columnNames = {"教师编号","姓名","性别","出生日期","院系","职称"};
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
		
		
		teacherListWindow.add(scrollPane);
		teacherListWindow.add(insert);
		teacherListWindow.add(delete);
		teacherListWindow.add(replace);
		teacherListWindow.add(find);
		
		table.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		insert.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		delete.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		replace.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		find.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		scrollPane.setBounds(0, 0, 850, 480);
		insert.setBounds(200, 480, 70, 40);
		delete.setBounds(330, 480, 70, 40);
		replace.setBounds(460, 480, 70, 40);
		find.setBounds(590, 480, 70, 40);
		
		MyListener listen = new MyListener();
		insert.addActionListener(listen);
		delete.addActionListener(listen);
		replace.addActionListener(listen);
		find.addActionListener(listen);
		
		insert.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                insert.setBackground(Color.lightGray);
            }
		});
		insert.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                insert.setBackground(null);
            }
		});
		delete.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                delete.setBackground(Color.lightGray);
            }
		});
		delete.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                delete.setBackground(null);
            }
		});
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
		find.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                find.setBackground(Color.lightGray);
            }
		});
		find.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                find.setBackground(null);
            }
		});
		
		insert.setContentAreaFilled(false);
		delete.setContentAreaFilled(false);
		replace.setContentAreaFilled(false);
		find.setContentAreaFilled(false);
		
		insert.setFocusPainted(false);
		delete.setFocusPainted(false);
		replace.setFocusPainted(false);
		find.setFocusPainted(false);
		
		teacherListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        teacherListWindow.validate();
		teacherListWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == insert) {
					it = new InsertTeacher();
					it.show();					
			}
			else if(e.getSource() == delete) {
				if(table.getSelectedRow() == -1) {
					if(de.DELETE == false) {
						de.show();
					}
				}
				else {
					String str1 = "DELETE FROM TeacherList WHERE ID = '" + model.getValueAt(table.getSelectedRow(), 0) + "';";
					String str = "DELETE FROM PasswordList WHERE name = '" + model.getValueAt(table.getSelectedRow(), 0) + "';";	
					model.removeRow(table.getSelectedRow());						    				   
				    try { 
				    	stmt.execute(str);
					    stmt.execute(str1);
				    } catch (SQLException e1) {
					    e1.printStackTrace();
				    }
				}
			}
			else if(e.getSource() == replace) {
				if(table.getSelectedRow() == -1) {
					if(re.REPLACE == false) {
						re.show();
					}
				}
				else {
					rt = new ReplaceTeacher();
					rt.show();
				}
			}
			else if(e.getSource() == find) {
				ft = new FindTeacher();
				ft.show();
			}
			
		}
		
	}	
	
	public class InsertTeacher {
		
		JFrame insertTeacherWindow;
		JButton yes;
		JButton no;
		JLabel ID;
		JTextField IDString;
		JLabel name;
		JTextField nameString;
		JLabel gender;
		JTextField genderString;
		JLabel birthday;
		JTextField birthdayString;
		JLabel institute;
		JTextField instituteString;
		JLabel title;
		JTextField titleString;
		InsertError ie;
		public boolean flag = false;
		
		public InsertTeacher() {
			
			ie = new InsertError();
			
			insertTeacherWindow = new JFrame("添加老师");
			insertTeacherWindow.setSize(500,350);
			insertTeacherWindow.setResizable(false);
			insertTeacherWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			insertTeacherWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","编      号","姓      名","性      别","出生年月","院      系","职      称"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			ID = new JLabel(label[2]);
			IDString = new JTextField();
			name = new JLabel(label[3]);
			nameString = new JTextField();
			gender = new JLabel(label[4]);
			genderString = new JTextField();
			birthday = new JLabel(label[5]);
			birthdayString = new JTextField();
			institute = new JLabel(label[6]);
			instituteString = new JTextField();
			title = new JLabel(label[7]);
			titleString = new JTextField();
			       
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ID.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			IDString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			gender.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			genderString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthday.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthdayString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			title.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			titleString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			insertTeacherWindow.add(ID);
			insertTeacherWindow.add(IDString);
			insertTeacherWindow.add(name);
			insertTeacherWindow.add(nameString);
			insertTeacherWindow.add(gender);
			insertTeacherWindow.add(genderString);
			insertTeacherWindow.add(birthday);
			insertTeacherWindow.add(birthdayString);
			insertTeacherWindow.add(institute);
			insertTeacherWindow.add(instituteString);
			insertTeacherWindow.add(title);
			insertTeacherWindow.add(titleString);
			insertTeacherWindow.add(yes);
			insertTeacherWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			institute.setBounds(0, 150, 60, 30);
			instituteString.setBounds(80, 150, 150, 30);
			title.setBounds(250, 150, 60, 30);
			titleString.setBounds(330, 150, 150, 30);
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
			int x1 = (dim1.width-insertTeacherWindow.getSize().width)/2;
			int y1 = (dim1.height-insertTeacherWindow.getSize().height)/2;
			insertTeacherWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			insertTeacherWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			insertTeacherWindow.validate();
			insertTeacherWindow.setVisible(true);	
			insertTeacherWindow.setAlwaysOnTop(true);
			
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),
							instituteString.getText(),titleString.getText()};
					for(int i = 0;i < s.length; i++) {
						if(s[i].compareTo("") != 0) {
							flag = true;
						}
						else {
							flag = false;
							break;
						}
					}
					if(flag == true) {
						//查重
						for(int i = 0;i < row; i++) {
							String str = (String)model.getValueAt(i, 0);
							if(s[0].compareTo(str) == 0) {
								flag = false;
								se.show();
								break;
							}
							else {
								flag = true;
							}
						}
						if(flag == true) {
							teacherInformation = s;
							model.addRow(teacherInformation);
							String str = "INSERT INTO TeacherList (ID,name,gender,birthday,institute,title,identity)"
									+ "VALUES ('" + s[0] + "','" + s[1] + "','" + s[2] + "','" + s[3] + "','" + s[4] + "','" + s[5] + "','教       师');";
							String str1 = "INSERT INTO PasswordList (name,password,identity)"
									+ "VALUES ('" + s[0] + "','" + s[0] + "','教       师')";
							try {
								stmt.execute(str);
								stmt.execute(str1);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}				
							insertTeacherWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					insertTeacherWindow.dispose();
				}
			}
			
		}

	}
	
    public class ReplaceTeacher {
		
		JFrame replaceTeacherWindow;
		JButton yes;
		JButton no;
		JLabel ID;
		JTextField IDString;
		JLabel name;
		JTextField nameString;
		JLabel gender;
		JTextField genderString;
		JLabel birthday;
		JTextField birthdayString;
		JLabel institute;
		JTextField instituteString;
		JLabel title;
		JTextField titleString;
		ReplaceError re;
		InsertError ie;
		public boolean flag = false;
		
		public ReplaceTeacher() {
			
			re = new ReplaceError();
			ie = new InsertError();
			
			replaceTeacherWindow = new JFrame("修改教师");
			replaceTeacherWindow.setSize(500,350);
			replaceTeacherWindow.setResizable(false);
			replaceTeacherWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			replaceTeacherWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","编      号","姓      名","性      别","出生年月","院      系","职      称"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			ID = new JLabel(label[2]);
			IDString = new JTextField();
			IDString.setText((String) model.getValueAt(table.getSelectedRow(), 0));
			name = new JLabel(label[3]);
			nameString = new JTextField();
			nameString.setText((String) model.getValueAt(table.getSelectedRow(), 1));
			gender = new JLabel(label[4]);
			genderString = new JTextField();
			genderString.setText((String) model.getValueAt(table.getSelectedRow(), 2));
			birthday = new JLabel(label[5]);
			birthdayString = new JTextField();
			birthdayString.setText((String) model.getValueAt(table.getSelectedRow(), 3));
			institute = new JLabel(label[6]);
			instituteString = new JTextField();
			instituteString.setText((String) model.getValueAt(table.getSelectedRow(), 4));
			title = new JLabel(label[7]);
			titleString = new JTextField();
			titleString.setText((String) model.getValueAt(table.getSelectedRow(), 5));
			       
			yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ID.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			IDString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			gender.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			genderString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthday.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthdayString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			title.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			titleString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			
			replaceTeacherWindow.add(ID);
			replaceTeacherWindow.add(IDString);
			replaceTeacherWindow.add(name);
			replaceTeacherWindow.add(nameString);
			replaceTeacherWindow.add(gender);
			replaceTeacherWindow.add(genderString);
			replaceTeacherWindow.add(birthday);
			replaceTeacherWindow.add(birthdayString);
			replaceTeacherWindow.add(institute);
			replaceTeacherWindow.add(instituteString);
			replaceTeacherWindow.add(title);
			replaceTeacherWindow.add(titleString);
			replaceTeacherWindow.add(yes);
			replaceTeacherWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			institute.setBounds(0, 150, 60, 30);
			instituteString.setBounds(80, 150, 150, 30);
			title.setBounds(250, 150, 60, 30);
			titleString.setBounds(330, 150, 150, 30);
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
			int x1 = (dim1.width-replaceTeacherWindow.getSize().width)/2;
			int y1 = (dim1.height-replaceTeacherWindow.getSize().height)/2;
			replaceTeacherWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			replaceTeacherWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			replaceTeacherWindow.validate();
			replaceTeacherWindow.setVisible(true);	
			replaceTeacherWindow.setAlwaysOnTop(true);
						
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),
							instituteString.getText(),titleString.getText()};
					for(int i = 0;i < s.length; i++) {
						if(s[i].compareTo("") != 0) {
							flag = true;
						}
						else {
							flag = false;
							break;
						}
					}
					if(flag == true) {
						//查重
						int selectRow = table.getSelectedRow();
						for(int i = 0;i < row; i++) {
							if(i == selectRow) {
								continue;
							}
							String str = (String)model.getValueAt(i, 0);
							if(s[0].compareTo(str) == 0) {
								flag = false;
								se.show();
								break;
							}
							else {
								flag = true;
							}
						}
						if(flag == true) {
							teacherInformation = s;
							String origin = (String)model.getValueAt(table.getSelectedRow(), 0);  //记住原来编号,防止编号改变
							String str = "UPDATE TeacherList SET ID = '" + s[0] + "',name = '" + s[1] + "',gender = '" + s[2] + "',birthday = '" + s[3]
									+ "',institute = '" + s[4] + "',title = '" + s[5] + "' WHERE ID = '"
									+ model.getValueAt(table.getSelectedRow(), 0) + "';";
							String str1 = "UPDATE PasswordList SET name = '" + s[0] + "',password = '" + s[0] +
									"' WHERE name = '" + (String)model.getValueAt(table.getSelectedRow(), 0) + "';";
							for(int i = 0;i < s.length; i++) {
								model.setValueAt(s[i], table.getSelectedRow(), i);
							}
							try {
								stmt.executeUpdate(str);
								if(s[0].compareTo(origin) != 0) {
									stmt.executeUpdate(str1);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							replaceTeacherWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					replaceTeacherWindow.dispose();
				}
			}
			
		}

	}

    public class FindTeacher {
    	
    	JFrame findTeacherWindow;
		JButton yes;
		JButton no;
		JLabel ID;
		JTextField IDString;
		JLabel name;
		JTextField nameString;
		JLabel gender;
		JTextField genderString;
		JLabel birthday;
		JTextField birthdayString;
		JLabel institute;
		JTextField instituteString;
		JLabel title;
		JTextField titleString;
		InsertError ie;
		FindError fe;
		FindResult fr;
		public boolean flag = false;
		
		public FindTeacher() {
			
			fr = new FindResult();
			ie = new InsertError();
			fe = new FindError();
			
			findTeacherWindow = new JFrame("查找教师");
			findTeacherWindow.setSize(500,350);
			findTeacherWindow.setResizable(false);
			findTeacherWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			findTeacherWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","学      号","姓      名","性      别","出生年月","院      系","职      称"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			ID = new JLabel(label[2]);
			IDString = new JTextField();
			name = new JLabel(label[3]);
			nameString = new JTextField();
			gender = new JLabel(label[4]);
			genderString = new JTextField();
			birthday = new JLabel(label[5]);
			birthdayString = new JTextField();
			institute = new JLabel(label[6]);
			instituteString = new JTextField();
			title = new JLabel(label[7]);
			titleString = new JTextField();
			       
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ID.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			IDString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			gender.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			genderString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthday.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			birthdayString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			title.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			titleString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			
			findTeacherWindow.add(ID);
			findTeacherWindow.add(IDString);
			findTeacherWindow.add(name);
			findTeacherWindow.add(nameString);
			findTeacherWindow.add(gender);
			findTeacherWindow.add(genderString);
			findTeacherWindow.add(birthday);
			findTeacherWindow.add(birthdayString);
			findTeacherWindow.add(institute);
			findTeacherWindow.add(instituteString);
			findTeacherWindow.add(title);
			findTeacherWindow.add(titleString);
			findTeacherWindow.add(yes);
			findTeacherWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			institute.setBounds(0, 150, 60, 30);
			instituteString.setBounds(80, 150, 150, 30);
			title.setBounds(250, 150, 60, 30);
			titleString.setBounds(330, 150, 150, 30);
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
			int x1 = (dim1.width-findTeacherWindow.getSize().width)/2;
			int y1 = (dim1.height-findTeacherWindow.getSize().height)/2;
			findTeacherWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			findTeacherWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			findTeacherWindow.validate();
			findTeacherWindow.setVisible(true);	
			findTeacherWindow.setAlwaysOnTop(true);
			
		}
    	
    	class MyListener implements ActionListener {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),
							instituteString.getText(),titleString.getText()};
					int value[] = {0,0,0,0,0,0};
					for(int i = 0;i < s.length; i++) {
						if(s[i].compareTo("") != 0) {
							value[i] = 1;
							flag = true;
						}
					}
					
					if(flag == true) {
						boolean ans =false;
						for(int i = 0;i < row; i++) {
							boolean m = true;
							for(int j = 0;j < s.length; j++) {
								if(value[j] == 1) {
									if(s[j].compareTo((String)model.getValueAt(i, j)) != 0) {
										m = false;
										break;
									}
								}
							}
							
							if(m == true) {
								ans = true;
								String values[] = new String[s.length];
								for(int j = 0;j < s.length; j++) {
									values[j] = (String)model.getValueAt(i, j);
								}
								fr.findModel.addRow(values);
							}
						}
						
						if(ans == false) {
							if(fe.FIND == false) {								
							    fe.show();
							}
						}
						else {
							if(fr.FINDRESULT == false) {
								  findTeacherWindow.dispose();
						          fr.show();
						    }							
						}	
						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}
				}
				else {
					findTeacherWindow.dispose();
				}
				
			}
    		
    	}
    	
    	class FindResult {
    		
    		JFrame findResultWindow;
    		JTable findTable;
    		DefaultTableModel findModel;
    		DefaultTableCellRenderer findCenter;
    		JButton yes;
    		JScrollPane findScrollPane;
    		public static boolean FINDRESULT = false;
    		
    		@SuppressWarnings("serial")
			public FindResult() {
    			
    			findResultWindow = new JFrame("查询结果");
    			findResultWindow.setLayout(null);
    			findResultWindow.setResizable(false);
    			findResultWindow.setUndecorated(false);
    			
    			String label[] = {"确认"};
    			yes = new JButton(label[0]);
    			
    			String []columnNames = {"编号","姓名","性别","出生年月","院系","职称"};
    			String[][] tableValues = {
    					};
    			findModel = new DefaultTableModel(tableValues,columnNames);
    			findCenter = new DefaultTableCellRenderer();
    			findCenter.setHorizontalAlignment(JLabel.CENTER);
    			findTable = new JTable(findModel){
    				public boolean isCellEditable(int row, int column) { 
    					 return false;
    				}
    		    };
    			findTable.setDefaultRenderer(Object.class, findCenter);
    			findScrollPane = new JScrollPane(findTable);
    			findTable.getTableHeader().setPreferredSize(new Dimension(findTable.getWidth(), 30));
    			findTable.setRowHeight(30); 
    			
    			
    			findResultWindow.add(findScrollPane);
    			findResultWindow.add(yes);
    			
    			findTable.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
    			findTable.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
    			yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
    			
    			findScrollPane.setBounds(0, 0, 850, 440);
    			yes.setBounds(380, 445, 70, 40);
    			
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
    		
    		void show() {
    			
    			FINDRESULT = true;
    			
    			Toolkit kit = Toolkit.getDefaultToolkit();
    			Image image = kit.getImage("src/Picture/picture3.JPG");       
    			findResultWindow.setIconImage(image);
    			findResultWindow.setSize(863,525);
    			findResultWindow.setLocation(teacherListWindow.getBounds().x-5,teacherListWindow.getBounds().y);
    			
    			findResultWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    			findResultWindow.validate();
    			findResultWindow.setVisible(true);
    			
    		}
    		
    		class MyListener implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == yes) {
						FINDRESULT = false;
						findResultWindow.dispose();
					}					
				}   			
    			
    		}
    		
    	}
    	
    }
	
}

