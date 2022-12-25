import java.util.*;

public class FindAllPrimitiveRoots {
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
        System.out.println("<--Primitive Root Table-->");
        for (int i = 1; i <n ; i++) {
            for(int j=1;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();

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
        System.out.println("Order of each element:- ");
        System.out.println(orderOfElement);
        for(Integer ele:orderOfElement.keySet()){

            if(orderOfElement.get(ele)==phi){
                primitiveRootList.add(ele);
            }
        }
        System.out.println(primitiveRootList);
    }
    private static int countNumberOfPrimitiveRoots(){
        return primitiveRootList.size();
    }
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        System.out.println("Enter any number:-");
        int n=sc.nextInt();
        System.out.println("Calculated Phin:- "+calculatePhi(n));
        primtiveRootTable(n);
        System.out.println("No. of primitive roots:-"+countNumberOfPrimitiveRoots());
    }
}
