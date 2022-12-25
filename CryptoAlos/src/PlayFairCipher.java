import java.util.ArrayList;
import java.util.Scanner;
public class PlayFairCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphabet1 =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("1 FOR ENCRYPTION:");
        System.out.println("2 FOR DECRYPTION:");
        System.out.println("Other Key FOR EXIT:");
        int c=sc.nextInt();
        boolean result=false;
        String pt="";String ct="";
        String pt1="";
        String ct1="";
        ArrayList<Integer> spaces=new ArrayList<>();
        int f=0;
        String key="";
        switch(c) {
            case 1:
                while(!result){
                    System.out.println("ENTER Plaintext : ");
                    pt=sc.next();
                    pt1=pt;
                    pt=pt.replaceAll("\\s+","");
                    result = pt.matches("[a-z]+");
                    if(result==false)
                        System.out.println("ENTER CORRECT STRING::");
                }
                for(int i=0;i<pt1.length();i++){
                    if(pt1.charAt(i)==' ') spaces.add(i);
                }
                while (f == 0) {
                    System.out.println("ENTER KEY : ");
                    key = sc.next();
                    key=key.replaceAll("\\s+","");
                    result = key.matches("[a-z]+");
                    if (result == false)
                        System.out.println("ENTER CORRECT KEY::");
                    else
                        f = 1;
                }
//
                String res=encryptByPlayfairCipher(pt, key);
//System.out.println("Plain Text :"+pt1);
                StringBuffer str1 = new StringBuffer(res);
                for (int i = 0; i < pt1.length(); i++) {//
//
//
//
//
//
//
                    for (int j = 0; j < spaces.size(); j++) {
                        if (spaces.get(j) == i) {
                            str1.insert(i, ' ');
                        }
                    }
                }
                String dec1="";
                dec1 = str1.toString();
                System.out.println("Cipher Text:"+res);
                break;
            case 2:
                while(!result){
                    System.out.println("ENTER Cipher Text : ");
                    pt=sc.next();
                    pt1=pt;
                    pt=pt.replaceAll("\\s+","");
                    result = pt.matches("[a-z]+");
                    if(result==false)
                        System.out.println("ENTER CORRECT STRING::");
                }
                for(int i=0;i<pt1.length();i++){
                    if(pt1.charAt(i)==' ') spaces.add(i);
                }
                while (f == 0) {
                    System.out.println("ENTER KEY : ");
                    key = sc.next();
                    key=key.replaceAll("\\s+","");
                    result = key.matches("[a-z]+");
                    if (result == false)
                        System.out.println("ENTER CORRECT KEY::");
                    else
                        f = 1;}
                String res1=dencryptByPlayfairCipher(pt, key);
                System.out.println("Cipher Text :"+pt1);
                StringBuffer str2 = new StringBuffer(res1);
//
//
//
//
//
//
//
//
//
//
//
//
                for (int i = 0; i < pt1.length(); i++) {
                    for (int j = 0; j < spaces.size(); j++) {
                        if (spaces.get(j) == i) {
                            str2.insert(i, ' ');
                        }
                    }
                }
                String dec2="";
                dec2 = str2.toString();
                System.out.println("Plain Text :"+res1);
                break;
//
        }
    }
    static String encryptByPlayfairCipher(String pt, String key)
    {
        char arr[][]=new char[5][5];
        String ps="";
        int lengthPT=pt.length();
        if(pt.length()%2==1)
            pt=pt+'z';
        StringBuffer str11 = new StringBuffer(pt);
        int cc=0;
        for (int i = 0; i <lengthPT; i=i+2) {
            if(str11.charAt(i)==str11.charAt(i+1)){
                str11.insert(i+1,'z');
                cc+=1;}
            if(cc==2){
                cc=0;
                lengthPT+=2;
            }
        }
        ps = str11.toString();
        int sizePS=ps.length();
        if(sizePS%2!=0)
            ps+='z';
        System.out.println("Plain Text :"+ps);
        char psA[]=ps.toCharArray();
        int count=0;
// System.out.println(ps);
        char ptA[]=pt.toCharArray();
        char keyA[]=key.toCharArray();
        int sizeK=key.length();
        generateKeyTable(keyA, sizeK, arr);
//
//
//
//
//
//
//
//
//
//
//
        System.out.println("KEY FILLED 2D MATRIX:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        sizePS=ps.length();
        System.out.println("Plain Text :"+ new String(psA));
        String ans=encrypt(psA,arr, sizePS);
        System.out.println("Cipher Text:"+ans);
        return ans;
    }
    static void generateKeyTable(char key[], int ks, char keyT[][])
    {
        int i, j, k, flag = 0;
// a 26 character hashmap
// to store count of the alphabet
        int dicty[]= new int[26];
        for (i = 0; i < ks; i++) {
            if (key[i] != 'j')
                dicty[key[i] - 97] = 2;
        }
        dicty['j' - 97] = 1;
        i = 0;
        j = 0;
        for (k = 0; k < ks; k++) {
            if (dicty[key[k] - 97] == 2) {
                dicty[key[k] - 97] -= 1;
                keyT[i][j] = key[k];
                j++;
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }
        for (k = 0; k < 26; k++) {
            if (dicty[k] == 0) {
                keyT[i][j] = (char)(k + 97);
                j++;if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }
    }
    static int mod5(int a) { return (a % 5); }
    static String encrypt(char str[], char keyT[][], int ps)
    {
        if(ps%2!=0)
            ps=ps+1;
        int i;
        int a[]=new int[4];
        for (i = 0; i < ps; i += 2) {
            search(keyT, str[i], str[i + 1], a);
            if (a[0] == a[2]) {
                str[i] = keyT[a[0]][mod5(a[1] + 1)];
                str[i + 1] = keyT[a[0]][mod5(a[3] + 1)];
            }
            else if (a[1] == a[3]) {
                str[i] = keyT[mod5(a[0] + 1)][a[1]];
                str[i + 1] = keyT[mod5(a[2] + 1)][a[1]];
            }
            else {
                str[i] = keyT[a[0]][a[3]];
                str[i + 1] = keyT[a[2]][a[1]];
            }
        }
        return new String(str);
    }
    static void search(char keyT[][], char a, char b, int arr[]){
        int i, j;
        if (a == 'j')
            a = 'i';
        else if (b == 'j')
            b = 'i';
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                if (keyT[i][j] == a) {
                    arr[0] = i;
                    arr[1] = j;
                }
                else if (keyT[i][j] == b) {
                    arr[2] = i;
                    arr[3] = j;
                }
            }
        }
    }
    static String dencryptByPlayfairCipher(String pt, String key)
    {
        char arr[][]=new char[5][5];
        String ps=pt;
        int lengthPT=pt.length();
        if(pt.length()%2==1)
            pt=pt+'z';
        StringBuffer str11 = new StringBuffer(pt);
        int cc=0;
        for (int i = 0; i <lengthPT; i=i+2) {
            if(str11.charAt(i)==str11.charAt(i+1)){
                str11.insert(i+1,'z');cc+=1;
            }
            if(cc==2){
                cc=0;
                lengthPT+=2;
            }
        }
        ps = str11.toString();
        int sizePS=ps.length();
        if(sizePS%2!=0)
            ps+='z';
        System.out.println("Cipher Text :"+ps);
        char psA[]=ps.toCharArray();
        int count=0;
//
        System.out.println(ps);
        char ptA[]=pt.toCharArray();
        char keyA[]=key.toCharArray();
        int sizeK=key.length();
        generateKeyTable(keyA, sizeK, arr);
//
//
//
//
//
//
//
//
//
//
        System.out.println("KEY FILLED 2D MATRIX:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        sizePS=ps.length();
        System.out.println("Plain Text :"+ new String(psA));
        String ans=decrypt(psA,arr, sizePS);
        System.out.println("Cipher Text:"+ans);
        return ans;}
    static String decrypt(char str[], char keyT[][], int ps)
    {
        if(ps%2!=0)
            ps=ps+1;
        int i;
        int a[]=new int[4];
        for (i = 0; i < ps; i += 2) {
            search(keyT, str[i], str[i + 1], a);
            if (a[0] == a[2]) {
                str[i] = keyT[a[0]][mod5(a[1] - 1)];
                str[i + 1] = keyT[a[0]][mod5(a[3] - 1)];
            }
            else if (a[1] == a[3]) {
                int a1=mod5(a[0] - 1);
                if(a1<0)
                    a1=a1+5;
                int a2=mod5(a[2] - 1);
                if(a2<0)
                    a2=a2+5;
                str[i] = keyT[a1][a[1]];
                str[i + 1] = keyT[a2][a[1]];
            }
            else {
                str[i] = keyT[a[0]][a[3]];
                str[i + 1] = keyT[a[2]][a[1]];
            }
        }
        return new String(str);
    }
}