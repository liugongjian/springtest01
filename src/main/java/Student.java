import java.util.Random;

public class Student {
    private Di01 d1;//构造方法注入的成员变量
    private Di02 d2;

    private Di02 d3;//Setter方法注入的成员变量
    public Di02 getD3() {
        return d3;
    }
    //通过Setter方法注入
    public void setD3(Di02 d3) {
        this.d3 = d3;
    }

    private int id;
    private String name;
    private Integer age;
    private Integer sex;
    private String address;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }

    public Student(Di01 di01, Di02 di02){
        this.d1 = di01;
        this.d2 = di02;
    }
}