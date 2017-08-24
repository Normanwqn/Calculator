
public class Calculator {
    public static void main(String[] args){
        //Initialisation
        String s= args[0];
        double[] n = new double[10];
        char[] op = new char[9];
        int[] priority = new int[9];
        Boolean[] validity = new Boolean[10];
        for (int i=0;i<validity.length;i++){
            validity[i] = true;
        }
        int i = 0;
        int j = 0;
        int loc = 0;
        Boolean overide = false;
        //Extracting the numbers and operators
        while (i<=s.length()-1){
            if (isDigit(s.charAt(i)) && (s.charAt(i) != ' ')) {
                n[j] = n [j] * 10 + Integer.parseInt(Character.toString(s.charAt(i)));
                //System.out.println(n[j]);
                //System.out.println(j);
                i++;
            }
            else if (s.charAt(i) != ' ') {
                if (s.charAt(i) == '(') {
                    overide = true;
                } else if (s.charAt(i) == ')') {
                    overide = false;
                }
                //assign priorities to the operators
                if (isOp(s.charAt(i)) && (! overide)) {
                    //System.out.println("C:" +s.charAt(i) + " j:" + j + " i:" + i);
                    switch (s.charAt(i)) {
                        case '+': priority[j] = 1;
                            break;
                        case '-': priority[j] = 1;
                            break;
                        case '*': priority[j] = 0;
                            break;
                        case '/': priority[j] = 0;
                            break;
                        default: priority[j] = 100;
                    }
                } else if (overide) {
                    priority[j] = 0;
                }
                if (isOp(s.charAt(i))) {
                    op[j] = s.charAt(i);
                    j++;
                }
                i++;
                /*System.out.println("Op: "+ op[0]+ " "+ op[1] + " "+ op[2] + " " + op[3] );
                 System.out.println("Prior: "+ priority[0]+ " "+ priority[1] + " "+ priority[2] + " " + priority[3] + " " + priority[4]);*/
            }
        }
        i = 0;
        //System.out.println("j " + j);
        //Performing calculations
        int nextValid = 0;
        while (i<=j-1) {
            loc = findMin(j, priority);  //Find the operator with the highest priority
            nextValid = findValid(loc, j, validity);  //Find the next valid number
            //System.out.println(nextValid);
            n[nextValid] = calculate(n[loc], n[nextValid], op[loc]); //Calculate
            n[loc] = 0;
            validity[loc] = false;
            priority[loc] = 100;
            i++;
        }
        /*System.out.println(op);
         System.out.println(n);
         System.out.println(priority[0] + " " + priority[1]);*/
        System.out.println("= " + n[j]);
    }
    public static boolean isDigit(char a){
        if ((a == '0') || (a == '1') || (a == '2') || (a == '3') || (a == '3') || (a == '4') || (a == '5') || (a == '6') || (a == '7') || (a == '8') || (a == '9')) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isOp(char a){
        if ((a == '+') || (a == '-') || (a == '*') || (a == '/')) {
            return true;
        } else {
            return false;
        }
    }
    
    public static int findMin(int length, int[] p){
        int min_p = 100;
        int min_i = 100;
        int ii = 0;
        while (ii <= length - 1) {
            if (min_p > p[ii]) {
                min_p = p[ii];
                min_i = ii;
                
            } 
            ii++;
        }
        return min_i;
    }
    
    public static int findValid(int pos, int length, Boolean[] v){
        int ii = pos + 1;
        int min = 100;
        while (ii <= length) {
            if ((min > ii) && (v[ii])) {
                min = ii;
            }
            ii++;
        }
        return min;
    }
    
    public static double calculate(double a, double b, char sign){
        switch (sign) {
            case '+': return a+b;
            case '-': return a-b;
            case '*': return a*b;
            case '/': return a/b;
            default: return 0;
        }
    }
}
