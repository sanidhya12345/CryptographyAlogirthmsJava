import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class HillCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        List<Integer> list = new ArrayList<>();
        System.out.println("1 FOR ENCRYPTION:");
        System.out.println("2 FOR DECRYPTION:");System.out.println("Other Key FOR EXIT:");
        int c=sc.nextInt();
        boolean result=false;
        String pt="";
        String ct="";
        String pt1="";
        String ptd="";
        String ct1="";
        String km="";
        String key="";
        int key1=0;
        String ka="";
        int f=0;
        ArrayList<Integer> spaces=new ArrayList<>();
        switch(c) {
            case 1:
                while (!result) {
                    System.out.println("ENTER Plaintext : ");
                    pt = sc.next();
                    pt1 = pt;
                    pt = pt.replaceAll("\\s+", "");
                    result = pt.matches("[a-z]+");
                    if (result == false)
                        System.out.println("ENTER CORRECT STRING::");
                }
                for (int i = 0; i < pt1.length(); i++) {
                    if (pt1.charAt(i) == ' ') spaces.add(i);
                }while (f == 0) {
                System.out.println("ENTER KEY : ");
                key = sc.next();
                key = key.replaceAll("\\s+", "");
                result = key.matches("[a-z]+");
                if (result == false)
                    System.out.println("ENTER CORRECT KEY::");
                else
                    f = 1;
            }
                String ans=HillCipher(pt, key);
                System.out.println("Plain Text : "+pt);
                System.out.println("Key: "+key);
                System.out.println("Cipher Text: "+ans);
                break;
        }
    }
    static String HillCipher(String message, String key) {
        int row=key.length()/3;
        if(key.length()%3!=0){
            row=row+1;
        }
        System.out.println("row:"+row);
        int [][]keyMatrix = new int[row][3];
        getKeyMatrix(key, keyMatrix,row);System.out.println("Key:==>");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(keyMatrix[i][j]+" ");
            }
            System.out.println();
        }
        int Prow=message.length()/3;
        if(message.length()%3!=0){
            Prow=Prow+1;
        }
        int [][]messageVector = new int[3][Prow];
        getMessageMatrix(message, messageVector,Prow);
        System.out.println("Message:==>");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < Prow; j++) {
                System.out.print(messageVector[i][j]+" ");
            }
            System.out.println();
        }
        int [][]cipherMatrix = new int[row][Prow];
        encrypt(cipherMatrix, keyMatrix, messageVector,row,Prow);
        System.out.println("Cipher:==>");
        for (int i = 0; i < row; i++) {for (int j = 0; j < Prow; j++) {
            System.out.print(cipherMatrix[i][j]+" ");
        }
            System.out.println();
        }
//
        String CipherText="";
        for (int i = 0; i < row; i++){
            for (int j = 0; j <Prow; j++) {
                CipherText += alphabet1.charAt(cipherMatrix[i][j]);
            }
        }
        return CipherText;
//
    }
    static void getKeyMatrix(String key, int keyMatrix[][],int row)
    {
        int k = 0;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if(k>=key.length()){
                    keyMatrix[i][j]=25;
                }
                else {
                    keyMatrix[i][j] = alphabet.indexOf(key.charAt(k));k++;
                }
            }
        }
    }
    static void getMessageMatrix(String key, int messageVector[][],int row)
    {
        int k = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < row; j++)
            {
                if(k>=key.length()){
                    messageVector[i][j]=25;
                }
                else {
                    messageVector[i][j] = alphabet.indexOf(key.charAt(k));
                    k++;
                }
            }
        }
    }
    static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][],int row,
                        int Prow)
    {
        int x, i, j;
        for (i = 0; i < row; i++)
        {for (j = 0; j < Prow; j++)
        {
            cipherMatrix[i][j] = 0;
            for (x = 0; x < 3; x++)
            {
                cipherMatrix[i][j] +=
                        keyMatrix[i][x] * messageVector[x][j];
            }
            cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
        }
        }
    }
}