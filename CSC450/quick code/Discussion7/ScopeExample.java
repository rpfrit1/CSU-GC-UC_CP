package Discussion7;

public class ScopeExample {
    private String priClssString;//this String is accessible as long as there is an object of ScopeExample type
    //If only one method manipulates and uses it, it should be created as a method variable instead

    public static void main(String[] args) {
        ScopeExample scopeExample = new ScopeExample();
        scopeExample.priClssString = "Hello";
        for(int i = 0; i < 5; i++){
            scopeExample.priClssString += " World" + i;//the i variable has scope only within the loop
        }//end for loop
        System.out.println(scopeExample.priClssString);
    }//end main method

    //create setters and getters
    public String getPriClssString() {
        return priClssString;
    }//end getPriClssString method
   
    public void setPriClssString(String priClssString) {
        this.priClssString = priClssString;
    }//end setPriClssString method
   
    public void printPriClssString(){
        System.out.println(priClssString);
    }//end printPriClssString method

    //create toString
    @Override
    public String toString() {
        return "ScopeExample{" +
                "priClssString='" + priClssString + '\'' +
                '}';
    }//end toString method

    //create equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScopeExample that = (ScopeExample) o;//the variable "that" has a scope that ends at the return.
        return priClssString.equals(that.priClssString);
    }//end equals method

    //create hashCode
    @Override
    public int hashCode() {
        return priClssString.hashCode();
    }//end hashCode method
    
    //create constructors
    public ScopeExample() {
        this(null);
    }//end ScopeExample constructor

    public ScopeExample(String priClssString) {
        this.priClssString = priClssString;
    }//end ScopeExample constructor
}//end ScopeExample class