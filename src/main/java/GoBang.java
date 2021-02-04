import java.util.Scanner;
import java.util.Set;

public class GoBang {
    //棋盘
    private String[][] d_chessBoard;
    //棋盘大小
    private final int SIZE = 10;

    //最近一次下棋的位置
    private int x;
    private int y;

    //标记当前是不是黑棋
    private boolean isBlack = false;

    // 多少棋子是胜利
    private  final int WIN_COUNT = 5;
    public static void main(String [] args){

        GoBang chess = new GoBang();
        chess.init();
        chess.render();
        Scanner scanner = new Scanner(System.in);
        while (true){
            // 下棋
            System.out.println("请输入坐标下棋");
            chess.player(scanner);
         // 刷新棋盘，打印
            chess.render();
            //判断是否五子连珠，只要判断当前棋子的周围就可以了
            if(chess.check()) {
                if (chess.isBlack) {
                    System.out.println("白棋胜利");
                } else {
                    System.out.println("黑棋胜利");
                }
                break;
            }
        }
    }

    /**
     * 遍历当前棋子周围是否构成五子连珠
     * x ，y 为当前棋子坐标
     * @return
     */
    private boolean check() {
        // 用于存储当前有多少个棋子连线
        int count = 1;
        // 存储当前位置是什么棋子
        String currentCheck = d_chessBoard[x][y];

        // 遍历上下方向
        // 遍历上方
        for (int i = x - 1; i >= 0; i --) {
            if (d_chessBoard[i][y].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        if (count >= WIN_COUNT) {
            return true;
        }
        // 遍历下方
        for (int i = x + 1; i < SIZE; i++) {
            if (d_chessBoard[i][y].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 如果大于5，返回胜利，如果小于5，把总数重置为1
        System.out.println("当前棋子竖直方向：" + count);
        if (count >= WIN_COUNT) {
            return true;
        } else {
            count = 1;
        }

        // 遍历左右方向
        // 遍历左方
        for (int i = y - 1; i >= 0; i --) {
            if (d_chessBoard[x][i].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        if (count >= WIN_COUNT) {
            return true;
        }
        for (int i = y + 1; i < SIZE; i ++) {
            if (d_chessBoard[x][i].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 如果大于5，返回胜利，如果小于5，把总数重置为1
        System.out.println("当前棋子水平方向：" + count);
        if (count >= WIN_COUNT) {
            return true;
        } else {
            count = 1;
        }

        // 遍历 \ 方向
        // 遍历左上方
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i --, j --) {
            if(d_chessBoard[i][j].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 遍历右下方
        for (int i = x + 1, j = y + 1; i < SIZE && j < SIZE; i ++, j ++) {
            if(d_chessBoard[i][j].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 如果大于5，返回胜利，如果小于5，把总数重置为1
        System.out.println("当前棋子 \\ 方向：" + count);
        if (count >= WIN_COUNT) {
            return true;
        } else {
            count = 1;
        }

        // 遍历 / 方向
        // 遍历 右上方
        for (int i = x - 1, j = y + 1; i >= 0 && j < SIZE; i --, j ++) {
            if(d_chessBoard[i][j].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 遍历 左下方
        for (int i = x + 1, j = y - 1; i < SIZE && j >= 0; i ++, j --){
            if(d_chessBoard[i][j].equals(currentCheck)){
                count ++;
            } else {
                break;
            }
        }
        // 如果大于5，返回胜利，如果小于5，把总数重置为1
        System.out.println("当前棋子 / 方向：" + count);
        if (count >= WIN_COUNT) {
            return true;
        }
        return false;
    }

    /**
     * 初始化棋盘
     */
    private void init(){
        d_chessBoard = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                d_chessBoard[i][j] = "+";
            }
        }
    }

    /**
     * 打印棋盘
     */
    private void render(){
        System.out.print(" ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("\033[92;0m" + i + "\33[0m" + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print("\033[92;0m" + i + "\33[0m" + "");
            for (int j = 0; j <SIZE; j++) {
                System.out.print(d_chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 下棋
     * @param scanner
     */
    private void player(Scanner scanner){
        //用户输入字符串
        String inputStr = scanner.nextLine();
        //字符串分割成数组
        String[] strArr = inputStr.split(",");
        //判断大小书否正确
        if (strArr.length != 2){
            System.out.print("请输入您的坐标");
            player(scanner);
        }
        //判断输入是否合法
        try{
            x = Integer.parseInt(strArr[0]);
            y = Integer.parseInt(strArr[1]);
        }catch (NumberFormatException e){
            System.out.print("请输入正确的数字");
            player(scanner);
        }
        //判断输入数字是否超标
        if(x < SIZE && y < SIZE){
            if(isBlack){
                d_chessBoard[x][y] = "A";
            }else{
                d_chessBoard[x][y] = "X";
            }
            isBlack = !isBlack;
            // 输入合法 开始下棋

        }else{
            System.out.print("请输入正确的数字范围");
            player(scanner);
        }
    }
}
