import java.util.List;
import java.util.Stack;
import static java.lang.System.exit;

public class Parser {

    public enum NonTerminals {S,EXP,PAR_A,PAR_B,COMMA,FUN};
    public BinaryTree parse(List<Token> tokens){
        Stack<NonTerminals> st = new Stack<>();
        st.push(NonTerminals.S);
        BinaryTree tree = null;
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
                //aqui lo del abstract syntax tree
                //NO SE SABE SI ESTA BIEN
                if (tree.isLeaf()) {
                    tree.setLeft(new BinaryTree(tokens.get(i)));
                } else if (tree.getLeft()!=null && tree.getRight()==null) {
                    tree.setRight(new BinaryTree(tokens.get(i)));
                }
                i++;
            } else if (nt == NonTerminals.FUN && tokens.get(i).getType()== Token.Type.FUN) {
                //NO SE SABE SI ESTA BIEN
                if(tree==null) {
                    root = new BinaryTree(tokens.get(i));
                    tree = root;
                } if ()

                }
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
        //MIRAR SI LOS TOKENS SE HAN MIRAO TODOS
        return tree;
    }

}
