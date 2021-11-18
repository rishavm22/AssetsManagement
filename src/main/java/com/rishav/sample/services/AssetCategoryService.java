package com.rishav.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishav.sample.model.AssetCategory;
import com.rishav.sample.repo.AssetCategoryRepository;

@Service
public class AssetCategoryService {
	
	@Autowired
	private AssetCategoryRepository assetCategoryRepo;

	public List<AssetCategory> getCategories() {
		return assetCategoryRepo.findAll();
	}

	public AssetCategory updateCategories(Long id, AssetCategory model) {
		AssetCategory nullAssetCategory= null;
		AssetCategory assetCategory = assetCategoryRepo.findById(id).orElse(nullAssetCategory);
		if(assetCategory==null) return nullAssetCategory;
		model.setId(id);
		assetCategory = model;
		return assetCategoryRepo.save(assetCategory);
	}

}
