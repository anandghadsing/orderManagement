package com.main.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manager 
{
	    @Id
	    @GeneratedValue
	    private Long id;

	    private String name;
	    private String department;

	    @OneToMany(mappedBy = "manager")
	    private List<Employee> employees;
}
