import java.util.Arrays;

public class Date {
    public static int day_counter = 0;

    {
        day_counter++;
    }

    public static Player[] player;
    //using colours
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    //
    public Date(Player[] player) {
        this.player = player;
    }

    public static boolean validation_Of_Names(String[] name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name[0])) {
                for (int j = 0; j < player.length; j++) {
                    if (player[j].getName().equals(name[1]))
                        return true;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean is_vote_dead(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (player[i].isIs_alive())
                    return true;
            }
        }
        return false;
    }

    public static boolean is_voter_Silenced(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (player[i].isIs_silence())
                    return true;
            }
        }
        return false;
    }

    public static Player ReturnPlayerFromName(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                return player[i];
            }
        }
        return null;
    }

    public static void check_voters(String string) {
        if(string.length()<3){
            System.out.println("this in not a true command!");
        }
        else {
            String[] names = string.split(" ");
            if (!validation_Of_Names(names)) {
                System.out.println("\u001B[31m" + "𝑼𝒔𝒆𝒓 𝒏𝒐𝒕 𝒇𝒐𝒖𝒏𝒅" + "\u001B[0m");
            } else if (is_voter_Silenced(names[0])) {
                //voter is silenced
                System.out.println("\u001B[34m" + "\uD835\uDC97\uD835\uDC90\uD835\uDC95\uD835\uDC86\uD835\uDC93 \uD835\uDC8A\uD835\uDC94 \uD835\uDC94\uD835\uDC8A\uD835\uDC8D\uD835\uDC86\uD835\uDC8F\uD835\uDC84\uD835\uDC86\uD835\uDC85" + "\u001B[0m");
            } else if (!is_vote_dead(names[1])) {
                System.out.println("\u001B[31m\uD835\uDC97\uD835\uDC90\uD835\uDC95\uD835\uDC86\uD835\uDC86 \uD835\uDC82\uD835\uDC8D\uD835\uDC93\uD835\uDC86\uD835\uDC82\uD835\uDC85\uD835\uDC9A \uD835\uDC85\uD835\uDC86\uD835\uDC82\uD835\uDC85\u001B[0m");
            } else if (!is_vote_dead(names[0])) {
                System.out.println("\u001B[31m\uD835\uDC97\uD835\uDC90\uD835\uDC95\uD835\uDC86\uD835\uDC93 \uD835\uDC82\uD835\uDC8D\uD835\uDC93\uD835\uDC86\uD835\uDC82\uD835\uDC85\uD835\uDC9A \uD835\uDC85\uD835\uDC86\uD835\uDC82\uD835\uDC85\u001B[0m");
            } else {
                ReturnPlayerFromName(names[1]).is_voted();
            }
        }
    }

    public static void delete_Silence() {
        for (int i = 0; i < player.length; i++) {
            player[i].setIs_silence(false);
        }
    }

    public static void deleteVotedPeopleFromLastDay(Player[] players) {
        for (Player value : players) {
            value.after_day();
        }
    }

    public static void ReportOFDay(Player[] players) {
        Player goingToDie = null;
        int valueOfVotes[] = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            valueOfVotes[i] = players[i].getVotes();
        }
        Arrays.sort(valueOfVotes);
        for (int i = 0; i < players.length; i++) {
            if (players[i].getVotes() == valueOfVotes[players.length - 1]) {
                goingToDie = players[i];
                break;
            }
        }
        if (valueOfVotes[players.length - 1] == valueOfVotes[players.length - 2]) {
            //nobody died
            System.out.println("\u001B[32m\uD835\uDC8F\uD835\uDC90\uD835\uDC83\uD835\uDC90\uD835\uDC85\uD835\uDC9A \uD835\uDC85\uD835\uDC8A\uD835\uDC86\uD835\uDC85\u001B[0m");
        } else if (goingToDie instanceof Joker) {
            //Joker won!
            Joker.JokerWon(goingToDie);
        } else if (goingToDie instanceof informer) {
            System.out.println(goingToDie.getName() + " was Killed");
            goingToDie.setIs_alive(false);
         //   informer.whatToInform(players, goingToDie, a);
        } else {
            goingToDie.setIs_alive(false);
            System.out.println("\u001B[31m" + goingToDie.getName() + " " + "died" + "\u001B[0m");
        }
        for (Player value : players) {
            value.after_day();
        }
    }
}
