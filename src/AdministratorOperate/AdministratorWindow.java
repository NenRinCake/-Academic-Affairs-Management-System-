package AdministratorOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class AdministratorWindow {
	
	JFrame administratorWindow;
	JLabel title;
	JButton exit;
	JLabel identity;
	JMenuBar menu;
	JMenuItem teacherList;
	JMenuItem studentList;
	JMenuItem passwordList;
	JMenuItem courseList;
	JLabel menuLabel;
	JLabel time;
	JLabel slogan;
	TeacherList tl;
	StudentList sl;
	PasswordList pl;
	CourseList cl;
	Exit exi;
	int X;
	int Y;
	
	public AdministratorWindow() throws Exception {
		
		exi = new Exit();
		tl = new TeacherList();
		sl = new StudentList();
		pl = new PasswordList();
		cl = new CourseList();
		
		administratorWindow = new JFrame("管理员操作");
		administratorWindow.setSize(1000,600);
		administratorWindow.setResizable(false);
		administratorWindow.setUndecorated(true);
		administratorWindow.setLayout(null);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
		administratorWindow.setIconImage(image);
        ImageIcon background = new ImageIcon("src/Picture/picture1.JPG");
		JLabel back = new JLabel(background);
		back.setBounds(0,0,administratorWindow.getSize().width,administratorWindow.getSize().height);
		JPanel imagePanel = (JPanel) administratorWindow.getContentPane();
		imagePanel.setOpaque(false);
		administratorWindow.getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));
        Dimension dim = kit.getScreenSize();
      	int x = (dim.width-administratorWindow.getSize().width)/2;
      	int y = (dim.height-administratorWindow.getSize().height)/2;
      	administratorWindow.setLocation(x,y);
      	X = administratorWindow.getBounds().x;
      	Y = administratorWindow.getBounds().y;
      	
      	String label[] = {"学生管理系统","退出","身份：管理员","  菜 单 列 表 ",
      			" 教 师 名 单 "," 学 生 名 单 "," 用 户 帐 密 "," 课 程 安 排 ","--- Eclipse First, the Rest Nowhere ---"};
      	time = new JLabel();
      	this.setTimer(time);
      	JLabel l = new JLabel();
      	title = new JLabel(label[0]);
      	exit = new JButton(label[1]);
      	identity = new JLabel(label[2]);
      	menuLabel = new JLabel(label[3]);
      	teacherList = new JMenuItem(label[4]);
      	studentList = new JMenuItem(label[5]);
      	passwordList = new JMenuItem(label[6]);
      	courseList = new JMenuItem(label[7]);
      	slogan = new JLabel(label[8]);
      	menu = new JMenuBar();
      	menu.setLayout(new GridLayout(10,1));
      	menu.add(menuLabel);
      	menu.add(teacherList);
      	menu.add(studentList);
      	menu.add(passwordList);
      	menu.add(courseList);
      	
      	title.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	exit.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	identity.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	teacherList.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	studentList.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	passwordList.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	courseList.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	menuLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	time.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	slogan.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	
      	administratorWindow.add(title);
      	administratorWindow.add(identity);
      	administratorWindow.add(exit);
      	administratorWindow.add(menu);
      	administratorWindow.add(l);
      	administratorWindow.add(time);
      	administratorWindow.add(slogan);
      	
      	slogan.setBounds(210, 10, 300, 30);
      	time.setBounds(590, 10, 230, 30);
      	title.setBounds(23, 10, 100, 30);
      	exit.setBounds(920, 5, 70, 40);
      	identity.setBounds(820, 10, 100, 30);
      	menu.setBounds(20, 50, 100, 530);
      	l.setBounds(130, 50, 860, 530);
      	
      	MyListener listen = new MyListener();
		exit.addActionListener(listen);
		teacherList.addActionListener(listen);
		studentList.addActionListener(listen);
		passwordList.addActionListener(listen);
		courseList.addActionListener(listen);
      	
      	teacherList.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                teacherList.setBackground(Color.lightGray);
            }
		});
		teacherList.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                teacherList.setBackground(null);
            }
		});
		studentList.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                studentList.setBackground(Color.lightGray);
            }
		});
		studentList.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                studentList.setBackground(null);
            }
		});
		passwordList.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                passwordList.setBackground(Color.lightGray);
            }
		});
		passwordList.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                passwordList.setBackground(null);
            }
		});
		courseList.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                courseList.setBackground(Color.lightGray);
            }
		});
		courseList.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                courseList.setBackground(null);
            }
		});
      	
      	title.setForeground(Color.WHITE); 	
		slogan.setForeground(Color.WHITE); 
		identity.setForeground(Color.WHITE); 	
		exit.setForeground(Color.WHITE); 	
		time.setForeground(Color.WHITE); 	
		menuLabel.setForeground(Color.WHITE); 
      	
        teacherList.setOpaque(true);
      	studentList.setOpaque(true);
      	passwordList.setOpaque(true);
      	courseList.setOpaque(true);
      	exit.setContentAreaFilled(false);
		menu.setOpaque(false);
      	
      	l.setBorder(BorderFactory.createEtchedBorder());
		menu.setBorder(BorderFactory.createEtchedBorder());
		exit.setBorder(BorderFactory.createEtchedBorder());
		teacherList.setBorder(BorderFactory.createEtchedBorder());
		studentList.setBorder(BorderFactory.createEtchedBorder());
		passwordList.setBorder(BorderFactory.createEtchedBorder());
		courseList.setBorder(BorderFactory.createEtchedBorder());
		
		exit.setFocusPainted(false);
      	
      	administratorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      	administratorWindow.validate();
      	administratorWindow.setVisible(true);
      	
	}
	
	class MyListener implements ActionListener{

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == exit) {
				try {
					pl.passwordListWindow.dispose();
					pl.PASSWORDLIST = false;
				}catch(Exception e1) {
					
				}
				if(exi.EXIT == false) {
					exi.exitWindow.setAlwaysOnTop(true);					
					exi.show();
				}
			}
			else if(e.getSource() == teacherList) {
				try {
					pl.passwordListWindow.dispose();
					pl.PASSWORDLIST = false;
				}catch(Exception e1) {
					
				}
				if(tl.TEACHERLIST == true) {
					administratorWindow.setAlwaysOnTop(false);
					tl.teacherListWindow.setLocation(X+135,Y+55);
					tl.teacherListWindow.setVisible(true);
				}		
			}
			else if(e.getSource() == studentList) {
				try {
					pl.passwordListWindow.dispose();
					pl.PASSWORDLIST = false;
				}catch(Exception e1) {
					
				}				
				if(sl.STUDENTLIST == true) {
					administratorWindow.setAlwaysOnTop(false);
					sl.studentListWindow.setLocation(X+135,Y+55);
					sl.studentListWindow.setVisible(true);
				}	
			}
			else if(e.getSource() == passwordList) {
				if(pl.PASSWORDLIST == false) {
					administratorWindow.setAlwaysOnTop(false);
					try {
						pl = new PasswordList();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					pl.passwordListWindow.setLocation(X+135,Y+55);
					pl.passwordListWindow.setVisible(true);
				}
				else {
					administratorWindow.setAlwaysOnTop(false);
					pl.passwordListWindow.setLocation(X+135,Y+55);
					pl.passwordListWindow.setVisible(true);
				}
			}
			else if(e.getSource() == courseList) {
				try {
					pl.passwordListWindow.dispose();
					pl.PASSWORDLIST = false;
				}catch(Exception e1) {
					
				}
				if(cl.COURSELIST == true) {
					administratorWindow.setAlwaysOnTop(false);
					cl.courseListWindow.setLocation(X+135,Y+55);
					cl.courseListWindow.setVisible(true);
				}
			}
		}
		
	}
	
private void setTimer(JLabel time) {
		
		final JLabel varTime = time;
		Timer timeAction = new Timer(100, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {				
				long timemillis = System.currentTimeMillis();
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				varTime.setText(df.format(new Date(timemillis)));				
			}
		});
		
		timeAction.start();
	}

}
