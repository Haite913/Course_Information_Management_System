import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterJFrame extends JFrame implements MouseListener {

    JButton register=new JButton("注册");
    JTextField username=new JTextField();
    JPasswordField password=new JPasswordField();



    RegisterJFrame(){
     initJFrame();
     initView();
    }

    public void initView() {

        JLabel usernameText=new JLabel("用户名");
        usernameText.setBounds(60,120,48,32);
        this.getContentPane().add(usernameText);

        username.setBounds(120, 120, 200, 30);
        this.getContentPane().add(username);

        JLabel passwordText = new JLabel("密码");
        passwordText.setBounds(60, 170, 48, 32);
        this.getContentPane().add(passwordText);

        password.setBounds(120, 170, 200, 30);
        this.getContentPane().add(password);

        //6.添加注册按钮
        register.setBounds(130, 220, 128, 47);
        register.addMouseListener(this);
        register.setBackground(null);
        register.setBorder(null);
        this.getContentPane().add(register);

        JLabel background=new JLabel(new ImageIcon("C:\\Users\\86189\\Desktop\\java curriculum design\\curriculum design\\image\\注册.jpg"));
        background.setBounds(0,0,352,317);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(370,330);
        //设置界面的标题
        this.setTitle("课程查询系统 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);//取消内部默认布局
        //让显示显示出来，建议写在最后
        this.setVisible(true);

        getContentPane();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==register){
            String newUsername= username.getText();
            String newPassword= password.getText();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //2.获取链接
                String url = "jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&characterEncoding=utf-8";
                String username = "root";
                String password = "root";
                Connection connection = DriverManager.getConnection(url, username, password);
                //定义sql
                //执行对象
                Statement statement = connection.createStatement();
                String sql1 = "select * from User";
                ResultSet resultSet = statement.executeQuery(sql1);
                boolean flag=true;
                while (resultSet.next()) {
                    if(newUsername.equals(resultSet.getString("username"))){
                        flag=false;
                    }
                }
                if (checkName(newUsername)==false){
                    JDialog jDialog = new JDialog();
                    //创建一个管理图片的容器对象JLabel
                    JLabel jLabel = new JLabel("用户名请输入必须是6-10位字母、数字、下划线且不能以数字开头");
                    //设置位置和宽高
                    jLabel.setBounds(0,0,20,30);
                    //把图片添加到弹框当中
                    jDialog.getContentPane().add(jLabel);
                    //给弹框设置大小
                    jDialog.setSize(250,100);
                    //让弹框置顶
                    jDialog.setAlwaysOnTop(true);
                    //让弹框居中
                    jDialog.setLocationRelativeTo(null);
                    //弹框不关闭则无法操作下面的界面
                    jDialog.setModal(true);
                    //让弹框显示出来
                    jDialog.setVisible(true);
                } else if (checkPwd(newPassword)==false) {
                    JDialog jDialog = new JDialog();
                    //创建一个管理图片的容器对象JLabel
                    JLabel jLabel = new JLabel("密码必须是6-20位的字母、数字、下划线");
                    //设置位置和宽高
                    jLabel.setBounds(0,0,20,30);
                    //把图片添加到弹框当中
                    jDialog.getContentPane().add(jLabel);
                    //给弹框设置大小
                    jDialog.setSize(250,100);
                    //让弹框置顶
                    jDialog.setAlwaysOnTop(true);
                    //让弹框居中
                    jDialog.setLocationRelativeTo(null);
                    //弹框不关闭则无法操作下面的界面
                    jDialog.setModal(true);
                    //让弹框显示出来
                    jDialog.setVisible(true);
                } else if (flag==false){
                    JDialog jDialog = new JDialog();
                    //创建一个管理图片的容器对象JLabel
                    JLabel jLabel = new JLabel("用户名已存在，请重新输入");
                    //设置位置和宽高
                    jLabel.setBounds(0,0,20,30);
                    //把图片添加到弹框当中
                    jDialog.getContentPane().add(jLabel);
                    //给弹框设置大小
                    jDialog.setSize(200,100);
                    //让弹框置顶
                    jDialog.setAlwaysOnTop(true);
                    //让弹框居中
                    jDialog.setLocationRelativeTo(null);
                    //弹框不关闭则无法操作下面的界面
                    jDialog.setModal(true);
                    //让弹框显示出来
                    jDialog.setVisible(true);
                }else{
                    try {
                        String sql2 = "INSERT INTO user(username,password) VALUES(\"" + newUsername + "\",\"" + newPassword + "\");";
                        int count = statement.executeUpdate(sql2);
                        if (count==0){
                            JDialog jDialog = new JDialog();
                            //创建一个管理图片的容器对象JLabel
                            JLabel jLabel = new JLabel("注册失败");
                            //设置位置和宽高
                            jLabel.setBounds(0,0,20,30);
                            //把图片添加到弹框当中
                            jDialog.getContentPane().add(jLabel);
                            //给弹框设置大小
                            jDialog.setSize(100,100);
                            //让弹框置顶
                            jDialog.setAlwaysOnTop(true);
                            //让弹框居中
                            jDialog.setLocationRelativeTo(null);
                            //弹框不关闭则无法操作下面的界面
                            jDialog.setModal(true);
                            //让弹框显示出来
                            jDialog.setVisible(true);
                        }else{
                            JDialog jDialog = new JDialog();
                            //创建一个管理图片的容器对象JLabel
                            JLabel jLabel = new JLabel("注册成功");
                            //设置位置和宽高
                            jLabel.setBounds(0,0,20,30);
                            //把图片添加到弹框当中
                            jDialog.getContentPane().add(jLabel);
                            //给弹框设置大小
                            jDialog.setSize(100,100);
                            //让弹框置顶
                            jDialog.setAlwaysOnTop(true);
                            //让弹框居中
                            jDialog.setLocationRelativeTo(null);
                            //弹框不关闭则无法操作下面的界面
                            jDialog.setModal(true);
                            //让弹框显示出来
                            jDialog.setVisible(true);
                            this.setVisible(false);
                        }
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
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
    public static boolean checkName(String name) {
        String regExp = "^[^0-9][\\w_]{5,9}$";
        if(name.matches(regExp)) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean checkPwd(String pwd) {
        String regExp = "^[\\w_]{6,20}$";
        if(pwd.matches(regExp)) {
            return true;
        }
        return false;
    }
}
