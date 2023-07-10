package hr.tvz.lbican.studapp.course;

public record CourseDTO(String name, Integer numberOfECTS) {

    public String printDetails(){
        return name() + " - " + numberOfECTS();
    }
}
