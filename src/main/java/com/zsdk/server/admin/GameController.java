package com.zsdk.server.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/games")
public class GameController {
	@RequestMapping(path = "/showGames", method = RequestMethod.GET)
	public String showByDate() {
		return "games";
	}
}