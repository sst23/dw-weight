package org.perlhacker.dwweight.api;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {
	@NotNull
	@JsonProperty
	private int date;

	@NotNull
	@JsonProperty
	private float weight;

	public Entry() {
	}

	public Entry(int date, float weight) {
		this.date = date;
		this.weight = weight;
	}

	public int getDate() {
		return date;
	}

	public float getWeight() {
		return weight;
	}
}