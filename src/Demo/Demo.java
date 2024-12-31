package Demo;

import Login.*;
import StudentOperate.*;
import TeacherOperate.*;
import AdministratorOperate.*;
import Error.*;
import MySQL.*;

@SuppressWarnings("unused")
public class Demo {
	public static void main(String []args) throws Exception {
		CreateDatabaseAndTable cdat = new CreateDatabaseAndTable();
		Login login = new Login();
		//TeacherWindow w1 = new TeacherWindow("T19800301");
	    //StudentWindow w2 = new StudentWindow("S20210101");
		//AdministratorWindow w3 = new AdministratorWindow();		
	}
}