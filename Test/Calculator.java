package Test;
import java.util.Stack;
public class Calculator
{
    /*中缀表达式转后缀表达式*/
    public String infixToSuffix(String exp)
    {
        Stack<Character> s = new Stack<>();                // 创建操作符堆栈
        StringBuilder suffix = new StringBuilder();        // 创建后缀表达式字符串
        int length = exp.length();
        for (int i = 0; i < length; i++)
        {
            char temp;
            char ch = exp.charAt(i);                    //从左至右扫描字符
            switch (ch)
            {
                case '(':
                    s.push(ch);                 //扫描到左括号直接将其输出到堆栈中,只有在遇到右括号时移除
                    break;
                case '+':
                case '-':
                    suffix.append(" ");
                    while (s.size() != 0)
                    {
                        temp = s.pop();         //堆栈非空，操作符的优先级低于堆栈出口的操作符
                        if (temp == '(')        //导出堆栈出口操作符,直到该操作符的优先级大于堆栈顶端的操作符
                        {
                            s.push('(');
                            break;
                        }
                        suffix.append(temp);
                        suffix.append(" ");
                    }
                    s.push(ch);                 //将扫描到的操作符导入到堆栈中
                    break;
                case '*':
                case '÷':
                    suffix.append(" ");
                    while (s.size() != 0)
                    {
                        temp = s.pop();
                        if (temp == '+' || temp == '-' || temp == '(')
                        {
                            s.push(temp);       //堆栈非空，操作符的优先级大于堆栈出口的操作符，直接将操作符存储到堆栈中
                            break;
                        }
                        else
                        {
                            suffix.append(temp);
                            suffix.append(" ");
                        }
                    }
                    s.push(ch);
                    break;
                case ')':
                    while (!s.isEmpty())
                    {
                        temp = s.pop();
                        if (temp == '(')        //将堆栈中的操作符导出,直到遇见左括号,将堆栈中的左括号移出堆栈
                        {
                            break;
                        }
                        else
                        {
                            suffix.append(" ");
                            suffix.append(temp);
                        }
                    }
                    break;
                default:
                    suffix.append(ch);          //扫描到字符为操作数，直接输出至后缀表达式
                    break;
            }
        }
        while (s.size() != 0)
        {                            //堆栈非空，将堆栈中的剩余操作符导出
            suffix.append(" ");
            suffix.append(s.pop());
        }
        return suffix.toString();
    }

    /*计算后缀表达式*/
    public String suffixToArithmetic(String exp,int r)
    {
        String[] strings = exp.split(" ");          //按空格分解字符串
        Stack<String> stack = new Stack<>();          //创建操作数栈
        for (String string : strings)
        {
            if (string.equals("+") || string.equals("-") || string.equals("*") || string.equals("÷"))
            {
                String y = stack.pop();                        //读取到运算符，提取栈顶的两个操作数
                String x = stack.pop();
                String rus = calculate(x, y, string);
                stack.push(rus);
                if (rus.equals("无解"))
                {
                    return rus;
                }
            }
            else
            {
                stack.push(string);
            }
        }

        String t = stack.pop();         //查看栈顶内容
        if (t.contains("'"))            //若其为带分数，返回无解
        {
            return "无解";
        }
        else                            //确保答案中自然数、真分数分子及分母在数值范围内，否则返回无解
        {
            int p,q;
            if (t.contains("/"))
            {
                int len = t.indexOf("/");
                p = Integer.parseInt(t.substring(0,len));
                q = Integer.parseInt(t.substring(len + 1));
                if (p > 0 && p < r && q > 0 && q < r)
                {
                    return t;
                }
                else
                {
                    return "无解";
                }
            }
            else
            {
                if(Integer.parseInt(t) < r && Integer.parseInt(t) > 0)
                {
                    return t;
                }
                else
                {
                    return "无解";
                }
            }
        }
    }

    /*自定义四则运算法则*/
    public String calculate(String x, String y, String ch)
    {
        String rus = "";
        boolean flag1 = false, flag2 = false;              //判断两个参数类型
        if (x.contains("/"))
            flag1 = true;
        if (y.contains("/"))
            flag2 = true;

        int a = 1, b = 1, c = 1, d = 1, f1 = 0, f2 = 0;     //转换两参数

        if (!flag1 && !flag2)               //类型1，均为整数
        {
            a = Integer.parseInt(x);
            b = Integer.parseInt(y);
        }

        if (!flag1 && flag2)                //类型2，x为整数，y为分数
        {
            a = Integer.parseInt(x);
            int lenf2 = y.indexOf("'");     //判断是否带分数
            if (lenf2 != -1)
            {
                f2 = Integer.parseInt(y.substring(0, lenf2));
            }

            int len = y.indexOf("/");       //提取分子，分母
            b = Integer.parseInt(y.substring(lenf2 + 1, len));
            d = Integer.parseInt(y.substring(len + 1));
            if (f2 < 0)                     //判断带分数是否负数
            {
                b = f2 * d - b;
            }
            else
            {
                b = f2 * d + b;
            }
        }

        if (flag1 && !flag2)                //类型3，x为分数，y为整数
        {
            int lenf1 = x.indexOf("'");
            if (lenf1 != -1)
            {
                f1 = Integer.parseInt(x.substring(0, lenf1));
            }

            int len = x.indexOf("/");
            a = Integer.parseInt(x.substring(lenf1 + 1, len));
            c = Integer.parseInt(x.substring(len + 1));
            if (f1 < 0)
            {
                a = f1 * c - a;
            }
            else
            {
                a = f1 * c + a;
            }

            b = Integer.parseInt(y);
        }

        if (flag1 && flag2)                 //类型4，均为分数
        {
            int lenf1 = x.indexOf("'");
            if (lenf1 != -1)
            {
                f1 = Integer.parseInt(x.substring(0, lenf1));
            }
            int len1 = x.indexOf("/");
            a = Integer.parseInt(x.substring(lenf1 + 1, len1));
            c = Integer.parseInt(x.substring(len1 + 1));
            if (f1 < 0)
            {
                a = f1 * c - a;
            }
            else
            {
                a = f1 * c + a;
            }

            int lenf2 = y.indexOf("'");
            if (lenf2 != -1)
            {
                f2 = Integer.parseInt(y.substring(0, lenf2));
            }
            int len2 = y.indexOf("/");
            b = Integer.parseInt(y.substring(lenf2 + 1, len2));
            d = Integer.parseInt(y.substring(len2 + 1));
            if (f2 < 0)
            {
                b = f2 * d - b;
            }
            else
            {
                b = f2 * d + b;
            }
        }

        CreateExercise ex = new CreateExercise();                //调用分数约分函数
        switch (ch)
        {
            case "+":
                rus = ex.Dating(a * d + c * b, c * d);
                break;
            case "-":
                rus = ex.Dating(a * d - c * b, c * d);
                break;
            case "*":
                rus = ex.Dating(a * b, c * d);
                break;
            case "÷":
                if (c * b == 0)
                {
                    rus = ("无解");
                    break;
                }
                else
                {
                    rus = ex.Dating(a * d, c * b);
                }
                break;
        }
        return rus;
    }
}