import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoginScreen extends JFrame implements ActionListener {

    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton registerButton=new JButton("New User? Click here to Register");
    JButton forgotPasswordButton=new JButton("FORGOT PASSWORD");
    Container container=getContentPane();

    //Creating constructor of LoginFrame() class
    LoginScreen()
    {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager()
    {
        //Setting layout manager of Container to null
        container.setLayout(null);
    }

    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,200,100,30);
        userTextField.setBounds(150,150,150,30);
        passwordField.setBounds(150,200,150,30);
        loginButton.setBounds(50,300,100,30);
        registerButton.setBounds(150,300,250,30);
        forgotPasswordButton.setBounds(50,350,250,30);
    }

    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(registerButton);
        container.add(forgotPasswordButton);
    }

    public void addActionEvent()
    {
        //adding Action listener to components
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        forgotPasswordButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            if (isValidLoginDetails(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                //connect to client

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        else if(e.getSource() == registerButton){
            RegistrationScreen frame=new RegistrationScreen();
            frame.setTitle("Registration Form");
            frame.setVisible(true);
            frame.setBounds(10,10,500,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            this.dispose();
        }
        else if(e.getSource() == forgotPasswordButton){
            ForgotPasswordScreen frame=new ForgotPasswordScreen();
            frame.setTitle("Forgot Password Form");
            frame.setVisible(true);
            frame.setBounds(10,10,500,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            this.dispose();
        }

    }

    private boolean isValidLoginDetails(String username, String  password){
        List<Account> accounts = Util.getAccounts();
        for(Account account: accounts){
            if(username.equals(account.getUserName()) && password.equals(account.getPassword())){
                return true;
            }
        }
        return false;
    }


}


