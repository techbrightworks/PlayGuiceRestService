package org.srinivas.siteworks.controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srinivas.siteworks.changeservice.ChangeService;
import org.srinivas.siteworks.data.PropertiesReadWriter;
import org.srinivas.siteworks.denomination.Coin;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostController extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Inject
	PropertiesReadWriter propertiesReadWriter;

	@Inject
	ChangeService changeServiceImpl;

	public Result handleGetOptimalCalculation(Integer pence) {
		logger.info("ChangeController:handleGetOptimalCalculation Method");
		List<Coin> coins = new ArrayList<Coin>();
		try {
			propertiesReadWriter.readInventoryData();
			coins = changeServiceImpl.getOptimalChangeFor(pence).stream().collect(Collectors.toList());
		} catch (Exception e) {
			logger.info("Error:" + e.getMessage());
			return ok(Json.toJson(coins));
		}
		return ok(Json.toJson(coins));
	}

	public Result handleGetSupplyCalculation(Integer pence) {
		logger.info("ChangeController:handleGetSupplyCalculation Method");
		List<Coin> coins = new ArrayList<Coin>();
		try {
			propertiesReadWriter.readInventoryData();
			coins = changeServiceImpl.getChangeFor(pence).stream().collect(Collectors.toList());
		} catch (Exception e) {
			logger.info("Error:" + e.getMessage());
			return ok(Json.toJson(coins));
		}

		return ok(Json.toJson(coins));
	}

}
