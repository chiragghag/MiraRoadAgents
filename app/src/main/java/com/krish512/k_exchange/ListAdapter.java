/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.krish512.k_exchange.R;

public class ListAdapter extends ArrayAdapter<Item> {
	private final Context context;
	private final ArrayList<Item> itemsArrayList;

	public enum Type {
		PROPERTY, MYPROPERTY, AGENT, ROW3LINES, NOTICEBOARD
	}

	private final Type type;

	public ListAdapter(Context context, ArrayList<Item> itemsArrayList,
			Type type) {

		super(context, R.layout.listrow3lines, itemsArrayList);

		this.context = context;
		this.itemsArrayList = itemsArrayList;
		this.type = type;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		if (type == Type.AGENT) {
			View rowView = inflater.inflate(R.layout.agentrow, parent, false);

			if (position % 2 == 1) {
				rowView.setBackgroundResource(R.drawable.listitemalternate);
			}

			// 3. Get the two text view from the rowView
			TextView row1 = (TextView) rowView.findViewById(R.id.row1);
			TextView row2 = (TextView) rowView.findViewById(R.id.row2);
			TextView row3 = (TextView) rowView.findViewById(R.id.row3);
			TextView rowLocality = (TextView) rowView
					.findViewById(R.id.rowLocality);
			TextView row3view = (TextView) rowView.findViewById(R.id.row3view);

			// 4. Set the text for textView
			row1.setText(itemsArrayList.get(position).getRow1());
			row2.setText(itemsArrayList.get(position).getRow2());
			row3.setText(itemsArrayList.get(position).getRow3());
			rowLocality.setText(itemsArrayList.get(position).getRowLocation());
			row3view.setText(itemsArrayList.get(position).getRow3view());

			// 5. return rowView
			return rowView;
		} else if (type == Type.PROPERTY) {
			View rowView = inflater
					.inflate(R.layout.propertyrow, parent, false);

			if (position % 2 == 1) {
				rowView.setBackgroundResource(R.drawable.listitemalternate);
			}

			if (itemsArrayList.get(position).getIsRed()) {
				rowView.setBackgroundResource(R.drawable.listitemred);
			}

			// 3. Get the two text view from the rowView
			TextView rowCost = (TextView) rowView.findViewById(R.id.rowCost);
			TextView rowType = (TextView) rowView.findViewById(R.id.rowType);
			TextView rowLocation = (TextView) rowView
					.findViewById(R.id.rowLocation);
			TextView rowArea = (TextView) rowView.findViewById(R.id.rowArea);
			TextView rowFloor = (TextView) rowView.findViewById(R.id.rowFloor);
			TextView rowAddress = (TextView) rowView
					.findViewById(R.id.rowAddress);
			TextView rowResCom = (TextView) rowView
					.findViewById(R.id.rowResCom);
			TextView rowSideDirect = (TextView) rowView
					.findViewById(R.id.rowSideDirect);
			TextView rowSellRent = (TextView) rowView
					.findViewById(R.id.rowSellRent);
			TextView rowPID = (TextView) rowView.findViewById(R.id.rowPID);
			TextView rowDate = (TextView) rowView.findViewById(R.id.rowDate);
			TextView row3view = (TextView) rowView.findViewById(R.id.row3view);

			// 4. Set the text for textView
			rowCost.setText(itemsArrayList.get(position).getRowCost());
			rowType.setText(itemsArrayList.get(position).getRowType());
			rowLocation.setText(itemsArrayList.get(position).getRowLocation());
			rowArea.setText(itemsArrayList.get(position).getRowArea());
			rowFloor.setText(itemsArrayList.get(position).getRowFloor());
			rowAddress.setText(itemsArrayList.get(position).getRowAddress());
			rowResCom.setText(itemsArrayList.get(position).getRowResCom());
			rowSideDirect.setText(itemsArrayList.get(position)
					.getRowSideDirect());
			rowSellRent.setText(itemsArrayList.get(position).getRowSellRent());
			rowPID.setText(itemsArrayList.get(position).getRowPID());
			rowDate.setText(itemsArrayList.get(position).getRowDate());
			row3view.setText(itemsArrayList.get(position).getRow3view());

			// 5. return rowView
			return rowView;
		} else if (type == Type.MYPROPERTY) {
			View rowView = inflater.inflate(R.layout.mypropertyrow, parent,
					false);

			if (position % 2 == 1) {
				rowView.setBackgroundResource(R.drawable.listitemalternate);
			}

			if (itemsArrayList.get(position).getIsRed()) {
				rowView.setBackgroundResource(R.drawable.listitemred);
			}

			// 3. Get the two text view from the rowView
			final CheckBox chkSelect = (CheckBox) rowView
					.findViewById(R.id.chkSelect);
			TextView rowCost = (TextView) rowView.findViewById(R.id.rowCost);
			TextView rowType = (TextView) rowView.findViewById(R.id.rowType);
			TextView rowLocation = (TextView) rowView
					.findViewById(R.id.rowLocation);
			TextView rowArea = (TextView) rowView.findViewById(R.id.rowArea);
			TextView rowFloor = (TextView) rowView.findViewById(R.id.rowFloor);
			TextView rowAddress = (TextView) rowView
					.findViewById(R.id.rowAddress);
			TextView rowResCom = (TextView) rowView
					.findViewById(R.id.rowResCom);
			TextView rowSideDirect = (TextView) rowView
					.findViewById(R.id.rowSideDirect);
			TextView rowSellRent = (TextView) rowView
					.findViewById(R.id.rowSellRent);
			TextView rowPID = (TextView) rowView.findViewById(R.id.rowPID);
			TextView rowDate = (TextView) rowView.findViewById(R.id.rowDate);
			TextView row3view = (TextView) rowView.findViewById(R.id.row3view);

			// 4. Set the text for textView
			chkSelect.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// CheckBox cb = (CheckBox) v;
					// chkSelect.setChecked(cb.isChecked());
					if (itemsArrayList.get(position).isSelected()) {
						itemsArrayList.get(position).unsetSelected();
					} else {
						itemsArrayList.get(position).setSelected();
					}
					chkSelect.setChecked(itemsArrayList.get(position)
							.isSelected());
				}
			});

			chkSelect.setChecked(itemsArrayList.get(position).isSelected());
			rowCost.setText(itemsArrayList.get(position).getRowCost());
			rowType.setText(itemsArrayList.get(position).getRowType());
			rowLocation.setText(itemsArrayList.get(position).getRowLocation());
			rowArea.setText(itemsArrayList.get(position).getRowArea());
			rowFloor.setText(itemsArrayList.get(position).getRowFloor());
			rowAddress.setText(itemsArrayList.get(position).getRowAddress());
			rowResCom.setText(itemsArrayList.get(position).getRowResCom());
			rowSideDirect.setText(itemsArrayList.get(position)
					.getRowSideDirect());
			rowSellRent.setText(itemsArrayList.get(position).getRowSellRent());
			rowPID.setText(itemsArrayList.get(position).getRowPID());
			rowDate.setText(itemsArrayList.get(position).getRowDate());
			row3view.setText(itemsArrayList.get(position).getRow3view());

			// 5. return rowView
			return rowView;
		} else if (type == Type.NOTICEBOARD) {
			View rowView = inflater.inflate(R.layout.noticeboardrow, parent,
					false);

			// if(itemsArrayList.get(position).getIsRed())
			// {
			// rowView.setBackgroundResource(R.drawable.listitemred);
			// }

			// 3. Get the two text view from the rowView
			TextView row1 = (TextView) rowView.findViewById(R.id.row1);
			TextView rowPhoneno = (TextView) rowView
					.findViewById(R.id.rowPhoneno);
			TextView rowAltno = (TextView) rowView.findViewById(R.id.rowAltno);
			TextView rowDate = (TextView) rowView.findViewById(R.id.rowDate);
			TextView row3 = (TextView) rowView.findViewById(R.id.row3);

			// 4. Set the text for textView
			row1.setText(itemsArrayList.get(position).getRow1());
			rowPhoneno.setText(itemsArrayList.get(position).getRowPhoneno());
			rowAltno.setText(itemsArrayList.get(position).getRowAltno());
			rowDate.setText(itemsArrayList.get(position).getRowDate());
			row3.setText(itemsArrayList.get(position).getRow3());

			// 5. return rowView
			return rowView;
		} else {
			View rowView = inflater.inflate(R.layout.listrow3lines, parent,
					false);

			if (position % 2 == 1) {
				rowView.setBackgroundResource(R.drawable.listitemalternate);
			}

			// 3. Get the two text view from the rowView
			TextView row1 = (TextView) rowView.findViewById(R.id.row1);
			TextView row2 = (TextView) rowView.findViewById(R.id.row2);
			TextView row3 = (TextView) rowView.findViewById(R.id.row3);
			TextView row3view = (TextView) rowView.findViewById(R.id.row3view);

			// 4. Set the text for textView
			row1.setText(itemsArrayList.get(position).getRow1());
			row2.setText(itemsArrayList.get(position).getRow2());
			row3.setText(itemsArrayList.get(position).getRow3());
			row3view.setText(itemsArrayList.get(position).getRow3view());

			// 5. return rowView
			return rowView;
		}
	}
}
