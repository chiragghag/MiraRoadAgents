/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

public class Item {
	private String row1;
	private String row2;
	private String row3;
	private String rowCost;
	private String rowType;
	private String rowLocation;
	private String rowArea;
	private String rowFloor;
	private String rowAddress;
	private String rowResCom;
	private String rowSideDirect;
	private String rowSellRent;
	private String rowPID;
	private String rowDate;
	private String row3view;
	private String rowPhoneno;
	private String rowAltno;
	private Boolean isRed = false;
	private Boolean isSelected = false;

	public Item(String row1) {
		super();
		this.row1 = row1;
	}

	public Item(String row1, String row2, String row3, String locality) {
		// Agent Listview
		super();
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		this.rowLocation = locality;
		this.row3view = "View";
	}

	public Item(String row1, String rowPhoneno, String rowAltno,
			String lastupdate, String row3) {
		super();
		this.row1 = row1;
		this.rowPhoneno = rowPhoneno;
		this.rowAltno = rowAltno;
		this.rowDate = lastupdate;
		this.row3 = row3;
	}

	public Item(String rowCost, String rowType, String rowLocation,
			String rowArea, String rowFloor, String rowResCom,
			String rowSideDirect, String rowSellRent, String rowPID,
			String rowDate, String row3contact) {
		super();
		this.rowCost = rowCost;
		this.rowType = rowType;
		this.rowLocation = rowLocation;
		this.rowArea = rowArea;
		this.rowFloor = rowFloor;
		this.rowResCom = rowResCom;
		this.rowSideDirect = rowSideDirect;
		this.rowSellRent = rowSellRent;
		this.rowPID = rowPID;
		this.rowDate = rowDate;
		this.row3view = row3contact;
	}

	public Item(String rowCost, String rowType, String rowLocation,
			String rowArea, String rowFloor, String rowAddress,
			String rowResCom, String rowSideDirect, String rowSellRent,
			String rowPID, String rowDate, String row3contact, Boolean isRed) {
		// Property List View
		super();
		this.rowCost = rowCost;
		this.rowType = rowType;
		this.rowLocation = rowLocation;
		this.rowArea = rowArea;
		this.rowFloor = rowFloor;
		this.rowAddress = rowAddress;
		this.rowResCom = rowResCom;
		this.rowSideDirect = rowSideDirect;
		this.rowSellRent = rowSellRent;
		this.rowPID = rowPID;
		this.rowDate = rowDate;
		this.row3view = row3contact;
		this.isRed = isRed;
	}

	public Item(String rowCost, String rowType, String rowLocation,
			String rowArea, String rowFloor, String rowAddress,
			String rowResCom, String rowSideDirect, String rowSellRent,
			String rowPID, String rowDate, String row3contact, Boolean isRed,
			Boolean isSelected) {
		super();
		this.rowCost = rowCost;
		this.rowType = rowType;
		this.rowLocation = rowLocation;
		this.rowArea = rowArea;
		this.rowFloor = rowFloor;
		this.rowAddress = rowAddress;
		this.rowResCom = rowResCom;
		this.rowSideDirect = rowSideDirect;
		this.rowSellRent = rowSellRent;
		this.rowPID = rowPID;
		this.rowDate = rowDate;
		this.row3view = row3contact;
		this.isRed = isRed;
		this.isSelected = isSelected;
	}

	public CharSequence getRow1() {
		// TODO Auto-generated method stub
		return this.row1;
	}

	public CharSequence getRow2() {
		// TODO Auto-generated method stub
		return this.row2;
	}

	public CharSequence getRow3() {
		// TODO Auto-generated method stub
		return this.row3;
	}

	public CharSequence getRowType() {
		// TODO Auto-generated method stub
		return this.rowType;
	}

	public CharSequence getRowCost() {
		// TODO Auto-generated method stub
		return this.rowCost;
	}

	public CharSequence getRowLocation() {
		// TODO Auto-generated method stub
		return this.rowLocation;
	}

	public CharSequence getRowArea() {
		// TODO Auto-generated method stub
		return this.rowArea;
	}

	public CharSequence getRowFloor() {
		// TODO Auto-generated method stub
		return this.rowFloor;
	}

	public CharSequence getRowResCom() {
		// TODO Auto-generated method stub
		return this.rowResCom;
	}

	public CharSequence getRowSideDirect() {
		// TODO Auto-generated method stub
		return this.rowSideDirect;
	}

	public CharSequence getRowSellRent() {
		// TODO Auto-generated method stub
		return this.rowSellRent;
	}

	public CharSequence getRowPID() {
		// TODO Auto-generated method stub
		return this.rowPID;
	}

	public CharSequence getRowDate() {
		// TODO Auto-generated method stub
		return this.rowDate;
	}

	public CharSequence getRow3view() {
		// TODO Auto-generated method stub
		return this.row3view;
	}

	public Boolean getIsRed() {
		// TODO Auto-generated method stub
		return this.isRed;
	}

	public Boolean isSelected() {
		// TODO Auto-generated method stub
		return this.isSelected;
	}

	public void setSelected() {
		// TODO Auto-generated method stub
		this.isSelected = true;
	}

	public void unsetSelected() {
		// TODO Auto-generated method stub
		this.isSelected = false;
	}

	public CharSequence getRowPhoneno() {
		return this.rowPhoneno;
	}

	public CharSequence getRowAltno() {
		return this.rowAltno;
	}

	public CharSequence getRowAddress() {
		return this.rowAddress;
	}
}
