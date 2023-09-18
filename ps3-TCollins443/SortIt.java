import java.util.*;

/**
   CSIS 225 Problem Set 3 Comparators Task

   @author Jim Teresco (starter framework) 
   @author Implemented by Saif Ullah, Jonathan Masih, and Trevor Collins
*/


class Team {

    protected String sport;
    protected String coach;
    protected int numPlayers;

    public Team(String s, String c, int n) {

        sport = s;
        coach = c;
        numPlayers = n;
    }

    public String getSport() {

        return sport;
    }

    public String getCoach() {

        return coach;
    }

    public int getNumPlayers() {

        return numPlayers;
    }

    public String toString() {

        return "Team for " + sport + ": coached by " + coach +
            " has " + numPlayers + " players";
    }
}


/**
 * Prints array in decreasing alphabetical order by the sport name
 * 
 * @return int value of the current order 
 */
class alphabeticalOrderTeam implements Comparator<Team>{

    @Override
    public int compare(Team team1, Team team2){
        //String findSportName = sportName.getSport();
        //int findNumOfPlayers = numOfPlayers.getNumPlayers();
        if(team1.getSport().compareTo(team2.getSport()) > 0){
            return -1;
        } else if(team1.getSport().compareTo(team2.getSport()) < 0){
            return 1;
        } else{
            return 0;
        }
    }
}

/**
 *  Prints array in increasing order by players
 * 
 * @return int value of the current order
 */
class NumOrderPlayer implements Comparator<Team>{

@Override
    public int compare(Team team1, Team team2){
        if(team1.getNumPlayers() == team2.getNumPlayers()){
            return 0;
        } else if(team1.getNumPlayers() < team2.getNumPlayers()){
            return -1;
        } else{
            return 1;
        }
    }
}


public class SortIt {

    public static void main(String args[]) {

        Team teams[] = new Team[5];
        teams[0] = new Team("Baseball", "Boone", 27);
        teams[1] = new Team("Hockey", "Bennett", 24);
        teams[2] = new Team("Badminton", "Brown", 5);
        teams[3] = new Team("Soccer", "Bruce", 15);
        teams[4] = new Team("Fencing", "Barry", 10);

        System.out.println("Original Order");
        System.out.println(Arrays.toString(teams));
        
        // once you have your comparators, use groups of lines like this to
        // demonstrate their correctness
        //System.out.println("Ordered by length of instructor name");
        //Arrays.sort(teams, new InstrLenComparator());
        //System.out.println(Arrays.toString(teams));

        Arrays.sort(teams, new alphabeticalOrderTeam());
        System.out.println("Printing out teams in decreasing alphabetical order: ");
        System.out.println(Arrays.toString(teams));

        Arrays.sort(teams, new NumOrderPlayer());
        System.out.println("Printing out teams in increasing order: ");
        System.out.println(Arrays.toString(teams));


        

        
    }
}
