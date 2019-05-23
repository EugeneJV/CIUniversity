package controllers;

public class MessageController {
    public static void askUserForInput() {
        System.out.println("To show the head of department: press 1 ");
        System.out.println("To show department statistics: press 2 ");
        System.out.println("To show the average salary for department: press 3");
        System.out.println("To show count of employee of department: press 4 ");
        System.out.println("To make global search: press 5 ");
    }
    public static void showInputWarning() {
        System.out.println("Wrong input,please try again");
    }

    public static void departmentMessage() {
        System.out.println("Enter department name from list: Faculty of Law, Faculty of Biology, Faculty of Economics");
    }
}
