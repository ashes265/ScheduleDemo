package schedule.demo.controller;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import schedule.demo.model.Calendar;
import schedule.demo.model.CheckNoti;
import schedule.demo.model.SendNotice;
import schedule.demo.repository.CalendarRepository;
import schedule.demo.repository.CheckNotiRepository;
import schedule.demo.service.impl.CalendarSpecification;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("calendars")
public class CalendarController {

    private JavaMailSender emailSender;
    private CalendarRepository calendarRepository;
    private CheckNotiRepository checkNotiRepository;

    public CalendarController(CalendarRepository calendarRepository,
                              JavaMailSender sender,
                              CheckNotiRepository checkNotiRepository
    ) {
        this.calendarRepository = calendarRepository;
        this.checkNotiRepository = checkNotiRepository;
        this.emailSender = sender;
    }


//    @GetMapping
//    public String list(ModelMap model, @RequestParam Optional<String> message,
//                       @PageableDefault(size = 10, sort = "createddate", direction = Sort.Direction.ASC) Pageable pageable,
//                       HttpSession session) {
//        Page<Calendar> pages = calendarRepository.findAll(pageable);
//        if (message.isPresent()) {
//            model.addAttribute("message", message.get());
//        }
//        List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
//        if (sortOrders.size() > 0) {
//            Sort.Order order = sortOrders.get(0);
//            model.addAttribute("sort", order.getProperty() + "," + (order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
//        } else {
//            model.addAttribute("sort", "createddate");
//        }
//
//        model.addAttribute("listCalendar", pages);
//        return "list";
//    }


    @GetMapping
    @ResponseBody
    public Page<Calendar> list(ModelMap model, @RequestParam Optional<String> message,
                               @PageableDefault(size = 10, sort = "createddate", direction = Sort.Direction.ASC) Pageable pageable,
                               HttpSession session) {
        Page<Calendar> pages = calendarRepository.findAll(pageable);
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
        }
        List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0) {
            Sort.Order order = sortOrders.get(0);
            model.addAttribute("sort", order.getProperty() + "," + (order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
        } else {
            model.addAttribute("sort", "createddate");
        }

        model.addAttribute("listCalendar", pages);
        return pages;
    }

    @GetMapping("listTest")
    public String listFilter(ModelMap model) {
        Specification specification = Specification.where(CalendarSpecification.hasId(1L)).and(CalendarSpecification.hasType("test status"));
        List<Calendar> list = calendarRepository.findAll(specification);
        model.addAttribute("listCalendar", list);
        return "listTest";
    }

    @PostMapping("/dlink/v1/push")
    @ResponseBody
    public SendNotice sendNoti(@RequestBody SendNotice sendNotice) {
        sendNotice.setCreationDate(new Date());
        return sendNotice;
    }

    @GetMapping("create")
    public String createOrUpdate(ModelMap model) {
        model.addAttribute("calendar", new Calendar());
        return "create";
    }

    @PostMapping("save")
    public String save(RedirectAttributes attributes,
                       Calendar calendar,
                       BindingResult result,
                       ModelMap model,
                       @RequestParam(required = false) Integer NotiOrNot) throws ParseException {
        if (result.hasErrors()) {
            model.addAttribute("calendar", calendar);
            return "create";
        }
        Date date = new Date();
        calendar.setCreateddate(date);
        if (NotiOrNot == 1) {
            CheckNoti noti = new CheckNoti();
            noti.setNoti_status(true);
            checkNotiRepository.save(noti);
        } else {
            CheckNoti noti = new CheckNoti();
            noti.setNoti_status(false);
            checkNotiRepository.save(noti);
        }

        //execute email send
//        SimpleMailMessage message = new SimpleMailMessage();
//        String[] listEmails = calendar.getReceiver().split(",");
//        for (int i = 0; i < listEmails.length; i++) {
//            message.setFrom("VNDIRECT");
//            //Send to
//            message.setTo(listEmails[i]);
//            //Subject email
//            message.setSubject(calendar.getTitle());
//            //Text in email
//            message.setText("The event will start in 15 minutes, be ready for the meeting.\n" + calendar.getFullmsg());
//            //execute send
//            emailSender.send(message);
//        }
        calendarRepository.save(calendar);
        attributes.addAttribute("message", "Add Successfull");
        return "redirect:/calendars";
    }

    //    @GetMapping("delete/{id}")
    @DeleteMapping(value = "/delete/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Calendar calendar = calendarRepository.getCalendarById(id);
        if (calendar == null) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("status","Not ok");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.NOT_FOUND);
        }
        calendarRepository.delete(calendar);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status","OK");
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping("update/{id}")
    public String createOrUpdate(ModelMap model,
                                 @PathVariable(name = "id", required = false) Long id) {
        Calendar calendar = calendarRepository.getById(id);
        model.addAttribute("calendar", calendar);
        return "update";
    }

}
