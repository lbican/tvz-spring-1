package hr.tvz.lbican.studapp.student;

public record StudentDTO(String jmbag, String firstName, String lastName, Integer numberOfECTS, boolean tuitionShouldBePaid) {
    public String printDetails() {
        return jmbag() + " - "  + firstName() + " " + lastName();
    }
}
