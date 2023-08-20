import java.util.Arrays;
import java.util.Scanner;

public class SmartbankingCLI{
    private static Scanner scanner= new Scanner(System.in) ;
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSITS = "Deposit";
        final String WITHDRAW = "WITHDRAW";
        final String TRANSFER = "🖨 TRANSFER";
        final String CHECK_BALANCE = "Check Balance" ;
        final String DELETE_ACCOUNT = "Delete account" ;

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[] customerNames = new String[0] ;
        String[] cutomerIDarray = new String[0] ;
        double[] accountBalance = new double[0];

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
                    System.out.printf("\tNew Customer ID: SDB-%05d\n", (customerNames.length + 1));

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
                                System.out.printf("\t%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = true;
                                break;
                            }
                        }
                    }while(valid);
                    String[] newCustomerNames = new String[customerNames.length+1] ;
                    String[] newCustomerIDArray = new String[customerNames.length+1] ;
                    double[] newAccountBalance = new double[accountBalance.length + 1] ;
                    for(int i = 0 ; i < customerNames.length ; i++){
                        newCustomerNames[i] = customerNames[i] ;
                        newCustomerIDArray[i] = cutomerIDarray[i] ;
                        newAccountBalance[i] = accountBalance[i];
                    }
                    newCustomerNames[newCustomerNames.length - 1] = name ;
                    newCustomerIDArray[newCustomerIDArray.length - 1] = String.format("SDB-%05d", cutomerIDarray.length) ;
                    customerNames = newCustomerNames ;
                    cutomerIDarray = newCustomerIDArray ;
                    int initialDeposit ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Initial Deposit :");
                        initialDeposit = scanner.nextInt() ;
                        scanner.nextLine() ;
                        if(initialDeposit<=5000){
                            System.out.printf(ERROR_MSG,"Iniial ammount should be higher than 5000");
                            valid = true ;
                            continue;
                        }
                        
                    } while (valid) ;
                    newAccountBalance[newAccountBalance.length-1] = initialDeposit ;
                    accountBalance = newAccountBalance ;
                    System.out.printf("\t%sID SDB:%05d | Name: %s Added successfully!%s\n",COLOR_GREEN_BOLD,customerNames.length,name,RESET);
                    System.out.print("\tDo you want to add another{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSITS :
                    String accNumber ;
                    loop :
                    do {
                        valid = false ;
                        System.out.print("Enter Account Number :");
                        accNumber = scanner.nextLine().strip() ;
                        if(accNumber.isBlank()){
                            System.out.printf(ERROR_MSG,"Acc number cannot be empty");
                            valid = true ;
                            continue ;
                        }
                        if(accNumber.startsWith("SDB-") || accNumber.length()<5 ){
                            System.out.printf(ERROR_MSG, "Invalid Format!") ;
                            valid = true ;
                            continue ;
                        }
                        for(int i = 4 ; i < accNumber.length(); i++){
                            if( !Character.isDigit(accNumber.charAt(i)) ){
                                 System.out.printf(ERROR_MSG,"Invalid Format");
                                 valid = true ;
                                 continue loop ;
                            }
                        }
                        int index = -1;
                        for(int i = 0 ; i < cutomerIDarray.length ; i++){
                          if(accNumber.equalsIgnoreCase(cutomerIDarray[i])){
                            System.out.println("Account found");
                            index = i ;
                          }
                        }
                        if(index<0){
                            System.out.printf(ERROR_MSG,"Account Number not found!!");
                            valid = true ;
                            continue ;
                        }
                        System.out.println(customerNames[index]);
                        break;
                    } while (valid);
            }
        }while (true);
    }
}