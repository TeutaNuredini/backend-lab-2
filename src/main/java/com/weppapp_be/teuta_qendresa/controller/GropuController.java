package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.entity.Gropu;
import com.weppapp_be.teuta_qendresa.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GropuController {

    private final GroupService groupService;


    @PostMapping("/save")
    public Gropu saveGroup(@RequestBody Gropu group) {
        return groupService.saveGroup(group);
    }

    @GetMapping("/get/{id}")
    public Gropu getGroupById(Long id) {
        return groupService.getGroupById(id);
    }

    @GetMapping("/all")
    public List<Gropu> getAllGroups() {
        return groupService.getAllGroups();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(@PathVariable  Long id) {
        groupService.deleteGroup(id);
    }
}
