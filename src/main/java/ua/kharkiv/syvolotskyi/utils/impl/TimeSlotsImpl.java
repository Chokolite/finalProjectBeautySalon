package ua.kharkiv.syvolotskyi.utils.impl;

import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Status;
import ua.kharkiv.syvolotskyi.utils.TimeSlots;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TimeSlotsImpl implements TimeSlots {
    private LocalDateTime startTime = changeHour(LocalDateTime.now(), 9, 0);
    private List<Appointment> appointmentListByMasterId = new ArrayList<>();
    private LocalDateTime finishHour = changeHour(startTime, 20, 0);
    private LocalDateTime finishDay = startTime.with(TemporalAdjusters.lastDayOfMonth());
    //Creatind map Map<data, Map<time, Boolean>> map. data = day. time - filing with hours started from startTime and finished with finishTime
    private Map<LocalDateTime, Map<LocalDateTime, Boolean>> shelude = new LinkedHashMap<>();


    //Creating map started from today and finished in last day of month, false = free slot
    @Override
    public Map<LocalDateTime, Map<LocalDateTime, Boolean>> createShelude(List<Appointment> appointmentList, Long masterId) {
        createAppointmentLocalDateTimeListByMasterId(appointmentList, masterId);
        for (; startTime.getDayOfMonth() <= finishDay.getDayOfMonth(); startTime = startTime.plusDays(1)) {
            shelude.put(startTime, fillSheludeOfDay(startTime, finishHour, appointmentListByMasterId));
            if (startTime.getDayOfMonth() == finishDay.getDayOfMonth()) {
                break;
            }
        }  //compare getDayOfMonth in the map with appointmentDate.getDayOfMonth if equals => value=true(busy)
        shelude.forEach((key, value) -> appointmentListByMasterId.forEach(al -> {
            if (!al.getStatus().equals(Status.COMPLETE)) {
                if (al.getLocalDateTime().getDayOfMonth() == key.getDayOfMonth() && al.getLocalDateTime().getHour() == key.getHour()) {
                    shelude.put(key, value);
                }
            }
        }));
        return shelude;
    }

    private void createAppointmentLocalDateTimeListByMasterId(List<Appointment> appointmentList, Long masterId) {
        appointmentList.forEach(ap -> {
            if (ap.getCatalog().getMaster().getId().equals(masterId)) {
                appointmentListByMasterId.add(ap);
            }
        });
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

        currentHour = originCurrentHour; //reset variable

        for (Appointment al : appointmentList) {
            for (; currentHour.getHour() <= finishHour.getHour(); currentHour = currentHour.plusHours(1)) {
                if (currentHour.getMonthValue() == al.getLocalDateTime().getMonthValue()) {
                    if (currentHour.getHour() == al.getLocalDateTime().getHour() && currentHour.getDayOfMonth() == al.getLocalDateTime().getDayOfMonth()) {
                        sheludeOfDay.put(currentHour, true);
                    }
                }
            }
            currentHour = originCurrentHour;
        }
        return sheludeOfDay;
    }
}
