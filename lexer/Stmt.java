import java.util.ArrayList;



class Stmt extends Token {

    Expr expr;
    Stmt stmt;
    ArrayList<Stmt> stmts;
    Stmt elseState;
    Name name;
    boolean opsemi;
    String id;
    String method;
    String unaryOp;
    ArrayList lst;
    ArrayList<Expr> args;
    int thisId;


    // if stmt else ...
    public Stmt(Expr e, Stmt st, Stmt elseState) {
        this.expr = e;
        this.stmt = st;
        this.elseState = elseState;
        this.thisId = 0;
    }

    // while ( expr ) stmt
    public Stmt(Expr e, Stmt st) {
        this.expr = e;
        this.stmt = st;
        this.thisId = 1;
    }


    // name = expr ;
    public Stmt(Name n, Expr e) {
        this.name = n;
        this.expr = e;
        this.thisId = 2;
    }


    // read ( readline ) ;
    // print ( printlist ) ;
    // printline ( printlinelist ) ;
    public Stmt(String method, ArrayList lst) {
        this.method = method;
        this.lst = lst;
        if(method == "read")
            this.thisId = 3;
        else if(method == "print")
            this.thisId = 4;
        else
            this.thisId = 5;
    }


    // id ( ) ;
    public Stmt(String id) {
        this.id = id;
        this.thisId = 6;
    }

    // id ( args )
    public Stmt(String id, ArrayList<Expr> argList, boolean funcFlag) {
        this.id = id;
        this.args = argList;
        this.thisId = 7;
    }

    // return ;
    public Stmt() {
        this.thisId = 8;
    }


    // return expr ;
    public Stmt(Expr e) {
        this.expr = e;
        this.thisId = 9;
    }


    // var ++/--
    public Stmt(Name n, String unaryOp) {
        this.name = n;
        this.unaryOp = unaryOp;
        this.thisId = 10;
    }


    // { stmts } ;
    public Stmt(ArrayList<Stmt> statements, boolean semi) {
        this.opsemi = semi;
        this.stmts = statements;
        this.thisId = 11;
    }



    public String toString(int t) {

        String ret = "";

        if(this.thisId == 0) {
            ret = "if (" + this.expr.toString() + ")\n" +
                  (this.thisId == 2 ? stmt.toString(t) : getTabs(t)+"{\n"+stmt.toString(t+1)+"\n"+getTabs(t)+"}") +
                  (this.elseState != null ? "\n"+getTabs(t)+"else\n"+(this.elseState.thisId == 2 ? elseState.toString(t) : getTabs(t) + "{\n" + elseState.toString(t+1) + "\n" + getTabs(t) + "}") : "" );
        }
        else if (this.thisId == 1) {
            ret = "while (" + expr.toString(t) + ")\n" + stmt.toString(stmt.thisId == 2 ? t : t+1) + "\n";
        }
        else if(this.thisId == 2) {
            ret = this.name.toString(t) + " = " + this.expr.toString(t) + ";";
        }
        else if(this.thisId == 3) {
             String list = "";
             for(Name n: (ArrayList<Name>)lst) {
                 list += n.toString(t) + ", ";
             }
             list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
             ret = this.method + "(" + list + ");";
        }
        else if(this.thisId == 4) {
             String list = "";
             for(Expr e: (ArrayList<Expr>)lst) {
                 list += e.toString(t) + ", ";
             }
             list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
             ret = this.method + "(" + list + ");";
        }
        else if(this.thisId == 5) {
             String list = "";
             for(Expr e: (ArrayList<Expr>)lst) {
                 list += e.toString(t) + ", ";
             }
             list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
             ret = this.method + "(" + list + ");";
        }
        else if(this.thisId == 6) {
            ret = this.id + "();";
        }
        else if(this.thisId == 7) {
            String list = "";
            for (Expr e: args) {
                list += e.toString() + ", ";
            }
            list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
            ret = this.id + "(" + list + ");";
        }
        else if(this.thisId == 8){
            ret = "return;";
        }
        else if(this.thisId == 9){
            ret = "return " + this.expr.toString(t) + ";";
        }
        else if(this.thisId == 10) {
            ret = name.toString(t) + this.unaryOp + ";";
        }
        else if(this.thisId == 11) {
            String tmp = "";
            for (Stmt st: stmts) {
                tmp += st.toString(t+1) + "\n";
            }
            ret = "{\n" + tmp + getTabs(t) + "}";
        }


        return getTabs(t) + ret;
    }


}