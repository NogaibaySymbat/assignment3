//import java.util.Random;
public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Math.abs(id * 37 + 41);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "MyTestingClass{" + "id=" + id + '}';
    }
}
