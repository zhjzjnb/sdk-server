package com.zsdk.server.admin;

import com.zsdk.server.bean.Data;
import com.zsdk.server.model.GameInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
	@RequestMapping(path = "/showGames", method = RequestMethod.GET)
	public String showByDate() {
		return "games";
	}


	@RequestMapping(path = "/getAllGames", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Data<GameInfo> getAllGames() {

		Data<GameInfo> data = new
				Data<GameInfo>();
		data.setTotal(1);
		GameInfo g = new GameInfo();
		g.setAppId(1);
		List<GameInfo> l = new ArrayList<GameInfo>();
		l.add(g);
		data.setRows(l);
		return data;
	}
}