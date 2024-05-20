import org.w3c.dom.ls.LSOutput;

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
                    manageStudent();
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
                return;
            }
        }

        System.out.println("未查找到对应教师信息");
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

    //管理学生
    private void manageStudent(){
        while (true) {
            Tools.printManageStudentMenu();
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    //添加学生
                    students.add(addStudent());
                    break;
                case 2:
                    //查看学生信息
                    showStudentInfo(students);
                    break;
                case 3:
                    //修改学生信息
                    editStudentInfo();
                    break;
                case 4:
                    //删除学生信息
                    deleteStudent(students);
                    break;
                case 5:
                    //列出已存储学生
                    showPeople(1);
                    break;
                case 6:
                    //返回上一级菜单
                    System.out.println("返回上一级菜单...");
                    return;
            }
        }
    }

    //添加学生信息
    private Students addStudent(){
        Students student = new Students();
        System.out.println("请输入学生姓名");
        student.setName(sc.next());
        System.out.println("请输入学生年龄");
        student.setAge(sc.nextInt());
        System.out.println("请输入学生性别");
        student.setSex(sc.next());

        //生成学生学号
        //判断当前系统是否存在学生
        if(students.isEmpty()){
            //系统中不存在学生信息
            student.setNumber(Tools.generateNumber(1));
        }else{
            //判断生成的学号是否重复
            student.setNumber(Tools.generateNumber(1));
            for (int i = 0; i < students.size(); i++) {
                if(students.get(i).getNumber().equals(student.getNumber()));{
                    //学生学号重复
                    student.setNumber(Tools.generateNumber(1));
                }
            }
        }

        //初始化学生成绩
        double[] score = new double[3];
        for (int i = 0; i < score.length; i++) {
            score[i] = 0.0;
        }
        student.setScores(score);

        //输出日志
        System.out.println("已添加学生：" + student.getName() + "\t" +"学号：" + student.getNumber());

        return student;
    }

    //查看学生信息
    private void showStudentInfo(ArrayList<Students> student){
        //判断系统中是否存在学生信息
        if(student.isEmpty()){
            System.out.println("当前系统中没有任何学生信息");
            return;
        }
        System.out.println("请输入学号");
        inputString = sc.next();
        for (int i = 0; i < student.size(); i++) {
            if(student.get(i).getNumber().equals(inputString)){
                //打印对应学生信息
                Tools.printStudentInfo(student , i);
                return;
            }
        }
        System.out.println("为查找到对应学生信息");

    }

    //修改学生信息
    private void editStudentInfo(){
        //判断系统中是否存在学生信息
        if(students.isEmpty()){
            System.out.println("当前系统中没有任何学生信息");
            return;
        }

        //系统中存在学生信息
        System.out.println("请输入欲修改学生的学号");
        inputString = sc.next();
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getNumber().equals(inputString)){
                //找到对应学生
                while (true) {
                    Tools.printEditStudentInfoMenu();
                    choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            //修改姓名
                            System.out.println("请输入新的姓名");
                            students.get(i).setName(sc.next());
                            System.out.println("操作完成"); //输出日志
                            return;
                        case 2:
                            //修改性别
                            System.out.println("请输入新的性别");
                            students.get(i).setSex(sc.next());
                            System.out.println("操作完成"); //输出日志
                            return;
                        case 3:
                            //修改年龄
                            System.out.println("请输入新的年龄");
                            students.get(i).setAge(sc.nextInt());
                            System.out.println("操作完成"); //输出日志
                            return;
                        case 4:
                            //修改成绩
                            for (int j = 0; j < students.get(i).getScores().length; j++) {
                                System.out.println("请输入第" + (j + 1) + "门课的成绩");
                                students.get(i).getScores()[j] = sc.nextDouble();
                            }
                            System.out.println("操作完成"); //输出日志
                            return;
                        default:
                            System.out.println("输入有误");
                            return;
                    }
                }
            }
        }
    }

    //删除学生信息
    private void deleteStudent(ArrayList<Students> students){
        //判断系统中是否有学生信息
        if(students.isEmpty()){
            //没有学生信息
            System.out.println("当前系统中没有任何学生信息");
        }else{
            //系统中存在学生信息
            System.out.println("请输入欲删除学生信息的学号");
            inputString = sc.next();
            for (int i = 0; i < students.size(); i++) {
                if(students.get(i).getNumber().equals(inputString)){
                    //再次确认是否执行删除
                    System.out.println("已找到学生：" + students.get(i).getName() + "\t" + "学号：" + students.get(i).getNumber());
                    System.out.println("是否执行删除？（0.否，1是）");
                    choice = sc.nextInt();
                    switch (choice){
                        case 0:
                            //不进行删除
                            System.out.println("学生" + students.get(i).getName() + "未删除");  //输出日志
                            return;
                        case 1:
                            //执行删除
                            students.remove(i);
                            System.out.println("学生" +students.get(i).getName() + "删除成功");
                    }
                }else{
                    System.out.println("系统中目前没有该学生信息");
                }
            }
        }

    }
}
