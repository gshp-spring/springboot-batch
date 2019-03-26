package com.sriharilabs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sriharilabs.model.Employee;
import com.sriharilabs.model.Hospitality;

@Service
public class ExcelUtilityService {

	XSSFWorkbook myWorkBook = null;
	XSSFSheet mySheet = null;
	Integer columnIndex;

	//Hospitality hospitality = null;
	int nameColumnNumber = 1;
	int idColumnNumber = 0;

	public boolean isThisRowNamesAndIdsExist(Row row, int cellNumber) {
		if (row.getCell(cellNumber) != null && !(row.getCell(cellNumber).toString().trim()).equals("")) {
			return true;
		}
		return false;
	}

	public boolean checkYes(Row row, int columnNumber) {
		if (row.getCell(columnNumber) != null && !(row.getCell(columnNumber).toString().trim()).equals("")
				&& (row.getCell(columnNumber).toString().trim()).equals("y")) {
			return true;
		}
		return false;
	}

	public List<Employee> getEmployeesFromYesColumn(int columnNumber) {

		List<Employee> employeeList = new ArrayList<Employee>();
		mySheet.rowIterator().forEachRemaining(row -> {
			
			
			Employee emp = new Employee();

			if (row.getRowNum() != 0 && row.getRowNum()!=1)  {
				if (isThisRowNamesAndIdsExist(row, nameColumnNumber) && isThisRowNamesAndIdsExist(row, idColumnNumber)
						&& checkYes(row, columnNumber)) {
					
					emp.setName(row.getCell(nameColumnNumber).toString());
					emp.setId((int) Float.parseFloat(row.getCell(idColumnNumber).toString()));
					employeeList.add(emp);

				}

			}

		});
		return employeeList;

	}

	public Hospitality getHospitalityEmployeesByRowColumn(int columnNumber) {

		Hospitality hospitality = new Hospitality();

		List<Employee> employeeList = getEmployeesFromYesColumn(columnNumber);
		employeeList.forEach(u -> {
			System.out.println(u.getId() + " ::  " + u.getName());
		});
		hospitality.setEmployeeList(employeeList);

		return hospitality;
	}

	public Integer getCoumnNumberByDate(int dateColumn_columnIndex) {
		mySheet.getRow(0).forEach(cell -> {
			if (cell.getCellType() == 0) {
				if ((int) cell.getNumericCellValue() == dateColumn_columnIndex) {
					columnIndex = cell.getColumnIndex();

				}
			}

		});
		return columnIndex;
	}

	public Hospitality saveHospitalityExcel(MultipartFile file, LocalDate localDate,String sheetName) {
		try {

			myWorkBook = new XSSFWorkbook(file.getInputStream());

			mySheet = myWorkBook.getSheet(sheetName);
			Hospitality hospitality=	getHospitalityEmployeesByRowColumn(getCoumnNumberByDate(localDate.getDayOfMonth()));
			
			hospitality.setDate(localDate);
			hospitality.setHospitalityType(sheetName);
			hospitality.setId(sheetName+localDate);
			return hospitality;
		} catch (Exception exception) {
			System.out.println(" 1 " + exception);
		}
		return null;
	}

}
