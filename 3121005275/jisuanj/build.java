package jisuanj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class build {
    @SuppressWarnings("resource")
    build() throws IOException {
        Scanner scannernum;
        Scanner scannersize;
        int num=0;
        int size=0;
        FormExpression fe = new FormExpression();
        Expression ex = new Expression();
        Random r = new Random();
        File que = new File("Exercises.txt");
        File ans = new File("Answers.txt");
        if(!que.exists()) {
            que.createNewFile();
        }
        if(!ans.exists()) {
            ans.createNewFile();
        }
        FileOutputStream fosque = new FileOutputStream(que);
        FileOutputStream fosans = new FileOutputStream(ans);

        System.out.println("请输入要生成的题目数(命令形式为:-n 自然数):");
        scannernum = new Scanner(System.in);
        String[] strnum = scannernum.nextLine().split(" ");

        if(strnum[0].equals("-n")&&strnum.length==2) {
            if(Integer.parseInt(strnum[1])>=1) {
                num = Integer.parseInt(strnum[1]);

                System.out.println("请输入题目中数值的最大值(命令形式为:-r 自然数):");
                scannersize = new Scanner(System.in);
                String[] strsize = scannersize.nextLine().split(" ");

                if(strsize[0].equals("-r")&&strsize.length==2) {
                    if(Integer.parseInt(strsize[1])>=2) {
                        size = Integer.parseInt(strsize[1]);

                        for(int i=0;i<num;i++) {
                            int opernum =  r.nextInt(3)+1;
                            String res = fe.FormExpression(size, opernum);
                            String result = i+1 + "." + res +"\r\n";
                            byte[] print = result.getBytes();
                            fosque.write(print);
                            Fenshu ansresult = ex.count(res);
                            String ansres = null;
                            if(ansresult.getDenominator()==1) {
                                ansres = i+1 + ". " +ansresult.getNumerator() + "\r\n";
                            }else {
                                if(ansresult.getNumerator()>ansresult.getDenominator()) {
                                    int fz = ansresult.getNumerator();
                                    int fm = ansresult.getDenominator();
                                    int mut = fz / fm;
                                    int newfz = fz % fm;
                                    ansres = i+1 + ". " + mut + "'" +newfz+"/"+fm + "\r\n";
                                }else if(ansresult.getNumerator()==ansresult.getDenominator()) {
                                    ansres = i+1 + ". " + "1" + "\r\n";
                                }else {
                                    ansres = i+1 + ". " +ansresult.getNumerator()+"/"+ansresult.getDenominator() + "\r\n";
                                }
                            }
                            byte[] ansprint = ansres.getBytes();
                            fosans.write(ansprint);
                            System.out.println(res);
                        }
                        fosque.flush();
                        fosans.flush();
                        fosque.close();
                        fosans.close();
                    }else {
                        System.out.println("-r参数设置错误，请重新输入，-n参数必须大于等于2!");
                    }
                }else {
                    System.out.println("-r命令输入错误，请重新输入!");
                }
            }else {
                System.out.println("-n参数设置错误，请重新输入，-n参数必须大于等于1!");
            }
        }else {
            System.out.println("-n命令输入错误，请重新输入!");
        }
    }
}



