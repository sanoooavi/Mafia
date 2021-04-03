import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Night {
    public static int night_counter = 0;
    public static Player[] player;

    public Night(Player[] player) {
        this.player = player;
    }
//used to give the number of night
    {
        night_counter++;
    }
//at the first of night we print all the awakens
    public static void givNamesOfAwakens(Player[] player) {
        for (int i = 0; i < player.length; i++) {
            if ((player[i] instanceof mafia || player[i] instanceof detective || player[i] instanceof doctor || player[i] instanceof godfather || player[i] instanceof silencer) && player[i].isIs_alive()) {
                System.out.println(player[i].getName() + ":" + " " + player[i].getAct());
            }
        }
    }
//again to check if the name is valid or not(also this is used for the command i mean both names)
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
//just to check if a name is valid or not
    public static boolean validation_Of_Names(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
//you can give a name and this method returns it's player
    public static Player ReturnPlayerFromName(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                return player[i];
            }
        }
        return null;
    }
//if you command a name which is not a night awaken this method comes to help
    public static boolean is_nightAwaken(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (player[i].getAct().equals("mafia") || player[i].getAct().equals("detective") || player[i].getAct().equals("doctor") || player[i].getAct().equals("godfather") || player[i].getAct().equals("silencer"))
                    return true;
            }
        }
        return false;
    }
//if the person is dead or not
    public static boolean is_vote_dead(String name) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getName().equals(name)) {
                if (!player[i].isIs_alive())
                    return true;
            }
        }
        return false;
    }
//this method is used while printing the people who were assassination case
    public static boolean check_non_repeated_name(ArrayList<Player> IntentionalCase, int i) {
        for (int j = 0; j < i; j++) {
            if (IntentionalCase.get(i).getName().equals(IntentionalCase.get(j).getName()))
                return false;
        }
        return true;
    }
//to sort the array list by the votes they have
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
//to check if the command we are giving at night is valid or not
    public static boolean check_voters(String string) {
        if(string.length()<3){
            System.out.println("this in not a true command!");
            return false;
        }
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
        } else if (ReturnPlayerFromName(names[0]) instanceof mafia || ReturnPlayerFromName(names[0]) instanceof godfather || (ReturnPlayerFromName(names[0]) instanceof silencer && (((silencer) ReturnPlayerFromName(names[0])).getParticipation() >= 1))) {
            return true;
        } else {
            question_and_answer(string);
        }
        return false;
    }
//to do the act of doctor or detective or silencer we use this method
    public static void question_and_answer(String order) {
        String[] str = order.split(" ");
        Player responder = null;
        Player questioner = null;
        questioner = ReturnPlayerFromName(str[0]);
        responder = ReturnPlayerFromName(str[1]);
        if (questioner instanceof silencer) {
            ((silencer) questioner).increaseParticipation();
            questioner.MakeSth(questioner, responder);
        } else if (questioner instanceof doctor && questioner.getHas_voted_atNight() == 0) {
            questioner.MakeSth(questioner, responder);
            //questioner.Has_voted_atNight();
           // responder.setHas_additional_life();
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
//this method is used to save all the mafias votes and choose their last commands
    public static ArrayList<Player> getKickedOut(ArrayList<String> night_voters,Player[]players) {
     //   if (night_voters ==null) {
         //   return null;
        //}
        ArrayList<Player> a = new ArrayList<>();
        ArrayList<Player> c = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        for (int i = night_voters.size() - 1; i >= 0; i--) {
            String[] names = night_voters.get(i).split(" ");
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
        for (int i=0;i<players.length;i++){
          for(int j=0;j<a.size() ;j++){
             if(players[i].getName().equals(a.get(j).getName())){
                 players[i].is_voted();
             }
           }
        }
        for (int i=0;i< players.length;i++){
            if(players[i].getVotes()>=1)
            c.add(players[i]);
        }
        return c;
    }
//this method is used when we are printing the people who were gonna dye but not all
    public static void TriedToKill(ArrayList<Player>a,String nameofDead) {
        String str;
        if (a.size() != 0) {
            str = "mafia tried to kill ";
            for (int i = 0; i < a.size(); i++)
                if (check_non_repeated_name(a, i))
                    if(!(a.get(i)instanceof bulletproof && nameofDead==null))
                    str += a.get(i).getName() + " ";
        } else {
            str = "noBody voted for someone last night";
        }
        if(!str.equals("mafia tried to kill ")){
            System.out.println(str);
        }
    }
//this whole method gives the name of the dead person and kill it
    public static String ReportOFNightName(Player[] players, ArrayList<Player> IntentionalCase) {
        //IntentionalCase is an array list which have all the people who were voted
        String nameOfDeath = null;
        //IntentionalCase is an array list which have all the people who were voted
        int count = IntentionalCase.size();//numberOfIntentional
        if (IntentionalCase.size() != 0) {
            bubbleSort(IntentionalCase);
            int countOfMaxVotes = 0;
            for (int i = 0; i < count; i++) {
                if (IntentionalCase.get(i).getVotes() == IntentionalCase.get(count - 1).getVotes()) {
                    countOfMaxVotes++;
                }
            }
            if (countOfMaxVotes == 1) {
                if (IntentionalCase.get(count - 1) instanceof bulletproof) {
                    nameOfDeath = bulletproof.DealingWithOneSelected(IntentionalCase.get(count - 1));
                } else if (IntentionalCase.get(count - 1).getHas_additional_life() == 0) {
                    nameOfDeath = IntentionalCase.get(count - 1).getName();
                    IntentionalCase.get(count - 1).setIs_alive(false);
                    if (IntentionalCase.get(count - 1) instanceof informer) {
                        informer.whatToInform(players, IntentionalCase.get(count - 1), IntentionalCase);
                    }
                }
            } else if (countOfMaxVotes == 2) {
                if (IntentionalCase.get(count - 1) instanceof bulletproof && IntentionalCase.get(count - 2) instanceof bulletproof) {
                    nameOfDeath = bulletproof.DealingWithSelected(IntentionalCase.get(count - 1), IntentionalCase.get(count - 2));
                } else if ((IntentionalCase.get(count - 1) instanceof bulletproof && !(IntentionalCase.get(count - 2) instanceof bulletproof)) || (!(IntentionalCase.get(count - 1) instanceof bulletproof) && IntentionalCase.get(count - 2) instanceof bulletproof)) {
                    nameOfDeath = bulletproof.DealingWithTwoSelected_OneBullet(IntentionalCase.get(count - 1), IntentionalCase.get(count - 2));
                } else {
                    if (IntentionalCase.get(count - 1).getHas_additional_life() == 0 && IntentionalCase.get(count - 2).getHas_additional_life() == 1) {
                        nameOfDeath = IntentionalCase.get(count - 1).getName();
                        IntentionalCase.get(count - 1).setIs_alive(false);
                        if (IntentionalCase.get(count - 1) instanceof informer) {
                            informer.whatToInform(players, IntentionalCase.get(count - 1), IntentionalCase);
                        }
                    } else if (IntentionalCase.get(count - 1).getHas_additional_life() == 1 && IntentionalCase.get(count - 2).getHas_additional_life() == 0) {
                        nameOfDeath = IntentionalCase.get(count - 2).getName();
                        IntentionalCase.get(count - 2).setIs_alive(false);
                        if (IntentionalCase.get(count - 2) instanceof informer) {
                            informer.whatToInform(players, IntentionalCase.get(count - 2), IntentionalCase);
                        }
                    }
                }
            }
        }
        return nameOfDeath;
    }
//to print the person who is silence during the next day
    public static void IsSilence(Player[] players) {
        String str = "";
        for (int i = 0; i < players.length; i++) {
            if (players[i].isIs_silence()) {
                str += "Silenced " + players[i].getName();
            }
        }
        if (!str.equals(""))
            System.out.println(str);
    }
//after the night we put everything back to normal
    public static void AfterNight(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] instanceof silencer) {
                ((silencer) players[i]).resetParticipation();
            }
            players[i].after_Night();
            players[i].resetHas_additional_life();
            players[i].after_day();
        }
    }
}
