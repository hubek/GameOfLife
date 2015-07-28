package com.hubson.lifegame.processor;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LifeGameProcessorTest {

	@Test
	public void checkStructureTreeDots() {
		List<Element> startElements = new ArrayList<Element>();
		startElements.add(new Element(8, 9));
		startElements.add(new Element(9, 9));
		startElements.add(new Element(10, 9));
		Structure startStructure = generateStructure(20, startElements);

		List<Element> expectedElements = new ArrayList<Element>();
		expectedElements.add(new Element(9, 8));
		expectedElements.add(new Element(9, 9));
		expectedElements.add(new Element(9, 10));
		Structure expectedStructure = generateStructure(20, expectedElements);

		LifeGameProcessor lifeGameProcessor = new LifeGameProcessor();

		// first iteration
		Structure actualStructure = lifeGameProcessor.execute(startStructure);
		assertTrue(expectedStructure.equals(actualStructure));

		// second iteration same as starting structure
		// (this kind of structure is looped)
		Structure actual2Structure = lifeGameProcessor.execute(actualStructure);
		assertTrue(startStructure.equals(actual2Structure));
	}

	private Structure generateStructure(int edgeLength, List<Element> elements) {
		Structure structure = new Structure();
		structure.setEdgeLength(edgeLength);
		structure.setElements(elements);
		return structure;
	}
}
