import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> tokenizer(String line) {

        List<Token> tokens = new ArrayList<>();
        char c;
        int i = 0;
        while(i< line.length()) {
            c = line.charAt(i);
            if (c == ')') {
                tokens.add(new Token(Token.Type.PAR_B,""));
                i++;
            }else if (c=='(') {
                tokens.add(new Token(Token.Type.PAR_A,""));
                i++;
            } else if (c == ',') {
                tokens.add(new Token(Token.Type.COMMA,""));
                i++;
            } else if (c == 'a' && line.length()>(i+2) && line.charAt(i+1)=='d' && line.charAt(i+2) == 'd') {
                tokens.add(new Token(Token.Type.FUN," + "));
                i+=3;
            } else if (c == 's' && line.length()>(i+2) && line.charAt(i+1)=='u' && line.charAt(i+2) == 'b') {
                tokens.add(new Token(Token.Type.FUN," - "));
                i+=3;
            }  else if (c == 'd' && line.length()>(i+2) && line.charAt(i+1)=='i' && line.charAt(i+2) == 'v') {
                tokens.add(new Token(Token.Type.FUN," / "));
                i+=3;
            }else if (c == 'p' && line.length()>(i+2) && line.charAt(i+1)=='o' && line.charAt(i+2) == 'w') {
                tokens.add(new Token(Token.Type.FUN,"^"));
                i+=3;
            }else if (c == 'm' && line.length()>(i+2) && line.charAt(i+1)=='u' && line.charAt(i+2) == 'l') {
                tokens.add(new Token(Token.Type.FUN," * "));
                i+=3;
            }else if (c == 'm' && line.length()>(i+2) && line.charAt(i+1)=='o' && line.charAt(i+2) == 'd') {
                tokens.add(new Token(Token.Type.FUN," % "));
                i+=3;
            }
            else if (c == '+' || c == '-' || Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while ((c == '.' || c == '+' || c == '-' || c == 'e' || Character.isDigit(c)) && i < line.length()) {
                    System.out.println(c);
                    num.append(c);
                    i++;
                    if (i < line.length())
                        c = line.charAt(i);
                }
                if (isOperand(num.toString()))
                    tokens.add(new Token(Token.Type.NUM, num.toString()));
                else
                    error();
            }
        }
        return tokens;
    }

    public void error() {
        System.out.println("Syntax error");
        System.exit(-1);
    }

    public Boolean isOperand(String op) {
        return (op.matches("[+-]?[0-9]+(\\.[0-9]+)?(e[+-]?[0-9]+)?"));
    }

}


