import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton login=new JButton("登陆");
    JButton register=new JButton("注册");
    JLabel havecode=new JLabel();



    String rightcode;
    JTextField code=new JTextField();
    JTextField username=new JTextField();
    JPasswordField password=new JPasswordField();
    LoginJFrame(){
       initJFrame();
       initView();
        this.setVisible(true);
    }

    public void initView() {
        JLabel usernameText=new JLabel("用户名");
        usernameText.setBounds(70,120,48,32);
        this.getContentPane().add(usernameText);

        username.setBounds(120, 120, 200, 30);
        this.getContentPane().add(username);

        JLabel passwordText = new JLabel("密码");
        passwordText.setBounds(70, 170, 48, 32);
        this.getContentPane().add(passwordText);

        password.setBounds(120, 170, 200, 30);
        this.getContentPane().add(password);

        JLabel codeText=new JLabel("验证码");
        codeText.setBounds(70,220,48,32);
        this.getContentPane().add(codeText);

        code.setBounds(120,220,100,30);
        this.getContentPane().add(code);

        login.setBounds(70, 250, 128, 47);
        login.addMouseListener(this);
        login.setBorder(null);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(220, 250, 128, 47);
        register.addMouseListener(this);
        register.setBorder(null);
        this.getContentPane().add(register);

        rightcode=CodeUtil.getCode();
        havecode.setText(rightcode);
        havecode.addMouseListener(this);
        havecode.setBounds(240,220,50,32);
        this.getContentPane().add(havecode);

        JLabel background=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\登陆.jpg"));
        background.setBounds(0,0,404,352);
        this.getContentPane().add(background);
    }

    public void initJFrame(){
        this.setSize(404, 352);//设置宽高
        this.setTitle("课程查询系统 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==login){
            String newUsername= username.getText();
            String newPassword= password.getText();
            String newCode= code.getText();
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
                String sql1 = "select * from User";
                ResultSet resultSet = statement.executeQuery(sql1);
                boolean flag=false;
                while (resultSet.next()) {
                    if(newUsername.equals(resultSet.getString("username"))&&newPassword.equals(resultSet.getString("password"))&&newCode.equalsIgnoreCase(rightcode)){
                        new RunningJFrame();
                        flag=true;
                    }
                }
                if (!flag){
                    JDialog jDialog = new JDialog();
                    //创建一个管理图片的容器对象JLabel
                    JLabel jLabel = new JLabel("用户名、密码或验证码错误");
                    //设置位置和宽高
                    jLabel.setBounds(0,0,70,30);
                    //把图片添加到弹框当中
                    jDialog.getContentPane().add(jLabel);
                    //给弹框设置大小
                    jDialog.setSize(170,100);
                    //让弹框置顶
                    jDialog.setAlwaysOnTop(true);
                    //让弹框居中
                    jDialog.setLocationRelativeTo(null);
                    //弹框不关闭则无法操作下面的界面
                    jDialog.setModal(true);
                    //让弹框显示出来
                    jDialog.setVisible(true);
                }
                statement.close();
                connection.close();
                }catch (Exception exception){
                exception.printStackTrace();
                }
            rightcode=CodeUtil.getCode();
            havecode.setText(rightcode);
        }else if(e.getSource()==register){
                new RegisterJFrame();
        }else if(e.getSource()==havecode){
            rightcode=CodeUtil.getCode();
            havecode.setText(rightcode);
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
}
