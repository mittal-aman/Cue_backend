package nyu.tandon.staffdirectory.controller;

import nyu.tandon.staffdirectory.model.Department;
import nyu.tandon.staffdirectory.model.Directory;
import nyu.tandon.staffdirectory.model.Personnel;
import nyu.tandon.staffdirectory.model.Response;
import nyu.tandon.staffdirectory.service.DepartmentService;
import nyu.tandon.staffdirectory.service.DirectoryService;
import nyu.tandon.staffdirectory.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/{deptCode}")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{empTypeCode}")
    public ResponseEntity<Response> getEmployees(@PathVariable("deptCode") String deptCode, @PathVariable("empTypeCode") String empTypeCode) {
        List<Directory> employees = directoryService.findAllEmployees(deptCode, empTypeCode);
        List<String> netIds = employees.stream().map(employee -> employee.getNetId()).collect(Collectors.toList());

        List<Personnel> personnels = personnelService.findEmployeesInfo(netIds);
        Collections.sort(personnels, Comparator.comparing(Personnel::getLastName));

        Response response = new Response();
        response.setPersonnels(personnels);
        Department deptName = getDeptName(deptCode);
        if (deptName != null && deptName.getDeptName() != null) {
            response.setDeptName(deptName.getDeptName());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Department getDeptName(String deptCode) {
        Department deptName = null;
        deptName = departmentService.findDeptName(deptCode);
        return deptName;
    }

}