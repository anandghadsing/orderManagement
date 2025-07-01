package com.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee 
{
	    @Id
	    @GeneratedValue
	    private Long id;

	    @Column(nullable = false)
	    private String name;

	    @Column(unique = true)
	    private String email;

	    private String designation;

	    @ManyToOne
	    private Manager manager;

	    @OneToOne(cascade = CascadeType.ALL)
	    private Address address;

	    @ManyToOne
	    private Company company;
}
