package StudentOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Error.*;


@SuppressWarnings("unused")
public class Grade {
	
	JFrame gradeWindow;
	JTable table;
	DefaultTableModel model;
	DefaultTableCellRenderer center;
	JScrollPane scrollPane;
	Connection con;
	Statement stmt;
	String id;
	public static boolean GRADE = false;
	
	@SuppressWarnings("serial")
	public Grade(String id) throws Exception{
		
		this.id = id;
		
		GRADE = true;
		gradeWindow = new JFrame("成绩查询");
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
		
		String str = "SELECT COUNT(*) FROM GradeList WHERE studentID = '" + this.id + "';";
		ResultSet rs = stmt.executeQuery(str);
		int num = 0;  //判断有多少行来创建合适的二维数组
		while(rs.next()) {
			num = rs.getInt(1);
		}		
		String p[][] = new String[num][2];
		int cnt = 0;		
		String str1 = "SELECT * FROM GradeList WHERE studentID = '" + this.id + "';";
		rs = stmt.executeQuery(str1);
		//导入数据
	    while(rs.next()) {
			String courseNameString = rs.getString("courseName");
			String gradeString = rs.getString("grade");
			p[cnt][0] = courseNameString;
			p[cnt][1] = gradeString;
			cnt++;
	    }
		
		String []columnNames = { "课程名称", "课程成绩" };
		String[][] tableValues = p;
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
		
        table.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		scrollPane.setBounds(0, 0, 850, 520);
		
		gradeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gradeWindow.validate();
		gradeWindow.setVisible(false);
		
	}	
	
}
