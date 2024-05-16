public class Students extends People{

    private String number;
    private double[] scores;

    //构造函数
    public Students() {
    }

    public Students(String number, double[] scores) {
        this.number = number;
        this.scores = scores;
    }

    //get与setter
    public double[] getScores() {
        return scores;  //这里是返回地址
    }

    //这里提供的是地址
    public void setScores(double[] scores) {
        this.scores = scores;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
