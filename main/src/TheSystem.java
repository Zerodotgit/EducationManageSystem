import java.util.ArrayList;
import java.util.Scanner;

public class TheSystem {
    //必要对象
    Scanner sc = new Scanner(System.in);

    //ArrayList集合
    ArrayList<Students> students = new ArrayList<>();
    ArrayList<Teachers> teachers = new ArrayList<>();

    //全局变量
    private int choice;
    private String inputString;

    //构造函数
    public TheSystem() {
    }

    //进入系统
    public void start(){
        //进入主菜单

        while (true) {
            Tools.printMainMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //管理教师
                    manageTeacher();
                    break;
                case 2:
                    //管理学生
                    break;
                default:
                    //退出
                    return;
            }
        }
    }

    //管理教师
    private void manageTeacher() {
        while (true) {
            Tools.printManageTeacherMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //添加教师信息
                    teachers.add(addTeacher());
                    System.out.println("操作成功");
                    break;
                case 2:
                    //查看教师信息
                    showTeacherInfo();
                    break;
                case 3:
                    //修改教师信息
                    editTeacherInfo();
                    break;
                case 4:
                    //删除教师信息
                    deleteTeacherInfo();
                    break;
                case 5:
                    //列出已存储的教师
                    showPeople(0);
                    break;
                case 6:
                    //返回主菜单
                    System.out.println("返回主菜单...");
                    return;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }

        }
    }

    //添加教师
    private Teachers addTeacher(){

        //功能实现（待添加异常捕获）
        Teachers teacher = new Teachers();
        System.out.println("请输入教师姓名");
        teacher.setName(sc.next());
        System.out.println("请输入教师年龄");
        teacher.setAge(sc.nextInt());
        System.out.println("请输入教师性别");
        teacher.setSex(sc.next());
        System.out.println("请输入教师所授课程");
        teacher.setMajor(sc.next());
        System.out.println("请输入教师薪资");
        teacher.setPay(sc.nextDouble());
        //生成教师工号
        //判断当前系统是否有教师信息
        if (!teachers.isEmpty()) {
            teacher.setNumber(Tools.generateNumber(0));
            for (int i = 0; i < teachers.size(); i++) {
                if(teachers.get(i).getNumber().equals(teacher.getNumber())){
                    teacher.setNumber(Tools.generateNumber(0));
                }
            }
        }else{
            teacher.setNumber(Tools.generateNumber(0));
        }

        //输出日志
        System.out.println("已添加教师：" + teacher.getName() + "\t工号：" + teacher.getNumber());

        return teacher;
    }

    //查看教师信息
    private void showTeacherInfo(){
        //判断teachers集合是否为空
        if(teachers.isEmpty()){
            System.out.println("当前系统中不存在任教师信息");
            return;
        }
        System.out.println("请输入教师工号");
        inputString = sc.next();

        //查找对应工号的教师信息
        for (int i = 0; i < teachers.size(); i++) {
            if(inputString.equals(teachers.get(i).getNumber())){
                //找到对应教师信息，进行打印
                System.out.println("姓名\t" + teachers.get(i).getName());
                System.out.println("性别\t" + teachers.get(i).getSex());
                System.out.println("年龄\t" + teachers.get(i).getAge());
                System.out.println("工号\t" + teachers.get(i).getNumber());
                System.out.println("授教课程\t" + teachers.get(i).getMajor());
                System.out.println("薪资\t" + teachers.get(i).getPay());

            }
        }
    }

    //打印已存储的学生或者教师列表
    private void showPeople(int judge){
        if(judge == 0){
            //判断系统中是否至少存在一名教师
            if(teachers.isEmpty()){
                System.out.println("当前系统中没有教师信息");
                return;
            }
            //打印已存储的教师
            Tools.printTeachersList(teachers);

        }else{
            //判断当前系统中是否存在一名学生
            if(students.isEmpty()){
                System.out.println("当前系统中没有学生信息");
                return;
            }
            //打印已存储的学生
            Tools.printStudentsList(students);
        }
    }

    //修改教师信息操作
    private void editTeacherInfo(){
        //判读系统中是否存在教师信息
        if(Tools.isThereAnyTeacherInfo(teachers))
            return;

        //系统中存在教师信息执行操作
        System.out.println("请输入欲更改教师的工号");
        inputString = sc.next();
        for (int i = 0; i < teachers.size(); i++) {
            //判断目标教师是否存在
            if(inputString.equals(teachers.get(i).getNumber())){
                System.out.println("目前选中：" + teachers.get(i).getName() + teachers.get(i).getNumber());
                Tools.printEditTeacherInfoMenu();
                while (true) {
                    choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            //修改姓名
                            Tools.editTeacherInfoOperator(teachers, choice, i);
                            return;
                        case 2:
                            //修改性别
                            Tools.editTeacherInfoOperator(teachers, choice, i);
                            return;
                        case 3:
                            //修改年龄
                            Tools.editTeacherInfoOperator(teachers, choice, i);
                            return;
                        case 4:
                            //修改授课科目
                            Tools.editTeacherInfoOperator(teachers, choice, i);
                            return;
                        case 5:
                            Tools.editTeacherInfoOperator(teachers, choice, i);
                            //修改薪资
                            return;
                        case 6:
                            //退出至上一级菜单
                            return;
                        default:
                            System.out.println("输入有误");
                            return;
                    }
                }
            }else{
                System.out.println("所选教师不存在！");
                return;
            }
        }
    }

    //删除教师信息操作
    private void deleteTeacherInfo(){
        //判断系统中是否存在教师信息
        if(Tools.isThereAnyTeacherInfo(teachers))
            return;

        //存在教师信息并执行操作
        System.out.println("请输入想要删除的教师工号");
        inputString = sc.next();
        for (int i = 0; i < teachers.size(); i++) {
            //判断是否存在对应教师
            if(teachers.get(i).getNumber().equals(inputString)){
                //找到对应教师，执行删除
                teachers.remove(i);
            }else{
                //没有找到对应教师
                System.out.println("输入的教师不存在!");
                return;
            }
        }
    }
}
