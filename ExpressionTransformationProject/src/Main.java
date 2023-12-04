import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        try(Scanner sc = new Scanner(new File("tesdt.txt"))){
            List<Token> tokens;
            while(sc.hasNextLine())
            {
                String line= sc.nextLine();
                if(!line.isEmpty()){
                    tokens = lexer.tokenizer(line);
                    BinaryTree abstractSyntaxTree = parser.parse(tokens);
                    parser.printTree(abstractSyntaxTree);
                    System.out.println();
                }
            }
        }catch (FileNotFoundException e) {
            System.err.println("File doesnt exist");
        }
    }
}