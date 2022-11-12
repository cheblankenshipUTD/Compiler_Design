
class Fielddecl extends Token
{
    boolean isFinal;
    String type;
    String id;
    int arrLength;
    Expr optionalEx;
    int declType;


    public Fielddecl(String type, String id, Expr opex, boolean isFinal)
    {
        this.type = type;
        this.id = id;
        this.optionalEx = opex;
        this.isFinal = isFinal;
        this.declType = 0;
    }


    public Fielddecl(String type, String id, int len)
    {
        this.type = type;
        this.id = id;
        this.arrLength = len;
        this.declType = 1;
    }


    public String toString(int t)
    {
        String result = "";
        if(this.declType == 0)
        {
            result += getTabs(t) + (this.isFinal ? "final " : "") + this.type + " " + this.id + (this.optionalEx != null ? " = " + this.optionalEx.toString() : "") + ";";
        }
        else if(this.declType == 1)
        {
            result += getTabs(t) + this.type + " " + "[" + this.arrLength + "];";
        }
        return result;
    }


}