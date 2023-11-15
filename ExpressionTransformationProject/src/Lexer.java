import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<String> tokenizer(String line){
        String[] split = line.split("[() ,]+");

        List<String> tokens = new ArrayList<>();

        for(String s : split)
        {
            switch (s.toLowerCase()){
                case "add": tokens.add(" + ");
                            break;
                case "sub": tokens.add(" - ");
                            break;
                case "mul": tokens.add(" * ");
                            break;
                case "div": tokens.add(" / ");
                            break;
                case "pow": tokens.add("^");
                            break;
                case "mod": tokens.add(" % ");
                            break;
                default:
                    if (isOperand(s))
                        tokens.add(s);
                    else
                        error();
                    break;
            }
        }
        return tokens;
    }

    public void error(){
        System.out.println("Syntax error");
        System.exit(-1);
    }

    public Boolean isOperand(String op){
        return (op.matches("[0-9]+"));
    }

}
