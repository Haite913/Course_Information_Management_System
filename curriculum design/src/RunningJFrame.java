import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class RunningJFrame extends JFrame implements MouseListener {
    JDialog jDialog4=new JDialog();
    JDialog jDialog7=new JDialog();

    JLabel background=new JLabel();
    JDialog jDialog5 = new JDialog();
    String[] columnName = new String[]{"记录号", "课程号", "课程名称","学分","教室","考试时间","成绩"};
    Set<Class_tb> class_tbs=new HashSet<>();
    // 表格具体数据
    String[][] columnDate = new String[][]{};
    JTable table = new JTable(columnDate, columnName);
    JTextField 原记录号=new JTextField();
    JTextField 记录号=new JTextField();
    JTextField 课程号=new JTextField();
    JTextField 课程名称=new JTextField();
    JTextField 学分=new JTextField();
    JTextField 教室=new JTextField();
    JTextField 考试时间=new JTextField();
    JTextField 成绩=new JTextField();
    Button 查询=new Button("CHECK");
    Button 添加=new Button("ADD");
    Button 修改=new Button("MODIFY");
    Button 删除=new Button("DELETE");

    Button 确定=new Button("OK");

    Button 确认删除=new Button("DELETE");

    Button 确认修改=new Button("MODIFY");
    RunningJFrame(){
        initJFrame();
        initView();
        init();
        setComponent ();
        addComponent();
        addListener();
        this.setVisible(true);
    }
    private void init () {
        setSize(603, 680);
        // 设置窗口位置居中
        setLocationRelativeTo(null);
        // 设置布局
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void setComponent () {
        // 设置第一列宽度为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        // 设置第二列宽度为120
        table.getColumnModel().getColumn(1).setPreferredWidth(120);

    }
    private void addComponent() {
        // 使用srcllpane会自动显示列名
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    private void addListener () {

    }
    public void initJFrame(){
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("课程查询系统 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
    }
    public void initView(){
        查询.setBounds(80, 400, 128, 47);
        查询.addMouseListener(this);
        this.getContentPane().add(查询);

        添加.setBounds(380, 400, 128, 47);
        添加.addMouseListener(this);
        this.getContentPane().add(添加);

        修改.setBounds(80, 500, 128, 47);
        修改.addMouseListener(this);
        this.getContentPane().add(修改);

        删除.setBounds(380, 500, 128, 47);
        删除.addMouseListener(this);
        this.getContentPane().add(删除);

        background=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\登陆背景.jpg"));
        background.setBounds(0,0,603,680);
        this.getContentPane().add(background);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getSource()==查询){
           try {
               Class.forName("com.mysql.jdbc.Driver");
               //2.获取链接
               String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
               String username = "root";
               String password = "root";
               Connection connection = DriverManager.getConnection(url, username, password);
               //定义sql
               //执行对象
               Statement statement = connection.createStatement();
               String sql1 = "select * from Class";
               ResultSet resultSet = statement.executeQuery(sql1);
               while (resultSet.next()) {
                   String 记录号 = resultSet.getString(1);
                   String 课程号 = resultSet.getString(2);
                   String 课程名称 = resultSet.getString(3);
                   float 学分 = resultSet.getFloat(4);
                   String 教室 = resultSet.getString(5);
                   String 考试时间 = resultSet.getString(6);
                   int 成绩 = resultSet.getInt(7);
                   Class_tb class_tb = new Class_tb(记录号, 课程号, 课程名称, 学分, 教室, 考试时间, 成绩);
                   class_tbs.add(class_tb);
               }
               statement.close();
               connection.close();
           } catch (Exception exception) {
               exception.printStackTrace();
           }
           columnDate = new String[class_tbs.size()][7];
           Iterator iterator=class_tbs.iterator();
           int i=0;
           while (iterator.hasNext()){
              Class_tb class_tb= (Class_tb) iterator.next();
              System.out.println(class_tb);
              columnDate[i][0]=class_tb.get记录号();
              columnDate[i][1]=class_tb.get课程号();
              columnDate[i][2]=class_tb.get课程名称();
              columnDate[i][3]= String.valueOf(class_tb.get学分());
              columnDate[i][4]=class_tb.get教室();
              columnDate[i][5]=class_tb.get考试时间();
              columnDate[i][6]= String.valueOf(class_tb.get成绩());
              i++;
           }
           JTable table = new JTable(columnDate, columnName);
           table.getColumnModel().getColumn(0).setPreferredWidth(40);
           // 设置第二列宽度为120
           table.getColumnModel().getColumn(1).setPreferredWidth(120);
           JScrollPane scrollPane = new JScrollPane(table);
           add(scrollPane, BorderLayout.CENTER);
           JLabel jLabel=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\新登陆背景.jpg"));
           jLabel.setBounds(400,400,603,200);
           setVisible(true);
       } else if (e.getSource()==添加) {
           //创建一个管理图片的容器对象JLabel
           JLabel RecordText=new JLabel("记录号");
           RecordText.setBounds(60,30,48,32);

           记录号.setBounds(120, 30, 200, 30);

           JLabel Class_1= new JLabel("课程号");
           Class_1.setBounds(60, 80, 48, 32);

           课程号.setBounds(120, 80, 200, 30);

           JLabel Class_Name=new JLabel("课程名称");
           Class_Name.setBounds(60,130,48,32);


           课程名称.setBounds(120, 130, 200, 30);

           JLabel fen= new JLabel("学分");
           fen.setBounds(60, 180, 48, 32);

           学分.setBounds(120, 180, 200, 30);

           JLabel Class_number= new JLabel("教室");
           Class_number.setBounds(60, 230, 48, 32);


           教室.setBounds(120, 230, 200, 30);

           JLabel Class_room= new JLabel("考试时间");
           Class_room.setBounds(60, 280, 48, 32);


           考试时间.setBounds(120, 280, 200, 30);

           JLabel test= new JLabel("成绩");
           test.setBounds(60, 330, 48, 32);


           成绩.setBounds(120, 330, 200, 30);


           //6.添加注册按钮
           确定.setBounds(130, 380, 128, 47);
           确定.addMouseListener(this);

           jDialog5=new JDialog();
           //把图片添加到弹框当中
           jDialog5.getContentPane().add(确定);

           jDialog5.getContentPane().add(记录号);
           jDialog5.getContentPane().add(课程号);
           jDialog5.getContentPane().add(考试时间);
           jDialog5.getContentPane().add(课程名称);
           jDialog5.getContentPane().add(教室);
           jDialog5.getContentPane().add(成绩);
           jDialog5.getContentPane().add(学分);

           jDialog5.getContentPane().add(Class_Name);
           jDialog5.getContentPane().add(Class_room);
           jDialog5.getContentPane().add(Class_number);
           jDialog5.getContentPane().add(fen);
           jDialog5.getContentPane().add(RecordText);
           jDialog5.getContentPane().add(Class_1);
           jDialog5.getContentPane().add(test);
           jDialog5.getContentPane().add(new JLabel());


           //给弹框设置大小
           jDialog5.setSize(400,500);
           //让弹框置顶
           jDialog5.setAlwaysOnTop(true);
           //让弹框居中
           jDialog5.setLocationRelativeTo(null);
           //弹框不关闭则无法操作下面的界面
           jDialog5.setModal(true);
           //让弹框显示出来
           JLabel jLabel=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\登陆背景.jpg"));
           jLabel.setBounds(0,0,603,680);
           jDialog5.getContentPane().add(jLabel);

           jDialog5.setVisible(true);

       }else if (e.getSource()==确定){
           String number1=记录号.getText();
           String number2=课程号.getText();
           String number3=课程名称.getText();
           float number4;
           if (学分.getText().length()==0){
               number4=0;
           }else{
               number4=Float.parseFloat(学分.getText());
           }
           String number5=教室.getText();
           String number6=考试时间.getText();
           int number7;
           if (学分.getText().length()==0){
               number7=0;
           }else{
               number7=Integer.parseInt(成绩.getText());
           }
           if(number1.length()==0||number2.length()==0||number3.length()==0||number5.length()==0||number6.length()==0||number7==0||number4==0){
               JDialog jDialog6 = new JDialog();
               //创建一个管理图片的容器对象JLabel
               JLabel jLabel1 = new JLabel("输入框不能为空");
               //设置位置和宽高
               jLabel1.setBounds(0,0,70,30);
               //把图片添加到弹框当中
               jDialog6.getContentPane().add(jLabel1);
               //给弹框设置大小
               jDialog6.setSize(170,100);
               //让弹框置顶
               jDialog6.setAlwaysOnTop(true);
               //让弹框居中
               jDialog6.setLocationRelativeTo(null);
               //弹框不关闭则无法操作下面的界面
               jDialog6.setModal(true);
               //让弹框显示出来
               jDialog6.setVisible(true);
           } else if (CheckClassName(number3)==false||CheckClassNum(number2)==false||CheckRecord(number1)==false) {
               JDialog jDialog6 = new JDialog();
               //创建一个管理图片的容器对象JLabel
               JLabel jLabel1 = new JLabel("记录号、课程号或课程名称有重复请重新输入");
               //设置位置和宽高
               jLabel1.setBounds(0,0,70,30);
               //把图片添加到弹框当中
               jDialog6.getContentPane().add(jLabel1);
               //给弹框设置大小
               jDialog6.setSize(300,100);
               //让弹框置顶
               jDialog6.setAlwaysOnTop(true);
               //让弹框居中
               jDialog6.setLocationRelativeTo(null);
               //弹框不关闭则无法操作下面的界面
               jDialog6.setModal(true);
               //让弹框显示出来
               jDialog6.setVisible(true);
           } else{
               try {
                   Class.forName("com.mysql.jdbc.Driver");
                   //2.获取链接
                   String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
                   String username = "root";
                   String password = "1234";
                   Connection connection = DriverManager.getConnection(url, username, password);
                   //定义sql
                   //执行对象
                   Statement statement = connection.createStatement();
                   String sql1 = "INSERT INTO Class(记录号,课程号,课程名称,学分,教室,考试时间,成绩) VALUES(\"" + number1 + "\",\"" + number2 + "\",\"" + number3 + "\"," + number4 + ",\"" + number5 + "\",\"" + number6 + "\"," + number7 + ");\n";
                   int count = statement.executeUpdate(sql1);
                   if (count != 0) {
                       Statement statement1 = connection.createStatement();
                       String sql2 = "select * from Class";
                       ResultSet resultSet1 = statement1.executeQuery(sql2);
                       while (resultSet1.next()) {
                           String 记录号 = resultSet1.getString(1);
                           String 课程号 = resultSet1.getString(2);
                           String 课程名称 = resultSet1.getString(3);
                           float 学分 = resultSet1.getFloat(4);
                           String 教室 = resultSet1.getString(5);
                           String 考试时间 = resultSet1.getString(6);
                           int 成绩 = resultSet1.getInt(7);
                           Class_tb class_tb = new Class_tb(记录号, 课程号, 课程名称, 学分, 教室, 考试时间, 成绩);
                           class_tbs.add(class_tb);
                       }
                   }
                   statement.close();
                   connection.close();
               } catch (Exception exception) {
                   exception.printStackTrace();
               }
               columnDate = new String[class_tbs.size()][7];
               Iterator iterator = class_tbs.iterator();
               int i = 0;
               while (iterator.hasNext()) {
                   Class_tb class_tb = (Class_tb) iterator.next();
                   //System.out.println(class_tb);
                   columnDate[i][0] = class_tb.get记录号();
                   columnDate[i][1] = class_tb.get课程号();
                   columnDate[i][2] = class_tb.get课程名称();
                   columnDate[i][3] = String.valueOf(class_tb.get学分());
                   columnDate[i][4] = class_tb.get教室();
                   columnDate[i][5] = class_tb.get考试时间();
                   columnDate[i][6] = String.valueOf(class_tb.get成绩());
                   i++;
               }
               JTable table = new JTable(columnDate, columnName);
               table.getColumnModel().getColumn(0).setPreferredWidth(40);
               // 设置第二列宽度为120
               table.getColumnModel().getColumn(1).setPreferredWidth(120);
               JScrollPane scrollPane = new JScrollPane(table);
               add(scrollPane, BorderLayout.CENTER);
               setVisible(true);
               jDialog5.setVisible(false);
           }
       }else if (e.getSource()==删除){
           JLabel RecordText=new JLabel("记录号");
           RecordText.setBounds(60,30,48,32);

           记录号.setBounds(120, 30, 200, 30);
           确认删除.setBounds(130, 80, 128, 47);
           确认删除.addMouseListener(this);

           jDialog4=new JDialog();

           //把图片添加到弹框当中
           jDialog4.getContentPane().add(确认删除);
           jDialog4.getContentPane().add(记录号);
           jDialog4.getContentPane().add(RecordText);
           jDialog4.getContentPane().add(new JLabel());

           //给弹框设置大小
           jDialog4.setSize(400,200);
           //让弹框置顶
           jDialog4.setAlwaysOnTop(true);
           //让弹框居中
           jDialog4.setLocationRelativeTo(null);
           //弹框不关闭则无法操作下面的界面
           jDialog4.setModal(true);
           JLabel jLabel=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\新登陆背景.jpg"));
           jLabel.setBounds(0,0,603,200);
           jDialog4.getContentPane().add(jLabel);
           //让弹框显示出来
           jDialog4.setVisible(true);
       }else if (e.getSource()==确认删除){
           String newRecord=记录号.getText();
           if (newRecord.length()==0){
               JDialog jDialog6 = new JDialog();
               //创建一个管理图片的容器对象JLabel
               JLabel jLabel1 = new JLabel("输入框不能为空");
               //设置位置和宽高
               jLabel1.setBounds(0,0,70,30);
               //把图片添加到弹框当中
               jDialog6.getContentPane().add(jLabel1);
               //给弹框设置大小
               jDialog6.setSize(170,100);
               //让弹框置顶
               jDialog6.setAlwaysOnTop(true);
               //让弹框居中
               jDialog6.setLocationRelativeTo(null);
               //弹框不关闭则无法操作下面的界面
               jDialog6.setModal(true);
               //让弹框显示出来
               jDialog6.setVisible(true);
           }else if (CheckRecord(newRecord)==true) {
               JDialog jDialog6 = new JDialog();
               //创建一个管理图片的容器对象JLabel
               JLabel jLabel1 = new JLabel("记录号不存在");
               //设置位置和宽高
               jLabel1.setBounds(0,0,70,30);
               //把图片添加到弹框当中
               jDialog6.getContentPane().add(jLabel1);
               //给弹框设置大小
               jDialog6.setSize(300,100);
               //让弹框置顶
               jDialog6.setAlwaysOnTop(true);
               //让弹框居中
               jDialog6.setLocationRelativeTo(null);
               //弹框不关闭则无法操作下面的界面
               jDialog6.setModal(true);
               //让弹框显示出来
               jDialog6.setVisible(true);
           } else {
               try {
                   Class.forName("com.mysql.jdbc.Driver");
                   //2.获取链接
                   String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
                   String username = "root";
                   String password = "1234";
                   Connection connection = DriverManager.getConnection(url, username, password);
                   //定义sql
                   //执行对象
                   Statement statement = connection.createStatement();
                   String sql1 = "DELETE FROM Class WHERE 记录号=\"" + newRecord + "\"";
                   int count = statement.executeUpdate(sql1);
                   if (count != 0) {
                       Statement statement1 = connection.createStatement();
                       String sql2 = "select * from Class";
                       ResultSet resultSet1 = statement1.executeQuery(sql2);
                       class_tbs = new HashSet<>();
                       while (resultSet1.next()) {
                           String 记录号 = resultSet1.getString(1);
                           String 课程号 = resultSet1.getString(2);
                           String 课程名称 = resultSet1.getString(3);
                           float 学分 = resultSet1.getFloat(4);
                           String 教室 = resultSet1.getString(5);
                           String 考试时间 = resultSet1.getString(6);
                           int 成绩 = resultSet1.getInt(7);
                           Class_tb class_tb = new Class_tb(记录号, 课程号, 课程名称, 学分, 教室, 考试时间, 成绩);
                           class_tbs.add(class_tb);
                       }
                   }
                   statement.close();
                   connection.close();
               } catch (Exception exception) {
                   exception.printStackTrace();
               }
               columnDate = new String[class_tbs.size()][7];
               Iterator iterator = class_tbs.iterator();
               int i = 0;
               while (iterator.hasNext()) {
                   Class_tb class_tb = (Class_tb) iterator.next();
                   //System.out.println(class_tb);
                   columnDate[i][0] = class_tb.get记录号();
                   columnDate[i][1] = class_tb.get课程号();
                   columnDate[i][2] = class_tb.get课程名称();
                   columnDate[i][3] = String.valueOf(class_tb.get学分());
                   columnDate[i][4] = class_tb.get教室();
                   columnDate[i][5] = class_tb.get考试时间();
                   columnDate[i][6] = String.valueOf(class_tb.get成绩());
                   i++;
               }
               JTable table = new JTable(columnDate, columnName){};
               table.getColumnModel().getColumn(0).setPreferredWidth(40);
               // 设置第二列宽度为120
               table.getColumnModel().getColumn(1).setPreferredWidth(120);
               JScrollPane scrollPane = new JScrollPane(table);
               add(scrollPane, BorderLayout.CENTER);

               setVisible(true);
               jDialog4.setVisible(false);
           }
       } else if(e.getSource()==修改){
           //创建一个管理图片的容器对象JLabel
           JLabel rRecordText=new JLabel("原记录号");
           rRecordText.setBounds(60,30,48,32);

           原记录号.setBounds(120,30,200,30);

           JLabel RecordText=new JLabel("记录号");
           RecordText.setBounds(60,80,48,32);

           记录号.setBounds(120, 80, 200, 30);

           JLabel Class_1= new JLabel("课程号");
           Class_1.setBounds(60, 130, 48, 32);

           课程号.setBounds(120, 130, 200, 30);

           JLabel Class_Name=new JLabel("课程名称");
           Class_Name.setBounds(60,180,48,32);


           课程名称.setBounds(120, 180, 200, 30);

           JLabel fen= new JLabel("学分");
           fen.setBounds(60, 230, 48, 32);

           学分.setBounds(120, 230, 200, 30);

           JLabel Class_number= new JLabel("教室");
           Class_number.setBounds(60, 280, 48, 32);


           教室.setBounds(120, 280, 200, 30);

           JLabel Class_room= new JLabel("考试时间");
           Class_room.setBounds(60, 330, 48, 32);


           考试时间.setBounds(120, 330, 200, 30);

           JLabel test= new JLabel("成绩");
           test.setBounds(60, 380, 48, 32);


           成绩.setBounds(120, 380, 200, 30);


           //6.添加注册按钮
           确认修改.setBounds(130, 430, 128, 47);
           确认修改.addMouseListener(this);

           jDialog7=new JDialog();
           //把图片添加到弹框当中
           jDialog7.getContentPane().add(确认修改);

           jDialog7.getContentPane().add(原记录号);
           jDialog7.getContentPane().add(记录号);
           jDialog7.getContentPane().add(课程号);
           jDialog7.getContentPane().add(考试时间);
           jDialog7.getContentPane().add(课程名称);
           jDialog7.getContentPane().add(教室);
           jDialog7.getContentPane().add(成绩);
           jDialog7.getContentPane().add(学分);

           jDialog7.getContentPane().add(Class_Name);
           jDialog7.getContentPane().add(Class_room);
           jDialog7.getContentPane().add(Class_number);
           jDialog7.getContentPane().add(fen);
           jDialog7.getContentPane().add(RecordText);
           jDialog7.getContentPane().add(Class_1);
           jDialog7.getContentPane().add(test);
           jDialog7.getContentPane().add(rRecordText);
           jDialog7.getContentPane().add(new JLabel());

           //给弹框设置大小
           jDialog7.setSize(400,550);
           //让弹框置顶
           jDialog7.setAlwaysOnTop(true);
           //让弹框居中
           jDialog7.setLocationRelativeTo(null);
           //弹框不关闭则无法操作下面的界面
           jDialog7.setModal(true);
           //让弹框显示出来

           JLabel jLabel=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\登陆背景.jpg"));
           jLabel.setBounds(0,0,603,680);
           jDialog7.getContentPane().add(jLabel);
           jDialog7.setVisible(true);
       } else if (e.getSource()==确认修改) {
           String number8=原记录号.getText();
           String number1=记录号.getText();
           String number2=课程号.getText();
           String number3=课程名称.getText();
           float number4;
           if (学分.getText().length()==0){
               number4=0;
           }else{
               number4=Float.parseFloat(学分.getText());
           }
           String number5=教室.getText();
           String number6=考试时间.getText();
           int number7;
           if (学分.getText().length()==0){
               number7=0;
           }else{
               number7=Integer.parseInt(成绩.getText());
           }
           if(number1.length()==0||number2.length()==0||number3.length()==0||number5.length()==0||number6.length()==0||number8.length()==0||number7==0||number4==0) {
               JDialog jDialog6 = new JDialog();
               //创建一个管理图片的容器对象JLabel
               JLabel jLabel1 = new JLabel("输入框不能为空");
               //设置位置和宽高
               jLabel1.setBounds(0, 0, 70, 30);
               //把图片添加到弹框当中
               jDialog6.getContentPane().add(jLabel1);
               //给弹框设置大小
               jDialog6.setSize(170, 100);
               //让弹框置顶
               jDialog6.setAlwaysOnTop(true);
               //让弹框居中
               jDialog6.setLocationRelativeTo(null);
               //弹框不关闭则无法操作下面的界面
               jDialog6.setModal(true);
               //让弹框显示出来
               jDialog6.setVisible(true);
           }else{
               try {
                   Class.forName("com.mysql.jdbc.Driver");
                   //2.获取链接
                   String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
                   String username = "root";
                   String password = "1234";
                   Connection connection = DriverManager.getConnection(url, username, password);
                   //定义sql
                   //执行对象
                   Statement statement = connection.createStatement();
                   String sql1 = "DELETE FROM Class WHERE 记录号=\""+number8+"\";";
                   int count = statement.executeUpdate(sql1);
                   if (count != 0) {
                      // statement = connection.createStatement();
                       String sql2 = "INSERT INTO Class(记录号,课程号,课程名称,学分,教室,考试时间,成绩) VALUES(\"" + number1 + "\",\"" + number2 + "\",\"" + number3 + "\"," + number4 + ",\"" + number5 + "\",\"" + number6 + "\"," + number7 + ");\n";
                       count = statement.executeUpdate(sql2);
                       if (count != 0) {
                        //   statement = connection.createStatement();
                           String sql3 = "select * from Class";
                           ResultSet resultSet1 = statement.executeQuery(sql3);
                           class_tbs=new HashSet<>();
                           while (resultSet1.next()) {
                               String 记录号 = resultSet1.getString(1);
                               String 课程号 = resultSet1.getString(2);
                               String 课程名称 = resultSet1.getString(3);
                               float 学分 = resultSet1.getFloat(4);
                               String 教室 = resultSet1.getString(5);
                               String 考试时间 = resultSet1.getString(6);
                               int 成绩 = resultSet1.getInt(7);
                               Class_tb class_tb = new Class_tb(记录号, 课程号, 课程名称, 学分, 教室, 考试时间, 成绩);
                               class_tbs.add(class_tb);
                           }
                       }
                   }
                   statement.close();
                   connection.close();
               } catch (Exception exception) {
                   exception.printStackTrace();
               }
               columnDate = new String[class_tbs.size()][7];
               Iterator iterator = class_tbs.iterator();
               int i = 0;
               while (iterator.hasNext()) {
                   Class_tb class_tb = (Class_tb) iterator.next();
                   //System.out.println(class_tb);
                   columnDate[i][0] = class_tb.get记录号();
                   columnDate[i][1] = class_tb.get课程号();
                   columnDate[i][2] = class_tb.get课程名称();
                   columnDate[i][3] = String.valueOf(class_tb.get学分());
                   columnDate[i][4] = class_tb.get教室();
                   columnDate[i][5] = class_tb.get考试时间();
                   columnDate[i][6] = String.valueOf(class_tb.get成绩());
                   i++;
               }
               JTable table = new JTable(columnDate, columnName);
               table.getColumnModel().getColumn(0).setPreferredWidth(40);
               // 设置第二列宽度为120
               table.getColumnModel().getColumn(1).setPreferredWidth(120);
               JScrollPane scrollPane = new JScrollPane(table);
               add(scrollPane, BorderLayout.CENTER);
               setVisible(true);
               jDialog7.setVisible(false);
           }

       }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public boolean CheckRecord(String s){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取链接
            String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);
            //定义sql
            //执行对象
            Statement statement = connection.createStatement();
            String sql1 = "select * from Class";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String 记录号 = resultSet.getString(1);
                if (s.equals(记录号)){
                    return false;
                }
            }
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
    public boolean CheckClassNum(String s){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取链接
            String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);
            //定义sql
            //执行对象
            Statement statement = connection.createStatement();
            String sql1 = "select * from Class";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String 课程号 = resultSet.getString(2);
                if (s.equals(课程号)){
                    return false;
                }
            }
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
    public boolean CheckClassName(String s){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取链接
            String url = "jdbc:mysql://127.0.0.1:3306/java?useSSL=false";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);
            //定义sql
            //执行对象
            Statement statement = connection.createStatement();
            String sql1 = "select * from Class";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String 课程名称 = resultSet.getString(3);
                if (s.equals(课程名称)){
                    return false;
                }
            }
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
