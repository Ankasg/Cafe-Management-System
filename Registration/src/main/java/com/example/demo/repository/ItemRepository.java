package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Items;
import com.example.demo.model.User;

@Repository
public interface ItemRepository extends JpaRepository<Items,Integer>{
	

}
