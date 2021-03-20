package Game;

public class game {
    public static final int BLACK = -1;
    public static final int EMPTY = 0;
    public static final int WHITE = 1;
    private static final int col = 8;
    private static final int row = 8;

    public int[][] chessboard;
    public int color;

    public game() {
        chessboard = new int[8][8];
        chessboard[3][3] = WHITE;
        chessboard[3][4] = BLACK;
        chessboard[4][3] = BLACK;
        chessboard[4][4] = WHITE;
        color = WHITE;
    }

    /* 检查某一步棋落子后，(d1, d2)方向上是否会有棋子翻转。若有，则返回true；反之返回false。 */
    public boolean CheckDirection(int x, int y, int bound, int currentColor, int d1, int d2) {
        int i;
        boolean findSame = false;
        boolean findDiff = false;
        boolean findEmpty = false;
        for(i = 1; i <= bound; i++) {
            int thisColor = chessboard[x + d1 * i][y + d2 * i];
            if(thisColor == currentColor)  {
                findSame = true;
                break;
            }
            if(thisColor == EMPTY) {
                findEmpty = true;
                break;
            }
            if(thisColor == -currentColor) findDiff = true;
        }
        return !findEmpty && findDiff && findSame;
    }

    /* 检查某一步棋是否合法。合法返回true，反之返回false。 */
    public boolean CheckAll(int x, int y, int currentColor) {
        if(chessboard[x][y] != 0) return false;
        int upperLeft = Math.min(x, y);
        int upperRight = Math.min(col - x - 1, y);
        int lowerLeft = Math.min(x, row - y - 1);
        int lowerRight = Math.min(col - x - 1, row - y - 1);
        boolean check1 = CheckDirection(x, y, upperLeft, currentColor, -1, -1);
        boolean check2 = CheckDirection(x, y, upperRight, currentColor, 1, -1);
        boolean check3 = CheckDirection(x, y, lowerLeft, currentColor, -1, 1);
        boolean check4 = CheckDirection(x, y, lowerRight, currentColor, 1, 1);
        boolean check5 = CheckDirection(x, y, x, currentColor, -1, 0);
        boolean check6 = CheckDirection(x, y, y, currentColor, 0, -1);
        boolean check7 = CheckDirection(x, y, col - x - 1, currentColor, 1, 0);
        boolean check8 = CheckDirection(x, y, row - y - 1, currentColor, 0, 1);
        return check1 || check2 || check3 || check4 || check5 || check6 || check7 || check8;
    }

    /* 检查某颜色是否有棋可下。 */
    boolean CheckColor(int color) {
        int i, j;
        for(i = 0; i < col; i++)
            for(j = 0; j < row; j++)
                if(chessboard[i][j] == EMPTY)
                    if(CheckAll(i, j, color)) return true;
        return false;
    }

    /* 检查游戏是否结束。 */
    public boolean IsGameOver() {
        return !CheckColor(WHITE) && !CheckColor(BLACK);
    }

    public void ReverseDirection(int x, int y, int d1, int d2) {
        int i = 1;
        while(true) {
            if(chessboard[x + d1 * i][y + d2 * i] == color) break;
            else chessboard[x + d1 * i][y + d2 * i] = color;
            i++;
        }
    }

    public boolean reverseAll(int x, int y) {
        int upperLeft = Math.min(x, y);
        int upperRight = Math.min(col - x - 1, y);
        int lowerLeft = Math.min(x, row - y - 1);
        int lowerRight = Math.min(col - x - 1, row - y - 1);
        boolean check1 = CheckDirection(x, y, upperLeft, color, -1, -1);
        if(check1) ReverseDirection(x, y, -1, -1);
        boolean check2 = CheckDirection(x, y, upperRight, color, 1, -1);
        if(check2) ReverseDirection(x, y, 1, -1);
        boolean check3 = CheckDirection(x, y, lowerLeft, color, -1, 1);
        if(check3) ReverseDirection(x, y, -1, 1);
        boolean check4 = CheckDirection(x, y, lowerRight, color, 1, 1);
        if(check4) ReverseDirection(x, y, 1, 1);
        boolean check5 = CheckDirection(x, y, x, color, -1, 0);
        if(check5) ReverseDirection(x, y, -1, 0);
        boolean check6 = CheckDirection(x, y, y, color, 0, -1);
        if(check6) ReverseDirection(x, y, 0, -1);
        boolean check7 = CheckDirection(x, y, col - x - 1, color, 1, 0);
        if(check7) ReverseDirection(x, y, 1, 0);
        boolean check8 = CheckDirection(x, y, row - y - 1, color, 0, 1);
        if(check8) ReverseDirection(x, y, 0, 1);
        chessboard[x][y] = color;
        if(check1 || check2 || check3 || check4 || check5 || check6 || check7 || check8) {
            color *= -1;
            return true;
        }
        else return false;
    }

    /* 若执白棋玩家获胜，返回true；反之返回false。 */
    public int FindWinner() {
        int i, j, white = 0, black = 0;
        for(i = 0; i < col; i++)
            for(j = 0; j < row; j++)
                if(chessboard[i][j] == WHITE) white++;
                else black++;
        if(white > black) return WHITE;
        else return BLACK;
    }

    public void reset() {
        int i, j;
        for(i = 0; i < col; i++)
            for(j = 0; j < row; j++)
                chessboard[i][j] = 0;

        chessboard[3][3] = WHITE;
        chessboard[3][4] = BLACK;
        chessboard[4][3] = BLACK;
        chessboard[4][4] = WHITE;
        color = WHITE;
    }
}
