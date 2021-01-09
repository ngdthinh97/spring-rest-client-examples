package guru.springframework.api.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayExample {

	List<Example> data = new ArrayList<Example>();

	public List<Example> getData() {
		return new ArrayList<Example>();
	}

	public void setData(List<Example> data) {
		this.data = data;
	}

}
