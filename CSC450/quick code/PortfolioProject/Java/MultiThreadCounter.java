package PortfolioProject.Java;

public class MultiThreadCounter extends Thread{
    private static int count = 0;

    //create a method to increment count 20 times
    public synchronized void countUp(){
        for (int i = 0; i < 20; i++){
                System.out.println("Counting up on " + getId() + ": " + count++);
            }
        }//end countUp method

    //create a method to decrement count until it is 0
    public synchronized void countDown(){
        for (int i = 0; i < 20; i++){
                System.out.println("Counting down on " + getId() + ": " + count--);
            }
        }//end countDown method

    public static void main(String[] args) {
        MultiThreadCounter counter1 = new MultiThreadCounter();
        MultiThreadCounter counter2 = new MultiThreadCounter();
        counter1.start();
        counter2.start();
    }//end main method
}//end MultiThreadCounter class

