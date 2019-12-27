import java.util.Random;

public class StudentIDFactory {
    private Random random = new Random();
    public int getStudentId(){
        return random.nextInt();
    }
}
