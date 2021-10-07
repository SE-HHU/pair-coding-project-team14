package Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
public class Test
{
    public static void main(String[] args)
    {
        LinkedHashMap<Integer,String> exerciseMap = new LinkedHashMap<>();      //存放题目
        LinkedHashMap<Integer,String> rightAnswerMap = new LinkedHashMap<>();   //存放正确答案
        ArrayList<Integer> rightRecord = new ArrayList<>();                     //用户所输入答案，根据正误存放
        ArrayList<Integer> wrongRecord = new ArrayList<>();
        CreateExercise ce = new CreateExercise();       //实例化
        FileOperation fo = new FileOperation();
        Calculator cal = new Calculator();

        Scanner in = new Scanner(System.in);
        System.out.print("请输入生成的题目个数：");
        int n = in.nextInt();
        System.out.print("请输入题目数值范围：");
        int r = in.nextInt();
        System.out.print("请确认题目中是否带括号(true/false)：");
        boolean b = in.nextBoolean();
        System.out.print("是否立即作答（y/n）：");
        String is_doNow = in.next();

        for (int i = 1; i <= n; i++)
        {
            String problem = ce.CreateEx(r,b);          //生成题目
            exerciseMap.put(i,problem);
            String result = cal.suffixToArithmetic(cal.infixToSuffix(problem),r);       //计算结果

            if (!result.equals("无解"))
            {
                System.out.println(i + ".  " + problem + "= " + result);
                rightAnswerMap.put(i, result);      //若答案不是无解，存入map

                //将题目及答案写入txt
                if (!fo.WriteToFile(i + ".  " + problem + "= ", 0))
                {
                    System.out.println("写入Exercise文件失败！");
                    System.exit(0);
                }
                if (!fo.WriteToFile(i + ". " + result, 1))
                {
                    System.out.println("写入Answer文件失败！");
                    System.exit(0);
                }
            }
            else        //若答案为无解，重新生成题目
            {
                i--;
            }
        }

        if(is_doNow.equals("y"))    //输入答案做对比
        {
            System.out.println("请按题号输入答案：");
            for (int i = 1; i <= n; i++)
            {
                System.out.print(i + ".  " + exerciseMap.get(i) + "=");
                String input = in.next();
                if (rightAnswerMap.get(i).equals(input))
                {
                    rightRecord.add(i);
                }
                else
                {
                    wrongRecord.add(i);
                }
            }

            if (rightRecord.size()!=0)          //写入Grade.txt
            {
                fo.WriteToFile("Correct:"+rightRecord.size()+" (", 2);
                for (int i = 0; i < rightRecord.size() - 1; i++)
                {
                    fo.WriteToFile(rightRecord.get(i)+",",2);
                }
                fo.WriteToFile(rightRecord.get(rightRecord.size()-1) + ")" + "\r\n",2);
            }
            else
            {
                fo.WriteToFile("Correct:"+rightRecord.size() + "\r\n",2);
            }
            if (wrongRecord.size()!=0)
            {
                fo.WriteToFile("Wrong:"+wrongRecord.size()+" (",2);
                for (int i = 0; i < wrongRecord.size() - 1; i++)
                {
                    fo.WriteToFile(wrongRecord.get(i)+",",2);
                }
                fo.WriteToFile(wrongRecord.get(wrongRecord.size()-1)+")" + "\r\n",2);
            }
            else
            {
                fo.WriteToFile("Wrong:"+wrongRecord.size() + "\r\n",2);
            }

        }

        Check ch = new Check();            //查重
        List<String> find = ch.checkAnswer(exerciseMap,rightAnswerMap);
        fo.WriteToFile("Repeat:" + find.size() + "\r\n",2);
        fo.WriteToFile("RepeatDetail:"+"\r\n",2);
        for(int k = 1; k <= find.size(); k++)
        {
            fo.WriteToFile("("+k+") " + find.get(k-1) + "\r\n",2);
        }

        if (fo.CloseOutBufferedWriter())
        {
            System.out.println("题目和答案文件创建成功！");
        }
        else
        {
            System.out.println("题目和答案文件创建失败！");
        }
    }
}

