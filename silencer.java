public class silencer extends Player {
    public int participation=0;
    public silencer(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }

    public static void MakeSilence(Player Silencer, Player Is_silent) {
        if (((silencer)Silencer).getParticipation() == 1) {
            Is_silent.setIs_silence(true);
        }
    }
    public void increaseParticipation() {
        this.participation++;
    }
    public int getParticipation() {
        return participation;
    }
    public void resetParticipation() {
        this.participation=0;
    }
}
