public abstract class Player {
    String name;
    String act = null;
    boolean is_alive = true;
    boolean is_silence;
    public int votes;
    public int has_voted_atNight = 0;
    public int has_additional_life ;

    public Player(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        this.name = name;
        this.act = act;
        this.is_alive = is_alive;
        this.is_silence = is_silence;
        this.votes = votes;
        this.has_additional_life=has_additional_life;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public void setIs_alive(boolean is_alive) {
        this.is_alive = is_alive;
    }

    public void setIs_silence(boolean is_silence) {
        this.is_silence = is_silence;
    }

    public int getHas_voted_atNight() {
        return has_voted_atNight;
    }

    public int getHas_additional_life() {
        return has_additional_life;
    }

    public boolean isIs_alive() {
        return is_alive;
    }

    public boolean isIs_silence() {
        return is_silence;
    }

    public String getAct() {
        return act;
    }

    public int getVotes() {
        return votes;
    }

    public String getName() {
        return name;
    }

    public void setHas_additional_life() {
        this.has_additional_life++;
    }
    public void resetHas_additional_life() {
        this.has_additional_life=0;
    }

    public void is_voted() {
        this.votes++;
    }

    public void after_day() {
        this.votes = 0;
    }

    public void Has_voted_atNight() {
        this.has_voted_atNight++;
    }

    public void after_Night() {
        this.has_voted_atNight = 0;
    }
    public void MakeSth(Player a, Player b){
    }
}
