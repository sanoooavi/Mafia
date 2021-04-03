public class Swap {

    public static void swapping(Player from, Player to) {
        String temp = from.getName();
        from.name = to.getName();
        to.name = temp;
    }
}
