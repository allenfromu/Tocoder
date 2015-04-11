import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Lottery {

    public String[] sortByOdds(String[] rules) {

        int len = rules.length;
        String[] names = new String[len];
        double[] pos = new double[len];
        for(int i = 0; i < len; i++){
            String temp = rules[i];
            String[] s = temp.split(":");
            names[i] = s[0];
            s = (s[1].trim()).split(" ");
            int ch = Integer.parseInt(s[0]);
            int bl = Integer.parseInt(s[1]);
            //String sorted = s[2];
            // String unique = s[3];
            if(s[2].equals("F") && s[3].equals("F")){
                pos[i] = 1.0/Math.pow(ch, bl);
            }
            else if(s[2].equals("F")&&s[3].equals("T")){
                double sum = 1.0;
                while(bl>0){
                    sum*=ch--;
                    bl--;
                }
                pos[i] = 1.0/sum;
            }
            else if(s[2].equals("T")&&s[3].equals("F")){
                double total =0;// fraction(ch-bl+1,ch)/(double)fraction(1,bl);
                for (int j = 0; j < bl ; j++) {
                    total+=fraction(ch-(bl-j)+1,ch)*Math.pow(bl-j,j)/(double)fraction(1,bl-j);
                }
                pos[i] = 1.0/total;
            }
            else{
                double total = 0;

                total=fraction(ch-bl+1,ch)/(double)fraction(1,bl);

                pos[i] = 1.0/total;
            }
        }

        for (int i = 0; i < len; i++) {
            double sm = pos[i];
            int n = i;
            for (int j = i; j < len; j++) {
                if(sm < pos[j]){
                    sm = pos[j];
                    n = j;
                }
            }
            double tem1 = pos[i];
            pos[i] = pos[n];
            pos[n] = tem1;
            String tem2 = names[i];
            names[i] = names[n];
            names[n] = tem2;

        }
