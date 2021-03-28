import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Night {
    public static int night_counter = 0;
    public static Player[] player;

    public Night(Player[] player) {
        this.player = player;
    }

    {
        night_counter++;
    }

    public static void givNamesOfAwakens(Player[] player) {
        for (int i = 0; i < player.length; i++) {
            if ((player[i] instanceof mafia || player[i] instanceof detective || player[i] instanceof doctor || player[i] instanceof godfather || player[i] instanceof silencer) && player[i].isIs_alive()) {
                System.out.println(player[i].getName() + ":" + " " + player[i].getAct());
            }
        }
    }

    public static boolean validation_Of_Names(String[] name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name[0])) {
                for (int j = 0; j < player.length; j++) {
                    if (player[j].getName().equals(name[1]))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean validation_Of_Names(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
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

    public static boolean is_nightAwaken(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (player[i].getAct().equals("mafia") || player[i].getAct().equals("detective") || player[i].getAct().equals("doctor") || player[i].getAct().equals("godfather") || player[i].getAct().equals("silencer"))
                    return true;
            }
        }
        return false;
    }

    public static boolean is_vote_dead(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (!player[i].isIs_alive())
                    return true;
            }
        }
        return false;
    }

    public static boolean check_non_repeated_name(ArrayList<Player> IntentionalCase, int i) {
        for (int j = 0; j < i; j++) {
            if (IntentionalCase.get(i).getName().equals(IntentionalCase.get(j).getName()))
                return false;
        }
        return true;
    }

    public static void bubbleSort(ArrayList<Player> IntentionalCase) {
        for (int i = 1; i < IntentionalCase.size(); i++) {
            for (int j = 0; j < IntentionalCase.size() - i; j++) {
                if ((IntentionalCase.get(j).getVotes()) > (IntentionalCase.get(j + 1).getVotes())) {
                    // swap Player[j] with Player[j+1]
                    Player temp = IntentionalCase.get(j);
                    IntentionalCase.add(j, IntentionalCase.get(j + 1));
                    IntentionalCase.add(j + 1, temp);
                }
            }
        }
    }

    public static boolean check_voters(String string) {
        String[] names = string.split(" ");
        if (!validation_Of_Names(names)) {
            System.out.println("user not joined");
            return false;
        } else if (is_vote_dead(names[0])) {
            System.out.println("user is dead");
            return false;
        } else if (is_vote_dead(names[1])) {
            System.out.println("votee already dead");
            return false;
        } else if (!is_nightAwaken(names[0])) {
            System.out.println("user can not wake up during night");
            return false;
        } else if (ReturnPlayerFromName(names[0]) instanceof mafia || ReturnPlayerFromName(names[0]) instanceof godfather || (ReturnPlayerFromName(names[0]) instanceof silencer && (((silencer) ReturnPlayerFromName(names[0])).getParticipation()>= 1))) {
            return true;
        } else {
            question_and_answer(string);
        }
        return false;
    }

    public static void question_and_answer(String order) {
        String[] str = order.split(" ");
        Player responder = null;
        Player questioner = null;
        questioner = ReturnPlayerFromName(str[0]);
        responder = ReturnPlayerFromName(str[1]);
        if (questioner instanceof silencer) {
            ((silencer) questioner).increaseParticipation();
            silencer.MakeSilence(questioner, responder);
        } else if (questioner instanceof doctor && questioner.getHas_voted_atNight() == 0) {
            questioner.Has_voted_atNight();
            responder.setHas_additional_life();
        } else if (questioner instanceof detective) {
            if (questioner.getHas_voted_atNight() == 0) {
                questioner.Has_voted_atNight();
                if (validation_Of_Names(str[1])) {
                    Player a = ReturnPlayerFromName(str[1]);
                    ((detective) questioner).answering_detective(a);
                } else {
                    System.out.println("user not found");
                }
            } else if (questioner.getHas_voted_atNight() != 0) {
                System.out.println("detective has already asked");
            }
        }
    }

    public static ArrayList<Player> getKickedOut(ArrayList<String> night_voters) {
        ArrayList<Player> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        for (int i = night_voters.size() - 1; i >= 0; i--) {
            String[] names = night_voters.get(i).split(" ");
           // if (ReturnPlayerFromName(names[0]) instanceof silencer) {
               // b.add(names[1]);
           // }
            if (ReturnPlayerFromName(names[0]).getHas_voted_atNight() == 0) {
                ReturnPlayerFromName(names[0]).Has_voted_atNight();
                b.add(names[1]);
            } else {
                night_voters.remove(i);
            }
        }
        for (int i = 0; i < b.size(); i++) {
            a.add(ReturnPlayerFromName(b.get(i)));
        }
        return a;
    }

    public static void ReportOFNight(Player[] players, ArrayList<Player> IntentionalCase) {
        //IntentionalCase is an array list which have all the people who were voted
        String str = "";
        int count = IntentionalCase.size();//numberOfIntentional
        if (IntentionalCase.size() != 0) {
            bubbleSort(IntentionalCase);
            str = "mafia tried to kill ";
            for (int i = 0; i < count; i++) {
                if (check_non_repeated_name(IntentionalCase, i))
                    str += IntentionalCase.get(i).getName() + " ";
            }
            int countOfMaxVotes = 0;
            for (int i = 0; i < count; i++) {
                if (IntentionalCase.get(i).getVotes() == IntentionalCase.get(count - 1).getVotes()) {
                    countOfMaxVotes++;
                }
            }
            if (countOfMaxVotes == 1) {
                if (IntentionalCase.get(count - 1) instanceof bulletproof) {
                    str = bulletproof.DealingWithOneSelected(str, IntentionalCase.get(count - 1));
                } else {
                    if (IntentionalCase.get(count - 1).getHas_additional_life() == 0) {
                        str += "\n" + IntentionalCase.get(count - 1).getName() + " was killed";
                        ReturnPlayerFromName(IntentionalCase.get(count - 1).getName()).setIs_alive(false);
                    } else {
                        str += "\nno body died last night";
                    }
                }
            } else if (countOfMaxVotes == 2) {
                if (IntentionalCase.get(count - 1) instanceof bulletproof && IntentionalCase.get(count - 2) instanceof bulletproof) {
                    str = bulletproof.DealingWithTwoSelected(str, IntentionalCase.get(count - 1), IntentionalCase.get(count - 2));
                } else if ((IntentionalCase.get(count - 1) instanceof bulletproof && !(IntentionalCase.get(count - 2) instanceof bulletproof)) || (!(IntentionalCase.get(count - 1) instanceof bulletproof) && IntentionalCase.get(count - 2) instanceof bulletproof)) {
                    str = bulletproof.DealingWithTwoSelected_OneBullet(str, IntentionalCase.get(count - 1), IntentionalCase.get(count - 2));
                } else {
                    if (IntentionalCase.get(count - 1).getHas_additional_life() == 0 && IntentionalCase.get(count - 2).getHas_additional_life() == 1) {
                        str += "\n" + IntentionalCase.get(count - 1).getName() + " was killed";
                        ReturnPlayerFromName(IntentionalCase.get(count - 1).getName()).setIs_alive(false);
                    } else if (IntentionalCase.get(count - 1).getHas_additional_life() == 1 && IntentionalCase.get(count - 2).getHas_additional_life() == 0) {
                        str += "\n" + IntentionalCase.get(count - 2).getName() + " was killed";
                        ReturnPlayerFromName(IntentionalCase.get(count - 2).getName()).setIs_alive(false);
                    } else if (IntentionalCase.get(count - 1).getHas_additional_life() == 0 && IntentionalCase.get(count - 2).getHas_additional_life() == 0) {
                        str += "\nno body died last night";
                    }
                }
            } else if (countOfMaxVotes > 2) {
                str += "\nno body died last night";
            }
        } else {
            str += "no body died last night";
        }
        for (int i = 0; i < players.length; i++) {
            if (players[i].isIs_silence()) {
                str += "\nSilenced " + players[i].getName();
            }
        }
        System.out.println(str);
        for (int i = 0; i < players.length; i++) {
            if(players[i] instanceof silencer){
                ((silencer) players[i]).resetParticipation();
            }
            players[i].after_Night();
            players[i].resetHas_additional_life();
            players[i].after_day();

        }
    }
}