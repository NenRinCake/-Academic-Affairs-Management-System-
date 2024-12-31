package TeacherOperate;

public class Teacher {
	
	private String ID;
	private String name;
	private String gender;
	private String birthday;
	private String institute;
	private String title;
	
	public Teacher(String ID,String name,String gender,String birthday,String institute,String title) {
		
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.institute = institute;
		this.title = title;
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
		
}
