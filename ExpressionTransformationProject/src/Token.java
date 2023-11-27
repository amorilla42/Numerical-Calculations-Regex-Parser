public class Token {
    public enum Type {FUN,NUM,PAR_A,PAR_B,COMMA};
    public enum Operation {ADD,SUB,MUL,DIV,MOD,POW};
    private Type type;
    private Operation operation;
    private String num;

    public Token(Type type, Operation operation) {
        this.type = type;
        this.operation = operation;
    }

    public Token(Type type){
        this.type=type;
    }

    public Token(Type type, String num){
        this.type=type;
        this.num=num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return  type +
                "," + operation +
                ' ';
    }
}
