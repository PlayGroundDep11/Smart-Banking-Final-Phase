import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SmartBanking{
    private static Scanner scanner= new Scanner(System.in) ;
    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String COLOR_GREEN_BOLD = "\033[33;1m";
    final static String RESET = "\033[0m";
    final static String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    final static String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

    public static ArrayList<String> customerName = new ArrayList<>();       // Names are maintain as arraylist
    private static ArrayList<Double> customeraccountBalance = new ArrayList<>();   // Account balance are maintain as arraylist
    public static ArrayList<String> customerID = new ArrayList<>() ;      // Account Numbers are maintain as arraylist

    public static void main(String[] args) {

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSITS = "Deposit";
        final String WITHDRAW = "WITHDRAW";
        final String TRANSFER = " TRANSFER";
        final String CHECK_BALANCE = "Check Balance" ;
        final String DELETE_ACCOUNT = "Delete account" ;


        String screen = DASHBOARD;


        do {
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD :
                    System.out.println("1] Create new Account");
                    System.out.println("2] Deposits");
                    System.out.println("3] Withdraw");
                    System.out.println("4] Transfer");
                    System.out.println("5] Check balance");
                    System.out.println("6] Delete Account");
                    System.out.println("7] Exit");
                    System.out.print("\n"+"Enter option :");
                    int option = scanner.nextInt() ;
                    scanner.nextLine();
                    switch(option){
                        case 1 : screen = CREATE_ACCOUNT ; break ;
                        case 2 : screen = DEPOSITS ; break ;
                        case 3 : screen = WITHDRAW ; break ;
                        case 4 : screen = TRANSFER ; break ;
                        case 5 : screen = CHECK_BALANCE ; break ;
                        case 6 : screen = DELETE_ACCOUNT ; break ;
                        case 7 : System.exit(0);
                        default : continue ;
                    }
                    break;
                case CREATE_ACCOUNT :
                    System.out.printf("\tNew Customer ID: SDB-%05d\n", (customerID.size() + 1));
                    String name = isNameValid() ;
                    Double initialDeposit ;
                    boolean valid =false ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Initial Deposit :");
                        initialDeposit = scanner.nextDouble() ;
                        scanner.nextLine() ;
                        if(initialDeposit<=5000){
                            System.out.printf(ERROR_MSG,"Iniial ammount should be higher than 5000");
                            valid = true ;
                            continue;
                        }  
                    } while (valid) ;
                    customerID.add(String.format("SDB-%05d", customerID.size() +1));
                    customerName.add(name);
                    customeraccountBalance.add(initialDeposit);
                    System.out.printf("\t%sID SDB:%05d | Name: %s Added successfully!%s\n",COLOR_GREEN_BOLD,customerID.size(),name,RESET);
                    System.out.print("\tDo you want to add another{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSITS :
                    int customerIndex = accNumValidation() ;
                    System.out.printf(SUCCESS_MSG,String.format("Current balance : Rs %,.2f",customeraccountBalance.get(customerIndex)));
                    Double depositAmount ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Deposit Amount:");
                        depositAmount = scanner.nextDouble() ;
                        scanner.nextLine() ;
                        if(depositAmount<=500){
                            System.out.printf(ERROR_MSG,"Deposit ammount should be higher than 500");
                            valid = true ;
                            continue;
                        }  
                    } while (valid) ;
                    customeraccountBalance.set(customerIndex, customeraccountBalance.get(customerIndex)+depositAmount);
                    System.out.printf(SUCCESS_MSG,String.format("Success ! | New Account Balaance : RS %,.2f", customeraccountBalance.get(customerIndex)));
                    System.out.println(customeraccountBalance);
                    System.out.print("\tDo you want to add another Deposit{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break ;
                case WITHDRAW :
                    int customerIndex1 = accNumValidation() ;
                    System.out.printf(SUCCESS_MSG,String.format("Current balance : Rs %,.2f",customeraccountBalance.get(customerIndex1)));
                    Double withdrawAmount ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Withdraw Amount:");
                        withdrawAmount = scanner.nextDouble() ;
                        scanner.nextLine() ;
                        if(withdrawAmount<=500){
                            System.out.printf(ERROR_MSG,"Withdraw ammount should be higher than 500");
                            valid = true ;
                            continue;
                        } 
                        if(withdrawAmount>customeraccountBalance.get(customerIndex1)){
                            System.out.printf(ERROR_MSG,"Withdraw ammount should lower than account balance");
                            valid = true ;
                            continue;
                        }  
                    } while (valid) ;
                    customeraccountBalance.set(customerIndex1,customeraccountBalance.get(customerIndex1)-withdrawAmount);
                    System.out.printf(SUCCESS_MSG,String.format("Success ! | New Account Balaance : RS %,.2f", customeraccountBalance.get(customerIndex1)));
                    System.out.println(customeraccountBalance);
                    System.out.print("\tDo you want to add anothow withdrawel{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break ;
                case TRANSFER :
                    System.out.println("\tEnter the account number of the Sender below");
                    int sendersAccIndex = accNumValidation() ;
                    System.out.println("\tEnter the account number of the Reciever below");
                    int recieversAccIndex = accNumValidation() ;
                    System.out.printf(SUCCESS_MSG,String.format("From Account : %s , Curremt balance : RS %,.2f ", customerID.get(sendersAccIndex),customeraccountBalance.get(sendersAccIndex)));
                    System.out.printf(SUCCESS_MSG,String.format("To Account : %s , Current balance : RS %,.2f", customerID.get(recieversAccIndex),customeraccountBalance.get(recieversAccIndex)));
                    double transferAmount ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Withdraw Amount:");
                        transferAmount = scanner.nextDouble() ;
                        scanner.nextLine() ;
                        if(transferAmount<=100){
                            System.out.printf(ERROR_MSG,"Transfer ammount should be higher than 100");
                            valid = true ;
                            continue;
                        } 
                        if(transferAmount>customeraccountBalance.get(sendersAccIndex) || customeraccountBalance.get(sendersAccIndex)<=500){
                            System.out.printf(ERROR_MSG,"Not enough balance to transfer");
                            valid = true ;
                            continue;
                        }  
                    } while (valid) ;
                    customeraccountBalance.set(sendersAccIndex, ( customeraccountBalance.get(sendersAccIndex) - transferAmount - transferAmount*0.02 ) ) ;
                    customeraccountBalance.set(recieversAccIndex, ( customeraccountBalance.get(recieversAccIndex) + transferAmount ) ) ;
                    System.out.printf(SUCCESS_MSG,"Transfer Successfull");
                    System.out.printf(SUCCESS_MSG,String.format("Sender's Account : %s , Curremt balance : RS %,.2f ", customerID.get(sendersAccIndex),customeraccountBalance.get(sendersAccIndex)));
                    System.out.printf(SUCCESS_MSG,String.format("Recievers Account : %s , Current balance : RS %,.2f", customerID.get(recieversAccIndex),customeraccountBalance.get(recieversAccIndex)));
                    System.out.print("\tDo you want to perform another Transfer{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break ;
                case CHECK_BALANCE :
                    int customerAccindex = accNumValidation() ;
                    System.out.printf(SUCCESS_MSG,String.format("Current balance : Rs %,.2f",customeraccountBalance.get(customerAccindex)));
                    if(customeraccountBalance.get(customerAccindex)<=500){
                            System.out.printf(ERROR_MSG,"Not enough balance to withdraw");
                    }else{
                        System.out.printf(SUCCESS_MSG,String.format("Amount available to withdraw %,.2f", customeraccountBalance.get(customerAccindex) - 500 ));
                    } 
                    System.out.print("\tDo you want to check another Balance{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break ;
                case DELETE_ACCOUNT :
                    System.out.println("\tEnter account number below to delete");
                    int dltAccindex = accNumValidation() ;
                    System.out.printf(SUCCESS_MSG,String.format("Customer name : %s , Current balance : Rs %,.2f",customerName.get(dltAccindex),customeraccountBalance.get(dltAccindex))); 
                    System.out.print("\tAre you sure to delete{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        System.out.printf(SUCCESS_MSG,String.format("Customer ID : %s ,Successfully Deleted ",customerID.get(dltAccindex) ) );
                        customeraccountBalance.remove(dltAccindex) ;
                        customerName.remove(dltAccindex);
                        customerID.remove(dltAccindex);
                         
                    }    
                    System.out.print("\tDo you want to delete another Account{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break ;
                
            }
        }while (true);
    }
    public static String isNameValid(){
        boolean valid;
        String name;
        do{
            valid = false;
            System.out.print("\tEnter Customer Name: ");
            name = scanner.nextLine().strip();
            if (name.isBlank()){
                System.out.printf(ERROR_MSG,"Name cannot be empty!!");
                valid = true;
                continue;
            }
            for (int i = 0; i < name.length(); i++) {
                if (!(Character.isLetter(name.charAt(i)) || 
                    Character.isSpaceChar(name.charAt(i))) ) {
                    System.out.printf("\t%sInvalid Name%s", COLOR_RED_BOLD, RESET);
                    valid = true;
                    break;
                }
            }
        
        }while(valid);
        return name ;
    }

    public static int accNumValidation(){
        String accountNumber ;
        boolean valid = true ;

        loop :
        do {
            System.out.print("\tEnter Account Number : ");
            accountNumber  = scanner.nextLine().strip() ;
            if(accountNumber.isEmpty()){
                System.out.printf(ERROR_MSG,"Account number cannot be empty. Enter Again !");
                continue loop;
            }
            if( !accountNumber.startsWith("SDB-") || accountNumber.length()<9){
                System.out.printf(ERROR_MSG,"Invalid format");
                continue loop;
            }
            char[] charArray = accountNumber.toCharArray();
            for(int i = 4 ; i < charArray.length ; i++ ){
                if(!Character.isDigit(charArray[i])){
                    System.out.printf(ERROR_MSG,"Invalid Format !");
                    continue loop;
                }
            }
            if(!customerID.contains(accountNumber)){
                System.out.printf(ERROR_MSG,"This account number does not exists !!");
                continue loop;
            }
            valid = false ;
        } while (valid);
        return customerID.indexOf(accountNumber) ;
    }

}