package accountPassing;


public class AccountData {
    private String accountName;
    private int accountPwd;//stores the passowrd hash

    public AccountData() {
        this("richard.fritsche", "Th!$!$@b@dP@$$w0rd");
    }//end default constructor

    public AccountData(String name, String pwd) {
        this.accountName = name;
        this.accountPwd = pwd.hashCode();
    }//end constructor

    public boolean checkAccount(String name, int pwd) {
        return (accountName.equals(name) && accountPwd == pwd);
    }//end checkAccount method
}//end class