package com.springboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Coin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column
    private String code;

    @Column
    private String name;
    
    @Column
    private Integer symbol;
    
    @Column
    private Integer rate;
    
    @Column
    private Integer description;
    
    @Column
    private Integer rate_float;
}
