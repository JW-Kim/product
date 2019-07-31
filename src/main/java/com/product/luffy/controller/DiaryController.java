package com.product.luffy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.product.luffy.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Disease;
import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiaryController.class);

    @Resource(name = "com.product.luffy.service.impl.DiaryService")
    private DiaryService diaryService;

    @Resource(name = "com.product.luffy.service.impl.NoteService")
    NoteService noteService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Diary> selectDiaryList(@RequestParam("noteId") String noteId) {
        GridResponseObject<Diary> gridResponseObject = new GridResponseObject<Diary>();
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        LOGGER.debug(">>>>>>>>>>>>>> selectDiaryList 시작 {} : " + noteId);
        List<Diary> diaryList = diaryService.selectDiaryList(noteId);

        LOGGER.debug(">>>>>>>>>>>>>> selectDiaryList 끝 {} : " + diaryList);

        gridResponseObject.setData(diaryList);
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return gridResponseObject;
    }

    @RequestMapping(value = "/month", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Diary> selectMonthDiaryList(@RequestParam("diaryMonth") String diaryMonth,
                                                   @RequestParam("noteId") String noteId) {

        GridResponseObject<Diary> gridResponseObject = new GridResponseObject<Diary>();
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("diaryMonth", diaryMonth);
        paramMap.put("noteId", noteId);
        List<Diary> diaryList = diaryService.selectMonthDiaryList(paramMap);

        gridResponseObject.setData(diaryList);
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return gridResponseObject;
    }

    @RequestMapping(value = "/{diaryId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<Diary> selectDiary(@PathVariable("diaryId") String diaryId) {
        ResponseObject<Diary> responseObject = new ResponseObject<Diary>();
        LOGGER.debug(">>>>>>>>>> selectDiary 시작 : " + diaryId);
        if(diaryService.selectDiaryAuth(diaryId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Diary diary = diaryService.selectDiary(diaryId);
        responseObject.setData(diary);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(value = "/preInfo/{noteId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<Diary> selectPreDiaryInfo(@PathVariable("noteId") String noteId, @RequestParam("diaryDt") String diaryDt) {
        ResponseObject<Diary> responseObject = new ResponseObject<Diary>();
        LOGGER.debug(">>>>>>>>>> selectPreDiaryInfo 시작 : " + noteId);
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Diary diary = diaryService.selectPreDiaryInfo(noteId, diaryDt);
        responseObject.setData(diary);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertDiary(@RequestBody Map<String, Object> requestParams) {
        LOGGER.debug(">>>>>>>>>>> insertDiary 시작 :" + requestParams);
        ResponseObject<String> responseObject = new ResponseObject<String>();

        Diary diary = setDiary(null, requestParams);
        if(noteService.selectUserNote(diary.getNoteId())){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }
        int rtn = diaryService.insertDiary(diary);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/{diaryId}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> updateDiary(@PathVariable("diaryId") String diaryId,
                                       @RequestBody Map<String, Object> requestParams) {
        LOGGER.debug(">>>>>>>>>>> updateDiary 시작 :" + diaryId + " : " + requestParams);
        ResponseObject<String> responseObject = new ResponseObject<String>();
        if(diaryService.selectDiaryAuth(diaryId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Diary diary = setDiary(diaryId, requestParams);

        int rtn = diaryService.updateDiary(diary);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/{diaryId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseObject<String> deleteDiary(@PathVariable("diaryId") String diaryId) {
        LOGGER.debug(">>>>>>>>>>> deleteDiary 시작 :" + diaryId);
        ResponseObject<String> responseObject = new ResponseObject<String>();
        if(diaryService.selectDiaryAuth(diaryId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("diaryId", diaryId);

        int rtn = diaryService.deleteDiary(paramMap);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/diseaseMonth", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Disease> selectDiseaseMonth(@RequestParam("diseaseMonth") String diseaseMonth,
                                                   @RequestParam("noteId") String noteId) {
        LOGGER.debug(">>>>>>>>>>>>>> getDiseaseMonth 시작 :" + diseaseMonth);
        GridResponseObject<Disease> gridResponseObject = new GridResponseObject<Disease>();
        if(noteService.selectUserNote(noteId
        )){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }
        if (diseaseMonth == null)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "질병일 파라미터가 없습니다.");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("diseaseMonth", diseaseMonth);
        paramMap.put("noteId", noteId);

        gridResponseObject.setData(diaryService.selectDiseaseMonth(paramMap));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value = "/disease/{diseaseId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<String> selectDisease(@PathVariable("diseaseId") String diseaseId) {
        LOGGER.debug(">>>>>>>>>>> selectDisease 시작 :" + diseaseId);
        ResponseObject<String> responseObject = new ResponseObject<String>();
        if(diaryService.selectDiseaseAuth(diseaseId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("diseaseId", diseaseId);

        responseObject.setData(diaryService.selectDisease(paramMap));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/disease", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertDisease(@RequestBody Map<String, Object> requestParams) {
        LOGGER.debug(">>>>>>>>>>> insertDisease 시작 :" + requestParams);
        ResponseObject<String> responseObject = new ResponseObject<String>();

        Disease disease = setDisease(null, requestParams);
        if(noteService.selectUserNote(disease.getNoteId())){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }
        int rtn = diaryService.insertDisease(disease);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/disease/{diseaseId}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> updateDisease(@PathVariable("diseaseId") String diseaseId,
                                         @RequestBody Map<String, Object> requestParams) {
        LOGGER.debug(">>>>>>>>>>> updateDisease 시작 :" + diseaseId + " : " + requestParams);
        ResponseObject<String> responseObject = new ResponseObject<String>();
        if(diaryService.selectDiseaseAuth(diseaseId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }
        Disease disease = setDisease(diseaseId, requestParams);

        int rtn = diaryService.updateDisease(disease);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/disease/{diseaseId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseObject<String> deleteDisease(@PathVariable("diseaseId") String diseaseId) {
        LOGGER.debug(">>>>>>>>>>> deleteDisease 시작 :" + diseaseId);
        ResponseObject<String> responseObject = new ResponseObject<String>();
        if(diaryService.selectDiseaseAuth(diseaseId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("diseaseId", diseaseId);

        int rtn = diaryService.deleteDisease(paramMap);
        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    private Diary setDiary(String diaryId, Map<String, Object> params) {
        Diary diary = new Diary();
        diaryId = diaryId == null ? IdGen.getNextId() : diaryId;
        String title = params.get("title") == null ? null : (String) params.get("title");
        String content = params.get("content") == null ? null : (String) params.get("content");
        String fileId = params.get("fileId") == null ? null : (String) params.get("fileId");
        String noteId = params.get("noteId") == null ? null : (String) params.get("noteId");
        String diaryDt = "";
        String feelingCd = params.get("feelingCd") == null ? "" : (String) params.get("feelingCd");
        String healthCd = params.get("healthCd") == null ? "" : (String) params.get("healthCd");
        String feverCd = params.get("feverCd") == null ? "" : (String) params.get("feverCd");
        String breakfastCd = params.get("breakfastCd") == null ? "" : (String) params.get("breakfastCd");
        String lunchCd = params.get("lunchCd") == null ? "" : (String) params.get("lunchCd");
        String dinnerCd = params.get("dinnerCd") == null ? "" : (String) params.get("dinnerCd");
        String shitCd = params.get("shitCd") == null ? "" : (String) params.get("shitCd");
        String shitCnt = params.get("shitCnt") == null ? "" : (String) params.get("shitCnt");
        String shitDesc = params.get("shitDesc") == null ? "" : (String) params.get("shitDesc");
        String sleepStartTime = params.get("sleepStartTime") == null ? null : (String) params.get("sleepStartTime");
        String sleepEndTime = params.get("sleepEndTime") == null ? null : (String) params.get("sleepEndTime");
        int height = params.get("height") == null ? null : Integer.parseInt(params.get("height") + "");
        float weight = params.get("weight") == null ? (float) 0 : Float.valueOf(params.get("weight") + "");

        if (params.get("diaryDt") == null || "".equals("diaryDt")) {
            Date date = new Date();
            diaryDt = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            diaryDt = (String) params.get("diaryDt");
        }

        if (title == null || content == null)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "타이틀, 내용에 등록된 내용이 없습니다.");

        diary.setDiaryId(diaryId);
        diary.setNoteId(noteId);
        diary.setTitle(title);
        diary.setContent(content);
        diary.setRegUserId(UserContext.getUserId());
        diary.setFileId(fileId);
        diary.setDiaryDt(diaryDt);
        diary.setStateId(IdGen.getNextId());
        diary.setFeelingCd(feelingCd);
        diary.setHealthCd(healthCd);
        diary.setFeverCd(feverCd);
        diary.setBreakfastCd(breakfastCd);
        diary.setLunchCd(lunchCd);
        diary.setDinnerCd(dinnerCd);
        diary.setShitCd(shitCd);
        diary.setShitCnt(shitCnt);
        diary.setShitDesc(shitDesc);
        diary.setSleepStartTime(sleepStartTime);
        diary.setSleepEndTime(sleepEndTime);
        diary.setHeight(height);
        diary.setWeight(weight);

        return diary;
    }

    private Disease setDisease(String diseaseId, Map<String, Object> params) {
        Disease disease = new Disease();

        diseaseId = diseaseId == null ? IdGen.getNextId() : diseaseId;
        String diseaseNm = params.get("diseaseNm") == null ? null : (String) params.get("diseaseNm");
        String symptom = params.get("symptom") == null ? null : (String) params.get("symptom");
        String hospitalNm = params.get("hospitalNm") == null ? null : (String) params.get("hospitalNm");
        String prescription = params.get("prescription") == null ? null : (String) params.get("prescription");
        String noteId = params.get("noteId") == null ? null : (String) params.get("noteId");

        String diseaseDt = "";
        if (params.get("diseaseDt") == null) {
            Date date = new Date();
            diseaseDt = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } else {
            diseaseDt = (String) params.get("diseaseDt");
        }

        if (noteId == null)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "noteId 정보가 올바르지 않습니다.");

        disease.setDiseaseId(diseaseId);
        disease.setNoteId(noteId);
        disease.setDiseaseNm(diseaseNm);
        disease.setSymptom(symptom);
        disease.setHospitalNm(hospitalNm);
        disease.setDiseaseDt(diseaseDt);
        disease.setPrescription(prescription);

        return disease;
    }
}
