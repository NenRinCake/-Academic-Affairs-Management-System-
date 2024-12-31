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


public class Grade {
	
	JFrame gradeWindow;
	JTable table;
	DefaultTableModel model;
	DefaultTableCellRenderer center;
	JButton replace;
	JScrollPane scrollPane;
	ReplaceError re;
	ReplaceGrade rg;
	Connection con;
	Statement stmt;
	String id;
	public String studentGrade[];
	public static boolean GRADE = false;
	
	@SuppressWarnings("serial")
	public Grade(String id) throws Exception{
		
		this.id = id;
		re= new ReplaceError();
		
		GRADE = true;
		gradeWindow = new JFrame("成绩录入");
		gradeWindow.setLayout(null);
		gradeWindow.setResizable(false);
		gradeWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        gradeWindow.setIconImage(image);
		gradeWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();		
		
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
		String p[][] = new String[num][5];
		String str1 = "SELECT * FROM GradeList WHERE courseName IN ( SELECT name FROM CourseList WHERE teacher = '" + name + "') AND studentID IN " +
		"( SELECT ID FROM StudentList WHERE schoolClass IN ( SELECT schoolClass FROM CourseList WHERE teacher = '" + name + "'));";
		rs = stmt.executeQuery(str1);
		int cnt = 0;		
		//导入数据
	    while(rs.next()) {
			String courseNameString = rs.getString("courseName");
			String studentNameString = rs.getString("studentName");
			String studentIDString = rs.getString("studentID");
			String gradeString = rs.getString("grade");
			String commentString = rs.getString("comment");
			p[cnt][0] = courseNameString;
			p[cnt][1] = studentNameString;
			p[cnt][2] = studentIDString;
			p[cnt][3] = gradeString;
			p[cnt][4] = commentString;
			cnt++;
	    }
		
		String label[] = {"修改"};
		String []columnNames = { "教授班级", "教授学生", "学生学号", "成绩" ,"备注" };
		String[][] tableValues = p;
		replace = new JButton(label[0]);
		model = new DefaultTableModel(tableValues,columnNames);
		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) { 
			 	 return false;
			  }
		};
		table.setDefaultRenderer(Object.class, center);
		scrollPane = new JScrollPane(table);
		table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 30));
		table.setRowHeight(30); 
		
		gradeWindow.add(scrollPane);
		gradeWindow.add(replace);
		
        table.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        replace.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		scrollPane.setBounds(0, 0, 850, 480);
		replace.setBounds(380, 480, 70, 40);
		
		MyListener listen = new MyListener();
		replace.addActionListener(listen);
		
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
		
		replace.setContentAreaFilled(false);
		
		replace.setFocusPainted(false);
		
		gradeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gradeWindow.validate();
		gradeWindow.setVisible(false);
		
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
					rg = new ReplaceGrade();
					rg.show();
				}
			}
			
		}
		
	}	
	
public class ReplaceGrade {
		
		JFrame replaceGradeWindow;
		JButton yes;
		JButton no;
		JLabel ID;
		JLabel IDString;
		JLabel name;
		JLabel nameString;
		JLabel schoolClass;
		JLabel schoolClassString;
		JLabel grade;
		JTextField gradeString;
		JLabel comment;
		JTextField commentString;
		ReplaceError re;
		InsertError ie;
		public boolean flag = false;
		
		public ReplaceGrade() {
			
			re = new ReplaceError();
			ie = new InsertError();
			
			replaceGradeWindow = new JFrame("修改成绩");
			replaceGradeWindow.setSize(500,350);
			replaceGradeWindow.setResizable(false);
			replaceGradeWindow.setLayout(null);
			Toolkit kit2 = Toolkit.getDefaultToolkit();
			Image image2 = kit2.getImage("src/Picture/picture3.JPG");
			replaceGradeWindow.setIconImage(image2);
			
			String label[] = {"确定","取消","学      号","姓      名","班      级","成      绩","备      注"};
			yes = new JButton(label[0]);
			no = new JButton(label[1]);
			ID = new JLabel(label[2]);
			IDString = new JLabel((String) model.getValueAt(table.getSelectedRow(), 2));
			name = new JLabel(label[3]);
			nameString = new JLabel((String) model.getValueAt(table.getSelectedRow(), 1));
			schoolClass = new JLabel(label[4]);
			schoolClassString = new JLabel((String) model.getValueAt(table.getSelectedRow(), 0));
			grade = new JLabel(label[5]);
			gradeString = new JTextField((String) model.getValueAt(table.getSelectedRow(), 3));
			comment = new JLabel(label[6]);
			commentString = new JTextField((String) model.getValueAt(table.getSelectedRow(), 4));
			       
	        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));		
			no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			ID.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			IDString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			name.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			nameString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClass.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			schoolClassString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			grade.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			gradeString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			comment.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			commentString.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
			
			
			replaceGradeWindow.add(ID);
			replaceGradeWindow.add(IDString);
			replaceGradeWindow.add(name);
			replaceGradeWindow.add(nameString);
			replaceGradeWindow.add(schoolClass);
			replaceGradeWindow.add(schoolClassString);
			replaceGradeWindow.add(grade);
			replaceGradeWindow.add(gradeString);
			replaceGradeWindow.add(schoolClassString);
			replaceGradeWindow.add(comment);
			replaceGradeWindow.add(commentString);
			replaceGradeWindow.add(yes);
			replaceGradeWindow.add(no);
			
			schoolClass.setBounds(0, 10, 60, 30);
			schoolClassString.setBounds(80, 10, 150, 30);
			name.setBounds(250, 10, 60, 30);
			nameString.setBounds(330, 10, 150, 30);
			ID.setBounds(0, 80, 60, 30);
			IDString.setBounds(80, 80, 150, 30);
			grade.setBounds(250, 80, 60, 30);
			gradeString.setBounds(330, 80, 150, 30);
			comment.setBounds(0, 150, 60, 30);
			commentString.setBounds(80, 150, 400, 30);
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
			int x1 = (dim1.width-replaceGradeWindow.getSize().width)/2;
			int y1 = (dim1.height-replaceGradeWindow.getSize().height)/2;
			replaceGradeWindow.setLocation(x1,y1);
			
			replaceGradeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			replaceGradeWindow.validate();
			replaceGradeWindow.setVisible(true);	
			replaceGradeWindow.setAlwaysOnTop(true);
			
		}
		
		class MyListener implements ActionListener{

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == yes) {
					String s[] = {gradeString.getText(),commentString.getText()};
					if(s[0].compareTo("") != 0) {
							flag = true;
						}
						else {
							flag = false;
						}
					if(flag == true) {
						studentGrade = s;
						String str = "UPDATE GradeList SET grade = " + Integer. parseInt(s[0]) + ",comment = '" + s[1] + "' WHERE courseName = '"
								+ model.getValueAt(table.getSelectedRow(), 0) + "' AND studentID = '" + model.getValueAt(table.getSelectedRow(), 2)
								+ "';";
						try {
							stmt.execute(str);
						} catch (SQLException e1) {
							
						}
						model.setValueAt(s[0], table.getSelectedRow(), 3);
						model.setValueAt(s[1], table.getSelectedRow(), 4);
						replaceGradeWindow.dispose();
					}
					else {
						if(ie.INSERT == false) {
						    ie.show();
						}
					}				
					
				}
				else {
					replaceGradeWindow.dispose();
				}
			}
			
		}

	}
	
}
