package com.rishav.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishav.sample.model.AssetCategory;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {

}
