package com.sanain.photo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanain.photo.mapper.PtUserMapper;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserExample;
import com.sanain.photo.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户的service
 * @Author sinain
 */
@Service
public class PtUserService {
    @Autowired
    private PtUserMapper ptUserMapper;

    /**
     * 根据邮箱和密码查询
     * @param ptUser
     * @return
     */
    public PtUser selectByEmailAndPassword(PtUser ptUser){
        //如果邮箱或者密码为空直接返回null
        if(ptUser == null || StringUtils.isEmpty(ptUser.getUserEmail()) ||
                StringUtils.isEmpty(ptUser.getUserPassword())) {
            return null;
        }
        //把密码进行md5加密
        String md5 = MD5Utils.stringToMD5(ptUser.getUserPassword());
        ptUser.setUserPassword(md5);

        PtUserExample example = new PtUserExample();
        PtUserExample.Criteria criteria= example.createCriteria();
        //设置查询条件为 user_email 等于ptUser.getUserEmail()
        //也就用户名相等
        // where user_email='ptUser.getUserEmail()' and user_password='md5'
        criteria.andUserEmailEqualTo(ptUser.getUserEmail());

        //设置查询条件为 user_password 等于md5
        //也就是密码相等
        criteria.andUserPasswordEqualTo(md5);

        //进行查询
        List<PtUser> userList = ptUserMapper.selectByExample(example);

        return returnUser(userList);

    }

    /**
     * 新增用户
     * @param ptUser
     * @return
     */
    @Transactional
    public PtUser insertUser(PtUser ptUser) {
        //如果邮箱或者密码为空直接返回null
        if(ptUser == null || StringUtils.isEmpty(ptUser.getUserEmail()) ||
                StringUtils.isEmpty(ptUser.getUserPassword())) {
            return null;
        }
        ptUser.setIsUse(0);
        ptUser.setPhotoPath("default.png");
        ptUser.setUserName(ptUser.getUserEmail());
        ptUser.setSignature("");
        ptUser.setUserPhone("");
        ptUser.setCreateTime(new Date());
        //用户的密码加密
        ptUser.setUserPassword(MD5Utils.stringToMD5(ptUser.getUserPassword()));
        int i = ptUserMapper.insertSelective(ptUser);

        if(i == 0){
            return null;
        }

        /**下面代码为查询出来刚才插入的用户**/
        PtUserExample example = new PtUserExample();
        PtUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserEmailEqualTo(ptUser.getUserEmail());

        List<PtUser> ptUsers = ptUserMapper.selectByExample(example);

        return returnUser(ptUsers);
    }

    /**
     *更新用户信息
     * @return
     */
    @Transactional
    public PtUser updateUser(PtUser ptUser){
        if(ptUser == null){
            return ptUser;
        }

        if(ptUser.getUserPhone() == null){
            ptUser.setUserPhone("");
        }
        if(ptUser.getSignature() == null){
            ptUser.setSignature("");
        }
        if(!StringUtils.isEmpty(ptUser.getUserPassword())){
            ptUser.setUserPassword(MD5Utils.stringToMD5(ptUser.getUserPassword()));
        }
        //根据主键更新，并且不为空的属性才会更新到数据库中
        int i = ptUserMapper.updateByPrimaryKeySelective(ptUser);
        if(i == 0){
            return null;
        }

        ptUser = ptUserMapper.selectUnionByPrimaryKey(ptUser.getUserId());
        return ptUser;
    }

    /**
     * 根据id删除
     */
    @Transactional
    public boolean deleteById(Integer userId){
        if(StringUtils.isEmpty(userId)){
            return false;
        }

        int i = ptUserMapper.deleteByPrimaryKey(userId);

        return i > 0 ? true : false;
    }

    /**
     * 根据id查询
     */
    public PtUser selectById(Integer userId){
        if(StringUtils.isEmpty(userId)){
            return null;
        }

        return ptUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 是否已经存在相同邮箱
     * @param email
     * @return  false 不存在 true已经存在
     */
    public boolean hasSameEmail(String email){
        if(StringUtils.isEmpty(email)){
            return false;
        }

        PtUserExample example = new PtUserExample();
        PtUserExample.Criteria criteria = example.createCriteria();
        //查询条件为 user_email = email
        criteria.andUserEmailEqualTo(email);

        List<PtUser> ptUsers = ptUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(ptUsers)){
            return false;
        }

        return true;
    }


    /**
     * 根据邮箱查询
     * @param email
     * @return  false 不存在 true已经存在
     */
    public List<PtUser> selectByEmail(String email){
        if(StringUtils.isEmpty(email)){
            return new ArrayList<>();
        }

        PtUserExample example = new PtUserExample();
        PtUserExample.Criteria criteria = example.createCriteria();
        //查询条件为 user_email = email
        criteria.andUserEmailEqualTo(email);

        List<PtUser> ptUsers = ptUserMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(ptUsers)){
            for(PtUser ptUser:ptUsers){
                ptUser.setUserPassword("");
            }
        }

        return ptUsers;
    }


    /**
     * 当列表不为空，返回第一个元素，为空返回null
     * @param ptUsers
     * @return
     */
    private PtUser returnUser(List<PtUser> ptUsers){
        if(CollectionUtils.isEmpty(ptUsers)){
            return null;
        }

        return ptUsers.get(0);
    }


    /**
     * 根据条件分页查询查询
     * @param
     * @return  false 不存在 true已经存在
     */
    public PageInfo<PtUser> getListByExample(PtUser ptUser , PtUser currentUser , Integer pageNum , Integer pageSize){

        PtUserExample example = new PtUserExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);
        PtUserExample.Criteria criteria = example.createCriteria();

        /*邮箱不为空根据邮箱查询*/
        if(!StringUtils.isEmpty(ptUser.getUserEmail())){
            criteria.andUserEmailLike("%"+ptUser.getUserEmail()+"%woq zuofan");
        }

        /*用户名不为空根据邮箱查询*/
        if(!StringUtils.isEmpty(ptUser.getUserName())){
            criteria.andUserNameLike("%"+ptUser.getUserName()+"%");
        }

//        不查询自己
        criteria.andUserIdNotEqualTo(currentUser.getUserId());

        PageHelper.startPage(pageNum,pageSize);
        List<PtUser> ptUsers = ptUserMapper.selectUnionByExample(example);
        if(!CollectionUtils.isEmpty(ptUsers)){
            for(PtUser u : ptUsers){
                u.setIsUseStr(u.getIsUse() == 0 ? "启用":"禁用");
            }
        }
        PageInfo<PtUser> pageInfo = new PageInfo<>(ptUsers);

        return pageInfo;
    }
}
