//Program.java

import java.util.ArrayList;

class Program extends Token
{
//    private Expr expr;
////    Constructor
//    public Program(Expr e) {
//        expr = e;
//    }
//
//    public String toString(int t) {
//        return "Program:\n" + expr.toString(t+1) + "\n";
//    }

    private ArrayList<Stmt> stmts;
    // Constructor
    public Program(ArrayList<Stmt> sts) {
        this.stmts = sts;
    }

    public String toString(int t) {

        String list = "";
        for(Stmt st: stmts) {
            list += st.toString(t) + "\n";
        }
        list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);

        return "Program:\n" + list + "\n";
    }

}