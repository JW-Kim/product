package product.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import product.model.Diary;
import java.util.List;

@Repository(value = "DiaryDao")
public class DiaryDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Diary> selectDiaryList(){
        return sqlSession.selectList("selectDiaryList");
    }
}
