package com.anoop.expmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {
	@Autowired
	private RentService rentService;

	@RequestMapping(value = "/currentmonthpaid", method = RequestMethod.GET)
	public List<RentSheet> getCurrentMonthSheetDetails() {
		return rentService.getCurrentMonthRentSheetDetails();
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<RentSheet> getRentSheetHistory() {
		return rentService.getRentSheetHistory();
	}

	@RequestMapping(value = "/historypermonthandyear", method = RequestMethod.GET)
	public List<RentSheet> getRentPaidHistoryPerMonthAndYear(@PathVariable("month") int month,
			@PathVariable("year") int year) {
		return rentService.getRentSheetHistoryPerMonthAndYear(0, 0);
	}

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public void saveRentPaid(@RequestBody RentSheet rentPaid) {
		rentService.saveRentSheet(rentPaid);
	}

}
