package com.banks.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banks.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
