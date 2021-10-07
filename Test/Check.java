package Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
public class Check
{
    public List<String> checkAnswer(LinkedHashMap<Integer,String> m1,LinkedHashMap<Integer,String> m2)
    {
        List<String> find = new ArrayList<>();         //用于存取重复表达式的信息
        for (int i = 1; i < m2.size(); i++)
        {
            StringBuilder s = new StringBuilder();
            for (int j = i + 1; j < m2.size(); j++)
            {
                if (m2.get(i).equals(m2.get(j)))           //查找相同答案
                {
                    if (checkExp(m1.get(i),m1.get(j)))
                    {
                        s.append(i).append(",").append(m1.get(i)).append(" Repeat ").append(j).append(",").append(m1.get(j)).append("  ");
                    }
                }
            }
            if (s.length() > 0)
                find.add(s.toString());
        }
        return find;
    }

    public boolean checkExp(String exp1, String exp2)           //中缀转后缀，通过比较两表达式字符判断
    {
        Calculator cal = new Calculator();
        exp1 = cal.infixToSuffix(exp1);
        exp2 = cal.infixToSuffix(exp2);
        String [] strings = exp1.split(" ");
        for (String string : strings)
        {
            if (!exp2.contains(string))
            {
                return false;
            }
        }
        return true;
    }
}