package com.hubson.lifegame.processor;

import java.util.List;

public class Structure {

	private int edgeLength;
	private List<Element> elements;

	public int getEdgeLength() {
		return edgeLength;
	}

	public void setEdgeLength(int edgeLength) {
		this.edgeLength = edgeLength;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Structure))
			return false;
		if (obj == this)
			return true;
		Structure structure = (Structure) obj;

		return this.edgeLength == structure.getEdgeLength() && this.elements.containsAll(structure.getElements());
	}

}
