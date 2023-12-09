import java.util.ArrayList;
import java.util.List;

public class Lexer {


    /**
    * This method tokenizes a given line of text into a list of Tokens.
    * 
    * @param line The string to be tokenized.
    * 
    * The method works as follows:
    * - It iterates over each character in the line.
    * - If the character is a parenthesis, comma, or a known function name (like 'add', 'sub', 'div', 'pow', 'mul', 'mod'), it creates a new Token of the corresponding type and adds it to the list.
    * - If the character is a digit, a plus sign, or a minus sign, it treats it as part of a number. It collects all subsequent characters that are part of the number (digits, plus signs, minus signs, dots, and 'e') and adds a NUM type Token to the list.
    * - If the character doesn't match any of the above, it calls the error() method.
    * 
    * @return A list of Tokens representing the tokenized line.
    */
    public List<Token> tokenizer(String line) {

        List<Token> tokens = new ArrayList<>();
        char c;
        int i = 0;
        while(i< line.length()) {
            c = line.charAt(i);
            if (c == ')') {
                tokens.add(new Token(Token.Type.PAR_B));
                i++;
            }else if (c=='(') {
                tokens.add(new Token(Token.Type.PAR_A));
                i++;
            } else if (c == ',') {
                tokens.add(new Token(Token.Type.COMMA));
                i++;
            } else if (c == 'a' && line.length()>(i+2)
                    && line.charAt(i+1)=='d' && line.charAt(i+2) == 'd') {
                tokens.add(new Token(Token.Type.FUN, Token.Operation.ADD));
                i+=3;
            } else if (c == 's' && line.length()>(i+2)
                    && line.charAt(i+1)=='u' && line.charAt(i+2) == 'b') {
                tokens.add(new Token(Token.Type.FUN,Token.Operation.SUB));
                i+=3;
            }  else if (c == 'd' && line.length()>(i+2)
                    && line.charAt(i+1)=='i' && line.charAt(i+2) == 'v') {
                tokens.add(new Token(Token.Type.FUN,Token.Operation.DIV));
                i+=3;
            }else if (c == 'p' && line.length()>(i+2)
                    && line.charAt(i+1)=='o' && line.charAt(i+2) == 'w') {
                tokens.add(new Token(Token.Type.FUN,Token.Operation.POW));
                i+=3;
            }else if (c == 'm' && line.length()>(i+2)
                    && line.charAt(i+1)=='u' && line.charAt(i+2) == 'l') {
                tokens.add(new Token(Token.Type.FUN,Token.Operation.MUL));
                i+=3;
            }else if (c == 'm' && line.length()>(i+2)
                    && line.charAt(i+1)=='o' && line.charAt(i+2) == 'd') {
                tokens.add(new Token(Token.Type.FUN,Token.Operation.MOD));
                i+=3;
            }
            else if (c == '+' || c == '-' || Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while ((c == '.' || c == '+' || c == '-' || c == 'e'
                        || Character.isDigit(c)) && i < line.length()) {
                    num.append(c);
                    i++;
                    if (i < line.length())
                        c = line.charAt(i);
                }
                if (isOperand(num.toString()))
                    tokens.add(new Token(Token.Type.NUM, num.toString()));
                else
                    error();
            }else
                error();
        }
        return tokens;
    }

    public void error() {
        System.out.println("Lexical error");
        System.exit(-1);
    }


    /**
    * This method checks if a given string is a valid operand.
    * 
    * @param op The string to be checked.
    * 
    * An operand is considered valid if it matches the regular expression "[+-]?[0-9]+(\\.[0-9]+)?(e[+-]?[0-9]+)?". 
    * This regular expression checks for:
    * - An optional sign (+ or -) at the beginning.
    * - One or more digits.
    * - An optional decimal part, which consists of a dot followed by one or more digits.
    * - An optional exponent part, which consists of 'e', an optional sign, and one or more digits.
    * 
    * @return true if the string is a valid operand, false otherwise.
    */
    public Boolean isOperand(String op) {
        return (op.matches("[+-]?[0-9]+(\\.[0-9]+)?(e[+-]?[0-9]+)?"));
    }

}


