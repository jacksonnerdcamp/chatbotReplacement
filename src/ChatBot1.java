import java.util.Scanner;

public class ChatBot1 {
    boolean greeted = false;
    String[] receipt = new String[5];
    int receiptIterator = 0;
    boolean mainCourseAdded;
    boolean sideAdded;
    boolean drinkAdded;
    int menuIterator = 0;
    String[] mainCourses = {
            "eggs",
            "pancakes",
            "waffles",
    };
    String[] sides = {
            "homefries",
            "fries",
            "bacon",
            "sausage",
            "sweetpotato fries"
    };
    String[] drinks = {
            "water",
            "orange juice",
            "apple juice",
            "cranberry juice",
            "coffee"
    };

    String response;

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
                response = mainCourses[menuIterator] + "has been added to your order";
            }
            if(menuIterator > 3){
                response = transformIWantStatement(statement);
            }
            menuIterator++;
        }
        if(mainCourseAdded && !sideAdded)
        {
            response = "Would you like a side with that?";
        }
        return response;
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
        return "I'm sorry, but we are currently out of " + restOfStatement;
    }
}
