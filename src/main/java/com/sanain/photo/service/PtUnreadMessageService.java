package com.sanain.photo.service;

import com.sanain.photo.mapper.PtUnreadMessageMapper;
import com.sanain.photo.pojo.PtUnreadMessage;
import com.sanain.photo.pojo.PtUnreadMessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 未读消息的service
 * @Author sanain
 */
@Service
public class PtUnreadMessageService  {
    @Autowired
    private PtUnreadMessageMapper mapper;

    /**
     * 根据接收方id查询自己所有的未读消息
     * @param toId
     * @return
     */
    public List<PtUnreadMessage> getAllByToId(Integer toId){
        if(toId == null){
            return null;
        }
        PtUnreadMessageExample example = new PtUnreadMessageExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);
        PtUnreadMessageExample.Criteria criteria = example.createCriteria();
        criteria.andToIdEqualTo(toId);

        return mapper.selectByExample(example);
    }


    /**
     * 根据接收方id删除
     * @param toId
     */
    @Transactional
    public void deleteByToId( Integer toId){
        PtUnreadMessageExample example = new PtUnreadMessageExample();
        PtUnreadMessageExample.Criteria criteria = example.createCriteria();
        criteria.andToIdEqualTo(toId);

        mapper.deleteByExample(example);
    }


    /**
     * 插入未读信息
     * @param message
     */
    @Transactional
    public void insertUnreadMessage(PtUnreadMessage message){
//        message.setCreateTime(new Date());

        mapper.insert(message);
    }
}
