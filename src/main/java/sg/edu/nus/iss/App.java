package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static String dirPath = "./data2";
    static String fileName = "";


    private App(){

    }
    public static void main(String[] args) throws IOException {

        File newDirectory = new File(dirPath);
        if (newDirectory.exists()){

        } else {
            newDirectory.mkdir();
        }

        displayMessage("Welcome to my shopping cart!");

        List<String> shoppingCartItems = new ArrayList<>();

        Console console = System.console();
        String incomingInput = "";

        while(true){
            incomingInput = console.readLine("Please input action: (Type \"quit\" to exit, or type help to list commands.)\n");

            switch (incomingInput){
                case "help":
                    displayMessage("list: displays all items in shopping cart");
                    displayMessage("login <name>: to access your shopping cart");
                    displayMessage("add <item>: to add an item to your shopping cart");
                    displayMessage("delete <item>: to remove an item to your shopping cart");
                    displayMessage("exit or quit: exits the program");
                    break;
                case "list":
                    //to do show list
                    if (shoppingCartItems.size() > 0){
                        listItems(shoppingCartItems);
                    } else {
                        displayMessage("Cart is empty");
                    }
                    break;
                case "users":
                    //to do show users
                    break;

                case "quit":
                case "exit":
                    displayMessage("Goodbye!");
                    System.exit(0);


                default:
            }

            String stringValue = "";
            if (incomingInput.startsWith("login")){
                incomingInput = incomingInput.replace(',',' ');

                Scanner scanner = new Scanner(incomingInput.substring(6));

                while (scanner.hasNext()) {
                    fileName = scanner.next();
                }

                File loginFile = new File(dirPath + File.separator + fileName);

                boolean isCreated = loginFile.createNewFile();

                if (isCreated) {
                    displayMessage("File created successfully! Filename: " + loginFile.getCanonicalFile());
                } else {
                    displayMessage("File already created!");
                }
            }


            if (incomingInput.startsWith("add")){
                addToCart(shoppingCartItems, incomingInput);
            }

            if (incomingInput.startsWith("delete")){
                deleteFromCart(shoppingCartItems, incomingInput);
            }

        }
    }

    private static void deleteFromCart(List<String> shoppingCartItems, String incomingInput) {
        String stringValue;
        if(incomingInput.startsWith("delete")){
            incomingInput = incomingInput.replace(',',' ');
            Scanner scanner = new Scanner(incomingInput.substring(6));

            while (scanner.hasNext()){
                stringValue = scanner.next();
                int itemIndexToDelete = Integer.parseInt(stringValue);
                itemIndexToDelete--;

                if (itemIndexToDelete >= shoppingCartItems.size()){
                    displayMessage("Item does not exist");
                } else{
                    shoppingCartItems.remove(itemIndexToDelete);
                }
            }
        }
        listItems(shoppingCartItems);
    }

    private static void addToCart(List<String> shoppingCartItems, String incomingInput) {
        String stringValue;
        if(incomingInput.startsWith("add")){
            incomingInput = incomingInput.replace(',',' ');

            Scanner scanner = new Scanner(incomingInput.substring(4));

            while (scanner.hasNext()){
                stringValue = scanner.next();
                shoppingCartItems.add(stringValue);
            }
        }
        listItems(shoppingCartItems);
    }

    public static void displayMessage (String message){
        System.out.println(message);
    }

    public static void listItems(List<String> shoppingCartItems){
        for (int i = 0; i < shoppingCartItems.size(); i++){
            System.out.println(i+1 + ". " + shoppingCartItems.get(i));
        }
    }
}