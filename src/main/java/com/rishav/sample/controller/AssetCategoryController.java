package com.rishav.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishav.sample.model.AssetCategory;
import com.rishav.sample.services.AssetCategoryService;

@RestController
@RequestMapping("/asset/categories")
public class AssetCategoryController {
	
	@Autowired
	private AssetCategoryService assetService;
	
	//To Get Asset Category List
	@GetMapping("/all")
	public ResponseEntity<List<AssetCategory>> getAssetCategoryList() {
		return new ResponseEntity<>(assetService.getCategories(), HttpStatus.OK);
	}
	
	//To Update Asset Category
	@PatchMapping("/update/{{id}}")
	public ResponseEntity<AssetCategory> updateAsset(@RequestBody AssetCategory model, @RequestParam Long id) {
		AssetCategory updatedCategory = assetService.updateCategories(id, model);
		if(updatedCategory==null) return new ResponseEntity<>(null, HttpStatus.OK);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
}
