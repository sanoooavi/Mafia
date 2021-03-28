public class Joker extends Player{
    public Joker(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }
    public static void JokerWon() {
            System.out.println("\u001B[31m" + "\uD83C\uDD79\uD83C\uDD7E\uD83C\uDD7A\uD83C\uDD74\uD83C\uDD81 \uD83C\uDD86\uD83C\uDD7E\uD83C\uDD7D❗" + "\u001B[0m");
            System.exit(0);
        }
    }
