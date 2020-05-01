package com.sanain.photo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanain.photo.mapper.PtAnnounMapper;
import com.sanain.photo.pojo.PtAnnoun;
import com.sanain.photo.pojo.PtAnnounExample;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author sanain
 */
@Service
public class PtAnnounService {
    @Autowired
    private PtAnnounMapper ptAnnounMapper;
    @Autowired
    private PtAnnounHistoryService ptAnnounHistoryService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 插入公告
     * @param announ
     * @return
     */
    @Transactional
    public boolean insertAnnoun(PtAnnoun announ){
        announ.setCreateTime(new Date());

//        删除redis中的最新十条公告的缓存
        redisUtil.del(ConstantUtil.LASTER_ANNOUN);
        Integer i = ptAnnounMapper.insertSelective(announ);

        return i > 0;
    }

    /**
     * 查询最新的十条公告
     * @return
     */
    public PageInfo<PtAnnoun> getFiftyLatest(){
//        查询redis中是否已经有缓存，如果有就返回
        Object o = redisUtil.get(ConstantUtil.LASTER_ANNOUN);
        if(o != null){
            return new PageInfo<>(JsonUtils.jsonToList(o.toString(),PtAnnoun.class));
        }
        PageHelper.startPage(1,10);

        PtAnnounExample example = new PtAnnounExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);

        List<PtAnnoun> ptAnnouns = ptAnnounMapper.selectByExample(example);
//        查询到的结果放入redis中
        redisUtil.set(ConstantUtil.LASTER_ANNOUN,JsonUtils.listToJson(ptAnnouns));
        return new PageInfo<>(ptAnnouns);
    }

    /**
     * 根据条件分页查询公告
     * @return
     */
    public PageInfo<PtAnnoun> getListByExample(PtAnnoun ptAnnoun , Integer pageNum , Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);

        PtAnnounExample example = new PtAnnounExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);
        PtAnnounExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(ptAnnoun.getTitle())){
            criteria.andTitleLike("%"+ptAnnoun.getTitle()+"%");
        }

        List<PtAnnoun> ptAnnouns = ptAnnounMapper.selectByExample(example);

        return new PageInfo<>(ptAnnouns);
    }


    /**
     * 删除公告
     * @param
     * @return
     */
    @Transactional
    public boolean deleteAnnoun(Integer announId){
        if(announId == null){
            return false;
        }
        //        删除redis中的最新十条公告的缓存
        redisUtil.del(ConstantUtil.LASTER_ANNOUN);
        Integer i = ptAnnounMapper.deleteByPrimaryKey(announId);

        ptAnnounHistoryService.deleteByAnnounId(announId);

        return i > 0;
    }
}
