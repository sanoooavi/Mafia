public class doctor extends Player{

    public doctor(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
  }
    @Override
     public  void MakeSth(Player  questioner, Player responder) {
        questioner.Has_voted_atNight();
         responder.setHas_additional_life();
        }
    }
