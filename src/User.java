
public class User {
	
private String username;
private String password;
private String name;
private String family;
User(String username,String password){
	this.username=username;
	this.password=password;
}

User(String username,String password,String name,String family){
	this.username=username;
	this.password=password;
	this.name=name;
	this.family=family;
}

protected String getUsername() {
	return username;
}
protected void setUsername(String username) {
	this.username = username;
}
protected String getPassword() {
	return password;
}
protected void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFamily() {
	return family;
}

public void setFamily(String family) {
	this.family = family;
}

}
