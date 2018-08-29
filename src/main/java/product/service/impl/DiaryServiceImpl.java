package product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.dao.DiaryDao;
import product.model.Diary;
import product.service.DiaryService;

import java.util.List;

@Service("product.service.impl.DiaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryDao diaryDao;

    public List<Diary> selectDiaryList(){
        return diaryDao.selectDiaryList();
    }
}
