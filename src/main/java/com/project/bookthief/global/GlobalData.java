package com.project.bookthief.global;

import java.util.ArrayList;
import java.util.List;

import com.project.bookthief.entity.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart= new ArrayList<Product>();
	}
}
