import java.util.Scanner;
public class ChatBotRunner {
    public static void main(String[] args)
    {
        ChatBot1 chatbot1 = new ChatBot1();

        Scanner in = new Scanner (System.in);
        System.out.println("Welcome to would you like to order breakfast, lunch or dinner?");
        String userInput = in.nextLine();
        String statement = "";
        while(!statement.equals("bye"))
        {
            if (userInput.equals("breakfast") || (userInput.equals("Breakfast")))
            {
                chatbot1.chatLoop(statement);
                statement = in.nextLine();
            }
        }
    }
}
