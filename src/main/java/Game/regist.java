package Game;

import javax.swing.*;
import java.awt.*;

public class regist {
    public JFrame registFrame;            //登陆框架
    public JLabel registLabel;            //登陆页面
    public JLabel userLabel;                //用户
    public JLabel passwordLabel;            //密码
    public JLabel passwordEnsureLabel;      //密码确认
    public JButton ensureButton;            //确认按钮
    public JTextField user;                 //用户
    public JPasswordField password;         //密码
    public JPasswordField passwordEnsure;   //密码

    public regist() {
        Font font = new Font("宋体", Font.PLAIN, 20);

        registFrame = new JFrame("注册");
        registFrame.setSize(450, 360);

        userLabel = new JLabel("用户名");
        userLabel.setBounds(30, 60, 80, 50);
        userLabel.setFont(font);

        passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(30, 130, 80, 50);
        passwordLabel.setFont(font);

        passwordEnsureLabel = new JLabel("密码确认");
        passwordEnsureLabel.setBounds(30, 200, 80, 50);
        passwordEnsureLabel.setFont(font);

        ensureButton = new JButton("注册");
        ensureButton.setBounds(175, 270, 100, 50);
        ensureButton.setFont(font);

        user = new JTextField();
        user.setBounds(150, 60, 250, 50);
        user.setFont(font);

        password = new JPasswordField();
        password.setBounds(150, 130, 250, 50);
        password.setFont(font);

        passwordEnsure = new JPasswordField();
        passwordEnsure.setBounds(150, 200, 250, 50);
        passwordEnsure.setFont(font);

        registLabel = new JLabel();
        registLabel.add(user);
        registLabel.add(password);
        registLabel.add(passwordEnsure);
        registLabel.add(userLabel);
        registLabel.add(passwordLabel);
        registLabel.add(passwordEnsureLabel);
        registLabel.add(ensureButton);
        registFrame.add(registLabel);
        registFrame.setVisible(false);
        registFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registFrame.setLocationRelativeTo(null);
    }

    public String getUserName() {
        return user.getText();
    }

    public boolean EnsurePassword() {
        return String.valueOf(password.getPassword()).equals(String.valueOf(passwordEnsure.getPassword()));
    }

    public String getUserPassword() {
        return String.valueOf(passwordEnsure.getPassword());
    }
}
