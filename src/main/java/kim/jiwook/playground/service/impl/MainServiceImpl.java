package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.mapper.MainMapper;
import kim.jiwook.playground.service.MainService;
import kim.jiwook.playground.vo.MainVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {
    private final MainMapper mainMapper;

    @Override
    public int insertMain(MainVO vo) {
        return mainMapper.insertMain(vo);
    }
}
