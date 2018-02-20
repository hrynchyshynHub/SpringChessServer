package network.model;
import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String password;

    public Player(Long id, String username, String firstName, String secondName, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
            Objects.equals(username, player.username) &&
            Objects.equals(firstName, player.firstName) &&
            Objects.equals(secondName, player.secondName) &&
            Objects.equals(password, player.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, firstName, secondName, password);
    }
}
