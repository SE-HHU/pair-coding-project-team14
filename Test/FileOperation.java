package Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation
{
    File ExerciseFile = null;
    File AnswerFile = null;
    File GradeFile = null;
    BufferedWriter ExerciseOut = null;
    BufferedWriter AnswerOut = null;
    BufferedWriter GradeOut = null;

    public FileOperation()
    {
        if (this.CreateFile())
        {
            this.setOutBufferedWriter();
        }
        else
        {
            System.out.println("创建文件失败！");
        }
    }

    public void setOutBufferedWriter()
    {
        try
        {
            this.ExerciseOut = new BufferedWriter(new FileWriter(ExerciseFile));
            this.AnswerOut = new BufferedWriter(new FileWriter(AnswerFile));
            this.GradeOut = new BufferedWriter(new FileWriter(GradeFile));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean CreateFile()
    {
        String relativelyPath=System.getProperty("user.dir");
        //System.out.println(relativelyPath);
        ExerciseFile = new File(relativelyPath + "\\Exercises" + ".txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
        AnswerFile = new File(relativelyPath + "\\Answers" + ".txt");
        GradeFile = new File(relativelyPath + "\\Grade" + ".txt");
        if (ExerciseFile.exists())          //若文件已存在，则将其删除
        {
            ExerciseFile.delete();
        }
        if (AnswerFile.exists())
        {
            AnswerFile.delete();
        }
        if(GradeFile.exists())
        {
            GradeFile.delete();
        }
        try                                 //创建新文件
        {
            ExerciseFile.createNewFile();
            AnswerFile.createNewFile();
            GradeFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public boolean WriteToFile(String content, int flag)
    {
        /* 写入txt文件 */
        try
        {
            switch (flag)           //flag控制写入哪个文件
            {
                case 0:
                    ExerciseOut.write(content);
                    ExerciseOut.write("\r\n");
                    ExerciseOut.flush();
                    return true;
                case 1:
                    AnswerOut.write(content);
                    AnswerOut.write("\r\n");
                    AnswerOut.flush();
                    return true;
                case 2:
                    GradeOut.write(content);
                    GradeOut.flush();
                    return true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean CloseOutBufferedWriter()
    {
        try                         //关闭文件
        {
            ExerciseOut.close();
            AnswerOut.close();
            GradeOut.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
