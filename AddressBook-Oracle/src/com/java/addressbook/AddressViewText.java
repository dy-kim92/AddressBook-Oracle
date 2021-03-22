package com.java.addressbook;

public class AddressViewText {
	
	public void mainPrint() {
		System.out.println("--------------------------------------");
		System.out.println("*        전화번호 관리 프로그램           *");
		System.out.println("--------------------------------------");
	}
	
	public void selectMenu() {
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("--------------------------------------");
		System.out.print(">메뉴번호 : ");
	}
	
	public void numError() {
		System.out.println("[다시 입력해 주세요]");
	}
	
	public void endPrint() {
		System.out.println("--------------------------------------");
		System.out.println("*             감사합니다                *");
		System.out.println("--------------------------------------");
	}

}
