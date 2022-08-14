package com.ekremfikretbardak.teachcareertodoapp.data.repository;

import com.ekremfikretbardak.teachcareertodoapp.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ITaskRepository extends JpaRepository<TaskEntity,Long> {
}
