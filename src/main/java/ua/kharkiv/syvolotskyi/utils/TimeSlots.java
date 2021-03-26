package ua.kharkiv.syvolotskyi.utils;

import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Status;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TimeSlots {
    private Long masterId;
    private LocalDateTime startTime = changeHour(LocalDateTime.now(), 9, 0);
    private List<Appointment> appointmentListByMasterId = new ArrayList<>();
    private LocalDateTime finishHour = changeHour(startTime, 20, 0);
    private LocalDateTime finishDay = startTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    //Створити мапу Map<Дата, Map<час, Boolean>> map. Дата = день. Час - заповнити годинами починаючи з поточного часу і до finishTime
    private Map<LocalDateTime, Map<LocalDateTime, Boolean>> shelude = new LinkedHashMap<>();
    private List<Appointment> appointmentList;

    public TimeSlots(List<Appointment> appointmentList, Long masterId) {
        this.appointmentList = appointmentList;
        this.masterId = masterId;
    }


    public TimeSlots(LocalDateTime currentTime, List<Appointment> appointmentList) {
        this.startTime = currentTime;
        this.appointmentList = appointmentList;
    }

    //Створюю мапу із датами починаючи з сьогодні і по пятницю, false = free slot
    public Map<LocalDateTime, Map<LocalDateTime, Boolean>> createShelude() {
        createAppointmentLocalDateTimeListByMasterId();
        for (; startTime.getDayOfMonth() <= finishDay.getDayOfMonth(); startTime = startTime.plusDays(1)) {
            shelude.put(startTime, fillSheludeOfDay(startTime, finishHour, appointmentListByMasterId));
        }  //порівнюю getDayOfMonth в мапі з appointmentDate.getDayOfMonth if equals => value=true(busy)
        for (Map.Entry<LocalDateTime, Map<LocalDateTime, Boolean>> m : shelude.entrySet()) {
            for (Appointment al : appointmentListByMasterId) {
                if(!al.getStatus().equals(Status.COMPLETE)) {
                    if (al.getLocalDateTime().getDayOfMonth() == m.getKey().getDayOfMonth() && al.getLocalDateTime().getHour() == m.getKey().getHour()) {
                        shelude.put(m.getKey(), m.getValue());
                    }
                }
            }
        }
        return shelude;
    }

    private void createAppointmentLocalDateTimeListByMasterId() {
        for(Appointment ap : appointmentList){
            if(ap.getCatalog().getMaster().getId().equals(masterId)){
                appointmentListByMasterId.add(ap);
            }
        }
    }

    private static LocalDateTime changeHour(LocalDateTime currentTime, int hour, int minute) {
        return currentTime.toLocalDate().atTime(hour, minute);
    }

    private static Map<LocalDateTime, Boolean> fillSheludeOfDay(LocalDateTime currentHour, LocalDateTime finishHour, List<Appointment> appointmentList) {
        Map<LocalDateTime, Boolean> sheludeOfDay = new LinkedHashMap<>();
        LocalDateTime originCurrentHour = currentHour;

        for (; currentHour.getHour() <= finishHour.getHour(); currentHour = currentHour.plusHours(1)) {
            sheludeOfDay.put(currentHour, false);
        }
        currentHour = originCurrentHour;
        for (Appointment al : appointmentList) {
            for (; currentHour.getHour() <= finishHour.getHour(); currentHour = currentHour.plusHours(1)) {
                if (currentHour.getHour() == al.getLocalDateTime().getHour() && currentHour.getDayOfMonth() == al.getLocalDateTime().getDayOfMonth()) {
                    if(al.getStatus().equals(Status.COMPLETE)){
                        sheludeOfDay.put(currentHour, false);
                    }
                }
            }
            currentHour = originCurrentHour;
        }
        return sheludeOfDay;
    }
}
