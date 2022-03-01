package com.lou.cloud_office_back.utils;

import com.lou.cloud_office_back.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ExcelUtil {

    /**
     * 导出Excel
     *
     * @param users 内容
     * @param wb    HSSFWorkbook对象
     * @return
     */
    public void getHSSFWorkbook(List<User> users, HSSFWorkbook wb) throws IOException {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("云办公");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        // excel标题
        String[] title = {"id", "姓名", "部门","密码","角色"};
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        int count = users.size();
        for (User user : users) {
            HSSFRow row_temp = sheet.createRow(count--);
            row_temp.createCell(0).setCellValue(user.getId());
            row_temp.createCell(1).setCellValue(user.getUsername());
            row_temp.createCell(2).setCellValue(user.getDept());
            row_temp.createCell(3).setCellValue(user.getPassword());
            if (user.getRole()==0){
                row_temp.createCell(4).setCellValue("员工");
            }else {
                row_temp.createCell(4).setCellValue("管理员");
            }
//            for (int j = 0;j < User.class.getDeclaredFields().length;j++){
//
//            }
        }

//        //创建内容
//        String[][] content = new String[values.size()][3];
//
//        for (int i = 0; i < values.size(); i++) {
//            Map<String, Object> obj = values.get(i);
//            content[i][0] = (String)obj.get("report_time");
//            content[i][1] = obj.get("name").toString();
//            content[i][2] = obj.get("report_content").toString();
//        }
//
//
//        for(int i=0;i<values.size();i++){
//            row = sheet.createRow(i + 1);
//            for(int j=0;j<content[i].length;j++){
//                //将内容按顺序赋给对应的列对象
//                row.createCell(j).setCellValue(content[i][j]);
//            }
//        }
//
//        for (int i = 0; i < title.length; i++) {
//            sheet.autoSizeColumn(i);
//            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*17/10);
//        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date1 = "E:/Excel/云办公"+sdf.format(date)+".xls";

        FileOutputStream stream = new FileOutputStream(new File(date1));
        wb.write(stream);
        stream.flush();
        stream.close();
    }
}