package PaperCheck;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class PaperCheck {public static void main(String[] args) throws IOException {
    String Orig_path=args[0];                   //命令行读入路径
    String Add_path=args[1];
    String Res_path=args[2];


    BufferedReader Orig_reader=new BufferedReader(new FileReader(Orig_path));   //利用字符流文件来输入路径
    BufferedReader Add_reader=new BufferedReader(new FileReader(Add_path));

    String orig_txt=readText(Orig_reader);          //得到文本
    String Add_txt=readText(Add_reader);

    double res=getSimilarityRatio(orig_txt,Add_txt);

    String res_=String.format("%.2f", res/100);
    if(Add_path.length()==0) res_="0.00";
    writeText(res_,Res_path);
}
    static String readText(BufferedReader br) throws  IOException {           //用字符流文件读取文本并保存在字符串中
        StringBuilder txt=new StringBuilder();
        String line =br.readLine();
        while(line!=null){
            txt.append(line).append('\n');
            line=br.readLine();
        }
        return txt.toString();
    }       //读取文本函数
    static void writeText(String res, String path){
        try {
            FileWriter writer=new FileWriter(path);
            writer.write(res);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }                       //写入结果文本函数
    public static float getSimilarityRatio(String str, String target) {
        // 矩阵
        int[][] d;
        int n = str.length();
        int m = target.length();
        // 遍历str的
        int i;
        // 遍历target的
        int j;
        // str的
        char ch1;
        // target的
        char ch2;
        // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        int temp;
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        // 遍历str
        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }
        System.out.println("差异步骤：" + d[n][m]);
        float similarity = 1 - (float) d[n][m] / Math.max(str.length(), target.length());
        System.out.println("相似度：" + similarity);
        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;


    }   //计算相似度
}
