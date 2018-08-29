package product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import product.model.Diary;
import product.service.DiaryService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "diary")
public class DiaryController {

    @Resource(name = "product.service.impl.DiaryService")
    private DiaryService diaryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody Object selectDiaryList(){
        Object object = new Object();

        List<Diary> diaryList = diaryService.selectDiaryList();
        for(int i=0; i<diaryList.size(); i++){
            System.out.println(diaryList.get(i).toString());
        }

        return object;
    }
}
