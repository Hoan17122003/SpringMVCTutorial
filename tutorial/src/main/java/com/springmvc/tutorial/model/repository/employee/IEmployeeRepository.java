package com.springmvc.tutorial.model.repository.employee;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer>, IEmployeeRepositoryCustom {
    // Tìm kiếm Category theo tên có hỗ trợ phân trang
    Page<Employee> findByFullNameContaining(String searchValue, Pageable pageable);

    Employee findByEmail(String email);
}
