package com.example;

import Files.FileSystem;
import Files.LogInFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static String username = "";
    private static String password = "";
    private static String firstName = "";
    private static final FileSystem fileSystem = new FileSystem();
    private static final LogInFile logIn = new LogInFile();

    private static String color = "\u001B[34m";

    public static void main(String[] args) throws InterruptedException {
        webSeries();
    }

    private static void enterIn() throws InterruptedException {

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\033[1m" + "\033[4m" + "Wel Come!" + "\033[0m");

        Thread.sleep(500);
        System.out.println("Loading(-)");

        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            System.out.print("---------");
            if (i + 1 == 10) {
                System.out.println("100%.Done");
            }
        }

        System.out.println("\t\t\t\t<------Select account------>");
        System.out.println("+------------------------------------------------------------------------------------------+");
        System.out.println("|                        |                                  |                              |");
        System.out.println("|\033[1m \u001B[31m * USER ACCOUNT        |           * ADMIN ACCOUNT        |           * Sign-Up\u001B[38m\033[0m          |");
        System.out.println("|                        |                                  |                              |");
        System.out.println("+------------------------------------------------------------------------------------------+");

    }

    private static void addSeries() {

        System.out.println("--------------Enter details below-----------");
        System.out.println("Enter Series Name:-");
        String name = scanner.nextLine();
        System.out.println("Enter Rating:-");
        String rating = scanner.nextLine();
        System.out.println("Enter cast details as paragraph:-");
        String cast = scanner.nextLine();
        System.out.println("Enter the language:-");
        String language = scanner.nextLine();
        System.out.println("Enter the description:-");
        String description = scanner.nextLine();

        WebSeries series = new WebSeries(name, rating, cast, language, description);

        fileSystem.addSeries(series);
    }

    private static int adminMenu() {

        int selection;
        System.out.println("\u001B[31m\033[1m\n\t\t1.Show all Series");
        System.out.println("\t\t2.Add Series");
        System.out.println("\t\t3.Quit\n");
        System.out.println("\t\t4.Delete Series");
        System.out.println("\t\t5.Update series\033[0m\u001B[38m");

        while (true) {
            try {
                System.out.println("Enter your choice");
                selection = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }

        return selection;

    }

    private static String performAnalysis(int option) {

        String returnValue = "";

        if (option == 1) {
            returnValue = showList();
        }else
        if (option == 2) {
            returnValue = search();
        }else if(option == 4){
            returnValue = showAdmin();
        }

        return returnValue;

    }

    private static String showAdmin() {

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\033[1m\033[4mDEVELOPERS DETAILS \033[0m:");

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t|\033[1m\033[4mNAME            |      E-MAIL            \033[0m|");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t|HRITHIK R KHARVI");
        System.out.println("|hrithikkharvi9@gmail.com|");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t|                |                        |");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t|ARJUN BR        ");
        System.out.println("|arjun7gmail.com         |");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------+");

        System.out.println("\n\n\u001B[31mEnter:             'back' to go back / 'quit' to quit app \u001B[38m");

        return scanner.nextLine();
    }

    private static String search() {

        searchItem();
        System.out.println("\n\n\u001B[31mEnter:             'back' to go back / 'quit' to quit app \u001B[38m");
        return scanner.nextLine();
    }

    public static String showList() {
        boolean quit = false;
        String returnValue = "";
        int count = 1;
        while (!quit) {
            List<WebSeries> list = fileSystem.getSeries();
            if(count == 1) {
                System.out.println("\t\t\t\t\t\t\t\tWel come, " + firstName);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tTOP Web series ");
                loopThrough(list);
            }

            System.out.println("\u001B[31mEnter sort");
            System.out.println("Enter:     'Description' for details / 'quit' to exit the app / 'back' to menu          \u001B[38m");
            String details = scanner.nextLine();
            if (details.equalsIgnoreCase("description")) {
                System.out.print("Enter the series id");
                int id;
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }
                id = scanner.nextInt();
                WebSeries selectedSeri = list.get(id - 1);
                System.out.print("\n");
                printOutput(selectedSeri);
                System.out.println("\n\n\033[1m\u001B[31mEnter back to go back to list\u001B[38m");

//                System.out.println("\u001B[38m");
                scanner.nextLine();
                String goBack = scanner.nextLine();

                if (goBack.equalsIgnoreCase("back")) {
                    continue;
                }
            }else
            if (details.equalsIgnoreCase("back")) {
                returnValue = "back";
                quit = true;
            }else if(details.equalsIgnoreCase("sort")){
                sort();
            } else {
                quit = true;
                returnValue = "quit";
            }
            count++;
        }

        return returnValue;

    }

    private static void printOutput(WebSeries selectedSeri) {
        System.out.println("\t\t\t\t\t\t\t+-------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t|\t\t\t\t\t\t\t" + color + "\033[1m\033[4m" + selectedSeri.getName() + "\033[0m");
        System.out.println("\t\t\t\t\t\t\t|IMDB        : " + selectedSeri.getRating());
        System.out.println("\t\t\t\t\t\t\t|Casts       : " + selectedSeri.getCasts());
        System.out.println("\t\t\t\t\t\t\t|Language    : " + selectedSeri.getLanguage());
        String description = selectedSeri.getDescription();
        List<String> descriptionList = getDesList(description);

        System.out.print("\t\t\t\t\t\t\t|Description : ");
        System.out.println(descriptionList.get(0));
        for (int i = 1; i < descriptionList.size(); i++) {
            System.out.println("\t\t\t\t\t\t\t|\t\t\t   " + descriptionList.get(i));
        }
        System.out.println("\t\t\t\t\t\t\t+----------------------------------------------------------------------------------------------------");
    }

    public static boolean checkCredentials() {
        boolean isOk = false;
        int count = 1;

        while (!isOk) {
            if (count > 1) {
                System.out.println("\u001B[31m \033[1mWrong Credentials: want to try again ? yes:no\033[0m");
                String isContinue = scanner.nextLine();
                if (isContinue.equalsIgnoreCase("no")) {
                    isOk = false;
                    break;
                }
            }
            System.out.print("Enter your username : ");
            username = scanner.nextLine();
            System.out.print("Enter your password : ");
            password = scanner.nextLine();
            Map<String, String> creds = logIn.getCredentials();

            isOk = creds.containsKey(username) && creds.get(username).equals(password);
            count++;
        }

        return isOk;

    }

    public static int menu() {
        int selection;
        System.out.println("\n\t\t\033[1m+-------------------+");
        System.out.println("\t\t|1.Show all Series  |");
        System.out.println("\t\t|2.Search           |");
        System.out.println("\t\t|3.Quit             |");
        System.out.println("\t\t|4.About-us         |");
        System.out.println("\t\t+-------------------+\033[0m\n");

        while (true) {
            try {
                System.out.println("Enter your choice");
                selection = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Cannot covert Into number");
            }
        }

        return selection;
    }

    public static List<String> getDesList(String description) {
        ArrayList<String> descriptionList = new ArrayList<>();
        int initial = 0;
        if (description.length() >= 80) {
            int rem = description.length();
            while (rem > 80) {
                rem = rem - 80;
                descriptionList.add(description.substring(initial, initial + 80));
                initial += 80;
            }
            if (rem > 0) {
                descriptionList.add(description.substring(initial));
            }
        } else {
            descriptionList.add(description);
        }

        return descriptionList;

    }

    public static void webSeries()throws InterruptedException{
        enterIn();
        System.out.print("Enter Your choice : ");
        String input = "";
        boolean isCorrect = false;
        while (!isCorrect) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("User") || input.equalsIgnoreCase("Admin") || input.equalsIgnoreCase("signup")) {
                isCorrect = true;
            }else{
                System.out.print("Please Enter valid input --> ");
            }
        }

        if (input.equalsIgnoreCase("user")) {
            boolean isOk = checkCredentials();
            if (isOk) {
                System.out.print("Enter your first-name : ");
                firstName = scanner.nextLine();
                int selection = menu();

                String next = performAnalysis(selection);
                while (true) {
                    if (next.equalsIgnoreCase("back")) {
                        selection = menu();
                        next = performAnalysis(selection);
                    } else {
                        break;
                    }
                }
            }

        } else if((input.equalsIgnoreCase("sign-up") || (input.equalsIgnoreCase("signup")))){
            System.out.print("Enter your USERNAME : ");
            String userName = scanner.nextLine();
            System.out.println();
            System.out.println("Enter your PASSWORD : ");
            String pass = scanner.nextLine();

            Map<String,String> credentials = logIn.getCredentials();
            credentials.put(userName,pass);
            logIn.save();
            webSeries();

        }else {
            boolean adminCheck = checkCredentials();
            if (adminCheck) {
                String methodReturn = "";
                while (true) {
                    int choice = adminMenu();
                    if (choice == 1) {
                        methodReturn = showList();
                        if (methodReturn.equalsIgnoreCase("quit")) {
                            break;
                        }
                    } else if (choice == 2) {
                        addSeries();
                    } else if(choice == 4){
                        deleteSeri();
                    }else if(choice == 5){
                        boolean isUpdated = updateSeri();
                        if(isUpdated){
                            System.out.println("Successfully updated");
                        }else{
                            System.out.println("Data not found");
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        fileSystem.save();
    }

    private static boolean updateSeri() {

        System.out.println("Enter series name to be updated :--> ");
        String name = scanner.nextLine();

        for(WebSeries seri : fileSystem.getSeries()){
            if(seri.getName().equalsIgnoreCase(name)){
                System.out.println("Enter name : ");
                seri.setName(scanner.nextLine());
                System.out.println("Enter rating : ");
                seri.setRating(scanner.nextLine());
                System.out.println("Enter cast : ");
                seri.setCasts(scanner.nextLine());
                System.out.println("Enter language : ");
                seri.setLanguage(scanner.nextLine());
                System.out.println("Enter description : ");
                seri.setDescription(scanner.nextLine());

                fileSystem.save();
                return true;
            }

        }

        return false;


    }

    public static void deleteSeri(){
        WebSeries result = searchItem();
        boolean isTrue = fileSystem.getSeries().remove(result);

        if(isTrue){
            System.out.println("Successful!!!!");
        }
        fileSystem.save();
    }

    public static WebSeries searchItem(){
        System.out.print("Enter Search Name : ");
        String searchFor = scanner.nextLine();
        WebSeries result = fileSystem.search(searchFor);
        if(result == null){
            System.out.println("Data Not Found!");
            return null;
        }else {
            printOutput(result);
        }
        return result;
    }

    public static void sort(){

        System.out.println("+---------------------------------------");
        System.out.println("|           1.English                   |");
        System.out.println("|           2.Hindi                     |");
        System.out.println("|           3.Other                     |");
        System.out.println("+---------------------------------------+");

        System.out.println("Enter your choice ");
        int sortOrder;

        while(true){
            try{
                sortOrder = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch(NumberFormatException e){
                System.out.println("Please enter the valid number");
            }
        }

        if(sortOrder == 1){
            getSortedList("English");
        }else if(sortOrder == 2){
            getSortedList("Hindi");
        }else{
            getSortedList("Other");
        }
    }

    public static void getSortedList(String language){

        List<WebSeries> webSeries = new ArrayList<>();

        if(!language.equalsIgnoreCase("other")) {
            for (WebSeries seri : fileSystem.getSeries()) {
                if (seri.getLanguage().equalsIgnoreCase(language)) {
                    webSeries.add(seri);
                }
            }
        }else{
            for (WebSeries seri : fileSystem.getSeries()) {
                if (!seri.getLanguage().equalsIgnoreCase("English") && !seri.getLanguage().equalsIgnoreCase("Hindi")) {
                    webSeries.add(seri);
                }
            }
        }

        loopThrough(webSeries);

    }

    public static void loopThrough(List<WebSeries> list){
        int count = 1;
        System.out.println("\t\t\t\t\t\t\t\t\033[1m\033[4mId\033[0m | \033[1m\033[4mNames\033[0m");
        for (WebSeries seri : list) {
            System.out.println("\t\t\t\t\t\t\t\t" + count + "  : " + seri.getName());
            count++;
        }
    }


}





















