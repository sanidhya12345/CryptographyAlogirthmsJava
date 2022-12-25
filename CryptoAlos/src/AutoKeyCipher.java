import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoKeyCipher {
    static ArrayList<String> elist=new ArrayList<>();
    static ArrayList<ArrayList<Integer>> keyList=new ArrayList<>();
    static ArrayList<ArrayList<Integer>> dkeyList=new ArrayList<>();
    private static boolean isContainOtherThanUpperCase(String cipher){
        Pattern pat=Pattern.compile("[^A-Z]");
        Matcher mat= pat.matcher(cipher);
        return mat.find();
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
    private static void showDetails(){
        System.out.println("1.Encryption");
        System.out.println("2.Decryption");
        System.out.println("3.Brute Force Attack");
        System.out.println("4.Exit");
    }
    static HashMap<Integer,String> map=new HashMap<>();
    private static int bruteForceKeyFinder(String plain,char [] chars,String [] array){
        for(int i=1;i<=26;i++){
            ArrayList<ArrayList<Integer>> plist=brutefill(array,i);
            String val=bruteDecrypter(plist,array,chars);
            map.put(i,val);
        }
        int ans=0;
        for(int i:map.keySet()){
            if(plain.equals(map.get(i))){
                ans=i;
            }
        }
        return ans;
    }
    private static ArrayList<ArrayList<Integer>> brutefill(String [] array,int key){
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        for(String i:array){
            ArrayList<Integer> klist=new ArrayList<>();
            int [] karr=new int[i.length()];
            karr[0]=key;
            String convert=i.toLowerCase();
            for(int j=1;j<i.length();j++){
                int tep=(convert.charAt(j-1)-'a')-karr[j-1];
                if(tep<0){
                    tep+=26;
                }
                karr[j]=tep;
            }
            for(int item:karr){
                klist.add(item);
            }
            list.add(klist);
            int p=convert.charAt(convert.length()-1)-'a'-karr[karr.length-1];
            if(p<0){
                p+=26;
            }
            key=p;
        }
        return list;
    }
    private static String bruteDecrypter(ArrayList<ArrayList<Integer>> lst,String[] array,char [] chars){
        String decrypted="";
        ArrayList<String> plist=new ArrayList<>();
        for(int i=0;i<lst.size();i++){
            String ans="";
            String str=array[i].toLowerCase();
            for(int j=0;j<lst.get(i).size();j++){
                int temp=((str.charAt(j)-'a')-lst.get(i).get(j));
                if(temp<0){
                    temp+=26;
                    temp=temp%26;
                }
                else{
                    temp=temp%26;
                }
                ans+=chars[temp];
            }
            plist.add(ans);
        }
        for(String i:plist){
            decrypted+=i+" ";
        }
        return decrypted.trim();
    }
    private static String decryptnewCipher(String [] array,char [] chars){
        String decrypted="";
        ArrayList<String> plist=new ArrayList<>();
        for(int i=0;i<dkeyList.size();i++){
            String ans="";
            String str=array[i].toLowerCase();
            for(int j=0;j<dkeyList.get(i).size();j++){
                int temp=((str.charAt(j)-'a')-dkeyList.get(i).get(j));
                if(temp<0){
                    temp+=26;
                    temp=temp%26;
                }
                else{
                    temp=temp%26;
                }
                ans+=chars[temp];
            }
            plist.add(ans);
        }
        for(String i:plist){
            decrypted+=i+" ";
        }
        return decrypted.trim();
    }
    private static String  decryption(char [] chars){
        String decrypted="";
        ArrayList<String> plist=new ArrayList<>();
        for(int i=0;i<keyList.size();i++){
            String ans="";
            String str=elist.get(i).toLowerCase();
            for(int j=0;j<keyList.get(i).size();j++){
                int temp=((str.charAt(j)-'a')-keyList.get(i).get(j));
                if(temp<0){
                    temp+=26;
                    temp=temp%26;
                }
                else{
                    temp=temp%26;
                }
                ans+=chars[temp];
            }
            plist.add(ans);
        }
        for(String i:plist){
            decrypted+=i+" ";
        }
        return decrypted.trim();
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
    private static boolean isContainsOtherCharacters(String plain){
        Pattern pattern = Pattern.compile("[^a-z]");
        Matcher matcher = pattern.matcher(plain);
        return matcher.find();
    }
    private static void keyListFiller(String [] array,int key){
        for(String i:array){
            ArrayList<Integer> klist=new ArrayList<>();
            int [] karr=new int[i.length()];
            karr[0]=key;
            String convert=i.toLowerCase();
            for(int j=1;j<i.length();j++){
                int tep=(convert.charAt(j-1)-'a')-karr[j-1];
                if(tep<0){
                    tep+=26;
                }
                karr[j]=tep;
            }
            for(int item:karr){
                klist.add(item);
            }
            dkeyList.add(klist);
            int p=convert.charAt(convert.length()-1)-'a'-karr[karr.length-1];
            if(p<0){
                p+=26;
            }
            key=p;
        }
    }
    private static String encryption(String [] array,int encryption_key,char [] chars){
        String encryptedsCipher="";

        for(String i:array){
            ArrayList<Integer> klist=new ArrayList<>();
            klist.add(encryption_key);
            String substr=i.substring(0,i.length()-1);
            int k=(i.charAt(i.length()-1)-'a');
            for(char c:substr.toCharArray()){
                int temp=c-'a';
                klist.add(temp);
            }
            keyList.add(klist);
            String s="";
            for(int j=0;j<i.length();j++){
                int t=((i.charAt(j)-'a')+klist.get(j))%26;
                s+=chars[t];
            }
            elist.add(s);
            encryption_key=k;
        }
        for(String i:elist){
            encryptedsCipher+=i+" ";
        }
        return encryptedsCipher.trim();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Auto-Key Cipher System");
        showDetails();
        System.out.println("Enter the plain text");
        String plain=sc.nextLine();
        String [] arrayOfplain=plain.split(" ");
        while(checkForSpecialCharactersOtherThanSpaces(arrayOfplain)){
            System.out.println("Re-enter Plain Text");
            plain=sc.nextLine();
            arrayOfplain=plain.split(" ");
        }
        System.out.println("Enter your choice");
        int choice=sc.nextInt();
        char [] chars=new char[26];
        for(int i=0;i<26;i++){
            chars[i]=(char)('a'+i);
        }
        String cipher="";
        String key;
        int kl=0;
        while (true){
            if(choice==1){
                System.out.println("<--Encryption Phase-->");
                System.out.println("Enter Encryption Key:-");
                key=sc.next();
                boolean result = key.matches("[0-9]+");
                while(!result){
                    System.out.println("Enter correct key");
                    key=sc.next();
                    result=key.matches("[0-9]+");
                }
                int ky=Integer.parseInt(key);
                kl=ky;
                if(ky>26){
                    ky=ky%26;
                }
                cipher=encryption(arrayOfplain,ky,chars);
                System.out.println("Encrypted Cipher Text:- "+cipher.toUpperCase());
                System.out.println("Enter your choice for next step:-");
                choice=sc.nextInt();
            }
            if(choice==2){
                System.out.println("<--Decryption Started-->");
                System.out.println("Do you want to decrypt the previous text enter 1 or 2");
                int ch=sc.nextInt();
                if(ch==1){
                    System.out.println("Enter decryption key");
                    String decryt_key=sc.next();
                    boolean varl=decryt_key.matches("[0-9]+");
                    while(!varl){
                        System.out.println("RE-enter key");
                        decryt_key=sc.next();
                        varl=decryt_key.matches("[0-9]+");
                    }
                    int dk=Integer.parseInt(decryt_key);
                    if(plain.equals(decryption(chars)) && kl==dk){
                        System.out.println("Congrats decryption done successfully "+decryption(chars));
                    }
                    else{
                        System.out.println("Try Brute Force");
                    }
                }
                if(ch==2){
                    Scanner scn=new Scanner(System.in).useDelimiter("\n");
                    System.out.println("Enter Cipher Text");
                    String ct=scn.next();
                    String [] arr=ct.split(" ");
                    while(checkCipher(arr)){
                        System.out.println("Re-enter Cipher text");
                        ct=scn.next();
                        arr=ct.split(" ");
                    }
                    String [] newArray=new String[arr.length];
                    for(int i=0;i<arr.length;i++){
                        newArray[i]=arr[i];
                    }
                    System.out.println("Enter Decryption key:-");
                    String dkey=sc.next();
                    boolean res=dkey.matches("[0-9]+");
                    while(!res){
                        System.out.println("Re-enter key");
                        dkey=sc.next();
                        res=dkey.matches("[0-9]+");
                    }
                    int wk=Integer.parseInt(dkey);
                    keyListFiller(newArray,wk);
                    if(plain.equals(decryptnewCipher(newArray,chars))){
                        System.out.println("Yeah you find the original message in one go:- "+decryptnewCipher(newArray,chars));
                    }
                    else{
                        System.out.println("New Decrypted text:- "+decryptnewCipher(newArray,chars));
                    }

                }
                System.out.println("Enter choice for next step");
                choice=sc.nextInt();
            }
            if(choice==3){
                System.out.println("<--Brute Force Approach-->");
                int keybf=bruteForceKeyFinder(plain,chars,cipher.split(" "));
                System.out.println("Yeah We find the hidden encrypted key:-"+keybf);
                System.out.println("Enter choice");
                choice=sc.nextInt();
            }
            if(choice==4){
                System.exit(0);
            }
        }

    }
}
