package org.liuyi.projects.personal.common.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ReadFile {
	public static void main(String[] args) {
		new ReadFile().read("C:\\Users\\liuyi\\Desktop\\最新对数据库操作\\实验班\\新建文本文档.txt","C:\\Users\\liuyi\\Desktop\\最新对数据库操作\\实验班\\新.txt");;
	}
	
	private void read(String filePath,String outFilePath){
		File file=new File(filePath);
		
		try{
//			
//			FileWriter fw=new FileWriter(outFilePath);
//			FileReader fr=new  FileReader(file);
//			BufferedReader br=new BufferedReader(fr);
//			for(int i=fr.read();i>0;i=fr.read()){
//				
//				fw.write(i);
//				
//			}
//			fw.flush();
			FileInputStream inFile=new FileInputStream(file);
			FileOutputStream outFile=new FileOutputStream("C:\\Users\\liuyi\\Desktop\\最新对数据库操作\\实验班\\欢迎.txt");
			OutputStreamWriter ow=new OutputStreamWriter(outFile, "gbk");
			BufferedWriter bw=new BufferedWriter(ow);
			InputStreamReader iRead=new InputStreamReader(inFile,"gbk");
			BufferedReader br=new BufferedReader(iRead);
			String text;
			while((text=br.readLine())!=null){
				bw.write(text);
				bw.newLine();
				System.out.println(text);
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
