
import controllers.InputController;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.Scanner;

import static controllers.MessageController.askUserForInput;
import static controllers.MessageController.departmentMessage;
import static controllers.MessageController.showInputWarning;
import static controllers.SaveController.saveAllModels;

public class EnterPoint{

    public static Scanner scanner = new Scanner(System.in);
    public static InputController input = new InputController();

    public static void scan() {
        askUserForInput();

        String next = scanner.nextLine();
        switch (next){
        case "1":
            departmentMessage();
            input.headOfDepartment(scanner.nextLine());
            continueOfWork();
            break;
        case "2":
            departmentMessage();
            input.departmentStatistics(scanner.nextLine());
            continueOfWork();
            break;
        case "3":
            departmentMessage();
            input.averageSalary(scanner.nextLine());
            continueOfWork();
            break;
        case "4":
            departmentMessage();
            input.countOfEmployee(scanner.nextLine());
            continueOfWork();
            break;
        case "5":
            System.out.println("Search:");
            input.globalSearch(scanner.nextLine());
            continueOfWork();
            break;
        default:
            showInputWarning();
            scan();
            break;
    } }

    public static void continueOfWork() {
        System.out.println("Do you want to continue working with the application? Y/N");
        String choise = scanner.nextLine();

        switch (choise) {
            case "Y":
                scan();
                break;
            case "N":
                System.out.println("Exit the application");
                scanner.close();
                break;
            default:
                showInputWarning();
                continueOfWork();
                break;
        }
    }

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        saveAllModels();
        scan();

        session.close();
    }
}
