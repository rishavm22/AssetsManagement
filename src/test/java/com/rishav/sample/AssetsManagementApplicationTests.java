package com.rishav.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rishav.sample.model.Asset;
import com.rishav.sample.model.AssetCategory;
import com.rishav.sample.model.Employee;
import com.rishav.sample.repo.AssetCategoryRepository;
import com.rishav.sample.repo.AssetRepository;
import com.rishav.sample.repo.EmployeeRepository;
import com.rishav.sample.services.AssetCategoryService;
import com.rishav.sample.services.AssetService;

@SpringBootTest
class AssetsManagementApplicationTests {

	@Autowired
	AssetService assetService;
	@Autowired
	AssetCategoryService categoryService;
	
	@MockBean
	AssetRepository assetRepo;
	@MockBean
	EmployeeRepository empRepo;
	@MockBean
	AssetCategoryRepository categoryRepo;
	
	public List<Asset> getSampleAssets() {
		List<Asset> sampleAssets = new ArrayList<>(5); 
		sampleAssets.addAll(Stream.of(
				new Asset(1L,"abc","Available", new Date(2020,03,05), "note", 1L, 1L),
				new Asset(2L,"abc","Available", new Date(2020,03,05), "note", 2L, 2L)
				).collect(Collectors.toList()));
		return sampleAssets;
	}
	
	public List<AssetCategory> getSampleAssetCategories() {
		List<AssetCategory> sampleAssetCategories = new ArrayList<>(5); 
		sampleAssetCategories.addAll(Stream.of(
				new AssetCategory(1L,"Name1","Desc"),
				new AssetCategory(1L,"Name2","Desc"),
				new AssetCategory(1L,"Name3","Desc"),
				new AssetCategory(1L,"Name4","Desc")
				).collect(Collectors.toList()));
		return sampleAssetCategories;
	}
	
	@Test
	public void getAssetCategoryList() {
		when(categoryRepo.findAll()).thenReturn(getSampleAssetCategories());
		assertEquals(4, categoryService.getCategories().size());
	}
	
	@Test
	public void updateAssetCategory() {
		AssetCategory newCategory = new AssetCategory(1L,"NameNew","Desc");
		Long category_id =1L;
		AssetCategory testCategory = new AssetCategory(1L,"Name1","Desc");
		doReturn(Optional.of(testCategory)).when(categoryRepo).findById(category_id);
		AssetCategory updatedCategory = categoryService.updateCategories(category_id,newCategory);
		if(updatedCategory!=null) {
			assertEquals(newCategory, updatedCategory);
		}
	}
	
	@Test
	public void getAssetListTest() {
		when(assetRepo.findAll())
		.thenReturn(getSampleAssets());
		assertEquals(getSampleAssets(), assetService.getAssets());
	}
	
	@Test
	public void getAssetByNameTest() {
		String name = "abc";
		when(assetRepo.findByName(name))
		.thenReturn(getSampleAssets());
		assertEquals(2, assetService.getAssetsByName(name).size());
		for(int i=0; i<assetService.getAssetsByName(name).size(); i++) assertEquals(name,assetService.getAssetsByName(name).get(i).getName());
	}
	
	@Test
	public void assignEmployeeTest() {
		Long emp_id = 1L; Long asset_id = 1L;
		Employee testEmployee = new Employee(1L,"Full Name1","Owner");
		doReturn(Optional.of(testEmployee)).when(empRepo).findById(emp_id);
		Asset testAsset = new Asset(1L,"abc","Available", new Date(2020,03,05), "note", null, 1L);
		doReturn(Optional.of(testAsset)).when(assetRepo).findById(asset_id);
		Asset serviceAsset = assetService.assignEmployee(asset_id,emp_id);
		if(serviceAsset!=null) {
			assertEquals(emp_id, serviceAsset.getAssignedTo());
			assertEquals("Assigned", serviceAsset.getStatus());
		}
	}
	
	@Test
	public void updateAsset() {
		Asset newAsset = new Asset(1L,"abcNew","Available", new Date(2020,03,05), "note", 1L, 1L);
		Long asset_id =1L;
		Asset testAsset = new Asset(1L,"abc","Available", new Date(2020,03,05), "note", 1L, 1L);
		doReturn(Optional.of(testAsset)).when(assetRepo).findById(asset_id);
		Asset updatedAsset = assetService.updateAsset(asset_id,newAsset);
		if(updatedAsset!=null) {
			assertEquals(newAsset, updatedAsset);
		}
	}
	
	@Test
	public void recoverAssetTest() {
		Long asset_id = 1L;
		Asset testAsset = new Asset(1L,"abc","Available", new Date(2020,03,05), "note", 1L, 1L);
		doReturn(Optional.of(testAsset)).when(assetRepo).findById(asset_id);
		Asset serviceAsset = assetService.recoverAsset(asset_id);
		if(serviceAsset!=null) {
			assertEquals("Recovered", serviceAsset.getStatus());
			assertEquals(null, serviceAsset.getAssignedTo());
		}
	}
	
	@Test
	public void deleteAsset() {
		Long asset_id =1L;
		assetService.deleteAsset(asset_id);
		verify(assetRepo,times(1)).getById(asset_id);
	}

}
