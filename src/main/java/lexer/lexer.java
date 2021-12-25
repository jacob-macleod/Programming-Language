/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jacob
 */
package lexer;

public class lexer {
    // Keytokens are operational tokens such as +, -, *, IF, etc. I may turn this from an array into a comment because I may not need it in an array form
    public String[] keyTokens = {"ADD", "SUBCTRACT", "MULTIPLY", "DIVIDE", "LEFT_BRACKET", "RIGHT_BRACKET"};
    public static String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    // Used to hold the tokenised version of the line currently being analysed
    public static String[] tokenArray = {};
    // Used to hold the array of numbers found - for examble, in 123 + 8, when analysing the first term, it would hold 123
    public static String numbersFound = "";
    static boolean error = true;
    
    
    
    public static String[] appendToArray(String[] array, String item) {
        String[] arrayToReturn = new String[array.length + 1];
        
        // Iterate through array and copy over all it's values to the new array
        for (int arrayIndex=0; arrayIndex < array.length; arrayIndex ++) {
            arrayToReturn[arrayIndex] = array[arrayIndex];
        }
        
        // Add item to the last location in arrayToreturn
        arrayToReturn[arrayToReturn.length-1] = item;
        
        return arrayToReturn;
    }
   
    public static String[] convertStringToArray(String string) {
        String[] arrayToReturn = string.split("");
        
        return arrayToReturn;
    }
    
    public static String[] createTokens (String line) {
        String[] lineArr = convertStringToArray(line);
        error = true;
        
        for (int i=0; i<line.length(); i++) {
            // Check for each token
            // TODO: Make it go to the next part if a space is found
            if (lineArr[i].equals("+")) {
                tokenArray = appendToArray(tokenArray, "ADD");
                error = false;
            } else if (lineArr[i].equals("-")) {
                tokenArray = appendToArray(tokenArray, "SUBTRACT");
                error = false;
            } else if (lineArr[i].equals("*")) {
                tokenArray = appendToArray(tokenArray, "MULTIPLY");
                error = false;
            } else if (lineArr[i].equals("/")) {
                tokenArray = appendToArray(tokenArray, "DIVIDE");
                error = false;
            } else if (lineArr[i].equals("(")) {
                tokenArray = appendToArray(tokenArray, "LEFT_BRACKET");
                error = false;
            } else if (lineArr[i].equals(")")) {
                tokenArray = appendToArray(tokenArray, "RIGHT_BRACKET");
                error = false;
            }else {
                numbersFound = "";
                
                for (int digitIndex = 0; digitIndex < 9; digitIndex ++) {
                    if (lineArr[i].equals(digits[digitIndex])) {
                        numbersFound = numbersFound + lineArr[i];
                        error = false;
                        
                        // Add all the numbers after this number to numbersFound until another operator is reached
                        for (int numberIndex = i; numberIndex < 10; numberIndex ++) {
                            for (int digitIndex2 = 0; digitIndex2 < 10; digitIndex2 ++) {
                                if (lineArr[numberIndex] == digits[digitIndex2]) {
                                    numbersFound = numbersFound + tokenArray[numberIndex];
                                    error = false;
                                } else {
                                    numberIndex = 11;
                                    break;
                                }
                            }
                        }
                       
                    tokenArray = appendToArray(tokenArray, numbersFound);
                    } else {
                        if (error == true) {
                            // \u001b[31m is the ansi escape code to turn text red and \u001b[0m resets the color of text 
                            System.out.println("\u001b[31mERROR: Unkown token at " + lineArr[i] + "\u001b[0m");
                        }
                    }
                }
            }
        }
        
        return tokenArray;
    }
}
