import java.time.LocalDate;

public class User {


    private String firstname;
    private String lastName;
    private LocalDate birthDate;
    private int phone;

    public User(String firstname, String lastName, LocalDate birthDate, int phone) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (phone != user.phone) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return birthDate != null ? birthDate.equals(user.birthDate) : user.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + phone;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phone=" + phone +
                '}';
    }
}