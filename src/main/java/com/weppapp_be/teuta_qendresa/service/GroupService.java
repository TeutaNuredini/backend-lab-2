package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.entity.Gropu;
import com.weppapp_be.teuta_qendresa.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;


    public Gropu saveGroup(Gropu group) {
        return groupRepository.save(group);
    }

    public Gropu getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Gropu> getAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
