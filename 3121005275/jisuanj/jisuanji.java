package jisuanj;

import java.io.IOException;
import java.util.Scanner;

public class jisuanji {
    private static Scanner scanner;

    public jisuanji() {
    }

    public static void main(String[] args) throws IOException {
        while(true) {
            System.out.println("ygt zh");
            System.out.println("请输入: 1或2 (1.生成算式;2.校对答案)");
            scanner = new Scanner(System.in);
            String what = scanner.nextLine();
            Scanner scanner1;
            String exit;
            if (what.equals("1")) {
                new build();
                System.out.println("是否结束: YES or NO");
                scanner1 = new Scanner(System.in);
                exit = scanner1.nextLine();
                if (!exit.equals("YES")) {
                    continue;
                }
            } else {
                if (!what.equals("2")) {
                    System.out.println("输入错误，请重新输入!");
                    continue;
                }

                new CorrectandWrong();
                System.out.println("是否结束: YES or NO");
                scanner1 = new Scanner(System.in);
                exit = scanner1.nextLine();
                if (!exit.equals("YES")) {
                    continue;
                }
            }

            return;
        }
    }
}