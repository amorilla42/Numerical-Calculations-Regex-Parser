public class BinaryTree {
    private Token node;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(Token node){
        this.node=node;
        this.right=null;
        this.left=null;
    }

    public Token getNode() {
        return node;
    }

    public void setNode(Token node) {
        this.node = node;
    }

    public BinaryTree getLeft() {return left;}

    public void setLeft(BinaryTree left) {this.left = left;}

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public Boolean isLeaf(){
        return (this.getLeft() == null && this.getRight() == null);
    }

    public boolean insertInAST(Token elem) {

        if (this.isLeaf()) {
            this.setLeft(new BinaryTree(elem));
            return true;
        } else if (this.getLeft().getNode().getType()==Token.Type.NUM && this.getRight()==null) {
            this.setRight(new BinaryTree(elem));
            return true;
        } else if (this.getRight()!=null && this.getRight().getNode().getType()==Token.Type.FUN) {
            return this.getRight().insertInAST(elem);
        } else if (this.getLeft().getNode().getType()==Token.Type.FUN && this.getRight()==null) {
            boolean hasBeenInserted;
            hasBeenInserted = this.getLeft().insertInAST(elem);
            if(!hasBeenInserted)
                this.setRight(new BinaryTree(elem));
            return true;
        }
        return false;
    }
}
