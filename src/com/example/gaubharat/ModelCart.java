package com.example.gaubharat;

import java.util.ArrayList;

import com.example.gaubharat.ModelProducts;

public class ModelCart{
	
   private  ArrayList<ModelProducts> cartProducts = new ArrayList<ModelProducts>();
	

   public ModelProducts getProducts(int pPosition) {
		
		return cartProducts.get(pPosition);
	}
	
	public void setProducts(ModelProducts Products) {
	   
		cartProducts.add(Products);
		
	}
	
	public int getCartSize() {
		   
		return cartProducts.size();
		
	}
 
	public boolean checkProductInCart(ModelProducts aProduct) {
		   
		return cartProducts.contains(aProduct);
		
	}

}
