package jiwook.kim.playground.service.impl;

import jiwook.kim.playground.repository.BoardRepo;
import jiwook.kim.playground.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
}
