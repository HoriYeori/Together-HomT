package com.ssafy.togetherhomt.exercise.attendance;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    private AttendanceRepository attendanceRepository;
    private UserRepository userRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    // 00시가 되면 초기화
    @Scheduled(cron = "0 0 0 * * ?")
    public void create(){
        System.out.println("attendance created!!!!!!!");
        List<User> users = userRepository.findAll();
        attendanceRepository.deleteAll();

        for(User user:users){
            Attendance attendance = Attendance.builder()
                    .user(user)
                    .done(false)
                    .build();
            attendanceRepository.save(attendance);
        }
    }

    // 오늘 불참러 조회
    public List<AttendanceDto> todayAttendance(){
        List<AttendanceDto> attendees = new ArrayList<>();
        for(Attendance attendance:attendanceRepository.findAllByDoneFalse()){
            AttendanceDto attendanceDto = AttendanceDto.builder()
                    .username(attendance.getUser().getUsername())
                    .image(attendance.getUser().getImagePath())
                    .done(attendance.getDone())
                    .build();
            attendees.add(attendanceDto);
        }
        return attendees;
    }
}
