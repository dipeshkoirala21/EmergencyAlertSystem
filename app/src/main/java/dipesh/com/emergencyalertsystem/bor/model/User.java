package dipesh.com.emergencyalertsystem.bor.model;

public class User {

    private int id;
    private String name, email, contact, date_of_birth;

    public User(int id, String name, String email, String contact, String date_of_birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
