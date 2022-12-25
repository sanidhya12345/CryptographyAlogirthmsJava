import java.util.Scanner;

public class EucledianAlgorithm {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        if(num1<num2){
            int temp=num1;
            num1=num2;
            num2=temp;
        }
        int r1=num1;
        int r2=num2;
        int q;
        int r;
        while(r2>0){
            q=r1/r2;
            r=r1-q*r2;
            r1=r2;
            r2=r;
        }
        System.out.println("GCD of Two Numbers:- "+r1);
    }
}
