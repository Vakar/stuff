package space.vakar.stuff.persistence.model;

import space.vakar.stuff.persistence.api.DomainEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "APP_USER")
public class User implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_NAME")
    private String userName;
    private String email;
    private String pswd;

    public User() {
    }

    public User(String userName, String email, String pswd) {
        this.userName = userName;
        this.email = email;
        this.pswd = pswd;
    }

    public User(int id, String userName, String email, String pswd) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.pswd = pswd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pswd, user.pswd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, pswd);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
