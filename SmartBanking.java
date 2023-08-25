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

    public static ArrayList<String[]> customer = new ArrayList<>();       // Sepeate String arrays with length 2 for each customers name and account balance
    public static ArrayList<String> accountNumber = new ArrayList<>() ;      // Account Numbers are maintain as arraylist

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
                    String[] customerDetails = new String[2] ;
                    System.out.printf("\tNew Customer ID: SDB-%05d\n", (customer.size() + 1));

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
                    accountNumber.add(String.format("SDB-%05d", customer.size() + 1));
                    customerDetails[0] = name ;
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
                    customerDetails[1] = initialDeposit+"" ;
                    customer.add(customerDetails);          //String contain name and account balance added to customer list
                    System.out.printf("\t%sID SDB:%05d | Name: %s Added successfully!%s\n",COLOR_GREEN_BOLD,customer.size(),name,RESET);
                    System.out.print("\tDo you want to add another{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSITS :
                    System.out.print("\tEnter Account number to add deposit :");
                    String accNumber = scanner.nextLine().strip() ;
            }
        }while (true);
    }
    public static void
}