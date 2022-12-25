import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ElgamalSignatureScheme {
    static ArrayList<Integer> primitiveRootList=new ArrayList<>();
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
    static void findPrimefactors(List<Integer> s, int n)
    {
        while (n % 2 == 0)
        {
            s.add(2);
            n = n / 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i = i + 2)
        {
            while (n % i == 0)
            {
                s.add(i);
                n = n / i;
            }
        }
        if (n > 2)
        {
            s.add(n);
        }
    }
    private static int calculatePhi(int n){
        if(isPrime(n)) return n-1;
        List<Integer> listofPrime=new ArrayList<>();
        findPrimefactors(listofPrime,n);
        HashMap<Integer,Integer> map=new HashMap<>();
        for(Integer i:listofPrime){
            if(!map.containsKey(i)){
                map.put(i,1);
            }
            else{
                map.put(i,map.get(i)+1);
            }
        }
        int pro=1;
        for(int i:map.keySet()){
            if(map.get(i)>1){
                int calc=(int)Math.abs(Math.pow(i,map.get(i))-Math.pow(i,map.get(i)-1));
                pro*=calc;
            }
            else{
                pro*=(i-1);
            }
        }
        return pro;
    }
    static void primtiveRootTable(int n){
        int phi=calculatePhi(n);
        int [][] matrix=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                int calc=power(i,j,n);
                matrix[i][j]=calc;
            }
        }
        HashMap<Integer,Integer> orderOfElement=new HashMap<>();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(matrix[i][j]==1){
                    orderOfElement.put(i,j);
                    break;
                }
            }
        }
        for(Integer ele:orderOfElement.keySet()){

            if(orderOfElement.get(ele)==phi){
                primitiveRootList.add(ele);
            }
        }
        System.out.println(primitiveRootList);
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
        System.out.println("Enter prime number:-");
        int q=sc.nextInt();
        while(!isPrime(q)){
            System.out.println("Enter prime number:-");
            q= sc.nextInt();
        }
        primtiveRootTable(q);
        System.out.println("Select any primitive root of q:-");
        int alpha=sc.nextInt();
        System.out.println("Enter private key less than q:-");
        int xa=sc.nextInt();
        int ya=power(alpha,xa,q);
        System.out.println("Public Key:- "+"{"+q+","+alpha+","+ya+"}");
        System.out.println("Private Key:- "+xa);
        System.out.println("Enter msg:-");
        int msg=sc.nextInt();

        List<Integer> listofrandomvalues=new ArrayList<>();
        for (int i=1;i<=q-1;i++){
            if(gcd(i,q-1)==1) listofrandomvalues.add(i);
        }
        System.out.println("Random Integer List having gcd 1:-");
        System.out.println(listofrandomvalues);
        System.out.println("Select random integer k");
        int k=sc.nextInt();
        int s1=power(alpha,k,q);
        int kinv=modInverse(k,q-1);
        int cal=((msg-xa*s1)*kinv)%(q-1);
        if(cal<0){
            cal+=(q-1);
        }
        System.out.println("Signature S1:-"+s1);
        System.out.println("Signature S2:-"+cal);
        System.out.println("Now time for verification:-");
        int v1=power(alpha,msg,q);
        System.out.println("Value of v1:-"+v1);
        int v2=(power(ya,s1,q)*power(s1,cal,q))%q;
        System.out.println("Value of v2:-"+v2);
        if(v1==v2){
            System.out.println("Verified Successfuly:-");
        }
        else{
            System.out.println("Not Verified Successfully:-");
        }
    }
}
