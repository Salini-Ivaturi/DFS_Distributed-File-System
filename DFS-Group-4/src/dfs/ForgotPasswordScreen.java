package dfs;

import javax.swing.*;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class ForgotPasswordScreen extends JFrame implements ActionListener {

    JLabel userNameLabel=new JLabel("Enter Username");
    JTextField userNameField=new JTextField();

    JLabel securityQuestionText=new JLabel("Please answer below Security Question :");
    JLabel securityQuestionLabel=new JLabel("What is your favorite Sport?");

    JTextField securityQuestionField=new JTextField();

    JLabel passwordLabel=new JLabel("Enter New Password");
    JPasswordField passwordField=new JPasswordField();

    JLabel confirmPasswordLabel=new JLabel("Confirm Password");
    JPasswordField confirmPasswordField = new JPasswordField();

    JButton submitButton=new JButton("SUBMIT");
    JButton loginButton=new JButton("Click here to Login");

    Container container=getContentPane();

    //Creating constructor of LoginFrame() class
    ForgotPasswordScreen()
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
        userNameLabel.setBounds(50,120,150,30);
        userNameField.setBounds(250,120,150,30);
        securityQuestionText.setBounds(50,150,350,30);
        securityQuestionLabel.setBounds(50,180,250,30);
        securityQuestionField.setBounds(250,180,150,30);
        passwordLabel.setBounds(50,220,150,30);
        passwordField.setBounds(250,220,150,30);
        confirmPasswordLabel.setBounds(50,260,150,30);
        confirmPasswordField.setBounds(250,260,150,30);

        submitButton.setBounds(50,310,100,30);
        loginButton.setBounds(200,310,300,30);
    }

    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(userNameField);
        container.add(userNameLabel);
        container.add(securityQuestionText);
        container.add(securityQuestionLabel);
        container.add(securityQuestionField);
        container.add(passwordField);
        container.add(passwordLabel);
        container.add(confirmPasswordLabel);
        container.add(confirmPasswordField);
        container.add(submitButton);
        container.add(loginButton);
    }

    public void addActionEvent()
    {
        //adding Action listener to components
        submitButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = userNameField.getText();
        String password = new String(passwordField.getPassword());;
        String confirmPassword = new String(confirmPasswordField.getPassword());;
        String securityAnswer = securityQuestionField.getText();
        if (e.getSource() == submitButton){
            if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(this, "Password and Confirm password should be same");
            }
            else if(isUserNameAndSecurityAnswerInValid(username, securityAnswer)){
                JOptionPane.showMessageDialog(this, "UserName and Security Answer needs to be Valid");
            }
            else{
                if(updatePassword(username, password, securityAnswer))
                    JOptionPane.showMessageDialog(this, "password updated successfully");
                new LoginScreen();
                this.dispose();
            }
        }
        else if(e.getSource() == loginButton){
            LoginScreen frame=new LoginScreen();
            frame.setTitle("Login Form");
            frame.setVisible(true);
            frame.setBounds(10,10,500,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            this.dispose();
        }
    }

    private boolean isUserNameAndSecurityAnswerInValid(String username, String securityAnswer){
        List<Account> accountList = Util.getAccounts();
        if(accountList==null || accountList.size()==0)
            return true;

        for(Account account: accountList){
            if(account.getUserName().equals(username) && account.getSecurityQuestion().equals(securityAnswer)){
                return false;
            }
        }
        return true;
    }

    private boolean updatePassword(String username, String password, String securityAnswer){
        List<Account> accountList = Util.getAccounts();
        if(accountList==null || accountList.size()==0)
            return false;
        for(Account account: accountList){
            if(account.getUserName().equals(username) && account.getSecurityQuestion().equals(securityAnswer)){
                account.setPassword(password);
            }
        }
        updateFile(accountList);
        return true;
    }

    private void updateFile(List<Account> accounts){
        File currentFile = new File(Constants.FILENAME);
        currentFile.delete();


        try {
            FileWriter fw = new FileWriter(Constants.FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Account account: accounts){
                bw.write(account.toString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
