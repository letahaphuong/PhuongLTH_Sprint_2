package equalHashcode;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "Le Vu Nguyen");
        Student student2 = new Student(1, "Le Vu Nguyen");

        System.out.println("HashCode Nguyên" + student1.hashCode());
        System.out.println("HashCode Vũ" + student2.hashCode());
        System.out.println(student1.equals(student2));
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student1);
        studentSet.add(student2);
        System.out.println(studentSet.size());
        for (Student element : studentSet
        ) {
            System.out.println(element);
        }
    }
}
