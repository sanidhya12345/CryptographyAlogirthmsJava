import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSASIGNATURE {
    static int power(int x, int y, int p)
    {
        int res = 1;

        x = x % p;
        if (x == 0)
            return 0;

        while (y > 0)
        {
            if ((y & 1) != 0)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }
    static boolean isPrime(int n)
    {
        if (n <= 1)
        {
            return false;
        }
        if (n <= 3)
        {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0)
        {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
            {
                return false;
            }
        }

        return true;
    }
    static int modInverse(int A, int M)
    {

        for (int X = 1; X < M; X++)
            if (((A % M) * (X % M)) % M == 1)
                return X;
        return 1;
    }
    static int gcd(int num1,int num2){
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
        return r1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value of p:-");
        int p=sc.nextInt();
        while (!isPrime(p)){
            System.out.println("re-enter p value:-");
            p=sc.nextInt();
        }
        System.out.println("Enter the value of q:-");
        int q=sc.nextInt();
        while (!isPrime(q)){
            System.out.println("re-enter q value:-");
            q=sc.nextInt();
        }
        while (p==q){
            System.out.println("Please re enter any p or q value:-");
            q=sc.nextInt();
        }
        int n=p*q;
        int phin=(p-1)*(q-1);
        List<Integer> e_values=new ArrayList<>();
        List<Integer> d_values=new ArrayList<>();
        for(int i=1;i<=phin;i++){
            if(gcd(i,phin)==1){
                e_values.add(i);
            }
        }
        System.out.println("E-values:- "+e_values);
        for(Integer i:e_values){
            d_values.add(modInverse(i,phin));
        }
        System.out.println("D-values:- "+d_values);
        System.out.println("select e value:- ");
        int e=sc.nextInt();
        System.out.println("select d value:- ");
        int d=sc.nextInt();
        System.out.println("Enter the msg:-");
        int msg=sc.nextInt();
        int s=power(msg,d,n);
        System.out.println("Signature Value:- "+s);
        int sdesh=power(s,e,n);
        System.out.println("S` Value:-"+sdesh);
        if(sdesh==msg){
            System.out.println("Verified Successfully....");
        }
        else{
            System.out.println("Not verified");
        }
    }
}
