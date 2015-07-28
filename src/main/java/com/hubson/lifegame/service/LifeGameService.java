package com.hubson.lifegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubson.lifegame.processor.LifeGameProcessor;
import com.hubson.lifegame.processor.Structure;

@Service
public class LifeGameService {

	@Autowired
	LifeGameProcessor lifeGameProcessor;

	public Structure calculateSequentStructure(Structure structure) {
		return lifeGameProcessor.execute(structure);
	}
}
