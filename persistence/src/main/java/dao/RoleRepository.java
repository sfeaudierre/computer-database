package dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Set<Role> findByName(String name);   
}