package com.jiit.vikram.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jiit.vikram.dto.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByDepartmentName(String departmentName);
}
