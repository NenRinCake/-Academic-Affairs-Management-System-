package StudentOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class StudentWindow {
	
	JFrame studentWindow;
	JLabel title;
	JButton exit;
	JLabel identity;
	JMenuBar menu;
	JMenuItem information;
	JMenuItem course;
	JMenuItem grade;
	JMenuItem replacePassword;
	JLabel menuLabel;
	JLabel time;
	JLabel slogan;
	StudentInformation inf;
	Grade gra;
	Course cou;
	ReplacePassword rep;
	Exit exi;
	int X;
	int Y;
	String id;
	
	public StudentWindow(String id) throws Exception{
		
		this.id = id;
		inf = new StudentInformation(this.id);
		gra = new Grade(this.id);
		cou = new Course(this.id);
		rep = new ReplacePassword(this.id);
		exi = new Exit();
		
		studentWindow = new JFrame("学生操作");
		studentWindow.setSize(1000,600);
		studentWindow.setResizable(false);
		studentWindow.setUndecorated(true);
		studentWindow.setLayout(null);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage("src/Picture/picture3.JPG");       
        studentWindow.setIconImage(image);
        ImageIcon background = new ImageIcon("src/Picture/picture5.JPG");
		JLabel back = new JLabel(background);
		back.setBounds(0,0,studentWindow.getSize().width,studentWindow.getSize().height);
		JPanel imagePanel = (JPanel) studentWindow.getContentPane();
		imagePanel.setOpaque(false);
		studentWindow.getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));
        Dimension dim = kit.getScreenSize();
      	int x = (dim.width-studentWindow.getSize().width)/2;
      	int y = (dim.height-studentWindow.getSize().height)/2;
      	studentWindow.setLocation(x,y);
      	X = studentWindow.getBounds().x;
      	Y = studentWindow.getBounds().y;
      	
      	String label[] = {"学生管理系统","退出","身份：学生"," 个 人 信 息 ","  菜 单 列 表 ",
      			" 成 绩 查 询 "," 选 课 结 果 "," 修 改 密 码 ","--- Eclipse First, the Rest Nowhere ---"};
      	time = new JLabel();
      	this.setTimer(time);
      	JLabel l = new JLabel();
      	title = new JLabel(label[0]);
      	exit = new JButton(label[1]);
      	identity = new JLabel(label[2]);
      	information = new JMenuItem(label[3]);
      	menuLabel = new JLabel(label[4]);
      	grade = new JMenuItem(label[5]);
      	course = new JMenuItem(label[6]);
      	replacePassword = new JMenuItem(label[7]);
      	slogan = new JLabel(label[8]);    	
      	menu = new JMenuBar();
      	menu.setLayout(new GridLayout(10,1));
      	menu.add(menuLabel);
      	menu.add(information);
      	menu.add(course);
      	menu.add(grade);
      	menu.add(replacePassword);
      	
      	title.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	exit.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	identity.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	information.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	course.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	grade.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	replacePassword.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	menuLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	time.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
      	slogan.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));     	
      	
      	studentWindow.add(title);
      	studentWindow.add(identity);
      	studentWindow.add(exit);
      	studentWindow.add(menu);
      	studentWindow.add(l);
      	studentWindow.add(time);
      	studentWindow.add(slogan);
      	
      	slogan.setBounds(210, 10, 300, 30);
      	time.setBounds(590, 10, 230, 30);
      	title.setBounds(23, 10, 200, 30);
      	exit.setBounds(920, 5, 70, 40);
      	identity.setBounds(830, 10, 100, 30);
      	menu.setBounds(20, 50, 100, 530);
      	l.setBounds(130, 50, 860, 530);
      	
      	MyListener listen = new MyListener();
		exit.addActionListener(listen);
		information.addActionListener(listen);
		course.addActionListener(listen);
		grade.addActionListener(listen);
		replacePassword.addActionListener(listen);
		
		information.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                information.setBackground(Color.lightGray);
            }
		});
		information.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                information.setBackground(null);
            }
		});
		course.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                course.setBackground(Color.lightGray);
            }
		});
		course.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                course.setBackground(null);
            }
		});
		grade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                grade.setBackground(Color.lightGray);
            }
		});
		grade.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                grade.setBackground(null);
            }
		});
		replacePassword.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                replacePassword.setBackground(Color.lightGray);
            }
		});
		replacePassword.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                replacePassword.setBackground(null);
            }
		});
		
		title.setForeground(Color.WHITE); 	
		slogan.setForeground(Color.WHITE); 
		identity.setForeground(Color.WHITE); 	
		exit.setForeground(Color.WHITE); 	
		time.setForeground(Color.WHITE); 	
		menuLabel.setForeground(Color.WHITE); 	
		
		information.setOpaque(true);
		course.setOpaque(true);
		grade.setOpaque(true);
		replacePassword.setOpaque(true);
		exit.setContentAreaFilled(false);
		menu.setOpaque(false);
		
		l.setBorder(BorderFactory.createEtchedBorder());
		menu.setBorder(BorderFactory.createEtchedBorder());
		exit.setBorder(BorderFactory.createEtchedBorder());
		information.setBorder(BorderFactory.createEtchedBorder());
		course.setBorder(BorderFactory.createEtchedBorder());
		grade.setBorder(BorderFactory.createEtchedBorder());
		replacePassword.setBorder(BorderFactory.createEtchedBorder());
		
		exit.setFocusPainted(false);
		
		studentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      	studentWindow.validate();
		studentWindow.setVisible(true);
		
	}
	
	class MyListener implements ActionListener{

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == exit) {
				if(exi.EXIT == false) {
					exi.exitWindow.setAlwaysOnTop(true);
					exi.show();
				}
			}
			else if(e.getSource() == information) {
				if(inf.INFORMATION == true) {
			    	studentWindow.setAlwaysOnTop(false);
			    	inf.informationWindow.setLocation(X+135,Y+55);
			    	inf.informationWindow.setVisible(true);
			    }
			}
			else if(e.getSource() == course) {
				if(cou.COURSE == true) {
			    	studentWindow.setAlwaysOnTop(false);
			    	cou.courseWindow.setLocation(X+135,Y+55);
			    	cou.courseWindow.setVisible(true);
			    }
			}
			else if(e.getSource() == grade) {
				if(gra.GRADE == true) {
			    	studentWindow.setAlwaysOnTop(false);
			    	gra.gradeWindow.setLocation(X+135,Y+55);
			    	gra.gradeWindow.setVisible(true);
			    }
			}
			else if(e.getSource() == replacePassword) {
				if(rep.REPLACEPASSWORD == true) {
					studentWindow.setAlwaysOnTop(false);
			    	rep.replacePasswordWindow.setLocation(X+135,Y+55);
			    	rep.replacePasswordWindow.setVisible(true);
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
