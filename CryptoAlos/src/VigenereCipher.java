import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class VigenereCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 3, 5, 7, 11, 15, 17, 19, 21, 23, 25);
        System.out.println("1 FOR ENCRYPTION:");
        System.out.println("2 FOR DECRYPTION:");
        System.out.println("3 FOR BRUTEFORCE:");
        System.out.println("Other Key FOR EXIT:");
        int c = sc.nextInt();
        boolean result = false;
        String pt = "";
        String ct = "";
        String pt1 = "";String ptd = "";
        String ct1 = "";
        String km = "";
        String key = "";
        int kmf = 0;
        String ka = "";
        int kaf = 0;
        int f = 0;
        ArrayList<Integer> spaces = new ArrayList<>();
        switch (c) {
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
                    result = key.matches("[a-z]+");
                    if (result == false)
                        System.out.println("ENTER CORRECT KEY::");
                    else
                        f = 1;
                }
                String keyL=generateKey(pt,key);
                String enc1=cipherText(pt,keyL);
//
                StringBuffer str1 = new StringBuffer(enc1);
                System.out.println(spaces);
                for (int i = 0; i < pt1.length(); i++) {
                    for (int j = 0; j < spaces.size(); j++) {
                        if (spaces.get(j) == i) {
                            str1.insert(i, ' ');
                        }
                    }
                }enc1 = str1.toString();
                System.out.println("Plaintext : " + pt1);
                System.out.println("Encrypted : " + enc1);
                break;
            case 2:
                while(!result){
                    System.out.println("ENTER Ciphertext : ");
                    ptd=sc.next();
                    pt1=ptd;
                    ptd=ptd.replaceAll("\\s+","");
                    result = ptd.matches("[A-Z]+");
                    if(result==false)
                        System.out.println("ENTER CORRECT STRING::");
                }
                for(int i=0;i<pt1.length();i++){
                    if(pt1.charAt(i)==' ') spaces.add(i);
                }
                for(int i=0;i<ptd.length();i++){
                    int p1=alphabet1.indexOf(ptd.charAt(i));
                    pt+=alphabet.charAt(p1);
                }
                while (f == 0) {
                    System.out.println("ENTER KEY : ");
                    key = sc.next();
                    result = key.matches("[a-z]+");
                    if (result == false)
                        System.out.println("ENTER CORRECT KEY::");
                    else
                        f = 1;
                }
                String keyE=generateKey(pt,key);
                String dec1=originalText(pt,keyE);
//
                StringBuffer str2 = new StringBuffer(dec1);
                System.out.println(spaces);
                for (int i = 0; i < pt1.length(); i++) {
                    for (int j = 0; j < spaces.size(); j++) {
                        if (spaces.get(j) == i) {
                            str2.insert(i, ' ');
                        }
                    }
                }dec1 = str2.toString();
                System.out.println("Plaintext : " + pt1);
                System.out.println("Encrypted : " + dec1);
                break;
            case 3:
                String ciptext = "";
                String ciptext1 = "";
                boolean ctresult = false;
                boolean outerloop = false;
                while (!outerloop) {
                    result = false;
                    ctresult = false;
                    while (!result) {
                        System.out.println("ENTER Plaintext : ");
                        pt = sc.next();
                        pt1 = pt;
                        pt = pt.replaceAll("\\s+", "");
                        result = pt.matches("[a-z]+");
                        if (result == false)
                            System.out.println("ENTER CORRECT STRING::");
                    }
                    while (!ctresult) {
                        System.out.println("ENTER Ciphertext : ");
                        ptd = sc.next();
                        ciptext1 = ptd;
                        ptd = ptd.replaceAll("\\s+", "");
                        ctresult = ptd.matches("[A-Z]+");
                        if (ctresult == false)
                            System.out.println("ENTER CORRECT STRING::");
                    }
                    for(int i=0;i<ptd.length();i++){
                        int p33=alphabet1.indexOf(ptd.charAt(i));
                        ciptext+=alphabet.charAt(p33);
                    }
                    int flag = 1;
                    if (ciptext1.length() != pt1.length()) {
                        System.out.println("Length of both are not same");
                        flag = 0;
                    }
                    int flagC = 1;
                    if (flag == 1) {for (int i = 0; i < pt1.length(); i++) {
                        if (pt1.charAt(i) == ' ') {
                            if (ciptext1.charAt(i) == ' ') {
                            } else {
                                flagC = 0;
                            }
                        }
                    }
                    }
                    if (flagC == 0) {
                        System.out.println("Spaces are not equal or at same place");
                    }
                    if (flagC == 1 && flag == 1) {
                        outerloop = true;
                    }
                }
                String keyBrute = "";
                keyBrute=originalText(ciptext,pt);
                System.out.println("KEY : "+keyBrute);
                break;
        }
    }
    static String generateKey(String str, String key)
    {
        int x = str.length();
        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }static String cipherText(String str, String key)
    {
        String cipher_text="";
        for (int i = 0; i < str.length(); i++)
        {
            int x = (alphabet.indexOf(str.charAt(i)) + alphabet.indexOf(key.charAt(i))) %26;
            cipher_text+=alphabet1.charAt(x);
        }
        return cipher_text;
    }
    static String originalText(String cipher_text, String key)
    {
        String orig_text="";
        for (int i = 0 ; i < cipher_text.length() &&
                i < key.length(); i++)
        {
            int x = (alphabet.indexOf(cipher_text.charAt(i)) -
                    alphabet.indexOf(key.charAt(i)) + 26) %26;
            orig_text+=alphabet.charAt(x);
        }
        return orig_text;
    }
}