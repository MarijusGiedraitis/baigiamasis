package database;

public class Narys {

    private int id;
    private String name, surname, email, birthDate, sex, password;

    public Narys() {
    }

    public Narys(int id, String name, String surname, String email, String birthDate, String sex,
    		String password) {
    	
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
