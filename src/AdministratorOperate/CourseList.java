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

@SuppressWarnings("unused")
public class CourseList {
	
	JFrame courseListWindow;
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
	InsertCourse ic;
	ReplaceCourse rc;
	FindCourse fc;
	int row;
	Connection con;
	Statement stmt;
	public String courseInformation[];
	public static boolean COURSELIST = false;
	
	@SuppressWarnings("serial")
	public CourseList() throws Exception{
		
		de = new DeleteError();
		re = new ReplaceError();
		se = new IDSameError();
		
		COURSELIST = true;
		courseListWindow = new JFrame("课程安排");
		courseListWindow.setLayout(null);
		courseListWindow.setResizable(false);
		courseListWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
		courseListWindow.setIconImage(image);
		courseListWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
								
		String str = "SELECT COUNT(*) FROM CourseList;";
		String str1 = "SELECT * FROM CourseList;";
								
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
			String nameString = rs.getString("name");
			String teacherString = rs.getString("teacher");
			String schoolClassString = rs.getString("schoolClass");
			String creditString = rs.getString("credit");
			String timeString = rs.getString("time");
			p[cnt][0] = nameString;
			p[cnt][1] = teacherString;
			p[cnt][2] = schoolClassString;
			p[cnt][3] = creditString;
			p[cnt][4] = timeString;
			cnt++;
		}
		
		String label[] = {"插入","删除","修改","查找"};
		insert = new JButton(label[0]);
		delete = new JButton(label[1]);
		replace = new JButton(label[2]);
		find = new JButton(label[3]);
		
		String []columnNames = {"课程名称","授课教师","授课班级","学分","学时"};
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
		
		
		courseListWindow.add(scrollPane);
		courseListWindow.add(insert);
		courseListWindow.add(delete);
		courseListWindow.add(replace);
		courseListWindow.add(find);
		
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
		
		courseListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		courseListWindow.validate();
		courseListWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == insert) {
					ic = new InsertCourse();
					ic.show();					
			}
			else if(e.getSource() == delete) {
				if(table.getSelectedRow() == -1) {
					if(de.DELETE == false) {
						de.show();
					}
				}
				else {
					String str1 = "DELETE FROM CourseList WHERE name = '" + model.getValueAt(table.getSelectedRow(), 0) + "' AND schoolClass = '"
							+ model.getValueAt(table.getSelectedRow(), 2) + "';";
					String str2 = "DELETE FROM GradeList WHERE courseName = '" + model.getValueAt(table.getSelectedRow(), 0) + 
							"' AND studentID IN ( SELECT ID FROM StudentList WHERE schoolClass = '" + model.getValueAt(table.getSelectedRow(), 2) + "');";
					model.removeRow(table.getSelectedRow());						    				   
				    try { 
					    stmt.execute(str1);
					    stmt.execute(str2);
				    } catch (SQLException e1) {
					    
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
					rc = new ReplaceCourse();
					rc.show();
				}
			}
			else if(e.getSource() == find) {
				fc = new FindCourse();
				fc.show();
			}
			
		}
		
	}	
	
	public class InsertCourse {
		
		JFrame insertCourseWindow;
		JButton yes;
		JButton no;
		JLabel name;
		JTextField nameString;
		JLabel teacher;
		JTextField teacherString;
		JLabel schoolClass;
		JTextField schoolClassString;
		JLabel credit;
		JTextField creditString;
		JLabel time;
		JTextField timeString;
		InsertError ie;
		public boolean flag = false;
		
		public InsertCourse() {
			
			ie = new InsertError();
			
			insertCourseWindow = new JFrame("添加课程");
			insertCourseWindow.setSize(500,350);
			insertCourseWindow.setResizable(false);
			insertCourseWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			insertCourseWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","课程名称","授课教师","授课班级","学      分","学      时"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			name = new JLabel(label[2]);
			nameString = new JTextField();
			teacher = new JLabel(label[3]);
			teacherString = new JTextField();
			schoolClass = new JLabel(label[4]);
			schoolClassString = new JTextField();
			credit = new JLabel(label[5]);
			creditString = new JTextField();
			time = new JLabel(label[6]);
			timeString = new JTextField();
			       
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacher.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacherString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			credit.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			creditString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			time.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			timeString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			insertCourseWindow.add(name);
			insertCourseWindow.add(nameString);
			insertCourseWindow.add(teacher);
			insertCourseWindow.add(teacherString);
			insertCourseWindow.add(schoolClass);
			insertCourseWindow.add(schoolClassString);
			insertCourseWindow.add(credit);
			insertCourseWindow.add(creditString);
			insertCourseWindow.add(time);
			insertCourseWindow.add(timeString);
			insertCourseWindow.add(yes);
			insertCourseWindow.add(no);
			
			name.setBounds(0, 10, 60, 30);
			nameString.setBounds(80, 10, 150, 30);
			teacher.setBounds(250, 10, 60, 30);
			teacherString.setBounds(330, 10, 150, 30);
			schoolClass.setBounds(0, 80, 60, 30);
			schoolClassString.setBounds(80, 80, 150, 30);
			credit.setBounds(250, 80, 60, 30);
			creditString.setBounds(330, 80, 150, 30);
			time.setBounds(0, 150, 60, 30);
			timeString.setBounds(80, 150, 150, 30);
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
			int x1 = (dim1.width-insertCourseWindow.getSize().width)/2;
			int y1 = (dim1.height-insertCourseWindow.getSize().height)/2;
			insertCourseWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			insertCourseWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			insertCourseWindow.validate();
			insertCourseWindow.setVisible(true);	
			insertCourseWindow.setAlwaysOnTop(true);
			
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {nameString.getText(),teacherString.getText(),schoolClassString.getText(),
							creditString.getText(),timeString.getText()};
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
							String str1 = (String)model.getValueAt(i, 0);
							String str2 = (String)model.getValueAt(i, 2);
							if(s[0].compareTo(str1) == 0 &&s[2].compareTo(str2) == 0) {
								flag = false;
								se.show();
								break;
							}
							else {
								flag = true;
							}
						}
						if(flag == true) {
							courseInformation = s;
							model.addRow(courseInformation);
							String str = "INSERT INTO CourseList (name,teacher,schoolClass,credit,time)"
									+ "VALUES ('" + s[0] + "','" + s[1] + "','" + s[2] + "'," + s[3] + "," + s[4] + ");";
							try {
								stmt.execute(str);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}				
							insertCourseWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					insertCourseWindow.dispose();
				}
			}
			
		}

	}
	
    public class ReplaceCourse {
		
		JFrame replaceCourseWindow;
		JButton yes;
		JButton no;
		JLabel name;
		JTextField nameString;
		JLabel teacher;
		JTextField teacherString;
		JLabel schoolClass;
		JTextField schoolClassString;
		JLabel credit;
		JTextField creditString;
		JLabel time;
		JTextField timeString;
		ReplaceError re;
		InsertError ie;
		public boolean flag = false;
		
		public ReplaceCourse() {
			
			re = new ReplaceError();
			ie = new InsertError();
			
			replaceCourseWindow = new JFrame("修改课程");
			replaceCourseWindow.setSize(500,350);
			replaceCourseWindow.setResizable(false);
			replaceCourseWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			replaceCourseWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","课程名称","授课教师","授课班级","学      分","学      时"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			name = new JLabel(label[2]);
			nameString = new JTextField();
			nameString.setText((String) model.getValueAt(table.getSelectedRow(), 0));
			teacher = new JLabel(label[3]);
			teacherString = new JTextField();
			teacherString.setText((String) model.getValueAt(table.getSelectedRow(), 1));
			schoolClass = new JLabel(label[4]);
			schoolClassString = new JTextField();
			schoolClassString.setText((String) model.getValueAt(table.getSelectedRow(), 2));
			credit = new JLabel(label[5]);
			creditString = new JTextField();
			creditString.setText((String) model.getValueAt(table.getSelectedRow(), 3));
			time = new JLabel(label[6]);
			timeString = new JTextField();
			timeString.setText((String) model.getValueAt(table.getSelectedRow(), 4));
			       
			yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacher.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacherString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			credit.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			creditString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			time.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			timeString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			replaceCourseWindow.add(name);
			replaceCourseWindow.add(nameString);
			replaceCourseWindow.add(teacher);
			replaceCourseWindow.add(teacherString);
			replaceCourseWindow.add(schoolClass);
			replaceCourseWindow.add(schoolClassString);
			replaceCourseWindow.add(credit);
			replaceCourseWindow.add(creditString);
			replaceCourseWindow.add(time);
			replaceCourseWindow.add(timeString);
			replaceCourseWindow.add(yes);
			replaceCourseWindow.add(no);
			
			name.setBounds(0, 10, 60, 30);
			nameString.setBounds(80, 10, 150, 30);
			teacher.setBounds(250, 10, 60, 30);
			teacherString.setBounds(330, 10, 150, 30);
			schoolClass.setBounds(0, 80, 60, 30);
			schoolClassString.setBounds(80, 80, 150, 30);
			credit.setBounds(250, 80, 60, 30);
			creditString.setBounds(330, 80, 150, 30);
			time.setBounds(0, 150, 60, 30);
			timeString.setBounds(80, 150, 150, 30);
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
			int x1 = (dim1.width-replaceCourseWindow.getSize().width)/2;
			int y1 = (dim1.height-replaceCourseWindow.getSize().height)/2;
			replaceCourseWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			replaceCourseWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			replaceCourseWindow.validate();
			replaceCourseWindow.setVisible(true);	
			replaceCourseWindow.setAlwaysOnTop(true);
						
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {nameString.getText(),teacherString.getText(),schoolClassString.getText(),
							creditString.getText(),timeString.getText()};
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
							String str1 = (String)model.getValueAt(i, 0);
							String str2 = (String)model.getValueAt(i, 2);
							if(s[0].compareTo(str1) == 0 && s[2].compareTo(str2) == 0) {
								flag = false;
								se.show();
								break;
							}
							else {
								flag = true;
							}
						}
						if(flag == true) {
							courseInformation = s;
							String str = "UPDATE CourseList SET name = '" + s[0] + "',teacher = '" + s[1] + "',schoolClass = '" + s[2] + "',credit = " + s[3]
									+ ",time = " + s[4] + " WHERE name = '" + model.getValueAt(table.getSelectedRow(), 0) + "' AND schoolClass = '" + model.getValueAt(table.getSelectedRow(), 2) + "';";
							System.out.println(str);
							for(int i = 0;i < s.length; i++) {
								model.setValueAt(s[i], table.getSelectedRow(), i);
							}
							try {
								stmt.executeUpdate(str);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							replaceCourseWindow.dispose();
						}						
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					replaceCourseWindow.dispose();
				}
			}
			
		}

	}

    public class FindCourse {
    	
    	JFrame findCourseWindow;
		JButton yes;
		JButton no;
		JLabel name;
		JTextField nameString;
		JLabel teacher;
		JTextField teacherString;
		JLabel schoolClass;
		JTextField schoolClassString;
		JLabel credit;
		JTextField creditString;
		JLabel time;
		JTextField timeString;
		InsertError ie;
		FindError fe;
		FindResult fr;
		public boolean flag = false;
		
		public FindCourse() {
			
			fr = new FindResult();
			ie = new InsertError();
			fe = new FindError();
			
			findCourseWindow = new JFrame("查找课程");
			findCourseWindow.setSize(500,350);
			findCourseWindow.setResizable(false);
			findCourseWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			findCourseWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","课程名称","授课教师","授课班级","学      分","学      时"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			name = new JLabel(label[2]);
			nameString = new JTextField();
			teacher = new JLabel(label[3]);
			teacherString = new JTextField();
			schoolClass = new JLabel(label[4]);
			schoolClassString = new JTextField();
			credit = new JLabel(label[5]);
			creditString = new JTextField();
			time = new JLabel(label[6]);
			timeString = new JTextField();
			       
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacher.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			teacherString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			credit.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			creditString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			time.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			timeString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

			findCourseWindow.add(name);
			findCourseWindow.add(nameString);
			findCourseWindow.add(teacher);
			findCourseWindow.add(teacherString);
			findCourseWindow.add(schoolClass);
			findCourseWindow.add(schoolClassString);
			findCourseWindow.add(credit);
			findCourseWindow.add(creditString);
			findCourseWindow.add(time);
			findCourseWindow.add(timeString);
			findCourseWindow.add(yes);
			findCourseWindow.add(no);
			
			name.setBounds(0, 10, 60, 30);
			nameString.setBounds(80, 10, 150, 30);
			teacher.setBounds(250, 10, 60, 30);
			teacherString.setBounds(330, 10, 150, 30);
			schoolClass.setBounds(0, 80, 60, 30);
			schoolClassString.setBounds(80, 80, 150, 30);
			credit.setBounds(250, 80, 60, 30);
			creditString.setBounds(330, 80, 150, 30);
			time.setBounds(0, 150, 60, 30);
			timeString.setBounds(80, 150, 150, 30);
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
			int x1 = (dim1.width-findCourseWindow.getSize().width)/2;
			int y1 = (dim1.height-findCourseWindow.getSize().height)/2;
			findCourseWindow.setLocation(x1,y1);
			row = table.getRowCount();
			
			findCourseWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			findCourseWindow.validate();
			findCourseWindow.setVisible(true);	
			findCourseWindow.setAlwaysOnTop(true);
			
		}
    	
    	class MyListener implements ActionListener {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == yes) {
					String s[] = {nameString.getText(),teacherString.getText(),schoolClassString.getText(),
							creditString.getText(),timeString.getText()};
					int value[] = {0,0,0,0,0};
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
								  findCourseWindow.dispose();
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
					findCourseWindow.dispose();
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
    			
    			String []columnNames = {"课程名称","授课教师","授课班级","学分","学时"};
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
    			findResultWindow.setLocation(courseListWindow.getBounds().x-5,courseListWindow.getBounds().y);
    			
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
