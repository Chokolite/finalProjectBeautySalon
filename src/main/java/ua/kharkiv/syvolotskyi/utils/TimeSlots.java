package ua.kharkiv.syvolotskyi.utils;

import ua.kharkiv.syvolotskyi.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TimeSlots {
    Map<LocalDateTime, Map<LocalDateTime, Boolean>> createSchedule(List<Appointment> appointmentList, Long masterId);
}
