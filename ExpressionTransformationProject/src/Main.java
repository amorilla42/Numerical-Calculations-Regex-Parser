import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        try(Scanner sc = new Scanner(new File("test.txt"))){
            List<Token> tokens;
            while(sc.hasNextLine())
            {
                tokens = lexer.tokenizer(sc.nextLine());
                BinaryTree abstractSyntaxTree = parser.parse(tokens);
                //imprimir con parentesis
            }



        }catch (NoSuchElementException e){
            e.printStackTrace();
        }




    }
}