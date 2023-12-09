import java.util.List;
import java.util.Stack;

public class Parser {

    public enum NonTerminals {S,EXP,PAR_A,PAR_B,COMMA,FUN};

    /**
    * This method parses a list of Tokens into an Abstract Syntax Tree (AST).
    * 
    * @param tokens The list of Tokens to be parsed.
    * 
    * The method works as follows:
    * - It creates a stack and pushes the start symbol onto it.
    * - It creates an empty AST.
    * - It iterates over the list of Tokens. For each Token, it pops the top symbol from the stack and checks if the Token matches the expected type based on the symbol. 
    *   If it does, it performs the appropriate action (pushing more symbols onto the stack, inserting the Token into the AST, or moving to the next Token). 
    *   If it doesn't, it prints an error message and exits.
    * - After all Tokens have been processed, it checks if there are any remaining Tokens. If there are, it prints an error message and exits.
    * 
    * @return The root of the AST.
    */
    public BinaryTree parse(List<Token> tokens){
        Stack<NonTerminals> st = new Stack<>();
        st.push(NonTerminals.S);
        BinaryTree root = null;
        int i = 0;
        while (!st.empty()){
            NonTerminals nt = st.pop();
            if(nt == NonTerminals.S && tokens.get(i).getType() == Token.Type.FUN){
                st.push(NonTerminals.PAR_B);
                st.push(NonTerminals.EXP);
                st.push(NonTerminals.COMMA);
                st.push(NonTerminals.EXP);
                st.push(NonTerminals.PAR_A);
                st.push(NonTerminals.FUN);
            } else if (nt == NonTerminals.EXP && tokens.get(i).getType()== Token.Type.FUN) {
                st.push(NonTerminals.PAR_B);
                st.push(NonTerminals.EXP);
                st.push(NonTerminals.COMMA);
                st.push(NonTerminals.EXP);
                st.push(NonTerminals.PAR_A);
                st.push(NonTerminals.FUN);
            } else if (nt == NonTerminals.EXP && tokens.get(i).getType()== Token.Type.NUM) {
                root.insertInAST(tokens.get(i));
                i++;
            } else if (nt == NonTerminals.FUN && tokens.get(i).getType()== Token.Type.FUN) {
                if(root==null)
                    root = new BinaryTree(tokens.get(i));
                else
                    root.insertInAST(tokens.get(i));
                i++;
            } else if (nt == NonTerminals.PAR_A && tokens.get(i).getType()== Token.Type.PAR_A) {
                i++;
            } else if (nt == NonTerminals.PAR_B && tokens.get(i).getType()== Token.Type.PAR_B) {
                i++;
            } else if (nt == NonTerminals.COMMA && tokens.get(i).getType()== Token.Type.COMMA) {
                i++;
            }else {
                System.out.println("Syntax Error");
                System.exit(-1);
            }
        }
        if (i != tokens.size() ) {
            System.out.println("Syntax Error");
            System.exit(-1);
        }
        return root;
    }

    /**
    * This method returns the precedence value of a given operator Token.
    * 
    * @param t The operator Token whose precedence value is to be determined.
    * 
    * The method works as follows:
    * - If the operator is ADD or SUB, it returns 0.
    * - If the operator is MUL, DIV, or MOD, it returns 1.
    * - If the operator is POW, it returns 2.
    * - For any other operator, it returns 3.
    * 
    * The precedence values are used to determine the order of operations when parsing expressions. Operators with higher precedence values are evaluated before operators with lower precedence values.
    * 
    * @return The precedence value of the operator.
    */
    private int operatorPrecedenceValue(Token t){
        if (t.getOperation()== Token.Operation.ADD || t.getOperation()== Token.Operation.SUB)
            return 0;
        if (t.getOperation()== Token.Operation.MUL || t.getOperation()== Token.Operation.DIV || t.getOperation()== Token.Operation.MOD)
            return 1;
        if (t.getOperation()== Token.Operation.POW)
            return 2;
        return 3;
    }

    private void printFun(Token.Operation fun){
        if (fun == Token.Operation.MOD)
            System.out.print("%");
        else if (fun == Token.Operation.MUL) {
            System.out.print("*");
        }else if (fun == Token.Operation.DIV) {
            System.out.print("/");
        }else if (fun == Token.Operation.ADD) {
            System.out.print("+");
        }else if (fun == Token.Operation.POW) {
            System.out.print("^");
        }else if (fun == Token.Operation.SUB) {
            System.out.print("-");}
    }


    /**
    * This method prints the Abstract Syntax Tree (AST) in infix notation.
    * 
    * @param tree The root of the AST to be printed.
    * 
    * The method works as follows:
    * - If the tree is a leaf, it prints the number stored in the node.
    * - If the tree is not a leaf, it calculates the precedence value of the operator stored in the node and the operators stored in the left and right children (if they exist).
    * - If the precedence value of the root operator is greater than the precedence value of the left child operator, it prints a left parenthesis before printing the left subtree.
    * - It then prints the left subtree (recursively calling `printTree`), the root operator, and the right subtree (recursively calling `printTree`).
    * - If the precedence value of the root operator is greater than the precedence value of the right child operator, it prints a right parenthesis after printing the right subtree.
    */
    public void printTree(BinaryTree tree){
        int leftChildValue,rootValue,rightChildValue;
        boolean leftPar=false,rightPar=false;

        if(tree!=null && tree.isLeaf()){
            System.out.print(tree.getNode().getNum());
        }else if (tree!=null){
            rootValue=operatorPrecedenceValue(tree.getNode());
            if (tree.getLeft()!=null) {
                leftChildValue = operatorPrecedenceValue(tree.getLeft().getNode());
                leftPar = rootValue>leftChildValue;
            }
            if (tree.getRight()!=null) {
                rightChildValue = operatorPrecedenceValue(tree.getRight().getNode());
                rightPar = rootValue>rightChildValue;
            }
            if (leftPar)
                System.out.print("(");
            printTree(tree.getLeft());
            if (leftPar)
                System.out.print(")");
            printFun(tree.getNode().getOperation());
            if (rightPar)
                System.out.print("(");
            printTree(tree.getRight());
            if (rightPar)
                System.out.print(")");
        }
    }
}
