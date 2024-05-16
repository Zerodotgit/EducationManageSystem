import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tools {

    //打印菜单
    public static void printMainMenu(){
        System.out.println("""
                ===欢迎进入教育管理系统===
                1.管理教师
                2.管理学生
                3.退出
                """);
        System.out.println("请输入选择");
    }

    //打印教师管理菜单
    public static void printManageTeacherMenu(){
        System.out.println("""
                ===管理教师===
                1.添加教师信息
                2.查看教师信息
                3.修改教师信息
                4.删除教师信息
                5.列出已存储教师姓名
                6.返回主菜单
                """);
        System.out.println("请输入选择");
    }

    //生成学生学号或教师工号
    public static String generateNumber(int judge){
        Random r = new Random();
        String number;

        //生成教师工号
        if(judge == 0){
            number = "T";
            for (int i = 0; i < 8; i++) {
                number = number + r.nextInt(10);
            }
            return number;
        }else{
            number = "S";
            for (int i = 0; i < 8; i++) {
                number = number + r.nextInt(10);
            }
            return number;
        }
    }

    //打印已存储的教师
    public static void printTeachersList(ArrayList<Teachers> teachers){
        int count = 1;
        System.out.println("当前系统中有以下教师信息");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.print(teachers.get(i).getName() + "\t" + teachers.get(i).getNumber() + "\t");

            //每五个数据换一行
            count++;
            if(count == 5){
                count = 1;
                System.out.println();
            }
        }
        System.out.println();

    }

    //打印已存储的学生
    public static void printStudentsList(ArrayList<Students> students){
        int count = 1;
        System.out.println("当前系统中有以下学生信息");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + "." + students.get(i).getName() + "\t" + students.get(i).getNumber() + "\t");

            //每五个数据换一行
            count++;
            if(count == 5){
                count = 1;
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void printEditTeacherInfoMenu(){
        System.out.println("===教师信息修改===");
        System.out.println("""
                1.修改姓名
                2.修改性别
                3.修改年龄
                4.修改授课科目
                5.修改薪资
                """);

    }

    public static void editTeacherInfoOperator(ArrayList<Teachers> teachers, int choice, int who){
        //必要对象
        Scanner sc = new Scanner(System.in);

        //执行
        switch (choice){
            case 1:
                //修改姓名
                System.out.println("输入新的姓名");
                teachers.get(who).setName(sc.next());
                break;
            case 2:
                //修改性别
                System.out.println("输入新的性别");
                teachers.get(who).setName(sc.next());
                break;
            case 3:
                //修改年龄
                System.out.println("输入新的年龄");
                teachers.get(who).setAge(sc.nextInt());
                break;
            case 4:
                //修改授课科目
                System.out.println("输入新的授课科目");
                teachers.get(who).setMajor(sc.next());
                break;
            case 5:
                //修改薪资
                System.out.println("输入新的薪资");
                teachers.get(who).setPay(sc.nextDouble());
                break;
        }
    }

    //功能：判断系统中受否存在教师信息
    public static boolean isThereAnyTeacherInfo(ArrayList<Teachers> teachers){
        if(teachers.isEmpty()){
            System.out.println("当前系统中无任何教师信息，请先创建");
            return !teachers.isEmpty();
        }else{
            return false;
        }
    }

    //功能：判断系统中是否存在学生信息
    public static boolean isThereAnyStudentInfo(ArrayList<Students> students){
        return !students.isEmpty();
    }

}
