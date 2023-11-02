package kim.jiwook.playground.mapper;

import kim.jiwook.playground.vo.MainVO;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;
import java.sql.SQLException;

@Mapper
public interface MainMapper {
    int insertMain(MainVO vo) throws IOException, SQLException;
}
