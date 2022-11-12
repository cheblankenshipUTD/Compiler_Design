
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

        return id + ( (this.index != null) ? "[" + this.index.toString(t) + "]" : "" );

    }

}