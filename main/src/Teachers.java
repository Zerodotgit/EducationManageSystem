public class Teachers extends People{

    //构造函数
    public Teachers() {
    }

    public Teachers(String name, int age, String sex, String number, String major, double pay) {
        super(name, age, sex);
        this.number = number;
        this.major = major;
        this.pay = pay;
    }

    private String number;
    private String major;
    private double pay;

    //getter与setter
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
