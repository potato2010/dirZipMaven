package wz.test.maven.dirzip;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 2017/2/2.
 */
public class DirZip {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1){
            String filePath= args[0];
            File Source=new File(filePath);
            if(!Source.isDirectory()){
                System.out.println("请输入一个父目录。");
                System.exit(1);
            }

            File[] fileList=Source.listFiles();
            int i = 0;
            if (fileList != null) {
                i=fileList.length;
            }
            else {
                i = 0;
            }


            //		System.out.println(i);
            for(int j=1;j<=i;j++){
                if (fileList != null && fileList[j - 1].isDirectory()) {
                    File zipDir = fileList[j - 1];
                    File[] temp = zipDir.listFiles();
                    if (temp != null) {
                        if (temp.length == 1) {
                            zipFile(zipDir);
                        } else if (isPictureDir(fileList[j - 1])) {
                            zipFile(zipDir);
                        }
                    }

                }
            }

        }
        else {
            System.out.println("please input a dir");

        }

    }


    private static boolean isPictureDir(File filedir) {
        File[] fileList=filedir.listFiles();
        int i=fileList.length;
        int num=0;
        for(int j=1;j<=i;j++)
        {
            File files=new File(fileList[j-1].getName());
            if(files.isDirectory()){
                return false;
            }
            String extension = FilenameUtils.getExtension(fileList[j-1].getName());

            if(extension.equals("jpg")||extension.equals("png")||extension.equals("JPG")||extension.equals("PNG"))
            {num++;}
            else
            {System.out.println("[非图片文件]"+fileList[j-1]);}
        }
        if(num>=1)
        {return true;}
        else
        {System.out.println("[非图片目录]"+filedir);
            return false;
        }


    }
    private static void zipFile(File filedir) throws IOException, InterruptedException
    {
        String cmdStr="7z.exe a -mx=0 \""+filedir+"\".zip \""+filedir+"\"";
        Process process = Runtime.getRuntime().exec(cmdStr);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String echo_string;
        while((echo_string=bufferedReader.readLine()) != null)
        {
        }
        process.waitFor();
        System.out.println("[压缩目录]"+filedir);
        String fileName=filedir.getPath()+".zip";
        File fileZip=new File(fileName);
        if(fileZip.exists())
        {
            org.apache.commons.io.FileUtils.deleteDirectory(filedir);
        }

    }
}
