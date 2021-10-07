package Test;
import java.util.Random;
public class CreateExercise
{
    Random rd = new Random();

    /*随机生成表达式*/
    public String CreateEx (int r, boolean b)
    {
        String ex = "";
        int n = rd.nextInt(3);
        switch (n)
        {
            case 0:     //一个运算符
                ex = CreateNum(r) + CreateChar() + CreateNum(r);
                break;
            case 1:     //两个运算符
                if(b)
                {
                    int temp = rd.nextInt(2);
                    switch (temp)   //b == true,括号生成位置
                    {
                        case 0:
                            ex = "(" + CreateNum(r) + CreateChar() + CreateNum(r) + ")" + CreateChar() + CreateNum(r);
                            break;
                        case 1:
                            ex = CreateNum(r) + CreateChar() + "(" + CreateNum(r) + CreateChar() + CreateNum(r) + ")";
                            break;
                    }
                }
                else    //b == false
                {
                    ex = CreateNum(r) + CreateChar() + CreateNum(r) + CreateChar() + CreateNum(r);
                }
                break;
            case 2:     //三个运算符
                if(b)
                {
                    int temp = rd.nextInt(4);
                    switch (temp)    //b == true,括号生成位置
                    {
                        case 0:
                            ex="("+ CreateNum(r)+ CreateChar()+ CreateNum(r)+")"+ CreateChar()+ CreateNum(r)+ CreateChar()+ CreateNum(r);
                            break;
                        case 1:
                            ex=CreateNum(r)+ CreateChar()+"("+ CreateNum(r)+ CreateChar()+ CreateNum(r)+")"+ CreateChar()+ CreateNum(r);
                            break;
                        case 2:
                            ex=CreateNum(r)+ CreateChar()+ CreateNum(r)+ CreateChar()+"("+ CreateNum(r)+ CreateChar()+ CreateNum(r)+")";
                            break;
                        case 3:
                            ex="("+ CreateNum(r)+ CreateChar()+ CreateNum(r)+ CreateChar()+ CreateNum(r)+")"+ CreateChar()+ CreateNum(r);
                            break;
                        case 4:
                            ex=CreateNum(r)+ CreateChar()+"("+ CreateNum(r)+ CreateChar()+ CreateNum(r)+ CreateChar()+ CreateNum(r) +")";
                            break;
                    }
                }
                else    //b == false
                {
                    ex = CreateNum(r) + CreateChar() + CreateNum(r) + CreateChar() + CreateNum(r) + CreateChar() + CreateNum(r);
                }
                break;
        }
        return ex;          //返回表达式
    }

    /*随机生成操作数*/
    public String CreateNum(int r)
    {
        String s = "";
        switch(rd.nextInt(2))    //随机生成整数或分数
        {
            case 0:
                s = Integer.toString(rd.nextInt(r - 1) + 1);
                break;
            case 1:
                int a,b;
                do
                {
                    a = rd.nextInt(r - 1) + 1;
                    b = rd.nextInt(r - 2) + 2;
                }while (a > b);     //以生成真分数
                s = Dating(a,b);
                break;
        }
        return s;
    }

    /*随机生成运算符*/
    public String CreateChar()
    {
        String s = "";
        switch(rd.nextInt(4))
        {
            case 0:
                s = "+";
                break;
            case 1:
                s = "-";
                break;
            case 2:
                s = "*";
                break;
            case 3:
                s = "÷";
                break;
        }
        return s;
    }

    /*约分*/
    public String Dating(int a,int b)
    {
        String s;
        int gys = 1;
        int c;
        c = a / b;
        a = a % b;
        if(c < 0)
        {
            a = a * -1;
        }
        for (int i = 1; i <= a; i++)
        {
            if (a % i == 0 && b % i == 0)
            {
                gys = i;
            }
        }
        a = a / gys;                            //生成最简分数
        b = b / gys;
        if(a == 0)
        {
            s = Integer.toString(c);
        }
        else if(c==0)
        {
            s = a + "/" + b;
        }
        else
        {
            s = c + "'" + a + "/" + b;
        }
        return s;
    }
}
