package com.demo.demo.repository;

import com.demo.demo.entity.Entry;
import com.demo.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    
}
