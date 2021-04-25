package com.example.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.video.model.Komentar;
import com.example.video.model.Video;
import com.example.video.repository.KomentarRepository;
import com.example.video.service.KomentarService;

@Service
public class JpaKomentarService implements KomentarService {
	
	@Autowired
	private KomentarRepository kometarRepository;
	
	
	@Override
	public Page<Komentar> findAll(int pageNo) {
	
		return kometarRepository.findAll(PageRequest.of(pageNo, 4));
	}

	@Override
	public Komentar save(Komentar komentar) {
		return kometarRepository.save(komentar);
	}

	@Override
	public Komentar delete(Long id) {
java.util.Optional<Komentar> por=  kometarRepository.findById(id);
		
		if (por.isPresent()){
			kometarRepository.deleteById(id);
		//porRep.delete(por.get());
			
			
			return por.get();
		}
		return null;
		
	}

	@Override
	public Komentar findOneId(Long id) {
	
		return kometarRepository.findOneById(id);
	}

}
