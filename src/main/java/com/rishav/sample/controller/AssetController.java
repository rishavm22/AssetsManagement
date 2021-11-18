package com.rishav.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishav.sample.model.Asset;
import com.rishav.sample.services.AssetService;

// To access Asset
@RestController
@RequestMapping("/asset")
public class AssetController {
	
	@Autowired
	private AssetService assetService;

	//To Get Asset List
	@GetMapping("/all")
	public ResponseEntity<List<Asset>> getAssetList() {
		return new ResponseEntity<>(assetService.getAssets() , HttpStatus.OK);
	}
	
	
	//To Get Asset By Name
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Asset>> getAssetByName(@RequestParam String name) {
		return new ResponseEntity<>(assetService.getAssetsByName(name), HttpStatus.OK);
	}
	
	//To Update Asset
	@PatchMapping("/update/{id}")
	public ResponseEntity<Asset> updateAsset(@RequestBody Asset model, @RequestParam Long id) {
		
		return new ResponseEntity<>(assetService.updateAsset(id, model) , HttpStatus.OK);
	}
	
	//To Assign Asset
	@PatchMapping("/assign/{asset_id}/{emp_id}")
	public ResponseEntity<Asset> assignAsset(@RequestParam Long asset_id, @RequestParam Long emp_id) {
		Asset result = assetService.assignEmployee(asset_id, emp_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	//To Recover Asset
	@PatchMapping("/assign/{asset_id}")
	public ResponseEntity<Asset> assignAsset(@RequestParam Long asset_id) {
		Asset result = assetService.recoverAsset(asset_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	//To Delete Asset
	@DeleteMapping("/delete/{{id}}")
	public ResponseEntity<Boolean> recoverAsset(@RequestParam Long id) {
		Boolean result  = assetService.deleteAsset(id);
		if(result==null) return new ResponseEntity<>(false, HttpStatus.OK);
		else return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
