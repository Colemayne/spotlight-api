package com.coleman.documenter.service;

import com.coleman.documenter.domain.test.Test;
import com.coleman.documenter.domain.test.TestItem;
import com.coleman.documenter.repository.TestItemRepository;
import com.coleman.documenter.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestItemRepository testItemRepository;

    @Transactional(noRollbackFor = Exception.class)
    public boolean deleteById(int id) {
        try {
            Test test = testRepository.findById(id).get();
            testItemRepository.deleteAll(test.getTestItems());
            testRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
