package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class player {
    private player self;

    public login Login;
    public regist Regist;
    public mainMenu MainMenu;
    public client Client;

    public player() {
        Login = new login();
        Regist = new regist();
        self = this;
        init();
    }

    public void init() {
        ActionListener loginButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = Login.user.getText();
                String pwd = String.valueOf(Login.password.getPassword());
                try {
                    if(ID == null)
                        JOptionPane.showMessageDialog(null, "用户名不得为空！");
                    else if(pwd.length() == 0)
                        JOptionPane.showMessageDialog(null, "密码不得为空！");
                    else if(!database.checkUnique(ID))
                        JOptionPane.showMessageDialog(null, "用户名不存在！");
                    else if(!database.login(ID, pwd))
                        JOptionPane.showMessageDialog(null, "密码错误！");
                    else {
                        Login.loginFrame.setVisible(false);
                        Client = new client(ID, self);
                        JOptionPane.showMessageDialog(null, "登陆成功！");
                        MainMenu = new mainMenu(self);
                        MainMenu.MainMenu.setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        Login.loginButton.addActionListener(loginButton);

        ActionListener registButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.user.setText("");
                Login.password.setText("");
                Login.loginFrame.setVisible(false);
                Regist.registFrame.setVisible(true);
            }
        };
        Login.registButton.addActionListener(registButton);

        ActionListener ensureButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = Regist.user.getText();
                String pwd = String.valueOf(Regist.password.getPassword());
                String pwdEnsure = String.valueOf(Regist.passwordEnsure.getPassword());
                boolean success = false;
                try {
                    if(ID == null)
                        JOptionPane.showMessageDialog(null, "用户名不得为空！");
                    else if(pwd.length() == 0)
                        JOptionPane.showMessageDialog(null, "密码不得为空！");
                    else if(pwdEnsure.length() == 0)
                        JOptionPane.showMessageDialog(null, "密码确认不得为空！");
                    else if(!pwd.equals(pwdEnsure))
                        JOptionPane.showMessageDialog(null, "两次密码不一致！");
                    else if(database.checkUnique(ID))
                        JOptionPane.showMessageDialog(null, "用户名已被占用！");
                    else {
                        database.createAccount(ID, pwd);
                        success = true;
                        JOptionPane.showMessageDialog(null, "注册成功！");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(success) {
                    Regist.user.setText("");
                    Regist.password.setText("");
                    Regist.passwordEnsure.setText("");
                    Login.loginFrame.setVisible(true);
                    Regist.registFrame.setVisible(false);
                }
            }
        };
        Regist.ensureButton.addActionListener(ensureButton);
    }

    public static void main(String[] args) {
        new player();
    }
}
