package StudentOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

@SuppressWarnings("unused")
public class StudentInformation {
	
	JFrame informationWindow;
	JLabel ID;
	JLabel IDString;
	JLabel name;
	JLabel nameString;
	JLabel gender;
	JLabel genderString;
	JLabel birthday;
	JLabel birthdayString;
	JLabel ethnic;
	JLabel ethnicString;
	JLabel institute;
	JLabel instituteString;
	JLabel dept;
	JLabel deptString;
	JLabel schoolClass;
	JLabel schoolClassString;
	Student s;
	String id;
	Connection con;
	Statement stmt;
	public static boolean INFORMATION = false;
	
	public StudentInformation(String id) throws Exception {
		
		this.id = id;
		
		informationWindow = new JFrame("个人信息");
		informationWindow.setLayout(new GridLayout(8,1));
		INFORMATION = true;
		informationWindow.setResizable(false);
		informationWindow.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        informationWindow.setIconImage(image);
		informationWindow.setSize(850,520);
		
		//连接数据库
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/nrc";
		String user = "root";
		String password = "0721";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
		
		String str1 = "SELECT * FROM StudentList WHERE ID = '" + this.id + "';";
						
		ResultSet rs = stmt.executeQuery(str1);	
		String p[][] = new String[1][8];
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
			p[0][0] = IDString;
			p[0][1] = nameString;
			p[0][2] = genderString;
			p[0][3] = birthdayString;
			p[0][4] = ethnicString;
			p[0][5] = instituteString;;
			p[0][6] = deptString;
			p[0][7] = schoolClassString;
		}
		
		JPanel row1 = new JPanel(new GridLayout(1,2));
		JPanel row2 = new JPanel(new GridLayout(1,2));
		JPanel row3 = new JPanel(new GridLayout(1,2));
		JPanel row4 = new JPanel(new GridLayout(1,2));
		JPanel row5 = new JPanel(new GridLayout(1,2));
		JPanel row6 = new JPanel(new GridLayout(1,2));
		JPanel row7 = new JPanel(new GridLayout(1,2));
		JPanel row8 = new JPanel(new GridLayout(1,2));
		
		String label[] = {" 学 生 学 号 "," 姓         名 "," 性         别 "," 出 生 日 期 "," 民         族 ",
				" 院         系 "," 专         业 "," 班         级 "};
		ID = new JLabel(label[0]);
		IDString = new JLabel(p[0][0]);
		name = new JLabel(label[1]);
		nameString = new JLabel(p[0][1]);
		gender = new JLabel(label[2]);
		genderString = new JLabel(p[0][2]);
		birthday = new JLabel(label[3]);
		birthdayString = new JLabel(p[0][3]);
		ethnic = new JLabel(label[4]);
		ethnicString = new JLabel(p[0][4]);
		institute = new JLabel(label[5]);
		instituteString = new JLabel(p[0][5]);
		dept = new JLabel(label[6]);
		deptString = new JLabel(p[0][6]);
		schoolClass = new JLabel(label[7]);
		schoolClassString = new JLabel(p[0][7]);
				
		row1.add(ID);
		row1.add(IDString);
		row2.add(name);
		row2.add(nameString);
	    row3.add(gender);
		row3.add(genderString);
		row4.add(birthday);
		row4.add(birthdayString);
		row5.add(ethnic);
		row5.add(ethnicString);
		row6.add(institute);
		row6.add(instituteString);
		row7.add(dept);
		row7.add(deptString);
		row8.add(schoolClass);
		row8.add(schoolClassString);
		
		informationWindow.add(row1);
		informationWindow.add(row2);
		informationWindow.add(row3);
		informationWindow.add(row4);
		informationWindow.add(row5);
		informationWindow.add(row6);
		informationWindow.add(row7);
		informationWindow.add(row8);
		
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
		
		ID.setBorder(BorderFactory.createEtchedBorder());
		IDString.setBorder(BorderFactory.createEtchedBorder());
		name.setBorder(BorderFactory.createEtchedBorder());
		nameString.setBorder(BorderFactory.createEtchedBorder());
		gender.setBorder(BorderFactory.createEtchedBorder());
		genderString.setBorder(BorderFactory.createEtchedBorder());
		birthday.setBorder(BorderFactory.createEtchedBorder());
		birthdayString.setBorder(BorderFactory.createEtchedBorder());
		ethnic.setBorder(BorderFactory.createEtchedBorder());
		ethnicString.setBorder(BorderFactory.createEtchedBorder());
		institute.setBorder(BorderFactory.createEtchedBorder());
		instituteString.setBorder(BorderFactory.createEtchedBorder());
		dept.setBorder(BorderFactory.createEtchedBorder());
		deptString.setBorder(BorderFactory.createEtchedBorder());
		schoolClass.setBorder(BorderFactory.createEtchedBorder());
		schoolClassString.setBorder(BorderFactory.createEtchedBorder());
		
		informationWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		informationWindow.validate();
		informationWindow.setVisible(false);
		
	}
	
}
