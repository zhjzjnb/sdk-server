package com.zsdk.server.admin;

import com.zsdk.server.bean.Data;
import com.zsdk.server.cache.CacheManager;
import com.zsdk.server.model.GameInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
	public Data<GameInfo> getAllGames(HttpServletRequest request) {

		Data<GameInfo> data = new
				Data<GameInfo>();
		List<GameInfo> list = CacheManager.getInstance().getGameList();
		data.setTotal(list.size());
		data.setRows(list);
		return data;
	}
}