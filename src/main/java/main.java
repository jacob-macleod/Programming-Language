/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jacob
 */
import lexer.*;


public class main {
    public static void main (String[] args) {
        String[] tokens = lexer.createTokens("2+  8  5   + 5");
        
        for (int i=0; i<tokens.length;i++) {
            System.out.println(tokens[i]);
        }
    }
}
