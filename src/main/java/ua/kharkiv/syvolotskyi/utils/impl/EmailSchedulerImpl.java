package ua.kharkiv.syvolotskyi.utils.impl;

import ua.kharkiv.syvolotskyi.utils.EmailScheduler;
import ua.kharkiv.syvolotskyi.utils.EmailSender;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduledExecutorService starts EmailSender with a one day delay
 */
public class EmailSchedulerImpl implements EmailScheduler {

    private static final long PERIOD = ChronoUnit.DAYS.getDuration().getSeconds();
    private ScheduledExecutorService scheduledExecutorService;
    private final EmailSender emailSender;

    public EmailSchedulerImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void startScheduler() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(emailSender::notifyUsersAboutFeedback,
                getInitialDelayToEndOfTheDay(), PERIOD, TimeUnit.SECONDS);
    }

    @Override
    public void stopScheduler() {
        scheduledExecutorService.shutdown();
    }

    private long getInitialDelayToEndOfTheDay() {
        ZonedDateTime tomorrowMidnight = ZonedDateTime.now(ZoneOffset.UTC)
                .plusDays(1)
                .truncatedTo(ChronoUnit.DAYS);
        return ChronoUnit.SECONDS
                .between(ZonedDateTime.now(ZoneOffset.UTC), tomorrowMidnight);
    }
}
