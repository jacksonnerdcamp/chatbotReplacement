import java.util.Scanner;
import java.math.BigDecimal;
public class ChatBot1 {
    boolean greeted = false;
    String[] receipt = new String[5];
    int receiptIterator = 0;
    boolean mainCourseAdded = false;
    boolean sideAdded = false;
    boolean drinkAdded = false;
    boolean specialRequest = false;
    int menuIterator = 0;
    int sideMenuIterator = 0;
    int drinksMenuIterator = 0;
    String response;
    String[] mainCourses = {
            "eggs",
            "pancakes",
            "waffles",
    };
    String[] sides = {
            "sweetpotato fries",
            "homefries",
            "fries",
            "bacon",
            "sausage",
            "toast"
    };
    String[] drinks = {
            "water",
            "orange juice",
            "apple juice",
            "cranberry juice",
            "coffee"
    };
    String[] oneLiners = {
            "Did you hear about the semi-colon that broke the law? He was given two consecutive sentences.",
            "What’s the difference between a good joke and a bad joke timing.",
            "Velcro – what a rip-off!",

    };
    public void chatLoop(String statement) {
        Scanner in = new Scanner(System.in);
        System.out.println("Good morning! What would you like for breakfast?");

        while (!statement.equals("bye")) {
            statement = in.nextLine();
            System.out.println(getResponse(statement));

        }
    }

    public String getResponse(String statement) {
        while(!mainCourseAdded)
        {
            if (findKeyword(statement, mainCourses[menuIterator], 0) >= 0) {
                receipt[receiptIterator] = mainCourses[menuIterator];
                receiptIterator++;
                mainCourseAdded = true;
                response = "I've added " + mainCourses[menuIterator] +
                        " to your order. What would you like on the side?";
                return response;
            }
            menuIterator++;
            if(menuIterator > 2){
                response = transformIWantStatement(statement);
                menuIterator = 0;
                return response;
            }
        }
        if(!sideAdded)
        {
            while(!sideAdded) {
                if (findKeyword(statement, sides[sideMenuIterator], 0) >= 0) {
                    receipt[receiptIterator] = sides[sideMenuIterator];
                    receiptIterator++;
                    sideAdded = true;
                    response = "Mmmmm, " + sides[sideMenuIterator] + " is a great choice! What would you like to drink?";
                    return response;
                }
                if (sideMenuIterator > 5) {
                    response = transformIWantStatement(statement);
                }
                sideMenuIterator++;
            }
        }
        if(!drinkAdded)
        {
            while(!drinkAdded) {
                if (findKeyword(statement, drinks[drinksMenuIterator], 0) >= 0) {
                    receipt[receiptIterator] = drinks[drinksMenuIterator];
                    receiptIterator++;
                    drinkAdded = true;
                    response = "Allllright! I've added " + drinks[drinksMenuIterator] + " to your order. " +
                            "Write any special requests here:";
                    return response;
                }
                drinksMenuIterator++;
                if (drinksMenuIterator > 4) {
                    response = transformIWantStatement(statement);
                    return response;
                }
            }
        }
        if(!specialRequest)
        {
            receipt[receiptIterator] = statement;
            specialRequest = true;
            return "Your request has been added. When you are ready for your receipt, just ask.";
        }


        if(findKeyword(statement, "receipt", 0) >= 0 && drinkAdded)
        {
            System.out.println("Here is your receipt");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Main Course: " + receipt[0]);
            System.out.println("Side: " + receipt[1]);
            System.out.println("Drink: " + receipt[2]);
            if(specialRequest)
            {
                System.out.println("Special Requests: " + receipt[3]);
            }
            System.out.println();
            System.out.println("Joke of the day:");
            System.out.println(oneLiners[(int)(Math.random()*3)]);
            System.out.println();
            System.out.println("As we are a farm to table fast food restaurant, our breakfast prices" +
                    " are subject to change.");
            System.out.println("Please ask your cashier for the prices of the day.");

        }

        return "";
    }

    private int findKeyword(String statement, String goal,
                            int startPos) {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();
        int returnPsn = -1;

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0) {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0) {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length()) {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
            {
                returnPsn = psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }
        return returnPsn;
    }
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "I'm sorry, but " + restOfStatement + " is not a main menu item.";
    }
}
