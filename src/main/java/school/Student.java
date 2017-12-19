package school;

import java.util.Arrays;
import java.util.List;

public final class Student {

    private final String name;
    private final float gpa;
    private final List<String> courses;
    private final List<String> scholarships;

    private Student(String name, float gpa, List<String> courses, List<String> scholarships) {
        this.name = name;
        this.gpa = gpa;
        this.courses = courses;
        this.scholarships = scholarships;
    }

    public static Student ofNameGpaCourses(
            String name, float gpa, String... courses) {
        return new Student(name, gpa, Arrays.asList(courses), null);
    }

    public static Student ofNameGpaCoursesScholarships(
            String name, float gpa, String... courses) {
        return new Student(name, gpa, Arrays.asList(courses),
                Arrays.asList("Small", "Helpful"));
    }

    public String getName() {
        return name;
    }

    public float getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<String> getscholarships() {
        return scholarships;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
            (scholarships == null
                ? ", No trunk"
                : ", scholarships=" + scholarships)
            + '}';
    }
}
