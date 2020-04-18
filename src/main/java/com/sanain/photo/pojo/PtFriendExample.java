package com.sanain.photo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtFriendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PtFriendExample() {
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

        public Criteria andSelfIdIsNull() {
            addCriterion("self_id is null");
            return (Criteria) this;
        }

        public Criteria andSelfIdIsNotNull() {
            addCriterion("self_id is not null");
            return (Criteria) this;
        }

        public Criteria andSelfIdEqualTo(Integer value) {
            addCriterion("self_id =", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdNotEqualTo(Integer value) {
            addCriterion("self_id <>", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdGreaterThan(Integer value) {
            addCriterion("self_id >", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("self_id >=", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdLessThan(Integer value) {
            addCriterion("self_id <", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdLessThanOrEqualTo(Integer value) {
            addCriterion("self_id <=", value, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdIn(List<Integer> values) {
            addCriterion("self_id in", values, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdNotIn(List<Integer> values) {
            addCriterion("self_id not in", values, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdBetween(Integer value1, Integer value2) {
            addCriterion("self_id between", value1, value2, "selfId");
            return (Criteria) this;
        }

        public Criteria andSelfIdNotBetween(Integer value1, Integer value2) {
            addCriterion("self_id not between", value1, value2, "selfId");
            return (Criteria) this;
        }

        public Criteria andFriendIdIsNull() {
            addCriterion("friend_id is null");
            return (Criteria) this;
        }

        public Criteria andFriendIdIsNotNull() {
            addCriterion("friend_id is not null");
            return (Criteria) this;
        }

        public Criteria andFriendIdEqualTo(Integer value) {
            addCriterion("friend_id =", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotEqualTo(Integer value) {
            addCriterion("friend_id <>", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThan(Integer value) {
            addCriterion("friend_id >", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend_id >=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThan(Integer value) {
            addCriterion("friend_id <", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThanOrEqualTo(Integer value) {
            addCriterion("friend_id <=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdIn(List<Integer> values) {
            addCriterion("friend_id in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotIn(List<Integer> values) {
            addCriterion("friend_id not in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdBetween(Integer value1, Integer value2) {
            addCriterion("friend_id between", value1, value2, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotBetween(Integer value1, Integer value2) {
            addCriterion("friend_id not between", value1, value2, "friendId");
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

        public Criteria andFriendPhotoIsNull() {
            addCriterion("friend_photo is null");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoIsNotNull() {
            addCriterion("friend_photo is not null");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoEqualTo(String value) {
            addCriterion("friend_photo =", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoNotEqualTo(String value) {
            addCriterion("friend_photo <>", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoGreaterThan(String value) {
            addCriterion("friend_photo >", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("friend_photo >=", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoLessThan(String value) {
            addCriterion("friend_photo <", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoLessThanOrEqualTo(String value) {
            addCriterion("friend_photo <=", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoLike(String value) {
            addCriterion("friend_photo like", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoNotLike(String value) {
            addCriterion("friend_photo not like", value, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoIn(List<String> values) {
            addCriterion("friend_photo in", values, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoNotIn(List<String> values) {
            addCriterion("friend_photo not in", values, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoBetween(String value1, String value2) {
            addCriterion("friend_photo between", value1, value2, "friendPhoto");
            return (Criteria) this;
        }

        public Criteria andFriendPhotoNotBetween(String value1, String value2) {
            addCriterion("friend_photo not between", value1, value2, "friendPhoto");
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