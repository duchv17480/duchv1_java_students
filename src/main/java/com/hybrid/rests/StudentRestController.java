package com.hybrid.rests;

import com.hybrid.dtos.StudentDto;
import com.hybrid.entities.Student;
import com.hybrid.services.IStudentService;
import com.hybrid.utils.Constants;
import com.hybrid.utils.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public @ResponseBody
    Response findById(@PathVariable Integer id){
        Student student = studentService.getById(id);
        if (student == null){
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success("Get By Id OK").withData(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response save(@RequestBody StudentDto studentDto){
        Student student = modelMapper.map(studentDto, Student.class);
        Student studentCreate = studentService.create(student);
        return Response.success("Created").withData(studentCreate);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response update(@RequestBody StudentDto studentDto,@PathVariable("id")Integer id){
        Student student = studentService.getById(id);
        if (student == null){
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        student = modelMapper.map(studentDto, Student.class);
        Student studentCreate = studentService.create(student);
        return Response.success("Updated").withData(studentCreate);
    }

    /**
     * delete
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Response delete(@PathVariable Integer id){
        if (id > 0){
           Student student = studentService.getById(id);
            if (student == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            studentService.delete(id);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS,"Xoa thanh cong:"+student.getLastName());
        }else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }

    }
}

