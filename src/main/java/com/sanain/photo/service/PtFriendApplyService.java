package com.sanain.photo.service;

import com.sanain.photo.mapper.PtFriendApplyMapper;
import com.sanain.photo.pojo.PtFriendApply;
import com.sanain.photo.pojo.PtFriendApplyExample;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友申请的service
 * @Author sanain
 */
@Service
public class PtFriendApplyService {
    @Autowired
    private PtFriendApplyMapper ptFriendApplyMapper;
    @Autowired
    private PtFriendService ptFriendService;
    @Autowired
    private PtUserService ptUserService;

    /**
     * 查询申请添加自己为好友的申请
     * @param userId
     * @return
     */
    public List<PtFriendApply> getAllApply(Integer userId){
        if(userId == null){
            return null;
        }

        PtFriendApplyExample example = new PtFriendApplyExample();
        //设置根据创建时间查询，倒序查询
        example.setOrderByClause("create_date");
        example.setDistinct(true);
        PtFriendApplyExample.Criteria criteria = example.createCriteria();
        criteria.andToIdEqualTo(userId);

        return ptFriendApplyMapper.selectByExample(example);
    }


    /**
     * 删除申请
     * @param applyId
     * @return
     */
    @Transactional
    public boolean deleteApply(Integer applyId){
        if(applyId == null){
            return false;
        }

        int i = ptFriendApplyMapper.deleteByPrimaryKey(applyId);

        return i == 0 ? false : true;
    }

    /**
     * 添加
     * @param apply
     * @return
     */
    @Transactional
    public Map<String,Object> insert(PtFriendApply apply , PtUser ptUser){
        Map<String,Object> map = new HashMap<>();

        /*查询之前是否已经发送过申请，并且对方还未处理*/
        PtFriendApplyExample example1 = new PtFriendApplyExample();
        PtFriendApplyExample.Criteria criteria1 = example1.createCriteria();
        /*设置查询条件为申请者id等于当前用户id*/
        criteria1.andFromIdEqualTo(ptUser.getUserId());

        criteria1.andToIdEqualTo(apply.getToId());
        criteria1.andIsDealEqualTo(0);

        List<PtFriendApply> ptFriendApplies1 = ptFriendApplyMapper.selectByExample(example1);
        if(!CollectionUtils.isEmpty(ptFriendApplies1)){
            map.put("msg","已经未处理的好友申请，不能多次申请");
            map.put("result",false);
            map.put("applyInfo",null);
            return map;
        }

        //插入申请
        apply.setFromPhoto(ptUser.getPhotoPath());
        apply.setFromId(ptUser.getUserId());
        apply.setFromUserName(ptUser.getUserName());
        apply.setCreateDate(new Date());
        apply.setIsDeal(0);
        int i = ptFriendApplyMapper.insertSelective(apply);

        if (i == 0) {
            return null;
        }

        //查询出来刚才插入的对象
        PtFriendApplyExample example2 = new PtFriendApplyExample();
        PtFriendApplyExample.Criteria criteria = example2.createCriteria();
        criteria.andToIdEqualTo(apply.getToId());
        criteria.andFromIdEqualTo(apply.getFromId());
        List<PtFriendApply> ptFriendApplies = ptFriendApplyMapper.selectByExample(example2);

        PtFriendApply first = getFirst(ptFriendApplies);
        map.put("msg","提交申请成功");
        map.put("result",true);
        map.put("applyInfo",first);
        return map;
    }


    /**
     * 根据申请id查询
     * @param applyId
     * @return
     */
    public PtFriendApply selectApplyById(Integer applyId){
        if(applyId == null){
            return null;
        }

        return ptFriendApplyMapper.selectByPrimaryKey(applyId);
    }


    /**
     * 处理申请（通过、拒绝）
     * @param apply
     * @return
     */
    @Transactional
    public Map<String , Object> dealApply(PtFriendApply apply){
        Map<String,Object> map = new HashMap<>();

        PtFriendApply ptFriendApply = ptFriendApplyMapper.selectByPrimaryKey(apply.getId());
        if(ptFriendApply.getIsDeal() == 1){
            map.put("result",false);
            map.put("msg","该申请已经被处理过，不能再次处理");
            return map;
        }

        if(apply.getResult() == null){
            map.put("result",false);
            map.put("msg","还未进行处理申请");
            return map;
        }

        apply.setIsDeal(1);
        int i = ptFriendApplyMapper.updateByPrimaryKeySelective(apply);

        if(i == 0){
            map.put("result",false);
            map.put("msg","更新申请表出错");
            return map;
        }

        map.put("result",true);
        map.put("msg","申请处理成功");

        //如果是通过，就添加好友关系
        if(apply.getResult() == 0) {
            apply = ptFriendApplyMapper.selectByPrimaryKey(apply.getId());
            ptFriendService.insert(apply);
        }

        return map;
    }

    /**
     * 获取第一个元素
     * @param list
     * @return
     */
    private   PtFriendApply getFirst(List<PtFriendApply> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }
}
