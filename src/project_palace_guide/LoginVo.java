package project_palace_guide;

public class LoginVo {
	String id;
	String pwd;
	String img_server;
	String img_user;
	String Nationality;
	int compl;
	
	public int getCompl() {
		return compl;
	}
	public void setCompl(int compl) {
		this.compl = compl;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getImg_server() {
		return img_server;
	}
	public void setImg_server(String img_server) {
		this.img_server = img_server;
	}
	public String getImg_user() {
		return img_user;
	}
	public void setImg_user(String img_user) {
		this.img_user = img_user;
	}
}
