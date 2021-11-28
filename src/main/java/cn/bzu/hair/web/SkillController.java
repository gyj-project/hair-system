package cn.bzu.hair.web;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.Skill;
import cn.bzu.hair.service.SkillService;
import cn.bzu.hair.utils.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * (Skill)表控制层
 *
 * @author 高玉津
 * @since 2020-04-20 14:02:45
 */
@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;





    @GetMapping("/data/query")
    public Msg getSkills(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(required = false) Long skillId,
                           @RequestParam(required = false) String businessName,
                           @RequestParam(required = false) String barberName){


        PageMethod.startPage(page,size);
        List<Skill> list = skillService.querySkills(skillId, businessName, barberName);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @PutMapping()
    public Msg updateSkill(@RequestBody Skill skill) {
        if (skillService.updateSkill(skill)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping()
    public Msg addSkill(@RequestBody Skill skill) {
        if (skillService.addSkill(skill)) {
            return Msg.success();
        }
        return Msg.fail();
    }
    @DeleteMapping("/{id}")
    public Msg deleteSkill(@PathVariable("id") Long id) {
        skillService.deleteSkillById(id);
        return Msg.success();
    }
    @GetMapping("/query/by/businessId")
    public Msg getBarberByBusinessId(@RequestParam("id") Long businessId) {
        List<Skill> skills = skillService.getSkillByBusinessId(businessId);
        return Msg.success().add("skill", skills);
    }

    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportTemplate(HttpServletResponse response) throws IOException {

        byte[] bytes = FileUtil.getFileBinaryForDownload(
                FileUtil.getTemplatePath("/templates/skill.xlsx"));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDispositionFormData("attachment", "skillTemplate.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

//        response.setContentType("application/octet-stream");
        //response.setHeader("content-type", "application/octet-stream");
       // response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //response.setHeader("Content-Disposition", "attachment;filename=skill.xlsx");

    }
    @RequestMapping(value = "/import/new", method = RequestMethod.POST)
    public void importSkill(@RequestParam("file") MultipartFile file, HttpServletResponse response)throws Exception{

        try (InputStream in = file.getInputStream()) {
            skillService.importSkillInfo(in,null);

        } catch (IOException e) {
            throw new Exception("read File error");
        }
        response.sendRedirect("http://localhost:8080/#/skillManager");
    }

    @GetMapping(value = "/export/data")
    public void exportInvoicingSite(HttpServletRequest request,
                                    @RequestParam(required = false) String businessName,
                                    @RequestParam(required = false) String barberName,
                                     HttpServletResponse response) throws IOException {

        List<Skill> skills = skillService.querySkills(null, businessName, barberName);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("技能表");
        HSSFRow row = null;
        row = sheet.createRow(0);
        row.setHeight((short)(22.50*20));
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("理发师");
        row.createCell(2).setCellValue("业务");

        for(int i = 0; i < skills.size(); i++){
            row = sheet.createRow(i+1);
            Skill skill = skills.get(i);
            row.createCell(0).setCellValue(skill.getId());
            row.createCell(1).setCellValue(skill.getBarberName());
            row.createCell(2).setCellValue(skill.getBusinessName());


        }
        // 默认行高
        sheet.setDefaultRowHeight((short)(16.5*25));
        // 列宽自适应
        for(int i=0;i<=13;i++){
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();

    }



}