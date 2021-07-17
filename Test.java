package product;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class A extends Thread {
    String str="";
   
        int gameNo= 56;
        int flag=0;
       
    public void run(){
    synchronized(str){
        try {
            
            for(int i=0;i<3;i++){
                if(flag==1)
                throw new InterruptedException();
            hilo(Thread.currentThread().getName());
            str.notify();
            str.wait();
            str.notify();
            Thread.sleep(500);
            if(flag==1)
                throw new InterruptedException();
            hilo(Thread.currentThread().getName());
            }
        } catch (InterruptedException ex) {
            System.out.println("Game Finished!!!");
        }
        
    }
    }

    private void hilo(String name) {
        
           System.out.println("Player"+name+">");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            if(input==gameNo){
                
                System.out.println("You Win!");
                flag = 1;
            }else if(input<gameNo){
                
                System.out.println("Higher!");
            }else if(input>gameNo){
               
                System.out.println("Lower!");
            } 
       }
    
}

public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread(new A(),"A");
        Thread t2 = new Thread(new A(),"B");
        t1.start();
        t2.start();
    }
}