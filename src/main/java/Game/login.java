package Game;

import javax.swing.*;
import java.awt.*;

public class login {
    public JLabel loginLabel;           //登陆页面
    public JFrame loginFrame;           //登陆框架
    public JLabel userLabel;            //用户
    public JLabel passwordLabel;        //密码
    public JButton loginButton;         //登陆按钮
    public JButton registButton;        //注册按钮
    public JTextField user;             //用户
    public JPasswordField password;     //密码

    public login() {
        Font font = new Font("宋体", Font.PLAIN, 20);

        loginFrame = new JFrame("登陆");
        loginFrame.setSize(450, 360);

        userLabel = new JLabel("用户名");
        userLabel.setBounds(30, 60, 60, 50);
        userLabel.setFont(font);

        passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(30, 130, 60, 50);
        passwordLabel.setFont(font);

        loginButton = new JButton("登陆");
        loginButton.setBounds(90, 250, 100, 50);
        loginButton.setFont(font);

        registButton = new JButton("注册");
        registButton.setBounds(250, 250, 100, 50);
        registButton.setFont(font);

        user = new JTextField();
        user.setBounds(150, 60, 250, 50);
        user.setFont(font);

        password = new JPasswordField();
        password.setBounds(150, 130, 250, 50);
        password.setFont(font);

        loginLabel = new JLabel();
        loginLabel.add(user);
        loginLabel.add(password);
        loginLabel.add(userLabel);
        loginLabel.add(passwordLabel);
        loginLabel.add(loginButton);
        loginLabel.add(registButton);

        loginFrame.add(loginLabel);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
    }
}
