package kim.jiwook.playground.mapper;

import kim.jiwook.playground.vo.MainVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    int insertMain(MainVO vo);
}
