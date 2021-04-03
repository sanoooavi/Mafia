import java.util.ArrayList;
import java.util.Random;
public class informer extends Player{
    public informer(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }
    public static String findJoker(Player[]players){
        Random rand = new Random();
        int n=rand.nextInt(players.length);
            if (players[n] instanceof Joker)
                return players[n].getName();
        return null;
    }
    public static String findAliveMafia(Player[]players){
        Random rand = new Random();
        int n=rand.nextInt(players.length);
            if (players[n] instanceof mafia && players[n].isIs_alive())
                return players[n].getName();
        return null;
    }
    public static Player findToKill( ArrayList<Player>a){
        Random rand = new Random();
        int n=rand.nextInt(a.size());
            if (a.get(n).isIs_alive())
                return a.get(n);
        return null;
    }
    //this method gives a fact randomly when the informer dies
    public static void whatToInform(Player[]players, Player informer, ArrayList<Player> a){
        System.out.println(informer.getName()+" was an informer");
        Random rand = new Random();
        while (true) {
            int n = rand.nextInt(4);
            if (n == 0) {
                System.out.println("Number of alive mafia : " + Main.giveNumbersOFMafias(players));
                break;
            } else if (n == 1) {
                String str = findJoker(players);
                if (str!=null){
                    System.out.println("There is a joker who’s name starts with " + str.charAt(0));
                    break;
                }
            } else if (n == 2) {
                String str = findAliveMafia(players);
                if (str!=null){
                    System.out.println("There is a mafia who’s name starts with " + str.charAt(0));
                    break;
                }
            } else  {
                Player b=findToKill(a);
                if(b!=null){
                    System.out.println(b.getName()+"was voted to be killed");
                    break;
                }
            }
        }
    }
}
