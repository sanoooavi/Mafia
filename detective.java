public class detective extends Player {
    public detective(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }

    public void answering_detective(Player player) {
        if (player.isIs_alive()) {
            if ((player instanceof mafia||player instanceof silencer)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else if (!player.isIs_alive()) {
            System.out.println("suspect is dead");
        }
    }
}
