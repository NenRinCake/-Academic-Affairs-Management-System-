package Error;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("unused")
public class DeleteError {
	
	JFrame deleteErrorWindow;
	JLabel delete;
	JButton yes;
	public static boolean DELETE = false;
	
	public DeleteError() {
		
		deleteErrorWindow = new JFrame("删除错误");
		deleteErrorWindow.setSize(300,200);
		deleteErrorWindow.setResizable(false);
		deleteErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		deleteErrorWindow.setIconImage(image2);
		
		String label[] = {"未进行删除选择或没有能删除的项目","确定"};
		delete = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
        delete.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		deleteErrorWindow.add(delete);
		deleteErrorWindow.add(yes);
		
		delete.setBounds(25, 50, 250, 30);
		yes.setBounds(110, 100, 70, 40);
		
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
	
	public void show() {
		
		DELETE = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-deleteErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-deleteErrorWindow.getSize().height)/2;
		deleteErrorWindow.setLocation(x1,y1);
		
		deleteErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		deleteErrorWindow.validate();
		deleteErrorWindow.setVisible(true);	
		deleteErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				DELETE = false;
				deleteErrorWindow.dispose();
			}			
		}
		
	}

}
