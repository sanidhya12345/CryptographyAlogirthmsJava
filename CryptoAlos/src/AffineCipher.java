import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AffineCipher {
    static HashMap<ArrayList<Integer>,String> hmap=new HashMap<>();
    private static ArrayList<Integer> bruteForceKeyFinder(String [] cipherArray,char [] chars,String plain){
        for(int i:map.keySet()){
            int [] arr=new int[2];
            for(int j=1;j<=26;j++){
                arr[0]=i;
                arr[1]=j;
                ArrayList<Integer> lst=new ArrayList<>();
                for(int k:arr){
                    lst.add(k);
                }
                String ans=decryption(i,j,cipherArray,chars);
                hmap.put(lst,ans);
            }
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(ArrayList<Integer> key:hmap.keySet()){
            if(plain.equals(hmap.get(key))){
                ans=key;
            }
        }
        return ans;
    }
    private static String decryption(int mkey,int akey,String [] cipherarray,char [] chars){
        String decrypted="";
        ArrayList<String> clist=new ArrayList<>();
        for(String i:cipherarray){
            String convert=i.toLowerCase();
            String ans="";
            ArrayList<Integer> list=new ArrayList<>();
            for(char ch:convert.toCharArray()){
                int temp=((ch-'a')-akey);
                if(temp<0){
                    temp+=26;
                }
                int val=(temp*map.get(mkey))%26;
                ans+=chars[val];
            }
            clist.add(ans);
        }
        for(String i:clist){
            decrypted+=i+" ";
        }
        return decrypted.trim();
    }
    private static String encryption(int mkey,int akey,String [] array,char [] chars){
        ArrayList<String> plist=new ArrayList<>();
        String encrypted="";
        for(String i:array){
            ArrayList<Integer> elist=new ArrayList<>();
            for(char ch:i.toCharArray()){
                int temp=(((ch-'a')*mkey)+akey)%26;
                elist.add(temp);
            }
            String ans="";
            for(int j:elist) ans+=chars[j];
            plist.add(ans);
        }
        for(String i:plist){
            encrypted+=i+" ";
        }
        return encrypted.trim();
    }
    static HashMap<Integer,Integer> map=new HashMap<>();
    private static void modInverse(int a, int m)
    {
        for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                map.put(a,x);
    }
    private static void showDetails(){
        System.out.println("1.Encryption");
        System.out.println("2.Decryption");
        System.out.println("3.Brute Force Attack");
        System.out.println("4.Exit");
    }
    static ArrayList<Integer> domain=new ArrayList<>();
    private static boolean isContainsOtherCharacters(String plain){
        Pattern pattern = Pattern.compile("[^a-z]");
        Matcher matcher = pattern.matcher(plain);
        return matcher.find();
    }
    private static boolean checkForSpecialCharactersOtherThanSpaces(String [] array){
        int count=0;
        for(String i:array){
            if(isContainsOtherCharacters(i)){
                count++;
            }
        }
        return count>0;
    }
    private static boolean checkCipher(String [] array){
        int count=0;
        for(String i:array){
            if(isContainOtherThanUpperCase(i)){
                count++;
            }
        }
        return count>0;
    }
    private static boolean isContainOtherThanUpperCase(String cipher){
        Pattern pat=Pattern.compile("[^A-Z]");
        Matcher mat= pat.matcher(cipher);
        return mat.find();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("<---Affine Cipher System--->");
        showDetails();
        System.out.println("Enter the plain text:-");
        String plain=sc.nextLine();
        String [] arrayOfplain=plain.split(" ");
        while(checkForSpecialCharactersOtherThanSpaces(arrayOfplain)){
            System.out.println("Re-enter Plain Text");
            plain=sc.nextLine();
            arrayOfplain=plain.split(" ");
        }
        System.out.println("Enter your choice:-");
        int choice=sc.nextInt();
        char [] chars=new char[26];
        for(int i=0;i<26;i++){
            chars[i]= (char) ('a'+i);
        }
        String cipher="";
        for(int i=1;i<=26;i++){
            modInverse(i,26);
        }
        Set<Integer> keyDomain=map.keySet();
        for(int i:keyDomain) domain.add(i);
        String mkey;
        String akey;
        int kl=0;
        while(true){
            if(choice==1){
                System.out.println("Encryption");
                System.out.println("Enter multiplicative encryption key:-");
                mkey=sc.next();
                boolean result = mkey.matches("[0-9]+");
                while(!result) {
                    System.out.println("Enter correct key");
                    mkey = sc.next();
                    result = mkey.matches("[0-9]+");
                }
                int ky=Integer.parseInt(mkey);
                while(!keyDomain.contains(ky)){
                    System.out.println("Enter valid key from valid key domain:-");
                    System.out.println(keyDomain);
                    mkey=sc.next();
                    ky = Integer.parseInt(mkey);
                }
                kl=ky;
                if(ky>26){
                    ky=ky%26;
                }
                System.out.println("Enter additive key for encryption:-");
                akey=sc.next();
                boolean res = akey.matches("[0-9]+");
                while(!res){
                    System.out.println("Enter correct key");
                    akey=sc.next();
                    res=akey.matches("[0-9]+");
                }
                int ak=Integer.parseInt(akey);
                if(ak>26) ak=ak%26;
                cipher=encryption(ky,ak,arrayOfplain,chars);
                System.out.println("Cipher Text:-"+cipher.toUpperCase());
                System.out.println("Enter choice");
                choice=sc.nextInt();
            }
            if(choice==2){
                System.out.println("Decryption");
                System.out.println("do you want to decrypt the previous one or new one enter 1 or 2:-");
                int ch=sc.nextInt();
                if(ch==1){
                    System.out.println("Enter Multiplicative key:-");
                    String mk=sc.next();
                    boolean result = mk.matches("[0-9]+");
                    while(!result){
                        System.out.println("Enter correct key");
                        mk=sc.next();
                        result=mk.matches("[0-9]+");
                    }
                    int ky=Integer.parseInt(mk);
                    kl=ky;
                    if(ky>26){
                        ky=ky%26;
                    }
                    System.out.println("Enter additive key for encryption:-");
                    String aky=sc.next();
                    boolean res = aky.matches("[0-9]+");
                    while(!res){
                        System.out.println("Enter correct key");
                        aky=sc.next();
                        res=aky.matches("[0-9]+");
                    }
                    int ak=Integer.parseInt(aky);
                    if(ak>26) ak=ak%26;
                    String [] arrayofcipher=cipher.split(" ");
                    String decrypted=decryption(ky,ak,arrayofcipher,chars);
                    System.out.println("Decrypted Text:- "+decrypted);
                }
                if(ch==2){
                    Scanner scn=new Scanner(System.in).useDelimiter("\n");
                    System.out.println("Enter Cipher Text");
                    String ci=scn.next();
                    String [] array=ci.split(" ");
                    while (checkCipher(array)){
                        System.out.println("Re -enter Cipher Text");
                        ci=scn.next();
                        array=ci.split(" ");
                    }
                    System.out.println("Enter Multiplicative key:-");
                    String mk=sc.next();
                    boolean result = mk.matches("[0-9]+");
                    while(!result){
                        System.out.println("Enter correct key");
                        mk=sc.next();
                        result=mk.matches("[0-9]+");
                    }
                    int ky=Integer.parseInt(mk);
                    kl=ky;
                    if(ky>26){
                        ky=ky%26;
                    }
                    System.out.println("Enter additive key for encryption:-");
                    String aky=sc.next();
                    boolean res = aky.matches("[0-9]+");
                    while(!res){
                        System.out.println("Enter correct key");
                        aky=sc.next();
                        res=aky.matches("[0-9]+");
                    }
                    int ak=Integer.parseInt(aky);
                    if(ak>26) ak=ak%26;
                    String newDecrypted=decryption(ky,ak,array,chars);
                    System.out.println("Decryted Text:- "+newDecrypted);
                }
                System.out.println("Next choice");
                choice=sc.nextInt();
            }
            if(choice==3){
                System.out.println("Brute Force");
                ArrayList<Integer> ans=bruteForceKeyFinder(cipher.split(" "),chars,plain);
                System.out.println("Multiplicative key is:- "+ans.get(0));
                System.out.println("Additive Key is:- "+ans.get(1));;
                System.out.println("Enter choice");
                choice=sc.nextInt();
            }
            if(choice==4){
                System.exit(0);
            }
        }
    }
}
