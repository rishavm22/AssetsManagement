package com.rishav.sample.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rishav.sample.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	List<Asset> findByName(String name);

}
