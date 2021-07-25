package dfs;

import javax.swing.*;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class RegistrationScreen extends JFrame implements ActionListener {

    JLabel userLabel=new JLabel("USERNAME");
    JTextField userTextField=new JTextField();
    JLabel passwordLabel=new JLabel("PASSWORD");
    JPasswordField passwordField=new JPasswordField();
    JLabel securityQuestionText=new JLabel("SECURITY QUESTION :");
    JLabel securityQuestionLabel=new JLabel("What is your favorite Sport?");
    JTextField securityQuestionField=new JTextField();
    JButton registerButton=new JButton("REGISTER");
    JButton loginButton=new JButton("Already User? Click here for Login");
    Container container=getContentPane();


    RegistrationScreen()
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
        securityQuestionText.setBounds(50,240,150,30);
        securityQuestionLabel.setBounds(50,260,300,30);
        securityQuestionField.setBounds(150,290,150,30);

        loginButton.setBounds(200,400,250,30);
        registerButton.setBounds(50,400,100,30);
    }

    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(securityQuestionLabel);
        container.add(securityQuestionField);
        container.add(securityQuestionText);
        container.add(loginButton);
        container.add(registerButton);
    }

    public void addActionEvent()
    {
        //adding Action listener to components
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Account account = new Account(userTextField.getText(),
                new String(passwordField.getPassword()),
                securityQuestionField.getText());

        if(e.getSource() == registerButton){
            if(isValidRegistrationDetails(account)){
                saveRegistrationDetails(account);
                JOptionPane.showMessageDialog(this, "Registration Successful");
                LoginScreen();
            }
            else{
                JOptionPane.showMessageDialog(this, "Please verify username, password" +
                        "and security question");
            }

        }
        else if(e.getSource() == loginButton){
            LoginScreen();
        }
    }

    public boolean isValidRegistrationDetails(Account userDetail){

        List<Account> accounts = Util.getAccounts();
        for(Account account: accounts){
            if(userDetail.getUserName().equals(account.getUserName())){
                return false;
            }
        }
        return true;
    }





    // append to file
    public void saveRegistrationDetails(Account account) {
        try {
            FileWriter fw = new FileWriter(Constants.FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(account.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LoginScreen() {
        LoginScreen frame=new LoginScreen();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        this.dispose();
    }
}