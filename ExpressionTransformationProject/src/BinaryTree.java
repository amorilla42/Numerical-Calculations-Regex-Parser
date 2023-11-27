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

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public Boolean isLeaf(){
        return (this.getLeft()==null && this.getRight()==null);
    }
}
