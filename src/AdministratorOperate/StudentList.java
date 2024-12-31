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

public class StudentList {
	
	JFrame studentListWindow;
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
	InsertStudent is;
	ReplaceStudent rs;
	FindStudent fs;
	int row;
	Connection con;
	Statement stmt;
	public String studentInformation[];
	public static boolean STUDENTLIST = false;
	
	@SuppressWarnings("serial")
	public StudentList() throws Exception{
		
		de = new DeleteError();
		re = new ReplaceError();
		se = new IDSameError();
		
		STUDENTLIST = true;
		studentListWindow = new JFrame("学生名单");
		studentListWindow.setLayout(null);
		studentListWindow.setResizable(false);
		studentListWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        studentListWindow.setIconImage(image);
		studentListWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
				
	    String str = "SELECT COUNT(*) FROM StudentList;";
		String str1 = "SELECT * FROM StudentList;";
				
		ResultSet rs = stmt.executeQuery(str);
		int num = 0;  //判断有多少行来创建合适的二维数组
		while(rs.next()) {
			num = rs.getInt(1);
		}		
		String p[][] = new String[num][8];
		rs = stmt.executeQuery(str1);
		int cnt = 0;
		//导入数据
	    while(rs.next()) {
			String IDString = rs.getString("ID");
			String nameString = rs.getString("name");
			String genderString = rs.getString("gender");
			String birthdayString = rs.getString("birthday");
			String ethnicString = rs.getString("ethnic");
			String instituteString = rs.getString("institute");
			String deptString = rs.getString("dept");
			String schoolClassString = rs.getString("schoolClass");
			p[cnt][0] = IDString;
			p[cnt][1] = nameString;
			p[cnt][2] = genderString;
			p[cnt][3] = birthdayString;
			p[cnt][4] = ethnicString;
			p[cnt][5] = instituteString;
			p[cnt][6] = deptString;
			p[cnt][7] = schoolClassString;
			cnt++;
	    }
		
		String label[] = {"插入","删除","修改","查找"};
		insert = new JButton(label[0]);
		delete = new JButton(label[1]);
		replace = new JButton(label[2]);
		find = new JButton(label[3]);
		
		String []columnNames = {"学号","姓名","性别","出生年月","民族","院系","专业","班级"};
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
		
		
		studentListWindow.add(scrollPane);
		studentListWindow.add(insert);
		studentListWindow.add(delete);
		studentListWindow.add(replace);
		studentListWindow.add(find);
		
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
		
		studentListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		studentListWindow.validate();
		studentListWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == insert) {
					is = new InsertStudent();
					is.show();					
			}
			else if(e.getSource() == delete) {
				if(table.getSelectedRow() == -1) {
					if(de.DELETE == false) {
						de.show();
					}
				}
				else {
					String str1 = "DELETE FROM StudentList WHERE ID = '" + model.getValueAt(table.getSelectedRow(), 0) + "';";
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
					rs = new ReplaceStudent();
					rs.show();
				}
			}
			else if(e.getSource() == find) {
				fs = new FindStudent();
				fs.show();
			}
			
		}
		
	}	
	
	public class InsertStudent {
		
		JFrame insertStudentWindow;
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
		JLabel ethnic;
		JTextField ethnicString;
		JLabel institute;
		JTextField instituteString;
		JLabel dept;
		JTextField deptString;
		JLabel schoolClass;
		JTextField schoolClassString;
		InsertError ie;
		public boolean flag = false;
		
		public InsertStudent() {
			
			ie = new InsertError();
			
			insertStudentWindow = new JFrame("添加学生");
			insertStudentWindow.setSize(500,350);
			insertStudentWindow.setResizable(false);
			insertStudentWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			insertStudentWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","学      号","姓      名","性      别","出生年月","民      族","院      系","专      业","班      级"};
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
			ethnic = new JLabel(label[6]);
			ethnicString = new JTextField();
			institute = new JLabel(label[7]);
			instituteString = new JTextField();
			dept = new JLabel(label[8]);
			deptString = new JTextField();
			schoolClass = new JLabel(label[9]);
			schoolClassString = new JTextField();
			       
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
			ethnic.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ethnicString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			dept.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			deptString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			
			insertStudentWindow.add(ID);
			insertStudentWindow.add(IDString);
			insertStudentWindow.add(name);
			insertStudentWindow.add(nameString);
			insertStudentWindow.add(gender);
			insertStudentWindow.add(genderString);
			insertStudentWindow.add(birthday);
			insertStudentWindow.add(birthdayString);
			insertStudentWindow.add(ethnic);
			insertStudentWindow.add(ethnicString);
			insertStudentWindow.add(institute);
			insertStudentWindow.add(instituteString);
			insertStudentWindow.add(dept);
			insertStudentWindow.add(deptString);
			insertStudentWindow.add(schoolClass);
			insertStudentWindow.add(schoolClassString);
			insertStudentWindow.add(yes);
			insertStudentWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			ethnic.setBounds(0, 150, 60, 30);
			ethnicString.setBounds(80, 150, 150, 30);
			institute.setBounds(250, 150, 60, 30);
			instituteString.setBounds(330, 150, 150, 30);
			dept.setBounds(0, 220, 60, 30);
			deptString.setBounds(80, 220, 150, 30);
			schoolClass.setBounds(250, 220, 60, 30);
			schoolClassString.setBounds(330, 220, 150, 30);
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
			int x1 = (dim1.width-insertStudentWindow.getSize().width)/2;
			int y1 = (dim1.height-insertStudentWindow.getSize().height)/2;
			insertStudentWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			insertStudentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			insertStudentWindow.validate();
			insertStudentWindow.setVisible(true);	
			insertStudentWindow.setAlwaysOnTop(true);
			
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),ethnicString.getText(),
							instituteString.getText(),deptString.getText(),schoolClassString.getText()};
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
							studentInformation = s;
							model.addRow(studentInformation);
							String str = "INSERT INTO StudentList (ID,name,gender,birthday,ethnic,institute,dept,schoolClass,identity)"
									+ "VALUES ('" + s[0] + "','" + s[1] + "','" + s[2] + "','" + s[3] + "','" + s[4] + "','" + s[5] + 
									"','" + s[6] + "','" + s[7] + "','学       生');";
							String str1 = "INSERT INTO PasswordList (name,password,identity)"
									+ "VALUES ('" + s[0] + "','" + s[0] + "','学       生')";
							try {
								stmt.execute(str);
								stmt.execute(str1);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}							
							insertStudentWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					insertStudentWindow.dispose();
				}
			}
			
		}

	}
	
    public class ReplaceStudent {
		
		JFrame replaceStudentWindow;
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
		JLabel ethnic;
		JTextField ethnicString;
		JLabel institute;
		JTextField instituteString;
		JLabel dept;
		JTextField deptString;
		JLabel schoolClass;
		JTextField schoolClassString;
		ReplaceError re;
		InsertError ie;
		public boolean flag = false;
		
		public ReplaceStudent() {
			
			re = new ReplaceError();
			ie = new InsertError();
			
			replaceStudentWindow = new JFrame("修改学生");
			replaceStudentWindow.setSize(500,350);
			replaceStudentWindow.setResizable(false);
			replaceStudentWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			replaceStudentWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","学      号","姓      名","性      别","出生年月","民      族","院      系","专      业","班      级"};
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
			ethnic = new JLabel(label[6]);
			ethnicString = new JTextField();
			ethnicString.setText((String) model.getValueAt(table.getSelectedRow(), 4));
			institute = new JLabel(label[7]);
			instituteString = new JTextField();
			instituteString.setText((String) model.getValueAt(table.getSelectedRow(), 5));
			dept = new JLabel(label[8]);
			deptString = new JTextField();
			deptString.setText((String) model.getValueAt(table.getSelectedRow(), 6));
			schoolClass = new JLabel(label[9]);
			schoolClassString = new JTextField();
			schoolClassString.setText((String) model.getValueAt(table.getSelectedRow(), 7));
			       
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
			ethnic.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ethnicString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			dept.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			deptString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			
			replaceStudentWindow.add(ID);
			replaceStudentWindow.add(IDString);
			replaceStudentWindow.add(name);
			replaceStudentWindow.add(nameString);
			replaceStudentWindow.add(gender);
			replaceStudentWindow.add(genderString);
			replaceStudentWindow.add(birthday);
			replaceStudentWindow.add(birthdayString);
			replaceStudentWindow.add(ethnic);
			replaceStudentWindow.add(ethnicString);
			replaceStudentWindow.add(institute);
			replaceStudentWindow.add(instituteString);
			replaceStudentWindow.add(dept);
			replaceStudentWindow.add(deptString);
			replaceStudentWindow.add(schoolClass);
			replaceStudentWindow.add(schoolClassString);
			replaceStudentWindow.add(yes);
			replaceStudentWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			ethnic.setBounds(0, 150, 60, 30);
			ethnicString.setBounds(80, 150, 150, 30);
			institute.setBounds(250, 150, 60, 30);
			instituteString.setBounds(330, 150, 150, 30);
			dept.setBounds(0, 220, 60, 30);
			deptString.setBounds(80, 220, 150, 30);
			schoolClass.setBounds(250, 220, 60, 30);
			schoolClassString.setBounds(330, 220, 150, 30);
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
			int x1 = (dim1.width-replaceStudentWindow.getSize().width)/2;
			int y1 = (dim1.height-replaceStudentWindow.getSize().height)/2;
			replaceStudentWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			replaceStudentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			replaceStudentWindow.validate();
			replaceStudentWindow.setVisible(true);	
			replaceStudentWindow.setAlwaysOnTop(true);
						
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),ethnicString.getText(),
							instituteString.getText(),deptString.getText(),schoolClassString.getText()};
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
							studentInformation = s;
							String origin = (String)model.getValueAt(table.getSelectedRow(), 0);
							String str = "UPDATE StudentList SET ID = '" + s[0] + "',name = '" + s[1] + "',gender = '" + s[2] + "',birthday = '" + s[3]
									+ "',ethnic = '" + s[4] + "',institute = '" + s[5] + "',dept = '" + s[6] + "',schoolClass = '" + s[7] + "' WHERE ID = '"
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
							replaceStudentWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					replaceStudentWindow.dispose();
				}
			}
			
		}

	}

    public class FindStudent {
    	
    	JFrame findStudentWindow;
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
		JLabel ethnic;
		JTextField ethnicString;
		JLabel institute;
		JTextField instituteString;
		JLabel dept;
		JTextField deptString;
		JLabel schoolClass;
		JTextField schoolClassString;
		InsertError ie;
		FindError fe;
		FindResult fr;
		public boolean flag = false;
		
		public FindStudent() {
			
			fr = new FindResult();
			ie = new InsertError();
			fe = new FindError();
			
			findStudentWindow = new JFrame("添加学生");
			findStudentWindow.setSize(500,350);
			findStudentWindow.setResizable(false);
			findStudentWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			findStudentWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","学      号","姓      名","性      别","出生年月","民      族","院      系","专      业","班      级"};
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
			ethnic = new JLabel(label[6]);
			ethnicString = new JTextField();
			institute = new JLabel(label[7]);
			instituteString = new JTextField();
			dept = new JLabel(label[8]);
			deptString = new JTextField();
			schoolClass = new JLabel(label[9]);
			schoolClassString = new JTextField();
			       
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
			ethnic.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ethnicString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			institute.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			instituteString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			dept.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			deptString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			
			findStudentWindow.add(ID);
			findStudentWindow.add(IDString);
			findStudentWindow.add(name);
			findStudentWindow.add(nameString);
			findStudentWindow.add(gender);
			findStudentWindow.add(genderString);
			findStudentWindow.add(birthday);
			findStudentWindow.add(birthdayString);
			findStudentWindow.add(ethnic);
			findStudentWindow.add(ethnicString);
			findStudentWindow.add(institute);
			findStudentWindow.add(instituteString);
			findStudentWindow.add(dept);
			findStudentWindow.add(deptString);
			findStudentWindow.add(schoolClass);
			findStudentWindow.add(schoolClassString);
			findStudentWindow.add(yes);
			findStudentWindow.add(no);
			
			ID.setBounds(0, 10, 60, 30);
			IDString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			gender.setBounds(0, 80, 60, 30);
			genderString.setBounds(80, 80, 150, 30);
			birthday.setBounds(250, 80, 60, 30);
			birthdayString.setBounds(330, 80, 150, 30);
			ethnic.setBounds(0, 150, 60, 30);
			ethnicString.setBounds(80, 150, 150, 30);
			institute.setBounds(250, 150, 60, 30);
			instituteString.setBounds(330, 150, 150, 30);
			dept.setBounds(0, 220, 60, 30);
			deptString.setBounds(80, 220, 150, 30);
			schoolClass.setBounds(250, 220, 60, 30);
			schoolClassString.setBounds(330, 220, 150, 30);
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
			int x1 = (dim1.width-findStudentWindow.getSize().width)/2;
			int y1 = (dim1.height-findStudentWindow.getSize().height)/2;
			findStudentWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			findStudentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			findStudentWindow.validate();
			findStudentWindow.setVisible(true);	
			findStudentWindow.setAlwaysOnTop(true);
			
		}
    	
    	class MyListener implements ActionListener {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == yes) {
					String s[] = {IDString.getText(),nameString.getText(),genderString.getText(),birthdayString.getText(),ethnicString.getText(),
							instituteString.getText(),deptString.getText(),schoolClassString.getText()};
					int value[] = {0,0,0,0,0,0,0,0};
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
								  findStudentWindow.dispose();
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
					findStudentWindow.dispose();
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
    			
    			String []columnNames = {"学号","姓名","性别","出生年月","民族","院系","专业","班级"};
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
    			findResultWindow.setLocation(studentListWindow.getBounds().x-5,studentListWindow.getBounds().y);
    			
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
