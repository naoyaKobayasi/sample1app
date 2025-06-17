package com.example.sample1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sample1app.model.TestTable;
import java.util.Optional;

@Repository
public interface TestTableRepository extends JpaRepository<TestTable, Integer> {

    public Optional<TestTable> findById(int title);

}
