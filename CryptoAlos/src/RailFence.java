import java.util.ArrayList;
import java.util.Scanner;

public class RailFence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        ArrayList<Integer> spaces=new ArrayList<>();
        boolean result=false;
        String pt="";
        String pt1="";
        while(!result){
            System.out.println("Enter plaintext : ");
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
        System.out.println("Enter Depth:");
        int k=sc.nextInt();
        int len=pt.length();
        String ans=encryptRailFence(pt, k);
        System.out.println();
        System.out.println("Encrypted Plain Text:- "+ans);
        System.out.println();
        System.out.println("Original Plain Text:- "+decryptRailFence(ans,k));
    }


    private static String encryptRailFence(String text, int key){
        char[][] rail=new char[key][(text.length())];
        for (int i=0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] ='\f';
        boolean dir_down = false;
        int row = 0, col = 0;


        for (int i=0; i < text.length(); i++)
        {
            if (row == 0 || row == key-1)
                if(dir_down ==true)
                    dir_down=false;
                else
                    dir_down=true;
            rail[row][col++] = text.charAt(i);
            if(dir_down ==true)
                row+=1;
            else
                row-=1;
        }

        String result = "";
        for (int i=0; i < key; i++)
            for (int j=0; j < text.length(); j++)
                if (rail[i][j]!='\f')
                    result += rail[i][j];

        for (int i=0;i<key;i++){
            for (int j=0; j < text.length(); j++){
                System.out.print(rail[i][j]+" ");
            }
            System.out.println();
        }

        return result;
    }



    private static String decryptRailFence(String text, int key){
        char[][] rail=new char[key][(text.length())];
        for (int i=0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] ='\f';
        boolean dir_down = false;

        int row = 0, col = 0;

        for (int i=0; i < text.length(); i++)
        {
            if (row == 0)
                dir_down = true;
            if (row == key-1)
                dir_down = false;
            rail[row][col++] = '*';

            if(dir_down ==true)
                row+=1;
            else
                row-=1;
        }
        int index = 0;
        for (int i=0; i<key; i++)
            for (int j=0; j<text.length(); j++)
                if (rail[i][j] == '*' && index<text.length())
                    rail[i][j] = text.charAt(index++);
        String result="";

        row = 0;
        col = 0;
        for (int i=0; i< text.length(); i++)
        {
            if (row == 0)
                dir_down = true;
            if (row == key-1)
                dir_down = false;


            if (rail[row][col] != '*')
                result += rail[row][col++];

            if(dir_down ==true)
                row+=1;
            else
                row-=1;
        }
        return result;
    }
}