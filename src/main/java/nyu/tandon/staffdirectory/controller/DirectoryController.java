package nyu.tandon.staffdirectory.controller;

import nyu.tandon.staffdirectory.model.*;
import nyu.tandon.staffdirectory.service.DepartmentService;
import nyu.tandon.staffdirectory.service.DirectoryService;
import nyu.tandon.staffdirectory.service.GroupService;
import nyu.tandon.staffdirectory.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private GroupService groupService;

    @GetMapping("/{empTypeCode}")
    public ResponseEntity<PersonnelResponse> getEmployees(@PathVariable("deptCode") String deptCode, @PathVariable("empTypeCode") String empTypeCode) {
        try {
            List<Directory> employees = directoryService.findAllEmployees(deptCode, empTypeCode);
            List<String> netIds = employees.stream()
                    .map(Directory::getNetId)
                    .collect(Collectors.toList());

            List<Personnel> personnels = retrievePersonnelInfo(netIds);

            PersonnelResponse personnelResponse = createPersonnelResponse(personnels, deptCode);

            if (personnelResponse != null) {
                return new ResponseEntity<>(personnelResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{flag}")
    public ResponseEntity<List<GroupNameResponse>> getListAosOrResearch(@PathVariable("deptCode") String deptCode, @PathVariable("flag") String flag) {
        try {
            List<Groupinfo> groupInfo = groupService.findGroupInfo(flag, deptCode);
            List<String> groupNames = groupInfo.stream()
                    .map(Groupinfo::getGroupName)
                    .collect(Collectors.toList());

            List<GroupNameResponse> groupInfoList = new ArrayList<>();
            for (int i = 0; i < groupNames.size(); i++) {
                String groupName = groupNames.get(i);
                if (groupName != null) {
                    GroupNameResponse groupNameResponse = new GroupNameResponse();
                    groupNameResponse.setTitle(groupName);
                    groupNameResponse.setId(i + 1);
                    groupInfoList.add(groupNameResponse);
                }
            }

            if (!groupInfoList.isEmpty()) {
                groupInfoList.forEach(System.out::println);
                return new ResponseEntity<>(groupInfoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/aos/{groupName}")
    public ResponseEntity<AosResponse> getAos(@PathVariable("deptCode") String deptCode, @PathVariable("groupName") String groupName) {
        try {
            System.out.println(groupName+"Aman");
            Groupinfo groupCodeInfo = groupService.findGroupCode(deptCode, groupName);
            String groupCode = null;
            String description = null;
            if (groupCodeInfo != null) {
                groupCode = groupCodeInfo.getGroupCode();
                description = groupCodeInfo.getGroupDescription();
            }
            List<Directory> directories = directoryService.findEmployees(deptCode, groupCode);
            List<String> netIds = directories.stream().map(Directory::getNetId).collect(Collectors.toList());

            List<Personnel> personnels = retrievePersonnelInfo(netIds);

            AosResponse aosResponse = createAosResponse(personnels, deptCode, description);

            if (aosResponse != null) {
                return new ResponseEntity<>(aosResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rg/{groupName}")
    public ResponseEntity<AosResponse> getRg(@PathVariable("deptCode") String deptCode, @PathVariable("groupName") String groupName) {
        try {
            Groupinfo groupCodeInfo = groupService.findGroupCode(deptCode, groupName);

            String groupCode = null;
            String description = null;
            if (groupCodeInfo != null) {
                groupCode = groupCodeInfo.getGroupCode();
                description = groupCodeInfo.getGroupDescription();
            }
            List<Directory> directories = directoryService.findEmployees(deptCode, groupCode);
            List<String> netIds = directories.stream().map(Directory::getNetId).collect(Collectors.toList());

            List<Personnel> personnels = retrievePersonnelInfo(netIds);

            AosResponse aosResponse = createAosResponse(personnels, deptCode, description);

            if (aosResponse != null) {
                return new ResponseEntity<>(aosResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/SC")
    public ResponseEntity<String> getStudentClub(@PathVariable("deptCode") String deptCode) {
        try {
            Department dept= null;
            dept = departmentService.findStudentClubUrl(deptCode);
            if (dept != null && dept.getEventsUrl() != null) {
                return new ResponseEntity<>(dept.getEventsUrl(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Personnel> retrievePersonnelInfo(List<String> netIds) {
        try {
            List<Personnel> personnels = personnelService.findEmployeesInfo(netIds);
            if (personnels != null) {
                Collections.sort(personnels, Comparator.comparing(Personnel::getLastName));
            }
            return personnels;
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return null;
        }
    }

    private PersonnelResponse createPersonnelResponse(List<Personnel> personnels, String deptCode) {
        try {
            PersonnelResponse personnelResponse = new PersonnelResponse();
            if (personnels != null) {
                personnelResponse.setPersonnels(personnels);
                Department deptName = getDeptName(deptCode);
                if (deptName != null && deptName.getDeptName() != null) {
                    personnelResponse.setDeptName(deptName.getDeptName());
                }
                return personnelResponse;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return null;
        }
    }

    private AosResponse createAosResponse(List<Personnel> personnels, String deptCode, String description) {
        try {
            AosResponse aosResponse = new AosResponse();
            if (personnels != null) {
                aosResponse.setPersonnels(personnels);
                Department deptName = getDeptName(deptCode);
                if (deptName != null && deptName.getDeptName() != null) {
                    aosResponse.setDeptName(deptName.getDeptName());
                }
                if (description != null) {
                    aosResponse.setDescription(description);
                }
                return aosResponse;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return null;
        }
    }

    public Department getDeptName(String deptCode) {
        try {
            Department deptName = null;
            deptName = departmentService.findDeptName(deptCode);
            return deptName;
        } catch (Exception e) {
            // Handle and log the exception
            e.printStackTrace();
            return null;
        }
    }
}
