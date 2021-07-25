package dfs;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Util {


    public static Account createAccount(String data){
        Account account = null;
        if(data!=null && data.length()>0){
            String[] params = data.split(" ");
            if(params.length==3)
                account = new Account(params[0], params[1], params[2]);
        }
        return account;
    }


    //read from file
    public static List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        try {
            File myObj = new File(Constants.FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Account account = Util.createAccount(data);
                if(Objects.nonNull(account))
                    accounts.add(account);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return accounts;
    }
}
