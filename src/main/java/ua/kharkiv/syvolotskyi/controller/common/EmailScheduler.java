package ua.kharkiv.syvolotskyi.controller.common;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailScheduler {

    private static final long PERIOD = ChronoUnit.DAYS.getDuration().getSeconds();

    private final EmailSender emailSender;

    public EmailScheduler(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void startScheduler() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(emailSender::notifyUsersAboutFeedback, getInitialDelayToEndOfTheDay(), PERIOD, TimeUnit.SECONDS);
    }

    private long getInitialDelayToEndOfTheDay() {
        ZonedDateTime tomorrowMidnight = ZonedDateTime.now(ZoneOffset.UTC)
                .plusDays(1)
                .truncatedTo(ChronoUnit.DAYS);
        return ChronoUnit.SECONDS.between(ZonedDateTime.now(ZoneOffset.UTC), tomorrowMidnight);
    }
}
