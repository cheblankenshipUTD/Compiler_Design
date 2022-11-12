import java.util.ArrayList;


class Memberdecls extends Token
{

    ArrayList<Fielddecl> fielddecls;
    ArrayList<Methoddecl> methoddecls;


    public Memberdecls(Fielddecl f, Memberdecls mds) {
        mds.fielddecls.add(0, f);
        this.fielddecls = mds.fielddecls;
        this.methoddecls = mds.methoddecls;
    }

    public Memberdecls(ArrayList<Fielddecl> fs, ArrayList<Methoddecl> ms)
    {
        this.fielddecls = fs;
        this.methoddecls = ms;
    }

    public Memberdecls(ArrayList<Methoddecl> ms)
    {
        this.fielddecls = new ArrayList<Fielddecl>();
        this.methoddecls = ms;
    }

    public String toString(int t)
    {
        String result = "";
        for(Fielddecl f: this.fielddecls) {
            result += f.toString(t) + "\n";
        }
        for(Methoddecl m: this.methoddecls) {
            result += m.toString(t) + "\n";
        }
        return result;
    }

}
