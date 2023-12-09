import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
    * This is the main method of the program. It reads a file line by line, tokenizes each line into a list of Tokens using the Lexer, 
    * parses the list of Tokens into an Abstract Syntax Tree (AST) using the Parser, and then prints the AST.
    * 
    * @param args Command line arguments. Not used in this method.
    * 
    * The method works as follows:
    * - It creates a Lexer and a Parser.
    * - It opens the file "test.txt" for reading. If the file doesn't exist, it prints an error message and exits.
    * - It reads the file line by line. If a line is not empty, it tokenizes the line into a list of Tokens using the Lexer,
     *  parses the list of Tokens into an AST using the Parser, and then prints the AST.
    * 
    * @throws FileNotFoundException If the file "test.txt" does not exist.
    */
    public static void main(String[] args) throws FileNotFoundException {

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        try(Scanner sc = new Scanner(new File("test.txt"))){
            List<Token> tokens;
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                line = Lexer.removeSpacesAndComments(line);
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