public class Run {

    public static void main(String[] args) {
        //必要对象
        TheSystem theSystem = new TheSystem();

        System.out.println("系统启动...");  //输出日志
        theSystem.start();
        System.out.println("系统退出");     //输出日志
    }
}
