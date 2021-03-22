package com.java.addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class AddressService {

	static final String rootPath = System.getProperty("user.dir") + "\\";
	static final String filename = rootPath + "PhoneDB.txt";
	
	//	리스트 출력
	public void showList() {
		
		int num = 0;
		Reader reader = null;
		BufferedReader br = null;
		
		try {
			reader = new FileReader(filename);
			br = new BufferedReader(reader);
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(line,	
														",");
				System.out.print((num + 1) + ".\t");
				while(st.hasMoreTokens()) {	
					String token = st.nextToken();
					System.out.print(token + "\t");
				}
				System.out.println();
				num++;
			}
			
		}catch (FileNotFoundException e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//	삽입
	public void addressInsert() {
		
		Scanner sc = new Scanner(System.in);
	
		Writer writer = null;
		BufferedWriter bw = null;
		
		System.out.println("<2.등록>");
		System.out.print(">이름 : ");
		String name = sc.next();
		System.out.print(">휴대전화 : ");
		String hp = sc.next();
		System.out.print(">집전화 : ");
		String tel = sc.next();
		
		try {
			writer = new FileWriter(filename, true);
			bw = new BufferedWriter(writer);
			
			bw.write(name + "," + hp + "," + tel);
			bw.newLine();
			bw.flush();
			
			System.out.println("[등록되었습니다.]");
			
		}catch(FileNotFoundException e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			}catch (Exception e) {
				
			}
		}
	}
	
	
	//	삭제
	public void addressDelete() {
		
		Scanner sc = new Scanner(System.in);
	
		Reader reader = null;
		Writer writer = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
	
		Vector<String> v = new Vector<>();
		
		System.out.println("<3.삭제>");
		System.out.print(">번호 : ");
		int num = sc.nextInt();
		int flag = 0;
		
		try {
			reader = new FileReader(filename);
			br = new BufferedReader(reader);
			
			writer = new FileWriter(filename, true);
			bw = new BufferedWriter(writer);
			
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(line,
														"\n");
				v.addElement(line);
				
				while(st.hasMoreTokens()) {	
					String token = st.nextToken();
				}
				flag++;
			}
			
			FileWriter fw = new FileWriter(filename);
			
			for(int i = 0; i < v.size(); i++) {
				if(i != (num - 1)) {
					fw.write(v.elementAt(i) + "\n");
				}else {
				}
			}
			
			fw.flush();
			fw.close();
			
			System.out.println("[삭제되었습니다.]");
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
				br.close();
				v.clear();
			}catch (Exception e) {
				
			}
		}
	}
	
	
	//	검색
	public void addressSearch() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<4.검색>");
		System.out.print(">이름 : ");
		String name = sc.next();
		
		int num = 0;
		Reader reader = null;
		BufferedReader br = null;
		
		try {
			reader = new FileReader(filename);
			br = new BufferedReader(reader);
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(line,
														",");
				if(line.contains(name) == true) {
					System.out.print((num + 1) + ".\t");
					while(st.hasMoreTokens()) {	
						String token = st.nextToken();
						System.out.print(token + "\t");
					}
					System.out.println();
				}
				
				num++;
			}
			
		}catch (FileNotFoundException e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
