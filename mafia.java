public class mafia extends Player {
    public int has_voted_atNight = 0;
    public mafia(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);

    }
}
