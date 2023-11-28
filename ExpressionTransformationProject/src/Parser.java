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
                root = insertInAST(tokens.get(i),root,root);
                i++;
            } else if (nt == NonTerminals.FUN && tokens.get(i).getType()== Token.Type.FUN) {
                if(root==null)
                    root = new BinaryTree(tokens.get(i));
                else
                    root = insertInAST(tokens.get(i),root,root);
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

    private BinaryTree insertInAST(Token elem, BinaryTree root, BinaryTree bin){
        BinaryTree ptr = bin;
        BinaryTree node = new BinaryTree(elem);

        if (bin.isLeaf() && elem.getType()==Token.Type.FUN) {
            ptr.setLeft(node);
        }else if (ptr.getLeft()==null && elem.getType()== Token.Type.NUM) {
            ptr.setLeft(node);
        } else if (ptr.getLeft().getNode().getType()==Token.Type.NUM && ptr.getRight()==null) {
            ptr.setRight(node);
        } else if (ptr.getRight()!=null && ptr.getRight().getNode().getType()==Token.Type.FUN) {
            insertInAST(elem,root,bin.getRight());
        } else if (ptr.getLeft().getNode().getType()==Token.Type.FUN && ptr.getRight()==null) {
            insertInAST(elem,root,bin.getLeft());
        } else if (ptr.getLeft().getNode().getType()==Token.Type.FUN && ptr.getLeft().getRight().getNode().getType()== Token.Type.FUN) {
            insertInAST(elem,root,bin.getLeft().getRight());
        } else if (ptr.getLeft().getNode().getType()== Token.Type.NUM && ptr.getRight().getNode().getType()== Token.Type.NUM){
            root.setRight(node);
        }
        return bin;
    }

    private int operatorPrecedenceValue(Token t){
        if (t.getOperation()== Token.Operation.ADD || t.getOperation()== Token.Operation.SUB)
            return 0;
        if (t.getOperation()== Token.Operation.MUL || t.getOperation()== Token.Operation.DIV || t.getOperation()== Token.Operation.MOD)
            return 1;
        if (t.getOperation()== Token.Operation.POW)
            return 2;
        return 3;
    }

    public void printTree(BinaryTree tree){
        int rootValue,childValue;

        if (tree.getNode().getType()==Token.Type.NUM) {
            System.out.print(tree.getNode().getNum());
        }else {
            rootValue = operatorPrecedenceValue(tree.getNode());
            if (tree.getLeft()!=null && tree.getLeft().getNode().getType()==Token.Type.FUN) {
                childValue = operatorPrecedenceValue(tree.getLeft().getNode());
                if (rootValue > childValue) {
                    System.out.print('(');
                    printTree(tree.getLeft());
                    System.out.print(')');
                }
            }
            if (tree.getRight()!=null && tree.getRight().getNode().getType()==Token.Type.FUN){
                childValue = operatorPrecedenceValue(tree.getRight().getNode());
                if (rootValue > childValue){
                    System.out.print('(');
                    printTree(tree.getRight());
                    System.out.print(')');
                }
            }
        } 
    }
}
