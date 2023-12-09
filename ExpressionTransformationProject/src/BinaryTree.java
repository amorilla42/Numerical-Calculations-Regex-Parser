public class BinaryTree {
    private Token node;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(Token node){
        this.node=node;
        this.right=null;
        this.left=null;
    }

    public Token getNode() {return node;}

    public void setNode(Token node) {this.node = node;}

    public BinaryTree getLeft() {return left;}

    public void setLeft(BinaryTree left) {this.left = left;}

    public BinaryTree getRight() {return right;}

    public void setRight(BinaryTree right) {this.right = right;}

    /**
     * Check if the node has no children
     * @return true if the node is a leaf, false otherwise
     **/
    public Boolean isLeaf(){
        return (this.getLeft() == null && this.getRight() == null);
    }

    /**
    * This method is used to insert a Token into the Abstract Syntax Tree (AST).
    * 
    * @param elem The Token object to be inserted into the AST.
    * 
    * The method works as follows:
    * - If the current node is a leaf (i.e., it has no children), it creates a new BinaryTree node with the Token as the left child.
    * - If the left child of the current node is a NUM type Token and the right child is null, it creates a new BinaryTree node with the Token as the right child.
    * - If the right child of the current node is a FUN type Token, it recursively tries to insert the Token into the right subtree.
    * - If the left child of the current node is a FUN type Token and the right child is null, it tries to insert the Token into the left subtree. 
    *   If the insertion fails, it creates a new BinaryTree node with the Token as the right child.
    * 
    * @return true if the Token has been inserted successfully, false otherwise.
    */
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
