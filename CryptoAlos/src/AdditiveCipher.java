import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdditiveCipher {
    static ArrayList<String> enryptionList=new ArrayList<>();
    private static int bruteForceKeyFinder(char[] chars,String plain){
        HashMap<Integer,String> bruteforcemap=new HashMap<>();
        for(int i=1;i<=26;i++){
            String res=decryption(i,chars).trim();
            bruteforcemap.put(i,res);
        }
        int ans=0;
        for (int key: bruteforcemap.keySet()){
            String temp=bruteforcemap.get(key);
            if(plain.equals(temp)){
                ans=key;
            }
        }
        System.out.println(bruteforcemap);
        return ans;
    }
    private static String decryptNewCipher(int decryption_key,char [] chars,String [] arr){
        ArrayList<String> plainList=new ArrayList<>();
        String plaintext="";
        for(String i:arr){
            String convert=i.toLowerCase();
            String ans="";
            for(char ch:convert.toCharArray()){
                int temp=((ch-'a')-decryption_key)%26;
                if(temp<0){
                    temp+=26;
                    temp=temp%26;
                }
                else{
                    temp=temp%26;
                }
                ans+=chars[temp];
            }
            plainList.add(ans);
        }
        for(String i:plainList){
            plaintext+=i+" ";
        }
        return plaintext.trim();
    }
    private static String decryption(int decryption_key,char[] chars){
        ArrayList<String> plainList=new ArrayList<>();
        String plaintext="";
        for(String i:enryptionList){
            String convert=i.toLowerCase();
            String ans="";
            for(char ch:convert.toCharArray()){
                int temp=((ch-'a')-decryption_key)%26;
                if(temp<0){
                    temp+=26;
                    temp=temp%26;
                }
                else{
                    temp=temp%26;
                }
                ans+=chars[temp];
            }
           plainList.add(ans);
        }
        for(String i:plainList){
            plaintext+=i+" ";
        }
        return plaintext.trim();
    }
    private static String encryption(String [] array,int encryption_key,char [] chars){
        String encryptedCipher="";
        for(String i:array){
            ArrayList<Integer> clist=new ArrayList<>();
            for(char ch:i.toCharArray()){
                int temp=((ch-'a')+encryption_key)%26;
                clist.add(temp);
            }
            String s="";
            for(int j:clist){
                s+=chars[j];
            }
            enryptionList.add(s);
        }
        for(int i=0;i<enryptionList.size();i++){
            encryptedCipher+=enryptionList.get(i)+" ";
        }
        return encryptedCipher.trim();
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
    private static void showDetails(){
        System.out.println("1.Encryption");
        System.out.println("2.Decryption");
        System.out.println("3.Brute Force Attack");
        System.out.println("4.Exit");
    }
    private static boolean isContainOtherThanUpperCase(String cipher){
        Pattern pat=Pattern.compile("[^A-Z]");
        Matcher mat= pat.matcher(cipher);
        return mat.find();
    }
    private static boolean isContainsOtherCharacters(String plain){
        Pattern pattern = Pattern.compile("[^a-z]");
        Matcher matcher = pattern.matcher(plain);
        return matcher.find();
    }
    private static boolean comparePlainWithCipherUsingKey(int key,char [] chars,String plain){
        System.out.println("Decrypted Plain Text:- "+decryption(key,chars));
        return plain.equals(decryption(key,chars));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("<---Additive Cipher System--->");
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
        int key=0;
       while(true){
           if(choice==1){
               System.out.println("<--Encryption Phase-->");
               System.out.println("Enter the Encryption Key:-");
               key=sc.nextInt();
               if(key>26){
                   key=key%26;
               }
               cipher=encryption(arrayOfplain,key,chars).toUpperCase();
               System.out.println("Encrypted Cipher Text:- "+cipher);
               showDetails();
               System.out.println("Enter your choice:-");
               choice=sc.nextInt();
           }
           else if(choice==2){
               System.out.println("<--Decryption Started-->");
               System.out.println("Do you want to decrypt the previous Cipher text,Enter 1 or 2");
               int ch=sc.nextInt();
               if(ch==1){
                   int decrypt_key=sc.nextInt();
                   if(comparePlainWithCipherUsingKey(decrypt_key,chars,plain)){
                       System.out.println("Plain Text Matched Successfully:-");
                   }
                   else {
                       System.out.println("No Match! Try Brute Force!!");
                   }
               }
               if(ch==2){
                   Scanner scn=new Scanner(System.in).useDelimiter("\n");
                   System.out.println("Enter CipherText:-");
                   String ci=scn.next();
                   String [] arr=ci.split(" ");
                   while(checkCipher(arr)){
                       System.out.println("Re-enter Cipher Text");
                       ci=scn.next();
                       arr=ci.split(" ");
                   }
                   System.out.println("Enter Decryption key:-");
                   int dkey=sc.nextInt();
                   String pl=decryptNewCipher(dkey,chars,arr);
                   if(plain.equals(pl)){
                       System.out.println("Congrats decryption successfully:- "+pl);
                   }
                   else{
                       System.out.println("Try Brute Force.");
                   }
               }
               showDetails();
               System.out.println("Enter your choice:-");
               choice=sc.nextInt();
           }
           else if(choice==3){
               System.out.println("Brute Force Attack");
               int encrypted=bruteForceKeyFinder(chars,plain);
               System.out.println("Congrats we successfully crack encryption key:- "+encrypted);
               System.out.println("Now Perform decryption to get plain text.");
               System.out.println("Decryption Starting---->");
               System.out.println("Original Plain Text:- "+decryption(encrypted,chars));
               showDetails();
               System.out.println("Enter your choice");
               choice=sc.nextInt();
           }
           else if(choice==4){
               System.exit(0);
           }
       }
    }
}
