package com.hubson.lifegame.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hubson.lifegame.entity.BenchmarkEntry;
import com.hubson.lifegame.processor.Structure;
import com.hubson.lifegame.service.BenchmarkService;
import com.hubson.lifegame.service.LifeGameService;

@RestController
@RequestMapping(value = "/rest/lifegame")
public class LifeGameController {
	private final static Logger log = LoggerFactory.getLogger(LifeGameController.class);

	@Autowired
	private LifeGameService lifeGameService;

	@Autowired
	private BenchmarkService benchmarkService;

	@ResponseBody
	@RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Structure calculate(@RequestBody Structure structure) {
		List<BenchmarkEntry> benchmarkList = benchmarkService.findAll();
		log.info("NUMBER OF BENCHMARK ENTRIES: " + benchmarkList.size());

		long startTime = System.nanoTime();
		Structure calculatedStructure = lifeGameService.calculateSequentStructure(structure);
		long duration = System.nanoTime() - startTime;
		benchmarkService.saveBenchmarkEntry(calculatedStructure.getElements().size(), duration);

		return calculatedStructure;
	}
}
