public class Joker extends Player{
    //used to not let make more than a Joker
    public static int numOfJokers=0;
    public Joker(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }
    {
        numOfJokers++;
    }
    //a method used when you kill the joker in the day
    public static void JokerWon(Player a) {
            a.setIs_alive(false);
            System.out.println("\u001B[31m" + "You killed The Joker❗" + "\u001B[0m");
            System.out.println("\u001B[31m" + "\uD83C\uDD79\uD83C\uDD7E\uD83C\uDD7A\uD83C\uDD74\uD83C\uDD81 \uD83C\uDD86\uD83C\uDD7E\uD83C\uDD7D❗" + "\u001B[0m");
            System.exit(0);
        }
    }
