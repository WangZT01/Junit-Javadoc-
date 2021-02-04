

public class ColorSelect {
    public static void main(String [] args){
        for (int i = 90; i < 98; i++) {
            System.out.print("\033[" + i + ";0m" + "abc中文" + "\033[0m index--> " + i);
            System.out.println();
        }
        for (int i = 30; i < 40; i++) {
            System.out.print("\033[" + i + ";0m" + "abc中文" + "\033[0m index--> " + i);
            System.out.println();
        }

        //1m 粗体 4m 下划线 7m反色
        System.out.println("\033[92:1mabc中文\033[0m index--> " + 92);
        System.out.println("\033[92:1mabc中文\033[0m index--> " + 92);
        System.out.println("\033[92:1mabc中文\033[0m index--> " + 92);
    }
}
