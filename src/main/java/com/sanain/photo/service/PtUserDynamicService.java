package com.sanain.photo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanain.photo.mapper.PtUserDynamicMapper;
import com.sanain.photo.pojo.*;
import com.sanain.photo.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author sanain
 */
@Service
public class PtUserDynamicService {
    @Autowired
    private PtUserDynamicMapper ptUserDynamicMapper;
    @Autowired
    private PtUserDynamicCommentService ptUserDynamicCommentService;

    /**
     * 获取所有好友的动态
     * @param
     * @return
     */
    public PageInfo<PtUserDynamic> getAllFriendDynamic(List<PtFriend> friendList, Integer pageNum , Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<PtUserDynamic> ptUserDynamics = ptUserDynamicMapper.selectAllFriendDynamic(friendList);

        for(PtUserDynamic dynamic : ptUserDynamics){
            // 获取评论
            List<PtUserDynamicComment> commentList = ptUserDynamicCommentService.getCommentByDynamicId(dynamic.getId());
            dynamic.setCommentList(commentList);
            List<String> list = new ArrayList<>();
            //把动态的照片路径拼凑出来
            if(!StringUtils.isEmpty(dynamic.getPhotoPaths())){
                for(String str : dynamic.getPhotoPaths().split(";")){
                    if(StringUtils.isEmpty(str)){
                        continue;
                    }
                    list.add(ConstantUtil.PRO_PATH+ConstantUtil.PATH_DYNAMIC_IMG+str);
                }
                dynamic.setPhotoList(list);
            }
        }

        return new PageInfo<>(ptUserDynamics);
    }

    /**
     * 添加动态
     * @param ptUserDynamic
     * @return
     */
    @Transactional
    public boolean insertDynamic(PtUserDynamic ptUserDynamic){
        ptUserDynamic.setCommentCount(0);
        ptUserDynamic.setStarCount(0);
        ptUserDynamic.setCreateTime(new Date());

        int i = ptUserDynamicMapper.insertSelective(ptUserDynamic);

        return i == 0 ? false : true;
    }


    /**
     * 删除动态
     * @param
     * @return
     */
    @Transactional
    public boolean deleteDynamic(Integer id){
        if(id == null){
            return false;
        }
        int i = ptUserDynamicMapper.deleteByPrimaryKey(id);

        if(i == 0){
            return false;
        }

        ptUserDynamicCommentService.deleteCommentByDynamicId(id);

        return i == 0 ? false : true;
    }

    /**
     * 添加或者减少动态点赞数
     * @param
     * @return
     */
    @Transactional
    public PtUserDynamic addStarCount(Integer id , boolean isAdd){
        if(id == null){
            return null;
        }
        PtUserDynamic dynamic = ptUserDynamicMapper.selectByPrimaryKey(id);

        if(isAdd){
            dynamic.setStarCount(dynamic.getStarCount()+1);
        }else if(!isAdd && dynamic.getStarCount() >0){
            dynamic.setStarCount(dynamic.getStarCount()-1);
        }

        ptUserDynamicMapper.updateByPrimaryKeySelective(dynamic);

        return dynamic;
    }

    /**
     * 添加或者减少动态评论数量
     * @param
     * @return
     */
    @Transactional
    public boolean addCommentCount(Integer id , boolean isAdd){
        if(id == null){
            return false;
        }
        PtUserDynamic dynamic = ptUserDynamicMapper.selectByPrimaryKey(id);

        if(isAdd){
            dynamic.setCommentCount(dynamic.getCommentCount()+1);
        }else if(!isAdd && dynamic.getCommentCount() >0){
            dynamic.setCommentCount(dynamic.getCommentCount()-1);
        }

        Integer i= ptUserDynamicMapper.updateByPrimaryKeySelective(dynamic);

        return i == 0 ? false : true;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public PtUserDynamic getById(Integer id){
        if(id == null){
            return null;
        }

        return ptUserDynamicMapper.selectByPrimaryKey(id);
    }
}
