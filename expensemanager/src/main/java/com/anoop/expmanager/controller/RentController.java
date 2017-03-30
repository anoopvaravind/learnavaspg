package com.anoop.expmanager.controller;

import java.util.Date;
import java.util.List;

import com.anoop.expmanager.util.Notification;
import com.anoop.expmanager.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.RentService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/app/rent")
public class RentController {
    @Autowired
    private RentService rentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rent() throws Exception{
        return "rent";
    }

    @RequestMapping(value = "/renthistoryforuser", method = RequestMethod.GET)
    @ResponseBody
    public List<RentSheet> getRentHistoryForUser(HttpServletRequest request) throws Exception{
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        return rentService.getRentHistoryForUser(userSession.getUser().getId());
    }

    @RequestMapping(value = "/currentmonthpaid", method = RequestMethod.GET)
    public List<RentSheet> getCurrentMonthSheetDetails() throws Exception{
        return rentService.getCurrentMonthRentSheetDetails();
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<RentSheet> getRentSheetHistory() throws Exception{
        return rentService.getRentSheetHistory();
    }


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Notification saveRentPaid(@RequestBody RentSheet rentSheet) throws Exception{
        rentSheet.setRentPaidDate(new Date());
        rentSheet.setModifiedDate(new Date());
        rentService.saveRentSheet(rentSheet);
        return new Notification(true, false, "Rent paid Successfully", null);
    }


}
