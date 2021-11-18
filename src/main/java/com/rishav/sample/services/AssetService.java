package com.rishav.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishav.sample.model.Asset;
import com.rishav.sample.model.Employee;
import com.rishav.sample.repo.AssetRepository;
import com.rishav.sample.repo.EmployeeRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepo;
	@Autowired
	private EmployeeRepository employeeRepo;
	
	//To Get Asset List
	public List<Asset> getAssets() {
		// TODO Auto-generated method stub
		return assetRepo.findAll();
	}

	//To Get Asset By Name
	public List<Asset> getAssetsByName(String name) {
		// TODO Auto-generated method stub
		return assetRepo.findByName(name);
	}
	
	public Asset saveAsset(Long id, Asset model) {
		Asset asset = assetRepo.getById(id);
		model.setId(id);
		asset = model;
		return assetRepo.save(asset);
	}


	public Asset assignEmployee(Long asset_id, Long emp_id) {
		Asset nullAsset = null;
		Employee employee = employeeRepo.findById(emp_id).orElse(null);
		if(employee==null) return nullAsset;
		Asset asset = assetRepo.findById(asset_id).orElse(null);
		if(asset==null) return nullAsset;
		asset.setStatus("Assigned");
		asset.setAssignedTo(employee.getId());
		return assetRepo.save(asset);
	}
	
	public Asset updateAsset(Long asset_id, Asset newAsset) {
		Asset nullAsset = null;
		Asset asset = assetRepo.findById(asset_id).orElse(nullAsset);
		if(asset == null) return nullAsset;
		newAsset.setId(asset.getId());
		return assetRepo.save(newAsset);
	}


	public Boolean deleteAsset(Long id) {
		Asset asset = assetRepo.getById(id);
		if(asset==null) return false;
		if(asset.getStatus().equals("Assigned")) return false;
		assetRepo.deleteById(id);
		return true;
	}
	
	public Asset addAsset(Asset newAsset) {		
		return assetRepo.save(newAsset);
	}


	public Asset recoverAsset(Long asset_id) {
		Asset nullAsset = null;
		Asset asset = assetRepo.findById(asset_id).orElse(nullAsset);
		if(asset == null) return nullAsset;
		asset.setStatus("Recovered");
		asset.setAssignedTo(null);
		return assetRepo.save(asset);
	}

}
