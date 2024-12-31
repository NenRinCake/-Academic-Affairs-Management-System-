package StudentOperate;

public class Student {
	
	private String ID;
	private String name;
	private String gender;
	private String birthday;
	private String ethnic;
	private String institute;
	private String dept;
	private String schoolClass;
	
	public Student(String ID,String name,String gender,String birthday,String ethnic,String institute,String dept,String schoolClass) {
		
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.ethnic = ethnic;
		this.institute = institute;
		this.dept = dept;
		this.schoolClass = schoolClass;
		
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

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}
	
}
