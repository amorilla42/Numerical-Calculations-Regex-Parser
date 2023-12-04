import java.util.List;
import java.util.Stack;
import static java.lang.System.exit;

public class Parser {

    public enum NonTerminals {S,EXP,PAR_A,PAR_B,COMMA,FUN};
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
                exit(-1);
            }
        }
        if (i != tokens.size() ) {// A VER SI LA CONDICION ESTA BIEN MUTANTE
            System.out.println("Syntax Error");
            exit(-1);
        }
        return root;
    }
/*
    private boolean inssertInAST(Token elem, BinaryTree bin){
        BinaryTree ptr = bin;
        BinaryTree node = new BinaryTree(elem);
        if(bin==null)
            return false;
        if (bin.isLeaf()) {
            ptr.setLeft(node);
            return true;
        } else if (ptr.getLeft().getNode().getType()==Token.Type.NUM && ptr.getRight()==null) {
            ptr.setRight(node);
            return true;
        } else if (ptr.getRight()!=null && ptr.getRight().getNode().getType()==Token.Type.FUN) {
            return insertInAST(elem,bin.getRight());
        } else if (ptr.getLeft().getNode().getType()==Token.Type.FUN && ptr.getRight()==null) {
            boolean hasBeenInserted;
            hasBeenInserted = insertInAST(elem,bin.getLeft());
            if(!hasBeenInserted)
                ptr.setRight(new BinaryTree(elem));
            return true;
        }
        return false;
    }
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
