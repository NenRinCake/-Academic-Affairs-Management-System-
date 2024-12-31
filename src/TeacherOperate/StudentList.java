package TeacherOperate;

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
	JButton find;
	JScrollPane scrollPane;
	FindStudent fs;
	int row;
	Connection con;
	Statement stmt;
	String id;
	public String studentInformation[];
	public static boolean STUDENTLIST = false;
	
	@SuppressWarnings("serial")
	public StudentList(String id) throws Exception{
		
		this.id = id;

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
		Statement stmt1 = con.createStatement();
		Statement stmt2 = con.createStatement();
		
		String str2 = "SELECT * FROM TeacherList WHERE ID = '" + this.id + "';";
		ResultSet rs = stmt.executeQuery(str2);
		String name = null;
		while(rs.next()) {
			name = rs.getString("name");
		}
		
		String str = "SELECT COUNT(*) FROM StudentList WHERE schoolClass IN ( SELECT schoolClass FROM CourseList WHERE teacher = '" + name + "');";
		rs = stmt.executeQuery(str);
		int num = 0;  //判断有多少行来创建合适的二维数组
		while(rs.next()) {
			num = rs.getInt(1);
		}		
		String p[][] = new String[num][8];
		String str1 = "SELECT * FROM StudentList WHERE schoolClass IN ( SELECT schoolClass FROM CourseList WHERE teacher = '" + name + "');";
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
	    
	    //成绩添加
	    String str3 = "SELECT name,schoolClass FROM CourseList WHERE teacher = '" + name + "';";
	    ResultSet rs1 = null;
 	    rs = stmt.executeQuery(str3);	    
	    while(rs.next()) {
	    	String nameString = rs.getString("name");
	    	String schoolClassString = rs.getString("schoolClass");
	    	String str4 = "SELECT name,ID FROM StudentList WHERE schoolClass = '" + schoolClassString + "';";
	    	rs1 = stmt1.executeQuery(str4);
	    	while(rs1.next()) {
	    		String str5 = "INSERT INTO GradeList (CourseName,StudentName,StudentID,grade,comment) VALUES ('" + nameString + 
	    				"','" + rs1.getString("name") + "','" + rs1.getString("ID") + "',0,null);";
	    		try{
	    			stmt2.execute(str5);
	    		}catch(SQLException e) {
	    			
	    		}
	    	}
	    }
		String label[] = {"查找"};
		find = new JButton(label[0]);
		
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
		studentListWindow.add(find);
		
		table.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		find.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		scrollPane.setBounds(0, 0, 850, 480);
		find.setBounds(370, 480, 70, 40);
		
		MyListener listen = new MyListener();
		find.addActionListener(listen);

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

		find.setContentAreaFilled(false);
		
		find.setFocusPainted(false);
		
		studentListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		studentListWindow.validate();
		studentListWindow.setVisible(false);
		
	}
	
	class MyListener implements ActionListener {

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == find) {
				fs = new FindStudent();
				fs.show();
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
			
			findStudentWindow = new JFrame("查找学生");
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
