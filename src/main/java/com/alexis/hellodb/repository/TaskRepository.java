package com.alexis.hellodb.repository;

import com.alexis.hellodb.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ce6915gp on 4/5/2018.
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    List<Task> findAllByOrderByUrgentDesc();

}
