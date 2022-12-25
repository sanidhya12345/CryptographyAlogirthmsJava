import java.util.Scanner;

public class MillerRabinPrimalityTest {
    private static void rabin(int n,int a)
    {
        int val=n-1;
        int k=0;
        while(val%2==0){
            k++;
            val=val/2;
        }
        System.out.println("Value of m:- "+val);
        System.out.println("Value of k:-"+k);
        int b= (int) (Math.pow(a,val)%n);
        boolean flag=false;
        System.out.println("Initial Value of b after calculation:-"+b);
        if(b==1) System.out.println("n is prime");
        else{
            for(int i=0;i<k;i++) {
                System.out.print("Iteration "+i+":- ");
                System.out.print(b+"*"+b +" mod "+n+" = "+b*b%n);
                System.out.println();
                b=b*b%n;
                if(b==1){
                    flag=true;
                    break;
                }
                if(b-n==-1){
                    System.out.println(b-n +" mod "+n+" = "+-1);
                    System.out.println("Prime");
                    break;
                }
            }
            if(flag){
                System.out.println("Composite");
            }
        }
        if(flag==false){
            System.out.println("Composite");
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number:-");
        int n=sc.nextInt();
        System.out.println("Enter the base value:-");
        int a=sc.nextInt();
        rabin(n,a);
    }
}
