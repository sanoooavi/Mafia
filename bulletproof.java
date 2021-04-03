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

    public static String DealingWithOneSelected(Player player) {
        String str =null;
        if (player.getHas_additional_life() == 0 && ((bulletproof) player).getHasShield() == 1) {
            ((bulletproof) player).reduceShield();
        } else if (player.getHas_additional_life() == 0 && ((bulletproof) player).getHasShield() == 0) {
            str = player.getName();
            player.setIs_alive(false);
        }
        return str;
    }

    public static String DealingWithSelected(Player player1, Player player2) {
        String str =null;
        if (player1.getHas_additional_life() == 1 && player2.getHas_additional_life() == 0 && ((bulletproof) player1).getHasShield() == 1 && ((bulletproof) player2).getHasShield() == 1) {
            ((bulletproof) player2).reduceShield();
        } else if (player1.getHas_additional_life() == 0 && player2.getHas_additional_life() == 1 && ((bulletproof) player1).getHasShield() == 1 && ((bulletproof) player2).getHasShield() == 1) {
            ((bulletproof) player1).reduceShield();
        } else if (player1.getHas_additional_life() == 1 && player2.getHas_additional_life() == 0 && ((bulletproof) player1).getHasShield() == 0 && ((bulletproof) player2).getHasShield() == 0) {
            str = player2.getName();
            player2.setIs_alive(false);
        } else if (player1.getHas_additional_life() == 0 && player2.getHas_additional_life() == 1 && ((bulletproof) player1).getHasShield() == 0 && ((bulletproof) player2).getHasShield() == 0) {
            str = player1.getName();
            player1.setIs_alive(false);
        }
        return str;
    }

    public static String DealingWithTwoSelected_OneBullet(Player player1, Player player2) {
        String str =null;
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
            str = non_Bull.getName();
            non_Bull.setIs_alive(false);
        } else if (Bull.getHas_additional_life() == 0 && non_Bull.getHas_additional_life() == 1 && ((bulletproof) Bull).getHasShield() == 0) {
            str = Bull.getName();
            Bull.setIs_alive(false);
        } else if (Bull.getHas_additional_life() == 1 && non_Bull.getHas_additional_life() == 0 && ((bulletproof) Bull).getHasShield() == 0) {
            str = non_Bull.getName();
            non_Bull.setIs_alive(false);
        }
        else if (Bull.getHas_additional_life() == 1 && non_Bull.getHas_additional_life() == 0 && ((bulletproof) Bull).getHasShield() == 1) {
            str = non_Bull.getName();
            non_Bull.setIs_alive(false);
        }
        return str;
    }

}
