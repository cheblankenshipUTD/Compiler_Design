class BinOp extends Token {

    String op;

    public BinOp(String op) {
        this.op = op;
    }

    public boolean isArithmetic() {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    public boolean isRelational() {
        return op.equals("<") || op.equals("<=") || op.equals(">") || op.equals(">=") || op.equals("==") || op.equals("<>");
    }

    public boolean isLogical() {
        return op.equals("||") || op.equals("&&");
    }

    public String toString() {
        return op;
    }

}