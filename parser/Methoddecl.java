import java.util.ArrayList;


class Methoddecl extends Token
{
    ArrayList<Argdecl> argdecls;
    ArrayList<Fielddecl> fielddecls;
    ArrayList<Stmt> stmts;
    String type, id;
    boolean hasSemi;



    public Methoddecl(String type, String id, ArrayList<Argdecl> as, ArrayList<Fielddecl> fs, ArrayList<Stmt> sts, boolean semi)
    {
        this.type = type;
        this.id = id;
        this.argdecls = as;
        this.fielddecls = fs;
        this.stmts = sts;
        this.hasSemi = semi;
    }


    public String toString(int t)
    {

        String result = "";
        String args = "";
        for(Argdecl a: this.argdecls) {
            args += a.toString() + ", ";
        }
        args = args.substring(0, args.length() > 0 ? args.length()-2 : 0);

        result += getTabs(t) + this.type + " " + id + "(" + args + ")" + " {\n";

        for(Fielddecl f: this.fielddecls) {
            result += f.toString(t+1) + "\n";
        }

        for(Stmt st: this.stmts) {
            result += st.toString(t+1) + "\n";
        }
        result += getTabs(t) + "}" + ( this.hasSemi ? ";" : "" );

        return result;
    }


}