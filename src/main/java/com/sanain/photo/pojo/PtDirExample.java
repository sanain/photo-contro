package com.sanain.photo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtDirExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PtDirExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDirIdIsNull() {
            addCriterion("dir_id is null");
            return (Criteria) this;
        }

        public Criteria andDirIdIsNotNull() {
            addCriterion("dir_id is not null");
            return (Criteria) this;
        }

        public Criteria andDirIdEqualTo(Integer value) {
            addCriterion("dir_id =", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotEqualTo(Integer value) {
            addCriterion("dir_id <>", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdGreaterThan(Integer value) {
            addCriterion("dir_id >", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dir_id >=", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdLessThan(Integer value) {
            addCriterion("dir_id <", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdLessThanOrEqualTo(Integer value) {
            addCriterion("dir_id <=", value, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdIn(List<Integer> values) {
            addCriterion("dir_id in", values, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotIn(List<Integer> values) {
            addCriterion("dir_id not in", values, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdBetween(Integer value1, Integer value2) {
            addCriterion("dir_id between", value1, value2, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dir_id not between", value1, value2, "dirId");
            return (Criteria) this;
        }

        public Criteria andDirNameIsNull() {
            addCriterion("dir_name is null");
            return (Criteria) this;
        }

        public Criteria andDirNameIsNotNull() {
            addCriterion("dir_name is not null");
            return (Criteria) this;
        }

        public Criteria andDirNameEqualTo(String value) {
            addCriterion("dir_name =", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotEqualTo(String value) {
            addCriterion("dir_name <>", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameGreaterThan(String value) {
            addCriterion("dir_name >", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameGreaterThanOrEqualTo(String value) {
            addCriterion("dir_name >=", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLessThan(String value) {
            addCriterion("dir_name <", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLessThanOrEqualTo(String value) {
            addCriterion("dir_name <=", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLike(String value) {
            addCriterion("dir_name like", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotLike(String value) {
            addCriterion("dir_name not like", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameIn(List<String> values) {
            addCriterion("dir_name in", values, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotIn(List<String> values) {
            addCriterion("dir_name not in", values, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameBetween(String value1, String value2) {
            addCriterion("dir_name between", value1, value2, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotBetween(String value1, String value2) {
            addCriterion("dir_name not between", value1, value2, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirUserIdIsNull() {
            addCriterion("dir_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDirUserIdIsNotNull() {
            addCriterion("dir_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDirUserIdEqualTo(Integer value) {
            addCriterion("dir_user_id =", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdNotEqualTo(Integer value) {
            addCriterion("dir_user_id <>", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdGreaterThan(Integer value) {
            addCriterion("dir_user_id >", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dir_user_id >=", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdLessThan(Integer value) {
            addCriterion("dir_user_id <", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("dir_user_id <=", value, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdIn(List<Integer> values) {
            addCriterion("dir_user_id in", values, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdNotIn(List<Integer> values) {
            addCriterion("dir_user_id not in", values, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdBetween(Integer value1, Integer value2) {
            addCriterion("dir_user_id between", value1, value2, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dir_user_id not between", value1, value2, "dirUserId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdIsNull() {
            addCriterion("dir_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdIsNotNull() {
            addCriterion("dir_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdEqualTo(Integer value) {
            addCriterion("dir_type_id =", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdNotEqualTo(Integer value) {
            addCriterion("dir_type_id <>", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdGreaterThan(Integer value) {
            addCriterion("dir_type_id >", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dir_type_id >=", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdLessThan(Integer value) {
            addCriterion("dir_type_id <", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("dir_type_id <=", value, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdIn(List<Integer> values) {
            addCriterion("dir_type_id in", values, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdNotIn(List<Integer> values) {
            addCriterion("dir_type_id not in", values, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("dir_type_id between", value1, value2, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andDirTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dir_type_id not between", value1, value2, "dirTypeId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDirImgIsNull() {
            addCriterion("dir_img is null");
            return (Criteria) this;
        }

        public Criteria andDirImgIsNotNull() {
            addCriterion("dir_img is not null");
            return (Criteria) this;
        }

        public Criteria andDirImgEqualTo(String value) {
            addCriterion("dir_img =", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgNotEqualTo(String value) {
            addCriterion("dir_img <>", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgGreaterThan(String value) {
            addCriterion("dir_img >", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgGreaterThanOrEqualTo(String value) {
            addCriterion("dir_img >=", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgLessThan(String value) {
            addCriterion("dir_img <", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgLessThanOrEqualTo(String value) {
            addCriterion("dir_img <=", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgLike(String value) {
            addCriterion("dir_img like", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgNotLike(String value) {
            addCriterion("dir_img not like", value, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgIn(List<String> values) {
            addCriterion("dir_img in", values, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgNotIn(List<String> values) {
            addCriterion("dir_img not in", values, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgBetween(String value1, String value2) {
            addCriterion("dir_img between", value1, value2, "dirImg");
            return (Criteria) this;
        }

        public Criteria andDirImgNotBetween(String value1, String value2) {
            addCriterion("dir_img not between", value1, value2, "dirImg");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}