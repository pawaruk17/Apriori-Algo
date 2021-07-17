package product;

import java.util.Scanner;

class App{
    public static void main(String[] args) {
        
   
   Scanner sc = new Scanner(System.in);
   String str = sc.nextLine();
   String[] arr = str.split("");
   int n = arr.length;
   int m = (int) Math.pow(2,n);
   System.out.println(n+" "+m);
   for(int j=0;j<m;j++){
   
   }
}
}