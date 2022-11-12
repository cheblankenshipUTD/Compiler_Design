import java.util.ArrayList;

class Expr extends Token {

    int thisId;
    int intLit;
    String id;
    String charStr;
    float floatLit;
    boolean bool;
    Expr expr[];
    BinOp binOp;
    String unaryOp;
    String castType;
    ArrayList<Expr> args;
    Name name;
    boolean isMethod = false;
    boolean castFlag = false;
    String value;
    Expr inner;
    String type;
//    FunCall funCall;


    // Integer Literal
    public Expr(int n) {
        this.thisId = 0;
        this.value = String.valueOf(n);
        this.type = "intlit";
    }

    // Float Literal
    public Expr(double n) {
        this.thisId = 1;
        this.value = String.valueOf(n);
        this.type = "floatlit";
    }

    // Boolean
    public Expr(boolean bool) {
        this.thisId = 2;
        this.value = bool ? "true" : "false";
        this.type = "boolean";
    }

    // Char Literal
    public Expr(char c) {
        this.thisId = 3;
        this.value = String.valueOf(c);
        this.type = "charlit";
    }

    // id ... this needs to be update to Name -> id | id []
    public Expr(Name n) {
        this.thisId = 4;
        this.name = n;
    }

    // id (args)
    // String ID
    // ArrayList<Expr>
    public Expr(String id, ArrayList<Expr> args) {
        this.thisId = 5;
        this.id = id;
        this.args = args;
    }

    // id ()
    // String ID
    // boolean method
    public Expr(String id, boolean isMethod) {
        this.value = id;
        this.thisId = 6;
    }


    // String Literal
    // String strValue
    // String type
    public Expr(String strValue, String type) {
        this.thisId = 7;
        this.value = strValue;
        this.type = type;
    }

    // Unaty Operation
    // Expr e
    // String op
    public Expr(Expr e, String op) {
        this.thisId = 8;
        this.unaryOp = op;
        this.expr = new Expr[]{e};
    }

    // (type) expr
    // String type
    // Expr e
    // boolean castFlag
    public Expr(String ct, Expr e, boolean castFlag) {
        this.thisId = 9;
        this.castType = ct;
        expr = new Expr[]{e};
        this.castFlag = castFlag;
    }

    // BinOp
    public Expr(Expr e1, BinOp bOp, Expr e2) {
        this.thisId = 10;
        expr = new Expr[]{e1, e2};
        this.binOp = bOp;
    }

    // TernaryOp: expr ? expr : expr
    public Expr(Expr e1, Expr e2, Expr e3) {
        this.thisId = 11;
        expr = new Expr[]{e1, e2, e3};
    }

    // ( Expr )
    public Expr(Expr e) {
        this.thisId = 12;
        expr = new Expr[]{e};
    }


    public String toString(int t) {

        String ret = "";

        if (thisId == 0) {
            ret = this.value;
        }
        else if(thisId == 1) {
            ret = this.value;
        }
        else if(thisId == 2) {
            ret = this.value;
        }
        else if(thisId == 3) {
            ret = this.value;
        }
        else if(thisId == 4) {
            ret = this.name.toString(0);
        }
        else if(thisId == 5) { // update later when args is done
            String tmp = "";
            for (Expr e: args) {
                tmp += e.toString(0) + ", ";
            }
            tmp = tmp.substring(0, tmp.length() > 0 ? tmp.length() - 2 : 0 );
            ret = this.id + "(" + tmp + ")";
        }
        else if(thisId == 6) {
            ret = this.value+"()";
        }
        else if(thisId == 7) {
            ret = this.value;
        }
        else if(thisId == 8) {
            ret = "("+this.unaryOp + this.expr[0].toString(0)+")";
        }
        else if(thisId == 9) {
            ret = "(" + "("+ this.castType +")" + this.expr[0].toString(0) + ")";
        }
        else if(thisId == 10) {
            ret = "("+this.expr[0].toString(0)+" "+this.binOp+" "+this.expr[1].toString(0)+")";
        }
        else if(thisId == 11) {
            ret = "("+this.expr[0].toString(0)+" ? "+this.expr[1].toString(0)+" : "+this.expr[2].toString(0)+")";
        }
        else if(thisId == 12) {
            ret = "(" +this.expr[0].toString(0)+ ")";
        }


        return getTabs(t) + ret;
    }


//    public FullType typeCheck() throws ExampleException {
//        FullType ret;
//        if (thisId == 0)
//            ret = binOp.typeCheck();
//        else if (thisId == 1)
//            ret = new FullType("var");
//        else if (thisId == 2) {
//            FullType thisType = table.getType(id);
//            datum = thisType;
//            if (thisType == null)
//                throw new ExampleException("Error: " + id + " not declared");
//            ret = thisType;
//        } else if (thisId == 3)
//            ret = new FullType("varf");
//        else if (thisId == 4) {
//            FullType thisType = table.getType(id);
//            datum = thisType;
//            FullType innerType = inner.typeCheck();
//            if (thisType == null || !thisType.isArray)
//                throw new ExampleException("Error: " + id + " not declared");
//            if (!innerType.baseType.equals("var"))
//                throw new ExampleException("Error: varf in array access");
//            ret = new FullType(thisType.baseType);
//        } else {
//            FullType thisType = funCall.typeCheck();
//            if (thisType == null || thisType.baseType.equals("void"))
//                throw new ExampleException("Error: void function in expression or undeclared");
//            ret = thisType;
//        }
//    }

}
