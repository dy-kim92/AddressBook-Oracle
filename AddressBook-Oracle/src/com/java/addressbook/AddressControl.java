package com.java.addressbook;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class AddressControl{
	
	//	스캐너
	Scanner sc = new Scanner(System.in);
	
	//	간편 텍스트출력 불러오기
	AddressViewText avt = new AddressViewText();
	
	public void start() {
		
		//	종료 플래그 설정
		boolean endFlag = true;
		avt.mainPrint();
		
		while(endFlag) {
			
			//	메뉴 출력 & 메뉴 번호 입력
			avt.selectMenu();
			int num = sc.nextInt();
			
			switch(num) {
			case 1:
				listAddress();
				break;
			case 2:
				insertAddress();
				break;
			case 3:
				deleteAddress();
				break;
			case 4:
				searchAddress();
				break;
			case 5:
				endFlag = false;
				break;
				
				default:
					avt.numError();
			}
			
		}
		avt.endPrint();
	}

	//	리스트 출력
	public static void listAddress() {
		AddressDao dao = new AddressService();
		List<AddressVo> list = dao.getList();
		
		Iterator<AddressVo> it = list.iterator();
		
		while(it.hasNext()) {
			AddressVo vo = it.next();
			System.out.printf("%d.\t %s\t %s\t %s\n",
					vo.getId(), vo.getName(), vo.getHp(), vo.getTel());
		}
	}
	
	//	등록
	public static void insertAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<2.등록>");
		System.out.print(">이름 : ");
		String name = sc.next();
		System.out.print(">휴대폰 : ");
		String hp = sc.next();
		System.out.print(">집전화 : ");
		String tel = sc.next();
		
		AddressVo vo = new AddressVo(name, hp, tel);
		AddressDao dao = new AddressService();
		
		boolean success = dao.insert(vo);
		System.out.println("Address INSERT : " + (success ? "성공": "실패"));
	}
	
	//	삭제
	public static void deleteAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<3.삭제>");
		System.out.print(">번호 : ");
		Long id = sc.nextLong();
		
		AddressDao dao = new AddressService();
		boolean success = dao.delete(id);
		
		System.out.println("Address DELETE : " + (success ? "성공": "실패"));
	}
	
	//	검색
	public static void searchAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<4.검색>");
		System.out.print(">이름 : ");
		String keyword = sc.next();
		
		AddressDao dao = new AddressService();
		List<AddressVo> list = dao.search(keyword);
		
		Iterator<AddressVo> it = list.iterator();
		
		while(it.hasNext()) {
			AddressVo vo = it.next();
			System.out.printf("%d.\t %s\t %s\t %s\n", 
					vo.getId(), vo.getName(), vo.getHp(), vo.getTel());
		}
	}
	
}
