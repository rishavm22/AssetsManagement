package com.rishav.sample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String status;
	@Column(name="purchase_date")
	private Date purchaseDate;
	@Column(name="condition_note")
	private String conditionNote;
	
	@Column(name="employee_id")
	private Long assignedTo;
	
	@JoinColumn(name="category_id")
	private Long category_id;
	
	public Asset(String name, String status, Date purchaseDate, String conditionNote, Long assignedTo,
			Long category_id) {
		super();
		this.name = name;
		this.status = status;
		this.purchaseDate = purchaseDate;
		this.conditionNote = conditionNote;
		this.assignedTo = assignedTo;
		this.category_id = category_id;
	}
	
	//To get unique id
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Asset category = (Asset) obj;
			if (id == null) {
				if (category.id != null)
					return false;
			} else if (!id.equals(category.id))
				return false;
			return true;
		}
	
}
