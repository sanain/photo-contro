package com.sanain.photo.service;

import com.sanain.photo.mapper.PtAnnounHistoryMapper;
import com.sanain.photo.mapper.PtAnnounMapper;
import com.sanain.photo.pojo.PtAnnounExample;
import com.sanain.photo.pojo.PtAnnounHistory;
import com.sanain.photo.pojo.PtAnnounHistoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author sanain
 */
@Service
public class PtAnnounHistoryService {
    @Autowired
    private PtAnnounHistoryMapper ptAnnounHistoryMapper;

    /**
     * 插入历史
     * @param history
     * @return
     */
    @Transactional
    public boolean insertHistory(PtAnnounHistory history){
        if(history.getAnnounId() == null || history.getUserId() == null){
            return false;
        }
        PtAnnounHistoryExample example = new PtAnnounHistoryExample();
        PtAnnounHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andAnnounIdEqualTo(history.getAnnounId());
        criteria.andUserIdEqualTo(history.getUserId());

//        如果已经存在就不要在插入
        List<PtAnnounHistory> historyList = ptAnnounHistoryMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(historyList)){
            return true;
        }

        history.setCreateTime(new Date());

        int i = ptAnnounHistoryMapper.insertSelective(history);

        return i > 0;
    }

    /**
     * 根据公告id删除
     * @param announId
     */
    @Transactional
    public void deleteByAnnounId(Integer announId){
        PtAnnounHistoryExample example = new PtAnnounHistoryExample();
        PtAnnounHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andAnnounIdEqualTo(announId);

        ptAnnounHistoryMapper.deleteByExample(example);
    }

    /**
     * 根据公告id和用户id查询
     * @param announId
     */
    public PtAnnounHistory selectByAnnounIdAndUserId(Integer announId,Integer userId){
        PtAnnounHistoryExample example = new PtAnnounHistoryExample();
        PtAnnounHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andAnnounIdEqualTo(announId);
        criteria.andUserIdEqualTo(userId);

        List<PtAnnounHistory> historyList = ptAnnounHistoryMapper.selectByExample(example);

        return returnFirst(historyList);
    }


    private PtAnnounHistory returnFirst(List<PtAnnounHistory> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }
}
