import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class homework2 {
    public static int n = 0;
    public static int n2 = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;
        int count = 0;
        int num = 1;
        //作为FileReader和FileWriter读取的对象
        System.out.println("请输入文件名:");
        String fileName = sc.nextLine();
        String file1 = "E:\\" + fileName;
        try {
            BufferedReader a = new BufferedReader(new FileReader(file1));
            StringBuffer c = new StringBuffer();
            //将文件内容存入StringBuffer中
            while ((s = a.readLine()) != null) {
                //用于拼接字符串
                c.append(s);
            }
            //将StringBuffer转换成String，然后再将所有字符转化成小写字符
            String m = c.toString().toLowerCase();
            System.out.println(m + '\n');
            //匹配由数字和26个字母组成的字符串
            String[] d = m.split("[^a-zA-Z0-9]+");
            //遍历数组将其存入Map<String, Integer>中
            Map<String, Integer> myTreeMap = new TreeMap<String, Integer>();
            for (int i = 0; i < d.length; i++) {
                //containsKey()方法用于检查特定键是否在TreeMap中映射
                if (myTreeMap.containsKey(d[i])) {
                    count = myTreeMap.get(d[i]);
                    myTreeMap.put(d[i], count + 1);
                } else {
                    myTreeMap.put(d[i], 1);
                }
            }
            System.out.println("total " + myTreeMap.size());
            //通过比较器实现排序
            List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(myTreeMap.entrySet());
            //按降序排序
            Collections.sort(list, new Comparator<Entry<String, Integer>>() {

                public int compare(Entry<String, Integer> k1, Entry<String, Integer> k2) {
                    //返回两个单词出现次数较多的那个单词的出现次数
                    return k2.getValue().compareTo(k1.getValue());
                }

            });


            System.out.println("请输入单词长度j位:");
            n2 = sc.nextInt();

            System.out.println("请输入词频前i位:");
            n = sc.nextInt();
            for (Entry<String, Integer> map : list) {
                if (map.getKey().length() <= n2) {
                    if (num <= n) {
                        //输出到程序控制台
                        System.out.println(map.getKey() + '\t' + map.getValue());
                        num++;
                    }
                }

            }
            //关闭文件指针
            a.close();
            // b.close();
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("文件读取错误");
        }
    }
}
