package com.example.controller;

import com.example.domain.DemoObj;
import com.example.service.ExcelExportService;
import com.example.view.XlsExcelView;
import com.example.view.XlsxExcelView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ExcelController {//导出 excel 的 controller

    @RequestMapping(value = "/export1",method = RequestMethod.GET)
    public ModelAndView export1(){
        //模型和视图
        ModelAndView mv =  new ModelAndView();
        //Excel 视图，并设置自定义导出接口
        XlsExcelView ev = new XlsExcelView(excelExportService1());
        //文件名
        ev.setFilename("所有角色.xls");
        //设置SQL 后台参数,例如分页
        List<DemoObj> demoObjList = new ArrayList<>();
        for(int i = 0 ;i <1000;i++){
            DemoObj obj = new DemoObj((long) i,"megumi"+i);
            demoObjList.add(obj);
        }

        mv.addObject("demos",demoObjList);
        mv.setView(ev);
        return  mv;
    }


    @RequestMapping(value = "/export2",method = RequestMethod.GET)
    public ModelAndView export2(){  // xlsx 导出
        //模型和视图
        ModelAndView mv =  new ModelAndView();
        //Excel 视图，并设置自定义导出接口
        XlsxExcelView ev = new XlsxExcelView(excelExportService2());
        //文件名
        ev.setFilename("所有角色.xlsx");
        //设置SQL 后台参数,例如分页
        List<DemoObj> demoObjList = new ArrayList<>();
        for(int i = 0 ;i <1000;i++){
            DemoObj obj = new DemoObj((long) i,"六花"+i);
            demoObjList.add(obj);
        }

        mv.addObject("demos",demoObjList);
        mv.setView(ev);
        return  mv;
    }

    @SuppressWarnings("unchecked")
    private  ExcelExportService excelExportService1(){//自定义服务导出 xls
        //使用 Lambda 表达式自定义导出 excel 规则
        return  (Map<String,Object> model , Workbook workbook) ->{
            //获取用户列表
            List<DemoObj> demoObjList = (List<DemoObj>) model.get("demos");
            //生成 Sheet
           HSSFSheet sheet = (HSSFSheet) workbook.createSheet("所有角色");
           sheet.setColumnWidth(0,1000);
            //加载标题
            HSSFRow title = sheet.createRow(0);
            title.createCell(0).setCellValue("编号");
            title.createCell(1).setCellValue("名称");
            //遍历角色列表生成一行行数据
            for(int i = 0 ;i <demoObjList.size() ;i++){
                DemoObj demoObj = demoObjList.get(i);
                int rowIdx = i+1;
                HSSFRow row = sheet.createRow(rowIdx);
                row.createCell(0).setCellValue(demoObj.getId());
                row.createCell(1).setCellValue(demoObj.getName());
            }
        };

    }



    @SuppressWarnings("unchecked")
    private  ExcelExportService excelExportService2(){//自定义服务导出 xlsx
        //使用 Lambda 表达式自定义导出 excel 规则
        return  (Map<String,Object> model , Workbook workbook) ->{
            //获取用户列表
            List<DemoObj> demoObjList = (List<DemoObj>) model.get("demos");
            //生成 Sheet
           XSSFSheet sheet = (XSSFSheet) workbook.createSheet("所有角色");
            //加载标题
            XSSFRow title = sheet.createRow(0);
            title.createCell(0).setCellValue("编号");
            title.createCell(1).setCellValue("名称");
            //遍历角色列表生成一行行数据
            for(int i = 0 ;i <demoObjList.size() ;i++){
                DemoObj demoObj = demoObjList.get(i);
                int rowIdx = i+1;
                XSSFRow row = sheet.createRow(rowIdx);
                row.createCell(0).setCellValue(demoObj.getId());
                row.createCell(1).setCellValue(demoObj.getName());
            }
        };

    }


}
