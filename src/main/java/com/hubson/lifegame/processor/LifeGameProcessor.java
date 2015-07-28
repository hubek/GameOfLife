package com.hubson.lifegame.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LifeGameProcessor implements Processor<Structure> {
	private final static Logger log = LoggerFactory.getLogger(LifeGameProcessor.class);

	@Override
	public Structure execute(Structure structure) {
		Structure calculatedStructure = updateElementsAndNeighbors(structure);
		return calculatedStructure;
	}

	private Structure updateElementsAndNeighbors(Structure structure) {
		List<Element> newElements = new ArrayList<>();

		int edgeLength = structure.getEdgeLength();
		boolean[][] structureArray = new boolean[edgeLength][edgeLength];
		boolean[][] revivedElements = new boolean[edgeLength][edgeLength];

		structure.getElements().forEach(e -> markElementOnArray(e.getX(), e.getY(), structureArray));

		structure.getElements().forEach(e -> {
			if (shouldSurvive(e, edgeLength, structureArray)) {
				newElements.add(e);
			}
			reviveNeighbors(e, structureArray, revivedElements, newElements);
		});

		Structure newStructure = new Structure();
		newStructure.setEdgeLength(edgeLength);
		newStructure.setElements(newElements);
		return newStructure;
	}

	private void reviveNeighbors(Element element, boolean[][] structureArray, boolean[][] revivedElements,
	        List<Element> newElements) {
		int x = element.getX();
		int y = element.getY();
		int edgeLength = structureArray.length;
		assert x >= 0 && x < edgeLength && y >= 0 && y < edgeLength;

		reviveElement(x - 1, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x - 1, y, structureArray, revivedElements, newElements);
		reviveElement(x - 1, y + 1, structureArray, revivedElements, newElements);
		reviveElement(x, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x, y + 1, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y + 1, structureArray, revivedElements, newElements);
	}

	private void reviveElement(int x, int y, boolean[][] structureArray, boolean[][] revivedElements,
	        List<Element> newElements) {
		int edgeLength = structureArray.length;
		if (x >= 0 && x < edgeLength && y >= 0 && y < edgeLength) {
			if (!revivedElements[x][y]) {
				if (3 == neighborsNumber(x, y, structureArray)) {
					revivedElements[x][y] = true;
					newElements.add(new Element(x, y));
				}
			}
		}
	}

	private void markElementOnArray(int x, int y, boolean[][] structureArray) {
		try {
			structureArray[x][y] = true;
		} catch (IndexOutOfBoundsException exception) {
			log.error("Improper element on list.", exception.getMessage());
		}
	}

	private boolean shouldSurvive(Element element, int edgeLength, boolean[][] structureArray) {
		int n = neighborsNumber(element.getX(), element.getY(), structureArray);
		if (2 == n || 3 == n) {
			return true;
		}
		return false;
	}

	private int neighborsNumber(int x, int y, boolean[][] structureArray) {

		int edgeLength = structureArray.length;
		assert x >= 0 && x < edgeLength && y >= 0 && y < edgeLength;

		int neighborsNumber = 0;

		if (x > 0) {
			if (structureArray[x - 1][y]) {
				neighborsNumber++;
			}
			if (y > 0 && structureArray[x - 1][y - 1]) {
				neighborsNumber++;
			}
			if (y < edgeLength - 1 && structureArray[x - 1][y + 1]) {
				neighborsNumber++;
			}
		}
		if (y > 0) {
			if (structureArray[x][y - 1]) {
				neighborsNumber++;
			}
		}
		if (y < edgeLength - 1) {
			if (structureArray[x][y + 1]) {
				neighborsNumber++;
			}
		}
		if (x < edgeLength - 1) {
			if (structureArray[x + 1][y]) {
				neighborsNumber++;
			}
			if (y > 0 && structureArray[x + 1][y - 1]) {
				neighborsNumber++;
			}
			if (y < edgeLength - 1 && structureArray[x + 1][y + 1]) {
				neighborsNumber++;
			}
		}
		return neighborsNumber;
	}
}
