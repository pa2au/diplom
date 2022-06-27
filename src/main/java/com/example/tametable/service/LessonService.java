package com.example.tametable.service;

import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.DTO.ListLessonGroup;
import com.example.tametable.DTO.ListLessonTeacher;
import com.example.tametable.DTO.ListTeacher;
import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.Role;
import com.example.tametable.entity.User;
import com.example.tametable.enums.Status;
import com.example.tametable.exception.EntityNotFoundException;
import com.example.tametable.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TimeLessonRepo timeLessonRepo;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private WeekDayRepository weekDayRepository;

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private UserRepository userRepository;


    //    public void update(LessonAddDto lessonDTO, Long id) throws Exception {
//        Lesson lesson = lessonRepo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
//        if (lesson.getUser() == userService.getUser() || userService.getUser().getRole() == Role.ADMIN) {
//            if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLectionAndStatus(
//                    userService.getUser(), lessonDTO.getWeekId(), lessonDTO.getTimeLessonId(), false, Status.ACTIVE)) {
//                if (!lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatusAndWeekTypeChislitelAndWeekTypeZnamenatel(lessonDTO.getGroupId(), lessonDTO.getTimeLessonId(), lessonDTO.getWeekId(), Status.ACTIVE)) {
//                    lesson.setTimeLesson(timeLessonRepo.findById(lessonDTO.getTimeLessonId()).orElse(null));
//                    lesson.setGroup(groupRepository.findById(lessonDTO.getGroupId()).orElse(null));
//                    lesson.setIsLection(lessonDTO.getIsLecture());
//                    lesson.setDiscipline(disciplineRepository.findById(lessonDTO.getDisciplineId()).orElse(null));
//                    lesson.setWeekDay(weekDayRepository.findById(lessonDTO.getWeekId()).orElse(null));
//                    lesson.setWeekTypeChislitel(lessonDTO.getWeekTypeChislitel());
//                    lesson.setLink(lessonDTO.getLink());
//                    lesson.setWeekTypeZnamenatel(lessonDTO.getWeekTypeZnamenatel());
//                    lessonRepo.save(lesson);
//                }
//            }
//        }
//        else
//            throw new Exception("Ошибка при изменении!");
//    }
    public List<Lesson> findAllByUser(User user) {
        return lessonRepo.findAllByUser(user);
    }

    public void delete(Long id) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
        lesson.setStatus(Status.DELETE);
        lessonRepo.save(lesson).getId();
    }


//    public void createLesson(LessonAddDto lessonAdd) throws Exception {
//        User user = userService.getUser();
//        if (lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user, lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(),Status.ACTIVE,
//                true, true,lessonAdd.getGroupId())) {
//            throw new Exception("уже существует!");
//        }
//        if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user, lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(),Status.ACTIVE,
//                true, true,lessonAdd.getGroupId())) {
//            Lesson lesson = new Lesson();
//            lesson.setUser(user);
//            lesson.setStatus(Status.ACTIVE);
//            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
//            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
//            lesson.setLection(lessonAdd.isLecture());
//            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
//            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
//            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
//            lesson.setLink(lessonAdd.getLink());
//            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
//            lessonRepo.save(lesson);
//        } else if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user,
//                lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(), Status.ACTIVE, false,
//                true,lessonAdd.getGroupId())) {
//            Lesson lesson = new Lesson();
//            lesson.setUser(user);
//            lesson.setStatus(Status.ACTIVE);
//            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
//            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
//            lesson.setLection(lessonAdd.isLecture());
//            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
//            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
//            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
//            lesson.setLink(lessonAdd.getLink());
//            lessonRepo.save(lesson);
//        }else if(!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user,
//                lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(),  Status.ACTIVE, true,
//                false,lessonAdd.getGroupId())) {
//            Lesson lesson = new Lesson();
//            lesson.setUser(user);
//            lesson.setStatus(Status.ACTIVE);
//            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
//            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
//            lesson.setLection(lessonAdd.isLecture());
//            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
//            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
//            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
//            lesson.setLink(lessonAdd.getLink());
//            lessonRepo.save(lesson);
//        }
//    }


    public String createLesson(LessonAddDto lessonAdd) {
        User user = userService.getUser();
        if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndGroup_Id(user, lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(), Status.ACTIVE, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(user);
            lesson.setStatus(Status.ACTIVE);
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
            lessonRepo.save(lesson);
            return "успешно создан!";
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user, lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, true, false, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(user);
            lesson.setStatus(Status.ACTIVE);
            if (lessonAdd.isWeekTypeChislitel()) {
                return "числитель уже существует!";
            }
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lessonRepo.save(lesson);
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user, lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, false, true, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(user);
            lesson.setStatus(Status.ACTIVE);
            if (lessonAdd.isWeekTypeZnamenatel()) {
                return "знаменатель уже существует!";
            }
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
            lessonRepo.save(lesson);
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(user, lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, true, true, lessonAdd.getGroupId())) {
            return "такой lesson уже существует!";
        }

        return "";
    }


    public List<ListLessonTeacher> getAllTeacherLessons(User user) {
        List<ListLessonTeacher> listModel = new ArrayList<>();
        List<Lesson> lessons = lessonRepo.findByUserAndStatus(user, Status.ACTIVE);
        for (Lesson lesson : lessons) {
            ListLessonTeacher listLessonTeacherModel = new ListLessonTeacher();
            listLessonTeacherModel.setIdLesson(lesson.getId());
            listLessonTeacherModel.setTimeLesson(lesson.getTimeLesson().getTime());
            listLessonTeacherModel.setDiscipline(lesson.getDiscipline().getName());
            listLessonTeacherModel.setGroup(lesson.getGroup().getName());
            listLessonTeacherModel.setLink(lesson.getLink());
            listLessonTeacherModel.setLink2(lesson.getLink2());
            listLessonTeacherModel.setWeekDay(lesson.getWeekDay().getName());
            listLessonTeacherModel.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            listLessonTeacherModel.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            listLessonTeacherModel.setIsLektion(lesson.isLection());
            listModel.add(listLessonTeacherModel);
        }
        return listModel;
    }


    public List<ListLessonGroup> getAllLessonsByGroupIdAndWeekId(Integer groupId, Integer weekId) {
        List<ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessonList = lessonRepo.
                findByStatusAndWeekDay_IdAndGroup_Id(Status.ACTIVE, weekId, groupId).stream().filter(lesson -> lesson.isWeekTypeZnamenatel() && lesson.isWeekTypeChislitel() ||
                !lesson.isWeekTypeZnamenatel() && lesson.isWeekTypeChislitel() ||
                !lesson.isWeekTypeChislitel() && lesson.isWeekTypeZnamenatel()).collect(Collectors.toList());
        for (Lesson lesson : lessonList) {
            ListLessonGroup model = new ListLessonGroup();
            model.setIdLesson(lesson.getId());
            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setLink2(lesson.getLink2());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            model.setIsLektion(lesson.isLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());
            listModel.add(model);

        }
        return listModel;
    }

    public List<ListLessonGroup> getAllLessonsByGroupId(Integer groupId) {
        List<ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessonList = lessonRepo.findByStatusAndGroup_Id(Status.ACTIVE, groupId);

        for (Lesson lesson : lessonList) {

            ListLessonGroup model = new ListLessonGroup();
            model.setIdLesson(lesson.getId());
            model.setLink2(lesson.getLink2());
            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            model.setIsLektion(lesson.isLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());
            listModel.add(model);
        }
        listModel.sort(Comparator.comparingInt(ListLessonGroup::getNumTimeLesson));
        return listModel;

    }

    public List<ListLessonGroup> getAllLessonsByWeekId(Integer weekDayId) {
        List<ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessons = lessonRepo.findByStatusAndWeekDay_Id(Status.ACTIVE, weekDayId);

        for (Lesson lesson : lessons) {
            ListLessonGroup model = new ListLessonGroup();

            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setIdLesson(lesson.getId());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setLink2(lesson.getLink2());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            model.setIsLektion(lesson.isLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());

            listModel.add(model);
        }
        listModel.sort(Comparator.comparingInt(ListLessonGroup::getNumTimeLesson));
        return listModel;
    }

    public List<ListLessonGroup> getAllLessonsForStudent(User user) {

        List<Lesson> lessons = lessonRepo.findByStatusAndGroup_Id(Status.ACTIVE, user.getGroup().getId());

        List<ListLessonGroup> groupListModel = new ArrayList<>();

        for (Lesson lesson : lessons) {
            ListLessonGroup model = new ListLessonGroup();

            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setLink2(lesson.getLink2());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            model.setIsLektion(lesson.isLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());

            groupListModel.add(model);
        }
        groupListModel.sort(Comparator.comparingInt(ListLessonGroup::getNumTimeLesson));
        return groupListModel;
    }

    public String updateLesson(Long id, LessonAddDto lessonAddDto) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doesn't find with id " + id));
//        if (lessonRepo.
//                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userService.getUser(), lessonAddDto.getWeekId()
//                        , lessonAddDto.getTimeLessonId(), Status.ACTIVE, true, false, lessonAddDto.getGroupId())) {
//            if (lessonAddDto.isWeekTypeChislitel()) {
//                return "числитель уже существует!";
//            }

        lesson.setTimeLesson(timeLessonRepo.findById(lessonAddDto.getTimeLessonId()).orElse(null));
        lesson.setLection(lessonAddDto.isLecture());
        lesson.setLink2(lessonAddDto.getLink2());
        lesson.setWeekTypeChislitel(lessonAddDto.isWeekTypeChislitel());
        lesson.setWeekTypeZnamenatel(lessonAddDto.isWeekTypeZnamenatel());
        lesson.setGroup(groupRepository.findById(lessonAddDto.getGroupId()).orElse(null));
        lesson.setLink(lessonAddDto.getLink());
        lessonRepo.save(lesson);
            return "успешно изменен!";
//        } else if (lessonRepo.
//                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userService.getUser(), lessonAddDto.getWeekId()
//                        , lessonAddDto.getTimeLessonId(), Status.ACTIVE, false, true, lessonAddDto.getGroupId())) {
//            if (lessonAddDto.isWeekTypeZnamenatel()) {
//                return "знаменатель уже существует!";
//            }
//            lesson.setDiscipline(disciplineRepository.findById(lessonAddDto.getDisciplineId()).orElse(null));
//            lesson.setWeekDay(weekDayRepository.findById(lessonAddDto.getWeekId()).orElse(null));
//            lesson.setTimeLesson(timeLessonRepo.findById(lessonAddDto.getTimeLessonId()).orElse(null));
//            lesson.setLection(lessonAddDto.isLecture());
//            lesson.setLink2(lessonAddDto.getLink2());
//            lesson.setWeekTypeChislitel(lessonAddDto.isWeekTypeChislitel());
////            lesson.setWeekTypeZnamenatel(lessonAddDto.isWeekTypeZnamenatel());
//            lesson.setGroup(groupRepository.findById(lessonAddDto.getGroupId()).orElse(null));
//            lesson.setLink(lessonAddDto.getLink());
//            lessonRepo.save(lesson);
//            return "успешно изменен!";
//        } else if (lessonRepo.
//                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userService.getUser(), lessonAddDto.getWeekId()
//                        , lessonAddDto.getTimeLessonId(), Status.ACTIVE, true, true, lessonAddDto.getGroupId())) {
//            lesson.setDiscipline(disciplineRepository.findById(lessonAddDto.getDisciplineId()).orElse(null));
//            lesson.setWeekDay(weekDayRepository.findById(lessonAddDto.getWeekId()).orElse(null));
//            lesson.setTimeLesson(timeLessonRepo.findById(lessonAddDto.getTimeLessonId()).orElse(null));
//            lesson.setLection(lessonAddDto.isLecture());
//            lesson.setLink2(lessonAddDto.getLink2());
//            lesson.setWeekTypeChislitel(lessonAddDto.isWeekTypeChislitel());
//            lesson.setWeekTypeZnamenatel(lessonAddDto.isWeekTypeZnamenatel());
//            lesson.setGroup(groupRepository.findById(lessonAddDto.getGroupId()).orElse(null));
//            lesson.setLink(lessonAddDto.getLink());
//            lessonRepo.save(lesson);
//            return "Успешно изменен!";
//        }
//
//        return "";
    }

    public String createLessonFromAdmin(LessonAddDto lessonAdd) {
        if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndGroup_Id(userRepository.findById(lessonAdd.getTeacherId()).orElse(null), lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(), Status.ACTIVE, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(userRepository.findById(lessonAdd.getTeacherId()).orElse(null));
            lesson.setStatus(Status.ACTIVE);
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
            lessonRepo.save(lesson);
            return "успешно создан!";
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userRepository.findById(lessonAdd.getTeacherId()).orElse(null), lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, true, false, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(userRepository.findById(lessonAdd.getTeacherId()).orElse(null));
            lesson.setStatus(Status.ACTIVE);
            if (lessonAdd.isWeekTypeChislitel()) {
                return "числитель уже существует!";
            }
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setWeekTypeZnamenatel(lessonAdd.isWeekTypeZnamenatel());
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lessonRepo.save(lesson);
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userRepository.findById(lessonAdd.getTeacherId()).orElse(null), lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, false, true, lessonAdd.getGroupId())) {
            Lesson lesson = new Lesson();
            lesson.setUser(userRepository.findById(lessonAdd.getTeacherId()).orElse(null));
            lesson.setStatus(Status.ACTIVE);
            if (lessonAdd.isWeekTypeZnamenatel()) {
                return "знаменатель уже существует!";
            }
            lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
            lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
            lesson.setLection(lessonAdd.isLecture());
            lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
            lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
            lesson.setLink(lessonAdd.getLink());
            lesson.setLink2(lessonAdd.getLink2());
            lesson.setWeekTypeChislitel(lessonAdd.isWeekTypeChislitel());
            lessonRepo.save(lesson);
        } else if (lessonRepo.
                existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(userRepository.findById(lessonAdd.getTeacherId()).orElse(null), lessonAdd.getWeekId()
                        , lessonAdd.getTimeLessonId(), Status.ACTIVE, true, true, lessonAdd.getGroupId())) {
            return "такой lesson уже существует!";
        }

        return "";
    }

    public List<ListTeacher> getAllTeachers() {
        List<User> users = userRepository.findAllByRole(Role.TEACHER);

        List<ListTeacher> listTeachers = new ArrayList<>();
        for (User user : users) {
            ListTeacher model = new ListTeacher();

            model.setName(user.getFirstName() + " " + user.getLastName());
            model.setTeacherId(user.getId());

            listTeachers.add(model);
        }

        return listTeachers;
    }

    public List<Lesson> getLessons() {
        return lessonRepo.findByStatus(Status.ACTIVE);
    }


    //в user get principal пихнешь
    public List<ListLessonGroup> getAllLessonsByUserGroupIdAndWeekId(User user, Integer weekId) {
        List<com.example.tametable.DTO.ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessonList = lessonRepo.
                findByUserAndStatusAndWeekDay_Id(user, Status.ACTIVE, weekId).stream().filter(lesson -> lesson.isWeekTypeZnamenatel() && lesson.isWeekTypeChislitel() ||
                !lesson.isWeekTypeZnamenatel() && lesson.isWeekTypeChislitel() ||
                !lesson.isWeekTypeChislitel() && lesson.isWeekTypeZnamenatel()).collect(Collectors.toList());
        for (Lesson lesson : lessonList) {
            com.example.tametable.DTO.ListLessonGroup model = new com.example.tametable.DTO.ListLessonGroup();
            model.setIdLesson(lesson.getId());
            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setLink2(lesson.getLink2());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.isWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.isWeekTypeZnamenatel());
            model.setIsLektion(lesson.isLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());
            listModel.add(model);

        }
        return listModel;
    }
}
