package com.mems.service.bl.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mems.domain.quiz.Subject;
import com.mems.repository.SubjectRepository;
import com.mems.service.bl.interfaces.SubjectService;
@PreAuthorize("Admin")
@Transactional
@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectDao;

	@Override
	public List<Subject> searchByKeyword(String word) {
		// TODO Auto-generated method stub
		return subjectDao.searchByKeyword(word);
	}

	@Override
	public Subject findById(Long Id) {
		// TODO Auto-generated method stub
		return subjectDao.findById(Id);
	}

	@Override
	public List<Subject> findByMission(String mission) {
		// TODO Auto-generated method stub
		return subjectDao.findByMission(mission);
	}
	
	@Override
	public Subject save(Subject Subject) {
		return subjectDao.save(Subject);
	}

	@Override
	public void delete(Subject Subject) {
		// TODO Auto-generated method stub
		subjectDao.delete(Subject);
	}

	@Override
	public List<Subject> findAll() {
		// TODO Auto-generated method stub
		return subjectDao.findAll();
	}

	
	
	

} // The End of Class;