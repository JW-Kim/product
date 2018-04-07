package product.auth.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import product.auth.model.Auth;
import product.auth.model.AuthGetParam;

import java.util.List;

@Repository(value = "product.auth.dao.AuthDao")
public class AuthDao {

    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    public List<Auth> selectAuth(AuthGetParam authGetParam){
        try{
            System.out.println("SqlSession ===> " + sqlSession);
            String test = sqlSession.selectOne("mapperExample."+"getMemberSelect2");
        } catch(Exception e) {
        }

        System.out.println("dao");
        return sqlSession.selectList("product.auth.dao.AuthDao.selectAuth");
    }
}
