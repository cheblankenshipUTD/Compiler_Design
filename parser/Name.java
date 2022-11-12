
class Name extends Token {

    String id;
    Expr index;

    public Name(String id) {
        this.id = id;
        this.index = null;
    }

    public Name(String id, Expr e) {
        this.id = id;
        this.index = e;
    }

    public String toString(int t) {
        return id + ( hasIndex() ? "[" + index.toString(t) + "]" : "" );
    }

    private boolean hasIndex() {
        return index != null;
    }

}