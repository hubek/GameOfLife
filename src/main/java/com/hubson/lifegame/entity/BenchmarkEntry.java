package com.hubson.lifegame.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BenchmarkEntry implements Serializable {

	private static final long serialVersionUID = -1141795401171116138L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int elementsNumber;

	private long nanoTime;

	public BenchmarkEntry() {

	}

	public BenchmarkEntry(int elementsNumber, long nanoTime) {
		this.elementsNumber = elementsNumber;
		this.nanoTime = nanoTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElementsNumber() {
		return elementsNumber;
	}

	public void setElementsNumber(int elementsNumber) {
		this.elementsNumber = elementsNumber;
	}

	public long getNanoTime() {
		return nanoTime;
	}

	public void setNanoTime(long nanoTime) {
		this.nanoTime = nanoTime;
	}

	@Override
	public String toString() {
		return String.format("BenchmarkEntry[id=%d, elementsNumber='%d', nanoTime='%d']", id, elementsNumber, nanoTime);
	}

	@Override
	public final int hashCode() {
		return elementsNumber;
	}
}
