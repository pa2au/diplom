package com.example.tametable.service;

import com.example.tametable.entity.Group;
import com.example.tametable.repository.FacultyRepository;
import com.example.tametable.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void save(int facultyId, String name) {
        Group group = Group.builder()
                .faculty(facultyRepository.getById(facultyId))
                .name(name)
                .build();
        groupRepository.save(group);
    }

    @Override
    public void delete(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group findGroupById(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Entity with %d doesn't exist", id)));
    }

    @Override
    public void updateGroup(int id, int facultyId, String name) {
        Group group = findGroupById(id);
        group.setFaculty(facultyRepository.getById(facultyId));
        group.setName(name);
        groupRepository.save(group);
    }

}
