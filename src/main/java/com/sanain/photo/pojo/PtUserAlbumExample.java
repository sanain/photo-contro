package com.sanain.photo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtUserAlbumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PtUserAlbumExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIsNull() {
            addCriterion("cover_paths is null");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIsNotNull() {
            addCriterion("cover_paths is not null");
            return (Criteria) this;
        }

        public Criteria andCoverPathsEqualTo(String value) {
            addCriterion("cover_paths =", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotEqualTo(String value) {
            addCriterion("cover_paths <>", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsGreaterThan(String value) {
            addCriterion("cover_paths >", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsGreaterThanOrEqualTo(String value) {
            addCriterion("cover_paths >=", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLessThan(String value) {
            addCriterion("cover_paths <", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLessThanOrEqualTo(String value) {
            addCriterion("cover_paths <=", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLike(String value) {
            addCriterion("cover_paths like", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotLike(String value) {
            addCriterion("cover_paths not like", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIn(List<String> values) {
            addCriterion("cover_paths in", values, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotIn(List<String> values) {
            addCriterion("cover_paths not in", values, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsBetween(String value1, String value2) {
            addCriterion("cover_paths between", value1, value2, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotBetween(String value1, String value2) {
            addCriterion("cover_paths not between", value1, value2, "coverPaths");
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

        public Criteria andTempIdIsNull() {
            addCriterion("temp_id is null");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNotNull() {
            addCriterion("temp_id is not null");
            return (Criteria) this;
        }

        public Criteria andTempIdEqualTo(Integer value) {
            addCriterion("temp_id =", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotEqualTo(Integer value) {
            addCriterion("temp_id <>", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThan(Integer value) {
            addCriterion("temp_id >", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_id >=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThan(Integer value) {
            addCriterion("temp_id <", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThanOrEqualTo(Integer value) {
            addCriterion("temp_id <=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdIn(List<Integer> values) {
            addCriterion("temp_id in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotIn(List<Integer> values) {
            addCriterion("temp_id not in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdBetween(Integer value1, Integer value2) {
            addCriterion("temp_id between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotBetween(Integer value1, Integer value2) {
            addCriterion("temp_id not between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkIsNull() {
            addCriterion("album_remark is null");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkIsNotNull() {
            addCriterion("album_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkEqualTo(String value) {
            addCriterion("album_remark =", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkNotEqualTo(String value) {
            addCriterion("album_remark <>", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkGreaterThan(String value) {
            addCriterion("album_remark >", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("album_remark >=", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkLessThan(String value) {
            addCriterion("album_remark <", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkLessThanOrEqualTo(String value) {
            addCriterion("album_remark <=", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkLike(String value) {
            addCriterion("album_remark like", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkNotLike(String value) {
            addCriterion("album_remark not like", value, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkIn(List<String> values) {
            addCriterion("album_remark in", values, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkNotIn(List<String> values) {
            addCriterion("album_remark not in", values, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkBetween(String value1, String value2) {
            addCriterion("album_remark between", value1, value2, "albumRemark");
            return (Criteria) this;
        }

        public Criteria andAlbumRemarkNotBetween(String value1, String value2) {
            addCriterion("album_remark not between", value1, value2, "albumRemark");
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