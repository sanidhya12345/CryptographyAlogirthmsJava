import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RCTransposition {
    private static char[][] matfill(char [][] matrix,char [] key){
        char [][] ans=new char[matrix.length][matrix[0].length];

        for(int i=0;i<key.length;i++){

            int index=Character.getNumericValue(key[i]);

            for(int j=0;j<matrix.length;j++){

                ans[j][i]=matrix[j][index];
            }
        }

//        for(int i=0;i<matrix.length;i++){
//            for(int j=0;j<matrix[0].length;j++){
//                System.out.print(ans[i][j]+" ");
//            }
//            System.out.println();
//        }
        return ans;
    }

    private static String decryption(char [][] matrix,char[] key){
        String decrypted="";

        char [][] ans=matfill(matrix,key);

        for(int i=0;i<ans.length;i++){
            for(int j=0;j<ans[0].length;j++){
                decrypted+=ans[i][j];
            }
        }
        return decrypted;
    }
    private static char [][] fillColumnWise(char [] cipher,int klength){
        int rows=cipher.length/klength;
        int cols=klength;
        char [][] matrix=new char[rows][cols];
        int index=0;
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                matrix[j][i]=cipher[index];
                index++;
            }
        }
        return matrix;
    }
    private static char[][] fillMatrix(char [] plain,int klength){
        int rows=plain.length/klength;
        int cols=klength;
        char [][] matrix=new char[rows][cols];
        int index=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j]=plain[index];
                index++;
            }
        }
        return matrix;
    }
    private static char[][] fillMatrix1(char [] plain,int klength){
        int rows=klength;
        int cols=klength;
        char [][] matrix=new char[rows][cols];
        int index=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j]=plain[index];
                index++;
            }
        }
        return matrix;
    }
    private static String encryptionOrder(String key){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<key.length();i++){
            map.put(Character.getNumericValue(key.toCharArray()[i]),i);
        }
        String keytransformed="";
        Set<Integer> set=map.keySet();
        for(Integer i: set){
            keytransformed+=map.get(i);
        }
        return keytransformed;
    }
    private static String encryption(char [][] matrix,int length,String key) {
        char[] karr = key.toCharArray();
        String encrypted = "";
        for (int i = 0; i < karr.length; i++) {
            int val = Character.getNumericValue(karr[i]);

            for (int j = 0; j < matrix.length; j++) {
                encrypted += matrix[j][val];
            }
        }
        return encrypted;
    }
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
//        private static boolean containsDuplicateInKey(String key){
//
//        }
        public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Scanner scn=new Scanner(System.in).useDelimiter("/n");
        System.out.println("Enter key:-");
        String key=sc.next();
        System.out.println("Enter plain text");
        String plain=scn.nextLine();
        String gen="";
            String [] arrayOfplain=plain.split(" ");
            while(checkForSpecialCharactersOtherThanSpaces(arrayOfplain)){
                System.out.println("Re-enter Plain Text");
                plain=scn.nextLine();
                arrayOfplain=plain.split(" ");
            }
            for(String i:arrayOfplain){
                gen+=i;
            }

            char [] array=gen.toCharArray();
            char [][] matrix=fillMatrix(array,key.length());
            int cal=gen.length()/key.length();
            if(cal*key.length()<gen.length()){
                int val=(key.length()*key.length())-gen.length();
                for(int i=0;i<val;i++){
                    gen+=(char)('z'-i);
                }
                System.out.println(gen);
                array=gen.toCharArray();
                matrix=fillMatrix1(array,key.length());
            }
            if(cal*key.length()>gen.length()){
                int val=(cal*key.length())-gen.length();
                for(int i=0;i<val;i++){
                    gen+=(char)('z'-i);
                }
                System.out.println(gen);
                array=gen.toCharArray();
                matrix=fillMatrix(array,key.length());
            }

        String ekey=encryptionOrder(key);
        String encrypted;
        String enc=encryption(matrix,ekey.length(),ekey);
        System.out.println("Enter Number of rounds of Encryption:-");
        int rounds=sc.nextInt();
        int r1=1;
        System.out.println("<--Rounds of RC-Encryption-->");
        while(rounds!=0){
            for (int i = 0; i < array.length/key.length(); i++) {
                for (int j=0;j<key.length();j++){
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }
            encrypted=encryption(matrix,ekey.length(),ekey);
            System.out.println();
            System.out.println("Encrypted After "+r1+" round:- "+encrypted);
            matrix=fillMatrix(encrypted.toCharArray(),ekey.length());
            enc=encrypted;
            System.out.println();
            r1++;
            rounds--;
        }
            System.out.println();
            System.out.println("Final encrypted after all rounds performed:- "+enc);
            System.out.println();

            System.out.println("Enter Decryption key:-");
            String key1=sc.next();
            String dkey="";
            for(char c:key1.toCharArray()){
                dkey+=(Character.getNumericValue(c)-1);
            }
            System.out.println("Enter cipher:-");
            String cipher=sc.next();
            System.out.println("Enter the number of decryption rounds:-");
             int drounds=sc.nextInt();
            System.out.println("<--Rounds of RC-Decryption-->");
            char [][] dmatrix=fillColumnWise(cipher.toCharArray(),key1.length());
            String decrypted;
            String dec="";
            int r2=1;
             while (drounds--!=0){
                 for (int i = 0; i < array.length/key.length(); i++) {
                     for (int j=0;j<key.length();j++){
                         System.out.print(dmatrix[i][j]+" ");
                     }
                     System.out.println();
                 }
                 decrypted=decryption(dmatrix,dkey.toCharArray());
                 dec=decrypted;
                 System.out.println();
                 System.out.println("Decrypted After "+r2+" round:- "+decrypted);
                 dmatrix=fillColumnWise(decrypted.toCharArray(),key1.length());
                 System.out.println();
                 r2++;
             }
            System.out.println();
            System.out.println("Final Decryption After all rounds:- "+dec);
            System.out.println();
            if(gen.equals(dec)){
                System.out.println("!!!Congratulations you have found the right plain text..");
            }
            else{
                System.out.println("!!!Sorry you have to try again!!!");
            }
    }
}
