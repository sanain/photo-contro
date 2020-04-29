package com.sanain.photo.service;

import com.sanain.photo.mapper.PtUserDynamicCommentMapper;
import com.sanain.photo.pojo.PtUserDynamicComment;
import com.sanain.photo.pojo.PtUserDynamicCommentExample;
import com.sanain.photo.util.ConstantUtil;
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
public class PtUserDynamicCommentService {
    @Autowired
    private PtUserDynamicCommentMapper ptUserDynamicCommentMapper;
    @Autowired
    private PtUserDynamicService ptUserDynamicService;
    /**
     * 根据动态id查询评论
     * @param id
     * @return
     */
    public List<PtUserDynamicComment> getCommentByDynamicId(Integer id){
        if(id == null){
            return null;
        }

        List<PtUserDynamicComment> commentList = ptUserDynamicCommentMapper.selectAllCommentByDynamicId(id);

        if(!CollectionUtils.isEmpty(commentList)){
            for(PtUserDynamicComment comment : commentList){
                if(comment.getUser() !=  null){
                    comment.getUser().setPhotoPath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+comment.getUser().getPhotoPath());
                }
                if(comment.getCommentUser() !=  null){
                    comment.getCommentUser().setPhotoPath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+comment.getCommentUser().getPhotoPath());
                }
            }
        }


        return commentList;
    }

    /**
     * 插入评论
     * @param comment
     * @return
     */
    @Transactional
    public PtUserDynamicComment insertComment(PtUserDynamicComment comment){
        comment.setCreateTime(new Date());
        int i = ptUserDynamicCommentMapper.insert(comment);

        if(i == 0){
            return null;
        }

        //从数据库中查出刚才插入的记录
        comment = ptUserDynamicCommentMapper.selectAllInfoById(comment.getId());

        if(comment.getUser() !=  null){
            comment.getUser().setPhotoPath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+comment.getUser().getPhotoPath());
        }
        if(comment.getCommentUser() !=  null){
            comment.getCommentUser().setPhotoPath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+comment.getCommentUser().getPhotoPath());
        }

//        添加评论数
        ptUserDynamicService.addCommentCount(comment.getDynamicId(),true);
        return comment;
    }

    /**
     * 根据动态id删除评论
     * @param id
     */
    public void deleteCommentByDynamicId(Integer id){
        PtUserDynamicCommentExample example = new PtUserDynamicCommentExample();
        PtUserDynamicCommentExample.Criteria criteria = example.createCriteria();
        criteria.andDynamicIdEqualTo(id);

        ptUserDynamicCommentMapper.deleteByExample(example);


    }

    /**
     * 删除评论
     * @param id
     */
    public void deleteCommentById(Integer id){
        PtUserDynamicComment comment = ptUserDynamicCommentMapper.selectByPrimaryKey(id);
        ptUserDynamicCommentMapper.deleteByPrimaryKey(id);
        //减少评论数
        ptUserDynamicService.addCommentCount(comment.getDynamicId(),false);
    }
}
