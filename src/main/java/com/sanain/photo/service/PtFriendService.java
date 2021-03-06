package com.sanain.photo.service;

import com.sanain.photo.mapper.PtFriendMapper;
import com.sanain.photo.mapper.PtUserMapper;
import com.sanain.photo.pojo.*;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友关系的service
 * @Author sanain
 */
@Service
public class PtFriendService {
    @Autowired
    private PtFriendMapper mapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PtUserService ptUserService;

    /**
     * 添加好友关系
     * 插入俩条记录
     * @return
     */
    @Transactional
    public Map<String,Object> insert(PtFriendApply apply){
        // 从redis中移除好友双方的好友列表
        redisUtil.del(ConstantUtil.ALL_FRIEND + apply.getToId());
        redisUtil.del(ConstantUtil.ALL_FRIEND + apply.getFromId());

        Map<String,Object> map = new HashMap<>();

        /*查询他们是否已经是朋友*/
        PtFriendExample example = new PtFriendExample();
        PtFriendExample.Criteria criteria = example.createCriteria();
        criteria.andFriendIdEqualTo(apply.getToId());
        criteria.andSelfIdEqualTo(apply.getFromId());
        List<PtFriend> ptFriends = mapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(ptFriends)){
            map.put("result",false);
            map.put("msg","你们已经是朋友");
            return map;
        }

        //插入一条以好友为主的记录
        PtUser toUser = ptUserService.selectById(apply.getToId());
        PtUser fromUser = ptUserService.selectById(apply.getFromId());
        PtFriend to = new PtFriend();
        to.setSelfId(toUser.getUserId());
        to.setFriendId(apply.getFromId());
        to.setCreateTime(new Date());
        to.setRemark(fromUser.getUserName());
        to.setFriendPhoto(fromUser.getPhotoPath());
        mapper.insertSelective(to);

        //插入一条以申请人为主的记录
        PtFriend from = new PtFriend();
        from.setSelfId(fromUser.getUserId());
        from.setFriendId(apply.getToId());
        from.setCreateTime(new Date());
        from.setRemark(toUser.getUserName());
        from.setFriendPhoto(toUser.getPhotoPath());
        mapper.insertSelective(from);


        map.put("result",true);
        map.put("msg","添加好友成功");
        return map;
    }


    /**
     * 根据用户id和好友id查询
     * @param userId
     * @return
     */
    public PtFriend getAllBySelfIdAndFriendId(Integer userId,Integer friendId){
        PtFriendExample example = new PtFriendExample();
        PtFriendExample.Criteria criteria = example.createCriteria();
        criteria.andSelfIdEqualTo(userId);
        criteria.andFriendIdEqualTo(friendId);

        return returnFrist(mapper.selectByExample(example));
    }


    /**
     * 根据用户id获取所有的好友列表
     * @param userId
     * @return
     */
    public List<PtFriend> getAllBySelfId(Integer userId){
        PtFriendExample example = new PtFriendExample();
        example.setOrderByClause("remark");
        PtFriendExample.Criteria criteria = example.createCriteria();
        criteria.andSelfIdEqualTo(userId);

        List<PtFriend> ptFriends = mapper.selectByExample(example);

        return ptFriends;
    }

    /**
     * 根据关系id获取好友消息
     * @param id
     * @return
     */
    public PtUser getFriendInfoById(Integer id){
        //获取好友关系
        PtFriend ptFriend = mapper.selectByPrimaryKey(id);

        //根据好友关系中的好友id从ptuser表中查询相对应的用户信息
        return ptUserService.selectById(ptFriend.getFriendId());
    }


    /**
     * 删除好友关系
     * @param
     */
    @Transactional
    public void deleteFriend(Integer fromId , Integer toId ){
        // 从redis中移除好友双方的好友列表
        redisUtil.del(ConstantUtil.ALL_FRIEND + toId);
        redisUtil.del(ConstantUtil.ALL_FRIEND + fromId);

        //删除以fromId为主的记录
        PtFriendExample example = new PtFriendExample();
        PtFriendExample.Criteria criteria1 = example.createCriteria();
        criteria1.andSelfIdEqualTo(fromId);
        criteria1.andFriendIdEqualTo(toId);
        example.or(criteria1);

        //删除以toId为主的记录
        PtFriendExample.Criteria criteria2 = example.createCriteria();
        criteria2.andSelfIdEqualTo(toId);
        criteria2.andFriendIdEqualTo(fromId);
        example.or(criteria2);

        mapper.deleteByExample(example);
    }

    /**
     * 返回第一条记录
     * @param list
     * @return
     */
    private PtFriend returnFrist(List<PtFriend> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }
}
