package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.MainVO;

import java.io.IOException;
import java.sql.SQLException;

public interface MainService {
    int insertMain(MainVO vo) throws IOException, SQLException;
}
