
class Argdecl extends Token
{

    String type;
    String id;
    boolean isArray;


    public Argdecl(String type, String id, boolean isArray)
    {
        this.type = type;
        this.id = id;
        this.isArray = isArray;
    }


    public String toString()
    {
        return this.type + " " + this.id + " " + (this.isArray ? "[]" : "");
    }



}