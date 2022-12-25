import java.util.Scanner;

public class ExtendedEucledianAlgorithm {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter num:-");
        int num=sc.nextInt();
        System.out.println("Enter modulous:-");
        int mod=sc.nextInt();
        if(num<mod){
            int temp=num;
            num=mod;
            mod=temp;
        }
        int r1=num;
        int r2=mod;
        int t1=0;
        int t2=1;
        int q;
        int r;
        int t;
        while(r2>0){
            q=r1/r2;
            r=r1-q*r2;
            r1=r2;
            r2=r;
            t=t1-q*t2;
            t1=t2;
            t2=t;

            if(r1==1) {
                System.out.println("Inverse of num is:- "+t1);
                break;
            }
        }
    }
}
