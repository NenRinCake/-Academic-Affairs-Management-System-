package TeacherOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

@SuppressWarnings("unused")
public class TeacherInformation {
	
	JFrame informationWindow;
	JLabel ID;
	JLabel IDString;
	JLabel name;
	JLabel nameString;
	JLabel gender;
	JLabel genderString;
	JLabel birthday;
	JLabel birthdayString;
	JLabel institute;
	JLabel instituteString;
	JLabel title;
	JLabel titleString;
	Teacher t;
	String id;
	Connection con;
	Statement stmt;
	public static boolean INFORMATION = false;
	
	public TeacherInformation(String id) throws Exception {
		
		this.id = id;
		
		informationWindow = new JFrame("个人信息");
		informationWindow.setLayout(new GridLayout(6,1));
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
		
		String str1 = "SELECT * FROM TeacherList WHERE ID = '" + this.id + "';";
						
		ResultSet rs = stmt.executeQuery(str1);	
		String p[][] = new String[1][6];
		//导入数据
		while(rs.next()) {
			String IDString = rs.getString("ID");
			String nameString = rs.getString("name");
			String genderString = rs.getString("gender");
			String birthdayString = rs.getString("birthday");
			String instituteString = rs.getString("institute");
			String titleString = rs.getString("title");
			p[0][0] = IDString;
			p[0][1] = nameString;
			p[0][2] = genderString;
			p[0][3] = birthdayString;
			p[0][4] = instituteString;
			p[0][5] = titleString;;
		}
		
		JPanel row1 = new JPanel(new GridLayout(1,2));
		JPanel row2 = new JPanel(new GridLayout(1,2));
		JPanel row3 = new JPanel(new GridLayout(1,2));
		JPanel row4 = new JPanel(new GridLayout(1,2));
		JPanel row5 = new JPanel(new GridLayout(1,2));
		JPanel row6 = new JPanel(new GridLayout(1,2));
		
		String label[] = {" 教 师 编 号 "," 姓         名 "," 性         别 "," 出 生 日 期 "," 院         系 "," 职         称 "};
		ID = new JLabel(label[0]);
		IDString = new JLabel(p[0][0]);
		name = new JLabel(label[1]);
		nameString = new JLabel(p[0][1]);
		gender = new JLabel(label[2]);
		genderString = new JLabel(p[0][2]);
		birthday = new JLabel(label[3]);
		birthdayString = new JLabel(p[0][3]);
		institute = new JLabel(label[4]);
		instituteString = new JLabel(p[0][4]);
		title = new JLabel(label[5]);
		titleString = new JLabel(p[0][5]);
				
		row1.add(ID);
		row1.add(IDString);
		row2.add(name);
		row2.add(nameString);
	    row3.add(gender);
	    row3.add(genderString);
		row4.add(birthday);
		row4.add(birthdayString);
		row5.add(institute);
		row5.add(instituteString);
		row6.add(title);
		row6.add(titleString);
		
		informationWindow.add(row1);
		informationWindow.add(row2);
		informationWindow.add(row3);
		informationWindow.add(row4);
		informationWindow.add(row5);
		informationWindow.add(row6);
		
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
		
		ID.setBorder(BorderFactory.createEtchedBorder());
		IDString.setBorder(BorderFactory.createEtchedBorder());
		name.setBorder(BorderFactory.createEtchedBorder());
		nameString.setBorder(BorderFactory.createEtchedBorder());
		gender.setBorder(BorderFactory.createEtchedBorder());
		genderString.setBorder(BorderFactory.createEtchedBorder());
		birthday.setBorder(BorderFactory.createEtchedBorder());
		birthdayString.setBorder(BorderFactory.createEtchedBorder());
		institute.setBorder(BorderFactory.createEtchedBorder());
		instituteString.setBorder(BorderFactory.createEtchedBorder());
		title.setBorder(BorderFactory.createEtchedBorder());
		titleString.setBorder(BorderFactory.createEtchedBorder());
		
		informationWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		informationWindow.validate();
		informationWindow.setVisible(false);
		
	}
	
}
