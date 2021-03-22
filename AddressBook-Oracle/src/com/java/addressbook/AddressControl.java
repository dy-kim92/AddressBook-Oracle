package com.java.addressbook;

import java.util.Scanner;


public class AddressControl{
	
	Scanner sc = new Scanner(System.in);
	
	AddressService as = new AddressService();
	AddressViewText avt = new AddressViewText();
	
	public void start() {
		
		boolean endFlag = true;
		avt.mainPrint();
		
		while(endFlag) {
			
			avt.selectMenu();
			int num = sc.nextInt();
			
			switch(num) {
			case 1:
				as.showList();
				break;
			case 2:
				as.addressInsert();
				break;
			case 3:
				as.addressDelete();
				break;
			case 4:
				as.addressSearch();
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

}
