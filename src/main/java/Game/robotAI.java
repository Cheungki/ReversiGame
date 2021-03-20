package Game;

public class robotAI {
    game Game;
    public int x;
    public int y;

    robotAI(game Game) {
        this.Game = Game;
    }

    int findBest(int color, int step)
    {
        if (step > 3)
            return 0;
        if (!Game.CheckColor(color)) {
            if (Game.CheckColor(color))
                return -findBest(-color, step);
            else
                return 0;
        }

        int i, j, x = 0, y = 0, max = 0;
        int temp = 0;
        boolean ans = false;
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++)
                if (temp == (Game.CheckAll(i, j, color) ? 1 : 0)) {
                    temp -= findBest(-color, step + 1);
                    if (temp > max || !ans) {
                        max = temp;
                        x = i;
                        y = j;
                        ans = true;
                    }
                }

        // 如果是第一步则标识白棋下子点
        if (step == 1) {
            this.x = x;
            this.y = y;
        }
        return max;
    }
}
