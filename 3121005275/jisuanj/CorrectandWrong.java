package jisuanj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Scanner;

public class CorrectandWrong {
    private Scanner scanner1;
    private Scanner scanner2;

    CorrectandWrong() throws IOException {
        System.out.println("请输入试题文件名: ");
        this.scanner1 = new Scanner(System.in);
        String Exefile = this.scanner1.nextLine();
        System.out.println("请输入答案文件名: ");
        this.scanner2 = new Scanner(System.in);
        String Ansfile = this.scanner2.nextLine();
        File exefile = new File(Exefile);
        File ansfile = new File(Ansfile);
        if (exefile.exists() && ansfile.exists()) {
            InputStreamReader exread = new InputStreamReader(new FileInputStream(Exefile), "GB2312");
            BufferedReader exbr = new BufferedReader(exread);
            InputStreamReader anread = new InputStreamReader(new FileInputStream(Ansfile), "GB2312");
            BufferedReader anbr = new BufferedReader(anread);
            String exback = null;
            String anback = null;
            String[] exlist = null;
            String[] anlist = null;
            String[] Exercises = null;
            StringBuffer exercises = new StringBuffer();
            String[] Answers = null;
            StringBuffer answers = new StringBuffer();
            int correctnum = 0;
            int wrongnum = 0;
            StringBuffer correct = new StringBuffer();

            String ex;
            StringBuffer wrong;
            for(wrong = new StringBuffer(); (ex = exbr.readLine()) != null; exercises.append(exback + ",")) {
                exlist = ex.split(" ");
                if (exlist[exlist.length - 1].equals("=")) {
                    exback = "-";
                } else {
                    exback = exlist[exlist.length - 1];
                }
            }

            Exercises = exercises.toString().split(",");

            String an;
            while((an = anbr.readLine()) != null) {
                anlist = an.split(" ");
                anback = anlist[anlist.length - 1];
                answers.append(anback + ",");
            }

            Answers = answers.toString().split(",");

            for(int i = 0; i < Exercises.length; ++i) {
                if (Exercises[i].equals(Answers[i])) {
                    correct.append(i + 1 + " ");
                    ++correctnum;
                } else {
                    wrong.append(i + 1 + " ");
                    ++wrongnum;
                }
            }

            System.out.println("Wrong: " + correctnum + " ( " + correct + ")");
            System.out.println("Correct: " + wrongnum + " ( " + wrong + ")");
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            double correctpercent = (double)correctnum / (double)Exercises.length;
            double wrongpercent = (double)wrongnum / (double)Exercises.length;
            System.out.println("错误率: " + nt.format(correctpercent));
            System.out.println("正确率: " + nt.format(wrongpercent));
            exread.close();
            anread.close();
            exbr.close();
            anbr.close();
        } else {
            System.out.println("找不到指定文件!");
        }

    }
}