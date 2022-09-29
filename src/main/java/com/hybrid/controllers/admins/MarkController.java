package com.hybrid.controllers.admins;

import com.hybrid.dtos.MarkDTO;
import com.hybrid.entities.Marks;
import com.hybrid.repositories.MarkRepository;
import com.hybrid.services.MarkService;
import com.hybrid.services.StudentService;
import com.hybrid.services.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("admin/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String list(Model model, @RequestParam("sid") Optional<Integer> sid) {
        if (sid.isPresent()) {
            model.addAttribute("list", markRepository.findMarks(sid.get()));
        } else {
            model.addAttribute("list", markService.findAll());
        }
        return "/views/marks/list";
    }

    @GetMapping("create")
    private String create(Marks marks, Model model) {
        model.addAttribute("listStudent", studentService.findAll());
        model.addAttribute("subject", subjectService.findAll());
        return "/views/marks/create";
    }

    @PostMapping("/store")
    public String store(Marks marks) {
        markService.save(marks);
        return "redirect:/admin/marks";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        markService.delete(id);
        return "redirect:/admin/marks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Marks marks, Model model) {
        model.addAttribute("listStudent", studentService.findAll());
        model.addAttribute("subject", subjectService.findAll());
        model.addAttribute("marks", marks);
        return "views/marks/edit";
    }

    @PostMapping("/update/{id}")
    public String update(Marks marks) {
        markService.save(marks);
        return "redirect:/admin/marks";
    }

    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "marks"+currentDateTime+".xlsx";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);
        List<Marks> listMarks = markService.findAll();
        ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Mark ID", "Student", "Subject", "Mark"};
        String[] nameMapping = {"id", "studentId", "subjectId", "mark"};
        beanWriter.writeHeader(csvHeader);
        for (Marks mark : listMarks) {
           var m = MarkDTO.builder()
                .id(mark.getId())
                .studentId(mark.getStudentId())
                .subjectId(mark.getSubjectId())
                .mark(mark.getMark())
                .build();
        log.info("markDTO"+m);
            beanWriter.write(m,nameMapping);
        }
        beanWriter.close();
    }
}

