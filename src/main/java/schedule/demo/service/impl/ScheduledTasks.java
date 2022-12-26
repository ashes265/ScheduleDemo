package schedule.demo.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import schedule.demo.model.Calendar;
import schedule.demo.model.CheckNoti;
import schedule.demo.repository.CalendarRepository;
import schedule.demo.repository.CheckNotiRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private CheckNotiRepository checkNotiRepository;
    @Autowired
    private JavaMailSender emailSender;
    private static final SimpleDateFormat fm=new SimpleDateFormat("dd-MM-yyyy");
    private static LocalTime localTime;

//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);


//    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() throws ParseException {
        List<Calendar> calendars=calendarRepository.findAll();
        if(calendars!=null){
            for (int i = 0; i < calendars.size(); i++) {
                //get now time
                localTime=LocalTime.now();
                //get database time
                LocalTime databaseTime=LocalTime.parse(calendars.get(i).getStarttime());
                //get now date
                Date dateNow=fm.parse(fm.format(new Date()));
                //get databasedate
                Date eventStartAt=fm.parse(fm.format(calendars.get(i).getUpdateddate()));
                //check equal day and "local time" less than "database time" 15 minutes and "noti active"
                CheckNoti noti=checkNotiRepository.findCheckNotiById(calendars.get(i).getId());
                if (dateNow.equals(eventStartAt) && (localTime.isAfter(databaseTime.minus(15, ChronoUnit.MINUTES))) && noti.getNoti_status()) {
//                    emailSender.send(sendEmail(calendars.get(i).getReceiver(),calendars.get(i).getTitle(),calendars.get(i).getFullmsg()));
                    //after send, set noti to false
//                    noti.setNoti_status(false);
                }
            }
        }
    }

    //send email
    public SimpleMailMessage sendEmail(String receiver, String title,String fullMsg){
        SimpleMailMessage message=new SimpleMailMessage();
        String []listEmails=receiver.split(",");
        for (int i = 0; i < listEmails.length; i++) {
            message.setFrom("VNDIRECT");
            //Send to
            message.setTo(listEmails[i]);
            //Subject email
            message.setSubject(title);
            //Text in email
            message.setText(fullMsg);
            //execute send
            emailSender.send(message);
        }
        return message;
    }
    public void scheduleTaskWithFixedDelay() {
    }

    public void scheduleTaskWithInitialDelay() {
    }

    public void scheduleTaskWithCronExpression() {
    }

}
