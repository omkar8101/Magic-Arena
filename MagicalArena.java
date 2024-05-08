public class MagicalArena {
    private Player player1;
    private Player player2;

    public MagicalArena(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    // Method to start the match
    public void startMatch() {
        Player attacker, defender;

        // Determine the initial attacker
        if (player1.getHealth() < player2.getHealth()) {
            attacker = player1;
            defender = player2;
        } else {
            attacker = player2;
            defender = player1;
        }

        // Continue the game until one of the players' health drops to 0 or below
        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            playTurn(attacker, defender);
            // Swap attacker and defender
            Player temp = attacker;
            attacker = defender;
            defender = temp;
        }

        // Determine the winner
        if (player1.getHealth() > 0) {
            System.out.println(player1.getName() + " wins!");
        } else {
            System.out.println(player2.getName() + " wins!");
        }
    }

    // Method to handle a turn of attack and defense
    private void playTurn(Player attacker, Player defender) {
        int attackRoll = attacker.rollDice();
        int defendRoll = defender.rollDice();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defendStrength = defender.getStrength() * defendRoll;

        int damageInflicted = attackDamage - defendStrength;

        if (damageInflicted > 0) {
            defender.setHealth(defender.getHealth() - damageInflicted);
        }

        System.out.println(attacker.getName() + " attacks with a roll of " + attackRoll +
                           " causing " + attackDamage + " damage.");
        System.out.println(defender.getName() + " defends with a roll of " + defendRoll +
                           " blocking " + defendStrength + " damage.");
        System.out.println(defender.getName() + "'s remaining health: " + defender.getHealth());
    }

    public static void main(String[] args) {
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);
        MagicalArena arena = new MagicalArena(playerA, playerB);
        arena.startMatch();
    }
}
