package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Puesto;


@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Long>{
	
	public Puesto getPuestoByid(int id);
}
