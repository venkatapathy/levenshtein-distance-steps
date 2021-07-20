import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public int editDistanceDP(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        int[][] solution;
        
        solution = new int[s1.length + 1][s2.length + 1];
        

        for (int i = 0; i <= s1.length; i++) {
            solution[i][0] = i;
        }

        for (int i = 0; i <= s2.length; i++) {
            solution[0][i] = i;
        }

        int m = s1.length;
        int n = s2.length;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1].equals(s2[j - 1]))
                    solution[i][j] = solution[i - 1][j - 1];
                else
                solution[i][j] = Math.min(solution[i - 1][j] + 1, Math.min(solution[i][j - 1] + 1,solution[i - 1][j - 1] + 1));
                
            }
        }
        
        System.out.println(solution[s1.length][s2.length]);
        //find the optimal path
        int si= s1.length;
        int sj= s2.length;
        ArrayList optimalPath = new ArrayList<String>();

        while(si>0 && sj >0){
            int current = solution[si][sj];

            // edge cases
            if (si - 1 < 0)
            {
                optimalPath.add("Delete '" + s1[si] + "'");
                si--;
                continue;
            }

            if (sj - 1 < 0)
            {
                optimalPath.add("Insert '" + s2[sj] + "'");
                sj--;
                continue;
            }


            int horizontal = solution[si][sj-1];
            int vertical = solution[si-1][sj];
            int diagonal = solution[si-1][sj-1];
            
            
            

            if((diagonal <= horizontal && diagonal <= vertical) && (diagonal == current-1 || diagonal == current)){
                
                if(diagonal == current -1){
                    //add to the Cword
                    optimalPath.add("Replace: "+s1[si-1]+" by "+s2[sj-1]);
                    si = si - 1;
                    sj = sj - 1;
                    
                }
                else{
                    optimalPath.add("Keep: "+s1[si-1]);
                    si=si-1;
                    sj=sj-1;

                }
            }
            else if(horizontal <= diagonal && horizontal <= current-1){
                optimalPath.add("Insert: "+s2[sj-1]);
                sj=sj-1;
            }
            else{
                optimalPath.add("Delete: "+s1[si-1]);
                si=si-1;

            }

                 
        }

        //Collections.reverse(optimalPath);
        System.out.println(optimalPath.toString());

        return solution[s1.length][s2.length];

    }

    public static void main(String[] args) {
        //sourceDocument
        String sourceSentence = "secod third";
        //editedDocument
        String editedSentence = "First second third";
        /* sentence1= sc.nextLine();              //reads string   
        System.out.println("Enter the edited sentence");
        sentence2= sc.nextLine();              //reads string   */
        App ed = new App();
        System.out.println("Edit Distance: " + ed.editDistanceDP(sourceSentence, editedSentence));

        
        
    }
}