package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtUserDynamic;
import com.sanain.photo.pojo.PtUserDynamicComment;
import com.sanain.photo.service.PtUserDynamicCommentService;
import com.sanain.photo.service.PtUserDynamicService;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**动态评论的controller
 * @Author sanain
 */

@Controller
@RequestMapping("/comment")
public class PtUserDynamicCommentController {
    @Autowired
    private PtUserDynamicCommentService commentService;
    @Autowired
    private PtUserDynamicService ptUserDynamicService;
    /**
     * 添加评论
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertComment")
    public Map<String,Object> insertComment(PtUserDynamicComment comment){

        comment = commentService.insertComment(comment);

        PtUserDynamic ptUserDynamic = ptUserDynamicService.getById(comment.getDynamicId());

        String msg = comment != null ? "插入成功":"插入失败";
        String status = comment != null ? "00":"01";
        Map<String,Object> map = new HashMap<>();
        map.put("commentInfo",comment);
        map.put("commentCount",ptUserDynamic.getCommentCount());

        return ResponseUtils.packaging(status,msg,map);
    }

    /**
     * 删除
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteComment")
    public Map<String,Object> deleteComment(Integer commentId, Integer dynamicId){
        if(commentId == null){
            return ResponseUtils.packaging("01","评论id不能为空",null);
        }
        commentService.deleteCommentByDynamicId(commentId);

        PtUserDynamic ptUserDynamic = ptUserDynamicService.getById(dynamicId);

        Map<String,Object> map = new HashMap<>();
        map.put("commentCount",ptUserDynamic.getCommentCount());

        return ResponseUtils.packaging("00","删除成功",map);
    }
}
