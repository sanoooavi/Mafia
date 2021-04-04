import java.util.*;
import java.util.Scanner;

public class Main {
    //just to colour some sentences
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
//check_act method checks if this act is part of the game or not
    public static boolean check_acts(String string) {
        if (string.equals("Joker") || string.equals("villager") || string.equals("detective") || string.equals("doctor") || string.equals("bulletproof") || string.equals("mafia") || string.equals("godfather") || string.equals("silencer") || string.equals("informer"))
            return true;
        return false;
    }
//this method is used to see if  the name given  is in the memory of the players' names
    public static boolean check_names(String name, String[] names) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name))
                return true;
        }
        return false;
    }
// this method checks if the assigning part is working or not?for example if there is a role or not!and both upper methods are used here
    public static boolean check_assign(String string, String[] names) {
        String[] check = string.split(" ");
        if (!check[0].equals("assign_role")) {
            return false;
        } else if (!check_names(check[1], names) && !check_acts(check[2])) {
            //user not found
            System.out.println("\u001B[31m" + "\uD835\uDC2E\uD835\uDC2C\uD835\uDC1E\uD835\uDC2B \uD835\uDC27\uD835\uDC28\uD835\uDC2D \uD835\uDC1F\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC1D" + "\u001B[0m");
            System.out.println("\u001B[31m" + "role not found");
            return false;
        } else if (!check_names(check[1], names)) {
            //user not found
            System.out.println("\u001B[31m" + "\uD835\uDC2E\uD835\uDC2C\uD835\uDC1E\uD835\uDC2B \uD835\uDC27\uD835\uDC28\uD835\uDC2D \uD835\uDC1F\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC1D" + "\u001B[0m");
            return false;
        } else if (!check_acts(check[2])) {
            System.out.println("\u001B[31m" + "role not found" + "\u001B[0m");
            return false;
        }
        return true;
    }

//this method news our players and give them all  information they need
    public static Player give_acts(String name, String act) {
        if (act.equals("Joker")) {
            if (Joker.numOfJokers == 0) {
                return new Joker(name, "Joker", true, false, 0, 0);
            }
        } else if (act.equals("villager")) {
            return new villager(name, "villager", true, false, 0, 0);
        } else if (act.equals("informer")) {
            return new informer(name, "informer", true, false, 0, 0);
        } else if (act.equals("detective")) {
            return new detective(name, "detective", true, false, 0, 0);
        } else if (act.equals("doctor")) {
            return new doctor(name, "doctor", true, false, 0, 0);
        } else if (act.equals("bulletproof")) {
            return new bulletproof(name, "bulletproof", true, false, 0, 0);
        } else if (act.equals("mafia")) {
            return new mafia(name, "mafia", true, false, 0, 0);
        } else if (act.equals("godfather")) {
            return new godfather(name, "godfather", true, false, 0, 0);
        } else if (act.equals("silencer")) {
            return new silencer(name, "silencer", true, false, 0, 0);
        }
        return null;
    }
//check if we can start the game or not and then give the full info
    public static boolean start_game(Player[] players) {
        int nullish = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                nullish++;
            }
        }
        if (nullish != 0) {
            System.out.println("\u001B[31m" + "one or more player do not have a role" + "\u001B[0m");
            return false;
        } else {
            for (int i = 0; i < players.length; i++) {
                System.out.println(players[i].getName() + ":" + " " + players[i].getAct());
            }
            System.out.println("Ready? Set! Go.");
        }
        return true;
    }
//to see if this person had an act before or not
    public static boolean ReturnIfHasBeenInvolved(String name, int counter, Player[] players) {
        for (int i = 0; i < counter; i++) {
            if (name.equals(players[i].getName()))
                return false;
        }
        return true;
    }
//to give number of mafias
    public static int giveNumbersOFMafias(Player[] players) {
        int CounterOFMafias = 0;

        for (int i = 0; i < players.length; i++) {
            if ((players[i] instanceof mafia || players[i] instanceof godfather || players[i] instanceof silencer) && players[i].isIs_alive()) {
                CounterOFMafias++;
            } else if (players[i] == null) {
                return 0;
            }
        }
        return CounterOFMafias;
    }
//to give number of villagers
    public static int giveNumbersOFVillagers(Player[] players) {
        int CounterOFVillagers = 0;
        for (int i = 0; i < players.length; i++) {
            if ((players[i] instanceof villager || players[i] instanceof detective || players[i] instanceof doctor || players[i] instanceof bulletproof || players[i] instanceof informer) && players[i].isIs_alive()) {
                CounterOFVillagers++;
            } else if (players[i] == null) {
                return 0;
            }
        }
        return CounterOFVillagers;
    }
//shows numbers of villagers and mafias
    public static void giveReport(Player[] players) {
        int NumsOFMafias = giveNumbersOFMafias(players);
        int NumsOFVillagers = giveNumbersOFVillagers(players);
        System.out.println("Mafia = " + NumsOFMafias + "\n" + "Villager = " + NumsOFVillagers);
    }
//check about the game situation and shows the winner
    public static void isEndOfTheGame(Player[] players) {
        int NumsOFMafias = giveNumbersOFMafias(players);
        int NumsOFVillagers = giveNumbersOFVillagers(players);
        if (NumsOFMafias >= NumsOFVillagers) {
            System.out.println("\u001B[31m" + "\uD83C\uDD7C\uD83C\uDD70\uD83C\uDD75\uD83C\uDD78\uD83C\uDD70 \uD83C\uDD86\uD83C\uDD7E\uD83C\uDD7D❗" + "\u001B[0m");
            System.exit(0);
        } else if (NumsOFMafias == 0) {
            System.out.println("\u001B[32m" + "\uD83C\uDD85\uD83C\uDD78\uD83C\uDD7B\uD83C\uDD7B\uD83C\uDD70\uD83C\uDD76\uD83C\uDD74\uD83C\uDD81\uD83C\uDD82 \uD83C\uDD86\uD83C\uDD7E\uD83C\uDD7D❗" + "\u001B[0m");
            System.exit(0);
        }
    }
//return the player if you give a name
    public static Player ReturnPlayerFromName(String name, Player[] player) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                return player[i];
            }
        }
        return null;
    }

    public static void namak() {
        System.out.print("\u001B[35m" + "────────────────────────────────────────────────────────────────────────────────\n" +
                "─██████──────────██████─██████████████─██████████████─██████████─██████████████─\n" +
                "─██▒▒██████████████▒▒██─██▒▒▒▒▒▒▒▒▒▒██─██▒▒▒▒▒▒▒▒▒▒██─██▒▒▒▒▒▒██─██▒▒▒▒▒▒▒▒▒▒██─\n" +
                "─██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██─██▒▒██████▒▒██─██▒▒██████████─████▒▒████─██▒▒██████▒▒██─\n" +
                "─██▒▒██████▒▒██████▒▒██─██▒▒██──██▒▒██─██▒▒██───────────██▒▒██───██▒▒██──██▒▒██─\n" +
                "─██▒▒██──██▒▒██──██▒▒██─██▒▒██████▒▒██─██▒▒██████████───██▒▒██───██▒▒██████▒▒██─\n" +
                "─██▒▒██──██▒▒██──██▒▒██─██▒▒▒▒▒▒▒▒▒▒██─██▒▒▒▒▒▒▒▒▒▒██───██▒▒██───██▒▒▒▒▒▒▒▒▒▒██─\n" +
                "─██▒▒██──██████──██▒▒██─██▒▒██████▒▒██─██▒▒██████████───██▒▒██───██▒▒██████▒▒██─\n" +
                "─██▒▒██──────────██▒▒██─██▒▒██──██▒▒██─██▒▒██───────────██▒▒██───██▒▒██──██▒▒██─\n" +
                "─██▒▒██──────────██▒▒██─██▒▒██──██▒▒██─██▒▒██─────────████▒▒████─██▒▒██──██▒▒██─\n" +
                "─██▒▒██──────────██▒▒██─██▒▒██──██▒▒██─██▒▒██─────────██▒▒▒▒▒▒██─██▒▒██──██▒▒██─\n" +
                "─██████──────────██████─██████──██████─██████─────────██████████─██████──██████─\n" +
                "────────────────────────────────────────────────────────────────────────────────\n" + "\u001B[0m");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        String[] names = null;//an array of the player's names
        Player[] players = null;
        int counter = 0;//to know how many people are playing
        int countOfStartGames = 0;//how many time the game was started
        int countOfCreateGames = 0;//how many time the game was created
        ArrayList<String> night_voters = null;
        namak();
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            String[] array = str.split(" ");
            if (array[0].equals("create_game")) {
                if (countOfCreateGames != 0) {
                    System.out.println("game has already started");
                    continue;
                } else {
                    if (array.length > 1) {
                        countOfCreateGames++;
                        names = str.substring(12).split(" ");
                        players = new Player[names.length];
                    }
                }

            } else if (array[0].equals("assign_role")) {
                int nmd = 0;
                if (countOfCreateGames == 0) {
                    System.out.println("no game created");
                    continue;
                } else {
                    // while(!check_assign(str, players)){
                    //  str = scanner.nextLine();
                    // }
                    if (array.length == 1 || array.length == 2) {
                        System.out.println("assign_role without name and role doesn't mean!!!");
                    } else if (check_assign(str, names)) {
                        for (int i = 0; i < counter; i++) {
                            if (players[i].getName().equals(array[1])) {
                                nmd++;
                                System.out.println("this guy has an act already!");
                            }
                        }
                        if (nmd == 0) {
                            if(array[2].equals("Joker")&& Joker.numOfJokers!=0){
                                System.out.println("This game can not have more than one Joker");
                            }
                            else {
                                players[counter++] = give_acts(array[1], array[2]);
                            }
                        }
                    }
                }
            } else if (array[0].equals("start_game")) {
                if (countOfCreateGames == 0) {
                    System.out.println("no game created");
                } else if (countOfStartGames != 0) {
                    System.out.println("game has already started");
                } else {
                    countOfStartGames++;
                    while (!start_game(players)) {
                        System.out.println("Give a role to those who have no role");
                        str = scanner.nextLine();
                        String[] array2 = str.split(" ");
                        if (check_assign(str, names)) {
                            if (ReturnIfHasBeenInvolved(array2[1], counter, players)) {
                                players[counter++] = give_acts(array2[1], array2[2]);
                            } else {
                                System.out.println("This person has already been involved");
                            }
                        }
                    }
                    String[] swapp = null;
                    while (true) {
                        new Date(players);
                        System.out.println("Day" + " " + Date.day_counter);
                        if (night_voters != null) {
                            String name = Night.ReportOFNightName(players, Night.getKickedOut(night_voters, players));
                            Night.TriedToKill(Night.getKickedOut(night_voters, players), name);
                            if (name != null) {
                                System.out.println(name + " was killed");
                            } else {
                                System.out.println("nobody died last night");
                            }
                        }
                        Night.IsSilence(players);
                        if (swapp != null) {
                            System.out.println(swapp[0] + " swapped characters with " + swapp[1]);
                        }
                        Night.AfterNight(players);
                        isEndOfTheGame(players);
                        while (scanner.hasNextLine()) {
                            str = scanner.nextLine();
                            if (str.contains("end_vote")) {
                                break;
                            }
                            if (str.contains("get_game_state")) {
                                giveReport(players);
                                continue;
                            }
                            if (str.contains("swap_character")) {
                                System.out.println("voting in progress");
                                continue;
                            }
                            if (str.contains("start_game") || str.contains("create_game")) {
                                System.out.println("game has already started");
                                continue;
                            }
                            Date.check_voters(str);
                        }
                        Date.ReportOFDay(players);
                        Date.deleteVotedPeopleFromLastDay(players);
                        Date.delete_Silence();
                        isEndOfTheGame(players);
                        new Night(players);
                        System.out.println("Night" + " " + Night.night_counter);
                        Night.givNamesOfAwakens(players);
                        night_voters = new ArrayList<>();
                        while (scanner.hasNextLine()) {
                            str = scanner.nextLine();
                            if (str.contains("end_night")) {
                                break;
                            }
                            if (str.contains("get_game_state")) {
                                giveReport(players);
                                continue;
                            }
                            if (str.contains("start_game") || str.contains("create_game")) {
                                System.out.println("game has already started");
                                continue;
                            }
                            if (str.contains("swap_character")) {
                                System.out.println("can’t swap before end of night");
                                continue;
                            }
                            if (Night.check_voters(str)) {
                                night_voters.add(str);
                            }
                        }
                        //swap_part
                        String swapper = scanner.nextLine();
                        String[] swap = swapper.split(" ");
                        if (!(ReturnPlayerFromName(swap[1], players).isIs_alive()) || !(ReturnPlayerFromName(swap[2], players)).isIs_alive()) {
                            System.out.println("user is dead");
                            swapp=null;
                        } else {
                            Swap.swapping(ReturnPlayerFromName(swap[1], players), ReturnPlayerFromName(swap[2], players));
                            swapp = new String[2];
                            swapp[0] = swap[1];
                            swapp[1] = swap[2];
                        }
                    }
                }
            } else if (array[0].equals("get_game_state")) {
                if (countOfCreateGames != 0) {
                    giveReport(players);
                    //no game created
                } else
                    System.out.println("\uD835\uDC8F\uD835\uDC90 \uD835\uDC88\uD835\uDC82\uD835\uDC8E\uD835\uDC86 \uD835\uDC84\uD835\uDC93\uD835\uDC86\uD835\uDC82\uD835\uDC95\uD835\uDC86\uD835\uDC85");
            } else {
                System.out.println("This order is out of range of the game");
            }
        }
    }
}
//https://github.com/sanoooavi/Mafia.git
