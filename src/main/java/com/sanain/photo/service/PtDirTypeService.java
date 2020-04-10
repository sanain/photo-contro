package com.sanain.photo.service;

import com.sanain.photo.mapper.PtDirTypeMapper;
import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtDirType;
import com.sanain.photo.pojo.PtDirTypeExample;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 文件夹分类的service
 * @Author sanain
 */
@Service
public class PtDirTypeService {
    @Autowired
    private PtDirTypeMapper ptDirTypeMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 增加文件夹分类
     * @param type
     * @return
     */
    @Transactional
    public PtDirType insertType(PtDirType type){
        type.setCreateTime(new Date());
        //先删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);
        int i = ptDirTypeMapper.insertSelective(type);
        //删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);

        if(i == 0){
            return null;
        }

        PtDirTypeExample example = new PtDirTypeExample();
        PtDirTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(type.getTypeName());

        List<PtDirType> ptDirTypes = ptDirTypeMapper.selectByExample(example);
        return returnFrist(ptDirTypes);
    }


    /**
     * 修改文件夹分类
     * @param type
     * @return
     */
    @Transactional
    public PtDirType updateType(PtDirType type){
        if(type.getTypeId() == null){
            return null;
        }
        //删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);
        int i = ptDirTypeMapper.updateByPrimaryKeySelective(type);
        //删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);
        if(i == 0){
            return null;
        }

        return ptDirTypeMapper.selectByPrimaryKey(type.getTypeId());
    }

    /**
     * 删除文件夹分类
     * @param typeId
     * @return
     */
    @Transactional
    public boolean deleteType(Integer typeId){
        if(typeId == null){
            return false;
        }

        //删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);
        int i = ptDirTypeMapper.deleteByPrimaryKey(typeId);
        //删除分类的缓存
        redisUtil.del(ConstantUtil.ALL_DIR_TYPE);

        return i == 0 ? false : true;
    }

    /**
     * 查询所有的文件夹分类
     * @param
     * @return
     */
    @Transactional
    public List<PtDirType> selectAllType(){
        Object o = redisUtil.get(ConstantUtil.ALL_DIR_TYPE);
        if(o != null){//缓存中存在，就先从缓存中拿
            return JsonUtils.jsonToList(o.toString(), PtDirType.class);
        }

        List<PtDirType> ptDirTypes = ptDirTypeMapper.selectAll();

        //放入缓存中
        redisUtil.set(ConstantUtil.ALL_DIR_TYPE,JsonUtils.listToJson(ptDirTypes));

        return ptDirTypes;
    }


    /**
     * 根据主键查询
     * @param typeId
     * @return
     */
    public PtDirType selectTypeById(Integer typeId){
        if(typeId == null){
            return null;
        }

        return ptDirTypeMapper.selectByPrimaryKey(typeId);
    }



    /**
     * 查询是否已经存在相同的文件夹分类名
     * @param
     * @return 存在 true  不存在 false
     */
    public boolean hasSameName(String name) {
        if (StringUtils.isEmpty(name)) {
            return true;
        }

        PtDirTypeExample example = new PtDirTypeExample();
        PtDirTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(name);

        List<PtDirType> ptDirTypes = ptDirTypeMapper.selectByExample(example);

        return CollectionUtils.isEmpty(ptDirTypes)?false:true;
    }

    /**
     * 当列表不为空，返回第一个元素，为空返回null
     * @param
     * @return
     */
    private PtDirType returnFrist(List<PtDirType> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }

}
