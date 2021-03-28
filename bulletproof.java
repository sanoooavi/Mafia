public class bulletproof extends Player {
    public bulletproof(String name, String act, boolean is_alive, boolean is_silence, int votes, int has_additional_life) {
        super(name, act, is_alive, is_silence, votes, has_additional_life);
    }

    public int hasShield = 1;

    public int getHasShield() {
        return hasShield;
    }

    public void reduceShield() {
        this.hasShield--;
    }

    public static String DealingWithOneSelected(String str, Player player) {
        if (player.getHas_additional_life() == 0 && ((bulletproof) player).getHasShield() == 1) {
            str += "\nno body died last night";
            ((bulletproof) player).reduceShield();
        } else if (player.getHas_additional_life() == 0 && ((bulletproof) player).getHasShield() == 0) {
            str += "\n" + player.getName() + " was killed";
            player.setIs_alive(false);
        } else if (player.getHas_additional_life() == 1) {
            str += "\nno body died last night";
        }
        return str;
    }

    public static String DealingWithTwoSelected(String str, Player player1, Player player2) {
        if (player1.getHas_additional_life() == 1 && player2.getHas_additional_life() == 0 && ((bulletproof) player1).getHasShield() == 1 && ((bulletproof) player2).getHasShield() == 1) {
            ((bulletproof) player2).reduceShield();
            str += "\nno body died last night";
        } else if (player1.getHas_additional_life() == 0 && player2.getHas_additional_life() == 1 && ((bulletproof) player1).getHasShield() == 1 && ((bulletproof) player2).getHasShield() == 1) {
            ((bulletproof) player1).reduceShield();
            str += "\nno body died last night";
        } else if (player1.getHas_additional_life() == 1 && player2.getHas_additional_life() == 0 && ((bulletproof) player1).getHasShield() == 0 && ((bulletproof) player2).getHasShield() == 0) {
            str += "\n" + player2.getName() + " was killed";
            player2.setIs_alive(false);
        } else if (player1.getHas_additional_life() == 0 && player2.getHas_additional_life() == 1 && ((bulletproof) player1).getHasShield() == 0 && ((bulletproof) player2).getHasShield() == 0) {
            str += "\n" + player1.getName() + " was killed";
            player1.setIs_alive(false);
        } else {
            str += "\nno body died last night";
        }
        return str;
    }

    public static String DealingWithTwoSelected_OneBullet(String str, Player player1, Player player2) {
        Player Bull = null;
        Player non_Bull = null;
        if (player1 instanceof bulletproof) {
            Bull = player1;
            non_Bull = player2;
        } else {
            Bull = player2;
            non_Bull = player1;
        }
        if (Bull.getHas_additional_life() == 0 && non_Bull.getHas_additional_life() == 0 && ((bulletproof) Bull).getHasShield() == 1) {
            str += "\n" + non_Bull.getName() + " was killed";
            non_Bull.setIs_alive(false);
        } else if (Bull.getHas_additional_life() == 0 && non_Bull.getHas_additional_life() == 1 && ((bulletproof) Bull).getHasShield() == 0) {
            str += "\n" + Bull.getName() + " was killed";
            Bull.setIs_alive(false);
        } else if (Bull.getHas_additional_life() == 1 && non_Bull.getHas_additional_life() == 0 && ((bulletproof) Bull).getHasShield() == 0) {
            str += "\n" + non_Bull.getName() + " was killed";
            non_Bull.setIs_alive(false);
        } else {
            str += "\nno body died last night";
        }
        return str;
    }

}
