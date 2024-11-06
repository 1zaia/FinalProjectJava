public enum Player {
    X, O, NONE;
    private static String playerXName;
    private static String playerOName;

    public static void setPlayerName(Player player, String name) {
        if (player == X) {
            playerXName = name;
        } else if (player == O) {
            playerOName = name;
        }
    }

    public static String getPlayerName(String player) {
        if ("X".equals(player)) {
            return playerXName;
        } else if ("O".equals(player)) {
            return playerOName;
        }
        return "Empate";
    }
}
